package org.yuzu.yuzu_emu.features.input;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.model.ButtonName;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.features.input.model.NpadStyleIndex;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class NativeInput {
    public static final NativeInput INSTANCE = new NativeInput();

    private NativeInput() {
    }

    public static /* synthetic */ void connectControllers$default(NativeInput nativeInput, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        nativeInput.connectControllers(i, z);
    }

    private final native void connectControllersImpl(boolean[] zArr);

    private final native int getButtonNameImpl(String str);

    private final native String getButtonParamImpl(int i, int i2);

    private final native String getStickParamImpl(int i, int i2);

    private final native int getStyleIndexImpl(int i);

    private final native int[] getSupportedStyleTagsImpl(int i);

    private final native boolean isControllerImpl(String str);

    private final native void onOverlayButtonEventImpl(int i, int i2, int i3);

    private final native void onOverlayJoystickEventImpl(int i, int i2, float f, float f2);

    private final native void setButtonParamImpl(int i, int i2, String str);

    private final native void setStickParamImpl(int i, int i2, String str);

    private final native void setStyleIndexImpl(int i, int i2);

    private final native void updateMappingsWithDefaultImpl(int i, String str, String str2);

    public final native void beginMapping(int i);

    public final void connectControllers(int i, boolean z) {
        boolean[] booleanArray;
        ArrayList arrayList = new ArrayList();
        if (z) {
            int i2 = 0;
            while (i2 < 8) {
                arrayList.add(Boolean.valueOf(i2 <= i));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i3 < 8) {
                arrayList.add(Boolean.valueOf(i3 < i));
                i3++;
            }
        }
        booleanArray = CollectionsKt___CollectionsKt.toBooleanArray(arrayList);
        connectControllersImpl(booleanArray);
    }

    public final native boolean createProfile(String str, int i);

    public final native boolean deleteProfile(String str, int i);

    public final ButtonName getButtonName(ParamPackage param) {
        Intrinsics.checkNotNullParameter(param, "param");
        return ButtonName.Companion.from(getButtonNameImpl(param.serialize()));
    }

    public final ParamPackage getButtonParam(int i, NativeButton button) {
        Intrinsics.checkNotNullParameter(button, "button");
        return new ParamPackage(getButtonParamImpl(i, button.getInt()));
    }

    public final native String[] getInputDevices();

    public final native String[] getInputProfileNames();

    public final native boolean getIsConnected(int i);

    public final native String getNextInput();

    public final ParamPackage getStickParam(int i, NativeAnalog stick) {
        Intrinsics.checkNotNullParameter(stick, "stick");
        return new ParamPackage(getStickParamImpl(i, stick.getInt()));
    }

    public final NpadStyleIndex getStyleIndex(int i) {
        return NpadStyleIndex.Companion.from(getStyleIndexImpl(i));
    }

    public final List getSupportedStyleTags(int i) {
        int[] supportedStyleTagsImpl = getSupportedStyleTagsImpl(i);
        ArrayList arrayList = new ArrayList(supportedStyleTagsImpl.length);
        for (int i2 : supportedStyleTagsImpl) {
            arrayList.add(NpadStyleIndex.Companion.from(i2));
        }
        return arrayList;
    }

    public final boolean isController(ParamPackage params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return isControllerImpl(params.serialize());
    }

    public final native boolean isProfileNameValid(String str);

    public final native void loadInputProfiles();

    public final native void loadPerGameConfiguration(int i, int i2, String str);

    public final native boolean loadProfile(String str, int i);

    public final native void onDeviceMotionEvent(int i, long j, float f, float f2, float f3, float f4, float f5, float f6);

    public final native void onGamePadAxisEvent(String str, int i, int i2, float f);

    public final native void onGamePadButtonEvent(String str, int i, int i2, int i3);

    public final void onOverlayButtonEvent(int i, NativeButton button, int i2) {
        Intrinsics.checkNotNullParameter(button, "button");
        onOverlayButtonEventImpl(i, button.getInt(), i2);
    }

    public final void onOverlayJoystickEvent(int i, NativeAnalog stick, float f, float f2) {
        Intrinsics.checkNotNullParameter(stick, "stick");
        onOverlayJoystickEventImpl(i, stick.getInt(), f, f2);
    }

    public final native void onReadNfcTag(byte[] bArr);

    public final native void onRemoveNfcTag();

    public final native void onTouchMoved(int i, float f, float f2);

    public final native void onTouchPressed(int i, float f, float f2);

    public final native void onTouchReleased(int i);

    public final native void registerController(YuzuInputDevice yuzuInputDevice);

    public final native void reloadInputDevices();

    public final native void resetControllerMappings(int i);

    public final native boolean saveProfile(String str, int i);

    public final void setButtonParam(int i, NativeButton button, ParamPackage param) {
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(param, "param");
        setButtonParamImpl(i, button.getInt(), param.serialize());
    }

    public final void setStickParam(int i, NativeAnalog stick, ParamPackage param) {
        Intrinsics.checkNotNullParameter(stick, "stick");
        Intrinsics.checkNotNullParameter(param, "param");
        setStickParamImpl(i, stick.getInt(), param.serialize());
    }

    public final void setStyleIndex(int i, NpadStyleIndex style) {
        Intrinsics.checkNotNullParameter(style, "style");
        setStyleIndexImpl(i, style.getInt());
    }

    public final native void stopMapping();

    public final void updateMappingsWithDefault(int i, ParamPackage deviceParams, String displayName) {
        Intrinsics.checkNotNullParameter(deviceParams, "deviceParams");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        updateMappingsWithDefaultImpl(i, deviceParams.serialize(), displayName);
    }
}
