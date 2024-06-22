package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public interface CancellableContinuation extends Continuation {
    void completeResume(Object obj);

    void invokeOnCancellation(Function1 function1);

    void resume(Object obj, Function1 function1);

    void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, Object obj);

    Object tryResume(Object obj, Object obj2, Function1 function1);
}
