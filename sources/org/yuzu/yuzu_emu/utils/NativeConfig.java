package org.yuzu.yuzu_emu.utils;

import org.yuzu.yuzu_emu.features.input.model.PlayerInput;
import org.yuzu.yuzu_emu.model.GameDir;
import org.yuzu.yuzu_emu.overlay.model.OverlayControlData;

/* loaded from: classes.dex */
public final class NativeConfig {
    public static final NativeConfig INSTANCE = new NativeConfig();

    private NativeConfig() {
    }

    public final native synchronized void addGameDir(GameDir gameDir);

    public final native synchronized boolean getBoolean(String str, boolean z);

    public final native synchronized byte getByte(String str, boolean z);

    public final native String getDefaultToString(String str);

    public final native synchronized GameDir[] getGameDirs();

    public final native synchronized PlayerInput[] getInputSettings(boolean z);

    public final native synchronized int getInt(String str, boolean z);

    public final native boolean getIsRuntimeModifiable(String str);

    public final native boolean getIsSaveable(String str);

    public final native boolean getIsSwitchable(String str);

    public final native synchronized long getLong(String str, boolean z);

    public final native synchronized OverlayControlData[] getOverlayControlData();

    public final native String getPairedSettingKey(String str);

    public final native synchronized short getShort(String str, boolean z);

    public final native synchronized String getString(String str, boolean z);

    public final native synchronized void initializeGlobalConfig();

    public final native synchronized void initializePerGameConfig(String str, String str2);

    public final native synchronized boolean isPerGameConfigLoaded();

    public final native synchronized void reloadGlobalConfig();

    public final native synchronized void saveControlPlayerValues();

    public final native synchronized void saveGlobalConfig();

    public final native synchronized void savePerGameConfig();

    public final native synchronized void setBoolean(String str, boolean z);

    public final native synchronized void setByte(String str, byte b);

    public final native synchronized void setDisabledAddons(String str, String[] strArr);

    public final native synchronized void setGameDirs(GameDir[] gameDirArr);

    public final native synchronized void setGlobal(String str, boolean z);

    public final native synchronized void setInputSettings(PlayerInput[] playerInputArr, boolean z);

    public final native synchronized void setInt(String str, int i);

    public final native synchronized void setLong(String str, long j);

    public final native synchronized void setOverlayControlData(OverlayControlData[] overlayControlDataArr);

    public final native synchronized void setShort(String str, short s);

    public final native synchronized void setString(String str, String str2);

    public final native synchronized void unloadGlobalConfig();

    public final native synchronized void unloadPerGameConfig();

    public final native synchronized boolean usingGlobal(String str);
}
