package org.yuzu.yuzu_emu.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;

/* loaded from: classes.dex */
public final class SetupWarningDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private int descriptionId;
    private int helpLinkId;
    private int page;
    private SetupFragment setupFragment;
    private int titleId;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SetupWarningDialogFragment newInstance(int i, int i2, int i3, int i4) {
            SetupWarningDialogFragment setupWarningDialogFragment = new SetupWarningDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Title", i);
            bundle.putInt("Description", i2);
            bundle.putInt("HelpLink", i3);
            bundle.putInt("Page", i4);
            setupWarningDialogFragment.setArguments(bundle);
            return setupWarningDialogFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(SetupWarningDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SetupFragment setupFragment = this$0.setupFragment;
        SetupFragment setupFragment2 = null;
        if (setupFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setupFragment");
            setupFragment = null;
        }
        setupFragment.pageForward();
        SetupFragment setupFragment3 = this$0.setupFragment;
        if (setupFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setupFragment");
        } else {
            setupFragment2 = setupFragment3;
        }
        setupFragment2.setPageWarned(this$0.page);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(SetupWarningDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getResources().getString(R$string.install_prod_keys_warning_help);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleId = requireArguments().getInt("Title");
        this.descriptionId = requireArguments().getInt("Description");
        this.helpLinkId = requireArguments().getInt("HelpLink");
        this.page = requireArguments().getInt("Page");
        Fragment requireParentFragment = requireParentFragment();
        Intrinsics.checkNotNull(requireParentFragment, "null cannot be cast to non-null type org.yuzu.yuzu_emu.fragments.SetupFragment");
        this.setupFragment = (SetupFragment) requireParentFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        MaterialAlertDialogBuilder negativeButton = new MaterialAlertDialogBuilder(requireContext()).setPositiveButton(R$string.warning_skip, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SetupWarningDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SetupWarningDialogFragment.onCreateDialog$lambda$0(SetupWarningDialogFragment.this, dialogInterface, i);
            }
        }).setNegativeButton(R$string.warning_cancel, (DialogInterface.OnClickListener) null);
        Intrinsics.checkNotNullExpressionValue(negativeButton, "setNegativeButton(...)");
        int i = this.titleId;
        if (i != 0) {
            negativeButton.setTitle(i);
        } else {
            negativeButton.setTitle((CharSequence) "");
        }
        int i2 = this.descriptionId;
        if (i2 != 0) {
            negativeButton.setMessage(i2);
        }
        if (this.helpLinkId != 0) {
            negativeButton.setNeutralButton(R$string.warning_help, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SetupWarningDialogFragment$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    SetupWarningDialogFragment.onCreateDialog$lambda$1(SetupWarningDialogFragment.this, dialogInterface, i3);
                }
            });
        }
        AlertDialog show = negativeButton.show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }
}
