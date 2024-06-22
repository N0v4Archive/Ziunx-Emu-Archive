package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemAddonBinding implements ViewBinding {
    public final MaterialCheckBox addonCheckbox;
    public final ConstraintLayout addonContainer;
    public final Button buttonDelete;
    private final ConstraintLayout rootView;
    public final LinearLayout textContainer;
    public final MaterialTextView title;
    public final MaterialTextView version;

    private ListItemAddonBinding(ConstraintLayout constraintLayout, MaterialCheckBox materialCheckBox, ConstraintLayout constraintLayout2, Button button, LinearLayout linearLayout, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = constraintLayout;
        this.addonCheckbox = materialCheckBox;
        this.addonContainer = constraintLayout2;
        this.buttonDelete = button;
        this.textContainer = linearLayout;
        this.title = materialTextView;
        this.version = materialTextView2;
    }

    public static ListItemAddonBinding bind(View view) {
        int i = R$id.addon_checkbox;
        MaterialCheckBox materialCheckBox = (MaterialCheckBox) ViewBindings.findChildViewById(view, i);
        if (materialCheckBox != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R$id.button_delete;
            Button button = (Button) ViewBindings.findChildViewById(view, i);
            if (button != null) {
                i = R$id.text_container;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R$id.title;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        i = R$id.version;
                        MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView2 != null) {
                            return new ListItemAddonBinding(constraintLayout, materialCheckBox, constraintLayout, button, linearLayout, materialTextView, materialTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static ListItemAddonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_addon, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
