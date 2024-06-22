package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeferredCoroutine extends AbstractCoroutine implements Deferred {
    public DeferredCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, true, z);
    }

    static /* synthetic */ Object await$suspendImpl(DeferredCoroutine deferredCoroutine, Continuation continuation) {
        Object awaitInternal = deferredCoroutine.awaitInternal(continuation);
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return awaitInternal;
    }

    @Override // kotlinx.coroutines.Deferred
    public Object await(Continuation continuation) {
        return await$suspendImpl(this, continuation);
    }
}
