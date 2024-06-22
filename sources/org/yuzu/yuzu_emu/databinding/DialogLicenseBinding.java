package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogLicenseBinding implements ViewBinding {
    private final CoordinatorLayout rootView;
    public final MaterialTextView textCopyright;
    public final MaterialTextView textLicense;
    public final MaterialTextView textLink;
    public final MaterialTextView textTitle;

    private DialogLicenseBinding(CoordinatorLayout coordinatorLayout, MaterialTextView materialTextView, MaterialTextView materialTextView2, MaterialTextView materialTextView3, MaterialTextView materialTextView4) {
        this.rootView = coordinatorLayout;
        this.textCopyright = materialTextView;
        this.textLicense = materialTextView2;
        this.textLink = materialTextView3;
        this.textTitle = materialTextView4;
    }

    public static DialogLicenseBinding bind(View view) {
        int i = R$id.text_copyright;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
        if (materialTextView != null) {
            i = R$id.text_license;
            MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView2 != null) {
                i = R$id.text_link;
                MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView3 != null) {
                    i = R$id.text_title;
                    MaterialTextView materialTextView4 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView4 != null) {
                        return new DialogLicenseBinding((CoordinatorLayout) view, materialTextView, materialTextView2, materialTextView3, materialTextView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_license, viewGroup, false);
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
