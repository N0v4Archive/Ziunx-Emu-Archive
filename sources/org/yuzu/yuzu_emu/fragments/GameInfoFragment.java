package org.yuzu.yuzu_emu.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.ViewKt;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.MaterialSharedAxis;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__IndentKt;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.FragmentGameInfoBinding;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.GameMetadata;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class GameInfoFragment extends Fragment {
    private FragmentGameInfoBinding _binding;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(GameInfoFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$special$$inlined$navArgs$1
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

    public GameInfoFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$special$$inlined$activityViewModels$default$3
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

    private final void copyToClipboard(String str, String str2) {
        Object systemService = requireContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(str, str2));
        if (Build.VERSION.SDK_INT < 33) {
            Toast.makeText(requireContext(), R$string.copied_to_clipboard, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GameInfoFragmentArgs getArgs() {
        return (GameInfoFragmentArgs) this.args$delegate.getValue();
    }

    private final FragmentGameInfoBinding getBinding() {
        FragmentGameInfoBinding fragmentGameInfoBinding = this._binding;
        Intrinsics.checkNotNull(fragmentGameInfoBinding);
        return fragmentGameInfoBinding;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$0(View view, View view2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        ViewKt.findNavController(view).popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$1(GameInfoFragment this$0, String pathString, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pathString, "$pathString");
        String string = this$0.getString(R$string.path);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.copyToClipboard(string, pathString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$2(GameInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.program_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.copyToClipboard(string, this$0.getArgs().getGame().getProgramIdHex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$3(GameInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.developer);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.copyToClipboard(string, this$0.getArgs().getGame().getDeveloper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$4(GameInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R$string.version);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this$0.copyToClipboard(string, this$0.getArgs().getGame().getVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$5(GameInfoFragment this$0, String pathString, View view) {
        String trimIndent;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pathString, "$pathString");
        trimIndent = StringsKt__IndentKt.trimIndent("\n                    " + this$0.getArgs().getGame().getTitle() + "\n                    " + this$0.getString(R$string.path) + " - " + pathString + "\n                    " + this$0.getString(R$string.program_id) + " - " + this$0.getArgs().getGame().getProgramIdHex() + "\n                    " + this$0.getString(R$string.developer) + " - " + this$0.getArgs().getGame().getDeveloper() + "\n                    " + this$0.getString(R$string.version) + " - " + this$0.getArgs().getGame().getVersion() + "\n                ");
        this$0.copyToClipboard(this$0.getArgs().getGame().getTitle(), trimIndent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$6(GameInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        companion.newInstance(requireActivity, R$string.verifying, true, new GameInfoFragment$onViewCreated$1$7$1(this$0, null)).show(this$0.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda7
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$8;
                insets$lambda$8 = GameInfoFragment.setInsets$lambda$8(GameInfoFragment.this, view, windowInsetsCompat);
                return insets$lambda$8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$8(GameInfoFragment this$0, View view, WindowInsetsCompat windowInsets) {
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
        MaterialToolbar toolbarInfo = this$0.getBinding().toolbarInfo;
        Intrinsics.checkNotNullExpressionValue(toolbarInfo, "toolbarInfo");
        viewUtils.updateMargins(toolbarInfo, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        NestedScrollView scrollInfo = this$0.getBinding().scrollInfo;
        Intrinsics.checkNotNullExpressionValue(scrollInfo, "scrollInfo");
        viewUtils.updateMargins(scrollInfo, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        LinearLayout contentInfo = this$0.getBinding().contentInfo;
        Intrinsics.checkNotNullExpressionValue(contentInfo, "contentInfo");
        contentInfo.setPadding(contentInfo.getPaddingLeft(), contentInfo.getPaddingTop(), contentInfo.getPaddingRight(), insets.bottom);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
        getArgs().getGame().setVersion(GameMetadata.INSTANCE.getVersion(getArgs().getGame().getPath(), true));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentGameInfoBinding.inflate(inflater);
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(false, false);
        getHomeViewModel().setStatusBarShadeVisibility(false);
        FragmentGameInfoBinding binding = getBinding();
        binding.toolbarInfo.setTitle(getArgs().getGame().getTitle());
        binding.toolbarInfo.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$0(view, view2);
            }
        });
        final String path = Uri.parse(getArgs().getGame().getPath()).getPath();
        if (path == null) {
            path = "";
        }
        Intrinsics.checkNotNull(path);
        binding.path.setHint(R$string.path);
        binding.pathField.setText(path);
        binding.pathField.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$1(GameInfoFragment.this, path, view2);
            }
        });
        binding.programId.setHint(R$string.program_id);
        binding.programIdField.setText(getArgs().getGame().getProgramIdHex());
        binding.programIdField.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$2(GameInfoFragment.this, view2);
            }
        });
        if (getArgs().getGame().getDeveloper().length() > 0) {
            binding.developer.setHint(R$string.developer);
            binding.developerField.setText(getArgs().getGame().getDeveloper());
            binding.developerField.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    GameInfoFragment.onViewCreated$lambda$7$lambda$3(GameInfoFragment.this, view2);
                }
            });
        } else {
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            TextInputLayout developer = binding.developer;
            Intrinsics.checkNotNullExpressionValue(developer, "developer");
            ViewUtils.setVisible$default(viewUtils, developer, false, false, 2, null);
        }
        binding.version.setHint(R$string.version);
        binding.versionField.setText(getArgs().getGame().getVersion());
        binding.versionField.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$4(GameInfoFragment.this, view2);
            }
        });
        binding.buttonCopy.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$5(GameInfoFragment.this, path, view2);
            }
        });
        binding.buttonVerifyIntegrity.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameInfoFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameInfoFragment.onViewCreated$lambda$7$lambda$6(GameInfoFragment.this, view2);
            }
        });
        setInsets();
    }
}
