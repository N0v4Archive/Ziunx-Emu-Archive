package org.yuzu.yuzu_emu.utils;

/* loaded from: classes.dex */
public final class GameMetadata {
    public static final GameMetadata INSTANCE = new GameMetadata();

    private GameMetadata() {
    }

    public final native String getDeveloper(String str);

    public final native byte[] getIcon(String str);

    public final native boolean getIsHomebrew(String str);

    public final native boolean getIsValid(String str);

    public final native String getProgramId(String str);

    public final native String getTitle(String str);

    public final native String getVersion(String str, boolean z);

    public final native void resetMetadata();
}
