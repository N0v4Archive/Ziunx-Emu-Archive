package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.yuzu.yuzu_emu.features.settings.model.AbstractByteSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class ByteSetting implements AbstractByteSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ByteSetting[] $VALUES;
    public static final ByteSetting AUDIO_VOLUME = new ByteSetting("AUDIO_VOLUME", 0, "volume");
    private final Lazy defaultValue$delegate;
    private final String key;

    private static final /* synthetic */ ByteSetting[] $values() {
        return new ByteSetting[]{AUDIO_VOLUME};
    }

    static {
        ByteSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private ByteSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.ByteSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Byte invoke() {
                return Byte.valueOf(Byte.parseByte(NativeConfig.INSTANCE.getDefaultToString(ByteSetting.this.getKey())));
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static ByteSetting valueOf(String str) {
        return (ByteSetting) Enum.valueOf(ByteSetting.class, str);
    }

    public static ByteSetting[] values() {
        return (ByteSetting[]) $VALUES.clone();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractByteSetting
    public byte getByte(boolean z) {
        return NativeConfig.INSTANCE.getByte(getKey(), z);
    }

    public Byte getDefaultValue() {
        return (Byte) this.defaultValue$delegate.getValue();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean getGlobal() {
        return AbstractByteSetting.DefaultImpls.getGlobal(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getKey() {
        return this.key;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getPairedSettingKey() {
        return AbstractByteSetting.DefaultImpls.getPairedSettingKey(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getValueAsString(boolean z) {
        return String.valueOf((int) getByte(z));
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isRuntimeModifiable() {
        return AbstractByteSetting.DefaultImpls.isRuntimeModifiable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSaveable() {
        return AbstractByteSetting.DefaultImpls.isSaveable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSwitchable() {
        return AbstractByteSetting.DefaultImpls.isSwitchable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void reset() {
        NativeConfig.INSTANCE.setByte(getKey(), getDefaultValue().byteValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractByteSetting
    public void setByte(byte b) {
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setByte(getKey(), b);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractByteSetting.DefaultImpls.setGlobal(this, z);
    }
}
