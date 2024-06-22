package org.yuzu.yuzu_emu.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class Installable {
    private final int descriptionId;
    private final Function0 export;
    private final Function0 install;
    private final int titleId;

    public Installable(int i, int i2, Function0 function0, Function0 function02) {
        this.titleId = i;
        this.descriptionId = i2;
        this.install = function0;
        this.export = function02;
    }

    public /* synthetic */ Installable(int i, int i2, Function0 function0, Function0 function02, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? null : function0, (i3 & 8) != 0 ? null : function02);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Installable)) {
            return false;
        }
        Installable installable = (Installable) obj;
        return this.titleId == installable.titleId && this.descriptionId == installable.descriptionId && Intrinsics.areEqual(this.install, installable.install) && Intrinsics.areEqual(this.export, installable.export);
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final Function0 getExport() {
        return this.export;
    }

    public final Function0 getInstall() {
        return this.install;
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31;
        Function0 function0 = this.install;
        int hashCode2 = (hashCode + (function0 == null ? 0 : function0.hashCode())) * 31;
        Function0 function02 = this.export;
        return hashCode2 + (function02 != null ? function02.hashCode() : 0);
    }

    public String toString() {
        return "Installable(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", install=" + this.install + ", export=" + this.export + ")";
    }
}
