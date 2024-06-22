package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemSettingsHeaderBinding implements ViewBinding {
    private final MaterialTextView rootView;
    public final MaterialTextView textHeaderName;

    private ListItemSettingsHeaderBinding(MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = materialTextView;
        this.textHeaderName = materialTextView2;
    }

    public static ListItemSettingsHeaderBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        MaterialTextView materialTextView = (MaterialTextView) view;
        return new ListItemSettingsHeaderBinding(materialTextView, materialTextView);
    }

    public static ListItemSettingsHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListItemSettingsHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_settings_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialTextView getRoot() {
        return this.rootView;
    }
}
