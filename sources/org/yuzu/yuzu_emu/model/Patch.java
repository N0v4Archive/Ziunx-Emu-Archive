package org.yuzu.yuzu_emu.model;

import androidx.annotation.Keep;
import kotlin.jvm.internal.Intrinsics;

@Keep
/* loaded from: classes.dex */
public final class Patch {
    private boolean enabled;
    private final String name;
    private final String programId;
    private final String titleId;
    private final int type;
    private final String version;

    public Patch(boolean z, String name, String version, int i, String programId, String titleId) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(programId, "programId");
        Intrinsics.checkNotNullParameter(titleId, "titleId");
        this.enabled = z;
        this.name = name;
        this.version = version;
        this.type = i;
        this.programId = programId;
        this.titleId = titleId;
    }

    public static /* synthetic */ Patch copy$default(Patch patch, boolean z, String str, String str2, int i, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = patch.enabled;
        }
        if ((i2 & 2) != 0) {
            str = patch.name;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = patch.version;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            i = patch.type;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str3 = patch.programId;
        }
        String str7 = str3;
        if ((i2 & 32) != 0) {
            str4 = patch.titleId;
        }
        return patch.copy(z, str5, str6, i3, str7, str4);
    }

    public final boolean component1() {
        return this.enabled;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.version;
    }

    public final int component4() {
        return this.type;
    }

    public final String component5() {
        return this.programId;
    }

    public final String component6() {
        return this.titleId;
    }

    public final Patch copy(boolean z, String name, String version, int i, String programId, String titleId) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(programId, "programId");
        Intrinsics.checkNotNullParameter(titleId, "titleId");
        return new Patch(z, name, version, i, programId, titleId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Patch)) {
            return false;
        }
        Patch patch = (Patch) obj;
        return this.enabled == patch.enabled && Intrinsics.areEqual(this.name, patch.name) && Intrinsics.areEqual(this.version, patch.version) && this.type == patch.type && Intrinsics.areEqual(this.programId, patch.programId) && Intrinsics.areEqual(this.titleId, patch.titleId);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getName() {
        return this.name;
    }

    public final String getProgramId() {
        return this.programId;
    }

    public final String getTitleId() {
        return this.titleId;
    }

    public final int getType() {
        return this.type;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((Boolean.hashCode(this.enabled) * 31) + this.name.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.type)) * 31) + this.programId.hashCode()) * 31) + this.titleId.hashCode();
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public String toString() {
        return "Patch(enabled=" + this.enabled + ", name=" + this.name + ", version=" + this.version + ", type=" + this.type + ", programId=" + this.programId + ", titleId=" + this.titleId + ")";
    }
}
