package org.yuzu.yuzu_emu.features.settings.model;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.yuzu.yuzu_emu.features.settings.model.AbstractShortSetting;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class ShortSetting implements AbstractShortSetting {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ShortSetting[] $VALUES;
    public static final ShortSetting RENDERER_SPEED_LIMIT = new ShortSetting("RENDERER_SPEED_LIMIT", 0, "speed_limit");
    private final Lazy defaultValue$delegate;
    private final String key;

    private static final /* synthetic */ ShortSetting[] $values() {
        return new ShortSetting[]{RENDERER_SPEED_LIMIT};
    }

    static {
        ShortSetting[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private ShortSetting(String str, int i, String str2) {
        Lazy lazy;
        this.key = str2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.model.ShortSetting$defaultValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Short invoke() {
                return Short.valueOf(Short.parseShort(NativeConfig.INSTANCE.getDefaultToString(ShortSetting.this.getKey())));
            }
        });
        this.defaultValue$delegate = lazy;
    }

    public static ShortSetting valueOf(String str) {
        return (ShortSetting) Enum.valueOf(ShortSetting.class, str);
    }

    public static ShortSetting[] values() {
        return (ShortSetting[]) $VALUES.clone();
    }

    public Short getDefaultValue() {
        return (Short) this.defaultValue$delegate.getValue();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean getGlobal() {
        return AbstractShortSetting.DefaultImpls.getGlobal(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getKey() {
        return this.key;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getPairedSettingKey() {
        return AbstractShortSetting.DefaultImpls.getPairedSettingKey(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractShortSetting
    public short getShort(boolean z) {
        return NativeConfig.INSTANCE.getShort(getKey(), z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public String getValueAsString(boolean z) {
        return String.valueOf((int) getShort(z));
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isRuntimeModifiable() {
        return AbstractShortSetting.DefaultImpls.isRuntimeModifiable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSaveable() {
        return AbstractShortSetting.DefaultImpls.isSaveable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public boolean isSwitchable() {
        return AbstractShortSetting.DefaultImpls.isSwitchable(this);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void reset() {
        NativeConfig.INSTANCE.setShort(getKey(), getDefaultValue().shortValue());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
    public void setGlobal(boolean z) {
        AbstractShortSetting.DefaultImpls.setGlobal(this, z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractShortSetting
    public void setShort(short s) {
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        if (nativeConfig.isPerGameConfigLoaded()) {
            setGlobal(false);
        }
        nativeConfig.setShort(getKey(), s);
    }
}
