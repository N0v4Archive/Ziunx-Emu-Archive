package kotlinx.coroutines;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Deferred extends Job {
    Object await(Continuation continuation);
}
