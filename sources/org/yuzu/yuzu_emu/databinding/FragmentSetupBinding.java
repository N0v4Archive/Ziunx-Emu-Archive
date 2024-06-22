package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentSetupBinding implements ViewBinding {
    public final MaterialButton buttonBack;
    public final MaterialButton buttonNext;
    public final ConstraintLayout constraintButtons;
    private final RelativeLayout rootView;
    public final RelativeLayout setupRoot;
    public final ViewPager2 viewPager2;

    private FragmentSetupBinding(RelativeLayout relativeLayout, MaterialButton materialButton, MaterialButton materialButton2, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.buttonBack = materialButton;
        this.buttonNext = materialButton2;
        this.constraintButtons = constraintLayout;
        this.setupRoot = relativeLayout2;
        this.viewPager2 = viewPager2;
    }

    public static FragmentSetupBinding bind(View view) {
        int i = R$id.button_back;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R$id.button_next;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R$id.constraint_buttons;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i = R$id.viewPager2;
                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                    if (viewPager2 != null) {
                        return new FragmentSetupBinding(relativeLayout, materialButton, materialButton2, constraintLayout, relativeLayout, viewPager2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentSetupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSetupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_setup, viewGroup, false);
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
