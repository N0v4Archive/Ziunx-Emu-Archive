package org.yuzu.yuzu_emu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialSharedAxis;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.adapters.FolderAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentFoldersBinding;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.ui.main.MainActivity;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class GameFoldersFragment extends Fragment {
    private FragmentFoldersBinding _binding;
    private final Lazy gamesViewModel$delegate;
    private final Lazy homeViewModel$delegate;

    public GameFoldersFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$3
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
        this.gamesViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$special$$inlined$activityViewModels$default$6
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
    public final FragmentFoldersBinding getBinding() {
        FragmentFoldersBinding fragmentFoldersBinding = this._binding;
        Intrinsics.checkNotNull(fragmentFoldersBinding);
        return fragmentFoldersBinding;
    }

    private final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(GameFoldersFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "$mainActivity");
        mainActivity.getGetGamesDirectory().launch(new Intent("android.intent.action.OPEN_DOCUMENT_TREE").getData());
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$4;
                insets$lambda$4 = GameFoldersFragment.setInsets$lambda$4(GameFoldersFragment.this, view, windowInsetsCompat);
                return insets$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$4(GameFoldersFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i = insets.left + insets2.left;
        int i2 = insets2.right + insets.right;
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialToolbar toolbarFolders = this$0.getBinding().toolbarFolders;
        Intrinsics.checkNotNullExpressionValue(toolbarFolders, "toolbarFolders");
        viewUtils.updateMargins(toolbarFolders, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_fab);
        FloatingActionButton buttonAdd = this$0.getBinding().buttonAdd;
        Intrinsics.checkNotNullExpressionValue(buttonAdd, "buttonAdd");
        viewUtils.updateMargins(buttonAdd, (r13 & 1) != 0 ? -1 : i + dimensionPixelSize, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2 + dimensionPixelSize, (r13 & 8) != 0 ? -1 : insets.bottom + dimensionPixelSize);
        RecyclerView listFolders = this$0.getBinding().listFolders;
        Intrinsics.checkNotNullExpressionValue(listFolders, "listFolders");
        viewUtils.updateMargins(listFolders, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listFolders2 = this$0.getBinding().listFolders;
        Intrinsics.checkNotNullExpressionValue(listFolders2, "listFolders");
        listFolders2.setPadding(listFolders2.getPaddingLeft(), listFolders2.getPaddingTop(), listFolders2.getPaddingRight(), insets.bottom + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_bottom_list_fab));
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
        getGamesViewModel().onOpenGameFoldersFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentFoldersBinding.inflate(inflater);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getGamesViewModel().onCloseGameFoldersFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarFolders.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameFoldersFragment.onViewCreated$lambda$0(GameFoldersFragment.this, view2);
            }
        });
        RecyclerView recyclerView = getBinding().listFolders;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        recyclerView.setAdapter(new FolderAdapter(requireActivity, getGamesViewModel()));
        StateFlow folders = getGamesViewModel().getFolders();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new GameFoldersFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, folders, null, this), 3, null);
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type org.yuzu.yuzu_emu.ui.main.MainActivity");
        final MainActivity mainActivity = (MainActivity) requireActivity2;
        getBinding().buttonAdd.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameFoldersFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameFoldersFragment.onViewCreated$lambda$3(MainActivity.this, view2);
            }
        });
        setInsets();
    }
}
