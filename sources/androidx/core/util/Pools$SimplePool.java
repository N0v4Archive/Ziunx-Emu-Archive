package androidx.core.util;

/* loaded from: classes.dex */
public class Pools$SimplePool implements Pools$Pool {
    private final Object[] mPool;
    private int mPoolSize;

    public Pools$SimplePool(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.mPool = new Object[i];
    }

    private boolean isInPool(Object obj) {
        for (int i = 0; i < this.mPoolSize; i++) {
            if (this.mPool[i] == obj) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.core.util.Pools$Pool
    public Object acquire() {
        int i = this.mPoolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.mPool;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.mPoolSize = i - 1;
        return obj;
    }

    @Override // androidx.core.util.Pools$Pool
    public boolean release(Object obj) {
        if (isInPool(obj)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.mPoolSize;
        Object[] objArr = this.mPool;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = obj;
        this.mPoolSize = i + 1;
        return true;
    }
}
