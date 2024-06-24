package org.yuzu.yuzu_emu.fragments;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding;
import org.yuzu.yuzu_emu.model.EmulationViewModel;

/* loaded from: classes.dex */
public final class EmulationFragment$onViewCreated$$inlined$collect$default$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ EmulationFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$$inlined$collect$default$2$1, reason: invalid class name */
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
                FlowCollector flowCollector = new FlowCollector() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$.inlined.collect.default.2.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        EmulationViewModel emulationViewModel;
                        FragmentEmulationBinding binding;
                        FragmentEmulationBinding binding2;
                        EmulationViewModel emulationViewModel2;
                        FragmentEmulationBinding binding3;
                        FragmentEmulationBinding binding4;
                        FragmentEmulationBinding binding5;
                        int intValue = ((Number) obj2).intValue();
                        if (intValue > 0) {
                            emulationViewModel2 = EmulationFragment.this.getEmulationViewModel();
                            if (intValue != ((Number) emulationViewModel2.getTotalShaders().getValue()).intValue()) {
                                binding3 = EmulationFragment.this.getBinding();
                                binding3.loadingProgressIndicator.setIndeterminate(false);
                                binding4 = EmulationFragment.this.getBinding();
                                if (intValue < binding4.loadingProgressIndicator.getMax()) {
                                    binding5 = EmulationFragment.this.getBinding();
                                    binding5.loadingProgressIndicator.setProgress(intValue);
                                }
                            }
                        }
                        emulationViewModel = EmulationFragment.this.getEmulationViewModel();
                        if (intValue == ((Number) emulationViewModel.getTotalShaders().getValue()).intValue()) {
                            binding = EmulationFragment.this.getBinding();
                            binding.loadingText.setText(R$string.loading);
                            binding2 = EmulationFragment.this.getBinding();
                            binding2.loadingProgressIndicator.setIndeterminate(true);
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
    public EmulationFragment$onViewCreated$$inlined$collect$default$2(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, EmulationFragment emulationFragment) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = emulationFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EmulationFragment$onViewCreated$$inlined$collect$default$2(this.$this_apply, this.$repeatState, this.$this_collect, continuation, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((EmulationFragment$onViewCreated$$inlined$collect$default$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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