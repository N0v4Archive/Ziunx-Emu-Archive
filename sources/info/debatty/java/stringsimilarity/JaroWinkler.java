package info.debatty.java.stringsimilarity;

import info.debatty.java.stringsimilarity.interfaces.StringSimilarity;
import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes.dex */
public class JaroWinkler implements StringSimilarity, Serializable {
    private final double threshold = 0.7d;

    private int[] matches(String str, String str2) {
        String str3;
        String str4;
        if (str.length() > str2.length()) {
            str4 = str;
            str3 = str2;
        } else {
            str3 = str;
            str4 = str2;
        }
        int max = Math.max((str4.length() / 2) - 1, 0);
        int[] iArr = new int[str3.length()];
        Arrays.fill(iArr, -1);
        boolean[] zArr = new boolean[str4.length()];
        int i = 0;
        for (int i2 = 0; i2 < str3.length(); i2++) {
            char charAt = str3.charAt(i2);
            int max2 = Math.max(i2 - max, 0);
            int min = Math.min(i2 + max + 1, str4.length());
            while (true) {
                if (max2 >= min) {
                    break;
                }
                if (!zArr[max2] && charAt == str4.charAt(max2)) {
                    iArr[i2] = max2;
                    zArr[max2] = true;
                    i++;
                    break;
                }
                max2++;
            }
        }
        char[] cArr = new char[i];
        char[] cArr2 = new char[i];
        int i3 = 0;
        for (int i4 = 0; i4 < str3.length(); i4++) {
            if (iArr[i4] != -1) {
                cArr[i3] = str3.charAt(i4);
                i3++;
            }
        }
        int i5 = 0;
        for (int i6 = 0; i6 < str4.length(); i6++) {
            if (zArr[i6]) {
                cArr2[i5] = str4.charAt(i6);
                i5++;
            }
        }
        int i7 = 0;
        for (int i8 = 0; i8 < i; i8++) {
            if (cArr[i8] != cArr2[i8]) {
                i7++;
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < str3.length() && str.charAt(i10) == str2.charAt(i10); i10++) {
            i9++;
        }
        return new int[]{i, i7 / 2, i9, str4.length()};
    }

    public final double getThreshold() {
        return this.threshold;
    }

    @Override // info.debatty.java.stringsimilarity.interfaces.StringSimilarity
    public final double similarity(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("s1 must not be null");
        }
        if (str2 == null) {
            throw new NullPointerException("s2 must not be null");
        }
        if (str.equals(str2)) {
            return 1.0d;
        }
        float f = matches(str, str2)[0];
        if (f == 0.0f) {
            return 0.0d;
        }
        double length = (((f / str.length()) + (f / str2.length())) + ((f - r0[1]) / f)) / 3.0f;
        return length > getThreshold() ? length + (Math.min(0.1d, 1.0d / r0[3]) * r0[2] * (1.0d - length)) : length;
    }
}
