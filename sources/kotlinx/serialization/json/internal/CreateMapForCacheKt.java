package kotlinx.serialization.json.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public abstract class CreateMapForCacheKt {
    public static final Map createMapForCache(int i) {
        return new ConcurrentHashMap(i);
    }
}
