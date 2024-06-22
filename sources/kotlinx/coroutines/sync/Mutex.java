package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Mutex {
    Object lock(Object obj, Continuation continuation);

    void unlock(Object obj);
}
