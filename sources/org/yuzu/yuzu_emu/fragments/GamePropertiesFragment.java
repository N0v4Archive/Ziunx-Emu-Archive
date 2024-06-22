package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$CreateDocument;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocument;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
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
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.transition.MaterialSharedAxis;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.io.FileTreeWalk;
import kotlin.io.FilesKt__FileTreeWalkKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.R$bool;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.GamePropertiesAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentGamePropertiesBinding;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.fragments.GamePropertiesFragmentDirections;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.InstallableProperty;
import org.yuzu.yuzu_emu.model.SubmenuProperty;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.GameIconUtils;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.MemoryUtil;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class GamePropertiesFragment extends Fragment {
    private FragmentGamePropertiesBinding _binding;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(GamePropertiesFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$navArgs$1
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
    private final ActivityResultLauncher exportSaves;
    private final Lazy gamesViewModel$delegate;
    private final Lazy homeViewModel$delegate;
    private final ActivityResultLauncher importSaves;

    public GamePropertiesFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$3
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
        this.gamesViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$6
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
        this.driverViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$7
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$8
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$special$$inlined$activityViewModels$default$9
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
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                GamePropertiesFragment.importSaves$lambda$10(GamePropertiesFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.importSaves = registerForActivityResult;
        ActivityResultLauncher registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts$CreateDocument("application/zip"), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                GamePropertiesFragment.exportSaves$lambda$11(GamePropertiesFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.exportSaves = registerForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportSaves$lambda$11(GamePropertiesFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.save_files_exporting, false, new GamePropertiesFragment$exportSaves$1$1(this$0, uri, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GamePropertiesFragmentArgs getArgs() {
        return (GamePropertiesFragmentArgs) this.args$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentGamePropertiesBinding getBinding() {
        FragmentGamePropertiesBinding fragmentGamePropertiesBinding = this._binding;
        Intrinsics.checkNotNull(fragmentGamePropertiesBinding);
        return fragmentGamePropertiesBinding;
    }

    private final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    private final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void importSaves$lambda$10(GamePropertiesFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        File file = new File(this$0.getArgs().getGame().getSaveDir());
        File file2 = new File(this$0.requireContext().getCacheDir().getPath() + "/saves/");
        file2.mkdir();
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.save_files_importing, false, new GamePropertiesFragment$importSaves$1$1(uri, file2, this$0, file, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(View view, View view2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        ViewKt.findNavController(view).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(GamePropertiesFragment this$0, ShortcutManager shortcutManager, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LifecycleOwner viewLifecycleOwner = this$0.getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new GamePropertiesFragment$onViewCreated$2$1(this$0, shortcutManager, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(GamePropertiesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LaunchGameDialogFragment.Companion.newInstance(this$0.getArgs().getGame()).show(this$0.getChildFragmentManager(), "LaunchGameDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadList() {
        if (this._binding == null) {
            return;
        }
        getDriverViewModel().updateDriverNameForGame(getArgs().getGame());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SubmenuProperty(R$string.f14info, R$string.info_description, R$drawable.ic_info_outline, null, null, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m146invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m146invoke() {
                GamePropertiesFragmentArgs args;
                FragmentGamePropertiesBinding binding;
                GamePropertiesFragmentDirections.Companion companion = GamePropertiesFragmentDirections.Companion;
                args = GamePropertiesFragment.this.getArgs();
                NavDirections actionPerGamePropertiesFragmentToGameInfoFragment = companion.actionPerGamePropertiesFragmentToGameInfoFragment(args.getGame());
                binding = GamePropertiesFragment.this.getBinding();
                ConstraintLayout root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionPerGamePropertiesFragmentToGameInfoFragment);
            }
        }, 24, null));
        arrayList.add(new SubmenuProperty(R$string.preferences_settings, R$string.per_game_settings_description, R$drawable.ic_settings, null, null, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m147invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m147invoke() {
                GamePropertiesFragmentArgs args;
                FragmentGamePropertiesBinding binding;
                HomeNavigationDirections.Companion companion = HomeNavigationDirections.Companion;
                args = GamePropertiesFragment.this.getArgs();
                NavDirections actionGlobalSettingsActivity = companion.actionGlobalSettingsActivity(args.getGame(), Settings.MenuTag.SECTION_ROOT);
                binding = GamePropertiesFragment.this.getBinding();
                ConstraintLayout root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionGlobalSettingsActivity);
            }
        }, 24, null));
        if (GpuDriverHelper.INSTANCE.supportsCustomDriverLoading()) {
            arrayList.add(new SubmenuProperty(R$string.gpu_driver_manager, R$string.install_gpu_driver_description, R$drawable.ic_build, null, getDriverViewModel().getSelectedDriverTitle(), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m148invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m148invoke() {
                    GamePropertiesFragmentArgs args;
                    FragmentGamePropertiesBinding binding;
                    GamePropertiesFragmentDirections.Companion companion = GamePropertiesFragmentDirections.Companion;
                    args = GamePropertiesFragment.this.getArgs();
                    NavDirections actionPerGamePropertiesFragmentToDriverManagerFragment = companion.actionPerGamePropertiesFragmentToDriverManagerFragment(args.getGame());
                    binding = GamePropertiesFragment.this.getBinding();
                    ConstraintLayout root = binding.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                    ViewKt.findNavController(root).navigate(actionPerGamePropertiesFragmentToDriverManagerFragment);
                }
            }, 8, null));
        }
        if (!getArgs().getGame().isHomebrew()) {
            arrayList.add(new SubmenuProperty(R$string.add_ons, R$string.add_ons_description, R$drawable.ic_edit, null, null, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$4
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m149invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m149invoke() {
                    GamePropertiesFragmentArgs args;
                    FragmentGamePropertiesBinding binding;
                    GamePropertiesFragmentDirections.Companion companion = GamePropertiesFragmentDirections.Companion;
                    args = GamePropertiesFragment.this.getArgs();
                    NavDirections actionPerGamePropertiesFragmentToAddonsFragment = companion.actionPerGamePropertiesFragmentToAddonsFragment(args.getGame());
                    binding = GamePropertiesFragment.this.getBinding();
                    ConstraintLayout root = binding.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                    ViewKt.findNavController(root).navigate(actionPerGamePropertiesFragmentToAddonsFragment);
                }
            }, 24, null));
            arrayList.add(new InstallableProperty(R$string.save_data, R$string.save_data_description, R$drawable.ic_save, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$5
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m150invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m150invoke() {
                    MessageDialogFragment newInstance;
                    MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                    FragmentActivity requireActivity = GamePropertiesFragment.this.requireActivity();
                    int i = R$string.import_save_warning;
                    int i2 = R$string.import_save_warning_description;
                    final GamePropertiesFragment gamePropertiesFragment = GamePropertiesFragment.this;
                    newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$5.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m151invoke();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: collision with other method in class */
                        public final void m151invoke() {
                            HomeViewModel homeViewModel;
                            homeViewModel = GamePropertiesFragment.this.getHomeViewModel();
                            homeViewModel.setOpenImportSaves(true);
                        }
                    }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                    newInstance.show(GamePropertiesFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                }
            }, new File(getArgs().getGame().getSaveDir()).exists() ? new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$6
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m152invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m152invoke() {
                    ActivityResultLauncher activityResultLauncher;
                    GamePropertiesFragmentArgs args;
                    activityResultLauncher = GamePropertiesFragment.this.exportSaves;
                    args = GamePropertiesFragment.this.getArgs();
                    activityResultLauncher.launch(args.getGame().getSaveZipName());
                }
            } : null));
            if (new File(getArgs().getGame().getSaveDir()).exists()) {
                arrayList.add(new SubmenuProperty(R$string.delete_save_data, R$string.delete_save_data_description, R$drawable.ic_delete, null, null, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$7
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m153invoke();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: collision with other method in class */
                    public final void m153invoke() {
                        MessageDialogFragment newInstance;
                        MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                        FragmentActivity requireActivity = GamePropertiesFragment.this.requireActivity();
                        int i = R$string.delete_save_data;
                        int i2 = R$string.delete_save_data_warning_description;
                        final GamePropertiesFragment gamePropertiesFragment = GamePropertiesFragment.this;
                        newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : R.string.cancel, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? R.string.ok : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$7.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Object invoke() {
                                m154invoke();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: collision with other method in class */
                            public final void m154invoke() {
                                GamePropertiesFragmentArgs args;
                                HomeViewModel homeViewModel;
                                args = GamePropertiesFragment.this.getArgs();
                                FilesKt__UtilsKt.deleteRecursively(new File(args.getGame().getSaveDir()));
                                Toast.makeText(YuzuApplication.Companion.getAppContext(), R$string.save_data_deleted_successfully, 0).show();
                                homeViewModel = GamePropertiesFragment.this.getHomeViewModel();
                                homeViewModel.reloadPropertiesList(true);
                            }
                        } : null);
                        newInstance.show(GamePropertiesFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                    }
                }, 24, null));
            }
            String userDirectory = DirectoryInitialization.INSTANCE.getUserDirectory();
            String lowerCase = getArgs().getGame().getSettingsName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            final File file = new File(userDirectory + "/shader/" + lowerCase);
            if (file.exists()) {
                arrayList.add(new SubmenuProperty(R$string.clear_shader_cache, R$string.clear_shader_cache_description, R$drawable.ic_delete, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$8
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        MemoryUtil memoryUtil;
                        float f;
                        FileTreeWalk walkTopDown;
                        Sequence filter;
                        Sequence map;
                        long sumOfLong;
                        if (file.exists()) {
                            walkTopDown = FilesKt__FileTreeWalkKt.walkTopDown(file);
                            filter = SequencesKt___SequencesKt.filter(walkTopDown, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$8$bytes$1
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(File it) {
                                    Intrinsics.checkNotNullParameter(it, "it");
                                    return Boolean.valueOf(it.isFile());
                                }
                            });
                            map = SequencesKt___SequencesKt.map(filter, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$8$bytes$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Long invoke(File it) {
                                    Intrinsics.checkNotNullParameter(it, "it");
                                    return Long.valueOf(it.length());
                                }
                            });
                            sumOfLong = SequencesKt___SequencesKt.sumOfLong(map);
                            memoryUtil = MemoryUtil.INSTANCE;
                            f = (float) sumOfLong;
                        } else {
                            memoryUtil = MemoryUtil.INSTANCE;
                            f = 0.0f;
                        }
                        return MemoryUtil.bytesToSizeUnit$default(memoryUtil, f, false, 2, null);
                    }
                }, null, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$9
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m155invoke();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: collision with other method in class */
                    public final void m155invoke() {
                        MessageDialogFragment newInstance;
                        MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                        FragmentActivity requireActivity = GamePropertiesFragment.this.requireActivity();
                        int i = R$string.clear_shader_cache;
                        int i2 = R$string.clear_shader_cache_warning_description;
                        final File file2 = file;
                        final GamePropertiesFragment gamePropertiesFragment = GamePropertiesFragment.this;
                        newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$reloadList$properties$1$9.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Object invoke() {
                                m156invoke();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: collision with other method in class */
                            public final void m156invoke() {
                                HomeViewModel homeViewModel;
                                FilesKt__UtilsKt.deleteRecursively(file2);
                                Toast.makeText(YuzuApplication.Companion.getAppContext(), R$string.cleared_shaders_successfully, 0).show();
                                homeViewModel = gamePropertiesFragment.getHomeViewModel();
                                homeViewModel.reloadPropertiesList(true);
                            }
                        }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                        newInstance.show(GamePropertiesFragment.this.getParentFragmentManager(), "MessageDialogFragment");
                    }
                }, 16, null));
            }
        }
        RecyclerView recyclerView = getBinding().listProperties;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        recyclerView.setAdapter(new GamePropertiesAdapter(viewLifecycleOwner, arrayList));
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda5
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$9;
                insets$lambda$9 = GamePropertiesFragment.setInsets$lambda$9(GamePropertiesFragment.this, view, windowInsetsCompat);
                return insets$lambda$9;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$9(GamePropertiesFragment this$0, View view, WindowInsetsCompat windowInsets) {
        int i;
        Object obj;
        View listAll;
        int i2;
        int i3;
        int i4;
        ViewUtils viewUtils;
        int i5;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i6 = insets.left + insets2.left;
        int i7 = insets.right + insets2.right;
        if (this$0.getResources().getBoolean(R$bool.small_layout)) {
            ViewUtils viewUtils2 = ViewUtils.INSTANCE;
            listAll = this$0.getBinding().listAll;
            Intrinsics.checkNotNullExpressionValue(listAll, "listAll");
            i3 = 0;
            i = 0;
            i4 = 10;
            obj = null;
            viewUtils = viewUtils2;
            i2 = i6;
        } else {
            if (ViewCompat.getLayoutDirection(this$0.getBinding().getRoot()) == 0) {
                ViewUtils viewUtils3 = ViewUtils.INSTANCE;
                NestedScrollView listAll2 = this$0.getBinding().listAll;
                Intrinsics.checkNotNullExpressionValue(listAll2, "listAll");
                i = 0;
                obj = null;
                viewUtils3.updateMargins(listAll2, (r13 & 1) != 0 ? -1 : 0, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i7, (r13 & 8) != 0 ? -1 : 0);
                listAll = this$0.getBinding().iconLayout;
                Intrinsics.checkNotNull(listAll);
                i3 = insets.top;
                i5 = 0;
                i4 = 12;
                viewUtils = viewUtils3;
                i2 = i6;
                viewUtils.updateMargins(listAll, (r13 & 1) != 0 ? -1 : i2, (r13 & 2) != 0 ? -1 : i3, (r13 & 4) != 0 ? -1 : i5, (r13 & 8) != 0 ? -1 : i);
                int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_fab);
                ViewUtils viewUtils4 = ViewUtils.INSTANCE;
                ExtendedFloatingActionButton buttonStart = this$0.getBinding().buttonStart;
                Intrinsics.checkNotNullExpressionValue(buttonStart, "buttonStart");
                viewUtils4.updateMargins(buttonStart, (r13 & 1) != 0 ? -1 : i6 + dimensionPixelSize, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i7 + dimensionPixelSize, (r13 & 8) != 0 ? -1 : insets.bottom + dimensionPixelSize);
                LinearLayout layoutAll = this$0.getBinding().layoutAll;
                Intrinsics.checkNotNullExpressionValue(layoutAll, "layoutAll");
                layoutAll.setPadding(layoutAll.getPaddingLeft(), insets.top, layoutAll.getPaddingRight(), insets.bottom + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_bottom_list_fab));
                return windowInsets;
            }
            ViewUtils viewUtils5 = ViewUtils.INSTANCE;
            NestedScrollView listAll3 = this$0.getBinding().listAll;
            Intrinsics.checkNotNullExpressionValue(listAll3, "listAll");
            i = 0;
            obj = null;
            viewUtils5.updateMargins(listAll3, (r13 & 1) != 0 ? -1 : i6, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : 0, (r13 & 8) != 0 ? -1 : 0);
            listAll = this$0.getBinding().iconLayout;
            Intrinsics.checkNotNull(listAll);
            i2 = 0;
            i3 = insets.top;
            i4 = 9;
            viewUtils = viewUtils5;
        }
        i5 = i7;
        viewUtils.updateMargins(listAll, (r13 & 1) != 0 ? -1 : i2, (r13 & 2) != 0 ? -1 : i3, (r13 & 4) != 0 ? -1 : i5, (r13 & 8) != 0 ? -1 : i);
        int dimensionPixelSize2 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_fab);
        ViewUtils viewUtils42 = ViewUtils.INSTANCE;
        ExtendedFloatingActionButton buttonStart2 = this$0.getBinding().buttonStart;
        Intrinsics.checkNotNullExpressionValue(buttonStart2, "buttonStart");
        viewUtils42.updateMargins(buttonStart2, (r13 & 1) != 0 ? -1 : i6 + dimensionPixelSize2, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i7 + dimensionPixelSize2, (r13 & 8) != 0 ? -1 : insets.bottom + dimensionPixelSize2);
        LinearLayout layoutAll2 = this$0.getBinding().layoutAll;
        Intrinsics.checkNotNullExpressionValue(layoutAll2, "layoutAll");
        layoutAll2.setPadding(layoutAll2.getPaddingLeft(), insets.top, layoutAll2.getPaddingRight(), insets.bottom + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_bottom_list_fab));
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(1, true));
        setReturnTransition(new MaterialSharedAxis(1, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentGamePropertiesBinding.inflate(getLayoutInflater());
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GamesViewModel.reloadGames$default(getGamesViewModel(), true, false, 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getDriverViewModel().updateDriverNameForGame(getArgs().getGame());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(true);
        getBinding().buttonBack.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GamePropertiesFragment.onViewCreated$lambda$0(view, view2);
            }
        });
        final ShortcutManager shortcutManager = (ShortcutManager) requireActivity().getSystemService(ShortcutManager.class);
        getBinding().buttonShortcut.setEnabled(shortcutManager.isRequestPinShortcutSupported());
        getBinding().buttonShortcut.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GamePropertiesFragment.onViewCreated$lambda$1(GamePropertiesFragment.this, shortcutManager, view2);
            }
        });
        GameIconUtils gameIconUtils = GameIconUtils.INSTANCE;
        Game game = getArgs().getGame();
        ImageView imageGameScreen = getBinding().imageGameScreen;
        Intrinsics.checkNotNullExpressionValue(imageGameScreen, "imageGameScreen");
        gameIconUtils.loadGameIcon(game, imageGameScreen);
        getBinding().title.setText(getArgs().getGame().getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView title = getBinding().title;
        Intrinsics.checkNotNullExpressionValue(title, "title");
        ViewUtils.marquee$default(viewUtils, title, 0L, 1, null);
        getBinding().buttonStart.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GamePropertiesFragment.onViewCreated$lambda$2(GamePropertiesFragment.this, view2);
            }
        });
        reloadList();
        StateFlow openImportSaves = getHomeViewModel().getOpenImportSaves();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new GamePropertiesFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, openImportSaves, null, this, this), 3, null);
        StateFlow reloadPropertiesList = getHomeViewModel().getReloadPropertiesList();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new GamePropertiesFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, reloadPropertiesList, null, this, this), 3, null);
        setInsets();
    }
}
