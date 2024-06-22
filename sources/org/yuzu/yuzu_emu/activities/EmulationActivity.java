package org.yuzu.yuzu_emu.activities;

import android.R;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$navigation;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.databinding.ActivityEmulationBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.PlayerInput;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.model.EmulationViewModel;
import org.yuzu.yuzu_emu.utils.InputHandler;
import org.yuzu.yuzu_emu.utils.Log;
import org.yuzu.yuzu_emu.utils.MemoryUtil;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.NfcReader;
import org.yuzu.yuzu_emu.utils.ParamPackage;
import org.yuzu.yuzu_emu.utils.ThemeHelper;

/* loaded from: classes.dex */
public final class EmulationActivity extends AppCompatActivity implements SensorEventListener {
    public static final Companion Companion = new Companion(null);
    private ActivityEmulationBinding binding;
    private final Lazy emulationViewModel$delegate;
    private boolean flipMotionOrientation;
    private boolean isActivityRecreated;
    private long motionTimestamp;
    private NfcReader nfcReader;
    private final float[] gyro = new float[3];
    private final float[] accel = new float[3];
    private final String actionPause = "ACTION_EMULATOR_PAUSE";
    private final String actionPlay = "ACTION_EMULATOR_PLAY";
    private final String actionMute = "ACTION_EMULATOR_MUTE";
    private final String actionUnmute = "ACTION_EMULATOR_UNMUTE";
    private EmulationActivity$pictureInPictureReceiver$1 pictureInPictureReceiver = new BroadcastReceiver() { // from class: org.yuzu.yuzu_emu.activities.EmulationActivity$pictureInPictureReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            String str2;
            String str3;
            String str4;
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            str = EmulationActivity.this.actionPlay;
            if (Intrinsics.areEqual(action, str)) {
                NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
                if (nativeLibrary.isPaused()) {
                    nativeLibrary.unpauseEmulation();
                }
            } else {
                String action2 = intent.getAction();
                str2 = EmulationActivity.this.actionPause;
                if (Intrinsics.areEqual(action2, str2)) {
                    NativeLibrary nativeLibrary2 = NativeLibrary.INSTANCE;
                    if (!nativeLibrary2.isPaused()) {
                        nativeLibrary2.pauseEmulation();
                    }
                }
            }
            String action3 = intent.getAction();
            str3 = EmulationActivity.this.actionUnmute;
            if (Intrinsics.areEqual(action3, str3)) {
                BooleanSetting booleanSetting = BooleanSetting.AUDIO_MUTED;
                if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(booleanSetting, false, 1, null)) {
                    booleanSetting.setBoolean(false);
                }
            } else {
                String action4 = intent.getAction();
                str4 = EmulationActivity.this.actionMute;
                if (Intrinsics.areEqual(action4, str4)) {
                    BooleanSetting booleanSetting2 = BooleanSetting.AUDIO_MUTED;
                    if (!AbstractBooleanSetting.DefaultImpls.getBoolean$default(booleanSetting2, false, 1, null)) {
                        booleanSetting2.setBoolean(true);
                    }
                }
            }
            EmulationActivity.this.buildPictureInPictureParams();
        }
    };

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [org.yuzu.yuzu_emu.activities.EmulationActivity$pictureInPictureReceiver$1] */
    public EmulationActivity() {
        final Function0 function0 = null;
        this.emulationViewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(EmulationViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.activities.EmulationActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.activities.EmulationActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.activities.EmulationActivity$special$$inlined$viewModels$default$3
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

    private final void enableFullscreenImmersive() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(2);
    }

    private final EmulationViewModel getEmulationViewModel() {
        return (EmulationViewModel) this.emulationViewModel$delegate.getValue();
    }

    private final PictureInPictureParams.Builder getPictureInPictureActionsBuilder(PictureInPictureParams.Builder builder) {
        RemoteAction remoteAction;
        RemoteAction remoteAction2;
        ArrayList arrayList = new ArrayList();
        if (NativeLibrary.INSTANCE.isPaused()) {
            Icon createWithResource = Icon.createWithResource(this, R$drawable.ic_pip_play);
            Intrinsics.checkNotNullExpressionValue(createWithResource, "createWithResource(...)");
            remoteAction = new RemoteAction(createWithResource, getString(R$string.play), getString(R$string.play), PendingIntent.getBroadcast(this, R$drawable.ic_pip_play, new Intent(this.actionPlay), 201326592));
        } else {
            Icon createWithResource2 = Icon.createWithResource(this, R$drawable.ic_pip_pause);
            Intrinsics.checkNotNullExpressionValue(createWithResource2, "createWithResource(...)");
            remoteAction = new RemoteAction(createWithResource2, getString(R$string.pause), getString(R$string.pause), PendingIntent.getBroadcast(this, R$drawable.ic_pip_pause, new Intent(this.actionPause), 201326592));
        }
        arrayList.add(remoteAction);
        if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.AUDIO_MUTED, false, 1, null)) {
            Icon createWithResource3 = Icon.createWithResource(this, R$drawable.ic_pip_unmute);
            Intrinsics.checkNotNullExpressionValue(createWithResource3, "createWithResource(...)");
            remoteAction2 = new RemoteAction(createWithResource3, getString(R$string.unmute), getString(R$string.unmute), PendingIntent.getBroadcast(this, R$drawable.ic_pip_unmute, new Intent(this.actionUnmute), 201326592));
        } else {
            Icon createWithResource4 = Icon.createWithResource(this, R$drawable.ic_pip_mute);
            Intrinsics.checkNotNullExpressionValue(createWithResource4, "createWithResource(...)");
            remoteAction2 = new RemoteAction(createWithResource4, getString(R$string.mute), getString(R$string.mute), PendingIntent.getBroadcast(this, R$drawable.ic_pip_mute, new Intent(this.actionMute), 201326592));
        }
        arrayList.add(remoteAction2);
        builder.setActions(arrayList);
        return builder;
    }

    private final PictureInPictureParams.Builder getPictureInPictureAspectBuilder(PictureInPictureParams.Builder builder) {
        Rational rational = null;
        int int$default = AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.RENDERER_ASPECT_RATIO, false, 1, null);
        if (int$default == 0) {
            rational = new Rational(16, 9);
        } else if (int$default == 1) {
            rational = new Rational(4, 3);
        } else if (int$default == 2) {
            rational = new Rational(21, 9);
        } else if (int$default == 3) {
            rational = new Rational(16, 10);
        }
        if (rational != null) {
            builder.setAspectRatio(rational);
        }
        return builder;
    }

    private final void startMotionSensorListener() {
        Object systemService = getSystemService("sensor");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        SensorManager sensorManager = (SensorManager) systemService;
        Sensor defaultSensor = sensorManager.getDefaultSensor(4);
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(1);
        sensorManager.registerListener(this, defaultSensor, 1);
        sensorManager.registerListener(this, defaultSensor2, 1);
    }

    private final void stopMotionSensorListener() {
        Object systemService = getSystemService("sensor");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        SensorManager sensorManager = (SensorManager) systemService;
        Sensor defaultSensor = sensorManager.getDefaultSensor(4);
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(1);
        sensorManager.unregisterListener(this, defaultSensor);
        sensorManager.unregisterListener(this, defaultSensor2);
    }

    public final void buildPictureInPictureParams() {
        PictureInPictureParams.Builder pictureInPictureAspectBuilder = getPictureInPictureAspectBuilder(getPictureInPictureActionsBuilder(new PictureInPictureParams.Builder()));
        if (Build.VERSION.SDK_INT >= 31) {
            pictureInPictureAspectBuilder.setAutoEnterEnabled(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.PICTURE_IN_PICTURE, false, 1, null) && (((Boolean) getEmulationViewModel().getEmulationStarted().getValue()).booleanValue() && !((Boolean) getEmulationViewModel().isEmulationStopping().getValue()).booleanValue()));
        }
        setPictureInPictureParams(pictureInPictureAspectBuilder.build());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (((event.getSource() & 16777232) == 16777232 || (event.getSource() & 1025) == 1025) && !((Boolean) getEmulationViewModel().getDrawerOpen().getValue()).booleanValue()) {
            if (event.getActionMasked() == 3) {
                return true;
            }
            return InputHandler.INSTANCE.dispatchGenericMotionEvent(event);
        }
        return super.dispatchGenericMotionEvent(event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (((event.getSource() & 16777232) == 16777232 || (event.getSource() & 1025) == 1025) && !((Boolean) getEmulationViewModel().getDrawerOpen().getValue()).booleanValue()) {
            return InputHandler.INSTANCE.dispatchKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }

    public final boolean isActivityRecreated() {
        return this.isActivityRecreated;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ParamPackage paramPackage;
        Log.INSTANCE.setGameLaunched(true);
        ThemeHelper.INSTANCE.setTheme(this);
        super.onCreate(bundle);
        InputHandler.INSTANCE.updateControllerData();
        boolean z = false;
        for (PlayerInput playerInput : NativeConfig.INSTANCE.getInputSettings(true)) {
            if (playerInput.hasMapping()) {
                z = true;
            }
        }
        ActivityEmulationBinding activityEmulationBinding = null;
        if (!z) {
            InputHandler inputHandler = InputHandler.INSTANCE;
            if (!inputHandler.getAndroidControllers().isEmpty()) {
                Iterator it = inputHandler.getRegisteredControllers().iterator();
                while (true) {
                    if (it.hasNext()) {
                        paramPackage = (ParamPackage) it.next();
                        if (paramPackage.get("port", -1) == 0) {
                            break;
                        }
                    } else {
                        paramPackage = null;
                        break;
                    }
                }
                if (paramPackage != null) {
                    NativeInput nativeInput = NativeInput.INSTANCE;
                    String string = getString(R$string.unknown);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    nativeInput.updateMappingsWithDefault(0, paramPackage, paramPackage.get("display", string));
                    NativeConfig.INSTANCE.saveGlobalConfig();
                }
            }
        }
        ActivityEmulationBinding inflate = ActivityEmulationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEmulationBinding = inflate;
        }
        setContentView(activityEmulationBinding.getRoot());
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R$id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        ((NavHostFragment) findFragmentById).getNavController().setGraph(R$navigation.emulation_navigation, getIntent().getExtras());
        this.isActivityRecreated = bundle != null;
        enableFullscreenImmersive();
        getWindow().getDecorView().setBackgroundColor(getColor(R.color.black));
        NfcReader nfcReader = new NfcReader(this);
        this.nfcReader = nfcReader;
        nfcReader.initialize();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext());
        if (defaultSharedPreferences.getBoolean("MemoryWarningShown", false)) {
            return;
        }
        MemoryUtil memoryUtil = MemoryUtil.INSTANCE;
        if (memoryUtil.isLessThan(8, memoryUtil.getTotalMemory())) {
            Toast.makeText(this, getString(R$string.device_memory_inadequate, memoryUtil.getDeviceRAM(), getString(R$string.memory_formatted, NumberFormat.getInstance().format((Object) 8), getString(R$string.memory_gigabyte))), 1).show();
            defaultSharedPreferences.edit().putBoolean("MemoryWarningShown", true).apply();
        }
    }

    public final void onEmulationStarted() {
        getEmulationViewModel().setEmulationStarted(true);
    }

    public final void onEmulationStopped(int i) {
        if (i == 0 && ((Number) getEmulationViewModel().getProgramChanged().getValue()).intValue() == -1) {
            finish();
        }
        getEmulationViewModel().setEmulationStopped(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0) {
            if (i == 66) {
                View findViewById = findViewById(R$id.surface_input_overlay);
                Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
                Object systemService = findViewById.getContext().getSystemService("input_method");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                ((InputMethodManager) systemService).hideSoftInputFromWindow(findViewById.getWindowToken(), 0);
            } else {
                int unicodeChar = event.getUnicodeChar();
                if (unicodeChar == 0) {
                    NativeLibrary.INSTANCE.submitInlineKeyboardInput(i);
                } else {
                    NativeLibrary.INSTANCE.submitInlineKeyboardText(String.valueOf((char) unicodeChar));
                }
            }
        }
        return super.onKeyDown(i, event);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        NfcReader nfcReader = this.nfcReader;
        if (nfcReader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcReader");
            nfcReader = null;
        }
        nfcReader.onNewIntent(intent);
        InputHandler.INSTANCE.updateControllerData();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        NfcReader nfcReader = this.nfcReader;
        if (nfcReader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcReader");
            nfcReader = null;
        }
        nfcReader.stopScanning();
        stopMotionSensorListener();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onPictureInPictureModeChanged(z, newConfig);
        if (!z) {
            try {
                unregisterReceiver(this.pictureInPictureReceiver);
            } catch (Exception unused) {
            }
            BooleanSetting booleanSetting = BooleanSetting.AUDIO_MUTED;
            if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(booleanSetting, false, 1, null)) {
                booleanSetting.setBoolean(false);
                return;
            }
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.actionPause);
        intentFilter.addAction(this.actionPlay);
        intentFilter.addAction(this.actionMute);
        intentFilter.addAction(this.actionUnmute);
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.pictureInPictureReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.pictureInPictureReceiver, intentFilter);
        }
    }

    public final void onProgramChanged(int i) {
        getEmulationViewModel().setProgramChanged(i);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        NfcReader nfcReader = this.nfcReader;
        if (nfcReader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcReader");
            nfcReader = null;
        }
        nfcReader.startScanning();
        startMotionSensorListener();
        InputHandler.INSTANCE.updateControllerData();
        buildPictureInPictureParams();
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        Display display;
        Intrinsics.checkNotNullParameter(event, "event");
        display = getDisplay();
        Integer valueOf = display != null ? Integer.valueOf(display.getRotation()) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            this.flipMotionOrientation = true;
        }
        if (valueOf != null && valueOf.intValue() == 3) {
            this.flipMotionOrientation = false;
        }
        if (event.sensor.getType() == 1) {
            if (this.flipMotionOrientation) {
                float[] fArr = this.accel;
                float[] fArr2 = event.values;
                fArr[0] = fArr2[1] / 9.80665f;
                fArr[1] = (-fArr2[0]) / 9.80665f;
            } else {
                float[] fArr3 = this.accel;
                float[] fArr4 = event.values;
                fArr3[0] = (-fArr4[1]) / 9.80665f;
                fArr3[1] = fArr4[0] / 9.80665f;
            }
            this.accel[2] = (-event.values[2]) / 9.80665f;
        }
        if (event.sensor.getType() == 4) {
            if (this.flipMotionOrientation) {
                float[] fArr5 = this.gyro;
                float[] fArr6 = event.values;
                fArr5[0] = (-fArr6[1]) / 6.0f;
                fArr5[1] = fArr6[0] / 6.0f;
            } else {
                float[] fArr7 = this.gyro;
                float[] fArr8 = event.values;
                fArr7[0] = fArr8[1] / 6.0f;
                fArr7[1] = (-fArr8[0]) / 6.0f;
            }
            this.gyro[2] = event.values[2] / 6.0f;
        }
        if (event.sensor.getType() != 1) {
            return;
        }
        long j = event.timestamp;
        long j2 = (j - this.motionTimestamp) / 1000;
        this.motionTimestamp = j;
        NativeInput nativeInput = NativeInput.INSTANCE;
        float[] fArr9 = this.gyro;
        float f = fArr9[0];
        float f2 = fArr9[1];
        float f3 = fArr9[2];
        float[] fArr10 = this.accel;
        nativeInput.onDeviceMotionEvent(0, j2, f, f2, f3, fArr10[0], fArr10[1], fArr10[2]);
        float[] fArr11 = this.gyro;
        float f4 = fArr11[0];
        float f5 = fArr11[1];
        float f6 = fArr11[2];
        float[] fArr12 = this.accel;
        nativeInput.onDeviceMotionEvent(8, j2, f4, f5, f6, fArr12[0], fArr12[1], fArr12[2]);
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        if (Build.VERSION.SDK_INT >= 31 || !AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.PICTURE_IN_PICTURE, false, 1, null) || isInPictureInPictureMode()) {
            return;
        }
        enterPictureInPictureMode(getPictureInPictureAspectBuilder(getPictureInPictureActionsBuilder(new PictureInPictureParams.Builder())).build());
    }
}
