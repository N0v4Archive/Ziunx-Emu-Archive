package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SliderViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private SliderSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SliderViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
        /*
            r2 = this;
            java.lang.String r0 = "binding"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "adapter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.widget.RelativeLayout r0 = r3.getRoot()
            java.lang.String r1 = "getRoot(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0, r4)
            r2.binding = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SliderViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(SliderViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        SliderSetting sliderSetting = this$0.setting;
        if (sliderSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting = null;
        }
        adapter.onClearClick(sliderSetting, this$0.getBindingAdapterPosition());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        SliderSetting sliderSetting = (SliderSetting) item;
        this.setting = sliderSetting;
        MaterialTextView materialTextView = this.binding.textSettingName;
        SliderSetting sliderSetting2 = null;
        if (sliderSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting = null;
        }
        materialTextView.setText(sliderSetting.getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, item.getDescription().length() > 0, false, 2, null);
        MaterialTextView materialTextView2 = this.binding.textSettingDescription;
        SliderSetting sliderSetting3 = this.setting;
        if (sliderSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting3 = null;
        }
        materialTextView2.setText(sliderSetting3.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, true, false, 2, null);
        MaterialTextView materialTextView3 = this.binding.textSettingValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = materialTextView3.getContext().getString(R$string.value_with_units);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Object[] objArr = new Object[2];
        SliderSetting sliderSetting4 = this.setting;
        if (sliderSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting4 = null;
        }
        objArr[0] = Integer.valueOf(SliderSetting.getSelectedValue$default(sliderSetting4, false, 1, null));
        SliderSetting sliderSetting5 = this.setting;
        if (sliderSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting5 = null;
        }
        objArr[1] = sliderSetting5.getUnits();
        String format = String.format(string, Arrays.copyOf(objArr, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        materialTextView3.setText(format);
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        SliderSetting sliderSetting6 = this.setting;
        if (sliderSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting6 = null;
        }
        ViewUtils.setVisible$default(viewUtils, buttonClear, sliderSetting6.getClearable(), false, 2, null);
        this.binding.buttonClear.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SliderViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SliderViewHolder.bind$lambda$0(SliderViewHolder.this, view);
            }
        });
        SliderSetting sliderSetting7 = this.setting;
        if (sliderSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            sliderSetting2 = sliderSetting7;
        }
        setStyle(sliderSetting2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SliderSetting sliderSetting = this.setting;
        SliderSetting sliderSetting2 = null;
        if (sliderSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting = null;
        }
        if (sliderSetting.isEditable()) {
            SettingsAdapter adapter = getAdapter();
            SliderSetting sliderSetting3 = this.setting;
            if (sliderSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
            } else {
                sliderSetting2 = sliderSetting3;
            }
            adapter.onSliderClick(sliderSetting2, getBindingAdapterPosition());
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SliderSetting sliderSetting = this.setting;
        SliderSetting sliderSetting2 = null;
        if (sliderSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            sliderSetting = null;
        }
        if (!sliderSetting.isEditable()) {
            return false;
        }
        SettingsAdapter adapter = getAdapter();
        SliderSetting sliderSetting3 = this.setting;
        if (sliderSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            sliderSetting2 = sliderSetting3;
        }
        return adapter.onLongClick(sliderSetting2, getBindingAdapterPosition());
    }
}
