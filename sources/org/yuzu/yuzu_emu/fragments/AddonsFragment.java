package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.documentfile.provider.DocumentFile;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.transition.MaterialSharedAxis;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.AddonAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentAddonsBinding;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.AddonUtil;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class AddonsFragment extends Fragment {
    private FragmentAddonsBinding _binding;
    private final Lazy addonViewModel$delegate;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AddonsFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$navArgs$1
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
    private final Lazy homeViewModel$delegate;
    private final ActivityResultLauncher installAddon;

    public AddonsFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$3
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
        this.addonViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AddonViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$special$$inlined$activityViewModels$default$6
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
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$OpenDocumentTree(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                AddonsFragment.installAddon$lambda$11(AddonsFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.installAddon = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonViewModel getAddonViewModel() {
        return (AddonViewModel) this.addonViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonsFragmentArgs getArgs() {
        return (AddonsFragmentArgs) this.args$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentAddonsBinding getBinding() {
        FragmentAddonsBinding fragmentAddonsBinding = this._binding;
        Intrinsics.checkNotNull(fragmentAddonsBinding);
        return fragmentAddonsBinding;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installAddon$lambda$11(AddonsFragment this$0, Uri uri) {
        boolean z;
        MessageDialogFragment newInstance;
        String str;
        boolean contains;
        MessageDialogFragment newInstance2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this$0.requireContext(), uri);
        if (fromTreeUri == null) {
            newInstance2 = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this$0.requireActivity(), (r31 & 2) != 0 ? 0 : R$string.invalid_directory, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.invalid_directory_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
            newInstance2.show(this$0.getParentFragmentManager(), "MessageDialogFragment");
            return;
        }
        DocumentFile[] listFiles = fromTreeUri.listFiles();
        Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
        int length = listFiles.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            DocumentFile documentFile = listFiles[i];
            List validAddonDirectories = AddonUtil.INSTANCE.getValidAddonDirectories();
            String name = documentFile.getName();
            if (name != null) {
                Intrinsics.checkNotNull(name);
                str = name.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
            } else {
                str = null;
            }
            contains = CollectionsKt___CollectionsKt.contains(validAddonDirectories, str);
            if (contains) {
                z = true;
                break;
            }
            i++;
        }
        newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this$0.requireActivity(), (r31 & 2) != 0 ? 0 : R$string.invalid_directory, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.invalid_directory_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
        if (!z) {
            newInstance.show(this$0.getParentFragmentManager(), "MessageDialogFragment");
            return;
        }
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.installing_game_content, false, new AddonsFragment$installAddon$1$1(fromTreeUri, this$0, newInstance, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(AddonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(AddonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new ContentTypeSelectionDialogFragment().show(this$0.getParentFragmentManager(), "ContentTypeSelectionDialogFragment");
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$$ExternalSyntheticLambda3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$12;
                insets$lambda$12 = AddonsFragment.setInsets$lambda$12(AddonsFragment.this, view, windowInsetsCompat);
                return insets$lambda$12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$12(AddonsFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        MaterialToolbar toolbarAddons = this$0.getBinding().toolbarAddons;
        Intrinsics.checkNotNullExpressionValue(toolbarAddons, "toolbarAddons");
        viewUtils.updateMargins(toolbarAddons, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listAddons = this$0.getBinding().listAddons;
        Intrinsics.checkNotNullExpressionValue(listAddons, "listAddons");
        viewUtils.updateMargins(listAddons, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listAddons2 = this$0.getBinding().listAddons;
        Intrinsics.checkNotNullExpressionValue(listAddons2, "listAddons");
        listAddons2.setPadding(listAddons2.getPaddingLeft(), listAddons2.getPaddingTop(), listAddons2.getPaddingRight(), insets.bottom + this$0.getResources().getDimensionPixelSize(R$dimen.spacing_bottom_list_fab));
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_fab);
        ExtendedFloatingActionButton buttonInstall = this$0.getBinding().buttonInstall;
        Intrinsics.checkNotNullExpressionValue(buttonInstall, "buttonInstall");
        viewUtils.updateMargins(buttonInstall, (r13 & 1) != 0 ? -1 : i + dimensionPixelSize, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2 + dimensionPixelSize, (r13 & 8) != 0 ? -1 : insets.bottom + dimensionPixelSize);
        return windowInsets;
    }

    public final ActivityResultLauncher getInstallAddon() {
        return this.installAddon;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getAddonViewModel().onOpenAddons(getArgs().getGame());
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentAddonsBinding.inflate(inflater);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getAddonViewModel().onCloseAddons();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getAddonViewModel().refreshAddons();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, false);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarAddons.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AddonsFragment.onViewCreated$lambda$0(AddonsFragment.this, view2);
            }
        });
        getBinding().toolbarAddons.setTitle(getString(R$string.addons_game, getArgs().getGame().getTitle()));
        RecyclerView recyclerView = getBinding().listAddons;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new AddonAdapter(getAddonViewModel()));
        StateFlow addonList = getAddonViewModel().getAddonList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new AddonsFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, addonList, null, this), 3, null);
        StateFlow showModInstallPicker = getAddonViewModel().getShowModInstallPicker();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new AddonsFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, showModInstallPicker, null, this, this), 3, null);
        StateFlow showModNoticeDialog = getAddonViewModel().getShowModNoticeDialog();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new AddonsFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, showModNoticeDialog, null, this, this), 3, null);
        StateFlow addonToDelete = getAddonViewModel().getAddonToDelete();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner4), null, null, new AddonsFragment$onViewCreated$$inlined$collect$default$4(viewLifecycleOwner4, state, addonToDelete, null, this, this), 3, null);
        getBinding().buttonInstall.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AddonsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AddonsFragment.onViewCreated$lambda$9(AddonsFragment.this, view2);
            }
        });
        setInsets();
    }
}
