package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class CardGameBinding implements ViewBinding {
    public final MaterialCardView cardGame;
    public final ShapeableImageView imageGameScreen;
    private final FrameLayout rootView;
    public final MaterialTextView textGameTitle;

    private CardGameBinding(FrameLayout frameLayout, MaterialCardView materialCardView, ShapeableImageView shapeableImageView, MaterialTextView materialTextView) {
        this.rootView = frameLayout;
        this.cardGame = materialCardView;
        this.imageGameScreen = shapeableImageView;
        this.textGameTitle = materialTextView;
    }

    public static CardGameBinding bind(View view) {
        int i = R$id.card_game;
        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
        if (materialCardView != null) {
            i = R$id.image_game_screen;
            ShapeableImageView shapeableImageView = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
            if (shapeableImageView != null) {
                i = R$id.text_game_title;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    return new CardGameBinding((FrameLayout) view, materialCardView, shapeableImageView, materialTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static CardGameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.card_game, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
