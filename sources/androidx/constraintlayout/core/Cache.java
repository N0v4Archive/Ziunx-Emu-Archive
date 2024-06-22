package androidx.constraintlayout.core;

/* loaded from: classes.dex */
public class Cache {
    Pools$Pool arrayRowPool;
    SolverVariable[] mIndexedVariables = new SolverVariable[32];
    Pools$Pool optimizedArrayRowPool;
    Pools$Pool solverVariablePool;

    public Cache() {
        final int i = 256;
        this.optimizedArrayRowPool = new Pools$Pool(i) { // from class: androidx.constraintlayout.core.Pools$SimplePool
            private final Object[] mPool;
            private int mPoolSize;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (i <= 0) {
                    throw new IllegalArgumentException("The max pool size must be > 0");
                }
                this.mPool = new Object[i];
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public Object acquire() {
                int i2 = this.mPoolSize;
                if (i2 <= 0) {
                    return null;
                }
                int i3 = i2 - 1;
                Object[] objArr = this.mPool;
                Object obj = objArr[i3];
                objArr[i3] = null;
                this.mPoolSize = i2 - 1;
                return obj;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public boolean release(Object obj) {
                int i2 = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = obj;
                this.mPoolSize = i2 + 1;
                return true;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public void releaseAll(Object[] objArr, int i2) {
                if (i2 > objArr.length) {
                    i2 = objArr.length;
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    Object obj = objArr[i3];
                    int i4 = this.mPoolSize;
                    Object[] objArr2 = this.mPool;
                    if (i4 < objArr2.length) {
                        objArr2[i4] = obj;
                        this.mPoolSize = i4 + 1;
                    }
                }
            }
        };
        this.arrayRowPool = new Pools$Pool(i) { // from class: androidx.constraintlayout.core.Pools$SimplePool
            private final Object[] mPool;
            private int mPoolSize;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (i <= 0) {
                    throw new IllegalArgumentException("The max pool size must be > 0");
                }
                this.mPool = new Object[i];
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public Object acquire() {
                int i2 = this.mPoolSize;
                if (i2 <= 0) {
                    return null;
                }
                int i3 = i2 - 1;
                Object[] objArr = this.mPool;
                Object obj = objArr[i3];
                objArr[i3] = null;
                this.mPoolSize = i2 - 1;
                return obj;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public boolean release(Object obj) {
                int i2 = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = obj;
                this.mPoolSize = i2 + 1;
                return true;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public void releaseAll(Object[] objArr, int i2) {
                if (i2 > objArr.length) {
                    i2 = objArr.length;
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    Object obj = objArr[i3];
                    int i4 = this.mPoolSize;
                    Object[] objArr2 = this.mPool;
                    if (i4 < objArr2.length) {
                        objArr2[i4] = obj;
                        this.mPoolSize = i4 + 1;
                    }
                }
            }
        };
        this.solverVariablePool = new Pools$Pool(i) { // from class: androidx.constraintlayout.core.Pools$SimplePool
            private final Object[] mPool;
            private int mPoolSize;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (i <= 0) {
                    throw new IllegalArgumentException("The max pool size must be > 0");
                }
                this.mPool = new Object[i];
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public Object acquire() {
                int i2 = this.mPoolSize;
                if (i2 <= 0) {
                    return null;
                }
                int i3 = i2 - 1;
                Object[] objArr = this.mPool;
                Object obj = objArr[i3];
                objArr[i3] = null;
                this.mPoolSize = i2 - 1;
                return obj;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public boolean release(Object obj) {
                int i2 = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = obj;
                this.mPoolSize = i2 + 1;
                return true;
            }

            @Override // androidx.constraintlayout.core.Pools$Pool
            public void releaseAll(Object[] objArr, int i2) {
                if (i2 > objArr.length) {
                    i2 = objArr.length;
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    Object obj = objArr[i3];
                    int i4 = this.mPoolSize;
                    Object[] objArr2 = this.mPool;
                    if (i4 < objArr2.length) {
                        objArr2[i4] = obj;
                        this.mPoolSize = i4 + 1;
                    }
                }
            }
        };
    }
}
