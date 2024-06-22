package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting;

/* loaded from: classes.dex */
public final class StringInputSetting extends SettingsItem {
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringInputSetting(AbstractStringSetting setting, int i, String titleString, int i2, String descriptionString) {
        super(setting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(setting, "setting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        this.type = 11;
    }

    public /* synthetic */ StringInputSetting(AbstractStringSetting abstractStringSetting, int i, String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractStringSetting, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "" : str2);
    }

    public static /* synthetic */ String getSelectedValue$default(StringInputSetting stringInputSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return stringInputSetting.getSelectedValue(z);
    }

    public final String getSelectedValue(boolean z) {
        return getSetting().getValueAsString(z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final void setSelectedValue(String selection) {
        Intrinsics.checkNotNullParameter(selection, "selection");
        AbstractSetting setting = getSetting();
        Intrinsics.checkNotNull(setting, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting");
        ((AbstractStringSetting) setting).setString(selection);
    }
}
