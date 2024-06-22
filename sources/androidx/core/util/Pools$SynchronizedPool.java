package androidx.core.util;

/* loaded from: classes.dex */
public class Pools$SynchronizedPool extends Pools$SimplePool {
    private final Object mLock;

    public Pools$SynchronizedPool(int i) {
        super(i);
        this.mLock = new Object();
    }

    @Override // androidx.core.util.Pools$SimplePool, androidx.core.util.Pools$Pool
    public Object acquire() {
        Object acquire;
        synchronized (this.mLock) {
            acquire = super.acquire();
        }
        return acquire;
    }

    @Override // androidx.core.util.Pools$SimplePool, androidx.core.util.Pools$Pool
    public boolean release(Object obj) {
        boolean release;
        synchronized (this.mLock) {
            release = super.release(obj);
        }
        return release;
    }
}
