package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractByteSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractByteSetting abstractByteSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractByteSetting);
        }

        public static String getPairedSettingKey(AbstractByteSetting abstractByteSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractByteSetting);
        }

        public static boolean isRuntimeModifiable(AbstractByteSetting abstractByteSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractByteSetting);
        }

        public static boolean isSaveable(AbstractByteSetting abstractByteSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractByteSetting);
        }

        public static boolean isSwitchable(AbstractByteSetting abstractByteSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractByteSetting);
        }

        public static void setGlobal(AbstractByteSetting abstractByteSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractByteSetting, z);
        }
    }

    byte getByte(boolean z);

    void setByte(byte b);
}
