package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final ConstraintLayout coordinatorMain;
    public final FragmentContainerView fragmentContainer;
    public final View navigationBarShade;
    public final View navigationView;
    private final ConstraintLayout rootView;
    public final View statusBarShade;

    private ActivityMainBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FragmentContainerView fragmentContainerView, View view, View view2, View view3) {
        this.rootView = constraintLayout;
        this.coordinatorMain = constraintLayout2;
        this.fragmentContainer = fragmentContainerView;
        this.navigationBarShade = view;
        this.navigationView = view2;
        this.statusBarShade = view3;
    }

    public static ActivityMainBinding bind(View view) {
        View findChildViewById;
        View findChildViewById2;
        View findChildViewById3;
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R$id.fragment_container;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
        if (fragmentContainerView == null || (findChildViewById = ViewBindings.findChildViewById(view, (i = R$id.navigation_bar_shade))) == null || (findChildViewById2 = ViewBindings.findChildViewById(view, (i = R$id.navigation_view))) == null || (findChildViewById3 = ViewBindings.findChildViewById(view, (i = R$id.status_bar_shade))) == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
        }
        return new ActivityMainBinding(constraintLayout, constraintLayout, fragmentContainerView, findChildViewById, findChildViewById2, findChildViewById3);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.activity_main, viewGroup, false);
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
