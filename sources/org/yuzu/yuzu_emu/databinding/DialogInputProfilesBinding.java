package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogInputProfilesBinding implements ViewBinding {
    public final RecyclerView listProfiles;
    private final RecyclerView rootView;

    private DialogInputProfilesBinding(RecyclerView recyclerView, RecyclerView recyclerView2) {
        this.rootView = recyclerView;
        this.listProfiles = recyclerView2;
    }

    public static DialogInputProfilesBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        RecyclerView recyclerView = (RecyclerView) view;
        return new DialogInputProfilesBinding(recyclerView, recyclerView);
    }

    public static DialogInputProfilesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogInputProfilesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_input_profiles, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RecyclerView getRoot() {
        return this.rootView;
    }
}
