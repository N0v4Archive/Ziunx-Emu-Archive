package org.yuzu.yuzu_emu.features.settings.ui;

import android.R;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;

/* loaded from: classes.dex */
public final class InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Lifecycle.State $repeatState;
    final /* synthetic */ LifecycleOwner $this_apply;
    final /* synthetic */ Flow $this_collect;
    int label;
    final /* synthetic */ InputProfileDialogFragment this$0;

    /* renamed from: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $this_collect;
        int label;
        final /* synthetic */ InputProfileDialogFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Continuation continuation, InputProfileDialogFragment inputProfileDialogFragment) {
            super(2, continuation);
            this.$this_collect = flow;
            this.this$0 = inputProfileDialogFragment;
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
                final InputProfileDialogFragment inputProfileDialogFragment = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onViewCreated$.inlined.collect.default.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        MessageDialogFragment newInstance;
                        SettingsViewModel settingsViewModel;
                        final String str = (String) obj2;
                        if (str.length() > 0) {
                            MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                            FragmentActivity requireActivity = InputProfileDialogFragment.this.requireActivity();
                            int i2 = R$string.delete_input_profile;
                            int i3 = R$string.delete_input_profile_description;
                            final InputProfileDialogFragment inputProfileDialogFragment2 = InputProfileDialogFragment.this;
                            newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i2, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i3, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onViewCreated$1$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m105invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m105invoke() {
                                    InputProfileSetting inputProfileSetting;
                                    SettingsViewModel settingsViewModel2;
                                    inputProfileSetting = InputProfileDialogFragment.this.setting;
                                    if (inputProfileSetting == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("setting");
                                        inputProfileSetting = null;
                                    }
                                    inputProfileSetting.deleteProfile(str);
                                    settingsViewModel2 = InputProfileDialogFragment.this.getSettingsViewModel();
                                    settingsViewModel2.setReloadListAndNotifyDataset(true);
                                }
                            }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? R.string.cancel : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onViewCreated$1$2
                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Object invoke() {
                                    m106invoke();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: collision with other method in class */
                                public final void m106invoke() {
                                }
                            } : null);
                            newInstance.show(InputProfileDialogFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                            settingsViewModel = InputProfileDialogFragment.this.getSettingsViewModel();
                            settingsViewModel.setShouldShowDeleteProfileDialog("");
                            InputProfileDialogFragment.this.dismiss();
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
    public InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow flow, Continuation continuation, InputProfileDialogFragment inputProfileDialogFragment) {
        super(2, continuation);
        this.$this_apply = lifecycleOwner;
        this.$repeatState = state;
        this.$this_collect = flow;
        this.this$0 = inputProfileDialogFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1(this.$this_apply, this.$repeatState, this.$this_collect, continuation, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
