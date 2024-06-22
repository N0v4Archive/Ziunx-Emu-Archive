package androidx.constraintlayout.core;

/* loaded from: classes.dex */
interface Pools$Pool {
    Object acquire();

    boolean release(Object obj);

    void releaseAll(Object[] objArr, int i);
}
