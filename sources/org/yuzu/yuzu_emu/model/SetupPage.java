package org.yuzu.yuzu_emu.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class SetupPage {
    private final Function1 buttonAction;
    private final int buttonIconId;
    private final int buttonTextId;
    private final int descriptionId;
    private final boolean hasWarning;
    private final int iconId;
    private final boolean leftAlignedIcon;
    private final Function0 stepCompleted;
    private final int titleId;
    private final int warningDescriptionId;
    private final int warningHelpLinkId;
    private final int warningTitleId;

    public SetupPage(int i, int i2, int i3, int i4, boolean z, int i5, Function1 buttonAction, boolean z2, int i6, int i7, int i8, Function0 stepCompleted) {
        Intrinsics.checkNotNullParameter(buttonAction, "buttonAction");
        Intrinsics.checkNotNullParameter(stepCompleted, "stepCompleted");
        this.iconId = i;
        this.titleId = i2;
        this.descriptionId = i3;
        this.buttonIconId = i4;
        this.leftAlignedIcon = z;
        this.buttonTextId = i5;
        this.buttonAction = buttonAction;
        this.hasWarning = z2;
        this.warningTitleId = i6;
        this.warningDescriptionId = i7;
        this.warningHelpLinkId = i8;
        this.stepCompleted = stepCompleted;
    }

    public /* synthetic */ SetupPage(int i, int i2, int i3, int i4, boolean z, int i5, Function1 function1, boolean z2, int i6, int i7, int i8, Function0 function0, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, z, i5, function1, z2, (i9 & 256) != 0 ? 0 : i6, (i9 & 512) != 0 ? 0 : i7, (i9 & 1024) != 0 ? 0 : i8, (i9 & 2048) != 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.model.SetupPage.1
            @Override // kotlin.jvm.functions.Function0
            public final StepState invoke() {
                return StepState.UNDEFINED;
            }
        } : function0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetupPage)) {
            return false;
        }
        SetupPage setupPage = (SetupPage) obj;
        return this.iconId == setupPage.iconId && this.titleId == setupPage.titleId && this.descriptionId == setupPage.descriptionId && this.buttonIconId == setupPage.buttonIconId && this.leftAlignedIcon == setupPage.leftAlignedIcon && this.buttonTextId == setupPage.buttonTextId && Intrinsics.areEqual(this.buttonAction, setupPage.buttonAction) && this.hasWarning == setupPage.hasWarning && this.warningTitleId == setupPage.warningTitleId && this.warningDescriptionId == setupPage.warningDescriptionId && this.warningHelpLinkId == setupPage.warningHelpLinkId && Intrinsics.areEqual(this.stepCompleted, setupPage.stepCompleted);
    }

    public final Function1 getButtonAction() {
        return this.buttonAction;
    }

    public final int getButtonIconId() {
        return this.buttonIconId;
    }

    public final int getButtonTextId() {
        return this.buttonTextId;
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final boolean getHasWarning() {
        return this.hasWarning;
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final boolean getLeftAlignedIcon() {
        return this.leftAlignedIcon;
    }

    public final Function0 getStepCompleted() {
        return this.stepCompleted;
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public final int getWarningDescriptionId() {
        return this.warningDescriptionId;
    }

    public final int getWarningHelpLinkId() {
        return this.warningHelpLinkId;
    }

    public final int getWarningTitleId() {
        return this.warningTitleId;
    }

    public int hashCode() {
        return (((((((((((((((((((((Integer.hashCode(this.iconId) * 31) + Integer.hashCode(this.titleId)) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.buttonIconId)) * 31) + Boolean.hashCode(this.leftAlignedIcon)) * 31) + Integer.hashCode(this.buttonTextId)) * 31) + this.buttonAction.hashCode()) * 31) + Boolean.hashCode(this.hasWarning)) * 31) + Integer.hashCode(this.warningTitleId)) * 31) + Integer.hashCode(this.warningDescriptionId)) * 31) + Integer.hashCode(this.warningHelpLinkId)) * 31) + this.stepCompleted.hashCode();
    }

    public String toString() {
        return "SetupPage(iconId=" + this.iconId + ", titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", buttonIconId=" + this.buttonIconId + ", leftAlignedIcon=" + this.leftAlignedIcon + ", buttonTextId=" + this.buttonTextId + ", buttonAction=" + this.buttonAction + ", hasWarning=" + this.hasWarning + ", warningTitleId=" + this.warningTitleId + ", warningDescriptionId=" + this.warningDescriptionId + ", warningHelpLinkId=" + this.warningHelpLinkId + ", stepCompleted=" + this.stepCompleted + ")";
    }
}
