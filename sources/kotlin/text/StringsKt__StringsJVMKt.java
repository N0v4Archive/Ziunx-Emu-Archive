package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractList;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* loaded from: classes.dex */
public abstract class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    public static String concatToString(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new String(cArr);
    }

    public static String concatToString(char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, cArr.length);
        return new String(cArr, i, i2 - i);
    }

    public static boolean endsWith(String str, String suffix, boolean z) {
        boolean regionMatches;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!z) {
            return str.endsWith(suffix);
        }
        regionMatches = regionMatches(str, str.length() - suffix.length(), suffix, 0, suffix.length(), true);
        return regionMatches;
    }

    public static /* synthetic */ boolean endsWith$default(String str, String str2, boolean z, int i, Object obj) {
        boolean endsWith;
        if ((i & 2) != 0) {
            z = false;
        }
        endsWith = endsWith(str, str2, z);
        return endsWith;
    }

    public static boolean equals(String str, String str2, boolean z) {
        return str == null ? str2 == null : !z ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static boolean isBlank(CharSequence charSequence) {
        boolean z;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return true;
        }
        Iterable indices = StringsKt__StringsKt.getIndices(charSequence);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(((IntIterator) it).nextInt()))) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return z;
    }

    public static boolean regionMatches(String str, int i, String other, int i2, int i3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return !z ? str.regionMatches(i, other, i2, i3) : str.regionMatches(z, i, other, i2, i3);
    }

    public static String repeat(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i + '.').toString());
        }
        if (i == 0) {
            return "";
        }
        if (i == 1) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char charAt = charSequence.charAt(0);
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < i; i2++) {
                cArr[i2] = charAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() * i);
        IntIterator it = new IntRange(1, i).iterator();
        while (it.hasNext()) {
            it.nextInt();
            sb.append(charSequence);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNull(sb2);
        return sb2;
    }

    public static final String replace(String str, char c, char c2, boolean z) {
        String sb;
        String str2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (z) {
            StringBuilder sb2 = new StringBuilder(str.length());
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (CharsKt__CharKt.equals(charAt, c, z)) {
                    charAt = c2;
                }
                sb2.append(charAt);
            }
            sb = sb2.toString();
            str2 = "toString(...)";
        } else {
            sb = str.replace(c, c2);
            str2 = "replace(...)";
        }
        Intrinsics.checkNotNullExpressionValue(sb, str2);
        return sb;
    }

    public static final String replace(String str, String oldValue, String newValue, boolean z) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        int i = 0;
        int indexOf = StringsKt__StringsKt.indexOf(str, oldValue, 0, z);
        if (indexOf < 0) {
            return str;
        }
        int length = oldValue.length();
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i, indexOf);
            sb.append(newValue);
            i = indexOf + length;
            if (indexOf >= str.length()) {
                break;
            }
            indexOf = StringsKt__StringsKt.indexOf(str, oldValue, indexOf + coerceAtLeast, z);
        } while (indexOf > 0);
        sb.append((CharSequence) str, i, str.length());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static /* synthetic */ String replace$default(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return replace(str, c, c2, z);
    }

    public static /* synthetic */ String replace$default(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return replace(str, str2, str3, z);
    }

    public static boolean startsWith(String str, String prefix, int i, boolean z) {
        boolean regionMatches;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!z) {
            return str.startsWith(prefix, i);
        }
        regionMatches = regionMatches(str, i, prefix, 0, prefix.length(), z);
        return regionMatches;
    }

    public static boolean startsWith(String str, String prefix, boolean z) {
        boolean regionMatches;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!z) {
            return str.startsWith(prefix);
        }
        regionMatches = regionMatches(str, 0, prefix, 0, prefix.length(), z);
        return regionMatches;
    }

    public static /* synthetic */ boolean startsWith$default(String str, String str2, int i, boolean z, int i2, Object obj) {
        boolean startsWith;
        if ((i2 & 4) != 0) {
            z = false;
        }
        startsWith = startsWith(str, str2, i, z);
        return startsWith;
    }

    public static /* synthetic */ boolean startsWith$default(String str, String str2, boolean z, int i, Object obj) {
        boolean startsWith;
        if ((i & 2) != 0) {
            z = false;
        }
        startsWith = startsWith(str, str2, z);
        return startsWith;
    }
}
