package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.transition.MaterialSharedAxis;
import java.util.List;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.LicenseAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentLicensesBinding;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.License;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class LicensesFragment extends Fragment {
    private FragmentLicensesBinding _binding;
    private final Lazy homeViewModel$delegate;

    public LicensesFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.LicensesFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.LicensesFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.LicensesFragment$special$$inlined$activityViewModels$default$3
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

    private final FragmentLicensesBinding getBinding() {
        FragmentLicensesBinding fragmentLicensesBinding = this._binding;
        Intrinsics.checkNotNull(fragmentLicensesBinding);
        return fragmentLicensesBinding;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(LicensesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoordinatorLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.LicensesFragment$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$2;
                insets$lambda$2 = LicensesFragment.setInsets$lambda$2(LicensesFragment.this, view, windowInsetsCompat);
                return insets$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$2(LicensesFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        AppBarLayout appbarLicenses = this$0.getBinding().appbarLicenses;
        Intrinsics.checkNotNullExpressionValue(appbarLicenses, "appbarLicenses");
        viewUtils.updateMargins(appbarLicenses, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listLicenses = this$0.getBinding().listLicenses;
        Intrinsics.checkNotNullExpressionValue(listLicenses, "listLicenses");
        viewUtils.updateMargins(listLicenses, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listLicenses2 = this$0.getBinding().listLicenses;
        Intrinsics.checkNotNullExpressionValue(listLicenses2, "listLicenses");
        listLicenses2.setPadding(listLicenses2.getPaddingLeft(), listLicenses2.getPaddingTop(), listLicenses2.getPaddingRight(), insets.bottom);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentLicensesBinding.inflate(getLayoutInflater());
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        List listOf;
        Intrinsics.checkNotNullParameter(view, "view");
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarLicenses.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.LicensesFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LicensesFragment.onViewCreated$lambda$0(LicensesFragment.this, view2);
            }
        });
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new License[]{new License(R$string.license_fidelityfx_fsr, R$string.license_fidelityfx_fsr_description, R$string.license_fidelityfx_fsr_link, R$string.license_fidelityfx_fsr_copyright, R$string.license_fidelityfx_fsr_text), new License(R$string.license_cubeb, R$string.license_cubeb_description, R$string.license_cubeb_link, R$string.license_cubeb_copyright, R$string.license_cubeb_text), new License(R$string.license_dynarmic, R$string.license_dynarmic_description, R$string.license_dynarmic_link, R$string.license_dynarmic_copyright, R$string.license_dynarmic_text), new License(R$string.license_ffmpeg, R$string.license_ffmpeg_description, R$string.license_ffmpeg_link, R$string.license_ffmpeg_copyright, R$string.license_ffmpeg_text), new License(R$string.license_opus, R$string.license_opus_description, R$string.license_opus_link, R$string.license_opus_copyright, R$string.license_opus_text), new License(R$string.license_sirit, R$string.license_sirit_description, R$string.license_sirit_link, R$string.license_sirit_copyright, R$string.license_sirit_text), new License(R$string.license_adreno_tools, R$string.license_adreno_tools_description, R$string.license_adreno_tools_link, R$string.license_adreno_tools_copyright, R$string.license_adreno_tools_text)});
        RecyclerView recyclerView = getBinding().listLicenses;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        recyclerView.setAdapter(new LicenseAdapter((AppCompatActivity) requireActivity, listOf));
        setInsets();
    }
}
