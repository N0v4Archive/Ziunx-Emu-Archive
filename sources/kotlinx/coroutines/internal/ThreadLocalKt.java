package kotlinx.coroutines.internal;

/* loaded from: classes.dex */
public abstract class ThreadLocalKt {
    public static final ThreadLocal commonThreadLocal(Symbol symbol) {
        return new ThreadLocal();
    }
}
