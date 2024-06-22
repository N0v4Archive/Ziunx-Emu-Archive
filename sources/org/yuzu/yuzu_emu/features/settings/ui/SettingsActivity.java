package org.yuzu.yuzu_emu.features.settings.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
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
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.R$attr;
import com.google.android.material.color.MaterialColors;
import java.io.File;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$navigation;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.ActivitySettingsBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.settings.utils.SettingsFile;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.InsetsHelper;
import org.yuzu.yuzu_emu.utils.Log;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ThemeHelper;

/* loaded from: classes.dex */
public final class SettingsActivity extends AppCompatActivity {
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SettingsActivityArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$special$$inlined$navArgs$1
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Bundle invoke() {
            Bundle bundle;
            Intent intent = this.getIntent();
            if (intent != null) {
                Activity activity = this;
                bundle = intent.getExtras();
                if (bundle == null) {
                    throw new IllegalStateException("Activity " + activity + " has null extras in " + intent);
                }
            } else {
                bundle = null;
            }
            if (bundle != null) {
                return bundle;
            }
            throw new IllegalStateException("Activity " + this + " has a null Intent");
        }
    });
    private ActivitySettingsBinding binding;
    private final Lazy settingsViewModel$delegate;

    public SettingsActivity() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$special$$inlined$viewModels$default$3
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
    }

    private final SettingsActivityArgs getArgs() {
        return (SettingsActivityArgs) this.args$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    private final void setInsets() {
        ActivitySettingsBinding activitySettingsBinding = this.binding;
        if (activitySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySettingsBinding = null;
        }
        ViewCompat.setOnApplyWindowInsetsListener(activitySettingsBinding.navigationBarShade, new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$6;
                insets$lambda$6 = SettingsActivity.setInsets$lambda$6(SettingsActivity.this, view, windowInsetsCompat);
                return insets$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$6(SettingsActivity this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        ActivitySettingsBinding activitySettingsBinding = this$0.binding;
        ActivitySettingsBinding activitySettingsBinding2 = null;
        if (activitySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySettingsBinding = null;
        }
        ViewGroup.LayoutParams layoutParams = activitySettingsBinding.navigationBarShade.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.height = insets.bottom;
        ActivitySettingsBinding activitySettingsBinding3 = this$0.binding;
        if (activitySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySettingsBinding2 = activitySettingsBinding3;
        }
        activitySettingsBinding2.navigationBarShade.setLayoutParams(marginLayoutParams);
        return windowInsets;
    }

    public final void navigateBack() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R$id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
        if (navHostFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            navHostFragment.getNavController().popBackStack();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ThemeHelper themeHelper = ThemeHelper.INSTANCE;
        themeHelper.setTheme(this);
        super.onCreate(bundle);
        ActivitySettingsBinding inflate = ActivitySettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        ActivitySettingsBinding activitySettingsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (!NativeConfig.INSTANCE.isPerGameConfigLoaded() && getArgs().getGame() != null) {
            SettingsFile settingsFile = SettingsFile.INSTANCE;
            Game game = getArgs().getGame();
            Intrinsics.checkNotNull(game);
            settingsFile.loadCustomConfig(game);
        }
        getSettingsViewModel().setGame(getArgs().getGame());
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R$id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        ((NavHostFragment) findFragmentById).getNavController().setGraph(R$navigation.settings_navigation, getIntent().getExtras());
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        InsetsHelper insetsHelper = InsetsHelper.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        if (insetsHelper.getSystemGestureType(applicationContext) != 2) {
            ActivitySettingsBinding activitySettingsBinding2 = this.binding;
            if (activitySettingsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySettingsBinding2 = null;
            }
            View view = activitySettingsBinding2.navigationBarShade;
            ActivitySettingsBinding activitySettingsBinding3 = this.binding;
            if (activitySettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySettingsBinding = activitySettingsBinding3;
            }
            view.setBackgroundColor(themeHelper.getColorWithOpacity(MaterialColors.getColor(activitySettingsBinding.navigationBarShade, R$attr.colorSurface), 0.9f));
        }
        StateFlow shouldRecreate = getSettingsViewModel().getShouldRecreate();
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$$inlined$collect$default$1(this, state, shouldRecreate, null, this, this), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$$inlined$collect$default$2(this, state, getSettingsViewModel().getShouldNavigateBack(), null, this, this), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$$inlined$collect$default$3(this, state, getSettingsViewModel().getShouldShowResetSettingsDialog(), null, this, this), 3, null);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsActivity$onCreate$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                SettingsActivity.this.navigateBack();
            }
        });
        setInsets();
    }

    public final void onSettingsReset() {
        if (getArgs().getGame() == null) {
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            nativeConfig.unloadGlobalConfig();
            File settingsFile = SettingsFile.INSTANCE.getSettingsFile("config.ini");
            if (!settingsFile.delete()) {
                throw new IOException("Failed to delete " + settingsFile);
            }
            nativeConfig.initializeGlobalConfig();
        } else {
            NativeConfig.INSTANCE.unloadPerGameConfig();
            SettingsFile settingsFile2 = SettingsFile.INSTANCE;
            Game game = getArgs().getGame();
            Intrinsics.checkNotNull(game);
            File customSettingsFile = settingsFile2.getCustomSettingsFile(game);
            if (!customSettingsFile.delete()) {
                throw new IOException("Failed to delete " + customSettingsFile);
            }
        }
        Toast.makeText(getApplicationContext(), getString(R$string.settings_reset), 1).show();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
        if (directoryInitialization.getAreDirectoriesReady()) {
            return;
        }
        directoryInitialization.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Log.INSTANCE.info("[SettingsActivity] Settings activity stopping. Saving settings to INI...");
        if (isFinishing()) {
            NativeInput.INSTANCE.reloadInputDevices();
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            nativeLibrary.applySettings();
            if (getArgs().getGame() == null) {
                NativeConfig.INSTANCE.saveGlobalConfig();
                return;
            }
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            if (nativeConfig.isPerGameConfigLoaded()) {
                nativeLibrary.logSettings();
                nativeConfig.savePerGameConfig();
                nativeConfig.unloadPerGameConfig();
            }
        }
    }
}
