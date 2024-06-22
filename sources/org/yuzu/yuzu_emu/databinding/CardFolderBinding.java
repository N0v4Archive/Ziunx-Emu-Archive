package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardFolderBinding implements ViewBinding {
    public final Button buttonDelete;
    public final Button buttonEdit;
    public final LinearLayout buttonLayout;
    public final MaterialTextView path;
    private final MaterialCardView rootView;

    private CardFolderBinding(MaterialCardView materialCardView, Button button, Button button2, LinearLayout linearLayout, MaterialTextView materialTextView) {
        this.rootView = materialCardView;
        this.buttonDelete = button;
        this.buttonEdit = button2;
        this.buttonLayout = linearLayout;
        this.path = materialTextView;
    }

    public static CardFolderBinding bind(View view) {
        int i = R$id.button_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.button_edit;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R$id.button_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R$id.path;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        return new CardFolderBinding((MaterialCardView) view, button, button2, linearLayout, materialTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardFolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_folder, viewGroup, false);
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
