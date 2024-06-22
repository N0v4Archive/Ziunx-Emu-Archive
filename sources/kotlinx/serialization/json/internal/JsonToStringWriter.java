package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* loaded from: classes.dex */
public final class JsonToStringWriter implements JsonWriter {
    private char[] array = CharArrayPool.INSTANCE.take();
    private int size;

    private final void appendStringSlowPath(int i, int i2, String str) {
        int i3;
        int length = str.length();
        while (i < length) {
            int ensureTotalCapacity = ensureTotalCapacity(i2, 2);
            char charAt = str.charAt(i);
            if (charAt < StringOpsKt.getESCAPE_MARKERS().length) {
                byte b = StringOpsKt.getESCAPE_MARKERS()[charAt];
                if (b == 0) {
                    i3 = ensureTotalCapacity + 1;
                    this.array[ensureTotalCapacity] = charAt;
                } else {
                    if (b == 1) {
                        String str2 = StringOpsKt.getESCAPE_STRINGS()[charAt];
                        Intrinsics.checkNotNull(str2);
                        int ensureTotalCapacity2 = ensureTotalCapacity(ensureTotalCapacity, str2.length());
                        str2.getChars(0, str2.length(), this.array, ensureTotalCapacity2);
                        i2 = ensureTotalCapacity2 + str2.length();
                    } else {
                        char[] cArr = this.array;
                        cArr[ensureTotalCapacity] = '\\';
                        cArr[ensureTotalCapacity + 1] = (char) b;
                        i2 = ensureTotalCapacity + 2;
                    }
                    this.size = i2;
                    i++;
                }
            } else {
                i3 = ensureTotalCapacity + 1;
                this.array[ensureTotalCapacity] = charAt;
            }
            i2 = i3;
            i++;
        }
        int ensureTotalCapacity3 = ensureTotalCapacity(i2, 1);
        this.array[ensureTotalCapacity3] = '\"';
        this.size = ensureTotalCapacity3 + 1;
    }

    private final void ensureAdditionalCapacity(int i) {
        ensureTotalCapacity(this.size, i);
    }

    private final int ensureTotalCapacity(int i, int i2) {
        int coerceAtLeast;
        int i3 = i2 + i;
        char[] cArr = this.array;
        if (cArr.length <= i3) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i3, i * 2);
            char[] copyOf = Arrays.copyOf(cArr, coerceAtLeast);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.array = copyOf;
        }
        return i;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void write(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        if (length == 0) {
            return;
        }
        ensureAdditionalCapacity(length);
        text.getChars(0, text.length(), this.array, this.size);
        this.size += length;
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void writeChar(char c) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.array;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = c;
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void writeQuoted(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ensureAdditionalCapacity(text.length() + 2);
        char[] cArr = this.array;
        int i = this.size;
        int i2 = i + 1;
        cArr[i] = '\"';
        int length = text.length();
        text.getChars(0, length, cArr, i2);
        int i3 = length + i2;
        for (int i4 = i2; i4 < i3; i4++) {
            char c = cArr[i4];
            if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c] != 0) {
                appendStringSlowPath(i4 - i2, i4, text);
                return;
            }
        }
        cArr[i3] = '\"';
        this.size = i3 + 1;
    }
}
