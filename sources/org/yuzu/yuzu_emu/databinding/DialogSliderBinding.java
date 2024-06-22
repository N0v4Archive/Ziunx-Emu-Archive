package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.slider.Slider;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogSliderBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final Slider slider;
    public final MaterialTextView textValue;

    private DialogSliderBinding(RelativeLayout relativeLayout, Slider slider, MaterialTextView materialTextView) {
        this.rootView = relativeLayout;
        this.slider = slider;
        this.textValue = materialTextView;
    }

    public static DialogSliderBinding bind(View view) {
        int i = R$id.slider;
        Slider slider = (Slider) ViewBindings.findChildViewById(view, i);
        if (slider != null) {
            i = R$id.text_value;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                return new DialogSliderBinding((RelativeLayout) view, slider, materialTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogSliderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSliderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_slider, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
