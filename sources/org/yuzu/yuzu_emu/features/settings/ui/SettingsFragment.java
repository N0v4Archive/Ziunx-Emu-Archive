package org.yuzu.yuzu_emu.features.settings.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.transition.MaterialSharedAxis;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$menu;
import org.yuzu.yuzu_emu.databinding.FragmentSettingsBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SettingsFragment extends Fragment {
    private FragmentSettingsBinding _binding;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SettingsFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$special$$inlined$navArgs$1
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
    private SettingsFragmentPresenter presenter;
    private SettingsAdapter settingsAdapter;
    private final Lazy settingsViewModel$delegate;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Settings.MenuTag.values().length];
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_ONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_TWO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_THREE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_FOUR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_FIVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_SIX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_SEVEN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_EIGHT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SettingsFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$special$$inlined$activityViewModels$default$3
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

    private final SettingsFragmentArgs getArgs() {
        return (SettingsFragmentArgs) this.args$delegate.getValue();
    }

    private final FragmentSettingsBinding getBinding() {
        FragmentSettingsBinding fragmentSettingsBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSettingsBinding);
        return fragmentSettingsBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getPlayerIndex() {
        switch (WhenMappings.$EnumSwitchMapping$0[getArgs().getMenuTag().ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            default:
                return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(SettingsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSettingsViewModel().setShouldNavigateBack(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$12(View view, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (menuItem.getItemId() != R$id.action_search) {
            return false;
        }
        ViewKt.findNavController(view).navigate(R$id.action_settingsFragment_to_settingsSearchFragment);
        return true;
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$13;
                insets$lambda$13 = SettingsFragment.setInsets$lambda$13(SettingsFragment.this, view, windowInsetsCompat);
                return insets$lambda$13;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$13(SettingsFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        RecyclerView listSettings = this$0.getBinding().listSettings;
        Intrinsics.checkNotNullExpressionValue(listSettings, "listSettings");
        viewUtils.updateMargins(listSettings, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listSettings2 = this$0.getBinding().listSettings;
        Intrinsics.checkNotNullExpressionValue(listSettings2, "listSettings");
        listSettings2.setPadding(listSettings2.getPaddingLeft(), listSettings2.getPaddingTop(), listSettings2.getPaddingRight(), insets.bottom);
        AppBarLayout appbarSettings = this$0.getBinding().appbarSettings;
        Intrinsics.checkNotNullExpressionValue(appbarSettings, "appbarSettings");
        viewUtils.updateMargins(appbarSettings, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
        setExitTransition(new MaterialSharedAxis(0, true));
        if (getPlayerIndex() != -1) {
            NativeInput nativeInput = NativeInput.INSTANCE;
            nativeInput.loadInputProfiles();
            nativeInput.reloadInputDevices();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle bundle) {
        Settings settings;
        int i;
        String playerString;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.settingsAdapter = new SettingsAdapter(this, requireContext);
        SettingsViewModel settingsViewModel = getSettingsViewModel();
        SettingsAdapter settingsAdapter = this.settingsAdapter;
        Intrinsics.checkNotNull(settingsAdapter);
        this.presenter = new SettingsFragmentPresenter(settingsViewModel, settingsAdapter, getArgs().getMenuTag());
        CollapsingToolbarLayout collapsingToolbarLayout = getBinding().toolbarSettingsLayout;
        Settings.MenuTag menuTag = getArgs().getMenuTag();
        Settings.MenuTag menuTag2 = Settings.MenuTag.SECTION_ROOT;
        if (menuTag != menuTag2 || getArgs().getGame() == null) {
            switch (WhenMappings.$EnumSwitchMapping$0[getArgs().getMenuTag().ordinal()]) {
                case 1:
                    settings = Settings.INSTANCE;
                    i = 1;
                    playerString = settings.getPlayerString(i);
                    break;
                case 2:
                    settings = Settings.INSTANCE;
                    i = 2;
                    playerString = settings.getPlayerString(i);
                    break;
                case 3:
                    settings = Settings.INSTANCE;
                    i = 3;
                    playerString = settings.getPlayerString(i);
                    break;
                case 4:
                    settings = Settings.INSTANCE;
                    i = 4;
                    playerString = settings.getPlayerString(i);
                    break;
                case 5:
                    settings = Settings.INSTANCE;
                    i = 5;
                    playerString = settings.getPlayerString(i);
                    break;
                case 6:
                    settings = Settings.INSTANCE;
                    i = 6;
                    playerString = settings.getPlayerString(i);
                    break;
                case 7:
                    settings = Settings.INSTANCE;
                    i = 7;
                    playerString = settings.getPlayerString(i);
                    break;
                case 8:
                    settings = Settings.INSTANCE;
                    i = 8;
                    playerString = settings.getPlayerString(i);
                    break;
                default:
                    playerString = getString(getArgs().getMenuTag().getTitleId());
                    Intrinsics.checkNotNullExpressionValue(playerString, "getString(...)");
                    break;
            }
        } else {
            Game game = getArgs().getGame();
            Intrinsics.checkNotNull(game);
            playerString = game.getTitle();
        }
        collapsingToolbarLayout.setTitle(playerString);
        RecyclerView recyclerView = getBinding().listSettings;
        recyclerView.setAdapter(this.settingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        getBinding().toolbarSettings.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsFragment.onViewCreated$lambda$1(SettingsFragment.this, view2);
            }
        });
        StateFlow shouldReloadSettingsList = getSettingsViewModel().getShouldReloadSettingsList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new SettingsFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, shouldReloadSettingsList, null, this, this), 3, null);
        StateFlow adapterItemChanged = getSettingsViewModel().getAdapterItemChanged();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new SettingsFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, adapterItemChanged, null, this, this), 3, null);
        StateFlow datasetChanged = getSettingsViewModel().getDatasetChanged();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new SettingsFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, datasetChanged, null, this, this), 3, null);
        StateFlow reloadListAndNotifyDataset = getSettingsViewModel().getReloadListAndNotifyDataset();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner4), null, null, new SettingsFragment$onViewCreated$$inlined$collect$default$4(viewLifecycleOwner4, state, reloadListAndNotifyDataset, null, this, this), 3, null);
        StateFlow shouldShowResetInputDialog = getSettingsViewModel().getShouldShowResetInputDialog();
        LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner5, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner5), null, null, new SettingsFragment$onViewCreated$$inlined$collect$default$5(viewLifecycleOwner5, state, shouldShowResetInputDialog, null, this, this), 3, null);
        if (getArgs().getMenuTag() == menuTag2) {
            getBinding().toolbarSettings.inflateMenu(R$menu.menu_settings);
            getBinding().toolbarSettings.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragment$$ExternalSyntheticLambda1
                @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean onViewCreated$lambda$12;
                    onViewCreated$lambda$12 = SettingsFragment.onViewCreated$lambda$12(view, menuItem);
                    return onViewCreated$lambda$12;
                }
            });
        }
        SettingsFragmentPresenter settingsFragmentPresenter = this.presenter;
        if (settingsFragmentPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
            settingsFragmentPresenter = null;
        }
        settingsFragmentPresenter.onViewCreated();
        setInsets();
    }
}
