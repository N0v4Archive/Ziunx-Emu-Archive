package org.yuzu.yuzu_emu.features.settings.model.view;

import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;

/* loaded from: classes.dex */
public final class IntSingleChoiceSetting extends SettingsItem {
    private final String[] choices;
    private final AbstractIntSetting intSetting;
    private final int type;
    private final Integer[] values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntSingleChoiceSetting(AbstractIntSetting intSetting, int i, String titleString, int i2, String descriptionString, String[] choices, Integer[] values) {
        super(intSetting, i, titleString, i2, descriptionString);
        Intrinsics.checkNotNullParameter(intSetting, "intSetting");
        Intrinsics.checkNotNullParameter(titleString, "titleString");
        Intrinsics.checkNotNullParameter(descriptionString, "descriptionString");
        Intrinsics.checkNotNullParameter(choices, "choices");
        Intrinsics.checkNotNullParameter(values, "values");
        this.intSetting = intSetting;
        this.choices = choices;
        this.values = values;
        this.type = 9;
    }

    public /* synthetic */ IntSingleChoiceSetting(AbstractIntSetting abstractIntSetting, int i, String str, int i2, String str2, String[] strArr, Integer[] numArr, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractIntSetting, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "" : str2, strArr, numArr);
    }

    public static /* synthetic */ int getSelectedValue$default(IntSingleChoiceSetting intSingleChoiceSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return intSingleChoiceSetting.getSelectedValue(z);
    }

    public final String getChoiceAt(int i) {
        IntRange indices;
        indices = ArraysKt___ArraysKt.getIndices(this.choices);
        return indices.contains(i) ? this.choices[i] : "";
    }

    public final String[] getChoices() {
        return this.choices;
    }

    public final int getSelectedValue(boolean z) {
        return this.intSetting.getInt(z);
    }

    public final int getSelectedValueIndex() {
        int length = this.values.length;
        for (int i = 0; i < length; i++) {
            if (this.values[i].intValue() == getSelectedValue$default(this, false, 1, null)) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem
    public int getType() {
        return this.type;
    }

    public final int getValueAt(int i) {
        IntRange indices;
        indices = ArraysKt___ArraysKt.getIndices(this.values);
        if (indices.contains(i)) {
            return this.values[i].intValue();
        }
        return -1;
    }

    public final void setSelectedValue(int i) {
        this.intSetting.setInt(i);
    }
}
