package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardSimpleOutlinedBinding implements ViewBinding {
    public final MaterialTextView description;
    public final MaterialTextView details;
    public final ImageView icon;
    private final MaterialCardView rootView;
    public final MaterialTextView title;

    private CardSimpleOutlinedBinding(MaterialCardView materialCardView, MaterialTextView materialTextView, MaterialTextView materialTextView2, ImageView imageView, MaterialTextView materialTextView3) {
        this.rootView = materialCardView;
        this.description = materialTextView;
        this.details = materialTextView2;
        this.icon = imageView;
        this.title = materialTextView3;
    }

    public static CardSimpleOutlinedBinding bind(View view) {
        int i = R$id.description;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
        if (materialTextView != null) {
            i = R$id.details;
            MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView2 != null) {
                i = R$id.icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R$id.title;
                    MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView3 != null) {
                        return new CardSimpleOutlinedBinding((MaterialCardView) view, materialTextView, materialTextView2, imageView, materialTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardSimpleOutlinedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_simple_outlined, viewGroup, false);
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
