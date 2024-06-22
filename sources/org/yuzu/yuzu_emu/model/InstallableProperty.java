package org.yuzu.yuzu_emu.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class InstallableProperty implements GameProperty {
    private final int descriptionId;
    private final Function0 export;
    private final int iconId;
    private final Function0 install;
    private final int titleId;

    public InstallableProperty(int i, int i2, int i3, Function0 function0, Function0 function02) {
        this.titleId = i;
        this.descriptionId = i2;
        this.iconId = i3;
        this.install = function0;
        this.export = function02;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InstallableProperty)) {
            return false;
        }
        InstallableProperty installableProperty = (InstallableProperty) obj;
        return this.titleId == installableProperty.titleId && this.descriptionId == installableProperty.descriptionId && this.iconId == installableProperty.iconId && Intrinsics.areEqual(this.install, installableProperty.install) && Intrinsics.areEqual(this.export, installableProperty.export);
    }

    public int getDescriptionId() {
        return this.descriptionId;
    }

    public final Function0 getExport() {
        return this.export;
    }

    public int getIconId() {
        return this.iconId;
    }

    public final Function0 getInstall() {
        return this.install;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.iconId)) * 31;
        Function0 function0 = this.install;
        int hashCode2 = (hashCode + (function0 == null ? 0 : function0.hashCode())) * 31;
        Function0 function02 = this.export;
        return hashCode2 + (function02 != null ? function02.hashCode() : 0);
    }

    public String toString() {
        return "InstallableProperty(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", iconId=" + this.iconId + ", install=" + this.install + ", export=" + this.export + ")";
    }
}
