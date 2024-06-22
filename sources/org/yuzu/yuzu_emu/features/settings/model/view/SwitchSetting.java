package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public final class SwitchSetting extends SettingsItem {
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchSetting(AbstractSetting setting, int i, String titleString, int i2, String descriptionString) {
        super(setting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(setting, "setting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        this.type = 1;
    }

    public /* synthetic */ SwitchSetting(AbstractSetting abstractSetting, int i, String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractSetting, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "" : str2);
    }

    public final boolean getIsChecked(boolean z) {
        AbstractSetting setting = getSetting();
        if (setting instanceof AbstractIntSetting) {
            return ((AbstractIntSetting) getSetting()).getInt(z) == 1;
        }
        if (setting instanceof AbstractBooleanSetting) {
            return ((AbstractBooleanSetting) getSetting()).getBoolean(z);
        }
        return false;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final void setChecked(boolean z) {
        AbstractSetting setting = getSetting();
        if (setting instanceof AbstractIntSetting) {
            ((AbstractIntSetting) getSetting()).setInt(z ? 1 : 0);
        } else if (setting instanceof AbstractBooleanSetting) {
            ((AbstractBooleanSetting) getSetting()).setBoolean(z);
        }
    }
}
