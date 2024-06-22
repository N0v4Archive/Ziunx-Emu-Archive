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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentFoldersBinding implements ViewBinding {
    public final AppBarLayout appbarFolders;
    public final FloatingActionButton buttonAdd;
    public final ConstraintLayout coordinatorFolders;
    public final RecyclerView listFolders;
    private final ConstraintLayout rootView;
    public final MaterialToolbar toolbarFolders;

    private FragmentFoldersBinding(ConstraintLayout constraintLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton, ConstraintLayout constraintLayout2, RecyclerView recyclerView, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.appbarFolders = appBarLayout;
        this.buttonAdd = floatingActionButton;
        this.coordinatorFolders = constraintLayout2;
        this.listFolders = recyclerView;
        this.toolbarFolders = materialToolbar;
    }

    public static FragmentFoldersBinding bind(View view) {
        int i = R$id.appbar_folders;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            i = R$id.button_add;
            FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, i);
            if (floatingActionButton != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R$id.list_folders;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R$id.toolbar_folders;
                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                    if (materialToolbar != null) {
                        return new FragmentFoldersBinding(constraintLayout, appBarLayout, floatingActionButton, constraintLayout, recyclerView, materialToolbar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentFoldersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentFoldersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_folders, viewGroup, false);
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
