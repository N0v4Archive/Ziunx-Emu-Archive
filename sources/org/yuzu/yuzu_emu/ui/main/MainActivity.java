package org.yuzu.yuzu_emu.ui.main;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.PathInterpolator;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$CreateDocument;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocument;
import androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.BottomNavigationViewKt;
import androidx.preference.PreferenceManager;
import com.google.android.material.R$attr;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.navigation.NavigationBarView;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$bool;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.ActivityMainBinding;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.fragments.AddGameFolderDialogFragment;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.fragments.ProgressDialogFragment;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.GameDir;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.TaskViewModel;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.InsetsHelper;
import org.yuzu.yuzu_emu.utils.ThemeHelper;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class MainActivity extends AppCompatActivity implements ThemeProvider {
    private final String CHECKED_DECRYPTION = "CheckedDecryption";
    private final Lazy addonViewModel$delegate;
    private ActivityMainBinding binding;
    private boolean checkedDecryption;
    private final Lazy driverViewModel$delegate;
    private final ActivityResultLauncher exportUserData;
    private final Lazy gamesViewModel$delegate;
    private final ActivityResultLauncher getAmiiboKey;
    private final ActivityResultLauncher getFirmware;
    private final ActivityResultLauncher getGamesDirectory;
    private final ActivityResultLauncher getProdKey;
    private final Lazy homeViewModel$delegate;
    private final ActivityResultLauncher importUserData;
    private final ActivityResultLauncher installGameUpdate;
    private final Lazy taskViewModel$delegate;
    private int themeId;

    public MainActivity() {
        final Function0 function0 = null;
        this.homeViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$3
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        this.gamesViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$6
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        this.taskViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TaskViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$8
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$7
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$9
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        this.addonViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AddonViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$11
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$10
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$12
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        this.driverViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$14
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$13
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$special$$inlined$viewModels$default$15
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts$OpenDocumentTree(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.getGamesDirectory$lambda$13(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.getGamesDirectory = registerForActivityResult;
        ActivityResultLauncher registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.getProdKey$lambda$15(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.getProdKey = registerForActivityResult2;
        ActivityResultLauncher registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.getFirmware$lambda$17(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResult(...)");
        this.getFirmware = registerForActivityResult3;
        ActivityResultLauncher registerForActivityResult4 = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.getAmiiboKey$lambda$18(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult4, "registerForActivityResult(...)");
        this.getAmiiboKey = registerForActivityResult4;
        ActivityResultLauncher registerForActivityResult5 = registerForActivityResult(new ActivityResultContract() { // from class: androidx.activity.result.contract.ActivityResultContracts$OpenMultipleDocuments
            @Override // androidx.activity.result.contract.ActivityResultContract
            public Intent createIntent(Context context, String[] input) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(input, "input");
                Intent type = new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", input).putExtra("android.intent.extra.ALLOW_MULTIPLE", true).setType("*/*");
                Intrinsics.checkNotNullExpressionValue(type, "Intent(Intent.ACTION_OPE…          .setType(\"*/*\")");
                return type;
            }

            @Override // androidx.activity.result.contract.ActivityResultContract
            public final ActivityResultContract.SynchronousResult getSynchronousResult(Context context, String[] input) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(input, "input");
                return null;
            }

            @Override // androidx.activity.result.contract.ActivityResultContract
            public final List parseResult(int i, Intent intent) {
                List emptyList;
                List clipDataUris$activity_release;
                if (!(i == -1)) {
                    intent = null;
                }
                if (intent != null && (clipDataUris$activity_release = ActivityResultContracts$GetMultipleContents.Companion.getClipDataUris$activity_release(intent)) != null) {
                    return clipDataUris$activity_release;
                }
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList;
            }
        }, new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.installGameUpdate$lambda$19(MainActivity.this, (List) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult5, "registerForActivityResult(...)");
        this.installGameUpdate = registerForActivityResult5;
        ActivityResultLauncher registerForActivityResult6 = registerForActivityResult(new ActivityResultContracts$CreateDocument("application/zip"), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.exportUserData$lambda$20(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult6, "registerForActivityResult(...)");
        this.exportUserData = registerForActivityResult6;
        ActivityResultLauncher registerForActivityResult7 = registerForActivityResult(new ActivityResultContracts$OpenDocument(), new ActivityResultCallback() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda8
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.importUserData$lambda$21(MainActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult7, "registerForActivityResult(...)");
        this.importUserData = registerForActivityResult7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkKeys() {
        MessageDialogFragment newInstance;
        if (NativeLibrary.INSTANCE.areKeysPresent()) {
            return;
        }
        newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : null, (r31 & 2) != 0 ? 0 : R$string.keys_missing, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.keys_missing_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : R$string.keys_missing_help, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
        newInstance.show(getSupportFragmentManager(), "MessageDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportUserData$lambda$20(MainActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion.newInstance(this$0, R$string.exporting_user_data, true, new MainActivity$exportUserData$1$1(this$0, uri, null)).show(this$0.getSupportFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonViewModel getAddonViewModel() {
        return (AddonViewModel) this.addonViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAmiiboKey$lambda$18(MainActivity this$0, Uri uri) {
        MessageDialogFragment newInstance;
        MessageDialogFragment newInstance2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        FileUtil fileUtil = FileUtil.INSTANCE;
        if (!Intrinsics.areEqual(fileUtil.getExtension(uri), "bin")) {
            newInstance2 = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this$0, (r31 & 2) != 0 ? 0 : R$string.reading_keys_failure, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.install_amiibo_keys_failure_extension_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
            newInstance2.show(this$0.getSupportFragmentManager(), "MessageDialogFragment");
            return;
        }
        this$0.getContentResolver().takePersistableUriPermission(uri, 1);
        if (fileUtil.copyUriToInternalStorage(uri, DirectoryInitialization.INSTANCE.getUserDirectory() + "/keys/", "key_retail.bin") != null) {
            if (NativeLibrary.INSTANCE.reloadKeys()) {
                Toast.makeText(this$0.getApplicationContext(), R$string.install_keys_success, 0).show();
            } else {
                newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this$0, (r31 & 2) != 0 ? 0 : R$string.invalid_keys_error, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.install_keys_failure_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : R$string.dumping_keys_quickstart_link, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                newInstance.show(this$0.getSupportFragmentManager(), "MessageDialogFragment");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getFirmware$lambda$17(MainActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion.newInstance$default(ProgressDialogFragment.Companion, this$0, R$string.firmware_installing, false, new MainActivity$getFirmware$1$1(uri, new File(this$0.getCacheDir().getPath() + "/registered/"), new FilenameFilter() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda11
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                boolean firmware$lambda$17$lambda$16;
                firmware$lambda$17$lambda$16 = MainActivity.getFirmware$lambda$17$lambda$16(file, str);
                return firmware$lambda$17$lambda$16;
            }
        }, this$0, new File(DirectoryInitialization.INSTANCE.getUserDirectory() + "/nand/system/Contents/registered/"), null), 4, null).show(this$0.getSupportFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getFirmware$lambda$17$lambda$16(File file, String str) {
        boolean endsWith$default;
        Intrinsics.checkNotNull(str);
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str, ".nca", false, 2, null);
        return endsWith$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getGamesDirectory$lambda$13(MainActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            this$0.processGamesDir(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getProdKey$lambda$15(MainActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            this$0.processKey(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void importUserData$lambda$21(MainActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        ProgressDialogFragment.Companion.newInstance$default(ProgressDialogFragment.Companion, this$0, R$string.importing_user_data, false, new MainActivity$importUserData$1$1(this$0, uri, null), 4, null).show(this$0.getSupportFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void installContent(List list) {
        ProgressDialogFragment.Companion.newInstance$default(ProgressDialogFragment.Companion, this, R$string.installing_game_content, false, new MainActivity$installContent$1(list, this, null), 4, null).show(getSupportFragmentManager(), "IndeterminateProgressDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installGameUpdate$lambda$19(MainActivity this$0, List documents) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documents, "documents");
        if (documents.isEmpty()) {
            return;
        }
        if (this$0.getAddonViewModel().getGame() == null) {
            this$0.installContent(documents);
        } else {
            ProgressDialogFragment.Companion.newInstance(this$0, R$string.verifying_content, false, new MainActivity$installGameUpdate$1$1(documents, this$0, null)).show(this$0.getSupportFragmentManager(), "IndeterminateProgressDialogFragment");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0() {
        return !DirectoryInitialization.INSTANCE.getAreDirectoriesReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(MainActivity this$0, NavHostFragment navHostFragment, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(navHostFragment, "$navHostFragment");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        if (itemId == R$id.gamesFragment) {
            this$0.getGamesViewModel().setShouldScrollToTop(true);
            return;
        }
        if (itemId == R$id.searchFragment) {
            this$0.getGamesViewModel().setSearchFocused(true);
        } else if (itemId == R$id.homeSettingsFragment) {
            navHostFragment.getNavController().navigate(HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_ROOT));
        }
    }

    private final void setInsets() {
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        ViewCompat.setOnApplyWindowInsetsListener(activityMainBinding.getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda10
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$12;
                insets$lambda$12 = MainActivity.setInsets$lambda$12(MainActivity.this, view, windowInsetsCompat);
                return insets$lambda$12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$12(MainActivity this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        ActivityMainBinding activityMainBinding = this$0.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        ViewGroup.LayoutParams layoutParams = activityMainBinding.statusBarShade.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.height = insets.top;
        ActivityMainBinding activityMainBinding3 = this$0.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        activityMainBinding3.statusBarShade.setLayoutParams(marginLayoutParams);
        ActivityMainBinding activityMainBinding4 = this$0.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = activityMainBinding4.navigationBarShade.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
        marginLayoutParams2.height = insets.bottom;
        ActivityMainBinding activityMainBinding5 = this$0.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding5;
        }
        activityMainBinding2.navigationBarShade.setLayoutParams(marginLayoutParams2);
        return windowInsets;
    }

    private final void setUpNavigation(NavController navController) {
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("FirstApplicationLaunch", true) && !getHomeViewModel().getNavigatedToSetup()) {
            navController.navigate(R$id.firstTimeSetupFragment);
            getHomeViewModel().setNavigatedToSetup(true);
            return;
        }
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        View view = activityMainBinding.navigationView;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.google.android.material.navigation.NavigationBarView");
        BottomNavigationViewKt.setupWithNavController((NavigationBarView) view, navController);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNavigation(final boolean z, boolean z2) {
        float width;
        float f;
        View view;
        float width2;
        float f2;
        ActivityMainBinding activityMainBinding = null;
        if (!z2) {
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            ActivityMainBinding activityMainBinding2 = this.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding2;
            }
            View navigationView = activityMainBinding.navigationView;
            Intrinsics.checkNotNullExpressionValue(navigationView, "navigationView");
            ViewUtils.setVisible$default(viewUtils, navigationView, z, false, 2, null);
            return;
        }
        boolean z3 = getResources().getBoolean(R$bool.small_layout);
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        ViewPropertyAnimator animate = activityMainBinding3.navigationView.animate();
        if (z) {
            ViewUtils viewUtils2 = ViewUtils.INSTANCE;
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            View navigationView2 = activityMainBinding4.navigationView;
            Intrinsics.checkNotNullExpressionValue(navigationView2, "navigationView");
            ViewUtils.setVisible$default(viewUtils2, navigationView2, true, false, 2, null);
            animate.setDuration(300L);
            animate.setInterpolator(new PathInterpolator(0.05f, 0.7f, 0.1f, 1.0f));
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (z3) {
                if (activityMainBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding5 = null;
                }
                View view2 = activityMainBinding5.navigationView;
                ActivityMainBinding activityMainBinding6 = this.binding;
                if (activityMainBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding6;
                }
                view2.setTranslationY(activityMainBinding.navigationView.getHeight() * 2);
                animate.translationY(0.0f);
            } else {
                if (activityMainBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding5 = null;
                }
                if (ViewCompat.getLayoutDirection(activityMainBinding5.navigationView) == 0) {
                    ActivityMainBinding activityMainBinding7 = this.binding;
                    if (activityMainBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding7 = null;
                    }
                    view = activityMainBinding7.navigationView;
                    ActivityMainBinding activityMainBinding8 = this.binding;
                    if (activityMainBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding = activityMainBinding8;
                    }
                    width2 = activityMainBinding.navigationView.getWidth();
                    f2 = -2;
                } else {
                    ActivityMainBinding activityMainBinding9 = this.binding;
                    if (activityMainBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding9 = null;
                    }
                    view = activityMainBinding9.navigationView;
                    ActivityMainBinding activityMainBinding10 = this.binding;
                    if (activityMainBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding = activityMainBinding10;
                    }
                    width2 = activityMainBinding.navigationView.getWidth();
                    f2 = 2;
                }
                view.setTranslationX(width2 * f2);
                animate.translationX(0.0f);
            }
        } else {
            animate.setDuration(300L);
            animate.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.8f, 0.15f));
            ActivityMainBinding activityMainBinding11 = this.binding;
            if (z3) {
                if (activityMainBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding11;
                }
                animate.translationY(activityMainBinding.navigationView.getHeight() * 2);
            } else {
                if (activityMainBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding11 = null;
                }
                if (ViewCompat.getLayoutDirection(activityMainBinding11.navigationView) == 0) {
                    ActivityMainBinding activityMainBinding12 = this.binding;
                    if (activityMainBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding = activityMainBinding12;
                    }
                    width = activityMainBinding.navigationView.getWidth();
                    f = -2;
                } else {
                    ActivityMainBinding activityMainBinding13 = this.binding;
                    if (activityMainBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding = activityMainBinding13;
                    }
                    width = activityMainBinding.navigationView.getWidth();
                    f = 2;
                }
                animate.translationX(width * f);
            }
        }
        animate.withEndAction(new Runnable() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.showNavigation$lambda$9(z, this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showNavigation$lambda$9(boolean z, MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            return;
        }
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        ActivityMainBinding activityMainBinding = this$0.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        View navigationView = activityMainBinding.navigationView;
        Intrinsics.checkNotNullExpressionValue(navigationView, "navigationView");
        viewUtils.setVisible(navigationView, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showStatusBarShade(final boolean z) {
        PathInterpolator pathInterpolator;
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        ViewPropertyAnimator animate = activityMainBinding.statusBarShade.animate();
        if (z) {
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding3 = null;
            }
            View statusBarShade = activityMainBinding3.statusBarShade;
            Intrinsics.checkNotNullExpressionValue(statusBarShade, "statusBarShade");
            ViewUtils.setVisible$default(viewUtils, statusBarShade, true, false, 2, null);
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            View view = activityMainBinding4.statusBarShade;
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding5;
            }
            view.setTranslationY(activityMainBinding2.statusBarShade.getHeight() * (-2));
            animate.setDuration(300L);
            animate.translationY(0.0f);
            pathInterpolator = new PathInterpolator(0.05f, 0.7f, 0.1f, 1.0f);
        } else {
            animate.setDuration(300L);
            ActivityMainBinding activityMainBinding6 = this.binding;
            if (activityMainBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding6;
            }
            animate.translationY(activityMainBinding2.navigationView.getHeight() * (-2));
            pathInterpolator = new PathInterpolator(0.3f, 0.0f, 0.8f, 0.15f);
        }
        animate.setInterpolator(pathInterpolator);
        animate.withEndAction(new Runnable() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.showStatusBarShade$lambda$11(z, this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showStatusBarShade$lambda$11(boolean z, MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            return;
        }
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        ActivityMainBinding activityMainBinding = this$0.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        View statusBarShade = activityMainBinding.statusBarShade;
        Intrinsics.checkNotNullExpressionValue(statusBarShade, "statusBarShade");
        viewUtils.setVisible(statusBarShade, false, false);
    }

    public final void finishSetup(NavController navController) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        navController.navigate(R$id.action_firstTimeSetupFragment_to_gamesFragment);
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        View view = activityMainBinding.navigationView;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.google.android.material.navigation.NavigationBarView");
        BottomNavigationViewKt.setupWithNavController((NavigationBarView) view, navController);
        showNavigation(true, true);
    }

    public final ActivityResultLauncher getExportUserData() {
        return this.exportUserData;
    }

    public final ActivityResultLauncher getGetAmiiboKey() {
        return this.getAmiiboKey;
    }

    public final ActivityResultLauncher getGetFirmware() {
        return this.getFirmware;
    }

    public final ActivityResultLauncher getGetGamesDirectory() {
        return this.getGamesDirectory;
    }

    public final ActivityResultLauncher getGetProdKey() {
        return this.getProdKey;
    }

    public final ActivityResultLauncher getImportUserData() {
        return this.importUserData;
    }

    public final ActivityResultLauncher getInstallGameUpdate() {
        return this.installGameUpdate;
    }

    @Override // org.yuzu.yuzu_emu.ui.main.ThemeProvider
    public int getThemeId() {
        return this.themeId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        SplashScreen.Companion.installSplashScreen(this).setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.splashscreen.SplashScreen.KeepOnScreenCondition
            public final boolean shouldKeepOnScreen() {
                boolean onCreate$lambda$0;
                onCreate$lambda$0 = MainActivity.onCreate$lambda$0();
                return onCreate$lambda$0;
            }
        });
        ThemeHelper themeHelper = ThemeHelper.INSTANCE;
        themeHelper.setTheme(this);
        super.onCreate(bundle);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        ActivityMainBinding activityMainBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (bundle != null) {
            this.checkedDecryption = bundle.getBoolean(this.CHECKED_DECRYPTION);
        }
        if (!this.checkedDecryption) {
            if (!PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("FirstApplicationLaunch", true)) {
                checkKeys();
            }
            this.checkedDecryption = true;
        }
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setSoftInputMode(48);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
        getWindow().setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding2 = null;
        }
        View view = activityMainBinding2.statusBarShade;
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        view.setBackgroundColor(themeHelper.getColorWithOpacity(MaterialColors.getColor(activityMainBinding3.getRoot(), R$attr.colorSurface), 0.9f));
        InsetsHelper insetsHelper = InsetsHelper.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        if (insetsHelper.getSystemGestureType(applicationContext) != 2) {
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            View view2 = activityMainBinding4.navigationBarShade;
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding5 = null;
            }
            view2.setBackgroundColor(themeHelper.getColorWithOpacity(MaterialColors.getColor(activityMainBinding5.getRoot(), R$attr.colorSurface), 0.9f));
        }
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R$id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        final NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
        setUpNavigation(navHostFragment.getNavController());
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding6 = null;
        }
        View view3 = activityMainBinding6.navigationView;
        Intrinsics.checkNotNull(view3, "null cannot be cast to non-null type com.google.android.material.navigation.NavigationBarView");
        ((NavigationBarView) view3).setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemReselectedListener
            public final void onNavigationItemReselected(MenuItem menuItem) {
                MainActivity.onCreate$lambda$1(MainActivity.this, navHostFragment, menuItem);
            }
        });
        if (!((Boolean) ((Pair) getHomeViewModel().getNavigationVisible().getValue()).getFirst()).booleanValue()) {
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            ActivityMainBinding activityMainBinding7 = this.binding;
            if (activityMainBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding7 = null;
            }
            View navigationView = activityMainBinding7.navigationView;
            Intrinsics.checkNotNullExpressionValue(navigationView, "navigationView");
            viewUtils.setVisible(navigationView, false, false);
            ActivityMainBinding activityMainBinding8 = this.binding;
            if (activityMainBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding8;
            }
            View statusBarShade = activityMainBinding.statusBarShade;
            Intrinsics.checkNotNullExpressionValue(statusBarShade, "statusBarShade");
            viewUtils.setVisible(statusBarShade, false, false);
        }
        StateFlow navigationVisible = getHomeViewModel().getNavigationVisible();
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MainActivity$onCreate$$inlined$collect$default$1(this, state, navigationVisible, null, this), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MainActivity$onCreate$$inlined$collect$default$2(this, state, getHomeViewModel().getStatusBarShadeVisible(), null, this), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MainActivity$onCreate$$inlined$collect$default$3(this, state, getHomeViewModel().getContentToInstall(), null, this, this), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MainActivity$onCreate$$inlined$collect$default$4(this, state, getHomeViewModel().getCheckKeys(), null, this, this), 3, null);
        setInsets();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ThemeHelper.INSTANCE.setCorrectTheme(this);
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(this.CHECKED_DECRYPTION, this.checkedDecryption);
    }

    public final void processGamesDir(Uri result) {
        Object obj;
        Intrinsics.checkNotNullParameter(result, "result");
        getContentResolver().takePersistableUriPermission(result, 1);
        String uri = result.toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        Iterator it = ((Iterable) getGamesViewModel().getFolders().getValue()).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((GameDir) obj).getUriString(), uri)) {
                    break;
                }
            }
        }
        if (((GameDir) obj) != null) {
            Toast.makeText(getApplicationContext(), R$string.folder_already_added, 0).show();
        } else {
            AddGameFolderDialogFragment.Companion.newInstance(uri).show(getSupportFragmentManager(), "AddGameFolderDialogFragment");
        }
    }

    public final boolean processKey(Uri result) {
        MessageDialogFragment newInstance;
        Intrinsics.checkNotNullParameter(result, "result");
        FileUtil fileUtil = FileUtil.INSTANCE;
        if (Intrinsics.areEqual(fileUtil.getExtension(result), "keys")) {
            getContentResolver().takePersistableUriPermission(result, 1);
            if (fileUtil.copyUriToInternalStorage(result, DirectoryInitialization.INSTANCE.getUserDirectory() + "/keys/", "prod.keys") == null) {
                return false;
            }
            if (NativeLibrary.INSTANCE.reloadKeys()) {
                Toast.makeText(getApplicationContext(), R$string.install_keys_success, 0).show();
                getHomeViewModel().setCheckKeys(true);
                GamesViewModel.reloadGames$default(getGamesViewModel(), true, false, 2, null);
                return true;
            }
            newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this, (r31 & 2) != 0 ? 0 : R$string.invalid_keys_error, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.install_keys_failure_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : R$string.dumping_keys_quickstart_link, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
        } else {
            newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this, (r31 & 2) != 0 ? 0 : R$string.reading_keys_failure, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.install_prod_keys_failure_extension_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
        }
        newInstance.show(getSupportFragmentManager(), "MessageDialogFragment");
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        setThemeId(i);
    }

    public void setThemeId(int i) {
        this.themeId = i;
    }
}
