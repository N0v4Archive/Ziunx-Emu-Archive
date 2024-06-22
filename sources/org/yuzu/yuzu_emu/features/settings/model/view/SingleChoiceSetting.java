package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public final class SingleChoiceSetting extends SettingsItem {
    private final int choicesId;
    private final int type;
    private final int valuesId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleChoiceSetting(AbstractSetting setting, int i, String titleString, int i2, String descriptionString, int i3, int i4) {
        super(setting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(setting, "setting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        this.choicesId = i3;
        this.valuesId = i4;
        this.type = 2;
    }

    public /* synthetic */ SingleChoiceSetting(AbstractSetting abstractSetting, int i, String str, int i2, String str2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractSetting, (i5 & 2) != 0 ? 0 : i, (i5 & 4) != 0 ? "" : str, (i5 & 8) != 0 ? 0 : i2, (i5 & 16) != 0 ? "" : str2, i3, i4);
    }

    public static /* synthetic */ int getSelectedValue$default(SingleChoiceSetting singleChoiceSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return singleChoiceSetting.getSelectedValue(z);
    }

    public final int getChoicesId() {
        return this.choicesId;
    }

    public final int getSelectedValue(boolean z) {
        if (getSetting() instanceof AbstractIntSetting) {
            return ((AbstractIntSetting) getSetting()).getInt(z);
        }
        return -1;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final int getValuesId() {
        return this.valuesId;
    }

    public final void setSelectedValue(int i) {
        AbstractSetting setting = getSetting();
        Intrinsics.checkNotNull(setting, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting");
        ((AbstractIntSetting) setting).setInt(i);
    }
}
