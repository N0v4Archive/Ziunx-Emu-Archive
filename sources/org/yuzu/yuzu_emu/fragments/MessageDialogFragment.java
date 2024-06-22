package org.yuzu.yuzu_emu.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.model.MessageDialogViewModel;
import org.yuzu.yuzu_emu.utils.Log;

/* loaded from: classes.dex */
public final class MessageDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private final Lazy messageDialogViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MessageDialogFragment newInstance(FragmentActivity fragmentActivity, int i, String titleString, int i2, String descriptionString, int i3, boolean z, int i4, String positiveButtonTitleString, Function0 function0, boolean z2, int i5, String negativeButtonTitleString, Function0 function02) {
            boolean z3;
            Intrinsics.checkNotNullParameter(titleString, "titleString");
            Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
            Intrinsics.checkNotNullParameter(positiveButtonTitleString, "positiveButtonTitleString");
            Intrinsics.checkNotNullParameter(negativeButtonTitleString, "negativeButtonTitleString");
            if (fragmentActivity != null) {
                MessageDialogViewModel messageDialogViewModel = (MessageDialogViewModel) new ViewModelProvider(fragmentActivity).get(MessageDialogViewModel.class);
                messageDialogViewModel.clear();
                messageDialogViewModel.setPositiveAction(function0);
                messageDialogViewModel.setNegativeAction(function02);
                z3 = false;
            } else {
                z3 = true;
            }
            if (fragmentActivity == null && (function0 == null || function02 == null)) {
                Log.INSTANCE.warning("[MessageDialogFragment] Tried to set action with no activity!");
            }
            MessageDialogFragment messageDialogFragment = new MessageDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Title", i);
            bundle.putString("TitleString", titleString);
            bundle.putInt("DescriptionId", i2);
            bundle.putString("DescriptionString", descriptionString);
            bundle.putInt("Link", i3);
            bundle.putBoolean("Dismissible", z);
            bundle.putBoolean("ClearActions", z3);
            bundle.putInt("PositiveButtonTitleId", i4);
            bundle.putString("PositiveButtonTitleString", positiveButtonTitleString);
            bundle.putBoolean("ShowNegativeButton", z2);
            bundle.putInt("NegativeButtonTitleId", i5);
            bundle.putString("NegativeButtonTitleString", negativeButtonTitleString);
            messageDialogFragment.setArguments(bundle);
            return messageDialogFragment;
        }
    }

    public MessageDialogFragment() {
        final Function0 function0 = null;
        this.messageDialogViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MessageDialogViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.MessageDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.MessageDialogFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.MessageDialogFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final MessageDialogViewModel getMessageDialogViewModel() {
        return (MessageDialogViewModel) this.messageDialogViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(MessageDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 positiveAction = this$0.getMessageDialogViewModel().getPositiveAction();
        if (positiveAction != null) {
            positiveAction.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(MessageDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 negativeAction = this$0.getMessageDialogViewModel().getNegativeAction();
        if (negativeAction != null) {
            negativeAction.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$2(MessageDialogFragment this$0, int i, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.openLink(string);
    }

    private final void openLink(String str) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ff A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
    @Override // androidx.fragment.app.DialogFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.app.Dialog onCreateDialog(android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.fragments.MessageDialogFragment.onCreateDialog(android.os.Bundle):android.app.Dialog");
    }
}
