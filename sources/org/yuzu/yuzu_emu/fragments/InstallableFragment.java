package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$CreateDocument;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocument;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import com.google.android.material.transition.MaterialSharedAxis;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.InstallableAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentInstallablesBinding;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.Installable;
import org.yuzu.yuzu_emu.ui.main.MainActivity;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class InstallableFragment extends Fragment {
    private FragmentInstallablesBinding _binding;
    private final ActivityResultLauncher exportSaves;
    private final Lazy homeViewModel$delegate;
    private final ActivityResultLauncher importSaves;

    public InstallableFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$special$$inlined$activityViewModels$default$3
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
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                InstallableFragment.importSaves$lambda$4(InstallableFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.importSaves = registerForActivityResult;
        ActivityResultLauncher registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts$CreateDocument("application/zip"), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                InstallableFragment.exportSaves$lambda$5(InstallableFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.exportSaves = registerForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportSaves$lambda$5(InstallableFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.save_files_exporting, false, new InstallableFragment$exportSaves$1$1(this$0, uri, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    private final FragmentInstallablesBinding getBinding() {
        FragmentInstallablesBinding fragmentInstallablesBinding = this._binding;
        Intrinsics.checkNotNull(fragmentInstallablesBinding);
        return fragmentInstallablesBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void importSaves$lambda$4(InstallableFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        File file = new File(this$0.requireContext().getCacheDir().getPath() + "/saves/");
        file.mkdir();
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.save_files_importing, false, new InstallableFragment$importSaves$1$1(uri, file, this$0, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(InstallableFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoordinatorLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$$ExternalSyntheticLambda3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$3;
                insets$lambda$3 = InstallableFragment.setInsets$lambda$3(InstallableFragment.this, view, windowInsetsCompat);
                return insets$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$3(InstallableFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        MaterialToolbar toolbarInstallables = this$0.getBinding().toolbarInstallables;
        Intrinsics.checkNotNullExpressionValue(toolbarInstallables, "toolbarInstallables");
        viewUtils.updateMargins(toolbarInstallables, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listInstallables = this$0.getBinding().listInstallables;
        Intrinsics.checkNotNullExpressionValue(listInstallables, "listInstallables");
        viewUtils.updateMargins(listInstallables, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listInstallables2 = this$0.getBinding().listInstallables;
        Intrinsics.checkNotNullExpressionValue(listInstallables2, "listInstallables");
        listInstallables2.setPadding(listInstallables2.getPaddingLeft(), listInstallables2.getPaddingTop(), listInstallables2.getPaddingRight(), insets.bottom);
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
        this._binding = FragmentInstallablesBinding.inflate(getLayoutInflater());
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        List listOf;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.yuzu.yuzu_emu.ui.main.MainActivity");
        final MainActivity mainActivity = (MainActivity) requireActivity;
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarInstallables.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InstallableFragment.onViewCreated$lambda$0(InstallableFragment.this, view2);
            }
        });
        StateFlow openImportSaves = getHomeViewModel().getOpenImportSaves();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new InstallableFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, openImportSaves, null, this), 3, null);
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Installable[]{new Installable(R$string.user_data, R$string.user_data_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m171invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m171invoke() {
                MainActivity.this.getImportUserData().launch(new String[]{"application/zip"});
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m172invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m172invoke() {
                MainActivity.this.getExportUserData().launch("export.zip");
            }
        }), new Installable(R$string.manage_save_data, R$string.manage_save_data_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m173invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m173invoke() {
                MessageDialogFragment newInstance;
                MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
                FragmentActivity requireActivity2 = InstallableFragment.this.requireActivity();
                int i = R$string.import_save_warning;
                int i2 = R$string.import_save_warning_description;
                final InstallableFragment installableFragment = InstallableFragment.this;
                newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity2, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$3.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m174invoke();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: collision with other method in class */
                    public final void m174invoke() {
                        HomeViewModel homeViewModel;
                        homeViewModel = InstallableFragment.this.getHomeViewModel();
                        homeViewModel.setOpenImportSaves(true);
                    }
                }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                newInstance.show(InstallableFragment.this.getParentFragmentManager(), "MessageDialogFragment");
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m175invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m175invoke() {
                ActivityResultLauncher activityResultLauncher;
                DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
                String userDirectory = directoryInitialization.getUserDirectory();
                NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
                File file = new File(userDirectory + "/nand" + nativeLibrary.getDefaultProfileSaveDataRoot(false));
                File file2 = new File(directoryInitialization.getUserDirectory() + "/nand" + nativeLibrary.getDefaultProfileSaveDataRoot(true));
                if (!file.exists() && !file2.exists()) {
                    Toast.makeText(YuzuApplication.Companion.getAppContext(), R$string.no_save_data_found, 0).show();
                    return;
                }
                activityResultLauncher = InstallableFragment.this.exportSaves;
                activityResultLauncher.launch(InstallableFragment.this.getString(R$string.save_data) + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        }), new Installable(R$string.install_game_content, R$string.install_game_content_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m176invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m176invoke() {
                MainActivity.this.getInstallGameUpdate().launch(new String[]{"*/*"});
            }
        }, null, 8, null), new Installable(R$string.install_firmware, R$string.install_firmware_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m177invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m177invoke() {
                MainActivity.this.getGetFirmware().launch(new String[]{"application/zip"});
            }
        }, null, 8, null), new Installable(R$string.install_prod_keys, R$string.install_prod_keys_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m178invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m178invoke() {
                MainActivity.this.getGetProdKey().launch(new String[]{"*/*"});
            }
        }, null, 8, null), new Installable(R$string.install_amiibo_keys, R$string.install_amiibo_keys_description, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.InstallableFragment$onViewCreated$installables$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m179invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m179invoke() {
                MainActivity.this.getGetAmiiboKey().launch(new String[]{"*/*"});
            }
        }, null, 8, null)});
        RecyclerView recyclerView = getBinding().listInstallables;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        recyclerView.setAdapter(new InstallableAdapter(listOf));
        setInsets();
    }
}
