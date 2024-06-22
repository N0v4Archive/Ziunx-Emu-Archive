package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.InputType;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class ButtonInputSetting extends InputSetting {
    private final InputType inputType;
    private final NativeButton nativeButton;
    private final int playerIndex;
    private final int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ButtonInputSetting(int i, NativeButton nativeButton, int i2, String titleString) {
        super(i2, titleString, null);
        Intrinsics.checkNotNullParameter(nativeButton, "nativeButton");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        this.playerIndex = i;
        this.nativeButton = nativeButton;
        this.type = 8;
        this.inputType = InputType.Button;
    }

    public /* synthetic */ ButtonInputSetting(int i, NativeButton nativeButton, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, nativeButton, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? "" : str);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public InputType getInputType() {
        return this.inputType;
    }

    public final NativeButton getNativeButton() {
        return this.nativeButton;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public int getPlayerIndex() {
        return this.playerIndex;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public String getSelectedValue() {
        ParamPackage buttonParam = NativeInput.INSTANCE.getButtonParam(getPlayerIndex(), this.nativeButton);
        return getDisplayString(buttonParam, buttonToText(buttonParam));
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting, org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public void setSelectedValue(ParamPackage param) {
        Intrinsics.checkNotNullParameter(param, "param");
        NativeInput.INSTANCE.setButtonParam(getPlayerIndex(), this.nativeButton, param);
    }
}
