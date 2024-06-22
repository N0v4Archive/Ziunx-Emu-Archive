package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public final class RealWeakMemoryCache implements WeakMemoryCache {
    public static final Companion Companion = new Companion(null);
    private final LinkedHashMap cache = new LinkedHashMap();
    private int operationsSinceCleanUp;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class InternalValue {
        private final WeakReference bitmap;
        private final Map extras;
        private final int identityHashCode;
        private final int size;

        public InternalValue(int i, WeakReference weakReference, Map map, int i2) {
            this.identityHashCode = i;
            this.bitmap = weakReference;
            this.extras = map;
            this.size = i2;
        }

        public final WeakReference getBitmap() {
            return this.bitmap;
        }

        public final Map getExtras() {
            return this.extras;
        }

        public final int getIdentityHashCode() {
            return this.identityHashCode;
        }

        public final int getSize() {
            return this.size;
        }
    }

    private final void cleanUpIfNecessary() {
        int i = this.operationsSinceCleanUp;
        this.operationsSinceCleanUp = i + 1;
        if (i >= 10) {
            cleanUp$coil_base_release();
        }
    }

    public final void cleanUp$coil_base_release() {
        Object firstOrNull;
        WeakReference bitmap;
        this.operationsSinceCleanUp = 0;
        Iterator it = this.cache.values().iterator();
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            if (arrayList.size() <= 1) {
                firstOrNull = CollectionsKt___CollectionsKt.firstOrNull(arrayList);
                InternalValue internalValue = (InternalValue) firstOrNull;
                if (((internalValue == null || (bitmap = internalValue.getBitmap()) == null) ? null : (Bitmap) bitmap.get()) == null) {
                    it.remove();
                }
            } else {
                int size = arrayList.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    int i3 = i2 - i;
                    if (((InternalValue) arrayList.get(i3)).getBitmap().get() == null) {
                        arrayList.remove(i3);
                        i++;
                    }
                }
                if (arrayList.isEmpty()) {
                    it.remove();
                }
            }
        }
    }

    @Override // coil.memory.WeakMemoryCache
    public synchronized MemoryCache.Value get(MemoryCache.Key key) {
        ArrayList arrayList = (ArrayList) this.cache.get(key);
        MemoryCache.Value value = null;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            InternalValue internalValue = (InternalValue) arrayList.get(i);
            Bitmap bitmap = (Bitmap) internalValue.getBitmap().get();
            MemoryCache.Value value2 = bitmap != null ? new MemoryCache.Value(bitmap, internalValue.getExtras()) : null;
            if (value2 != null) {
                value = value2;
                break;
            }
            i++;
        }
        cleanUpIfNecessary();
        return value;
    }

    @Override // coil.memory.WeakMemoryCache
    public synchronized void set(MemoryCache.Key key, Bitmap bitmap, Map map, int i) {
        LinkedHashMap linkedHashMap = this.cache;
        Object obj = linkedHashMap.get(key);
        if (obj == null) {
            obj = new ArrayList();
            linkedHashMap.put(key, obj);
        }
        ArrayList arrayList = (ArrayList) obj;
        int identityHashCode = System.identityHashCode(bitmap);
        InternalValue internalValue = new InternalValue(identityHashCode, new WeakReference(bitmap), map, i);
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                arrayList.add(internalValue);
                break;
            }
            InternalValue internalValue2 = (InternalValue) arrayList.get(i2);
            if (i < internalValue2.getSize()) {
                i2++;
            } else if (internalValue2.getIdentityHashCode() == identityHashCode && internalValue2.getBitmap().get() == bitmap) {
                arrayList.set(i2, internalValue);
            } else {
                arrayList.add(i2, internalValue);
            }
        }
        cleanUpIfNecessary();
    }

    @Override // coil.memory.WeakMemoryCache
    public synchronized void trimMemory(int i) {
        if (i >= 10 && i != 20) {
            cleanUp$coil_base_release();
        }
    }
}
