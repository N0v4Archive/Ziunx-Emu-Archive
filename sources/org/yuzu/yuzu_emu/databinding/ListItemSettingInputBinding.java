package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemSettingInputBinding implements ViewBinding {
    public final Button buttonOptions;
    private final RelativeLayout rootView;
    public final RelativeLayout settingBody;
    public final MaterialTextView textSettingName;
    public final MaterialTextView textSettingValue;

    private ListItemSettingInputBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = relativeLayout;
        this.buttonOptions = button;
        this.settingBody = relativeLayout2;
        this.textSettingName = materialTextView;
        this.textSettingValue = materialTextView2;
    }

    public static ListItemSettingInputBinding bind(View view) {
        int i = R$id.button_options;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R$id.text_setting_name;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                i = R$id.text_setting_value;
                MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView2 != null) {
                    return new ListItemSettingInputBinding(relativeLayout, button, relativeLayout, materialTextView, materialTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static ListItemSettingInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListItemSettingInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_setting_input, viewGroup, false);
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
