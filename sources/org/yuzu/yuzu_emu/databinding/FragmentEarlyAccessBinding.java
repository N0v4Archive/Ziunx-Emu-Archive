package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentEarlyAccessBinding implements ViewBinding {
    public final AppBarLayout appbarEa;
    public final LinearLayout cardEa;
    public final CoordinatorLayout coordinatorAbout;
    public final MaterialCardView getEarlyAccessButton;
    private final CoordinatorLayout rootView;
    public final NestedScrollView scrollEa;
    public final MaterialToolbar toolbarAbout;

    private FragmentEarlyAccessBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, LinearLayout linearLayout, CoordinatorLayout coordinatorLayout2, MaterialCardView materialCardView, NestedScrollView nestedScrollView, MaterialToolbar materialToolbar) {
        this.rootView = coordinatorLayout;
        this.appbarEa = appBarLayout;
        this.cardEa = linearLayout;
        this.coordinatorAbout = coordinatorLayout2;
        this.getEarlyAccessButton = materialCardView;
        this.scrollEa = nestedScrollView;
        this.toolbarAbout = materialToolbar;
    }

    public static FragmentEarlyAccessBinding bind(View view) {
        int i = R$id.appbar_ea;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            i = R$id.card_ea;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                i = R$id.get_early_access_button;
                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
                if (materialCardView != null) {
                    i = R$id.scroll_ea;
                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                    if (nestedScrollView != null) {
                        i = R$id.toolbar_about;
                        MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                        if (materialToolbar != null) {
                            return new FragmentEarlyAccessBinding(coordinatorLayout, appBarLayout, linearLayout, coordinatorLayout, materialCardView, nestedScrollView, materialToolbar);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentEarlyAccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentEarlyAccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_early_access, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
