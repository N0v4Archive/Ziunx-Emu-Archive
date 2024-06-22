package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentHomeSettingsBinding implements ViewBinding {
    public final RecyclerView homeSettingsList;
    public final LinearLayoutCompat linearLayoutSettings;
    public final ImageView logoImage;
    private final NestedScrollView rootView;
    public final NestedScrollView scrollViewSettings;

    private FragmentHomeSettingsBinding(NestedScrollView nestedScrollView, RecyclerView recyclerView, LinearLayoutCompat linearLayoutCompat, ImageView imageView, NestedScrollView nestedScrollView2) {
        this.rootView = nestedScrollView;
        this.homeSettingsList = recyclerView;
        this.linearLayoutSettings = linearLayoutCompat;
        this.logoImage = imageView;
        this.scrollViewSettings = nestedScrollView2;
    }

    public static FragmentHomeSettingsBinding bind(View view) {
        int i = R$id.home_settings_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R$id.linear_layout_settings;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
            if (linearLayoutCompat != null) {
                i = R$id.logo_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    NestedScrollView nestedScrollView = (NestedScrollView) view;
                    return new FragmentHomeSettingsBinding(nestedScrollView, recyclerView, linearLayoutCompat, imageView, nestedScrollView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentHomeSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentHomeSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_home_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }
}
