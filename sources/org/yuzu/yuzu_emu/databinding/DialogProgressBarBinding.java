package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogProgressBarBinding implements ViewBinding {
    public final MaterialTextView message;
    public final LinearProgressIndicator progressBar;
    private final LinearLayout rootView;

    private DialogProgressBarBinding(LinearLayout linearLayout, MaterialTextView materialTextView, LinearProgressIndicator linearProgressIndicator) {
        this.rootView = linearLayout;
        this.message = materialTextView;
        this.progressBar = linearProgressIndicator;
    }

    public static DialogProgressBarBinding bind(View view) {
        int i = R$id.message;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
        if (materialTextView != null) {
            i = R$id.progress_bar;
            LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
            if (linearProgressIndicator != null) {
                return new DialogProgressBarBinding((LinearLayout) view, materialTextView, linearProgressIndicator);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogProgressBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogProgressBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_progress_bar, viewGroup, false);
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
