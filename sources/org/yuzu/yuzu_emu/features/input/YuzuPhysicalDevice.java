package org.yuzu.yuzu_emu.features.input;

import android.view.InputDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.utils.InputHandler;

/* loaded from: classes.dex */
public final class YuzuPhysicalDevice implements YuzuInputDevice {
    private final InputDevice device;
    private final int port;
    private final YuzuVibrator vibrator;

    public YuzuPhysicalDevice(InputDevice device, int i, boolean z) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.device = device;
        this.port = i;
        this.vibrator = z ? YuzuVibrator.Companion.getSystemVibrator() : YuzuVibrator.Companion.getControllerVibrator(device);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public Integer[] getAxes() {
        int collectionSizeOrDefault;
        List<InputDevice.MotionRange> motionRanges = this.device.getMotionRanges();
        Intrinsics.checkNotNullExpressionValue(motionRanges, "getMotionRanges(...)");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(motionRanges, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it = motionRanges.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((InputDevice.MotionRange) it.next()).getAxis()));
        }
        return (Integer[]) arrayList.toArray(new Integer[0]);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public String getGUID() {
        return InputHandler.INSTANCE.getGUID(this.device);
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public String getName() {
        String name = this.device.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public int getPort() {
        return this.port;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public boolean getSupportsVibration() {
        return this.vibrator.supportsVibration();
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public boolean[] hasKeys(int[] keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        boolean[] hasKeys = this.device.hasKeys(Arrays.copyOf(keys, keys.length));
        Intrinsics.checkNotNullExpressionValue(hasKeys, "hasKeys(...)");
        return hasKeys;
    }

    @Override // org.yuzu.yuzu_emu.features.input.YuzuInputDevice
    public void vibrate(float f) {
        this.vibrator.vibrate(f);
    }
}
