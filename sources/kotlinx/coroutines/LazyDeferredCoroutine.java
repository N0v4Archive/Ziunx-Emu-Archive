package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class LazyDeferredCoroutine extends DeferredCoroutine {
    private final Continuation continuation;

    public LazyDeferredCoroutine(CoroutineContext coroutineContext, Function2 function2) {
        super(coroutineContext, false);
        Continuation createCoroutineUnintercepted;
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, this, this);
        this.continuation = createCoroutineUnintercepted;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void onStart() {
        CancellableKt.startCoroutineCancellable(this.continuation, this);
    }
}
