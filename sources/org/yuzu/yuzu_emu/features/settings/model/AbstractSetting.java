package org.yuzu.yuzu_emu.features.settings.model;

import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public interface AbstractSetting {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean getGlobal(AbstractSetting abstractSetting) {
            return NativeConfig.INSTANCE.usingGlobal(abstractSetting.getKey());
        }

        public static String getPairedSettingKey(AbstractSetting abstractSetting) {
            return NativeConfig.INSTANCE.getPairedSettingKey(abstractSetting.getKey());
        }

        public static /* synthetic */ String getValueAsString$default(AbstractSetting abstractSetting, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValueAsString");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return abstractSetting.getValueAsString(z);
        }

        public static boolean isRuntimeModifiable(AbstractSetting abstractSetting) {
            return NativeConfig.INSTANCE.getIsRuntimeModifiable(abstractSetting.getKey());
        }

        public static boolean isSaveable(AbstractSetting abstractSetting) {
            return NativeConfig.INSTANCE.getIsSaveable(abstractSetting.getKey());
        }

        public static boolean isSwitchable(AbstractSetting abstractSetting) {
            return NativeConfig.INSTANCE.getIsSwitchable(abstractSetting.getKey());
        }

        public static void setGlobal(AbstractSetting abstractSetting, boolean z) {
            NativeConfig.INSTANCE.setGlobal(abstractSetting.getKey(), z);
        }
    }

    boolean getGlobal();

    String getKey();

    String getPairedSettingKey();

    String getValueAsString(boolean z);

    boolean isRuntimeModifiable();

    boolean isSaveable();

    boolean isSwitchable();

    void reset();

    void setGlobal(boolean z);
}
