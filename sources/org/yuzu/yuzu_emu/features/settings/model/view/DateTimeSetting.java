package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.AbstractLongSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractSetting;

/* loaded from: classes.dex */
public final class DateTimeSetting extends SettingsItem {
    private final AbstractLongSetting longSetting;
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateTimeSetting(AbstractLongSetting longSetting, int i, String titleString, int i2, String descriptionString) {
        super(longSetting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(longSetting, "longSetting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        this.longSetting = longSetting;
        this.type = 6;
    }

    public /* synthetic */ DateTimeSetting(AbstractLongSetting abstractLongSetting, int i, String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractLongSetting, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "" : str2);
    }

    public static /* synthetic */ long getValue$default(DateTimeSetting dateTimeSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return dateTimeSetting.getValue(z);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final long getValue(boolean z) {
        return this.longSetting.getLong(z);
    }

    public final void setValue(long j) {
        AbstractSetting setting = getSetting();
        Intrinsics.checkNotNull(setting, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.AbstractLongSetting");
        ((AbstractLongSetting) setting).setLong(j);
    }
}
