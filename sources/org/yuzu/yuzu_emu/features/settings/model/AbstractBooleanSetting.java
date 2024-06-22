package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractBooleanSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean getBoolean$default(AbstractBooleanSetting abstractBooleanSetting, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBoolean");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return abstractBooleanSetting.getBoolean(z);
        }

        public static boolean getGlobal(AbstractBooleanSetting abstractBooleanSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractBooleanSetting);
        }

        public static String getPairedSettingKey(AbstractBooleanSetting abstractBooleanSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractBooleanSetting);
        }

        public static boolean isRuntimeModifiable(AbstractBooleanSetting abstractBooleanSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractBooleanSetting);
        }

        public static boolean isSaveable(AbstractBooleanSetting abstractBooleanSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractBooleanSetting);
        }

        public static boolean isSwitchable(AbstractBooleanSetting abstractBooleanSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractBooleanSetting);
        }

        public static void setGlobal(AbstractBooleanSetting abstractBooleanSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractBooleanSetting, z);
        }
    }

    boolean getBoolean(boolean z);

    void setBoolean(boolean z);
}
