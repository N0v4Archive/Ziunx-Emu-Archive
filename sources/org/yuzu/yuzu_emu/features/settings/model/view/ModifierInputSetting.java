package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.InputType;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class ModifierInputSetting extends InputSetting {
    private final InputType inputType;
    private final NativeAnalog nativeAnalog;
    private final int playerIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModifierInputSetting(int i, NativeAnalog nativeAnalog, int i2, String titleString) {
        super(i2, titleString, null);
        Intrinsics.checkNotNullParameter(nativeAnalog, "nativeAnalog");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        this.playerIndex = i;
        this.nativeAnalog = nativeAnalog;
        this.inputType = InputType.Button;
    }

    public /* synthetic */ ModifierInputSetting(int i, NativeAnalog nativeAnalog, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, nativeAnalog, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? "" : str);
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public InputType getInputType() {
        return this.inputType;
    }

    public final NativeAnalog getNativeAnalog() {
        return this.nativeAnalog;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public int getPlayerIndex() {
        return this.playerIndex;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public String getSelectedValue() {
        return buttonToText(new ParamPackage(NativeInput.INSTANCE.getStickParam(getPlayerIndex(), this.nativeAnalog).get("modifier", "")));
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.InputSetting
    public void setSelectedValue(ParamPackage param) {
        Intrinsics.checkNotNullParameter(param, "param");
        NativeInput nativeInput = NativeInput.INSTANCE;
        ParamPackage stickParam = nativeInput.getStickParam(getPlayerIndex(), this.nativeAnalog);
        stickParam.set("modifier", param.serialize());
        nativeInput.setStickParam(getPlayerIndex(), this.nativeAnalog, stickParam);
    }
}
