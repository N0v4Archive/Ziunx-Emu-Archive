package org.yuzu.yuzu_emu.utils;

import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.YuzuInputDevice;
import org.yuzu.yuzu_emu.features.input.YuzuInputOverlayDevice;
import org.yuzu.yuzu_emu.features.input.YuzuPhysicalDevice;
import org.yuzu.yuzu_emu.features.input.model.PlayerInput;

/* loaded from: classes.dex */
public final class InputHandler {
    public static final InputHandler INSTANCE = new InputHandler();
    private static Map androidControllers;
    private static List registeredControllers;

    static {
        Map emptyMap;
        emptyMap = MapsKt__MapsKt.emptyMap();
        androidControllers = emptyMap;
        registeredControllers = new ArrayList();
    }

    private InputHandler() {
    }

    public final boolean dispatchGenericMotionEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        YuzuPhysicalDevice yuzuPhysicalDevice = (YuzuPhysicalDevice) androidControllers.get(Integer.valueOf(event.getDevice().getControllerNumber()));
        if (yuzuPhysicalDevice == null) {
            return false;
        }
        List<InputDevice.MotionRange> motionRanges = event.getDevice().getMotionRanges();
        Intrinsics.checkNotNullExpressionValue(motionRanges, "getMotionRanges(...)");
        for (InputDevice.MotionRange motionRange : motionRanges) {
            NativeInput.INSTANCE.onGamePadAxisEvent(yuzuPhysicalDevice.getGUID(), yuzuPhysicalDevice.getPort(), motionRange.getAxis(), event.getAxisValue(motionRange.getAxis()));
        }
        return true;
    }

    public final boolean dispatchKeyEvent(KeyEvent event) {
        int i;
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        if (action == 0) {
            i = 1;
        } else {
            if (action != 1) {
                return false;
            }
            i = 0;
        }
        YuzuPhysicalDevice yuzuPhysicalDevice = (YuzuPhysicalDevice) androidControllers.get(Integer.valueOf(event.getDevice().getControllerNumber()));
        if (yuzuPhysicalDevice == null) {
            updateControllerData();
            yuzuPhysicalDevice = (YuzuPhysicalDevice) androidControllers.get(Integer.valueOf(event.getDevice().getControllerNumber()));
            if (yuzuPhysicalDevice == null) {
                return false;
            }
        }
        NativeInput.INSTANCE.onGamePadButtonEvent(yuzuPhysicalDevice.getGUID(), yuzuPhysicalDevice.getPort(), event.getKeyCode(), i);
        return true;
    }

    public final Map getAndroidControllers() {
        return androidControllers;
    }

    public final Map getDevices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int[] deviceIds = InputDevice.getDeviceIds();
        PlayerInput[] inputSettings = NativeConfig.INSTANCE.getInputSettings(true);
        Intrinsics.checkNotNull(deviceIds);
        int i = 0;
        for (int i2 : deviceIds) {
            InputDevice device = InputDevice.getDevice(i2);
            if (device != null && ((device.getSources() & 1025) == 1025 || (device.getSources() & 16777232) == 16777232)) {
                if (!linkedHashMap.containsKey(Integer.valueOf(device.getControllerNumber()))) {
                    Integer valueOf = Integer.valueOf(device.getControllerNumber());
                    Intrinsics.checkNotNull(device);
                    linkedHashMap.put(valueOf, new YuzuPhysicalDevice(device, i, inputSettings[i].getUseSystemVibrator()));
                }
                i++;
            }
        }
        return linkedHashMap;
    }

    public final String getGUID(InputDevice inputDevice) {
        Intrinsics.checkNotNullParameter(inputDevice, "<this>");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%016x%016x", Arrays.copyOf(new Object[]{Integer.valueOf(inputDevice.getProductId()), Integer.valueOf(inputDevice.getVendorId())}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final List getRegisteredControllers() {
        return registeredControllers;
    }

    public final void updateControllerData() {
        Map devices = getDevices();
        androidControllers = devices;
        Iterator it = devices.entrySet().iterator();
        while (it.hasNext()) {
            NativeInput.INSTANCE.registerController((YuzuInputDevice) ((Map.Entry) it.next()).getValue());
        }
        NativeInput nativeInput = NativeInput.INSTANCE;
        nativeInput.registerController(new YuzuInputOverlayDevice(androidControllers.isEmpty(), 100));
        registeredControllers.clear();
        for (String str : nativeInput.getInputDevices()) {
            registeredControllers.add(new ParamPackage(str));
        }
        List list = registeredControllers;
        if (list.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new Comparator() { // from class: org.yuzu.yuzu_emu.utils.InputHandler$updateControllerData$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((ParamPackage) obj).get("port", 0)), Integer.valueOf(((ParamPackage) obj2).get("port", 0)));
                    return compareValues;
                }
            });
        }
    }
}
