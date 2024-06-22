package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemSettingSwitchBinding implements ViewBinding {
    public final MaterialButton buttonClear;
    private final RelativeLayout rootView;
    public final MaterialSwitch switchWidget;
    public final MaterialTextView textSettingDescription;
    public final MaterialTextView textSettingName;

    private ListItemSettingSwitchBinding(RelativeLayout relativeLayout, MaterialButton materialButton, MaterialSwitch materialSwitch, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = relativeLayout;
        this.buttonClear = materialButton;
        this.switchWidget = materialSwitch;
        this.textSettingDescription = materialTextView;
        this.textSettingName = materialTextView2;
    }

    public static ListItemSettingSwitchBinding bind(View view) {
        int i = R$id.button_clear;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R$id.switch_widget;
            MaterialSwitch materialSwitch = (MaterialSwitch) ViewBindings.findChildViewById(view, i);
            if (materialSwitch != null) {
                i = R$id.text_setting_description;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R$id.text_setting_name;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        return new ListItemSettingSwitchBinding((RelativeLayout) view, materialButton, materialSwitch, materialTextView, materialTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static ListItemSettingSwitchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListItemSettingSwitchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_setting_switch, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
