package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentSettingsBinding implements ViewBinding {
    public final AppBarLayout appbarSettings;
    public final CoordinatorLayout coordinatorMain;
    public final RecyclerView listSettings;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar toolbarSettings;
    public final CollapsingToolbarLayout toolbarSettingsLayout;

    private FragmentSettingsBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout2, RecyclerView recyclerView, MaterialToolbar materialToolbar, CollapsingToolbarLayout collapsingToolbarLayout) {
        this.rootView = coordinatorLayout;
        this.appbarSettings = appBarLayout;
        this.coordinatorMain = coordinatorLayout2;
        this.listSettings = recyclerView;
        this.toolbarSettings = materialToolbar;
        this.toolbarSettingsLayout = collapsingToolbarLayout;
    }

    public static FragmentSettingsBinding bind(View view) {
        int i = R$id.appbar_settings;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i = R$id.list_settings;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R$id.toolbar_settings;
                MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                if (materialToolbar != null) {
                    i = R$id.toolbar_settings_layout;
                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, i);
                    if (collapsingToolbarLayout != null) {
                        return new FragmentSettingsBinding(coordinatorLayout, appBarLayout, coordinatorLayout, recyclerView, materialToolbar, collapsingToolbarLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_settings, viewGroup, false);
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
