package org.yuzu.yuzu_emu.fragments;

import android.content.Intent;
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
import org.yuzu.yuzu_emu.model.AddonViewModel;

/* loaded from: classes.dex */
public final class AddonsFragment$onViewCreated$$inlined$collect$default$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ AddonsFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$$inlined$collect$default$2$1, reason: invalid class name */
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
                FlowCollector flowCollector = new FlowCollector(addonsFragment) { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$onViewCreated$.inlined.collect.default.2.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        AddonViewModel addonViewModel;
                        if (((Boolean) obj2).booleanValue()) {
                            AddonsFragment.this.getInstallAddon().launch(new Intent("android.intent.action.OPEN_DOCUMENT_TREE").getData());
                        }
                        addonViewModel = AddonsFragment.this.getAddonViewModel();
                        addonViewModel.showModInstallPicker(false);
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
    public AddonsFragment$onViewCreated$$inlined$collect$default$2(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, AddonsFragment addonsFragment, AddonsFragment addonsFragment2) {
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
        return new AddonsFragment$onViewCreated$$inlined$collect$default$2(lifecycleOwner, state, flow, continuation, addonsFragment, addonsFragment);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AddonsFragment$onViewCreated$$inlined$collect$default$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
