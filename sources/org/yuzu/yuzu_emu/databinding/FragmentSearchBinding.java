package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentSearchBinding implements ViewBinding {
    public final ChipGroup chipGroup;
    public final Chip chipHomebrew;
    public final Chip chipRecentlyAdded;
    public final Chip chipRecentlyPlayed;
    public final Chip chipRetail;
    public final ImageView clearButton;
    public final ConstraintLayout constraintSearch;
    public final MaterialDivider divider;
    public final FrameLayout frameSearch;
    public final RecyclerView gridGamesSearch;
    public final HorizontalScrollView horizontalScrollView;
    public final ImageView iconNoResults;
    public final LinearLayout noResultsView;
    public final MaterialTextView noticeText;
    private final ConstraintLayout rootView;
    public final MaterialCardView searchBackground;
    public final LinearLayout searchContainer;
    public final EditText searchText;

    private FragmentSearchBinding(ConstraintLayout constraintLayout, ChipGroup chipGroup, Chip chip, Chip chip2, Chip chip3, Chip chip4, ImageView imageView, ConstraintLayout constraintLayout2, MaterialDivider materialDivider, FrameLayout frameLayout, RecyclerView recyclerView, HorizontalScrollView horizontalScrollView, ImageView imageView2, LinearLayout linearLayout, MaterialTextView materialTextView, MaterialCardView materialCardView, LinearLayout linearLayout2, EditText editText) {
        this.rootView = constraintLayout;
        this.chipGroup = chipGroup;
        this.chipHomebrew = chip;
        this.chipRecentlyAdded = chip2;
        this.chipRecentlyPlayed = chip3;
        this.chipRetail = chip4;
        this.clearButton = imageView;
        this.constraintSearch = constraintLayout2;
        this.divider = materialDivider;
        this.frameSearch = frameLayout;
        this.gridGamesSearch = recyclerView;
        this.horizontalScrollView = horizontalScrollView;
        this.iconNoResults = imageView2;
        this.noResultsView = linearLayout;
        this.noticeText = materialTextView;
        this.searchBackground = materialCardView;
        this.searchContainer = linearLayout2;
        this.searchText = editText;
    }

    public static FragmentSearchBinding bind(View view) {
        int i = R$id.chip_group;
        ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(view, i);
        if (chipGroup != null) {
            i = R$id.chip_homebrew;
            Chip chip = (Chip) ViewBindings.findChildViewById(view, i);
            if (chip != null) {
                i = R$id.chip_recently_added;
                Chip chip2 = (Chip) ViewBindings.findChildViewById(view, i);
                if (chip2 != null) {
                    i = R$id.chip_recently_played;
                    Chip chip3 = (Chip) ViewBindings.findChildViewById(view, i);
                    if (chip3 != null) {
                        i = R$id.chip_retail;
                        Chip chip4 = (Chip) ViewBindings.findChildViewById(view, i);
                        if (chip4 != null) {
                            i = R$id.clear_button;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                i = R$id.divider;
                                MaterialDivider materialDivider = (MaterialDivider) ViewBindings.findChildViewById(view, i);
                                if (materialDivider != null) {
                                    i = R$id.frame_search;
                                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                    if (frameLayout != null) {
                                        i = R$id.grid_games_search;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                        if (recyclerView != null) {
                                            i = R$id.horizontalScrollView;
                                            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(view, i);
                                            if (horizontalScrollView != null) {
                                                i = R$id.icon_no_results;
                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView2 != null) {
                                                    i = R$id.no_results_view;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                    if (linearLayout != null) {
                                                        i = R$id.notice_text;
                                                        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                                        if (materialTextView != null) {
                                                            i = R$id.search_background;
                                                            MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
                                                            if (materialCardView != null) {
                                                                i = R$id.search_container;
                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                if (linearLayout2 != null) {
                                                                    i = R$id.search_text;
                                                                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                                                                    if (editText != null) {
                                                                        return new FragmentSearchBinding(constraintLayout, chipGroup, chip, chip2, chip3, chip4, imageView, constraintLayout, materialDivider, frameLayout, recyclerView, horizontalScrollView, imageView2, linearLayout, materialTextView, materialCardView, linearLayout2, editText);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_search, viewGroup, false);
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
