package info.debatty.java.stringsimilarity;

import info.debatty.java.stringsimilarity.interfaces.StringSimilarity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes.dex */
public class Jaccard extends ShingleBased implements Serializable, StringSimilarity {
    public Jaccard(int i) {
        super(i);
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
        Map profile = getProfile(str);
        Map profile2 = getProfile(str2);
        HashSet hashSet = new HashSet();
        hashSet.addAll(profile.keySet());
        hashSet.addAll(profile2.keySet());
        return (((profile.keySet().size() + profile2.keySet().size()) - hashSet.size()) * 1.0d) / hashSet.size();
    }
}
