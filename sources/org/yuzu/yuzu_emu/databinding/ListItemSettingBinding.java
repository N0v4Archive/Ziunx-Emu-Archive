package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemSettingBinding implements ViewBinding {
    public final MaterialButton buttonClear;
    public final ImageView icon;
    private final RelativeLayout rootView;
    public final MaterialTextView textSettingDescription;
    public final MaterialTextView textSettingName;
    public final MaterialTextView textSettingValue;

    private ListItemSettingBinding(RelativeLayout relativeLayout, MaterialButton materialButton, ImageView imageView, MaterialTextView materialTextView, MaterialTextView materialTextView2, MaterialTextView materialTextView3) {
        this.rootView = relativeLayout;
        this.buttonClear = materialButton;
        this.icon = imageView;
        this.textSettingDescription = materialTextView;
        this.textSettingName = materialTextView2;
        this.textSettingValue = materialTextView3;
    }

    public static ListItemSettingBinding bind(View view) {
        int i = R$id.button_clear;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R$id.icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R$id.text_setting_description;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R$id.text_setting_name;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        i = R$id.text_setting_value;
                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView3 != null) {
                            return new ListItemSettingBinding((RelativeLayout) view, materialButton, imageView, materialTextView, materialTextView2, materialTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static ListItemSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListItemSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_setting, viewGroup, false);
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
