package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

/* loaded from: classes.dex */
public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 implements LifecycleEventObserver {
    final /* synthetic */ Function0 $block;
    final /* synthetic */ CancellableContinuation $co;
    final /* synthetic */ Lifecycle.State $state;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked;

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Object m45constructorimpl;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event != Lifecycle.Event.Companion.upTo(this.$state)) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
                CancellableContinuation cancellableContinuation = this.$co;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m45constructorimpl(ResultKt.createFailure(new LifecycleDestroyedException())));
                return;
            }
            return;
        }
        this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
        CancellableContinuation cancellableContinuation2 = this.$co;
        Function0 function0 = this.$block;
        try {
            Result.Companion companion2 = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(function0.invoke());
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(ResultKt.createFailure(th));
        }
        cancellableContinuation2.resumeWith(m45constructorimpl);
    }
}
