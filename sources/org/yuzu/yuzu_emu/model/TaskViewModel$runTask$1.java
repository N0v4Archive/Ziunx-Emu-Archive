package org.yuzu.yuzu_emu.model;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TaskViewModel$runTask$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ TaskViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskViewModel$runTask$1(TaskViewModel taskViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = taskViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TaskViewModel$runTask$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((TaskViewModel$runTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        MutableStateFlow mutableStateFlow3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function3 task = this.this$0.getTask();
            final TaskViewModel taskViewModel = this.this$0;
            Function2 function2 = new Function2() { // from class: org.yuzu.yuzu_emu.model.TaskViewModel$runTask$1$res$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                public final Boolean invoke(long j, long j2) {
                    MutableStateFlow mutableStateFlow4;
                    MutableStateFlow mutableStateFlow5;
                    mutableStateFlow4 = TaskViewModel.this._maxProgress;
                    mutableStateFlow4.setValue(Double.valueOf(j));
                    mutableStateFlow5 = TaskViewModel.this._progress;
                    mutableStateFlow5.setValue(Double.valueOf(j2));
                    return (Boolean) TaskViewModel.this.getCancelled().getValue();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).longValue(), ((Number) obj3).longValue());
                }
            };
            final TaskViewModel taskViewModel2 = this.this$0;
            Function1 function1 = new Function1() { // from class: org.yuzu.yuzu_emu.model.TaskViewModel$runTask$1$res$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((String) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(String message) {
                    MutableStateFlow mutableStateFlow4;
                    Intrinsics.checkNotNullParameter(message, "message");
                    mutableStateFlow4 = TaskViewModel.this._message;
                    mutableStateFlow4.setValue(message);
                }
            };
            this.label = 1;
            obj = task.invoke(function2, function1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        mutableStateFlow = this.this$0._result;
        mutableStateFlow.setValue(obj);
        mutableStateFlow2 = this.this$0._isComplete;
        mutableStateFlow2.setValue(Boxing.boxBoolean(true));
        mutableStateFlow3 = this.this$0._isRunning;
        mutableStateFlow3.setValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }
}
