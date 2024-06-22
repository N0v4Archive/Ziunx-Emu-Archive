package androidx.arch.core.internal;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class FastSafeIterableMap extends SafeIterableMap {
    private final HashMap mHashMap = new HashMap();

    public Map.Entry ceil(Object obj) {
        if (contains(obj)) {
            return ((SafeIterableMap.Entry) this.mHashMap.get(obj)).mPrevious;
        }
        return null;
    }

    public boolean contains(Object obj) {
        return this.mHashMap.containsKey(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    protected SafeIterableMap.Entry get(Object obj) {
        return (SafeIterableMap.Entry) this.mHashMap.get(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public Object putIfAbsent(Object obj, Object obj2) {
        SafeIterableMap.Entry entry = get(obj);
        if (entry != null) {
            return entry.mValue;
        }
        this.mHashMap.put(obj, put(obj, obj2));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        this.mHashMap.remove(obj);
        return remove;
    }
}
