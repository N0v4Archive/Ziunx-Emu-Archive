package org.yuzu.yuzu_emu.features.settings.ui;

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

/* loaded from: classes.dex */
public final class SettingsFragment$onViewCreated$$inlined$collect$default$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ SettingsFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$$inlined$collect$default$3$1, reason: invalid class name */
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
                FlowCollector flowCollector = new FlowCollector(settingsFragment) { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$.inlined.collect.default.3.1.1
                    /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
                    
                        r1 = r0.this$0.settingsAdapter;
                     */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            java.lang.Boolean r1 = (java.lang.Boolean) r1
                            boolean r1 = r1.booleanValue()
                            if (r1 == 0) goto L13
                            org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment r1 = org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment.this
                            org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r1 = org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment.access$getSettingsAdapter$p(r1)
                            if (r1 == 0) goto L13
                            r1.notifyDataSetChanged()
                        L13:
                            org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment r0 = org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment.this
                            org.yuzu.yuzu_emu.features.settings.ui.SettingsViewModel r0 = org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment.access$getSettingsViewModel(r0)
                            r1 = 0
                            r0.setDatasetChanged(r1)
                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$onViewCreated$$inlined$collect$default$3.AnonymousClass1.C00131.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
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
    public SettingsFragment$onViewCreated$$inlined$collect$default$3(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, SettingsFragment settingsFragment, SettingsFragment settingsFragment2) {
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
        return new SettingsFragment$onViewCreated$$inlined$collect$default$3(lifecycleOwner, state, flow, continuation, settingsFragment, settingsFragment);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SettingsFragment$onViewCreated$$inlined$collect$default$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
