package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Semaphore {
    Object acquire(Continuation continuation);

    void release();
}
