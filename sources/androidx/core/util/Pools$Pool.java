package androidx.core.util;

/* loaded from: classes.dex */
public interface Pools$Pool {
    Object acquire();

    boolean release(Object obj);
}
