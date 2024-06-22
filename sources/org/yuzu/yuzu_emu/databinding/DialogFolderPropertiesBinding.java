package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.checkbox.MaterialCheckBox;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogFolderPropertiesBinding implements ViewBinding {
    public final LinearLayout deepScanLayout;
    public final MaterialCheckBox deepScanSwitch;
    private final LinearLayout rootView;

    private DialogFolderPropertiesBinding(LinearLayout linearLayout, LinearLayout linearLayout2, MaterialCheckBox materialCheckBox) {
        this.rootView = linearLayout;
        this.deepScanLayout = linearLayout2;
        this.deepScanSwitch = materialCheckBox;
    }

    public static DialogFolderPropertiesBinding bind(View view) {
        int i = R$id.deep_scan_layout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R$id.deep_scan_switch;
            MaterialCheckBox materialCheckBox = (MaterialCheckBox) ViewBindings.findChildViewById(view, i);
            if (materialCheckBox != null) {
                return new DialogFolderPropertiesBinding((LinearLayout) view, linearLayout, materialCheckBox);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogFolderPropertiesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogFolderPropertiesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_folder_properties, viewGroup, false);
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
