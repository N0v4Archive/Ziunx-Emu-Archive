package org.yuzu.yuzu_emu.features.settings.ui;

import androidx.fragment.app.FragmentActivity;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;

/* loaded from: classes.dex */
public final class SettingsFragment$onViewCreated$$inlined$collect$default$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ SettingsFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$$inlined$collect$default$5$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $this_collect;
        int label;
        final /* synthetic */ SettingsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Continuation continuation, SettingsFragment settingsFragment, SettingsFragment settingsFragment2) {
            super(2, continuation);
            this.$this_collect = flow;
            this.this$0 = settingsFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            Flow flow = this.$this_collect;
            SettingsFragment settingsFragment = this.this$0;
            return new AnonymousClass1(flow, continuation, settingsFragment, settingsFragment);
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
                SettingsFragment settingsFragment = this.this$0;
                FlowCollector flowCollector = new FlowCollector(settingsFragment) { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$.inlined.collect.default.5.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        SettingsViewModel settingsViewModel;
                        MessageDialogFragment newInstance;
                        if (((Boolean) obj2).booleanValue()) {
                            MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                            FragmentActivity requireActivity = SettingsFragment.this.requireActivity();
                            int i2 = R$string.reset_mapping;
                            int i3 = R$string.reset_mapping_description;
                            final SettingsFragment settingsFragment2 = SettingsFragment.this;
                            newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i2, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i3, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$12$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m113invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m113invoke() {
                                    int playerIndex;
                                    SettingsViewModel settingsViewModel2;
                                    NativeInput nativeInput = NativeInput.INSTANCE;
                                    playerIndex = SettingsFragment.this.getPlayerIndex();
                                    nativeInput.resetControllerMappings(playerIndex);
                                    settingsViewModel2 = SettingsFragment.this.getSettingsViewModel();
                                    settingsViewModel2.setReloadListAndNotifyDataset(true);
                                }
                            }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$12$2
                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m114invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m114invoke() {
                                }
                            } : null);
                            newInstance.show(SettingsFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                        }
                        settingsViewModel = SettingsFragment.this.getSettingsViewModel();
                        settingsViewModel.setShouldShowResetInputDialog(false);
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
    public SettingsFragment$onViewCreated$$inlined$collect$default$5(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, SettingsFragment settingsFragment, SettingsFragment settingsFragment2) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = settingsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LifecycleOwner lifecycleOwner = this.$this_apply;
        Lifecycle.State state = this.$repeatState;
        Flow flow = this.$this_collect;
        SettingsFragment settingsFragment = this.this$0;
        return new SettingsFragment$onViewCreated$$inlined$collect$default$5(lifecycleOwner, state, flow, continuation, settingsFragment, settingsFragment);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SettingsFragment$onViewCreated$$inlined$collect$default$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            Flow flow = this.$this_collect;
            SettingsFragment settingsFragment = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flow, null, settingsFragment, settingsFragment);
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
