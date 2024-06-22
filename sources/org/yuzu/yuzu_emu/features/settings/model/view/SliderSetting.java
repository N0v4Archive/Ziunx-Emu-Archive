package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractByteSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractShortSetting;

/* loaded from: classes.dex */
public final class SliderSetting extends SettingsItem {
    private final int max;
    private final int min;
    private final int type;
    private final String units;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SliderSetting(AbstractSetting setting, int i, String titleString, int i2, String descriptionString, int i3, int i4, String units) {
        super(setting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(setting, "setting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        Intrinsics.checkNotNullParameter(units, "units");
        this.min = i3;
        this.max = i4;
        this.units = units;
        this.type = 3;
    }

    public /* synthetic */ SliderSetting(AbstractSetting abstractSetting, int i, String str, int i2, String str2, int i3, int i4, String str3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractSetting, (i5 & 2) != 0 ? 0 : i, (i5 & 4) != 0 ? "" : str, (i5 & 8) != 0 ? 0 : i2, (i5 & 16) != 0 ? "" : str2, (i5 & 32) == 0 ? i3 : 0, (i5 & 64) != 0 ? 100 : i4, (i5 & 128) == 0 ? str3 : "");
    }

    public static /* synthetic */ int getSelectedValue$default(SliderSetting sliderSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return sliderSetting.getSelectedValue(z);
    }

    public final int getMax() {
        return this.max;
    }

    public final int getMin() {
        return this.min;
    }

    public final int getSelectedValue(boolean z) {
        AbstractSetting setting = getSetting();
        if (setting instanceof AbstractByteSetting) {
            return ((AbstractByteSetting) getSetting()).getByte(z);
        }
        if (setting instanceof AbstractShortSetting) {
            return ((AbstractShortSetting) getSetting()).getShort(z);
        }
        if (setting instanceof AbstractIntSetting) {
            return ((AbstractIntSetting) getSetting()).getInt(z);
        }
        return -1;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final String getUnits() {
        return this.units;
    }

    public final void setSelectedValue(int i) {
        AbstractSetting setting = getSetting();
        if (setting instanceof AbstractByteSetting) {
            ((AbstractByteSetting) getSetting()).setByte((byte) i);
            return;
        }
        boolean z = setting instanceof AbstractShortSetting;
        AbstractSetting setting2 = getSetting();
        if (z) {
            ((AbstractShortSetting) setting2).setShort((short) i);
        } else {
            Intrinsics.checkNotNull(setting2, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting");
            ((AbstractIntSetting) setting2).setInt(i);
        }
    }
}
