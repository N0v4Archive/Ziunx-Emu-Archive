package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.yuzu.yuzu_emu.features.settings.model.AbstractLongSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class LongSetting implements AbstractLongSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LongSetting[] $VALUES;
    public static final LongSetting CUSTOM_RTC = new LongSetting("CUSTOM_RTC", 0, "custom_rtc");
    private final Lazy defaultValue$delegate;
    private final String key;

    private static final /* synthetic */ LongSetting[] $values() {
        return new LongSetting[]{CUSTOM_RTC};
    }

    static {
        LongSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private LongSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.LongSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                return Long.valueOf(Long.parseLong(NativeConfig.INSTANCE.getDefaultToString(LongSetting.this.getKey())));
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static LongSetting valueOf(String str) {
        return (LongSetting) Enum.valueOf(LongSetting.class, str);
    }

    public static LongSetting[] values() {
        return (LongSetting[]) $VALUES.clone();
    }

    public Long getDefaultValue() {
        return (Long) this.defaultValue$delegate.getValue();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean getGlobal() {
        return AbstractLongSetting.DefaultImpls.getGlobal(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getKey() {
        return this.key;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractLongSetting
    public long getLong(boolean z) {
        return NativeConfig.INSTANCE.getLong(getKey(), z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getPairedSettingKey() {
        return AbstractLongSetting.DefaultImpls.getPairedSettingKey(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getValueAsString(boolean z) {
        return String.valueOf(getLong(z));
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isRuntimeModifiable() {
        return AbstractLongSetting.DefaultImpls.isRuntimeModifiable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSaveable() {
        return AbstractLongSetting.DefaultImpls.isSaveable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSwitchable() {
        return AbstractLongSetting.DefaultImpls.isSwitchable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void reset() {
        NativeConfig.INSTANCE.setLong(getKey(), getDefaultValue().longValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractLongSetting.DefaultImpls.setGlobal(this, z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractLongSetting
    public void setLong(long j) {
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setLong(getKey(), j);
    }
}
