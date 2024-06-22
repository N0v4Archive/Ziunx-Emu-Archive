package kotlinx.serialization.json.internal;

import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class CharArrayPoolBase {
    private final ArrayDeque arrays = new ArrayDeque();
    private int charsTotal;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void releaseImpl(char[] array) {
        int i;
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            int length = this.charsTotal + array.length;
            i = ArrayPoolsKt.MAX_CHARS_IN_POOL;
            if (length < i) {
                this.charsTotal += array.length;
                this.arrays.addLast(array);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final char[] take(int i) {
        char[] cArr;
        synchronized (this) {
            cArr = (char[]) this.arrays.removeLastOrNull();
            if (cArr != null) {
                this.charsTotal -= cArr.length;
            } else {
                cArr = null;
            }
        }
        return cArr == null ? new char[i] : cArr;
    }
}
