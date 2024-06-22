package org.yuzu.yuzu_emu.features.settings.model.view;

import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$array;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.NpadStyleIndex;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.ByteSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.features.settings.model.LongSetting;
import org.yuzu.yuzu_emu.features.settings.model.ShortSetting;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public abstract class SettingsItem {
    public static final Companion Companion;
    private static final AbstractSetting emptySetting;
    private static final HashMap settingsItems;
    private final Lazy description$delegate;
    private final int descriptionId;
    private final String descriptionString;
    private final AbstractSetting setting;
    private final Lazy title$delegate;
    private final int titleId;
    private final String titleString;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AbstractSetting getEmptySetting() {
            return SettingsItem.emptySetting;
        }

        public final HashMap getSettingsItems() {
            return SettingsItem.settingsItems;
        }

        public final void put(HashMap hashMap, SettingsItem item) {
            Intrinsics.checkNotNullParameter(hashMap, "<this>");
            Intrinsics.checkNotNullParameter(item, "item");
            hashMap.put(item.getSetting().getKey(), item);
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        emptySetting = new AbstractSetting() { // from class: org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem$Companion$emptySetting$1
            private final String key = "";
            private final Object defaultValue = Boolean.FALSE;
            private final boolean isSaveable = true;

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return AbstractSetting.DefaultImpls.getGlobal(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return AbstractSetting.DefaultImpls.getPairedSettingKey(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return "";
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return AbstractSetting.DefaultImpls.isRuntimeModifiable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSaveable() {
                return this.isSaveable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isSwitchable() {
                return AbstractSetting.DefaultImpls.isSwitchable(this);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractSetting.DefaultImpls.setGlobal(this, z);
            }
        };
        HashMap hashMap = new HashMap();
        companion.put(hashMap, new StringInputSetting(StringSetting.DEVICE_NAME, R$string.device_name, null, 0, null, 28, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_USE_SPEED_LIMIT, R$string.frame_limit_enable, null, R$string.frame_limit_enable_description, null, 20, null));
        companion.put(hashMap, new SliderSetting(ShortSetting.RENDERER_SPEED_LIMIT, R$string.frame_limit_slider, null, R$string.frame_limit_slider_description, null, 1, 400, "%", 20, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.CPU_BACKEND, R$string.cpu_backend, null, 0, null, R$array.cpuBackendArm64Names, R$array.cpuBackendArm64Values, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.CPU_ACCURACY, R$string.cpu_accuracy, null, 0, null, R$array.cpuAccuracyNames, R$array.cpuAccuracyValues, 28, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.PICTURE_IN_PICTURE, R$string.picture_in_picture, null, R$string.picture_in_picture_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(new AbstractBooleanSetting() { // from class: org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem$Companion$settingsItems$1$dockedModeSetting$1
            private final boolean defaultValue;
            private final String key;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                BooleanSetting booleanSetting = BooleanSetting.USE_DOCKED_MODE;
                this.key = booleanSetting.getKey();
                this.defaultValue = booleanSetting.getDefaultValue().booleanValue();
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public boolean getBoolean(boolean z) {
                if (NativeInput.INSTANCE.getStyleIndex(0) == NpadStyleIndex.Handheld) {
                    return false;
                }
                return BooleanSetting.USE_DOCKED_MODE.getBoolean(z);
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
                return BooleanSetting.USE_DOCKED_MODE.getValueAsString(z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean isRuntimeModifiable() {
                return AbstractBooleanSetting.DefaultImpls.isRuntimeModifiable(this);
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
                BooleanSetting.USE_DOCKED_MODE.reset();
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public void setBoolean(boolean z) {
                BooleanSetting.USE_DOCKED_MODE.setBoolean(z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                AbstractBooleanSetting.DefaultImpls.setGlobal(this, z);
            }
        }, R$string.use_docked_mode, null, R$string.use_docked_mode_description, null, 20, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.REGION_INDEX, R$string.emulated_region, null, 0, null, R$array.regionNames, R$array.regionValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.LANGUAGE_INDEX, R$string.emulated_language, null, 0, null, R$array.languageNames, R$array.languageValues, 28, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.USE_CUSTOM_RTC, R$string.use_custom_rtc, null, R$string.use_custom_rtc_description, null, 20, null));
        companion.put(hashMap, new DateTimeSetting(LongSetting.CUSTOM_RTC, R$string.set_custom_rtc, null, 0, null, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_ACCURACY, R$string.renderer_accuracy, null, 0, null, R$array.rendererAccuracyNames, R$array.rendererAccuracyValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_RESOLUTION, R$string.renderer_resolution, null, 0, null, R$array.rendererResolutionNames, R$array.rendererResolutionValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_VSYNC, R$string.renderer_vsync, null, 0, null, R$array.rendererVSyncNames, R$array.rendererVSyncValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_SCALING_FILTER, R$string.renderer_scaling_filter, null, 0, null, R$array.rendererScalingFilterNames, R$array.rendererScalingFilterValues, 28, null));
        companion.put(hashMap, new SliderSetting(IntSetting.FSR_SHARPENING_SLIDER, R$string.fsr_sharpness, null, R$string.fsr_sharpness_description, null, 0, 0, "%", 116, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_ANTI_ALIASING, R$string.renderer_anti_aliasing, null, 0, null, R$array.rendererAntiAliasingNames, R$array.rendererAntiAliasingValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_SCREEN_LAYOUT, R$string.renderer_screen_layout, null, 0, null, R$array.rendererScreenLayoutNames, R$array.rendererScreenLayoutValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_ASPECT_RATIO, R$string.renderer_aspect_ratio, null, 0, null, R$array.rendererAspectRatioNames, R$array.rendererAspectRatioValues, 28, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.VERTICAL_ALIGNMENT, R$string.vertical_alignment, null, 0, null, R$array.verticalAlignmentEntries, R$array.verticalAlignmentValues, 20, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_USE_DISK_SHADER_CACHE, R$string.use_disk_shader_cache, null, R$string.use_disk_shader_cache_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_FORCE_MAX_CLOCK, R$string.renderer_force_max_clock, null, R$string.renderer_force_max_clock_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_ASYNCHRONOUS_SHADERS, R$string.renderer_asynchronous_shaders, null, R$string.renderer_asynchronous_shaders_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_REACTIVE_FLUSHING, R$string.renderer_reactive_flushing, null, R$string.renderer_reactive_flushing_description, null, 20, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.MAX_ANISOTROPY, R$string.anisotropic_filtering, null, R$string.anisotropic_filtering_description, null, R$array.anisoEntries, R$array.anisoValues, 20, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.AUDIO_OUTPUT_ENGINE, R$string.audio_output_engine, null, 0, null, R$array.outputEngineEntries, R$array.outputEngineValues, 28, null));
        companion.put(hashMap, new SliderSetting(ByteSetting.AUDIO_VOLUME, R$string.audio_volume, null, R$string.audio_volume_description, null, 0, 0, "%", 116, null));
        companion.put(hashMap, new SingleChoiceSetting(IntSetting.RENDERER_BACKEND, R$string.renderer_api, null, 0, null, R$array.rendererApiNames, R$array.rendererApiValues, 28, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.RENDERER_DEBUG, R$string.renderer_debug, null, R$string.renderer_debug_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(BooleanSetting.CPU_DEBUG_MODE, R$string.cpu_debug_mode, null, R$string.cpu_debug_mode_description, null, 20, null));
        companion.put(hashMap, new SwitchSetting(new AbstractBooleanSetting() { // from class: org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem$Companion$settingsItems$1$fastmem$1
            private final boolean isRuntimeModifiable;
            private final String key = "fastmem_combined";
            private final String pairedSettingKey = BooleanSetting.CPU_DEBUG_MODE.getKey();
            private final boolean defaultValue = true;
            private final boolean isSwitchable = true;
            private final boolean isSaveable = true;

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public boolean getBoolean(boolean z) {
                return AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.FASTMEM, false, 1, null) && AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.FASTMEM_EXCLUSIVES, false, 1, null);
            }

            public Boolean getDefaultValue() {
                return Boolean.valueOf(this.defaultValue);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public boolean getGlobal() {
                return BooleanSetting.FASTMEM.getGlobal() && BooleanSetting.FASTMEM_EXCLUSIVES.getGlobal();
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getKey() {
                return this.key;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getPairedSettingKey() {
                return this.pairedSettingKey;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public String getValueAsString(boolean z) {
                return String.valueOf(AbstractBooleanSetting.DefaultImpls.getBoolean$default(this, false, 1, null));
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
                return this.isSwitchable;
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void reset() {
                setBoolean(getDefaultValue().booleanValue());
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
            public void setBoolean(boolean z) {
                BooleanSetting.FASTMEM.setBoolean(z);
                BooleanSetting.FASTMEM_EXCLUSIVES.setBoolean(z);
            }

            @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
            public void setGlobal(boolean z) {
                BooleanSetting.FASTMEM.setGlobal(z);
                BooleanSetting.FASTMEM_EXCLUSIVES.setGlobal(z);
            }
        }, R$string.fastmem, null, 0, null, 28, null));
        settingsItems = hashMap;
    }

    public SettingsItem(AbstractSetting setting, int i, String titleString, int i2, String descriptionString) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(setting, "setting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        this.setting = setting;
        this.titleId = i;
        this.titleString = titleString;
        this.descriptionId = i2;
        this.descriptionString = descriptionString;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem$title$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                if (SettingsItem.this.getTitleId() == 0) {
                    return SettingsItem.this.getTitleString();
                }
                String string = YuzuApplication.Companion.getAppContext().getString(SettingsItem.this.getTitleId());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            }
        });
        this.title$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem$description$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                if (SettingsItem.this.getDescriptionId() == 0) {
                    return SettingsItem.this.getDescriptionString();
                }
                String string = YuzuApplication.Companion.getAppContext().getString(SettingsItem.this.getDescriptionId());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            }
        });
        this.description$delegate = lazy2;
    }

    public final boolean getClearable() {
        return !this.setting.getGlobal() && NativeConfig.INSTANCE.isPerGameConfigLoaded();
    }

    public final String getDescription() {
        return (String) this.description$delegate.getValue();
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final String getDescriptionString() {
        return this.descriptionString;
    }

    public final boolean getNeedsRuntimeGlobal() {
        return (!NativeLibrary.INSTANCE.isRunning() || this.setting.getGlobal() || NativeConfig.INSTANCE.isPerGameConfigLoaded()) ? false : true;
    }

    public final AbstractSetting getSetting() {
        return this.setting;
    }

    public final String getTitle() {
        return (String) this.title$delegate.getValue();
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public final String getTitleString() {
        return this.titleString;
    }

    public abstract int getType();

    public final boolean isEditable() {
        if (Intrinsics.areEqual(this.setting.getKey(), BooleanSetting.USE_DOCKED_MODE.getKey())) {
            return NativeInput.INSTANCE.getStyleIndex(0) != NpadStyleIndex.Handheld;
        }
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded() && !this.setting.isSaveable()) {
            return false;
        }
        if (!NativeLibrary.INSTANCE.isRunning()) {
            return true;
        }
        if (nativeConfig.isPerGameConfigLoaded() || this.setting.getGlobal()) {
            return this.setting.isRuntimeModifiable();
        }
        return false;
    }
}
