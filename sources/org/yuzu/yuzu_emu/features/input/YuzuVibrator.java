package org.yuzu.yuzu_emu.features.input;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.InputDevice;
import androidx.annotation.Keep;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.yuzu.yuzu_emu.YuzuApplication;

@Keep
/* loaded from: classes.dex */
public interface YuzuVibrator {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final YuzuVibrator getControllerVibrator(InputDevice device) {
            VibratorManager vibratorManager;
            Intrinsics.checkNotNullParameter(device, "device");
            if (Build.VERSION.SDK_INT >= 31) {
                vibratorManager = device.getVibratorManager();
                Intrinsics.checkNotNullExpressionValue(vibratorManager, "getVibratorManager(...)");
                return new YuzuVibratorManager(vibratorManager);
            }
            Vibrator vibrator = device.getVibrator();
            Intrinsics.checkNotNullExpressionValue(vibrator, "getVibrator(...)");
            return new YuzuVibratorManagerCompat(vibrator);
        }

        public final YuzuVibrator getSystemVibrator() {
            if (Build.VERSION.SDK_INT >= 31) {
                Object systemService = YuzuApplication.Companion.getAppContext().getSystemService("vibrator_manager");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.VibratorManager");
                return new YuzuVibratorManager(YuzuVibrator$Companion$$ExternalSyntheticApiModelOutline1.m(systemService));
            }
            Object systemService2 = YuzuApplication.Companion.getAppContext().getSystemService("vibrator");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.Vibrator");
            return new YuzuVibratorManagerCompat((Vibrator) systemService2);
        }

        public final VibrationEffect getVibrationEffect(float f) {
            int coerceIn;
            if (f <= 0.0f) {
                return null;
            }
            coerceIn = RangesKt___RangesKt.coerceIn((int) (f * 255.0d), 1, 255);
            return VibrationEffect.createOneShot(50L, coerceIn);
        }
    }

    boolean supportsVibration();

    void vibrate(float f);
}
