package info.debatty.java.stringsimilarity;

import info.debatty.java.stringsimilarity.interfaces.StringSimilarity;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class Cosine extends ShingleBased implements Serializable, StringSimilarity {
    public Cosine() {
    }

    public Cosine(int i) {
        super(i);
    }

    private static double dotProduct(Map map, Map map2) {
        if (map.size() >= map2.size()) {
            map2 = map;
            map = map2;
        }
        Iterator it = map.entrySet().iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            if (((Integer) map2.get(((Map.Entry) it.next()).getKey())) != null) {
                d += ((Integer) r2.getValue()).intValue() * 1.0d * r3.intValue();
            }
        }
        return d;
    }

    private static double norm(Map map) {
        double d = 0.0d;
        for (Map.Entry entry : map.entrySet()) {
            d += ((Integer) entry.getValue()).intValue() * 1.0d * ((Integer) entry.getValue()).intValue();
        }
        return Math.sqrt(d);
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
        if (str.length() < getK() || str2.length() < getK()) {
            return 0.0d;
        }
        Map profile = getProfile(str);
        Map profile2 = getProfile(str2);
        return dotProduct(profile, profile2) / (norm(profile) * norm(profile2));
    }
}
