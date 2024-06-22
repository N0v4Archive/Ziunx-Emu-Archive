package org.yuzu.yuzu_emu.features.input.model;

import androidx.annotation.Keep;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

@Keep
/* loaded from: classes.dex */
public final class PlayerInput {
    private String[] analogs;
    private long bodyColorLeft;
    private long bodyColorRight;
    private long buttonColorLeft;
    private long buttonColorRight;
    private String[] buttons;
    private boolean connected;
    private String[] motions;
    private String profileName;
    private boolean useSystemVibrator;
    private boolean vibrationEnabled;
    private int vibrationStrength;

    public PlayerInput(boolean z, String[] buttons, String[] analogs, String[] motions, boolean z2, int i, long j, long j2, long j3, long j4, String profileName, boolean z3) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        Intrinsics.checkNotNullParameter(analogs, "analogs");
        Intrinsics.checkNotNullParameter(motions, "motions");
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        this.connected = z;
        this.buttons = buttons;
        this.analogs = analogs;
        this.motions = motions;
        this.vibrationEnabled = z2;
        this.vibrationStrength = i;
        this.bodyColorLeft = j;
        this.bodyColorRight = j2;
        this.buttonColorLeft = j3;
        this.buttonColorRight = j4;
        this.profileName = profileName;
        this.useSystemVibrator = z3;
    }

    public final boolean component1() {
        return this.connected;
    }

    public final long component10() {
        return this.buttonColorRight;
    }

    public final String component11() {
        return this.profileName;
    }

    public final boolean component12() {
        return this.useSystemVibrator;
    }

    public final String[] component2() {
        return this.buttons;
    }

    public final String[] component3() {
        return this.analogs;
    }

    public final String[] component4() {
        return this.motions;
    }

    public final boolean component5() {
        return this.vibrationEnabled;
    }

    public final int component6() {
        return this.vibrationStrength;
    }

    public final long component7() {
        return this.bodyColorLeft;
    }

    public final long component8() {
        return this.bodyColorRight;
    }

    public final long component9() {
        return this.buttonColorLeft;
    }

    public final PlayerInput copy(boolean z, String[] buttons, String[] analogs, String[] motions, boolean z2, int i, long j, long j2, long j3, long j4, String profileName, boolean z3) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        Intrinsics.checkNotNullParameter(analogs, "analogs");
        Intrinsics.checkNotNullParameter(motions, "motions");
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        return new PlayerInput(z, buttons, analogs, motions, z2, i, j, j2, j3, j4, profileName, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(PlayerInput.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.input.model.PlayerInput");
        PlayerInput playerInput = (PlayerInput) obj;
        return this.connected == playerInput.connected && Arrays.equals(this.buttons, playerInput.buttons) && Arrays.equals(this.analogs, playerInput.analogs) && Arrays.equals(this.motions, playerInput.motions) && this.vibrationEnabled == playerInput.vibrationEnabled && this.vibrationStrength == playerInput.vibrationStrength && this.bodyColorLeft == playerInput.bodyColorLeft && this.bodyColorRight == playerInput.bodyColorRight && this.buttonColorLeft == playerInput.buttonColorLeft && this.buttonColorRight == playerInput.buttonColorRight && Intrinsics.areEqual(this.profileName, playerInput.profileName) && this.useSystemVibrator == playerInput.useSystemVibrator;
    }

    public final String[] getAnalogs() {
        return this.analogs;
    }

    public final long getBodyColorLeft() {
        return this.bodyColorLeft;
    }

    public final long getBodyColorRight() {
        return this.bodyColorRight;
    }

    public final long getButtonColorLeft() {
        return this.buttonColorLeft;
    }

    public final long getButtonColorRight() {
        return this.buttonColorRight;
    }

    public final String[] getButtons() {
        return this.buttons;
    }

    public final boolean getConnected() {
        return this.connected;
    }

    public final String[] getMotions() {
        return this.motions;
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final boolean getUseSystemVibrator() {
        return this.useSystemVibrator;
    }

    public final boolean getVibrationEnabled() {
        return this.vibrationEnabled;
    }

    public final int getVibrationStrength() {
        return this.vibrationStrength;
    }

    public final boolean hasMapping() {
        boolean z = false;
        for (String str : this.buttons) {
            if (!Intrinsics.areEqual(str, "[empty]")) {
                if (str.length() > 0) {
                    z = true;
                }
            }
        }
        for (String str2 : this.analogs) {
            if (!Intrinsics.areEqual(str2, "[empty]")) {
                if (str2.length() > 0) {
                    z = true;
                }
            }
        }
        for (String str3 : this.motions) {
            if (!Intrinsics.areEqual(str3, "[empty]")) {
                if (str3.length() > 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public int hashCode() {
        return (((((((((((((((((((((Boolean.hashCode(this.connected) * 31) + Arrays.hashCode(this.buttons)) * 31) + Arrays.hashCode(this.analogs)) * 31) + Arrays.hashCode(this.motions)) * 31) + Boolean.hashCode(this.vibrationEnabled)) * 31) + this.vibrationStrength) * 31) + Long.hashCode(this.bodyColorLeft)) * 31) + Long.hashCode(this.bodyColorRight)) * 31) + Long.hashCode(this.buttonColorLeft)) * 31) + Long.hashCode(this.buttonColorRight)) * 31) + this.profileName.hashCode()) * 31) + Boolean.hashCode(this.useSystemVibrator);
    }

    public final void setAnalogs(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.analogs = strArr;
    }

    public final void setBodyColorLeft(long j) {
        this.bodyColorLeft = j;
    }

    public final void setBodyColorRight(long j) {
        this.bodyColorRight = j;
    }

    public final void setButtonColorLeft(long j) {
        this.buttonColorLeft = j;
    }

    public final void setButtonColorRight(long j) {
        this.buttonColorRight = j;
    }

    public final void setButtons(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.buttons = strArr;
    }

    public final void setConnected(boolean z) {
        this.connected = z;
    }

    public final void setMotions(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.motions = strArr;
    }

    public final void setProfileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profileName = str;
    }

    public final void setUseSystemVibrator(boolean z) {
        this.useSystemVibrator = z;
    }

    public final void setVibrationEnabled(boolean z) {
        this.vibrationEnabled = z;
    }

    public final void setVibrationStrength(int i) {
        this.vibrationStrength = i;
    }

    public String toString() {
        return "PlayerInput(connected=" + this.connected + ", buttons=" + Arrays.toString(this.buttons) + ", analogs=" + Arrays.toString(this.analogs) + ", motions=" + Arrays.toString(this.motions) + ", vibrationEnabled=" + this.vibrationEnabled + ", vibrationStrength=" + this.vibrationStrength + ", bodyColorLeft=" + this.bodyColorLeft + ", bodyColorRight=" + this.bodyColorRight + ", buttonColorLeft=" + this.buttonColorLeft + ", buttonColorRight=" + this.buttonColorRight + ", profileName=" + this.profileName + ", useSystemVibrator=" + this.useSystemVibrator + ")";
    }
}
