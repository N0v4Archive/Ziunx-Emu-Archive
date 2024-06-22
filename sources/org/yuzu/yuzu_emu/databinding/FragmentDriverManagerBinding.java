package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentDriverManagerBinding implements ViewBinding {
    public final AppBarLayout appbarDrivers;
    public final ExtendedFloatingActionButton buttonInstall;
    public final ConstraintLayout coordinatorLicenses;
    public final RecyclerView listDrivers;
    private final ConstraintLayout rootView;
    public final MaterialToolbar toolbarDrivers;

    private FragmentDriverManagerBinding(ConstraintLayout constraintLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton, ConstraintLayout constraintLayout2, RecyclerView recyclerView, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.appbarDrivers = appBarLayout;
        this.buttonInstall = extendedFloatingActionButton;
        this.coordinatorLicenses = constraintLayout2;
        this.listDrivers = recyclerView;
        this.toolbarDrivers = materialToolbar;
    }

    public static FragmentDriverManagerBinding bind(View view) {
        int i = R$id.appbar_drivers;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            i = R$id.button_install;
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
            if (extendedFloatingActionButton != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R$id.list_drivers;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R$id.toolbar_drivers;
                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                    if (materialToolbar != null) {
                        return new FragmentDriverManagerBinding(constraintLayout, appBarLayout, extendedFloatingActionButton, constraintLayout, recyclerView, materialToolbar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentDriverManagerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDriverManagerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_driver_manager, viewGroup, false);
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
