package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity;

/* loaded from: classes.dex */
public final class ResetSettingsDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(SettingsActivity settingsActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(settingsActivity, "$settingsActivity");
        settingsActivity.onSettingsReset();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity");
        final SettingsActivity settingsActivity = (SettingsActivity) requireActivity;
        AlertDialog show = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.reset_all_settings).setMessage(R$string.reset_all_settings_description).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.ResetSettingsDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ResetSettingsDialogFragment.onCreateDialog$lambda$0(SettingsActivity.this, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }
}
