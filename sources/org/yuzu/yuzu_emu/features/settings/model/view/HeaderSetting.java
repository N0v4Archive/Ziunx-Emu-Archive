package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class HeaderSetting extends SettingsItem {
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderSetting(int i, String titleString) {
        super(SettingsItem.Companion.getEmptySetting(), i, titleString, 0, "");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
    }

    public /* synthetic */ HeaderSetting(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }
}
