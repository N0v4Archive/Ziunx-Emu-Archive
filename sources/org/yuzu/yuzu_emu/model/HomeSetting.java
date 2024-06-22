package org.yuzu.yuzu_emu.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public final class HomeSetting {
    private final int descriptionId;
    private final StateFlow details;
    private final int disabledMessageId;
    private final int disabledTitleId;
    private final int iconId;
    private final Function0 isEnabled;
    private final Function0 onClick;
    private final int titleId;

    public HomeSetting(int i, int i2, int i3, Function0 onClick, Function0 isEnabled, int i4, int i5, StateFlow details) {
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(isEnabled, "isEnabled");
        Intrinsics.checkNotNullParameter(details, "details");
        this.titleId = i;
        this.descriptionId = i2;
        this.iconId = i3;
        this.onClick = onClick;
        this.isEnabled = isEnabled;
        this.disabledTitleId = i4;
        this.disabledMessageId = i5;
        this.details = details;
    }

    public /* synthetic */ HomeSetting(int i, int i2, int i3, Function0 function0, Function0 function02, int i4, int i5, StateFlow stateFlow, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, function0, (i6 & 16) != 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.model.HomeSetting.1
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.TRUE;
            }
        } : function02, (i6 & 32) != 0 ? 0 : i4, (i6 & 64) != 0 ? 0 : i5, (i6 & 128) != 0 ? StateFlowKt.MutableStateFlow("") : stateFlow);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HomeSetting)) {
            return false;
        }
        HomeSetting homeSetting = (HomeSetting) obj;
        return this.titleId == homeSetting.titleId && this.descriptionId == homeSetting.descriptionId && this.iconId == homeSetting.iconId && Intrinsics.areEqual(this.onClick, homeSetting.onClick) && Intrinsics.areEqual(this.isEnabled, homeSetting.isEnabled) && this.disabledTitleId == homeSetting.disabledTitleId && this.disabledMessageId == homeSetting.disabledMessageId && Intrinsics.areEqual(this.details, homeSetting.details);
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final StateFlow getDetails() {
        return this.details;
    }

    public final int getDisabledMessageId() {
        return this.disabledMessageId;
    }

    public final int getDisabledTitleId() {
        return this.disabledTitleId;
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final Function0 getOnClick() {
        return this.onClick;
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.iconId)) * 31) + this.onClick.hashCode()) * 31) + this.isEnabled.hashCode()) * 31) + Integer.hashCode(this.disabledTitleId)) * 31) + Integer.hashCode(this.disabledMessageId)) * 31) + this.details.hashCode();
    }

    public final Function0 isEnabled() {
        return this.isEnabled;
    }

    public String toString() {
        return "HomeSetting(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", iconId=" + this.iconId + ", onClick=" + this.onClick + ", isEnabled=" + this.isEnabled + ", disabledTitleId=" + this.disabledTitleId + ", disabledMessageId=" + this.disabledMessageId + ", details=" + this.details + ")";
    }
}
