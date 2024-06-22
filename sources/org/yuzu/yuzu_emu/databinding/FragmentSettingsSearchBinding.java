package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentSettingsSearchBinding implements ViewBinding {
    public final Button backButton;
    public final Button clearButton;
    public final MaterialDivider divider;
    public final FrameLayout frameSearch;
    public final ImageView iconNoResults;
    public final LinearLayout noResultsView;
    public final MaterialTextView noticeText;
    public final RelativeLayout relativeLayout;
    private final ConstraintLayout rootView;
    public final MaterialCardView searchBackground;
    public final LinearLayout searchContainer;
    public final EditText searchText;
    public final RecyclerView settingsList;

    private FragmentSettingsSearchBinding(ConstraintLayout constraintLayout, Button button, Button button2, MaterialDivider materialDivider, FrameLayout frameLayout, ImageView imageView, LinearLayout linearLayout, MaterialTextView materialTextView, RelativeLayout relativeLayout, MaterialCardView materialCardView, LinearLayout linearLayout2, EditText editText, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.backButton = button;
        this.clearButton = button2;
        this.divider = materialDivider;
        this.frameSearch = frameLayout;
        this.iconNoResults = imageView;
        this.noResultsView = linearLayout;
        this.noticeText = materialTextView;
        this.relativeLayout = relativeLayout;
        this.searchBackground = materialCardView;
        this.searchContainer = linearLayout2;
        this.searchText = editText;
        this.settingsList = recyclerView;
    }

    public static FragmentSettingsSearchBinding bind(View view) {
        int i = R$id.back_button;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R$id.clear_button;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R$id.divider;
                MaterialDivider materialDivider = (MaterialDivider) ViewBindings.findChildViewById(view, i);
                if (materialDivider != null) {
                    i = R$id.frame_search;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout != null) {
                        i = R$id.icon_no_results;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R$id.no_results_view;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout != null) {
                                i = R$id.notice_text;
                                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                if (materialTextView != null) {
                                    i = R$id.relativeLayout;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                    if (relativeLayout != null) {
                                        i = R$id.search_background;
                                        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
                                        if (materialCardView != null) {
                                            i = R$id.search_container;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout2 != null) {
                                                i = R$id.search_text;
                                                EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                                                if (editText != null) {
                                                    i = R$id.settings_list;
                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                    if (recyclerView != null) {
                                                        return new FragmentSettingsSearchBinding((ConstraintLayout) view, button, button2, materialDivider, frameLayout, imageView, linearLayout, materialTextView, relativeLayout, materialCardView, linearLayout2, editText, recyclerView);
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

    public static FragmentSettingsSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSettingsSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_settings_search, viewGroup, false);
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
