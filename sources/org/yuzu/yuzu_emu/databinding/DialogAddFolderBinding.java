package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogAddFolderBinding implements ViewBinding {
    public final MaterialCheckBox deepScanSwitch;
    public final MaterialTextView path;
    private final LinearLayout rootView;

    private DialogAddFolderBinding(LinearLayout linearLayout, MaterialCheckBox materialCheckBox, MaterialTextView materialTextView) {
        this.rootView = linearLayout;
        this.deepScanSwitch = materialCheckBox;
        this.path = materialTextView;
    }

    public static DialogAddFolderBinding bind(View view) {
        int i = R$id.deep_scan_switch;
        MaterialCheckBox materialCheckBox = (MaterialCheckBox) ViewBindings.findChildViewById(view, i);
        if (materialCheckBox != null) {
            i = R$id.path;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                return new DialogAddFolderBinding((LinearLayout) view, materialCheckBox, materialTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogAddFolderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAddFolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_add_folder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
