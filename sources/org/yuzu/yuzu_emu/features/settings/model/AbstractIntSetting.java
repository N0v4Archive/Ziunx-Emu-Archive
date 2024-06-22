package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public interface AbstractIntSetting extends AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractIntSetting abstractIntSetting) {
            return AbstractSetting.DefaultImpls.getGlobal(abstractIntSetting);
        }

        public static /* synthetic */ int getInt$default(AbstractIntSetting abstractIntSetting, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInt");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return abstractIntSetting.getInt(z);
        }

        public static String getPairedSettingKey(AbstractIntSetting abstractIntSetting) {
            return AbstractSetting.DefaultImpls.getPairedSettingKey(abstractIntSetting);
        }

        public static boolean isRuntimeModifiable(AbstractIntSetting abstractIntSetting) {
            return AbstractSetting.DefaultImpls.isRuntimeModifiable(abstractIntSetting);
        }

        public static boolean isSaveable(AbstractIntSetting abstractIntSetting) {
            return AbstractSetting.DefaultImpls.isSaveable(abstractIntSetting);
        }

        public static boolean isSwitchable(AbstractIntSetting abstractIntSetting) {
            return AbstractSetting.DefaultImpls.isSwitchable(abstractIntSetting);
        }

        public static void setGlobal(AbstractIntSetting abstractIntSetting, boolean z) {
            AbstractSetting.DefaultImpls.setGlobal(abstractIntSetting, z);
        }
    }

    int getInt(boolean z);

    void setInt(int i);
}
