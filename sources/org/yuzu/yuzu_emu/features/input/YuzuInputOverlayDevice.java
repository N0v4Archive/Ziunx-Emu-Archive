package org.yuzu.yuzu_emu.features.input;

import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.input.YuzuInputDevice;

/* loaded from: classes.dex */
public final class YuzuInputOverlayDevice implements YuzuInputDevice {
    private final int port;
    private final boolean vibration;
    private final YuzuVibrator vibrator = YuzuVibrator.Companion.getSystemVibrator();

    public YuzuInputOverlayDevice(boolean z, int i) {
        this.vibration = z;
        this.port = i;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public Integer[] getAxes() {
        return YuzuInputDevice.DefaultImpls.getAxes(this);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public String getGUID() {
        return "00000000000000000000000000000000";
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public String getName() {
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.input_overlay);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public int getPort() {
        return this.port;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public boolean getSupportsVibration() {
        if (this.vibration) {
            return this.vibrator.supportsVibration();
        }
        return false;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public boolean[] hasKeys(int[] iArr) {
        return YuzuInputDevice.DefaultImpls.hasKeys(this, iArr);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public void vibrate(float f) {
        if (this.vibration) {
            this.vibrator.vibrate(f);
        }
    }
}
