package org.yuzu.yuzu_emu.features.settings.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;

/* loaded from: classes.dex */
public final class NewProfileItem implements ProfileItem {
    private final Function0 createNewProfile;
    private final String name;

    public NewProfileItem(Function0 createNewProfile) {
        Intrinsics.checkNotNullParameter(createNewProfile, "createNewProfile");
        this.createNewProfile = createNewProfile;
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.create_new_profile);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.name = string;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof NewProfileItem) && Intrinsics.areEqual(this.createNewProfile, ((NewProfileItem) obj).createNewProfile);
    }

    public final Function0 getCreateNewProfile() {
        return this.createNewProfile;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.ProfileItem
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.createNewProfile.hashCode();
    }

    public String toString() {
        return "NewProfileItem(createNewProfile=" + this.createNewProfile + ")";
    }
}
