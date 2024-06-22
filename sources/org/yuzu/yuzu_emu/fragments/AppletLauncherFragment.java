package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.transition.MaterialSharedAxis;
import java.util.List;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.AppletAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentAppletLauncherBinding;
import org.yuzu.yuzu_emu.model.Applet;
import org.yuzu.yuzu_emu.model.AppletInfo;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class AppletLauncherFragment extends Fragment {
    private FragmentAppletLauncherBinding _binding;
    private final Lazy homeViewModel$delegate;

    public AppletLauncherFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AppletLauncherFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AppletLauncherFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AppletLauncherFragment$special$$inlined$activityViewModels$default$3
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

    private final FragmentAppletLauncherBinding getBinding() {
        FragmentAppletLauncherBinding fragmentAppletLauncherBinding = this._binding;
        Intrinsics.checkNotNull(fragmentAppletLauncherBinding);
        return fragmentAppletLauncherBinding;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(AppletLauncherFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoordinatorLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.AppletLauncherFragment$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$2;
                insets$lambda$2 = AppletLauncherFragment.setInsets$lambda$2(AppletLauncherFragment.this, view, windowInsetsCompat);
                return insets$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$2(AppletLauncherFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        MaterialToolbar toolbarApplets = this$0.getBinding().toolbarApplets;
        Intrinsics.checkNotNullExpressionValue(toolbarApplets, "toolbarApplets");
        viewUtils.updateMargins(toolbarApplets, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listApplets = this$0.getBinding().listApplets;
        Intrinsics.checkNotNullExpressionValue(listApplets, "listApplets");
        viewUtils.updateMargins(listApplets, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        RecyclerView listApplets2 = this$0.getBinding().listApplets;
        Intrinsics.checkNotNullExpressionValue(listApplets2, "listApplets");
        listApplets2.setPadding(listApplets2.getPaddingLeft(), listApplets2.getPaddingTop(), listApplets2.getPaddingRight(), insets.bottom);
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
        this._binding = FragmentAppletLauncherBinding.inflate(inflater);
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        List listOf;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarApplets.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AppletLauncherFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AppletLauncherFragment.onViewCreated$lambda$0(AppletLauncherFragment.this, view2);
            }
        });
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Applet[]{new Applet(R$string.album_applet, R$string.album_applet_description, R$drawable.ic_album, AppletInfo.PhotoViewer, null, 16, null), new Applet(R$string.cabinet_applet, R$string.cabinet_applet_description, R$drawable.ic_nfc, AppletInfo.Cabinet, null, 16, null), new Applet(R$string.mii_edit_applet, R$string.mii_edit_applet_description, R$drawable.ic_mii, AppletInfo.MiiEdit, null, 16, null)});
        RecyclerView recyclerView = getBinding().listApplets;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        recyclerView.setAdapter(new AppletAdapter(requireActivity, listOf));
        setInsets();
    }
}
