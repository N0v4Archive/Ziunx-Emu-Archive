package kotlin.text;

import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static String drop(String str, int i) {
        int coerceAtMost;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, str.length());
            String substring = str.substring(coerceAtMost);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    public static char last(CharSequence charSequence) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        return charSequence.charAt(lastIndex);
    }

    public static String take(String str, int i) {
        int coerceAtMost;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, str.length());
            String substring = str.substring(0, coerceAtMost);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }
}
