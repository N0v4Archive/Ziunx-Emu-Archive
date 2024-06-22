package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class StringSetting implements AbstractStringSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ StringSetting[] $VALUES;
    private final Lazy defaultValue$delegate;
    private final String key;
    public static final StringSetting DRIVER_PATH = new StringSetting("DRIVER_PATH", 0, "driver_path");
    public static final StringSetting DEVICE_NAME = new StringSetting("DEVICE_NAME", 1, "device_name");

    private static final /* synthetic */ StringSetting[] $values() {
        return new StringSetting[]{DRIVER_PATH, DEVICE_NAME};
    }

    static {
        StringSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private StringSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.StringSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return NativeConfig.INSTANCE.getDefaultToString(StringSetting.this.getKey());
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static StringSetting valueOf(String str) {
        return (StringSetting) Enum.valueOf(StringSetting.class, str);
    }

    public static StringSetting[] values() {
        return (StringSetting[]) $VALUES.clone();
    }

    public String getDefaultValue() {
        return (String) this.defaultValue$delegate.getValue();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean getGlobal() {
        return AbstractStringSetting.DefaultImpls.getGlobal(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getKey() {
        return this.key;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getPairedSettingKey() {
        return AbstractStringSetting.DefaultImpls.getPairedSettingKey(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting
    public String getString(boolean z) {
        return NativeConfig.INSTANCE.getString(getKey(), z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getValueAsString(boolean z) {
        return getString(z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isRuntimeModifiable() {
        return AbstractStringSetting.DefaultImpls.isRuntimeModifiable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSaveable() {
        return AbstractStringSetting.DefaultImpls.isSaveable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSwitchable() {
        return AbstractStringSetting.DefaultImpls.isSwitchable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void reset() {
        NativeConfig.INSTANCE.setString(getKey(), getDefaultValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractStringSetting.DefaultImpls.setGlobal(this, z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting
    public void setString(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setString(getKey(), value);
    }
}
