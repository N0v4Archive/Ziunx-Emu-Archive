package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ResumeAwaitOnCompletion extends JobNode {
    private final CancellableContinuationImpl continuation;

    public ResumeAwaitOnCompletion(CancellableContinuationImpl cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void invoke(Throwable th) {
        Object unboxState;
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        boolean z = state$kotlinx_coroutines_core instanceof CompletedExceptionally;
        CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
        if (z) {
            Result.Companion companion = Result.Companion;
            unboxState = ResultKt.createFailure(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
        } else {
            Result.Companion companion2 = Result.Companion;
            unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        }
        cancellableContinuationImpl.resumeWith(Result.m45constructorimpl(unboxState));
    }
}
