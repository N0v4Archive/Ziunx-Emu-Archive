package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class ListItemInputProfileBinding implements ViewBinding {
    public final Button buttonDelete;
    public final LinearLayout buttonLayout;
    public final Button buttonLoad;
    public final Button buttonNew;
    public final Button buttonSave;
    private final ConstraintLayout rootView;
    public final MaterialTextView title;

    private ListItemInputProfileBinding(ConstraintLayout constraintLayout, Button button, LinearLayout linearLayout, Button button2, Button button3, Button button4, MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.buttonDelete = button;
        this.buttonLayout = linearLayout;
        this.buttonLoad = button2;
        this.buttonNew = button3;
        this.buttonSave = button4;
        this.title = materialTextView;
    }

    public static ListItemInputProfileBinding bind(View view) {
        int i = R$id.button_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.button_layout;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R$id.button_load;
                Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                if (button2 != null) {
                    i = R$id.button_new;
                    Button button3 = (Button) ViewBindings.findChildViewById(view, i);
                    if (button3 != null) {
                        i = R$id.button_save;
                        Button button4 = (Button) ViewBindings.findChildViewById(view, i);
                        if (button4 != null) {
                            i = R$id.title;
                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                            if (materialTextView != null) {
                                return new ListItemInputProfileBinding((ConstraintLayout) view, button, linearLayout, button2, button3, button4, materialTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static ListItemInputProfileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.list_item_input_profile, viewGroup, false);
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
