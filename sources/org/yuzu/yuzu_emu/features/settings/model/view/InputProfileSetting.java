package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class InputProfileSetting extends SettingsItem {
    private final int playerIndex;
    private final int type;

    public InputProfileSetting(int i) {
        super(SettingsItem.Companion.getEmptySetting(), R$string.profile, "", 0, "");
        this.playerIndex = i;
        this.type = 10;
    }

    public final boolean createProfile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return NativeInput.INSTANCE.createProfile(name, this.playerIndex);
    }

    public final boolean deleteProfile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return NativeInput.INSTANCE.deleteProfile(name, this.playerIndex);
    }

    public final String getCurrentProfile() {
        return NativeConfig.INSTANCE.getInputSettings(true)[this.playerIndex].getProfileName();
    }

    public final String[] getProfileNames() {
        return NativeInput.INSTANCE.getInputProfileNames();
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final boolean isProfileNameValid(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return NativeInput.INSTANCE.isProfileNameValid(name);
    }

    public final boolean loadProfile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        NativeInput nativeInput = NativeInput.INSTANCE;
        boolean loadProfile = nativeInput.loadProfile(name, this.playerIndex);
        nativeInput.reloadInputDevices();
        return loadProfile;
    }

    public final boolean saveProfile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return NativeInput.INSTANCE.saveProfile(name, this.playerIndex);
    }
}
