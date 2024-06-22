package org.yuzu.yuzu_emu.features.settings.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ExistingProfileItem implements ProfileItem {
    private final Function0 deleteProfile;
    private final Function0 loadProfile;
    private final String name;
    private final Function0 saveProfile;

    public ExistingProfileItem(String name, Function0 deleteProfile, Function0 saveProfile, Function0 loadProfile) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(deleteProfile, "deleteProfile");
        Intrinsics.checkNotNullParameter(saveProfile, "saveProfile");
        Intrinsics.checkNotNullParameter(loadProfile, "loadProfile");
        this.name = name;
        this.deleteProfile = deleteProfile;
        this.saveProfile = saveProfile;
        this.loadProfile = loadProfile;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExistingProfileItem)) {
            return false;
        }
        ExistingProfileItem existingProfileItem = (ExistingProfileItem) obj;
        return Intrinsics.areEqual(this.name, existingProfileItem.name) && Intrinsics.areEqual(this.deleteProfile, existingProfileItem.deleteProfile) && Intrinsics.areEqual(this.saveProfile, existingProfileItem.saveProfile) && Intrinsics.areEqual(this.loadProfile, existingProfileItem.loadProfile);
    }

    public final Function0 getDeleteProfile() {
        return this.deleteProfile;
    }

    public final Function0 getLoadProfile() {
        return this.loadProfile;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.ProfileItem
    public String getName() {
        return this.name;
    }

    public final Function0 getSaveProfile() {
        return this.saveProfile;
    }

    public int hashCode() {
        return (((((this.name.hashCode() * 31) + this.deleteProfile.hashCode()) * 31) + this.saveProfile.hashCode()) * 31) + this.loadProfile.hashCode();
    }

    public String toString() {
        return "ExistingProfileItem(name=" + this.name + ", deleteProfile=" + this.deleteProfile + ", saveProfile=" + this.saveProfile + ", loadProfile=" + this.loadProfile + ")";
    }
}
