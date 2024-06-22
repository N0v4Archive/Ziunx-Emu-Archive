package org.yuzu.yuzu_emu.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import java.util.List;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.GameAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentSearchBinding;
import org.yuzu.yuzu_emu.layout.AutofitGridLayoutManager;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SearchFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private FragmentSearchBinding _binding;
    private final Lazy gamesViewModel$delegate;
    private final Lazy homeViewModel$delegate;
    private SharedPreferences preferences;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ScoredGame {
        private final Game item;
        private final double score;
        final /* synthetic */ SearchFragment this$0;

        public ScoredGame(SearchFragment searchFragment, double d, Game item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.this$0 = searchFragment;
            this.score = d;
            this.item = item;
        }

        public final Game getItem() {
            return this.item;
        }

        public final double getScore() {
            return this.score;
        }
    }

    public SearchFragment() {
        final Function0 function0 = null;
        this.gamesViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$special$$inlined$activityViewModels$default$6
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b6 A[LOOP:2: B:49:0x01b0->B:51:0x01b6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void filterAndSearch() {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.fragments.SearchFragment.filterAndSearch():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusSearch() {
        if (this._binding != null) {
            getBinding().searchText.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(getBinding().searchText, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentSearchBinding getBinding() {
        FragmentSearchBinding fragmentSearchBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSearchBinding);
        return fragmentSearchBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(SearchFragment this$0, ChipGroup chipGroup, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(chipGroup, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(list, "<anonymous parameter 1>");
        this$0.filterAndSearch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(SearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().searchText.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(SearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.focusSearch();
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$$ExternalSyntheticLambda3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$18;
                insets$lambda$18 = SearchFragment.setInsets$lambda$18(SearchFragment.this, view, windowInsetsCompat);
                return insets$lambda$18;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$18(SearchFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_med);
        int dimensionPixelSize2 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation);
        int dimensionPixelSize3 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation_rail);
        int dimensionPixelSize4 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_chip);
        ConstraintLayout constraintSearch = this$0.getBinding().constraintSearch;
        Intrinsics.checkNotNullExpressionValue(constraintSearch, "constraintSearch");
        constraintSearch.setPadding(insets.left + insets2.left, insets.top, insets.right + insets2.right, constraintSearch.getPaddingBottom());
        RecyclerView gridGamesSearch = this$0.getBinding().gridGamesSearch;
        Intrinsics.checkNotNullExpressionValue(gridGamesSearch, "gridGamesSearch");
        gridGamesSearch.setPadding(gridGamesSearch.getPaddingLeft(), dimensionPixelSize, gridGamesSearch.getPaddingRight(), insets.bottom + dimensionPixelSize2 + dimensionPixelSize);
        LinearLayout noResultsView = this$0.getBinding().noResultsView;
        Intrinsics.checkNotNullExpressionValue(noResultsView, "noResultsView");
        noResultsView.setPadding(noResultsView.getPaddingLeft(), noResultsView.getPaddingTop(), noResultsView.getPaddingRight(), dimensionPixelSize2 + insets.bottom);
        ViewGroup.LayoutParams layoutParams = this$0.getBinding().divider.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (ViewCompat.getLayoutDirection(view) == 0) {
            FrameLayout frameSearch = this$0.getBinding().frameSearch;
            Intrinsics.checkNotNullExpressionValue(frameSearch, "frameSearch");
            frameSearch.setPadding(dimensionPixelSize3, frameSearch.getPaddingTop(), frameSearch.getPaddingRight(), frameSearch.getPaddingBottom());
            RecyclerView gridGamesSearch2 = this$0.getBinding().gridGamesSearch;
            Intrinsics.checkNotNullExpressionValue(gridGamesSearch2, "gridGamesSearch");
            gridGamesSearch2.setPadding(dimensionPixelSize3, gridGamesSearch2.getPaddingTop(), gridGamesSearch2.getPaddingRight(), gridGamesSearch2.getPaddingBottom());
            LinearLayout noResultsView2 = this$0.getBinding().noResultsView;
            Intrinsics.checkNotNullExpressionValue(noResultsView2, "noResultsView");
            noResultsView2.setPadding(dimensionPixelSize3, noResultsView2.getPaddingTop(), noResultsView2.getPaddingRight(), noResultsView2.getPaddingBottom());
            ChipGroup chipGroup = this$0.getBinding().chipGroup;
            Intrinsics.checkNotNullExpressionValue(chipGroup, "chipGroup");
            int i = dimensionPixelSize3 + dimensionPixelSize4;
            chipGroup.setPadding(i, chipGroup.getPaddingTop(), dimensionPixelSize4, chipGroup.getPaddingBottom());
            marginLayoutParams.leftMargin = i;
            marginLayoutParams.rightMargin = dimensionPixelSize4;
        } else {
            FrameLayout frameSearch2 = this$0.getBinding().frameSearch;
            Intrinsics.checkNotNullExpressionValue(frameSearch2, "frameSearch");
            frameSearch2.setPadding(frameSearch2.getPaddingLeft(), frameSearch2.getPaddingTop(), dimensionPixelSize3, frameSearch2.getPaddingBottom());
            RecyclerView gridGamesSearch3 = this$0.getBinding().gridGamesSearch;
            Intrinsics.checkNotNullExpressionValue(gridGamesSearch3, "gridGamesSearch");
            gridGamesSearch3.setPadding(gridGamesSearch3.getPaddingLeft(), gridGamesSearch3.getPaddingTop(), dimensionPixelSize3, gridGamesSearch3.getPaddingBottom());
            LinearLayout noResultsView3 = this$0.getBinding().noResultsView;
            Intrinsics.checkNotNullExpressionValue(noResultsView3, "noResultsView");
            noResultsView3.setPadding(noResultsView3.getPaddingLeft(), noResultsView3.getPaddingTop(), dimensionPixelSize3, noResultsView3.getPaddingBottom());
            ChipGroup chipGroup2 = this$0.getBinding().chipGroup;
            Intrinsics.checkNotNullExpressionValue(chipGroup2, "chipGroup");
            int i2 = dimensionPixelSize3 + dimensionPixelSize4;
            chipGroup2.setPadding(dimensionPixelSize4, chipGroup2.getPaddingTop(), i2, chipGroup2.getPaddingBottom());
            marginLayoutParams.leftMargin = dimensionPixelSize4;
            marginLayoutParams.rightMargin = i2;
        }
        this$0.getBinding().divider.setLayoutParams(marginLayoutParams);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSearchBinding.inflate(getLayoutInflater());
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        if (this._binding != null) {
            outState.putString("SearchText", getBinding().searchText.getText().toString());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(true, true);
        getHomeViewModel().setStatusBarShadeVisibility(true);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext());
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        this.preferences = defaultSharedPreferences;
        if (bundle != null) {
            getBinding().searchText.setText(bundle.getString("SearchText"));
        }
        RecyclerView recyclerView = getBinding().gridGamesSearch;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        recyclerView.setLayoutManager(new AutofitGridLayoutManager(requireContext, requireContext().getResources().getDimensionPixelSize(R$dimen.card_width)));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        recyclerView.setAdapter(new GameAdapter((AppCompatActivity) requireActivity));
        getBinding().chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.material.chip.ChipGroup.OnCheckedStateChangeListener
            public final void onCheckedChanged(ChipGroup chipGroup, List list) {
                SearchFragment.onViewCreated$lambda$1(SearchFragment.this, chipGroup, list);
            }
        });
        EditText searchText = getBinding().searchText;
        Intrinsics.checkNotNullExpressionValue(searchText, "searchText");
        searchText.addTextChangedListener(new TextWatcher() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$onViewCreated$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                FragmentSearchBinding binding;
                ViewUtils viewUtils = ViewUtils.INSTANCE;
                binding = SearchFragment.this.getBinding();
                ImageView clearButton = binding.clearButton;
                Intrinsics.checkNotNullExpressionValue(clearButton, "clearButton");
                ViewUtils.setVisible$default(viewUtils, clearButton, String.valueOf(charSequence).length() > 0, false, 2, null);
                SearchFragment.this.filterAndSearch();
            }
        });
        StateFlow searchFocused = getGamesViewModel().getSearchFocused();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new SearchFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, searchFocused, null, this, this), 3, null);
        StateFlow games = getGamesViewModel().getGames();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new SearchFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, games, null, this), 3, null);
        StateFlow searchedGames = getGamesViewModel().getSearchedGames();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new SearchFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, searchedGames, null, this), 3, null);
        getBinding().clearButton.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchFragment.onViewCreated$lambda$7(SearchFragment.this, view2);
            }
        });
        getBinding().searchBackground.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SearchFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchFragment.onViewCreated$lambda$8(SearchFragment.this, view2);
            }
        });
        setInsets();
        filterAndSearch();
    }
}
