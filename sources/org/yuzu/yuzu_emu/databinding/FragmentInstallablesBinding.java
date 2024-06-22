package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentInstallablesBinding implements ViewBinding {
    public final AppBarLayout appbarInstallables;
    public final CoordinatorLayout coordinatorLicenses;
    public final RecyclerView listInstallables;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar toolbarInstallables;

    private FragmentInstallablesBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout2, RecyclerView recyclerView, MaterialToolbar materialToolbar) {
        this.rootView = coordinatorLayout;
        this.appbarInstallables = appBarLayout;
        this.coordinatorLicenses = coordinatorLayout2;
        this.listInstallables = recyclerView;
        this.toolbarInstallables = materialToolbar;
    }

    public static FragmentInstallablesBinding bind(View view) {
        int i = R$id.appbar_installables;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i = R$id.list_installables;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R$id.toolbar_installables;
                MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                if (materialToolbar != null) {
                    return new FragmentInstallablesBinding(coordinatorLayout, appBarLayout, coordinatorLayout, recyclerView, materialToolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentInstallablesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentInstallablesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_installables, viewGroup, false);
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
