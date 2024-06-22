package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardDriverOptionBinding implements ViewBinding {
    public final Button buttonDelete;
    public final MaterialTextView description;
    public final RadioButton radioButton;
    private final MaterialCardView rootView;
    public final MaterialTextView title;
    public final MaterialTextView version;

    private CardDriverOptionBinding(MaterialCardView materialCardView, Button button, MaterialTextView materialTextView, RadioButton radioButton, MaterialTextView materialTextView2, MaterialTextView materialTextView3) {
        this.rootView = materialCardView;
        this.buttonDelete = button;
        this.description = materialTextView;
        this.radioButton = radioButton;
        this.title = materialTextView2;
        this.version = materialTextView3;
    }

    public static CardDriverOptionBinding bind(View view) {
        int i = R$id.button_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.description;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                i = R$id.radio_button;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
                if (radioButton != null) {
                    i = R$id.title;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        i = R$id.version;
                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView3 != null) {
                            return new CardDriverOptionBinding((MaterialCardView) view, button, materialTextView, radioButton, materialTextView2, materialTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardDriverOptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_driver_option, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }
}
