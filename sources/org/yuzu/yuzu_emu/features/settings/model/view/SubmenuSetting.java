package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.Settings;

/* loaded from: classes.dex */
public final class SubmenuSetting extends SettingsItem {
    private final int iconId;
    private final Settings.MenuTag menuKey;
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubmenuSetting(int i, String titleString, int i2, String descriptionString, int i3, Settings.MenuTag menuKey) {
        super(SettingsItem.Companion.getEmptySetting(), i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        Intrinsics.checkNotNullParameter(menuKey, "menuKey");
        this.iconId = i3;
        this.menuKey = menuKey;
        this.type = 4;
    }

    public /* synthetic */ SubmenuSetting(int i, String str, int i2, String str2, int i3, Settings.MenuTag menuTag, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? "" : str, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? "" : str2, (i4 & 16) != 0 ? 0 : i3, menuTag);
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final Settings.MenuTag getMenuKey() {
        return this.menuKey;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }
}
