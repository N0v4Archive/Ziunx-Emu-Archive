package org.yuzu.yuzu_emu.features.input;

import android.os.CombinedVibration;
import android.os.VibrationEffect;
import android.os.VibratorManager;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class YuzuVibratorManager implements YuzuVibrator {
    private final VibratorManager vibratorManager;

    public YuzuVibratorManager(VibratorManager vibratorManager) {
        Intrinsics.checkNotNullParameter(vibratorManager, "vibratorManager");
        this.vibratorManager = vibratorManager;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuVibrator
    public boolean supportsVibration() {
        int[] vibratorIds;
        vibratorIds = this.vibratorManager.getVibratorIds();
        Intrinsics.checkNotNullExpressionValue(vibratorIds, "getVibratorIds(...)");
        return !(vibratorIds.length == 0);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuVibrator
    public void vibrate(float f) {
        CombinedVibration createParallel;
        VibrationEffect vibrationEffect = YuzuVibrator.Companion.getVibrationEffect(f);
        if (vibrationEffect == null) {
            return;
        }
        VibratorManager vibratorManager = this.vibratorManager;
        createParallel = CombinedVibration.createParallel(vibrationEffect);
        vibratorManager.vibrate(createParallel);
    }
}
