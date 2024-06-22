package org.yuzu.yuzu_emu.applets.keyboard.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard;
import org.yuzu.yuzu_emu.databinding.DialogEditTextBinding;

/* loaded from: classes.dex */
public final class KeyboardDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogEditTextBinding binding;
    private SoftwareKeyboard.KeyboardConfig config;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KeyboardDialogFragment newInstance(SoftwareKeyboard.KeyboardConfig keyboardConfig) {
            KeyboardDialogFragment keyboardDialogFragment = new KeyboardDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("keyboard_config", keyboardConfig);
            keyboardDialogFragment.setArguments(bundle);
            return keyboardDialogFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$2(KeyboardDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SoftwareKeyboard softwareKeyboard = SoftwareKeyboard.INSTANCE;
        softwareKeyboard.getData().setResult(SoftwareKeyboard.SwkbdResult.Ok.ordinal());
        SoftwareKeyboard.KeyboardData data = softwareKeyboard.getData();
        DialogEditTextBinding dialogEditTextBinding = this$0.binding;
        if (dialogEditTextBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogEditTextBinding = null;
        }
        data.setText(String.valueOf(dialogEditTextBinding.editText.getText()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$3(DialogInterface dialogInterface, int i) {
        SoftwareKeyboard.INSTANCE.getData().setResult(SoftwareKeyboard.SwkbdResult.Cancel.ordinal());
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0113, code lost:
    
        if (r8.getPassword_mode() == org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard.SwkbdPasswordMode.Enabled.ordinal()) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0116, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x014b, code lost:
    
        if (r8.getPassword_mode() == org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard.SwkbdPasswordMode.Enabled.ordinal()) goto L90;
     */
    @Override // androidx.fragment.app.DialogFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.app.Dialog onCreateDialog(android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 493
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.applets.keyboard.ui.KeyboardDialogFragment.onCreateDialog(android.os.Bundle):android.app.Dialog");
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        SoftwareKeyboard softwareKeyboard = SoftwareKeyboard.INSTANCE;
        synchronized (softwareKeyboard.getDataLock()) {
            softwareKeyboard.getDataLock().notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }
}
