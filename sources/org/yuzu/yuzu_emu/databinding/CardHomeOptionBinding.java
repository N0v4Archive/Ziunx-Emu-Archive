package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardHomeOptionBinding implements ViewBinding {
    public final MaterialCardView optionCard;
    public final MaterialTextView optionDescription;
    public final MaterialTextView optionDetail;
    public final ImageView optionIcon;
    public final LinearLayout optionLayout;
    public final MaterialTextView optionTitle;
    private final MaterialCardView rootView;

    private CardHomeOptionBinding(MaterialCardView materialCardView, MaterialCardView materialCardView2, MaterialTextView materialTextView, MaterialTextView materialTextView2, ImageView imageView, LinearLayout linearLayout, MaterialTextView materialTextView3) {
        this.rootView = materialCardView;
        this.optionCard = materialCardView2;
        this.optionDescription = materialTextView;
        this.optionDetail = materialTextView2;
        this.optionIcon = imageView;
        this.optionLayout = linearLayout;
        this.optionTitle = materialTextView3;
    }

    public static CardHomeOptionBinding bind(View view) {
        MaterialCardView materialCardView = (MaterialCardView) view;
        int i = R$id.option_description;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
        if (materialTextView != null) {
            i = R$id.option_detail;
            MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView2 != null) {
                i = R$id.option_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R$id.option_layout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R$id.option_title;
                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView3 != null) {
                            return new CardHomeOptionBinding(materialCardView, materialCardView, materialTextView, materialTextView2, imageView, linearLayout, materialTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardHomeOptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_home_option, viewGroup, false);
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
