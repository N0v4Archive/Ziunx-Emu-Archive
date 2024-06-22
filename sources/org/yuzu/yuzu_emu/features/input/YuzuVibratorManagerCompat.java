package org.yuzu.yuzu_emu.features.input;

import android.os.VibrationEffect;
import android.os.Vibrator;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class YuzuVibratorManagerCompat implements YuzuVibrator {
    private final Vibrator vibrator;

    public YuzuVibratorManagerCompat(Vibrator vibrator) {
        Intrinsics.checkNotNullParameter(vibrator, "vibrator");
        this.vibrator = vibrator;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuVibrator
    public boolean supportsVibration() {
        return this.vibrator.hasVibrator();
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuVibrator
    public void vibrate(float f) {
        VibrationEffect vibrationEffect = YuzuVibrator.Companion.getVibrationEffect(f);
        if (vibrationEffect == null) {
            return;
        }
        this.vibrator.vibrate(vibrationEffect);
    }
}
