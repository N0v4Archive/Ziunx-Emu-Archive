package org.yuzu.yuzu_emu.features.input;

import androidx.annotation.Keep;
import kotlin.jvm.internal.Intrinsics;

@Keep
/* loaded from: classes.dex */
public interface YuzuInputDevice {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static Integer[] getAxes(YuzuInputDevice yuzuInputDevice) {
            return new Integer[0];
        }

        public static boolean[] hasKeys(YuzuInputDevice yuzuInputDevice, int[] keys) {
            Intrinsics.checkNotNullParameter(keys, "keys");
            return new boolean[0];
        }
    }

    Integer[] getAxes();

    String getGUID();

    String getName();

    int getPort();

    boolean getSupportsVibration();

    boolean[] hasKeys(int[] iArr);

    void vibrate(float f);
}
