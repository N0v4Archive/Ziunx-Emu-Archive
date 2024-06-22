package org.yuzu.yuzu_emu.fragments;

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
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.Patch;

/* loaded from: classes.dex */
public final class AddonsFragment$onViewCreated$$inlined$collect$default$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ AddonsFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$$inlined$collect$default$4$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $this_collect;
        int label;
        final /* synthetic */ AddonsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Continuation continuation, AddonsFragment addonsFragment, AddonsFragment addonsFragment2) {
            super(2, continuation);
            this.$this_collect = flow;
            this.this$0 = addonsFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            Flow flow = this.$this_collect;
            AddonsFragment addonsFragment = this.this$0;
            return new AnonymousClass1(flow, continuation, addonsFragment, addonsFragment);
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
                AddonsFragment addonsFragment = this.this$0;
                FlowCollector flowCollector = new FlowCollector(addonsFragment) { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$.inlined.collect.default.4.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        AddonViewModel addonViewModel;
                        MessageDialogFragment newInstance;
                        final Patch patch = (Patch) obj2;
                        if (patch != null) {
                            MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                            FragmentActivity requireActivity = AddonsFragment.this.requireActivity();
                            int i2 = R$string.confirm_uninstall;
                            int i3 = R$string.confirm_uninstall_description;
                            final AddonsFragment addonsFragment2 = AddonsFragment.this;
                            newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i2, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i3, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$9$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m127invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m127invoke() {
                                    AddonViewModel addonViewModel2;
                                    addonViewModel2 = AddonsFragment.this.getAddonViewModel();
                                    addonViewModel2.onDeleteAddon(patch);
                                }
                            }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$9$2
                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m128invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m128invoke() {
                                }
                            } : null);
                            newInstance.show(AddonsFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                        }
                        addonViewModel = AddonsFragment.this.getAddonViewModel();
                        addonViewModel.setAddonToDelete(null);
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
    public AddonsFragment$onViewCreated$$inlined$collect$default$4(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, AddonsFragment addonsFragment, AddonsFragment addonsFragment2) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = addonsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LifecycleOwner lifecycleOwner = this.$this_apply;
        Lifecycle.State state = this.$repeatState;
        Flow flow = this.$this_collect;
        AddonsFragment addonsFragment = this.this$0;
        return new AddonsFragment$onViewCreated$$inlined$collect$default$4(lifecycleOwner, state, flow, continuation, addonsFragment, addonsFragment);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AddonsFragment$onViewCreated$$inlined$collect$default$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AddonsFragment addonsFragment = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flow, null, addonsFragment, addonsFragment);
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
