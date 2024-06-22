package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocument;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
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
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.transition.MaterialFadeThrough;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$bool;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.SetupAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentSetupBinding;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.SetupCallback;
import org.yuzu.yuzu_emu.model.SetupPage;
import org.yuzu.yuzu_emu.model.StepState;
import org.yuzu.yuzu_emu.ui.main.MainActivity;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SetupFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private FragmentSetupBinding _binding;
    private SetupCallback gamesDirCallback;
    private final ActivityResultLauncher getGamesDirectory;
    private final ActivityResultLauncher getProdKey;
    private boolean[] hasBeenWarned;
    private final Lazy homeViewModel$delegate;
    private SetupCallback keyCallback;
    private MainActivity mainActivity;
    private SetupCallback notificationCallback;
    private final ActivityResultLauncher permissionLauncher;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SetupFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$special$$inlined$activityViewModels$default$3
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
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContract() { // from class: androidx.activity.result.contract.ActivityResultContracts$RequestPermission
            @Override // androidx.activity.result.contract.ActivityResultContract
            public Intent createIntent(Context context, String input) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(input, "input");
                return ActivityResultContracts$RequestMultiplePermissions.Companion.createIntent$activity_release(new String[]{input});
            }

            @Override // androidx.activity.result.contract.ActivityResultContract
            public ActivityResultContract.SynchronousResult getSynchronousResult(Context context, String input) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(input, "input");
                if (ContextCompat.checkSelfPermission(context, input) == 0) {
                    return new ActivityResultContract.SynchronousResult(Boolean.TRUE);
                }
                return null;
            }

            @Override // androidx.activity.result.contract.ActivityResultContract
            public Boolean parseResult(int i, Intent intent) {
                boolean z;
                if (intent == null || i != -1) {
                    return Boolean.FALSE;
                }
                int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                boolean z2 = false;
                if (intArrayExtra != null) {
                    int length = intArrayExtra.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            z = false;
                            break;
                        }
                        if (intArrayExtra[i2] == 0) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                    if (z) {
                        z2 = true;
                    }
                }
                return Boolean.valueOf(z2);
            }
        }, new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SetupFragment.permissionLauncher$lambda$8(SetupFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.permissionLauncher = registerForActivityResult;
        ActivityResultLauncher registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SetupFragment.getProdKey$lambda$9(SetupFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.getProdKey = registerForActivityResult2;
        ActivityResultLauncher registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts$OpenDocumentTree(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SetupFragment.getGamesDirectory$lambda$10(SetupFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResult(...)");
        this.getGamesDirectory = registerForActivityResult3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishSetup() {
        PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext()).edit().putBoolean("FirstApplicationLaunch", false).apply();
        MainActivity mainActivity = this.mainActivity;
        if (mainActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainActivity");
            mainActivity = null;
        }
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        mainActivity.finishSetup(ViewKt.findNavController(root));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentSetupBinding getBinding() {
        FragmentSetupBinding fragmentSetupBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSetupBinding);
        return fragmentSetupBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getGamesDirectory$lambda$10(SetupFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            MainActivity mainActivity = this$0.mainActivity;
            if (mainActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainActivity");
                mainActivity = null;
            }
            mainActivity.processGamesDir(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getProdKey$lambda$9(SetupFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            MainActivity mainActivity = this$0.mainActivity;
            SetupCallback setupCallback = null;
            if (mainActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainActivity");
                mainActivity = null;
            }
            mainActivity.processKey(uri);
            if (NativeLibrary.INSTANCE.areKeysPresent()) {
                SetupCallback setupCallback2 = this$0.keyCallback;
                if (setupCallback2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyCallback");
                } else {
                    setupCallback = setupCallback2;
                }
                setupCallback.onStepCompleted();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(SetupFragment this$0, List pages, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pages, "$pages");
        int currentItem = this$0.getBinding().viewPager2.getCurrentItem();
        SetupPage setupPage = (SetupPage) pages.get(currentItem);
        if (setupPage.getHasWarning()) {
            if (((StepState) setupPage.getStepCompleted().invoke()) != StepState.INCOMPLETE) {
                this$0.pageForward();
                return;
            }
            boolean[] zArr = this$0.hasBeenWarned;
            if (zArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("hasBeenWarned");
                zArr = null;
            }
            if (!zArr[currentItem]) {
                SetupWarningDialogFragment.Companion.newInstance(setupPage.getWarningTitleId(), setupPage.getWarningDescriptionId(), setupPage.getWarningHelpLinkId(), currentItem).show(this$0.getChildFragmentManager(), "SetupWarningDialogFragment");
                return;
            }
        }
        this$0.pageForward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(SetupFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pageBackward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void permissionLauncher$lambda$8(SetupFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            SetupCallback setupCallback = this$0.notificationCallback;
            if (setupCallback == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationCallback");
                setupCallback = null;
            }
            setupCallback.onStepCompleted();
        }
        if (bool.booleanValue() || this$0.shouldShowRequestPermissionRationale("android.permission.POST_NOTIFICATIONS")) {
            return;
        }
        new PermissionDeniedDialogFragment().show(this$0.getChildFragmentManager(), "PermissionDeniedDialogFragment");
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda5
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$11;
                insets$lambda$11 = SetupFragment.setInsets$lambda$11(SetupFragment.this, view, windowInsetsCompat);
                return insets$lambda$11;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$11(SetupFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i = insets.left + insets2.left;
        int i2 = insets.top + insets2.top;
        int i3 = insets.right + insets2.right;
        int i4 = insets.bottom + insets2.bottom;
        if (this$0.getResources().getBoolean(R$bool.small_layout)) {
            ViewPager2 viewPager2 = this$0.getBinding().viewPager2;
            Intrinsics.checkNotNullExpressionValue(viewPager2, "viewPager2");
            viewPager2.setPadding(i, i2, i3, viewPager2.getPaddingBottom());
        } else {
            ViewPager2 viewPager22 = this$0.getBinding().viewPager2;
            Intrinsics.checkNotNullExpressionValue(viewPager22, "viewPager2");
            viewPager22.setPadding(viewPager22.getPaddingLeft(), i2, viewPager22.getPaddingRight(), i4);
        }
        ConstraintLayout constraintButtons = this$0.getBinding().constraintButtons;
        Intrinsics.checkNotNullExpressionValue(constraintButtons, "constraintButtons");
        constraintButtons.setPadding(i, constraintButtons.getPaddingTop(), i3, i4);
        return windowInsets;
    }

    public final ActivityResultLauncher getGetGamesDirectory() {
        return this.getGamesDirectory;
    }

    public final ActivityResultLauncher getGetProdKey() {
        return this.getProdKey;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setExitTransition(new MaterialFadeThrough());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSetupBinding.inflate(getLayoutInflater());
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        if (this._binding != null) {
            MaterialButton buttonNext = getBinding().buttonNext;
            Intrinsics.checkNotNullExpressionValue(buttonNext, "buttonNext");
            outState.putBoolean("NextButtonVisibility", buttonNext.getVisibility() == 0);
            MaterialButton buttonBack = getBinding().buttonBack;
            Intrinsics.checkNotNullExpressionValue(buttonBack, "buttonBack");
            outState.putBoolean("BackButtonVisibility", buttonBack.getVisibility() == 0);
        }
        boolean[] zArr = this.hasBeenWarned;
        if (zArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hasBeenWarned");
            zArr = null;
        }
        outState.putBooleanArray("HasBeenWarned", zArr);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        NativeConfig.INSTANCE.saveGlobalConfig();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.yuzu.yuzu_emu.ui.main.MainActivity");
        this.mainActivity = (MainActivity) requireActivity;
        getHomeViewModel().setNavigationVisibility(false, false);
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        onBackPressedDispatcher.addCallback(viewLifecycleOwner, new OnBackPressedCallback() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FragmentSetupBinding binding;
                binding = SetupFragment.this.getBinding();
                int currentItem = binding.viewPager2.getCurrentItem();
                SetupFragment setupFragment = SetupFragment.this;
                if (currentItem > 0) {
                    setupFragment.pageBackward();
                } else {
                    setupFragment.requireActivity().finish();
                }
            }
        });
        requireActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(requireContext(), R.color.transparent));
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new SetupPage(R$drawable.ic_yuzu_title, R$string.welcome, R$string.welcome_description, 0, true, R$string.get_started, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SetupCallback) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SetupCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SetupFragment.this.pageForward();
            }
        }, false, 0, 0, 0, null, 3840, null));
        if (Build.VERSION.SDK_INT >= 33) {
            arrayList.add(new SetupPage(R$drawable.ic_notification, R$string.notifications, R$string.notifications_description, 0, false, R$string.give_permission, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((SetupCallback) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(SetupCallback it) {
                    ActivityResultLauncher activityResultLauncher;
                    Intrinsics.checkNotNullParameter(it, "it");
                    SetupFragment.this.notificationCallback = it;
                    activityResultLauncher = SetupFragment.this.permissionLauncher;
                    activityResultLauncher.launch("android.permission.POST_NOTIFICATIONS");
                }
            }, true, R$string.notification_warning, R$string.notification_warning_description, 0, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final StepState invoke() {
                    return NotificationManagerCompat.from(SetupFragment.this.requireContext()).areNotificationsEnabled() ? StepState.COMPLETE : StepState.INCOMPLETE;
                }
            }));
        }
        arrayList.add(new SetupPage(R$drawable.ic_key, R$string.keys, R$string.keys_description, R$drawable.ic_add, true, R$string.select_keys, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SetupCallback) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SetupCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SetupFragment.this.keyCallback = it;
                SetupFragment.this.getGetProdKey().launch(new String[]{"*/*"});
            }
        }, true, R$string.install_prod_keys_warning, R$string.install_prod_keys_warning_description, R$string.install_prod_keys_warning_help, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$5
            @Override // kotlin.jvm.functions.Function0
            public final StepState invoke() {
                String userDirectory = DirectoryInitialization.INSTANCE.getUserDirectory();
                StringBuilder sb = new StringBuilder();
                sb.append(userDirectory);
                sb.append("/keys/prod.keys");
                return (new File(sb.toString()).exists() && NativeLibrary.INSTANCE.areKeysPresent()) ? StepState.COMPLETE : StepState.INCOMPLETE;
            }
        }));
        arrayList.add(new SetupPage(R$drawable.ic_controller, R$string.games, R$string.games_description, R$drawable.ic_add, true, R$string.add_games, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SetupCallback) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SetupCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SetupFragment.this.gamesDirCallback = it;
                SetupFragment.this.getGetGamesDirectory().launch(new Intent("android.intent.action.OPEN_DOCUMENT_TREE").getData());
            }
        }, true, R$string.add_games_warning, R$string.add_games_warning_description, R$string.add_games_warning_help, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$7
            @Override // kotlin.jvm.functions.Function0
            public final StepState invoke() {
                return (NativeConfig.INSTANCE.getGameDirs().length == 0) ^ true ? StepState.COMPLETE : StepState.INCOMPLETE;
            }
        }));
        arrayList.add(new SetupPage(R$drawable.ic_check, R$string.done, R$string.done_description, R$drawable.ic_arrow_forward, false, R$string.text_continue, new Function1() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$2$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SetupCallback) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SetupCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SetupFragment.this.finishSetup();
            }
        }, false, 0, 0, 0, null, 3840, null));
        StateFlow shouldPageForward = getHomeViewModel().getShouldPageForward();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new SetupFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner2, state, shouldPageForward, null, this, this), 3, null);
        StateFlow gamesDirSelected = getHomeViewModel().getGamesDirSelected();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new SetupFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner3, state, gamesDirSelected, null, this, this), 3, null);
        ViewPager2 viewPager2 = getBinding().viewPager2;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        viewPager2.setAdapter(new SetupAdapter((AppCompatActivity) requireActivity2, arrayList));
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.setUserInputEnabled(false);
        getBinding().viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$onViewCreated$8
            private int previousPosition;

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                ViewUtils viewUtils;
                FragmentSetupBinding binding;
                MaterialButton buttonNext;
                long j;
                int i2;
                Object obj;
                ViewUtils viewUtils2;
                FragmentSetupBinding binding2;
                MaterialButton buttonNext2;
                long j2;
                int i3;
                Object obj2;
                FragmentSetupBinding binding3;
                FragmentSetupBinding binding4;
                FragmentSetupBinding binding5;
                FragmentSetupBinding binding6;
                super.onPageSelected(i);
                if (i != 1 || this.previousPosition != 0) {
                    if (i == 0 && this.previousPosition == 1) {
                        ViewUtils viewUtils3 = ViewUtils.INSTANCE;
                        binding3 = SetupFragment.this.getBinding();
                        MaterialButton buttonBack = binding3.buttonBack;
                        Intrinsics.checkNotNullExpressionValue(buttonBack, "buttonBack");
                        j2 = 0;
                        i3 = 2;
                        obj2 = null;
                        viewUtils2 = viewUtils3;
                        ViewUtils.hideView$default(viewUtils2, buttonBack, 0L, 2, null);
                        binding4 = SetupFragment.this.getBinding();
                        buttonNext2 = binding4.buttonNext;
                        Intrinsics.checkNotNullExpressionValue(buttonNext2, "buttonNext");
                    } else {
                        if (i != arrayList.size() - 1 || this.previousPosition != arrayList.size() - 2) {
                            if (i == arrayList.size() - 2 && this.previousPosition == arrayList.size() - 1) {
                                viewUtils = ViewUtils.INSTANCE;
                                binding = SetupFragment.this.getBinding();
                                buttonNext = binding.buttonNext;
                                Intrinsics.checkNotNullExpressionValue(buttonNext, "buttonNext");
                                j = 0;
                                i2 = 2;
                                obj = null;
                            }
                            this.previousPosition = i;
                        }
                        viewUtils2 = ViewUtils.INSTANCE;
                        binding2 = SetupFragment.this.getBinding();
                        buttonNext2 = binding2.buttonNext;
                        Intrinsics.checkNotNullExpressionValue(buttonNext2, "buttonNext");
                        j2 = 0;
                        i3 = 2;
                        obj2 = null;
                    }
                    ViewUtils.hideView$default(viewUtils2, buttonNext2, j2, i3, obj2);
                    this.previousPosition = i;
                }
                ViewUtils viewUtils4 = ViewUtils.INSTANCE;
                binding5 = SetupFragment.this.getBinding();
                MaterialButton buttonNext3 = binding5.buttonNext;
                Intrinsics.checkNotNullExpressionValue(buttonNext3, "buttonNext");
                j = 0;
                i2 = 2;
                obj = null;
                viewUtils = viewUtils4;
                ViewUtils.showView$default(viewUtils, buttonNext3, 0L, 2, null);
                binding6 = SetupFragment.this.getBinding();
                buttonNext = binding6.buttonBack;
                Intrinsics.checkNotNullExpressionValue(buttonNext, "buttonBack");
                ViewUtils.showView$default(viewUtils, buttonNext, j, i2, obj);
                this.previousPosition = i;
            }
        });
        getBinding().buttonNext.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetupFragment.onViewCreated$lambda$6(SetupFragment.this, arrayList, view2);
            }
        });
        getBinding().buttonBack.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.SetupFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetupFragment.onViewCreated$lambda$7(SetupFragment.this, view2);
            }
        });
        if (bundle != null) {
            boolean z = bundle.getBoolean("NextButtonVisibility");
            boolean z2 = bundle.getBoolean("BackButtonVisibility");
            boolean[] booleanArray = bundle.getBooleanArray("HasBeenWarned");
            Intrinsics.checkNotNull(booleanArray);
            this.hasBeenWarned = booleanArray;
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialButton buttonNext = getBinding().buttonNext;
            Intrinsics.checkNotNullExpressionValue(buttonNext, "buttonNext");
            ViewUtils.setVisible$default(viewUtils, buttonNext, z, false, 2, null);
            MaterialButton buttonBack = getBinding().buttonBack;
            Intrinsics.checkNotNullExpressionValue(buttonBack, "buttonBack");
            ViewUtils.setVisible$default(viewUtils, buttonBack, z2, false, 2, null);
        } else {
            this.hasBeenWarned = new boolean[arrayList.size()];
        }
        setInsets();
    }

    public final void pageBackward() {
        if (this._binding != null) {
            getBinding().viewPager2.setCurrentItem(r1.getCurrentItem() - 1);
        }
    }

    public final void pageForward() {
        if (this._binding != null) {
            ViewPager2 viewPager2 = getBinding().viewPager2;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    }

    public final void setPageWarned(int i) {
        boolean[] zArr = this.hasBeenWarned;
        if (zArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hasBeenWarned");
            zArr = null;
        }
        zArr[i] = true;
    }
}
