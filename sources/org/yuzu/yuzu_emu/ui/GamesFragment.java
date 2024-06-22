package org.yuzu.yuzu_emu.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.R$attr;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.textview.MaterialTextView;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.adapters.GameAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentGamesBinding;
import org.yuzu.yuzu_emu.layout.AutofitGridLayoutManager;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class GamesFragment extends Fragment {
    private FragmentGamesBinding _binding;
    private final Lazy gamesViewModel$delegate;
    private final Lazy homeViewModel$delegate;

    public GamesFragment() {
        final Function0 function0 = null;
        this.gamesViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$3
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
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$special$$inlined$activityViewModels$default$6
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
    public final FragmentGamesBinding getBinding() {
        FragmentGamesBinding fragmentGamesBinding = this._binding;
        Intrinsics.checkNotNull(fragmentGamesBinding);
        return fragmentGamesBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3$lambda$1(GamesFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GamesViewModel.reloadGames$default(this$0.getGamesViewModel(), false, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3$lambda$2(GamesFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0._binding == null) {
            return;
        }
        this$0.getBinding().swipeRefresh.setRefreshing(((Boolean) this$0.getGamesViewModel().isReloading().getValue()).booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scrollToTop() {
        if (this._binding != null) {
            getBinding().gridGames.smoothScrollToPosition(0);
        }
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$10;
                insets$lambda$10 = GamesFragment.setInsets$lambda$10(GamesFragment.this, view, windowInsetsCompat);
                return insets$lambda$10;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$10(GamesFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_large);
        int dimensionPixelSize2 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation);
        int dimensionPixelSize3 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation_rail);
        RecyclerView gridGames = this$0.getBinding().gridGames;
        Intrinsics.checkNotNullExpressionValue(gridGames, "gridGames");
        gridGames.setPadding(gridGames.getPaddingLeft(), insets.top + dimensionPixelSize, gridGames.getPaddingRight(), insets.bottom + dimensionPixelSize2 + dimensionPixelSize);
        this$0.getBinding().swipeRefresh.setProgressViewEndTarget(false, insets.top + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_refresh_end));
        int i = insets.left + insets2.left;
        int i2 = insets.right + insets2.right;
        if (ViewCompat.getLayoutDirection(view) == 0) {
            i += dimensionPixelSize3;
        } else {
            i2 += dimensionPixelSize3;
        }
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        SwipeRefreshLayout swipeRefresh = this$0.getBinding().swipeRefresh;
        Intrinsics.checkNotNullExpressionValue(swipeRefresh, "swipeRefresh");
        viewUtils.updateMargins(swipeRefresh, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        MaterialTextView noticeText = this$0.getBinding().noticeText;
        Intrinsics.checkNotNullExpressionValue(noticeText, "noticeText");
        noticeText.setPadding(noticeText.getPaddingLeft(), noticeText.getPaddingTop(), noticeText.getPaddingRight(), dimensionPixelSize2);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentGamesBinding.inflate(inflater);
        SwipeRefreshLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(true, true);
        getHomeViewModel().setStatusBarShadeVisibility(true);
        RecyclerView recyclerView = getBinding().gridGames;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        recyclerView.setLayoutManager(new AutofitGridLayoutManager(requireContext, requireContext().getResources().getDimensionPixelSize(R$dimen.card_width)));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        recyclerView.setAdapter(new GameAdapter((AppCompatActivity) requireActivity));
        SwipeRefreshLayout swipeRefreshLayout = getBinding().swipeRefresh;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                GamesFragment.onViewCreated$lambda$3$lambda$1(GamesFragment.this);
            }
        });
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(MaterialColors.getColor(getBinding().swipeRefresh, R$attr.colorPrimary));
        swipeRefreshLayout.setColorSchemeColors(MaterialColors.getColor(getBinding().swipeRefresh, R$attr.colorOnPrimary));
        swipeRefreshLayout.post(new Runnable() { // from class: org.yuzu.yuzu_emu.ui.GamesFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GamesFragment.onViewCreated$lambda$3$lambda$2(GamesFragment.this);
            }
        });
        StateFlow isReloading = getGamesViewModel().isReloading();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new GamesFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, isReloading, null, this), 3, null);
        StateFlow games = getGamesViewModel().getGames();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new GamesFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, games, null, this), 3, null);
        StateFlow shouldSwapData = getGamesViewModel().getShouldSwapData();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new GamesFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, shouldSwapData, null, this, this), 3, null);
        StateFlow shouldScrollToTop = getGamesViewModel().getShouldScrollToTop();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner4), null, null, new GamesFragment$onViewCreated$$inlined$collect$default$4(viewLifecycleOwner4, state, shouldScrollToTop, null, this, this), 3, null);
        setInsets();
    }
}
