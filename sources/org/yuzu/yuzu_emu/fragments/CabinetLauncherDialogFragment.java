package org.yuzu.yuzu_emu.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.CabinetLauncherDialogAdapter;
import org.yuzu.yuzu_emu.databinding.DialogListBinding;

/* loaded from: classes.dex */
public final class CabinetLauncherDialogFragment extends DialogFragment {
    private DialogListBinding binding;

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogListBinding inflate = DialogListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogListBinding dialogListBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        RecyclerView recyclerView = inflate.dialogList;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new CabinetLauncherDialogAdapter(this));
        MaterialAlertDialogBuilder title = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.cabinet_launcher);
        DialogListBinding dialogListBinding2 = this.binding;
        if (dialogListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogListBinding = dialogListBinding2;
        }
        AlertDialog create = title.setView((View) dialogListBinding.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogListBinding dialogListBinding = this.binding;
        if (dialogListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogListBinding = null;
        }
        LinearLayoutCompat root = dialogListBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
}
