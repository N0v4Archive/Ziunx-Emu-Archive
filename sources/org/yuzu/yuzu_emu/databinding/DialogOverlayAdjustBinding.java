package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.slider.Slider;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogOverlayAdjustBinding implements ViewBinding {
    public final TextView inputOpacityName;
    public final Slider inputOpacitySlider;
    public final TextView inputOpacityValue;
    public final TextView inputScaleName;
    public final Slider inputScaleSlider;
    public final TextView inputScaleValue;
    private final ConstraintLayout rootView;

    private DialogOverlayAdjustBinding(ConstraintLayout constraintLayout, TextView textView, Slider slider, TextView textView2, TextView textView3, Slider slider2, TextView textView4) {
        this.rootView = constraintLayout;
        this.inputOpacityName = textView;
        this.inputOpacitySlider = slider;
        this.inputOpacityValue = textView2;
        this.inputScaleName = textView3;
        this.inputScaleSlider = slider2;
        this.inputScaleValue = textView4;
    }

    public static DialogOverlayAdjustBinding bind(View view) {
        int i = R$id.input_opacity_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R$id.input_opacity_slider;
            Slider slider = (Slider) ViewBindings.findChildViewById(view, i);
            if (slider != null) {
                i = R$id.input_opacity_value;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R$id.input_scale_name;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R$id.input_scale_slider;
                        Slider slider2 = (Slider) ViewBindings.findChildViewById(view, i);
                        if (slider2 != null) {
                            i = R$id.input_scale_value;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                return new DialogOverlayAdjustBinding((ConstraintLayout) view, textView, slider, textView2, textView3, slider2, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogOverlayAdjustBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogOverlayAdjustBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_overlay_adjust, viewGroup, false);
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
