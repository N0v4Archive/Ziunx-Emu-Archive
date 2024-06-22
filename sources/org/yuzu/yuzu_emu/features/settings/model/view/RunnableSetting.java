package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class RunnableSetting extends SettingsItem {
    private final int iconId;
    private final boolean isRunnable;
    private final Function0 runnable;
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RunnableSetting(int i, String titleString, int i2, String descriptionString, boolean z, int i3, Function0 runnable) {
        super(SettingsItem.Companion.getEmptySetting(), i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.isRunnable = z;
        this.iconId = i3;
        this.runnable = runnable;
        this.type = 7;
    }

    public /* synthetic */ RunnableSetting(int i, String str, int i2, String str2, boolean z, int i3, Function0 function0, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? "" : str, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? "" : str2, z, (i4 & 32) != 0 ? 0 : i3, function0);
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final Function0 getRunnable() {
        return this.runnable;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final boolean isRunnable() {
        return this.isRunnable;
    }
}
