package org.yuzu.yuzu_emu.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.transition.MaterialSharedAxis;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.BuildConfig;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.HomeSettingAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentHomeSettingsBinding;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.HomeSetting;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.ui.main.MainActivity;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.Log;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class HomeSettingsFragment extends Fragment {
    private FragmentHomeSettingsBinding _binding;
    private final Lazy driverViewModel$delegate;
    private final Lazy homeViewModel$delegate;
    private MainActivity mainActivity;

    public HomeSettingsFragment() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$3
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
        this.driverViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$special$$inlined$activityViewModels$default$6
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

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentHomeSettingsBinding getBinding() {
        FragmentHomeSettingsBinding fragmentHomeSettingsBinding = this._binding;
        Intrinsics.checkNotNull(fragmentHomeSettingsBinding);
        return fragmentHomeSettingsBinding;
    }

    private final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    private final Intent getFileManagerIntent(String str) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName(str, "com.android.documentsui.files.FilesActivity");
        intent.setFlags(268435456);
        return intent;
    }

    private final Intent getFileManagerIntentOnDocumentProvider(String str) {
        String str2 = requireContext().getPackageName() + ".user";
        Intent intent = new Intent(str);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(DocumentsContract.buildRootUri(str2, "root"));
        intent.addFlags(194);
        return intent;
    }

    private final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openFileManager() {
        try {
            try {
                try {
                    try {
                        startActivity(getFileManagerIntentOnDocumentProvider("android.intent.action.VIEW"));
                    } catch (ActivityNotFoundException unused) {
                        startActivity(getFileManagerIntent("com.android.documentsui"));
                        showNoLinkNotification();
                    }
                } catch (ActivityNotFoundException unused2) {
                    Toast.makeText(requireContext(), getResources().getString(R$string.no_file_manager), 1).show();
                }
            } catch (ActivityNotFoundException unused3) {
                startActivity(getFileManagerIntentOnDocumentProvider("android.provider.action.BROWSE"));
            }
        } catch (ActivityNotFoundException unused4) {
            startActivity(getFileManagerIntent("com.google.android.documentsui"));
            showNoLinkNotification();
        }
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$3;
                insets$lambda$3 = HomeSettingsFragment.setInsets$lambda$3(HomeSettingsFragment.this, view, windowInsetsCompat);
                return insets$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$3(HomeSettingsFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation);
        int dimensionPixelSize2 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_navigation_rail);
        int i = insets.left + insets2.left;
        int i2 = insets.right + insets2.right;
        NestedScrollView scrollViewSettings = this$0.getBinding().scrollViewSettings;
        Intrinsics.checkNotNullExpressionValue(scrollViewSettings, "scrollViewSettings");
        scrollViewSettings.setPadding(scrollViewSettings.getPaddingLeft(), insets.top, scrollViewSettings.getPaddingRight(), insets.bottom);
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        NestedScrollView scrollViewSettings2 = this$0.getBinding().scrollViewSettings;
        Intrinsics.checkNotNullExpressionValue(scrollViewSettings2, "scrollViewSettings");
        viewUtils.updateMargins(scrollViewSettings2, (r13 & 1) != 0 ? -1 : i, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i2, (r13 & 8) != 0 ? -1 : 0);
        LinearLayoutCompat linearLayoutSettings = this$0.getBinding().linearLayoutSettings;
        Intrinsics.checkNotNullExpressionValue(linearLayoutSettings, "linearLayoutSettings");
        linearLayoutSettings.setPadding(linearLayoutSettings.getPaddingLeft(), linearLayoutSettings.getPaddingTop(), linearLayoutSettings.getPaddingRight(), dimensionPixelSize);
        if (ViewCompat.getLayoutDirection(view) == 0) {
            LinearLayoutCompat linearLayoutSettings2 = this$0.getBinding().linearLayoutSettings;
            Intrinsics.checkNotNullExpressionValue(linearLayoutSettings2, "linearLayoutSettings");
            linearLayoutSettings2.setPadding(dimensionPixelSize2, linearLayoutSettings2.getPaddingTop(), linearLayoutSettings2.getPaddingRight(), linearLayoutSettings2.getPaddingBottom());
        } else {
            LinearLayoutCompat linearLayoutSettings3 = this$0.getBinding().linearLayoutSettings;
            Intrinsics.checkNotNullExpressionValue(linearLayoutSettings3, "linearLayoutSettings");
            linearLayoutSettings3.setPadding(linearLayoutSettings3.getPaddingLeft(), linearLayoutSettings3.getPaddingTop(), dimensionPixelSize2, linearLayoutSettings3.getPaddingBottom());
        }
        return windowInsets;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void shareLog() {
        Uri uri;
        MainActivity mainActivity = this.mainActivity;
        MainActivity mainActivity2 = null;
        if (mainActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainActivity");
            mainActivity = null;
        }
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(mainActivity, DocumentsContract.buildDocumentUri("org.yuzu.yuzu_emu.ea.user", "root/log/yuzu_log.txt"));
        Intrinsics.checkNotNull(fromSingleUri);
        MainActivity mainActivity3 = this.mainActivity;
        if (mainActivity3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainActivity");
        } else {
            mainActivity2 = mainActivity3;
        }
        DocumentFile fromSingleUri2 = DocumentFile.fromSingleUri(mainActivity2, DocumentsContract.buildDocumentUri("org.yuzu.yuzu_emu.ea.user", "root/log/yuzu_log.txt.old.txt"));
        Intrinsics.checkNotNull(fromSingleUri2);
        Intent addFlags = new Intent("android.intent.action.SEND").setDataAndType(fromSingleUri.getUri(), "text/plain").addFlags(1);
        Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
        if (!Log.INSTANCE.getGameLaunched() && fromSingleUri2.exists()) {
            uri = fromSingleUri2.getUri();
        } else {
            if (!fromSingleUri.exists()) {
                Toast.makeText(requireContext(), getText(R$string.share_log_missing), 0).show();
                return;
            }
            uri = fromSingleUri.getUri();
        }
        addFlags.putExtra("android.intent.extra.STREAM", uri);
        startActivity(Intent.createChooser(addFlags, getText(R$string.share_log)));
    }

    private final void showNoLinkNotification() {
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(requireContext(), getString(R$string.notice_notification_channel_id)).setSmallIcon(R$drawable.ic_stat_notification_logo).setContentTitle(getString(R$string.notification_no_directory_link)).setContentText(getString(R$string.notification_no_directory_link_description)).setPriority(1).setAutoCancel(true);
        Intrinsics.checkNotNullExpressionValue(autoCancel, "setAutoCancel(...)");
        NotificationManagerCompat from = NotificationManagerCompat.from(requireContext());
        if (ContextCompat.checkSelfPermission(requireContext(), "android.permission.POST_NOTIFICATIONS") != 0) {
            Toast.makeText(requireContext(), getResources().getString(R$string.notification_permission_not_granted), 1).show();
        } else {
            from.notify(0, autoCancel.build());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setReenterTransition(new MaterialSharedAxis(0, false));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentHomeSettingsBinding.inflate(getLayoutInflater());
        NestedScrollView root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getDriverViewModel().updateDriverNameForGame(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setExitTransition(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getHomeViewModel().setNavigationVisibility(true, true);
        getHomeViewModel().setStatusBarShadeVisibility(true);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.yuzu.yuzu_emu.ui.main.MainActivity");
        this.mainActivity = (MainActivity) requireActivity;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HomeSetting(R$string.advanced_settings, R$string.settings_description, R$drawable.ic_settings, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m158invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m158invoke() {
                FragmentHomeSettingsBinding binding;
                NavDirections actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_ROOT);
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionGlobalSettingsActivity);
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.preferences_controls, R$string.preferences_controls_description, R$drawable.ic_controller, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m163invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m163invoke() {
                FragmentHomeSettingsBinding binding;
                NavDirections actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_INPUT);
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionGlobalSettingsActivity);
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.gpu_driver_manager, R$string.install_gpu_driver_description, R$drawable.ic_build, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m164invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m164invoke() {
                FragmentHomeSettingsBinding binding;
                NavDirections actionHomeSettingsFragmentToDriverManagerFragment = HomeSettingsFragmentDirections.Companion.actionHomeSettingsFragmentToDriverManagerFragment(null);
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionHomeSettingsFragmentToDriverManagerFragment);
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$4
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(GpuDriverHelper.INSTANCE.supportsCustomDriverLoading());
            }
        }, R$string.custom_driver_not_supported, R$string.custom_driver_not_supported_description, getDriverViewModel().getSelectedDriverTitle()));
        arrayList.add(new HomeSetting(R$string.applets, R$string.applets_description, R$drawable.ic_applet, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m165invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m165invoke() {
                FragmentHomeSettingsBinding binding;
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(R$id.action_homeSettingsFragment_to_appletLauncherFragment);
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$6
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(NativeLibrary.INSTANCE.isFirmwareAvailable());
            }
        }, R$string.applets_error_firmware, R$string.applets_error_description, null, 128, null));
        arrayList.add(new HomeSetting(R$string.manage_yuzu_data, R$string.manage_yuzu_data_description, R$drawable.ic_install, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m166invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m166invoke() {
                FragmentHomeSettingsBinding binding;
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(R$id.action_homeSettingsFragment_to_installableFragment);
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.manage_game_folders, R$string.select_games_folder_description, R$drawable.ic_add, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m167invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m167invoke() {
                FragmentHomeSettingsBinding binding;
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(R$id.action_homeSettingsFragment_to_gameFoldersFragment);
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.verify_installed_content, R$string.verify_installed_content_description, R$drawable.ic_check_circle, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$9

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$9$1, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function3 {
                /* synthetic */ Object L$0;
                int label;

                AnonymousClass1(Continuation continuation) {
                    super(3, continuation);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
                    anonymousClass1.L$0 = function2;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    FragmentActivity fragmentActivity;
                    String joinToString$default;
                    String str;
                    MessageDialogFragment.Companion companion;
                    int i;
                    String str2;
                    int i2;
                    int i3;
                    boolean z;
                    int i4;
                    String str3;
                    Function0 function0;
                    boolean z2;
                    int i5;
                    String str4;
                    Function0 function02;
                    int i6;
                    MessageDialogFragment newInstance;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    Function2 function2 = (Function2) this.L$0;
                    String[] verifyInstalledContents = NativeLibrary.INSTANCE.verifyInstalledContents(function2);
                    if (((Boolean) function2.invoke(Boxing.boxLong(100L), Boxing.boxLong(100L))).booleanValue()) {
                        companion = MessageDialogFragment.Companion;
                        fragmentActivity = null;
                        i = R$string.verify_no_result;
                        str2 = null;
                        i2 = R$string.verify_no_result_description;
                    } else {
                        if (!(verifyInstalledContents.length == 0)) {
                            fragmentActivity = null;
                            joinToString$default = ArraysKt___ArraysKt.joinToString$default(verifyInstalledContents, "\n", null, null, 0, null, null, 62, null);
                            String string = YuzuApplication.Companion.getAppContext().getString(R$string.verification_failed_for, joinToString$default);
                            str = string;
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            companion = MessageDialogFragment.Companion;
                            i = R$string.verify_failure;
                            str2 = null;
                            i2 = 0;
                            i3 = 0;
                            z = false;
                            i4 = 0;
                            str3 = null;
                            function0 = null;
                            z2 = false;
                            i5 = 0;
                            str4 = null;
                            function02 = null;
                            i6 = 16365;
                            newInstance = companion.newInstance((r31 & 1) != 0 ? null : fragmentActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : str2, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : str, (r31 & 32) != 0 ? 0 : i3, (r31 & 64) != 0 ? true : z, (r31 & 128) != 0 ? 0 : i4, (r31 & 256) != 0 ? "" : str3, (r31 & 512) != 0 ? null : function0, (r31 & 1024) != 0 ? false : z2, (r31 & 2048) == 0 ? i5 : 0, (r31 & 4096) == 0 ? str4 : "", (r31 & 8192) == 0 ? function02 : null);
                            return newInstance;
                        }
                        companion = MessageDialogFragment.Companion;
                        fragmentActivity = null;
                        i = R$string.verify_success;
                        str2 = null;
                        i2 = R$string.operation_completed_successfully;
                    }
                    str = null;
                    i3 = 0;
                    z = false;
                    i4 = 0;
                    str3 = null;
                    function0 = null;
                    z2 = false;
                    i5 = 0;
                    str4 = null;
                    function02 = null;
                    i6 = 16373;
                    newInstance = companion.newInstance((r31 & 1) != 0 ? null : fragmentActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : str2, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : str, (r31 & 32) != 0 ? 0 : i3, (r31 & 64) != 0 ? true : z, (r31 & 128) != 0 ? 0 : i4, (r31 & 256) != 0 ? "" : str3, (r31 & 512) != 0 ? null : function0, (r31 & 1024) != 0 ? false : z2, (r31 & 2048) == 0 ? i5 : 0, (r31 & 4096) == 0 ? str4 : "", (r31 & 8192) == 0 ? function02 : null);
                    return newInstance;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m168invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m168invoke() {
                ProgressDialogFragment.Companion companion = ProgressDialogFragment.Companion;
                FragmentActivity requireActivity2 = HomeSettingsFragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                companion.newInstance(requireActivity2, R$string.verifying, true, new AnonymousClass1(null)).show(HomeSettingsFragment.this.getParentFragmentManager(), "IndeterminateProgressDialogFragment");
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.share_log, R$string.share_log_description, R$drawable.ic_log, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m159invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m159invoke() {
                HomeSettingsFragment.this.shareLog();
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.open_user_folder, R$string.open_user_folder_description, R$drawable.ic_folder_open, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m160invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m160invoke() {
                HomeSettingsFragment.this.openFileManager();
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.preferences_theme, R$string.theme_and_color_description, R$drawable.ic_palette, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m161invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m161invoke() {
                FragmentHomeSettingsBinding binding;
                NavDirections actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_THEME);
                binding = HomeSettingsFragment.this.getBinding();
                NestedScrollView root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(actionGlobalSettingsActivity);
            }
        }, null, 0, 0, null, 240, null));
        arrayList.add(new HomeSetting(R$string.about, R$string.about_description, R$drawable.ic_info_outline, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$optionsList$1$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m162invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m162invoke() {
                NavController findNavController;
                HomeSettingsFragment.this.setExitTransition(new MaterialSharedAxis(0, true));
                Fragment primaryNavigationFragment = HomeSettingsFragment.this.getParentFragmentManager().getPrimaryNavigationFragment();
                if (primaryNavigationFragment == null || (findNavController = FragmentKt.findNavController(primaryNavigationFragment)) == null) {
                    return;
                }
                findNavController.navigate(R$id.action_homeSettingsFragment_to_aboutFragment);
            }
        }, null, 0, 0, null, 240, null));
        if (!BuildConfig.PREMIUM.booleanValue()) {
            arrayList.add(0, new HomeSetting(R$string.get_early_access, R$string.get_early_access_description, R$drawable.ic_diamond, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.HomeSettingsFragment$onViewCreated$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m157invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m157invoke() {
                    NavController findNavController;
                    HomeSettingsFragment.this.setExitTransition(new MaterialSharedAxis(0, true));
                    Fragment primaryNavigationFragment = HomeSettingsFragment.this.getParentFragmentManager().getPrimaryNavigationFragment();
                    if (primaryNavigationFragment == null || (findNavController = FragmentKt.findNavController(primaryNavigationFragment)) == null) {
                        return;
                    }
                    findNavController.navigate(R$id.action_homeSettingsFragment_to_earlyAccessFragment);
                }
            }, null, 0, 0, null, 240, null));
        }
        RecyclerView recyclerView = getBinding().homeSettingsList;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), recyclerView.getResources().getInteger(R$integer.grid_columns)));
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        recyclerView.setAdapter(new HomeSettingAdapter((AppCompatActivity) requireActivity2, viewLifecycleOwner, arrayList));
        setInsets();
    }
}
