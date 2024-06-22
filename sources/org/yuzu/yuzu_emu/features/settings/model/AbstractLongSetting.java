package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractLongSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractLongSetting abstractLongSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractLongSetting);
        }

        public static String getPairedSettingKey(AbstractLongSetting abstractLongSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractLongSetting);
        }

        public static boolean isRuntimeModifiable(AbstractLongSetting abstractLongSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractLongSetting);
        }

        public static boolean isSaveable(AbstractLongSetting abstractLongSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractLongSetting);
        }

        public static boolean isSwitchable(AbstractLongSetting abstractLongSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractLongSetting);
        }

        public static void setGlobal(AbstractLongSetting abstractLongSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractLongSetting, z);
        }
    }

    long getLong(boolean z);

    void setLong(long j);
}
