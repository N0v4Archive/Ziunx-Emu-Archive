package coil.memory;

import android.graphics.Bitmap;
import androidx.collection.LruCache;
import coil.memory.MemoryCache;
import coil.memory.RealStrongMemoryCache;
import coil.util.Bitmaps;
import java.util.Map;

/* loaded from: classes.dex */
public final class RealStrongMemoryCache implements StrongMemoryCache {
    private final RealStrongMemoryCache$cache$1 cache;
    private final WeakMemoryCache weakMemoryCache;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InternalValue {
        private final Bitmap bitmap;
        private final Map extras;
        private final int size;

        public InternalValue(Bitmap bitmap, Map map, int i) {
            this.bitmap = bitmap;
            this.extras = map;
            this.size = i;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Map getExtras() {
            return this.extras;
        }

        public final int getSize() {
            return this.size;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [coil.memory.RealStrongMemoryCache$cache$1] */
    public RealStrongMemoryCache(final int i, WeakMemoryCache weakMemoryCache) {
        this.weakMemoryCache = weakMemoryCache;
        this.cache = new LruCache(i) { // from class: coil.memory.RealStrongMemoryCache$cache$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.collection.LruCache
            public void entryRemoved(boolean z, MemoryCache.Key key, RealStrongMemoryCache.InternalValue internalValue, RealStrongMemoryCache.InternalValue internalValue2) {
                WeakMemoryCache weakMemoryCache2;
                weakMemoryCache2 = this.weakMemoryCache;
                weakMemoryCache2.set(key, internalValue.getBitmap(), internalValue.getExtras(), internalValue.getSize());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.collection.LruCache
            public int sizeOf(MemoryCache.Key key, RealStrongMemoryCache.InternalValue internalValue) {
                return internalValue.getSize();
            }
        };
    }

    public void clearMemory() {
        evictAll();
    }

    @Override // coil.memory.StrongMemoryCache
    public MemoryCache.Value get(MemoryCache.Key key) {
        InternalValue internalValue = (InternalValue) get(key);
        if (internalValue != null) {
            return new MemoryCache.Value(internalValue.getBitmap(), internalValue.getExtras());
        }
        return null;
    }

    public int getMaxSize() {
        return maxSize();
    }

    public int getSize() {
        return size();
    }

    @Override // coil.memory.StrongMemoryCache
    public void set(MemoryCache.Key key, Bitmap bitmap, Map map) {
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        if (allocationByteCountCompat <= getMaxSize()) {
            put(key, new InternalValue(bitmap, map, allocationByteCountCompat));
        } else {
            remove(key);
            this.weakMemoryCache.set(key, bitmap, map, allocationByteCountCompat);
        }
    }

    @Override // coil.memory.StrongMemoryCache
    public void trimMemory(int i) {
        if (i >= 40) {
            clearMemory();
            return;
        }
        boolean z = false;
        if (10 <= i && i < 20) {
            z = true;
        }
        if (z) {
            trimToSize(getSize() / 2);
        }
    }
}
