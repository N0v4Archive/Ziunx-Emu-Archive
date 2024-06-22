package org.yuzu.yuzu_emu.fragments;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.yuzu.yuzu_emu.fragments.EmulationFragment;
import org.yuzu.yuzu_emu.model.EmulationViewModel;

/* loaded from: classes.dex */
public final class EmulationFragment$onViewCreated$$inlined$collect$default$9 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ EmulationFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$$inlined$collect$default$9$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $this_collect;
        int label;
        final /* synthetic */ EmulationFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Continuation continuation, EmulationFragment emulationFragment) {
            super(2, continuation);
            this.$this_collect = flow;
            this.this$0 = emulationFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$this_collect, continuation, this.this$0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.$this_collect;
                final EmulationFragment emulationFragment = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$.inlined.collect.default.9.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        EmulationViewModel emulationViewModel;
                        Function0 function0;
                        EmulationFragment.EmulationState emulationState;
                        EmulationViewModel emulationViewModel2;
                        EmulationViewModel emulationViewModel3;
                        EmulationViewModel emulationViewModel4;
                        Handler handler;
                        final Function0 function02;
                        if (((Boolean) obj2).booleanValue()) {
                            emulationViewModel = EmulationFragment.this.getEmulationViewModel();
                            if (((Number) emulationViewModel.getProgramChanged().getValue()).intValue() != -1) {
                                function0 = EmulationFragment.this.perfStatsUpdater;
                                if (function0 != null) {
                                    handler = EmulationFragment.perfStatsUpdateHandler;
                                    function02 = EmulationFragment.this.perfStatsUpdater;
                                    Intrinsics.checkNotNull(function02);
                                    handler.removeCallbacks(new Runnable(function02) { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$sam$java_lang_Runnable$0
                                        private final /* synthetic */ Function0 function;

                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            Intrinsics.checkNotNullParameter(function02, "function");
                                            this.function = function02;
                                        }

                                        @Override // java.lang.Runnable
                                        public final /* synthetic */ void run() {
                                            this.function.invoke();
                                        }
                                    });
                                }
                                emulationState = EmulationFragment.this.emulationState;
                                if (emulationState == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("emulationState");
                                    emulationState = null;
                                }
                                emulationViewModel2 = EmulationFragment.this.getEmulationViewModel();
                                emulationState.changeProgram(((Number) emulationViewModel2.getProgramChanged().getValue()).intValue());
                                emulationViewModel3 = EmulationFragment.this.getEmulationViewModel();
                                emulationViewModel3.setProgramChanged(-1);
                                emulationViewModel4 = EmulationFragment.this.getEmulationViewModel();
                                emulationViewModel4.setEmulationStopped(false);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmulationFragment$onViewCreated$$inlined$collect$default$9(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, EmulationFragment emulationFragment) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = emulationFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EmulationFragment$onViewCreated$$inlined$collect$default$9(this.$this_apply, this.$repeatState, this.$this_collect, continuation, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((EmulationFragment$onViewCreated$$inlined$collect$default$9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = this.$this_apply;
            Lifecycle.State state = this.$repeatState;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_collect, null, this.this$0);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
