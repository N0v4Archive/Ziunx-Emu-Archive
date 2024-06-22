package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractShortSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractShortSetting abstractShortSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractShortSetting);
        }

        public static String getPairedSettingKey(AbstractShortSetting abstractShortSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractShortSetting);
        }

        public static boolean isRuntimeModifiable(AbstractShortSetting abstractShortSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractShortSetting);
        }

        public static boolean isSaveable(AbstractShortSetting abstractShortSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractShortSetting);
        }

        public static boolean isSwitchable(AbstractShortSetting abstractShortSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractShortSetting);
        }

        public static void setGlobal(AbstractShortSetting abstractShortSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractShortSetting, z);
        }
    }

    short getShort(boolean z);

    void setShort(short s);
}
