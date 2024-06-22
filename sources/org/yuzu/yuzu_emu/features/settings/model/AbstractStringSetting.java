package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractStringSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractStringSetting abstractStringSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractStringSetting);
        }

        public static String getPairedSettingKey(AbstractStringSetting abstractStringSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractStringSetting);
        }

        public static /* synthetic */ String getString$default(AbstractStringSetting abstractStringSetting, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getString");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return abstractStringSetting.getString(z);
        }

        public static boolean isRuntimeModifiable(AbstractStringSetting abstractStringSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractStringSetting);
        }

        public static boolean isSaveable(AbstractStringSetting abstractStringSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractStringSetting);
        }

        public static boolean isSwitchable(AbstractStringSetting abstractStringSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractStringSetting);
        }

        public static void setGlobal(AbstractStringSetting abstractStringSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractStringSetting, z);
        }
    }

    String getString(boolean z);

    void setString(String str);
}
