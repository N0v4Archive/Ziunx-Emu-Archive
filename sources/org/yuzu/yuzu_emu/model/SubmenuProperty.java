package org.yuzu.yuzu_emu.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;

/* loaded from: classes.dex */
public final class SubmenuProperty implements GameProperty {
    private final Function0 action;
    private final int descriptionId;
    private final Function0 details;
    private final StateFlow detailsFlow;
    private final int iconId;
    private final int titleId;

    public SubmenuProperty(int i, int i2, int i3, Function0 function0, StateFlow stateFlow, Function0 action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.titleId = i;
        this.descriptionId = i2;
        this.iconId = i3;
        this.details = function0;
        this.detailsFlow = stateFlow;
        this.action = action;
    }

    public /* synthetic */ SubmenuProperty(int i, int i2, int i3, Function0 function0, StateFlow stateFlow, Function0 function02, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, (i4 & 8) != 0 ? null : function0, (i4 & 16) != 0 ? null : stateFlow, function02);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubmenuProperty)) {
            return false;
        }
        SubmenuProperty submenuProperty = (SubmenuProperty) obj;
        return this.titleId == submenuProperty.titleId && this.descriptionId == submenuProperty.descriptionId && this.iconId == submenuProperty.iconId && Intrinsics.areEqual(this.details, submenuProperty.details) && Intrinsics.areEqual(this.detailsFlow, submenuProperty.detailsFlow) && Intrinsics.areEqual(this.action, submenuProperty.action);
    }

    public final Function0 getAction() {
        return this.action;
    }

    public int getDescriptionId() {
        return this.descriptionId;
    }

    public final Function0 getDetails() {
        return this.details;
    }

    public final StateFlow getDetailsFlow() {
        return this.detailsFlow;
    }

    public int getIconId() {
        return this.iconId;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.iconId)) * 31;
        Function0 function0 = this.details;
        int hashCode2 = (hashCode + (function0 == null ? 0 : function0.hashCode())) * 31;
        StateFlow stateFlow = this.detailsFlow;
        return ((hashCode2 + (stateFlow != null ? stateFlow.hashCode() : 0)) * 31) + this.action.hashCode();
    }

    public String toString() {
        return "SubmenuProperty(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", iconId=" + this.iconId + ", details=" + this.details + ", detailsFlow=" + this.detailsFlow + ", action=" + this.action + ")";
    }
}
