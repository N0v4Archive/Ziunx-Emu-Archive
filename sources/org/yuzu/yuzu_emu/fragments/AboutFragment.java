package org.yuzu.yuzu_emu.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.ViewKt;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.transition.MaterialSharedAxis;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.FragmentAboutBinding;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class AboutFragment extends Fragment {
    private FragmentAboutBinding _binding;
    private final Lazy homeViewModel$delegate;

    public AboutFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$special$$inlined$activityViewModels$default$3
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

    private final FragmentAboutBinding getBinding() {
        FragmentAboutBinding fragmentAboutBinding = this._binding;
        Intrinsics.checkNotNull(fragmentAboutBinding);
        return fragmentAboutBinding;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoordinatorLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$1(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.requireContext(), R$string.gaia_is_not_real, 0).show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.contributors_link);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.openLink(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setExitTransition(new MaterialSharedAxis(0, true));
        CoordinatorLayout root = this$0.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewKt.findNavController(root).navigate(R$id.action_aboutFragment_to_licensesFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.requireContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(this$0.getString(R$string.build), "6f589614a"));
        if (Build.VERSION.SDK_INT < 33) {
            Toast.makeText(this$0.requireContext(), R$string.copied_to_clipboard, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.support_link);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.openLink(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.website_link);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.openLink(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(AboutFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.github_link);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.openLink(string);
    }

    private final void openLink(String str) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda8
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$8;
                insets$lambda$8 = AboutFragment.setInsets$lambda$8(AboutFragment.this, view, windowInsetsCompat);
                return insets$lambda$8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$8(AboutFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        MaterialToolbar toolbarAbout = this$0.getBinding().toolbarAbout;
        Intrinsics.checkNotNullExpressionValue(toolbarAbout, "toolbarAbout");
        viewUtils.updateMargins(toolbarAbout, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        NestedScrollView scrollAbout = this$0.getBinding().scrollAbout;
        Intrinsics.checkNotNullExpressionValue(scrollAbout, "scrollAbout");
        viewUtils.updateMargins(scrollAbout, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        LinearLayout contentAbout = this$0.getBinding().contentAbout;
        Intrinsics.checkNotNullExpressionValue(contentAbout, "contentAbout");
        contentAbout.setPadding(contentAbout.getPaddingLeft(), contentAbout.getPaddingTop(), contentAbout.getPaddingRight(), insets.bottom);
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
        this._binding = FragmentAboutBinding.inflate(getLayoutInflater());
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        getHomeViewModel().setNavigationVisibility(false, true);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        getBinding().toolbarAbout.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$0(AboutFragment.this, view2);
            }
        });
        getBinding().imageLogo.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                boolean onViewCreated$lambda$1;
                onViewCreated$lambda$1 = AboutFragment.onViewCreated$lambda$1(AboutFragment.this, view2);
                return onViewCreated$lambda$1;
            }
        });
        getBinding().buttonContributors.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$2(AboutFragment.this, view2);
            }
        });
        getBinding().buttonLicenses.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$3(AboutFragment.this, view2);
            }
        });
        getBinding().textVersionName.setText("0.0.271");
        getBinding().buttonVersionName.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$4(AboutFragment.this, view2);
            }
        });
        getBinding().buttonDiscord.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$5(AboutFragment.this, view2);
            }
        });
        getBinding().buttonWebsite.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$6(AboutFragment.this, view2);
            }
        });
        getBinding().buttonGithub.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.AboutFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AboutFragment.onViewCreated$lambda$7(AboutFragment.this, view2);
            }
        });
        setInsets();
    }
}
