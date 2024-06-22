package org.yuzu.yuzu_emu.features.settings.ui;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$array;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.AnalogDirection;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.features.input.model.NpadStyleIndex;
import org.yuzu.yuzu_emu.features.input.model.PlayerInput;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.ByteSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.features.settings.model.LongSetting;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.features.settings.model.ShortSetting;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.HeaderSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ModifierInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.RunnableSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SubmenuSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SwitchSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class SettingsFragmentPresenter {
    private final SettingsAdapter adapter;
    private Settings.MenuTag menuTag;
    private ArrayList settingsList;
    private final SettingsViewModel settingsViewModel;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Settings.MenuTag.values().length];
            try {
                iArr[Settings.MenuTag.SECTION_ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_SYSTEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_RENDERER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_ONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_TWO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_THREE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_FOUR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_FIVE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_SIX.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_SEVEN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_INPUT_PLAYER_EIGHT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_THEME.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[Settings.MenuTag.SECTION_DEBUG.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[NpadStyleIndex.values().length];
            try {
                iArr2[NpadStyleIndex.Fullkey.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[NpadStyleIndex.Handheld.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[NpadStyleIndex.JoyconDual.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[NpadStyleIndex.JoyconLeft.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[NpadStyleIndex.JoyconRight.ordinal()] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[NpadStyleIndex.GameCube.ordinal()] = 6;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public SettingsFragmentPresenter(SettingsViewModel settingsViewModel, SettingsAdapter adapter, Settings.MenuTag menuTag) {
        Intrinsics.checkNotNullParameter(settingsViewModel, "settingsViewModel");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(menuTag, "menuTag");
        this.settingsViewModel = settingsViewModel;
        this.adapter = adapter;
        this.menuTag = menuTag;
        this.settingsList = new ArrayList();
    }

    private final void addAudioSettings(ArrayList arrayList) {
        add(arrayList, IntSetting.AUDIO_OUTPUT_ENGINE.getKey());
        add(arrayList, ByteSetting.AUDIO_VOLUME.getKey());
    }

    private final void addConfigSettings(ArrayList arrayList) {
        arrayList.add(new SubmenuSetting(R$string.preferences_system, null, R$string.preferences_system_description, null, R$drawable.ic_system_settings, Settings.MenuTag.SECTION_SYSTEM, 10, null));
        arrayList.add(new SubmenuSetting(R$string.preferences_graphics, null, R$string.preferences_graphics_description, null, R$drawable.ic_graphics, Settings.MenuTag.SECTION_RENDERER, 10, null));
        arrayList.add(new SubmenuSetting(R$string.preferences_audio, null, R$string.preferences_audio_description, null, R$drawable.ic_audio, Settings.MenuTag.SECTION_AUDIO, 10, null));
        arrayList.add(new SubmenuSetting(R$string.preferences_debug, null, R$string.preferences_debug_description, null, R$drawable.ic_code, Settings.MenuTag.SECTION_DEBUG, 10, null));
        arrayList.add(new RunnableSetting(R$string.reset_to_default, null, R$string.reset_to_default_description, null, !NativeLibrary.INSTANCE.isRunning(), R$drawable.ic_restore, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$addConfigSettings$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m115invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m115invoke() {
                SettingsViewModel settingsViewModel;
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldShowResetSettingsDialog(true);
            }
        }, 10, null));
    }

    private final void addDebugSettings(ArrayList arrayList) {
        arrayList.add(new HeaderSetting(R$string.gpu, null, 2, null));
        add(arrayList, IntSetting.RENDERER_BACKEND.getKey());
        add(arrayList, BooleanSetting.RENDERER_DEBUG.getKey());
        arrayList.add(new HeaderSetting(R$string.cpu, null, 2, null));
        add(arrayList, IntSetting.CPU_BACKEND.getKey());
        add(arrayList, IntSetting.CPU_ACCURACY.getKey());
        add(arrayList, BooleanSetting.CPU_DEBUG_MODE.getKey());
        add(arrayList, "fastmem_combined");
    }

    private final void addGraphicsSettings(ArrayList arrayList) {
        add(arrayList, IntSetting.RENDERER_ACCURACY.getKey());
        add(arrayList, IntSetting.RENDERER_RESOLUTION.getKey());
        add(arrayList, IntSetting.RENDERER_VSYNC.getKey());
        add(arrayList, IntSetting.RENDERER_SCALING_FILTER.getKey());
        add(arrayList, IntSetting.FSR_SHARPENING_SLIDER.getKey());
        add(arrayList, IntSetting.RENDERER_ANTI_ALIASING.getKey());
        add(arrayList, IntSetting.MAX_ANISOTROPY.getKey());
        add(arrayList, IntSetting.RENDERER_SCREEN_LAYOUT.getKey());
        add(arrayList, IntSetting.RENDERER_ASPECT_RATIO.getKey());
        add(arrayList, IntSetting.VERTICAL_ALIGNMENT.getKey());
        add(arrayList, BooleanSetting.PICTURE_IN_PICTURE.getKey());
        add(arrayList, BooleanSetting.RENDERER_USE_DISK_SHADER_CACHE.getKey());
        add(arrayList, BooleanSetting.RENDERER_FORCE_MAX_CLOCK.getKey());
        add(arrayList, BooleanSetting.RENDERER_ASYNCHRONOUS_SHADERS.getKey());
        add(arrayList, BooleanSetting.RENDERER_REACTIVE_FLUSHING.getKey());
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x054a  */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v8, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addInputPlayer(java.util.ArrayList r33, final int r34) {
        /*
            Method dump skipped, instructions count: 1548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter.addInputPlayer(java.util.ArrayList, int):void");
    }

    private final void addInputSettings(ArrayList arrayList) {
        List mutableList;
        Integer[] typedArray;
        this.settingsViewModel.setCurrentDevice(0);
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            NativeInput nativeInput = NativeInput.INSTANCE;
            nativeInput.loadInputProfiles();
            mutableList = ArraysKt___ArraysKt.toMutableList(nativeInput.getInputProfileNames());
            mutableList.add(0, "");
            String[] strArr = (String[]) mutableList.toArray(new String[0]);
            String string = getContext().getString(R$string.use_global_input_configuration);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            strArr[0] = string;
            int i = 0;
            for (int i2 = 8; i < i2; i2 = 8) {
                AbstractIntSetting perGameProfileSetting = getPerGameProfileSetting(mutableList, i);
                int i3 = i + 1;
                String playerProfileString = getPlayerProfileString(i3);
                int size = mutableList.size();
                int[] iArr = new int[size];
                for (int i4 = 0; i4 < size; i4++) {
                    iArr[i4] = i4;
                }
                typedArray = ArraysKt___ArraysJvmKt.toTypedArray(iArr);
                arrayList.add(new IntSingleChoiceSetting(perGameProfileSetting, 0, playerProfileString, 0, null, strArr, typedArray, 26, null));
                i = i3;
            }
            return;
        }
        SettingsFragmentPresenter$addInputSettings$getConnectedIcon$1 settingsFragmentPresenter$addInputSettings$getConnectedIcon$1 = new Function1() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$addInputSettings$getConnectedIcon$1
            public final Integer invoke(int i5) {
                return Integer.valueOf(NativeInput.INSTANCE.getIsConnected(i5) ? R$drawable.ic_controller : R$drawable.ic_controller_disconnected);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).intValue());
            }
        };
        PlayerInput[] inputSettings = nativeConfig.getInputSettings(true);
        Settings settings = Settings.INSTANCE;
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(1), 0, inputSettings[0].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 0)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_ONE, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(2), 0, inputSettings[1].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 1)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_TWO, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(3), 0, inputSettings[2].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 2)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_THREE, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(4), 0, inputSettings[3].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 3)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_FOUR, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(5), 0, inputSettings[4].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 4)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_FIVE, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(6), 0, inputSettings[5].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 5)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_SIX, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(7), 0, inputSettings[6].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 6)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_SEVEN, 5, null));
        arrayList.add(new SubmenuSetting(0, settings.getPlayerString(8), 0, inputSettings[7].getProfileName(), ((Number) settingsFragmentPresenter$addInputSettings$getConnectedIcon$1.invoke((Object) 7)).intValue(), Settings.MenuTag.SECTION_INPUT_PLAYER_EIGHT, 5, null));
    }

    private final void addSystemSettings(ArrayList arrayList) {
        add(arrayList, StringSetting.DEVICE_NAME.getKey());
        add(arrayList, BooleanSetting.RENDERER_USE_SPEED_LIMIT.getKey());
        add(arrayList, ShortSetting.RENDERER_SPEED_LIMIT.getKey());
        add(arrayList, BooleanSetting.USE_DOCKED_MODE.getKey());
        add(arrayList, IntSetting.REGION_INDEX.getKey());
        add(arrayList, IntSetting.LANGUAGE_INDEX.getKey());
        add(arrayList, BooleanSetting.USE_CUSTOM_RTC.getKey());
        add(arrayList, LongSetting.CUSTOM_RTC.getKey());
    }

    private final void addThemeSettings(ArrayList arrayList) {
        AbstractIntSetting abstractIntSetting = new AbstractIntSetting() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$addThemeSettings$1$theme$1
            private final int defaultValue;
            private final boolean isRuntimeModifiable;
            private final String key;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                IntSetting intSetting = IntSetting.THEME;
                this.key = intSetting.getKey();
                this.isRuntimeModifiable = intSetting.isRuntimeModifiable();
                this.defaultValue = intSetting.getDefaultValue().intValue();
            }

            public Integer getDefaultValue() {
                return Integer.valueOf(this.defaultValue);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return AbstractIntSetting.DefaultImpls.getGlobal(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public int getInt(boolean z) {
                return AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.THEME, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractIntSetting.DefaultImpls.getPairedSettingKey(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return AbstractSetting.DefaultImpls.getValueAsString$default(IntSetting.THEME, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return this.isRuntimeModifiable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return AbstractIntSetting.DefaultImpls.isSaveable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractIntSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                IntSetting.THEME.setInt(getDefaultValue().intValue());
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractIntSetting.DefaultImpls.setGlobal(this, z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public void setInt(int i) {
                SettingsViewModel settingsViewModel;
                IntSetting.THEME.setInt(i);
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldRecreate(true);
            }
        };
        arrayList.add(Build.VERSION.SDK_INT >= 31 ? new SingleChoiceSetting(abstractIntSetting, R$string.change_app_theme, null, 0, null, R$array.themeEntriesA12, R$array.themeValuesA12, 28, null) : new SingleChoiceSetting(abstractIntSetting, R$string.change_app_theme, null, 0, null, R$array.themeEntries, R$array.themeValues, 28, null));
        arrayList.add(new SingleChoiceSetting(new AbstractIntSetting() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$addThemeSettings$1$themeMode$1
            private final int defaultValue;
            private final boolean isRuntimeModifiable;
            private final String key;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                IntSetting intSetting = IntSetting.THEME_MODE;
                this.key = intSetting.getKey();
                this.isRuntimeModifiable = intSetting.isRuntimeModifiable();
                this.defaultValue = intSetting.getDefaultValue().intValue();
            }

            public Integer getDefaultValue() {
                return Integer.valueOf(this.defaultValue);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return AbstractIntSetting.DefaultImpls.getGlobal(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public int getInt(boolean z) {
                return AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.THEME_MODE, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractIntSetting.DefaultImpls.getPairedSettingKey(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return AbstractSetting.DefaultImpls.getValueAsString$default(IntSetting.THEME_MODE, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return this.isRuntimeModifiable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return AbstractIntSetting.DefaultImpls.isSaveable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractIntSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                SettingsViewModel settingsViewModel;
                IntSetting.THEME_MODE.setInt(getDefaultValue().intValue());
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldRecreate(true);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractIntSetting.DefaultImpls.setGlobal(this, z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public void setInt(int i) {
                SettingsViewModel settingsViewModel;
                IntSetting.THEME_MODE.setInt(i);
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldRecreate(true);
            }
        }, R$string.change_theme_mode, null, 0, null, R$array.themeModeEntries, R$array.themeModeValues, 28, null));
        arrayList.add(new SwitchSetting(new AbstractBooleanSetting() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$addThemeSettings$1$blackBackgrounds$1
            private final boolean defaultValue;
            private final boolean isRuntimeModifiable;
            private final String key;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                BooleanSetting booleanSetting = BooleanSetting.BLACK_BACKGROUNDS;
                this.key = booleanSetting.getKey();
                this.isRuntimeModifiable = booleanSetting.isRuntimeModifiable();
                this.defaultValue = booleanSetting.getDefaultValue().booleanValue();
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public boolean getBoolean(boolean z) {
                return AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.BLACK_BACKGROUNDS, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return AbstractBooleanSetting.DefaultImpls.getGlobal(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractBooleanSetting.DefaultImpls.getPairedSettingKey(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return AbstractSetting.DefaultImpls.getValueAsString$default(BooleanSetting.BLACK_BACKGROUNDS, false, 1, null);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return this.isRuntimeModifiable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return AbstractBooleanSetting.DefaultImpls.isSaveable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractBooleanSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                SettingsViewModel settingsViewModel;
                BooleanSetting booleanSetting = BooleanSetting.BLACK_BACKGROUNDS;
                booleanSetting.setBoolean(booleanSetting.getDefaultValue().booleanValue());
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldRecreate(true);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public void setBoolean(boolean z) {
                SettingsViewModel settingsViewModel;
                BooleanSetting.BLACK_BACKGROUNDS.setBoolean(z);
                settingsViewModel = SettingsFragmentPresenter.this.settingsViewModel;
                settingsViewModel.setShouldRecreate(true);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractBooleanSetting.DefaultImpls.setGlobal(this, z);
            }
        }, R$string.use_black_backgrounds, null, R$string.use_black_backgrounds_description, null, 20, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        return YuzuApplication.Companion.getAppContext();
    }

    private final List getExtraStickSettings(int i, NativeAnalog nativeAnalog) {
        NativeInput nativeInput = NativeInput.INSTANCE;
        boolean isController = nativeInput.isController(nativeInput.getStickParam(i, nativeAnalog));
        AbstractIntSetting stickIntSettingFromParam = getStickIntSettingFromParam(i, "modifier_scale", nativeAnalog, 0.5f);
        AbstractIntSetting stickIntSettingFromParam2 = getStickIntSettingFromParam(i, "range", nativeAnalog, 0.95f);
        AbstractIntSetting stickIntSettingFromParam3 = getStickIntSettingFromParam(i, "deadzone", nativeAnalog, 0.15f);
        ArrayList arrayList = new ArrayList();
        if (isController) {
            arrayList.add(new SliderSetting(stickIntSettingFromParam2, R$string.range, null, 0, null, 25, 150, null, 156, null));
            arrayList.add(new SliderSetting(stickIntSettingFromParam3, R$string.deadzone, null, 0, null, 0, 0, null, 252, null));
            return arrayList;
        }
        arrayList.add(new ModifierInputSetting(i, NativeAnalog.LStick, R$string.modifier, null, 8, null));
        arrayList.add(new SliderSetting(stickIntSettingFromParam, R$string.modifier_range, null, 0, null, 0, 0, null, 252, null));
        return arrayList;
    }

    private final AbstractIntSetting getPerGameProfileSetting(final List list, final int i) {
        return new AbstractIntSetting() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$getPerGameProfileSetting$1
            private final int defaultValue;
            private final String key = "";
            private boolean global = true;
            private final boolean isRuntimeModifiable = true;
            private final boolean isSaveable = true;

            private final PlayerInput[] getPlayers() {
                return NativeConfig.INSTANCE.getInputSettings(false);
            }

            public Integer getDefaultValue() {
                return Integer.valueOf(this.defaultValue);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return this.global;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public int getInt(boolean z) {
                String profileName = getPlayers()[i].getProfileName();
                int i2 = 0;
                for (Object obj : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    if (Intrinsics.areEqual((String) obj, profileName)) {
                        return i2;
                    }
                    i2 = i3;
                }
                return 0;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractIntSetting.DefaultImpls.getPairedSettingKey(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return String.valueOf(AbstractIntSetting.DefaultImpls.getInt$default(this, false, 1, null));
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return this.isRuntimeModifiable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return this.isSaveable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractIntSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                setInt(getDefaultValue().intValue());
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                this.global = z;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public void setInt(int i2) {
                NativeInput nativeInput = NativeInput.INSTANCE;
                nativeInput.loadPerGameConfiguration(i, i2, (String) list.get(i2));
                NativeInput.connectControllers$default(nativeInput, i, false, 2, null);
                NativeConfig.INSTANCE.saveControlPlayerValues();
            }
        };
    }

    private final String getPlayerProfileString(int i) {
        String string = getContext().getString(R$string.player_num_profile, Integer.valueOf(i));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private final List getStickDirections(int i, NativeAnalog nativeAnalog) {
        List listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new AnalogInputSetting[]{new AnalogInputSetting(i, nativeAnalog, AnalogDirection.Up, R$string.up, null, 16, null), new AnalogInputSetting(i, nativeAnalog, AnalogDirection.Down, R$string.down, null, 16, null), new AnalogInputSetting(i, nativeAnalog, AnalogDirection.Left, R$string.left, null, 16, null), new AnalogInputSetting(i, nativeAnalog, AnalogDirection.Right, R$string.right, null, 16, null)});
        return listOf;
    }

    private final AbstractIntSetting getStickIntSettingFromParam(final int i, final String str, final NativeAnalog nativeAnalog, final float f) {
        return new AbstractIntSetting(f, i, nativeAnalog, str) { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$getStickIntSettingFromParam$1
            final /* synthetic */ float $defaultValue;
            final /* synthetic */ String $paramName;
            final /* synthetic */ int $playerIndex;
            final /* synthetic */ NativeAnalog $stick;
            private final int defaultValue;
            private final String key = "";

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$defaultValue = f;
                this.$playerIndex = i;
                this.$stick = nativeAnalog;
                this.$paramName = str;
                this.defaultValue = (int) (f * 100);
            }

            public Integer getDefaultValue() {
                return Integer.valueOf(this.defaultValue);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return AbstractIntSetting.DefaultImpls.getGlobal(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public int getInt(boolean z) {
                return (int) (getParams().get(this.$paramName, this.$defaultValue) * 100);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractIntSetting.DefaultImpls.getPairedSettingKey(this);
            }

            public final ParamPackage getParams() {
                return NativeInput.INSTANCE.getStickParam(this.$playerIndex, this.$stick);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return String.valueOf(getInt(z));
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return AbstractIntSetting.DefaultImpls.isRuntimeModifiable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return AbstractIntSetting.DefaultImpls.isSaveable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractIntSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                setInt(getDefaultValue().intValue());
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractIntSetting.DefaultImpls.setGlobal(this, z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
            public void setInt(int i2) {
                ParamPackage params = getParams();
                params.set(this.$paramName, i2 / 100);
                NativeInput.INSTANCE.setStickParam(this.$playerIndex, this.$stick, params);
            }
        };
    }

    public static /* synthetic */ void loadSettingsList$default(SettingsFragmentPresenter settingsFragmentPresenter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        settingsFragmentPresenter.loadSettingsList(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadSettingsList$lambda$1(boolean z, SettingsFragmentPresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.adapter.notifyDataSetChanged();
        }
    }

    public final void add(ArrayList arrayList, String key) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = SettingsItem.Companion.getSettingsItems().get(key);
        Intrinsics.checkNotNull(obj);
        SettingsItem settingsItem = (SettingsItem) obj;
        if (this.settingsViewModel.getGame() == null || settingsItem.getSetting().isSwitchable()) {
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            boolean z = true;
            if (!nativeConfig.isPerGameConfigLoaded() && !NativeLibrary.INSTANCE.isRunning()) {
                settingsItem.getSetting().setGlobal(true);
            }
            String pairedSettingKey = settingsItem.getSetting().getPairedSettingKey();
            if (pairedSettingKey.length() > 0) {
                if (!NativeLibrary.INSTANCE.isRunning() || nativeConfig.isPerGameConfigLoaded()) {
                    z = nativeConfig.usingGlobal(pairedSettingKey);
                } else if (nativeConfig.usingGlobal(pairedSettingKey)) {
                    z = false;
                }
                if (!nativeConfig.getBoolean(pairedSettingKey, z)) {
                    return;
                }
            }
            arrayList.add(settingsItem);
        }
    }

    public final void addAbstract(ArrayList arrayList, SettingsItem item) {
        Object obj;
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(item, "item");
        String pairedSettingKey = item.getSetting().getPairedSettingKey();
        if (pairedSettingKey.length() > 0) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((SettingsItem) obj).getSetting().getKey(), pairedSettingKey)) {
                        break;
                    }
                }
            }
            SettingsItem settingsItem = (SettingsItem) obj;
            if (settingsItem == null) {
                return;
            }
            AbstractSetting setting = settingsItem.getSetting();
            Intrinsics.checkNotNull(setting, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting");
            if (!((AbstractBooleanSetting) setting).getBoolean(!NativeConfig.INSTANCE.isPerGameConfigLoaded())) {
                return;
            }
        }
        arrayList.add(item);
    }

    public final void loadSettingsList(final boolean z) {
        int i;
        ArrayList arrayList = new ArrayList();
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuTag.ordinal()]) {
            case 1:
                addConfigSettings(arrayList);
                break;
            case 2:
                addSystemSettings(arrayList);
                break;
            case 3:
                addGraphicsSettings(arrayList);
                break;
            case 4:
                addAudioSettings(arrayList);
                break;
            case 5:
                addInputSettings(arrayList);
                break;
            case 6:
                i = 0;
                addInputPlayer(arrayList, i);
                break;
            case 7:
                i = 1;
                addInputPlayer(arrayList, i);
                break;
            case 8:
                i = 2;
                addInputPlayer(arrayList, i);
                break;
            case 9:
                i = 3;
                addInputPlayer(arrayList, i);
                break;
            case 10:
                i = 4;
                addInputPlayer(arrayList, i);
                break;
            case 11:
                i = 5;
                addInputPlayer(arrayList, i);
                break;
            case 12:
                i = 6;
                addInputPlayer(arrayList, i);
                break;
            case 13:
                i = 7;
                addInputPlayer(arrayList, i);
                break;
            case 14:
                addThemeSettings(arrayList);
                break;
            case 15:
                addDebugSettings(arrayList);
                break;
        }
        this.settingsList = arrayList;
        this.adapter.submitList(arrayList, new Runnable() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsFragmentPresenter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SettingsFragmentPresenter.loadSettingsList$lambda$1(z, this);
            }
        });
    }

    public final void onViewCreated() {
        loadSettingsList$default(this, false, 1, null);
    }
}
