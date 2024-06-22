package org.yuzu.yuzu_emu.utils;

import android.os.Build;

/* loaded from: classes.dex */
public final class Log {
    public static final Log INSTANCE = new Log();
    private static boolean gameLaunched;

    private Log() {
    }

    public final native void debug(String str);

    public final native void error(String str);

    public final boolean getGameLaunched() {
        return gameLaunched;
    }

    public final native void info(String str);

    public final void logDeviceInfo() {
        String str;
        String str2;
        info("Device Manufacturer - " + Build.MANUFACTURER);
        info("Device Model - " + Build.MODEL);
        if (Build.VERSION.SDK_INT > 30) {
            str = Build.SOC_MANUFACTURER;
            info("SoC Manufacturer - " + str);
            str2 = Build.SOC_MODEL;
            info("SoC Model - " + str2);
        }
        info("Total System Memory - " + MemoryUtil.INSTANCE.getDeviceRAM());
    }

    public final void setGameLaunched(boolean z) {
        gameLaunched = z;
    }

    public final native void warning(String str);
}
