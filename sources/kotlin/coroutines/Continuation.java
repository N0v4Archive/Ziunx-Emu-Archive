package kotlin.coroutines;

/* loaded from: classes.dex */
public interface Continuation {
    CoroutineContext getContext();

    void resumeWith(Object obj);
}
