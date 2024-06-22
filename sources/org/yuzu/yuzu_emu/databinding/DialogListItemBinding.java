package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogListItemBinding implements ViewBinding {
    public final ImageView icon;
    private final LinearLayout rootView;
    public final MaterialTextView title;

    private DialogListItemBinding(LinearLayout linearLayout, ImageView imageView, MaterialTextView materialTextView) {
        this.rootView = linearLayout;
        this.icon = imageView;
        this.title = materialTextView;
    }

    public static DialogListItemBinding bind(View view) {
        int i = R$id.icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R$id.title;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                return new DialogListItemBinding((LinearLayout) view, imageView, materialTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
