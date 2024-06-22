package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentGameInfoBinding implements ViewBinding {
    public final AppBarLayout appbarInfo;
    public final MaterialButton buttonCopy;
    public final MaterialButton buttonVerifyIntegrity;
    public final LinearLayout contentInfo;
    public final CoordinatorLayout coordinatorAbout;
    public final TextInputLayout developer;
    public final TextInputEditText developerField;
    public final TextInputLayout path;
    public final TextInputEditText pathField;
    public final TextInputLayout programId;
    public final TextInputEditText programIdField;
    private final CoordinatorLayout rootView;
    public final NestedScrollView scrollInfo;
    public final MaterialToolbar toolbarInfo;
    public final TextInputLayout version;
    public final TextInputEditText versionField;

    private FragmentGameInfoBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MaterialButton materialButton, MaterialButton materialButton2, LinearLayout linearLayout, CoordinatorLayout coordinatorLayout2, TextInputLayout textInputLayout, TextInputEditText textInputEditText, TextInputLayout textInputLayout2, TextInputEditText textInputEditText2, TextInputLayout textInputLayout3, TextInputEditText textInputEditText3, NestedScrollView nestedScrollView, MaterialToolbar materialToolbar, TextInputLayout textInputLayout4, TextInputEditText textInputEditText4) {
        this.rootView = coordinatorLayout;
        this.appbarInfo = appBarLayout;
        this.buttonCopy = materialButton;
        this.buttonVerifyIntegrity = materialButton2;
        this.contentInfo = linearLayout;
        this.coordinatorAbout = coordinatorLayout2;
        this.developer = textInputLayout;
        this.developerField = textInputEditText;
        this.path = textInputLayout2;
        this.pathField = textInputEditText2;
        this.programId = textInputLayout3;
        this.programIdField = textInputEditText3;
        this.scrollInfo = nestedScrollView;
        this.toolbarInfo = materialToolbar;
        this.version = textInputLayout4;
        this.versionField = textInputEditText4;
    }

    public static FragmentGameInfoBinding bind(View view) {
        int i = R$id.appbar_info;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            i = R$id.button_copy;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                i = R$id.button_verify_integrity;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R$id.content_info;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                        i = R$id.developer;
                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, i);
                        if (textInputLayout != null) {
                            i = R$id.developer_field;
                            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, i);
                            if (textInputEditText != null) {
                                i = R$id.path;
                                TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, i);
                                if (textInputLayout2 != null) {
                                    i = R$id.path_field;
                                    TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, i);
                                    if (textInputEditText2 != null) {
                                        i = R$id.program_id;
                                        TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, i);
                                        if (textInputLayout3 != null) {
                                            i = R$id.program_id_field;
                                            TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, i);
                                            if (textInputEditText3 != null) {
                                                i = R$id.scroll_info;
                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                                                if (nestedScrollView != null) {
                                                    i = R$id.toolbar_info;
                                                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                                                    if (materialToolbar != null) {
                                                        i = R$id.version;
                                                        TextInputLayout textInputLayout4 = (TextInputLayout) ViewBindings.findChildViewById(view, i);
                                                        if (textInputLayout4 != null) {
                                                            i = R$id.version_field;
                                                            TextInputEditText textInputEditText4 = (TextInputEditText) ViewBindings.findChildViewById(view, i);
                                                            if (textInputEditText4 != null) {
                                                                return new FragmentGameInfoBinding(coordinatorLayout, appBarLayout, materialButton, materialButton2, linearLayout, coordinatorLayout, textInputLayout, textInputEditText, textInputLayout2, textInputEditText2, textInputLayout3, textInputEditText3, nestedScrollView, materialToolbar, textInputLayout4, textInputEditText4);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentGameInfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentGameInfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_game_info, viewGroup, false);
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
