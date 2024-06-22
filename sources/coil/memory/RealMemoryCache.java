package coil.memory;

import coil.memory.MemoryCache;
import coil.util.Collections;

/* loaded from: classes.dex */
public final class RealMemoryCache implements MemoryCache {
    private final StrongMemoryCache strongMemoryCache;
    private final WeakMemoryCache weakMemoryCache;

    public RealMemoryCache(StrongMemoryCache strongMemoryCache, WeakMemoryCache weakMemoryCache) {
        this.strongMemoryCache = strongMemoryCache;
        this.weakMemoryCache = weakMemoryCache;
    }

    @Override // coil.memory.MemoryCache
    public MemoryCache.Value get(MemoryCache.Key key) {
        MemoryCache.Value value = this.strongMemoryCache.get(key);
        return value == null ? this.weakMemoryCache.get(key) : value;
    }

    @Override // coil.memory.MemoryCache
    public void set(MemoryCache.Key key, MemoryCache.Value value) {
        this.strongMemoryCache.set(MemoryCache.Key.copy$default(key, null, Collections.toImmutableMap(key.getExtras()), 1, null), value.getBitmap(), Collections.toImmutableMap(value.getExtras()));
    }

    @Override // coil.memory.MemoryCache
    public void trimMemory(int i) {
        this.strongMemoryCache.trimMemory(i);
        this.weakMemoryCache.trimMemory(i);
    }
}
