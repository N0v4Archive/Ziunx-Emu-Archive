package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class IntSetting implements AbstractIntSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ IntSetting[] $VALUES;
    private final Lazy defaultValue$delegate;
    private final String key;
    public static final IntSetting CPU_BACKEND = new IntSetting("CPU_BACKEND", 0, "cpu_backend");
    public static final IntSetting CPU_ACCURACY = new IntSetting("CPU_ACCURACY", 1, "cpu_accuracy");
    public static final IntSetting REGION_INDEX = new IntSetting("REGION_INDEX", 2, "region_index");
    public static final IntSetting LANGUAGE_INDEX = new IntSetting("LANGUAGE_INDEX", 3, "language_index");
    public static final IntSetting RENDERER_BACKEND = new IntSetting("RENDERER_BACKEND", 4, "backend");
    public static final IntSetting RENDERER_ACCURACY = new IntSetting("RENDERER_ACCURACY", 5, "gpu_accuracy");
    public static final IntSetting RENDERER_RESOLUTION = new IntSetting("RENDERER_RESOLUTION", 6, "resolution_setup");
    public static final IntSetting RENDERER_VSYNC = new IntSetting("RENDERER_VSYNC", 7, "use_vsync");
    public static final IntSetting RENDERER_SCALING_FILTER = new IntSetting("RENDERER_SCALING_FILTER", 8, "scaling_filter");
    public static final IntSetting RENDERER_ANTI_ALIASING = new IntSetting("RENDERER_ANTI_ALIASING", 9, "anti_aliasing");
    public static final IntSetting RENDERER_SCREEN_LAYOUT = new IntSetting("RENDERER_SCREEN_LAYOUT", 10, "screen_layout");
    public static final IntSetting RENDERER_ASPECT_RATIO = new IntSetting("RENDERER_ASPECT_RATIO", 11, "aspect_ratio");
    public static final IntSetting AUDIO_OUTPUT_ENGINE = new IntSetting("AUDIO_OUTPUT_ENGINE", 12, "output_engine");
    public static final IntSetting MAX_ANISOTROPY = new IntSetting("MAX_ANISOTROPY", 13, "max_anisotropy");
    public static final IntSetting THEME = new IntSetting("THEME", 14, "theme");
    public static final IntSetting THEME_MODE = new IntSetting("THEME_MODE", 15, "theme_mode");
    public static final IntSetting OVERLAY_SCALE = new IntSetting("OVERLAY_SCALE", 16, "control_scale");
    public static final IntSetting OVERLAY_OPACITY = new IntSetting("OVERLAY_OPACITY", 17, "control_opacity");
    public static final IntSetting LOCK_DRAWER = new IntSetting("LOCK_DRAWER", 18, "lock_drawer");
    public static final IntSetting VERTICAL_ALIGNMENT = new IntSetting("VERTICAL_ALIGNMENT", 19, "vertical_alignment");
    public static final IntSetting FSR_SHARPENING_SLIDER = new IntSetting("FSR_SHARPENING_SLIDER", 20, "fsr_sharpening_slider");

    private static final /* synthetic */ IntSetting[] $values() {
        return new IntSetting[]{CPU_BACKEND, CPU_ACCURACY, REGION_INDEX, LANGUAGE_INDEX, RENDERER_BACKEND, RENDERER_ACCURACY, RENDERER_RESOLUTION, RENDERER_VSYNC, RENDERER_SCALING_FILTER, RENDERER_ANTI_ALIASING, RENDERER_SCREEN_LAYOUT, RENDERER_ASPECT_RATIO, AUDIO_OUTPUT_ENGINE, MAX_ANISOTROPY, THEME, THEME_MODE, OVERLAY_SCALE, OVERLAY_OPACITY, LOCK_DRAWER, VERTICAL_ALIGNMENT, FSR_SHARPENING_SLIDER};
    }

    static {
        IntSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private IntSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.IntSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(Integer.parseInt(NativeConfig.INSTANCE.getDefaultToString(IntSetting.this.getKey())));
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static IntSetting valueOf(String str) {
        return (IntSetting) Enum.valueOf(IntSetting.class, str);
    }

    public static IntSetting[] values() {
        return (IntSetting[]) $VALUES.clone();
    }

    public Integer getDefaultValue() {
        return (Integer) this.defaultValue$delegate.getValue();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean getGlobal() {
        return AbstractIntSetting.DefaultImpls.getGlobal(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
    public int getInt(boolean z) {
        return NativeConfig.INSTANCE.getInt(getKey(), z);
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
        NativeConfig.INSTANCE.setInt(getKey(), getDefaultValue().intValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractIntSetting.DefaultImpls.setGlobal(this, z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
    public void setInt(int i) {
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setInt(getKey(), i);
    }
}
