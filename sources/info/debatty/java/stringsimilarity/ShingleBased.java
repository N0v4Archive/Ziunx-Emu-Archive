package info.debatty.java.stringsimilarity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public abstract class ShingleBased {
    private static final Pattern SPACE_REG = Pattern.compile("\\s+");
    private final int k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShingleBased() {
        this(3);
    }

    public ShingleBased(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("k should be positive!");
        }
        this.k = i;
    }

    public final int getK() {
        return this.k;
    }

    public final Map getProfile(String str) {
        HashMap hashMap = new HashMap();
        String replaceAll = SPACE_REG.matcher(str).replaceAll(" ");
        int i = 0;
        while (true) {
            int length = replaceAll.length();
            int i2 = this.k;
            if (i >= (length - i2) + 1) {
                return Collections.unmodifiableMap(hashMap);
            }
            String substring = replaceAll.substring(i, i2 + i);
            Integer num = (Integer) hashMap.get(substring);
            hashMap.put(substring, num != null ? Integer.valueOf(num.intValue() + 1) : 1);
            i++;
        }
    }
}
