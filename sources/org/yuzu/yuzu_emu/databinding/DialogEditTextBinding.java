package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class DialogEditTextBinding implements ViewBinding {
    public final TextInputEditText editText;
    public final TextInputLayout editTextLayout;
    private final ConstraintLayout rootView;

    private DialogEditTextBinding(ConstraintLayout constraintLayout, TextInputEditText textInputEditText, TextInputLayout textInputLayout) {
        this.rootView = constraintLayout;
        this.editText = textInputEditText;
        this.editTextLayout = textInputLayout;
    }

    public static DialogEditTextBinding bind(View view) {
        int i = R$id.edit_text;
        TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, i);
        if (textInputEditText != null) {
            i = R$id.edit_text_layout;
            TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, i);
            if (textInputLayout != null) {
                return new DialogEditTextBinding((ConstraintLayout) view, textInputEditText, textInputLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static DialogEditTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogEditTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.dialog_edit_text, viewGroup, false);
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
