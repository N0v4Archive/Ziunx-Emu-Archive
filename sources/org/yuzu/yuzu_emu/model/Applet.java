package org.yuzu.yuzu_emu.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class Applet {
    private final AppletInfo appletInfo;
    private final CabinetMode cabinetMode;
    private final int descriptionId;
    private final int iconId;
    private final int titleId;

    public Applet(int i, int i2, int i3, AppletInfo appletInfo, CabinetMode cabinetMode) {
        Intrinsics.checkNotNullParameter(appletInfo, "appletInfo");
        Intrinsics.checkNotNullParameter(cabinetMode, "cabinetMode");
        this.titleId = i;
        this.descriptionId = i2;
        this.iconId = i3;
        this.appletInfo = appletInfo;
        this.cabinetMode = cabinetMode;
    }

    public /* synthetic */ Applet(int i, int i2, int i3, AppletInfo appletInfo, CabinetMode cabinetMode, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, appletInfo, (i4 & 16) != 0 ? CabinetMode.None : cabinetMode);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Applet)) {
            return false;
        }
        Applet applet = (Applet) obj;
        return this.titleId == applet.titleId && this.descriptionId == applet.descriptionId && this.iconId == applet.iconId && this.appletInfo == applet.appletInfo && this.cabinetMode == applet.cabinetMode;
    }

    public final AppletInfo getAppletInfo() {
        return this.appletInfo;
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.iconId)) * 31) + this.appletInfo.hashCode()) * 31) + this.cabinetMode.hashCode();
    }

    public String toString() {
        return "Applet(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", iconId=" + this.iconId + ", appletInfo=" + this.appletInfo + ", cabinetMode=" + this.cabinetMode + ")";
    }
}
