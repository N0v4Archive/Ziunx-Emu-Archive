package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentGamePropertiesBinding implements ViewBinding {
    public final Button buttonBack;
    public final Button buttonShortcut;
    public final ExtendedFloatingActionButton buttonStart;
    public final LinearLayout iconLayout;
    public final ImageView imageGameScreen;
    public final LinearLayout layoutAll;
    public final NestedScrollView listAll;
    public final RecyclerView listProperties;
    private final ConstraintLayout rootView;
    public final MaterialTextView title;

    private FragmentGamePropertiesBinding(ConstraintLayout constraintLayout, Button button, Button button2, ExtendedFloatingActionButton extendedFloatingActionButton, LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, NestedScrollView nestedScrollView, RecyclerView recyclerView, MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.buttonBack = button;
        this.buttonShortcut = button2;
        this.buttonStart = extendedFloatingActionButton;
        this.iconLayout = linearLayout;
        this.imageGameScreen = imageView;
        this.layoutAll = linearLayout2;
        this.listAll = nestedScrollView;
        this.listProperties = recyclerView;
        this.title = materialTextView;
    }

    public static FragmentGamePropertiesBinding bind(View view) {
        int i = R$id.button_back;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.button_shortcut;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R$id.button_start;
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                if (extendedFloatingActionButton != null) {
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R$id.icon_layout);
                    i = R$id.image_game_screen;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R$id.layout_all;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R$id.list_all;
                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                            if (nestedScrollView != null) {
                                i = R$id.list_properties;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView != null) {
                                    i = R$id.title;
                                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                    if (materialTextView != null) {
                                        return new FragmentGamePropertiesBinding((ConstraintLayout) view, button, button2, extendedFloatingActionButton, linearLayout, imageView, linearLayout2, nestedScrollView, recyclerView, materialTextView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentGamePropertiesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentGamePropertiesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_game_properties, viewGroup, false);
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
