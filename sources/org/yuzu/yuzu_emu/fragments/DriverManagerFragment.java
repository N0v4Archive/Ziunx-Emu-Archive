package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocument;
import androidx.appcompat.widget.Toolbar;
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
import androidx.navigation.NavArgsLazy;
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.transition.MaterialSharedAxis;
import java.util.List;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.R$menu;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.DriverAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentDriverManagerBinding;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class DriverManagerFragment extends Fragment {
    private FragmentDriverManagerBinding _binding;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(DriverManagerFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$navArgs$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Bundle invoke() {
            Bundle arguments = Fragment.this.getArguments();
            if (arguments != null) {
                return arguments;
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " has null arguments");
        }
    });
    private final Lazy driverViewModel$delegate;
    private final ActivityResultLauncher getDriver;
    private final Lazy homeViewModel$delegate;

    public DriverManagerFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$3
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
        this.driverViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$special$$inlined$activityViewModels$default$6
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
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DriverManagerFragment.getDriver$lambda$6(DriverManagerFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.getDriver = registerForActivityResult;
    }

    private final DriverManagerFragmentArgs getArgs() {
        return (DriverManagerFragmentArgs) this.args$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentDriverManagerBinding getBinding() {
        FragmentDriverManagerBinding fragmentDriverManagerBinding = this._binding;
        Intrinsics.checkNotNull(fragmentDriverManagerBinding);
        return fragmentDriverManagerBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getDriver$lambda$6(DriverManagerFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.installing_driver, false, new DriverManagerFragment$getDriver$1$1(uri, this$0, null)).show(this$0.getChildFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$0(DriverManagerFragment this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (menuItem.getItemId() != R$id.menu_driver_use_global) {
            return false;
        }
        StringSetting.DRIVER_PATH.setGlobal(true);
        this$0.getDriverViewModel().updateDriverList();
        RecyclerView.Adapter adapter = this$0.getBinding().listDrivers.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.yuzu.yuzu_emu.adapters.DriverAdapter");
        ((DriverAdapter) adapter).replaceList((List) this$0.getDriverViewModel().getDriverList().getValue());
        this$0.getDriverViewModel().showClearButton(false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(DriverManagerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(DriverManagerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDriver.launch(new String[]{"application/zip"});
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$$ExternalSyntheticLambda4
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$5;
                insets$lambda$5 = DriverManagerFragment.setInsets$lambda$5(DriverManagerFragment.this, view, windowInsetsCompat);
                return insets$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$5(DriverManagerFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i = insets.left + insets2.left;
        int i2 = insets.right + insets2.right;
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialToolbar toolbarDrivers = this$0.getBinding().toolbarDrivers;
        Intrinsics.checkNotNullExpressionValue(toolbarDrivers, "toolbarDrivers");
        viewUtils.updateMargins(toolbarDrivers, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listDrivers = this$0.getBinding().listDrivers;
        Intrinsics.checkNotNullExpressionValue(listDrivers, "listDrivers");
        viewUtils.updateMargins(listDrivers, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_fab);
        ExtendedFloatingActionButton buttonInstall = this$0.getBinding().buttonInstall;
        Intrinsics.checkNotNullExpressionValue(buttonInstall, "buttonInstall");
        viewUtils.updateMargins(buttonInstall, (r13 & 1) != 0 ? -1 : i + dimensionPixelSize, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2 + dimensionPixelSize, (r13 & 8) != 0 ? -1 : insets.bottom + dimensionPixelSize);
        RecyclerView listDrivers2 = this$0.getBinding().listDrivers;
        Intrinsics.checkNotNullExpressionValue(listDrivers2, "listDrivers");
        listDrivers2.setPadding(listDrivers2.getPaddingLeft(), listDrivers2.getPaddingTop(), listDrivers2.getPaddingRight(), insets.bottom + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_bottom_list_fab));
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentDriverManagerBinding.inflate(inflater);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getDriverViewModel().onCloseDriverManager(getArgs().getGame());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getDriverViewModel().onOpenDriverManager(getArgs().getGame());
        if (NativeConfig.INSTANCE.isPerGameConfigLoaded()) {
            getBinding().toolbarDrivers.inflateMenu(R$menu.menu_driver_manager);
            getDriverViewModel().showClearButton(!StringSetting.DRIVER_PATH.getGlobal());
            getBinding().toolbarDrivers.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$$ExternalSyntheticLambda0
                @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean onViewCreated$lambda$0;
                    onViewCreated$lambda$0 = DriverManagerFragment.onViewCreated$lambda$0(DriverManagerFragment.this, menuItem);
                    return onViewCreated$lambda$0;
                }
            });
            StateFlow showClearButton = getDriverViewModel().getShowClearButton();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new DriverManagerFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, showClearButton, null, this), 3, null);
        }
        if (!((Boolean) getDriverViewModel().isInteractionAllowed().getValue()).booleanValue()) {
            new DriversLoadingDialogFragment().show(getChildFragmentManager(), "DriversLoadingDialogFragment");
        }
        getBinding().toolbarDrivers.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DriverManagerFragment.onViewCreated$lambda$2(DriverManagerFragment.this, view2);
            }
        });
        getBinding().buttonInstall.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DriverManagerFragment.onViewCreated$lambda$3(DriverManagerFragment.this, view2);
            }
        });
        RecyclerView recyclerView = getBinding().listDrivers;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        recyclerView.setAdapter(new DriverAdapter(getDriverViewModel()));
        setInsets();
    }
}
