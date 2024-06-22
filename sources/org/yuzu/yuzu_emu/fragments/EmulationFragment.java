package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.Slider;
import com.google.android.material.textview.MaterialTextView;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$array;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$menu;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.activities.EmulationActivity;
import org.yuzu.yuzu_emu.databinding.DialogOverlayAdjustBinding;
import org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.features.settings.utils.SettingsFile;
import org.yuzu.yuzu_emu.fragments.EmulationFragment;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.EmulationViewModel;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.overlay.InputOverlay;
import org.yuzu.yuzu_emu.overlay.model.OverlayControl;
import org.yuzu.yuzu_emu.overlay.model.OverlayControlData;
import org.yuzu.yuzu_emu.overlay.model.OverlayLayout;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.GameHelper;
import org.yuzu.yuzu_emu.utils.GameIconUtils;
import org.yuzu.yuzu_emu.utils.Log;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class EmulationFragment extends Fragment implements SurfaceHolder.Callback {
    public static final Companion Companion = new Companion(null);
    private static final Handler perfStatsUpdateHandler;
    private static final Handler thermalStatsUpdateHandler;
    private FragmentEmulationBinding _binding;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(EmulationFragmentArgs.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$navArgs$1
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
    private final Lazy driverViewModel$delegate;
    private EmulationActivity emulationActivity;
    private EmulationState emulationState;
    private final Lazy emulationViewModel$delegate;
    private Game game;
    private boolean isInFoldableLayout;
    private Function0 perfStatsUpdater;
    private PowerManager powerManager;
    private Function0 thermalStatsUpdater;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EmulationState {
        private final Function0 emulationCanStart;
        public Thread emulationThread;
        private final String gamePath;
        private State state;
        private Surface surface;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* loaded from: classes.dex */
        public static final class State {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ State[] $VALUES;
            public static final State STOPPED = new State("STOPPED", 0);
            public static final State RUNNING = new State("RUNNING", 1);
            public static final State PAUSED = new State("PAUSED", 2);

            private static final /* synthetic */ State[] $values() {
                return new State[]{STOPPED, RUNNING, PAUSED};
            }

            static {
                State[] $values = $values();
                $VALUES = $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }

            private State(String str, int i) {
            }

            public static State valueOf(String str) {
                return (State) Enum.valueOf(State.class, str);
            }

            public static State[] values() {
                return (State[]) $VALUES.clone();
            }
        }

        /* loaded from: classes.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[State.values().length];
                try {
                    iArr[State.RUNNING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[State.PAUSED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[State.STOPPED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public EmulationState(String gamePath, Function0 emulationCanStart) {
            Intrinsics.checkNotNullParameter(gamePath, "gamePath");
            Intrinsics.checkNotNullParameter(emulationCanStart, "emulationCanStart");
            this.gamePath = gamePath;
            this.emulationCanStart = emulationCanStart;
            this.state = State.STOPPED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void changeProgram$lambda$0(EmulationState this$0, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.INSTANCE.debug("[EmulationFragment] Starting emulation thread.");
            NativeLibrary.INSTANCE.run(this$0.gamePath, i, false);
        }

        public static /* synthetic */ void run$default(EmulationState emulationState, boolean z, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            emulationState.run(z, i);
        }

        private final void runWithValidSurface(final int i) {
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            nativeLibrary.surfaceChanged(this.surface);
            if (((Boolean) this.emulationCanStart.invoke()).booleanValue()) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()];
                if (i2 == 2) {
                    Log.INSTANCE.debug("[EmulationFragment] Resuming emulation.");
                    nativeLibrary.unpauseEmulation();
                } else if (i2 != 3) {
                    Log.INSTANCE.debug("[EmulationFragment] Bug, run called while already running.");
                } else {
                    setEmulationThread(new Thread(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$EmulationState$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            EmulationFragment.EmulationState.runWithValidSurface$lambda$1(EmulationFragment.EmulationState.this, i);
                        }
                    }, "NativeEmulation"));
                    getEmulationThread().start();
                }
                this.state = State.RUNNING;
            }
        }

        static /* synthetic */ void runWithValidSurface$default(EmulationState emulationState, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = 0;
            }
            emulationState.runWithValidSurface(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void runWithValidSurface$lambda$1(EmulationState this$0, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.INSTANCE.debug("[EmulationFragment] Starting emulation thread.");
            NativeLibrary.INSTANCE.run(this$0.gamePath, i, true);
        }

        public final synchronized void changeProgram(final int i) {
            getEmulationThread().join();
            setEmulationThread(new Thread(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$EmulationState$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment.EmulationState.changeProgram$lambda$0(EmulationFragment.EmulationState.this, i);
                }
            }, "NativeEmulation"));
            getEmulationThread().start();
        }

        public final synchronized void clearSurface() {
            Log log;
            String str;
            if (this.surface == null) {
                log = Log.INSTANCE;
                str = "[EmulationFragment] clearSurface called, but surface already null.";
            } else {
                this.surface = null;
                log = Log.INSTANCE;
                log.debug("[EmulationFragment] Surface destroyed.");
                int i = WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()];
                if (i != 1) {
                    str = i != 2 ? "[EmulationFragment] Surface cleared while emulation stopped." : "[EmulationFragment] Surface cleared while emulation paused.";
                } else {
                    this.state = State.PAUSED;
                }
            }
            log.warning(str);
        }

        public final Thread getEmulationThread() {
            Thread thread = this.emulationThread;
            if (thread != null) {
                return thread;
            }
            Intrinsics.throwUninitializedPropertyAccessException("emulationThread");
            return null;
        }

        public final synchronized boolean isPaused() {
            return this.state == State.PAUSED;
        }

        public final synchronized boolean isRunning() {
            return this.state == State.RUNNING;
        }

        public final synchronized void newSurface(Surface surface) {
            this.surface = surface;
            if (surface != null) {
                runWithValidSurface$default(this, 0, 1, null);
            }
        }

        public final synchronized void pause() {
            State state = this.state;
            State state2 = State.PAUSED;
            if (state != state2) {
                Log.INSTANCE.debug("[EmulationFragment] Pausing emulation.");
                NativeLibrary.INSTANCE.pauseEmulation();
                this.state = state2;
            } else {
                Log.INSTANCE.warning("[EmulationFragment] Pause called while already paused.");
            }
        }

        public final synchronized void run(boolean z, int i) {
            if (!z) {
                Log.INSTANCE.debug("[EmulationFragment] activity resumed or fresh start");
            } else if (NativeLibrary.INSTANCE.isRunning()) {
                this.state = State.PAUSED;
            }
            if (this.surface != null) {
                runWithValidSurface(i);
            }
        }

        public final void setEmulationThread(Thread thread) {
            Intrinsics.checkNotNullParameter(thread, "<set-?>");
            this.emulationThread = thread;
        }

        public final synchronized void stop() {
            State state = this.state;
            State state2 = State.STOPPED;
            if (state != state2) {
                Log.INSTANCE.debug("[EmulationFragment] Stopping emulation.");
                NativeLibrary.INSTANCE.stopEmulation();
                this.state = state2;
            } else {
                Log.INSTANCE.warning("[EmulationFragment] Stop called while already stopped.");
            }
        }

        public final synchronized void updateSurface() {
            Surface surface = this.surface;
            if (surface != null) {
                NativeLibrary.INSTANCE.surfaceChanged(surface);
            }
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Settings.EmulationOrientation.values().length];
            try {
                iArr[Settings.EmulationOrientation.Unspecified.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Settings.EmulationOrientation.SensorLandscape.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Settings.EmulationOrientation.Landscape.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Settings.EmulationOrientation.ReverseLandscape.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Settings.EmulationOrientation.SensorPortrait.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Settings.EmulationOrientation.Portrait.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Settings.EmulationOrientation.ReversePortrait.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Settings.EmulationVerticalAlignment.values().length];
            try {
                iArr2[Settings.EmulationVerticalAlignment.Top.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[Settings.EmulationVerticalAlignment.Center.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[Settings.EmulationVerticalAlignment.Bottom.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        Looper myLooper = Looper.myLooper();
        Intrinsics.checkNotNull(myLooper);
        perfStatsUpdateHandler = new Handler(myLooper);
        Looper myLooper2 = Looper.myLooper();
        Intrinsics.checkNotNull(myLooper2);
        thermalStatsUpdateHandler = new Handler(myLooper2);
    }

    public EmulationFragment() {
        final Function0 function0 = null;
        this.emulationViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(EmulationViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$3
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
        this.driverViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$5
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$special$$inlined$activityViewModels$default$6
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

    private final void adjustOverlay() {
        final DialogOverlayAdjustBinding inflate = DialogOverlayAdjustBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        Slider slider = inflate.inputScaleSlider;
        slider.setValueTo(150.0f);
        slider.setValue(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_SCALE, false, 1, null));
        slider.addOnChangeListener(new BaseOnChangeListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda14
            @Override // com.google.android.material.slider.BaseOnChangeListener
            public final void onValueChange(Slider slider2, float f, boolean z) {
                EmulationFragment.adjustOverlay$lambda$39$lambda$36$lambda$35(DialogOverlayAdjustBinding.this, this, slider2, f, z);
            }
        });
        Slider slider2 = inflate.inputOpacitySlider;
        slider2.setValueTo(100.0f);
        slider2.setValue(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_OPACITY, false, 1, null));
        slider2.addOnChangeListener(new BaseOnChangeListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda15
            @Override // com.google.android.material.slider.BaseOnChangeListener
            public final void onValueChange(Slider slider3, float f, boolean z) {
                EmulationFragment.adjustOverlay$lambda$39$lambda$38$lambda$37(DialogOverlayAdjustBinding.this, this, slider3, f, z);
            }
        });
        inflate.inputScaleValue.setText(((int) inflate.inputScaleSlider.getValue()) + "%");
        inflate.inputOpacityValue.setText(((int) inflate.inputOpacitySlider.getValue()) + "%");
        new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.emulation_control_adjust).setView((View) inflate.getRoot()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                EmulationFragment.adjustOverlay$lambda$40(dialogInterface, i);
            }
        }).setNeutralButton(R$string.slider_default, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda17
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                EmulationFragment.adjustOverlay$lambda$41(EmulationFragment.this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustOverlay$lambda$39$lambda$36$lambda$35(DialogOverlayAdjustBinding this_apply, EmulationFragment this$0, Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(slider, "<anonymous parameter 0>");
        int i = (int) f;
        this_apply.inputScaleValue.setText(i + "%");
        this$0.setControlScale(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustOverlay$lambda$39$lambda$38$lambda$37(DialogOverlayAdjustBinding this_apply, EmulationFragment this$0, Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(slider, "<anonymous parameter 0>");
        int i = (int) f;
        this_apply.inputOpacityValue.setText(i + "%");
        this$0.setControlOpacity(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustOverlay$lambda$40(DialogInterface dialogInterface, int i) {
        NativeConfig.INSTANCE.saveGlobalConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustOverlay$lambda$41(EmulationFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setControlScale(50);
        this$0.setControlOpacity(100);
    }

    private final EmulationFragmentArgs getArgs() {
        return (EmulationFragmentArgs) this.args$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentEmulationBinding getBinding() {
        FragmentEmulationBinding fragmentEmulationBinding = this._binding;
        Intrinsics.checkNotNull(fragmentEmulationBinding);
        return fragmentEmulationBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EmulationViewModel getEmulationViewModel() {
        return (EmulationViewModel) this.emulationViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(EmulationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopConfiguringControls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$2(EmulationFragment this$0, MenuItem it) {
        Resources resources;
        int i;
        NavDirections actionGlobalSettingsActivity;
        Resources resources2;
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        EmulationState emulationState = null;
        if (itemId != R$id.menu_pause_emulation) {
            if (itemId == R$id.menu_settings) {
                actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_ROOT);
            } else {
                if (itemId != R$id.menu_settings_per_game) {
                    if (itemId == R$id.menu_controls) {
                        actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(null, Settings.MenuTag.SECTION_INPUT);
                        DrawerLayout root = this$0.getBinding().getRoot();
                        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                        ViewKt.findNavController(root).navigate(actionGlobalSettingsActivity);
                        return true;
                    }
                    if (itemId == R$id.menu_overlay_controls) {
                        this$0.showOverlayOptions();
                    } else if (itemId == R$id.menu_lock_drawer) {
                        IntSetting intSetting = IntSetting.LOCK_DRAWER;
                        int int$default = AbstractIntSetting.DefaultImpls.getInt$default(intSetting, false, 1, null);
                        if (int$default != 0) {
                            if (int$default == 1) {
                                intSetting.setInt(0);
                                it.setTitle(this$0.getResources().getString(R$string.lock_drawer));
                                resources = this$0.getResources();
                                i = R$drawable.ic_unlock;
                            }
                            this$0.getBinding().inGameMenu.requestFocus();
                            NativeConfig.INSTANCE.saveGlobalConfig();
                        } else {
                            intSetting.setInt(1);
                            it.setTitle(this$0.getResources().getString(R$string.unlock_drawer));
                            resources = this$0.getResources();
                            i = R$drawable.ic_lock;
                        }
                        it.setIcon(ResourcesCompat.getDrawable(resources, i, this$0.requireContext().getTheme()));
                        this$0.getBinding().inGameMenu.requestFocus();
                        NativeConfig.INSTANCE.saveGlobalConfig();
                    } else if (itemId == R$id.menu_exit) {
                        EmulationState emulationState2 = this$0.emulationState;
                        if (emulationState2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
                        } else {
                            emulationState = emulationState2;
                        }
                        emulationState.stop();
                        NativeConfig.INSTANCE.reloadGlobalConfig();
                        this$0.getEmulationViewModel().setIsEmulationStopping(true);
                        this$0.getBinding().drawerLayout.close();
                    }
                    return true;
                }
                actionGlobalSettingsActivity = HomeNavigationDirections.Companion.actionGlobalSettingsActivity(this$0.getArgs().getGame(), Settings.MenuTag.SECTION_ROOT);
            }
            this$0.getBinding().inGameMenu.requestFocus();
            DrawerLayout root2 = this$0.getBinding().getRoot();
            Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
            ViewKt.findNavController(root2).navigate(actionGlobalSettingsActivity);
            return true;
        }
        EmulationState emulationState3 = this$0.emulationState;
        if (emulationState3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            emulationState3 = null;
        }
        if (emulationState3.isPaused()) {
            EmulationState emulationState4 = this$0.emulationState;
            if (emulationState4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emulationState");
                emulationState4 = null;
            }
            EmulationState.run$default(emulationState4, false, 0, 2, null);
            it.setTitle(this$0.getResources().getString(R$string.emulation_pause));
            resources2 = this$0.getResources();
            i2 = R$drawable.ic_pause;
        } else {
            EmulationState emulationState5 = this$0.emulationState;
            if (emulationState5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            } else {
                emulationState = emulationState5;
            }
            emulationState.pause();
            it.setTitle(this$0.getResources().getString(R$string.emulation_unpause));
            resources2 = this$0.getResources();
            i2 = R$drawable.ic_play;
        }
        it.setIcon(ResourcesCompat.getDrawable(resources2, i2, this$0.requireContext().getTheme()));
        this$0.getBinding().inGameMenu.requestFocus();
        return true;
    }

    private final void resetInputOverlay() {
        IntSetting.OVERLAY_SCALE.reset();
        IntSetting.OVERLAY_OPACITY.reset();
        getBinding().surfaceInputOverlay.post(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                EmulationFragment.resetInputOverlay$lambda$13(EmulationFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resetInputOverlay$lambda$13(EmulationFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().surfaceInputOverlay.resetLayoutVisibilityAndPlacement();
    }

    private final void setControlOpacity(int i) {
        IntSetting.OVERLAY_OPACITY.setInt(i);
        getBinding().surfaceInputOverlay.refreshControls();
    }

    private final void setControlScale(int i) {
        IntSetting.OVERLAY_SCALE.setInt(i);
        getBinding().surfaceInputOverlay.refreshControls();
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().inGameMenu, new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda6
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$42;
                insets$lambda$42 = EmulationFragment.setInsets$lambda$42(view, windowInsetsCompat);
                return insets$lambda$42;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$42(View v, WindowInsetsCompat windowInsets) {
        int i;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        int i2 = 0;
        if (ViewCompat.getLayoutDirection(v) == 0) {
            i2 = insets.left;
            i = 0;
        } else {
            i = insets.right;
        }
        v.setPadding(i2, insets.top, i, v.getPaddingBottom());
        return windowInsets;
    }

    private final void showOverlayOptions() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), getBinding().inGameMenu.findViewById(R$id.menu_overlay_controls));
        popupMenu.getMenuInflater().inflate(R$menu.menu_overlay_options, popupMenu.getMenu());
        Menu menu = popupMenu.getMenu();
        menu.findItem(R$id.menu_toggle_fps).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_PERFORMANCE_OVERLAY, false, 1, null));
        menu.findItem(R$id.thermal_indicator).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_THERMAL_OVERLAY, false, 1, null));
        menu.findItem(R$id.menu_rel_stick_center).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.JOYSTICK_REL_CENTER, false, 1, null));
        menu.findItem(R$id.menu_dpad_slide).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.DPAD_SLIDE, false, 1, null));
        menu.findItem(R$id.menu_show_overlay).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_INPUT_OVERLAY, false, 1, null));
        menu.findItem(R$id.menu_haptics).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.HAPTIC_FEEDBACK, false, 1, null));
        menu.findItem(R$id.menu_touchscreen).setChecked(AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.TOUCHSCREEN, false, 1, null));
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda4
            @Override // androidx.appcompat.widget.PopupMenu.OnDismissListener
            public final void onDismiss(PopupMenu popupMenu2) {
                EmulationFragment.showOverlayOptions$lambda$23(popupMenu2);
            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda5
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean showOverlayOptions$lambda$32;
                showOverlayOptions$lambda$32 = EmulationFragment.showOverlayOptions$lambda$32(EmulationFragment.this, menuItem);
                return showOverlayOptions$lambda$32;
            }
        });
        popupMenu.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOverlayOptions$lambda$23(PopupMenu popupMenu) {
        NativeConfig.INSTANCE.saveGlobalConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showOverlayOptions$lambda$32(final EmulationFragment this$0, MenuItem menuItem) {
        BooleanSetting booleanSetting;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R$id.menu_toggle_fps) {
            menuItem.setChecked(!menuItem.isChecked());
            BooleanSetting.SHOW_PERFORMANCE_OVERLAY.setBoolean(menuItem.isChecked());
            this$0.updateShowFpsOverlay();
        } else if (itemId == R$id.thermal_indicator) {
            menuItem.setChecked(!menuItem.isChecked());
            BooleanSetting.SHOW_THERMAL_OVERLAY.setBoolean(menuItem.isChecked());
            this$0.updateThermalOverlay();
        } else if (itemId == R$id.menu_edit_overlay) {
            this$0.getBinding().drawerLayout.close();
            this$0.getBinding().surfaceInputOverlay.requestFocus();
            this$0.startConfiguringControls();
        } else if (itemId == R$id.menu_adjust_overlay) {
            this$0.adjustOverlay();
        } else if (itemId == R$id.menu_toggle_controls) {
            final OverlayControlData[] overlayControlData = NativeConfig.INSTANCE.getOverlayControlData();
            final boolean[] zArr = new boolean[overlayControlData.length];
            int length = overlayControlData.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                OverlayControlData overlayControlData2 = null;
                if (i >= length) {
                    break;
                }
                OverlayControlData overlayControlData3 = overlayControlData[i];
                int i3 = i2 + 1;
                int length2 = overlayControlData.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length2) {
                        break;
                    }
                    OverlayControlData overlayControlData4 = overlayControlData[i4];
                    if (Intrinsics.areEqual(((OverlayControl) OverlayControl.getEntries().get(i2)).getId(), overlayControlData4.getId())) {
                        overlayControlData2 = overlayControlData4;
                        break;
                    }
                    i4++;
                }
                zArr[i2] = overlayControlData2 != null && overlayControlData2.getEnabled();
                i++;
                i2 = i3;
            }
            final AlertDialog show = new MaterialAlertDialogBuilder(this$0.requireContext()).setTitle(R$string.emulation_toggle_controls).setMultiChoiceItems(R$array.gamepadButtons, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda9
                @Override // android.content.DialogInterface.OnMultiChoiceClickListener
                public final void onClick(DialogInterface dialogInterface, int i5, boolean z) {
                    EmulationFragment.showOverlayOptions$lambda$32$lambda$27(overlayControlData, dialogInterface, i5, z);
                }
            }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda10
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    EmulationFragment.showOverlayOptions$lambda$32$lambda$28(overlayControlData, this$0, dialogInterface, i5);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setNeutralButton(R$string.emulation_toggle_all, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda11
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    EmulationFragment.showOverlayOptions$lambda$32$lambda$29(dialogInterface, i5);
                }
            }).show();
            show.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    EmulationFragment.showOverlayOptions$lambda$32$lambda$31(zArr, overlayControlData, show, view);
                }
            });
        } else if (itemId == R$id.menu_show_overlay) {
            menuItem.setChecked(!menuItem.isChecked());
            BooleanSetting.SHOW_INPUT_OVERLAY.setBoolean(menuItem.isChecked());
            this$0.getBinding().surfaceInputOverlay.refreshControls();
        } else {
            if (itemId == R$id.menu_rel_stick_center) {
                menuItem.setChecked(!menuItem.isChecked());
                booleanSetting = BooleanSetting.JOYSTICK_REL_CENTER;
            } else if (itemId == R$id.menu_dpad_slide) {
                menuItem.setChecked(!menuItem.isChecked());
                booleanSetting = BooleanSetting.DPAD_SLIDE;
            } else if (itemId == R$id.menu_haptics) {
                menuItem.setChecked(!menuItem.isChecked());
                booleanSetting = BooleanSetting.HAPTIC_FEEDBACK;
            } else if (itemId == R$id.menu_touchscreen) {
                menuItem.setChecked(!menuItem.isChecked());
                booleanSetting = BooleanSetting.TOUCHSCREEN;
            } else if (itemId == R$id.menu_reset_overlay) {
                this$0.getBinding().drawerLayout.close();
                this$0.resetInputOverlay();
            }
            booleanSetting.setBoolean(menuItem.isChecked());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOverlayOptions$lambda$32$lambda$27(OverlayControlData[] overlayControlData, DialogInterface dialogInterface, int i, boolean z) {
        OverlayControlData overlayControlData2;
        Intrinsics.checkNotNullParameter(overlayControlData, "$overlayControlData");
        int length = overlayControlData.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                overlayControlData2 = null;
                break;
            }
            overlayControlData2 = overlayControlData[i2];
            if (Intrinsics.areEqual(((OverlayControl) OverlayControl.getEntries().get(i)).getId(), overlayControlData2.getId())) {
                break;
            } else {
                i2++;
            }
        }
        if (overlayControlData2 == null) {
            return;
        }
        overlayControlData2.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOverlayOptions$lambda$32$lambda$28(OverlayControlData[] overlayControlData, EmulationFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(overlayControlData, "$overlayControlData");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        nativeConfig.setOverlayControlData(overlayControlData);
        nativeConfig.saveGlobalConfig();
        this$0.getBinding().surfaceInputOverlay.refreshControls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOverlayOptions$lambda$32$lambda$29(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOverlayOptions$lambda$32$lambda$31(boolean[] optionsArray, OverlayControlData[] overlayControlData, AlertDialog alertDialog, View view) {
        Intrinsics.checkNotNullParameter(optionsArray, "$optionsArray");
        Intrinsics.checkNotNullParameter(overlayControlData, "$overlayControlData");
        int i = 0;
        boolean z = !optionsArray[0];
        int length = overlayControlData.length;
        int i2 = 0;
        while (i < length) {
            OverlayControlData overlayControlData2 = overlayControlData[i];
            optionsArray[i2] = z;
            alertDialog.getListView().setItemChecked(i2, z);
            overlayControlData[i2].setEnabled(z);
            i++;
            i2++;
        }
    }

    private final void startConfiguringControls() {
        EmulationActivity emulationActivity;
        if (AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.RENDERER_SCREEN_LAYOUT, false, 1, null) == Settings.EmulationOrientation.Unspecified.getInt() && (emulationActivity = this.emulationActivity) != null) {
            emulationActivity.setRequestedOrientation(getResources().getConfiguration().orientation == 1 ? 12 : 11);
        }
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        Button doneControlConfig = getBinding().doneControlConfig;
        Intrinsics.checkNotNullExpressionValue(doneControlConfig, "doneControlConfig");
        ViewUtils.setVisible$default(viewUtils, doneControlConfig, true, false, 2, null);
        getBinding().surfaceInputOverlay.setIsInEditMode(true);
    }

    private final void startEmulation(int i) {
        NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
        if (nativeLibrary.isRunning() || nativeLibrary.isPaused()) {
            return;
        }
        DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
        if (!directoryInitialization.getAreDirectoriesReady()) {
            directoryInitialization.start();
        }
        updateScreenLayout();
        EmulationState emulationState = this.emulationState;
        if (emulationState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            emulationState = null;
        }
        EmulationActivity emulationActivity = this.emulationActivity;
        Intrinsics.checkNotNull(emulationActivity);
        emulationState.run(emulationActivity.isActivityRecreated(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void startEmulation$default(EmulationFragment emulationFragment, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        emulationFragment.startEmulation(i);
    }

    private final void stopConfiguringControls() {
        EmulationActivity emulationActivity;
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        Button doneControlConfig = getBinding().doneControlConfig;
        Intrinsics.checkNotNullExpressionValue(doneControlConfig, "doneControlConfig");
        ViewUtils.setVisible$default(viewUtils, doneControlConfig, false, false, 2, null);
        getBinding().surfaceInputOverlay.setIsInEditMode(false);
        if (AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.RENDERER_SCREEN_LAYOUT, false, 1, null) == Settings.EmulationOrientation.Unspecified.getInt() && (emulationActivity = this.emulationActivity) != null) {
            emulationActivity.setRequestedOrientation(-1);
        }
        NativeConfig.INSTANCE.saveGlobalConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
    public final void updateFoldableLayout(EmulationActivity emulationActivity, WindowLayoutInfo windowLayoutInfo) {
        FoldingFeature foldingFeature;
        boolean z;
        Iterator it = windowLayoutInfo.getDisplayFeatures().iterator();
        while (true) {
            if (!it.hasNext()) {
                foldingFeature = 0;
                break;
            } else {
                foldingFeature = it.next();
                if (((DisplayFeature) foldingFeature) instanceof FoldingFeature) {
                    break;
                }
            }
        }
        FoldingFeature foldingFeature2 = foldingFeature instanceof FoldingFeature ? foldingFeature : null;
        if (foldingFeature2 != null) {
            if (foldingFeature2.isSeparating()) {
                emulationActivity.setRequestedOrientation(-1);
                if (Intrinsics.areEqual(foldingFeature2.getOrientation(), FoldingFeature.Orientation.HORIZONTAL)) {
                    getBinding().emulationContainer.getLayoutParams().height = foldingFeature2.getBounds().top;
                    getBinding().inputContainer.getLayoutParams().height = foldingFeature2.getBounds().bottom;
                    getBinding().inGameMenu.getLayoutParams().height = foldingFeature2.getBounds().bottom;
                    this.isInFoldableLayout = true;
                    getBinding().surfaceInputOverlay.setLayout(OverlayLayout.Foldable);
                }
            }
            z = foldingFeature2.isSeparating();
        } else {
            z = false;
        }
        if (!z) {
            getBinding().emulationContainer.getLayoutParams().height = -1;
            getBinding().inputContainer.getLayoutParams().height = -1;
            getBinding().inGameMenu.getLayoutParams().height = -1;
            this.isInFoldableLayout = false;
            updateOrientation();
            Configuration configuration = getResources().getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
            onConfigurationChanged(configuration);
        }
        getBinding().emulationContainer.requestLayout();
        getBinding().inputContainer.requestLayout();
        getBinding().inGameMenu.requestLayout();
    }

    private final void updateOrientation() {
        EmulationActivity emulationActivity = this.emulationActivity;
        if (emulationActivity != null) {
            int i = 0;
            switch (WhenMappings.$EnumSwitchMapping$0[Settings.EmulationOrientation.Companion.from(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.RENDERER_SCREEN_LAYOUT, false, 1, null)).ordinal()]) {
                case 1:
                    i = -1;
                    break;
                case 2:
                    i = 6;
                    break;
                case 3:
                    break;
                case 4:
                    i = 8;
                    break;
                case 5:
                    i = 7;
                    break;
                case 6:
                    i = 1;
                    break;
                case 7:
                    i = 9;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            emulationActivity.setRequestedOrientation(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateScreenLayout() {
        /*
            r8 = this;
            org.yuzu.yuzu_emu.features.settings.model.Settings$EmulationVerticalAlignment$Companion r0 = org.yuzu.yuzu_emu.features.settings.model.Settings.EmulationVerticalAlignment.Companion
            org.yuzu.yuzu_emu.features.settings.model.IntSetting r1 = org.yuzu.yuzu_emu.features.settings.model.IntSetting.VERTICAL_ALIGNMENT
            r2 = 0
            r3 = 1
            r4 = 0
            int r1 = org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting.DefaultImpls.getInt$default(r1, r2, r3, r4)
            org.yuzu.yuzu_emu.features.settings.model.Settings$EmulationVerticalAlignment r0 = r0.from(r1)
            org.yuzu.yuzu_emu.features.settings.model.IntSetting r1 = org.yuzu.yuzu_emu.features.settings.model.IntSetting.RENDERER_ASPECT_RATIO
            int r1 = org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting.DefaultImpls.getInt$default(r1, r2, r3, r4)
            r2 = 9
            r5 = 16
            r6 = 2
            r7 = 3
            if (r1 == 0) goto L3c
            if (r1 == r3) goto L35
            if (r1 == r6) goto L2d
            if (r1 == r7) goto L25
            r1 = r4
            goto L41
        L25:
            android.util.Rational r1 = new android.util.Rational
            r2 = 10
            r1.<init>(r5, r2)
            goto L41
        L2d:
            android.util.Rational r1 = new android.util.Rational
            r5 = 21
            r1.<init>(r5, r2)
            goto L41
        L35:
            android.util.Rational r1 = new android.util.Rational
            r2 = 4
            r1.<init>(r2, r7)
            goto L41
        L3c:
            android.util.Rational r1 = new android.util.Rational
            r1.<init>(r5, r2)
        L41:
            int[] r2 = org.yuzu.yuzu_emu.fragments.EmulationFragment.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r2[r0]
            r2 = -2
            r5 = -1
            if (r0 == r3) goto L8d
            if (r0 == r6) goto L63
            if (r0 == r7) goto L52
            goto La8
        L52:
            org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding r0 = r8.getBinding()
            org.yuzu.yuzu_emu.views.FixedRatioSurfaceView r0 = r0.surfaceEmulation
            r0.setAspectRatio(r1)
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r0.<init>(r5, r2)
            r1 = 81
            goto L9d
        L63:
            org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding r0 = r8.getBinding()
            org.yuzu.yuzu_emu.views.FixedRatioSurfaceView r0 = r0.surfaceEmulation
            r0.setAspectRatio(r4)
            org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding r0 = r8.getBinding()
            org.yuzu.yuzu_emu.views.FixedRatioSurfaceView r0 = r0.surfaceEmulation
            java.lang.String r1 = "surfaceEmulation"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
            if (r1 == 0) goto L85
            r1.width = r5
            r1.height = r5
            r0.setLayoutParams(r1)
            goto La8
        L85:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams"
            r8.<init>(r0)
            throw r8
        L8d:
            org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding r0 = r8.getBinding()
            org.yuzu.yuzu_emu.views.FixedRatioSurfaceView r0 = r0.surfaceEmulation
            r0.setAspectRatio(r1)
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r0.<init>(r5, r2)
            r1 = 49
        L9d:
            r0.gravity = r1
            org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding r1 = r8.getBinding()
            org.yuzu.yuzu_emu.views.FixedRatioSurfaceView r1 = r1.surfaceEmulation
            r1.setLayoutParams(r0)
        La8:
            org.yuzu.yuzu_emu.fragments.EmulationFragment$EmulationState r0 = r8.emulationState
            if (r0 != 0) goto Lb2
            java.lang.String r0 = "emulationState"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto Lb3
        Lb2:
            r4 = r0
        Lb3:
            r4.updateSurface()
            org.yuzu.yuzu_emu.activities.EmulationActivity r0 = r8.emulationActivity
            if (r0 == 0) goto Lbd
            r0.buildPictureInPictureParams()
        Lbd:
            r8.updateOrientation()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.fragments.EmulationFragment.updateScreenLayout():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateShowFpsOverlay() {
        boolean boolean$default = AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_PERFORMANCE_OVERLAY, false, 1, null);
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView showFpsText = getBinding().showFpsText;
        Intrinsics.checkNotNullExpressionValue(showFpsText, "showFpsText");
        ViewUtils.setVisible$default(viewUtils, showFpsText, boolean$default, false, 2, null);
        if (boolean$default) {
            final EmulationFragment$updateShowFpsOverlay$1 emulationFragment$updateShowFpsOverlay$1 = new EmulationFragment$updateShowFpsOverlay$1(this, 1);
            this.perfStatsUpdater = emulationFragment$updateShowFpsOverlay$1;
            Handler handler = perfStatsUpdateHandler;
            Intrinsics.checkNotNull(emulationFragment$updateShowFpsOverlay$1);
            handler.post(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment.updateShowFpsOverlay$lambda$14(Function0.this);
                }
            });
            return;
        }
        final Function0 function0 = this.perfStatsUpdater;
        if (function0 != null) {
            Handler handler2 = perfStatsUpdateHandler;
            Intrinsics.checkNotNull(function0);
            handler2.removeCallbacks(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment.updateShowFpsOverlay$lambda$15(Function0.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateShowFpsOverlay$lambda$14(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateShowFpsOverlay$lambda$15(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateThermalOverlay() {
        boolean boolean$default = AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_THERMAL_OVERLAY, false, 1, null);
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView showThermalsText = getBinding().showThermalsText;
        Intrinsics.checkNotNullExpressionValue(showThermalsText, "showThermalsText");
        ViewUtils.setVisible$default(viewUtils, showThermalsText, boolean$default, false, 2, null);
        if (boolean$default) {
            final EmulationFragment$updateThermalOverlay$1 emulationFragment$updateThermalOverlay$1 = new EmulationFragment$updateThermalOverlay$1(this);
            this.thermalStatsUpdater = emulationFragment$updateThermalOverlay$1;
            Handler handler = thermalStatsUpdateHandler;
            Intrinsics.checkNotNull(emulationFragment$updateThermalOverlay$1);
            handler.post(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment.updateThermalOverlay$lambda$16(Function0.this);
                }
            });
            return;
        }
        final Function0 function0 = this.thermalStatsUpdater;
        if (function0 != null) {
            Handler handler2 = thermalStatsUpdateHandler;
            Intrinsics.checkNotNull(function0);
            handler2.removeCallbacks(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment.updateThermalOverlay$lambda$17(Function0.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateThermalOverlay$lambda$16(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateThermalOverlay$lambda$17(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (!(context instanceof EmulationActivity)) {
            throw new IllegalStateException("EmulationFragment must have EmulationActivity parent");
        }
        EmulationActivity emulationActivity = (EmulationActivity) context;
        this.emulationActivity = emulationActivity;
        NativeLibrary.INSTANCE.setEmulationActivity(emulationActivity);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (this._binding == null) {
            return;
        }
        updateScreenLayout();
        boolean boolean$default = AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_INPUT_OVERLAY, false, 1, null);
        EmulationActivity emulationActivity = this.emulationActivity;
        if (emulationActivity != null && emulationActivity.isInPictureInPictureMode()) {
            if (getBinding().drawerLayout.isOpen()) {
                getBinding().drawerLayout.close();
            }
            if (boolean$default) {
                ViewUtils viewUtils = ViewUtils.INSTANCE;
                InputOverlay surfaceInputOverlay = getBinding().surfaceInputOverlay;
                Intrinsics.checkNotNullExpressionValue(surfaceInputOverlay, "surfaceInputOverlay");
                viewUtils.setVisible(surfaceInputOverlay, false, false);
                return;
            }
            return;
        }
        ViewUtils viewUtils2 = ViewUtils.INSTANCE;
        InputOverlay surfaceInputOverlay2 = getBinding().surfaceInputOverlay;
        Intrinsics.checkNotNullExpressionValue(surfaceInputOverlay2, "surfaceInputOverlay");
        ViewUtils.setVisible$default(viewUtils2, surfaceInputOverlay2, boolean$default && ((Boolean) getEmulationViewModel().getEmulationStarted().getValue()).booleanValue(), false, 2, null);
        if (this.isInFoldableLayout) {
            return;
        }
        getBinding().surfaceInputOverlay.setLayout(newConfig.orientation == 1 ? OverlayLayout.Portrait : OverlayLayout.Landscape);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Game game;
        Game game2;
        super.onCreate(bundle);
        updateOrientation();
        Object systemService = requireContext().getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        this.powerManager = (PowerManager) systemService;
        Uri data = requireActivity().getIntent().getData();
        Game game3 = null;
        if (data == null || !Game.Companion.getExtensions().contains(FileUtil.INSTANCE.getExtension(data))) {
            game = null;
        } else {
            GameHelper gameHelper = GameHelper.INSTANCE;
            Uri data2 = requireActivity().getIntent().getData();
            Intrinsics.checkNotNull(data2);
            game = gameHelper.getGame(data2, false);
        }
        try {
            if (getArgs().getGame() != null) {
                game2 = getArgs().getGame();
                Intrinsics.checkNotNull(game2);
            } else {
                Intrinsics.checkNotNull(game);
                game2 = game;
            }
            this.game = game2;
            if (getArgs().getCustom() || game != null) {
                SettingsFile settingsFile = SettingsFile.INSTANCE;
                Game game4 = this.game;
                if (game4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("game");
                    game4 = null;
                }
                settingsFile.loadCustomConfig(game4);
                NativeConfig.INSTANCE.unloadPerGameConfig();
            } else {
                NativeConfig.INSTANCE.reloadGlobalConfig();
            }
            getDriverViewModel().onLaunchGame();
            setRetainInstance(true);
            Game game5 = this.game;
            if (game5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("game");
            } else {
                game3 = game5;
            }
            this.emulationState = new EmulationState(game3.getPath(), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onCreate$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    DriverViewModel driverViewModel;
                    driverViewModel = EmulationFragment.this.getDriverViewModel();
                    return (Boolean) driverViewModel.isInteractionAllowed().getValue();
                }
            });
        } catch (NullPointerException unused) {
            Toast.makeText(requireContext(), R$string.no_game_present, 0).show();
            requireActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentEmulationBinding.inflate(getLayoutInflater());
        DrawerLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        NativeLibrary.INSTANCE.clearEmulationActivity();
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        EmulationState emulationState = this.emulationState;
        EmulationState emulationState2 = null;
        if (emulationState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            emulationState = null;
        }
        if (emulationState.isRunning()) {
            EmulationActivity emulationActivity = this.emulationActivity;
            boolean z = false;
            if (emulationActivity != null && emulationActivity.isInPictureInPictureMode()) {
                z = true;
            }
            if (!z) {
                EmulationState emulationState3 = this.emulationState;
                if (emulationState3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("emulationState");
                } else {
                    emulationState2 = emulationState3;
                }
                emulationState2.pause();
            }
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (requireActivity().isFinishing()) {
            return;
        }
        getBinding().surfaceEmulation.getHolder().addCallback(this);
        getBinding().doneControlConfig.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                EmulationFragment.onViewCreated$lambda$0(EmulationFragment.this, view2);
            }
        });
        getBinding().drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$2
            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerClosed(View drawerView) {
                FragmentEmulationBinding binding;
                EmulationViewModel emulationViewModel;
                Intrinsics.checkNotNullParameter(drawerView, "drawerView");
                binding = EmulationFragment.this.getBinding();
                binding.drawerLayout.setDrawerLockMode(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.LOCK_DRAWER, false, 1, null));
                emulationViewModel = EmulationFragment.this.getEmulationViewModel();
                emulationViewModel.setDrawerOpen(false);
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerOpened(View drawerView) {
                FragmentEmulationBinding binding;
                FragmentEmulationBinding binding2;
                EmulationViewModel emulationViewModel;
                Intrinsics.checkNotNullParameter(drawerView, "drawerView");
                binding = EmulationFragment.this.getBinding();
                binding.drawerLayout.setDrawerLockMode(0);
                binding2 = EmulationFragment.this.getBinding();
                binding2.inGameMenu.requestFocus();
                emulationViewModel = EmulationFragment.this.getEmulationViewModel();
                emulationViewModel.setDrawerOpen(true);
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerSlide(View drawerView, float f) {
                FragmentEmulationBinding binding;
                Intrinsics.checkNotNullParameter(drawerView, "drawerView");
                binding = EmulationFragment.this.getBinding();
                binding.surfaceInputOverlay.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), 100 + SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerStateChanged(int i) {
            }
        });
        getBinding().drawerLayout.setDrawerLockMode(1);
        TextView textView = (TextView) getBinding().inGameMenu.getHeaderView(0).findViewById(R$id.text_game_title);
        Game game = this.game;
        Game game2 = null;
        if (game == null) {
            Intrinsics.throwUninitializedPropertyAccessException("game");
            game = null;
        }
        textView.setText(game.getTitle());
        MenuItem findItem = getBinding().inGameMenu.getMenu().findItem(R$id.menu_lock_drawer);
        int int$default = AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.LOCK_DRAWER, false, 1, null);
        int i = int$default == 1 ? R$string.unlock_drawer : R$string.lock_drawer;
        int i2 = int$default == 0 ? R$drawable.ic_unlock : R$drawable.ic_lock;
        findItem.setTitle(getString(i));
        findItem.setIcon(ResourcesCompat.getDrawable(getResources(), i2, requireContext().getTheme()));
        getBinding().inGameMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$$ExternalSyntheticLambda1
            @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                boolean onViewCreated$lambda$2;
                onViewCreated$lambda$2 = EmulationFragment.onViewCreated$lambda$2(EmulationFragment.this, menuItem);
                return onViewCreated$lambda$2;
            }
        });
        setInsets();
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        onBackPressedDispatcher.addCallback(requireActivity, new OnBackPressedCallback() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$onViewCreated$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                EmulationViewModel emulationViewModel;
                FragmentEmulationBinding binding;
                if (NativeLibrary.INSTANCE.isRunning()) {
                    emulationViewModel = EmulationFragment.this.getEmulationViewModel();
                    binding = EmulationFragment.this.getBinding();
                    emulationViewModel.setDrawerOpen(!binding.drawerLayout.isOpen());
                }
            }
        });
        GameIconUtils gameIconUtils = GameIconUtils.INSTANCE;
        Game game3 = this.game;
        if (game3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("game");
            game3 = null;
        }
        ImageView loadingImage = getBinding().loadingImage;
        Intrinsics.checkNotNullExpressionValue(loadingImage, "loadingImage");
        gameIconUtils.loadGameIcon(game3, loadingImage);
        MaterialTextView materialTextView = getBinding().loadingTitle;
        Game game4 = this.game;
        if (game4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("game");
        } else {
            game2 = game4;
        }
        materialTextView.setText(game2.getTitle());
        getBinding().loadingTitle.setSelected(true);
        getBinding().loadingText.setSelected(true);
        WindowInfoTracker.Companion companion = WindowInfoTracker.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        WindowInfoTracker orCreate = companion.getOrCreate(requireContext);
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        Flow windowLayoutInfo = orCreate.windowLayoutInfo(requireActivity2);
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, windowLayoutInfo, null, this), 3, null);
        StateFlow shaderProgress = getEmulationViewModel().getShaderProgress();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, shaderProgress, null, this), 3, null);
        StateFlow totalShaders = getEmulationViewModel().getTotalShaders();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, totalShaders, null, this), 3, null);
        StateFlow shaderMessage = getEmulationViewModel().getShaderMessage();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner4), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$4(viewLifecycleOwner4, state, shaderMessage, null, this), 3, null);
        StateFlow emulationStarted = getEmulationViewModel().getEmulationStarted();
        LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner5, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner5), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$5(viewLifecycleOwner5, state, emulationStarted, null, this), 3, null);
        StateFlow isEmulationStopping = getEmulationViewModel().isEmulationStopping();
        LifecycleOwner viewLifecycleOwner6 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner6, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner6), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$6(viewLifecycleOwner6, state, isEmulationStopping, null, this), 3, null);
        StateFlow drawerOpen = getEmulationViewModel().getDrawerOpen();
        LifecycleOwner viewLifecycleOwner7 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner7, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner7), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$7(viewLifecycleOwner7, state, drawerOpen, null, this), 3, null);
        StateFlow programChanged = getEmulationViewModel().getProgramChanged();
        LifecycleOwner viewLifecycleOwner8 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner8, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner8), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$8(viewLifecycleOwner8, state, programChanged, null, this), 3, null);
        StateFlow emulationStopped = getEmulationViewModel().getEmulationStopped();
        LifecycleOwner viewLifecycleOwner9 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner9, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner9), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$9(viewLifecycleOwner9, state, emulationStopped, null, this), 3, null);
        StateFlow isInteractionAllowed = getDriverViewModel().isInteractionAllowed();
        LifecycleOwner viewLifecycleOwner10 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner10, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner10), null, null, new EmulationFragment$onViewCreated$$inlined$collect$default$10(viewLifecycleOwner10, state, isInteractionAllowed, null, this), 3, null);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Log.INSTANCE.debug("[EmulationFragment] Surface changed. Resolution: " + i2 + "x" + i3);
        EmulationState emulationState = this.emulationState;
        if (emulationState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            emulationState = null;
        }
        emulationState.newSurface(holder.getSurface());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        EmulationState emulationState = this.emulationState;
        if (emulationState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationState");
            emulationState = null;
        }
        emulationState.clearSurface();
    }
}
