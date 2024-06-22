package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardInstallableBinding implements ViewBinding {
    public final Button buttonExport;
    public final Button buttonInstall;
    public final MaterialTextView description;
    private final MaterialCardView rootView;
    public final MaterialTextView title;

    private CardInstallableBinding(MaterialCardView materialCardView, Button button, Button button2, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = materialCardView;
        this.buttonExport = button;
        this.buttonInstall = button2;
        this.description = materialTextView;
        this.title = materialTextView2;
    }

    public static CardInstallableBinding bind(View view) {
        int i = R$id.button_export;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.button_install;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R$id.description;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R$id.title;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        return new CardInstallableBinding((MaterialCardView) view, button, button2, materialTextView, materialTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardInstallableBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_installable, viewGroup, false);
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
