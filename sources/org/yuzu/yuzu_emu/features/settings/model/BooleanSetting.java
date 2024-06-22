package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class BooleanSetting implements AbstractBooleanSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BooleanSetting[] $VALUES;
    private final Lazy defaultValue$delegate;
    private final String key;
    public static final BooleanSetting AUDIO_MUTED = new BooleanSetting("AUDIO_MUTED", 0, "audio_muted");
    public static final BooleanSetting CPU_DEBUG_MODE = new BooleanSetting("CPU_DEBUG_MODE", 1, "cpu_debug_mode");
    public static final BooleanSetting FASTMEM = new BooleanSetting("FASTMEM", 2, "cpuopt_fastmem");
    public static final BooleanSetting FASTMEM_EXCLUSIVES = new BooleanSetting("FASTMEM_EXCLUSIVES", 3, "cpuopt_fastmem_exclusives");
    public static final BooleanSetting RENDERER_USE_SPEED_LIMIT = new BooleanSetting("RENDERER_USE_SPEED_LIMIT", 4, "use_speed_limit");
    public static final BooleanSetting USE_DOCKED_MODE = new BooleanSetting("USE_DOCKED_MODE", 5, "use_docked_mode");
    public static final BooleanSetting RENDERER_USE_DISK_SHADER_CACHE = new BooleanSetting("RENDERER_USE_DISK_SHADER_CACHE", 6, "use_disk_shader_cache");
    public static final BooleanSetting RENDERER_FORCE_MAX_CLOCK = new BooleanSetting("RENDERER_FORCE_MAX_CLOCK", 7, "force_max_clock");
    public static final BooleanSetting RENDERER_ASYNCHRONOUS_SHADERS = new BooleanSetting("RENDERER_ASYNCHRONOUS_SHADERS", 8, "use_asynchronous_shaders");
    public static final BooleanSetting RENDERER_REACTIVE_FLUSHING = new BooleanSetting("RENDERER_REACTIVE_FLUSHING", 9, "use_reactive_flushing");
    public static final BooleanSetting RENDERER_DEBUG = new BooleanSetting("RENDERER_DEBUG", 10, "debug");
    public static final BooleanSetting PICTURE_IN_PICTURE = new BooleanSetting("PICTURE_IN_PICTURE", 11, "picture_in_picture");
    public static final BooleanSetting USE_CUSTOM_RTC = new BooleanSetting("USE_CUSTOM_RTC", 12, "custom_rtc_enabled");
    public static final BooleanSetting BLACK_BACKGROUNDS = new BooleanSetting("BLACK_BACKGROUNDS", 13, "black_backgrounds");
    public static final BooleanSetting JOYSTICK_REL_CENTER = new BooleanSetting("JOYSTICK_REL_CENTER", 14, "joystick_rel_center");
    public static final BooleanSetting DPAD_SLIDE = new BooleanSetting("DPAD_SLIDE", 15, "dpad_slide");
    public static final BooleanSetting HAPTIC_FEEDBACK = new BooleanSetting("HAPTIC_FEEDBACK", 16, "haptic_feedback");
    public static final BooleanSetting SHOW_PERFORMANCE_OVERLAY = new BooleanSetting("SHOW_PERFORMANCE_OVERLAY", 17, "show_performance_overlay");
    public static final BooleanSetting SHOW_INPUT_OVERLAY = new BooleanSetting("SHOW_INPUT_OVERLAY", 18, "show_input_overlay");
    public static final BooleanSetting TOUCHSCREEN = new BooleanSetting("TOUCHSCREEN", 19, "touchscreen");
    public static final BooleanSetting SHOW_THERMAL_OVERLAY = new BooleanSetting("SHOW_THERMAL_OVERLAY", 20, "show_thermal_overlay");

    private static final /* synthetic */ BooleanSetting[] $values() {
        return new BooleanSetting[]{AUDIO_MUTED, CPU_DEBUG_MODE, FASTMEM, FASTMEM_EXCLUSIVES, RENDERER_USE_SPEED_LIMIT, USE_DOCKED_MODE, RENDERER_USE_DISK_SHADER_CACHE, RENDERER_FORCE_MAX_CLOCK, RENDERER_ASYNCHRONOUS_SHADERS, RENDERER_REACTIVE_FLUSHING, RENDERER_DEBUG, PICTURE_IN_PICTURE, USE_CUSTOM_RTC, BLACK_BACKGROUNDS, JOYSTICK_REL_CENTER, DPAD_SLIDE, HAPTIC_FEEDBACK, SHOW_PERFORMANCE_OVERLAY, SHOW_INPUT_OVERLAY, TOUCHSCREEN, SHOW_THERMAL_OVERLAY};
    }

    static {
        BooleanSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private BooleanSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.BooleanSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(Boolean.parseBoolean(NativeConfig.INSTANCE.getDefaultToString(BooleanSetting.this.getKey())));
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static BooleanSetting valueOf(String str) {
        return (BooleanSetting) Enum.valueOf(BooleanSetting.class, str);
    }

    public static BooleanSetting[] values() {
        return (BooleanSetting[]) $VALUES.clone();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
    public boolean getBoolean(boolean z) {
        return NativeConfig.INSTANCE.getBoolean(getKey(), z);
    }

    public Boolean getDefaultValue() {
        return (Boolean) this.defaultValue$delegate.getValue();
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
        return String.valueOf(getBoolean(z));
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
        NativeConfig.INSTANCE.setBoolean(getKey(), getDefaultValue().booleanValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting
    public void setBoolean(boolean z) {
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setBoolean(getKey(), z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractBooleanSetting.DefaultImpls.setGlobal(this, z);
    }
}
