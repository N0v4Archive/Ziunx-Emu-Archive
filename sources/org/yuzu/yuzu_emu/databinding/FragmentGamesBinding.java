package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentGamesBinding implements ViewBinding {
    public final RecyclerView gridGames;
    public final MaterialTextView noticeText;
    private final SwipeRefreshLayout rootView;
    public final SwipeRefreshLayout swipeRefresh;

    private FragmentGamesBinding(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, MaterialTextView materialTextView, SwipeRefreshLayout swipeRefreshLayout2) {
        this.rootView = swipeRefreshLayout;
        this.gridGames = recyclerView;
        this.noticeText = materialTextView;
        this.swipeRefresh = swipeRefreshLayout2;
    }

    public static FragmentGamesBinding bind(View view) {
        int i = R$id.grid_games;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R$id.notice_text;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
                return new FragmentGamesBinding(swipeRefreshLayout, recyclerView, materialTextView, swipeRefreshLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentGamesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentGamesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_games, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public SwipeRefreshLayout getRoot() {
        return this.rootView;
    }
}
