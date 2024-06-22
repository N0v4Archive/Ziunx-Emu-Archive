package org.yuzu.yuzu_emu.features.settings.model.view;

import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.ButtonName;
import org.yuzu.yuzu_emu.features.input.model.InputType;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public abstract class InputSetting extends SettingsItem {
    private final int type;

    private InputSetting(int i, String str) {
        super(SettingsItem.Companion.getEmptySetting(), i, str, 0, "");
        this.type = 8;
    }

    public /* synthetic */ InputSetting(int i, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str);
    }

    private final String getDirectionName(String str) {
        Context context;
        int i;
        int hashCode = str.hashCode();
        if (hashCode != 3739) {
            if (hashCode != 3089570) {
                if (hashCode != 3317767) {
                    if (hashCode != 108511772 || !str.equals("right")) {
                        return str;
                    }
                    context = getContext();
                    i = R$string.right;
                } else {
                    if (!str.equals("left")) {
                        return str;
                    }
                    context = getContext();
                    i = R$string.left;
                }
            } else {
                if (!str.equals("down")) {
                    return str;
                }
                context = getContext();
                i = R$string.down;
            }
        } else {
            if (!str.equals("up")) {
                return str;
            }
            context = getContext();
            i = R$string.up;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String analogToText(ParamPackage param, String direction) {
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(direction, "direction");
        if (!param.has("engine")) {
            String string = getContext().getString(R$string.not_set);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (Intrinsics.areEqual(param.get("engine", ""), "analog_from_button")) {
            return buttonToText(new ParamPackage(param.get(direction, "")));
        }
        if (!param.has("axis_x") || !param.has("axis_y")) {
            String string2 = getContext().getString(R$string.unknown);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        }
        String str = param.get("axis_x", "");
        String str2 = param.get("axis_y", "");
        boolean areEqual = Intrinsics.areEqual(param.get("invert_x", "+"), "-");
        boolean areEqual2 = Intrinsics.areEqual(param.get("invert_y", "+"), "-");
        if (Intrinsics.areEqual(direction, "modifier")) {
            String string3 = getContext().getString(R$string.unused);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            return string3;
        }
        int hashCode = direction.hashCode();
        if (hashCode != 3739) {
            if (hashCode != 3089570) {
                if (hashCode != 3317767) {
                    if (hashCode == 108511772 && direction.equals("right")) {
                        String string4 = getContext().getString(R$string.qualified_axis, str, areEqual ? "-" : "+");
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        return string4;
                    }
                } else if (direction.equals("left")) {
                    String string5 = getContext().getString(R$string.qualified_axis, str, areEqual ? "+" : "-");
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    return string5;
                }
            } else if (direction.equals("down")) {
                String string6 = getContext().getString(R$string.qualified_axis, str2, areEqual2 ? "-" : "+");
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                return string6;
            }
        } else if (direction.equals("up")) {
            String string7 = getContext().getString(R$string.qualified_axis, str2, areEqual2 ? "+" : "-");
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            return string7;
        }
        String string8 = getContext().getString(R$string.unknown);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        return string8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String buttonToText(ParamPackage param) {
        Context context;
        int i;
        String string;
        Intrinsics.checkNotNullParameter(param, "param");
        if (param.has("engine")) {
            String str = param.get("toggle", false) ? "~" : "";
            String str2 = param.get("inverted", false) ? "!" : "";
            String str3 = Intrinsics.areEqual(param.get("invert", "+"), "-") ? "-" : "";
            String str4 = param.get("turbo", false) ? "$" : "";
            ButtonName buttonName = NativeInput.INSTANCE.getButtonName(param);
            if (buttonName == ButtonName.Invalid) {
                context = getContext();
                i = R$string.invalid;
            } else {
                if (buttonName == ButtonName.Engine) {
                    return param.get("engine", "");
                }
                if (buttonName == ButtonName.Value) {
                    if (param.has("hat")) {
                        string = getContext().getString(R$string.qualified_hat, str4, str, str2, getDirectionName(param.get("direction", "")));
                    } else if (param.has("axis")) {
                        string = getContext().getString(R$string.qualified_button_stick_axis, str, str2, str3, param.get("axis", ""));
                    } else if (param.has("button")) {
                        string = getContext().getString(R$string.qualified_button, str4, str, str2, param.get("button", ""));
                    }
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    return string;
                }
                context = getContext();
                i = R$string.unknown;
            }
        } else {
            context = getContext();
            i = R$string.not_set;
        }
        string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    protected final Context getContext() {
        return YuzuApplication.Companion.getAppContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getDisplayString(ParamPackage params, String control) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(control, "control");
        String str = params.get("display", "");
        if (str.length() == 0) {
            String string = getContext().getString(R$string.not_set);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        return str + ": " + control;
    }

    public abstract InputType getInputType();

    public abstract int getPlayerIndex();

    public abstract String getSelectedValue();

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public abstract void setSelectedValue(ParamPackage paramPackage);
}
