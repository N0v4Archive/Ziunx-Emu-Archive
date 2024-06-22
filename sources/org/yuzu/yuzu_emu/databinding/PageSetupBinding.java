package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class PageSetupBinding implements ViewBinding {
    public final MaterialButton buttonAction;
    public final ImageView icon;
    private final View rootView;
    public final MaterialTextView textConfirmation;
    public final MaterialTextView textDescription;
    public final MaterialTextView textTitle;

    private PageSetupBinding(View view, MaterialButton materialButton, ImageView imageView, MaterialTextView materialTextView, MaterialTextView materialTextView2, MaterialTextView materialTextView3) {
        this.rootView = view;
        this.buttonAction = materialButton;
        this.icon = imageView;
        this.textConfirmation = materialTextView;
        this.textDescription = materialTextView2;
        this.textTitle = materialTextView3;
    }

    public static PageSetupBinding bind(View view) {
        int i = R$id.button_action;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R$id.icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R$id.text_confirmation;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R$id.text_description;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        i = R$id.text_title;
                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView3 != null) {
                            return new PageSetupBinding(view, materialButton, imageView, materialTextView, materialTextView2, materialTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static PageSetupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.page_setup, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }
}
