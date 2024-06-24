package org.yuzu.yuzu_emu.fragments;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.google.android.material.card.MaterialCardView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.fragments.EmulationFragment;
import org.yuzu.yuzu_emu.overlay.InputOverlay;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class EmulationFragment$onViewCreated$$inlined$collect$default$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ EmulationFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$$inlined$collect$default$5$1, reason: invalid class name */
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
                FlowCollector flowCollector = new FlowCollector() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$.inlined.collect.default.5.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        FragmentEmulationBinding binding;
                        FragmentEmulationBinding binding2;
                        FragmentEmulationBinding binding3;
                        EmulationFragment.EmulationState emulationState;
                        if (((Boolean) obj2).booleanValue()) {
                            binding = EmulationFragment.this.getBinding();
                            EmulationFragment.EmulationState emulationState2 = null;
                            binding.drawerLayout.setDrawerLockMode(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.LOCK_DRAWER, false, 1, null));
                            ViewUtils viewUtils = ViewUtils.INSTANCE;
                            binding2 = EmulationFragment.this.getBinding();
                            InputOverlay surfaceInputOverlay = binding2.surfaceInputOverlay;
                            Intrinsics.checkNotNullExpressionValue(surfaceInputOverlay, "surfaceInputOverlay");
                            ViewUtils.showView$default(viewUtils, surfaceInputOverlay, 0L, 2, null);
                            binding3 = EmulationFragment.this.getBinding();
                            MaterialCardView loadingIndicator = binding3.loadingIndicator;
                            Intrinsics.checkNotNullExpressionValue(loadingIndicator, "loadingIndicator");
                            ViewUtils.hideView$default(viewUtils, loadingIndicator, 0L, 2, null);
                            emulationState = EmulationFragment.this.emulationState;
                            if (emulationState == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("emulationState");
                            } else {
                                emulationState2 = emulationState;
                            }
                            emulationState2.updateSurface();
                            EmulationFragment.this.updateShowFpsOverlay();
                            EmulationFragment.this.updateThermalOverlay();
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
    public EmulationFragment$onViewCreated$$inlined$collect$default$5(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, EmulationFragment emulationFragment) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = emulationFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EmulationFragment$onViewCreated$$inlined$collect$default$5(this.$this_apply, this.$repeatState, this.$this_collect, continuation, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((EmulationFragment$onViewCreated$$inlined$collect$default$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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