package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.content.res.Resources;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SingleChoiceViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private SettingsItem setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SingleChoiceViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SingleChoiceViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(SingleChoiceViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        SettingsItem settingsItem = this$0.setting;
        if (settingsItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            settingsItem = null;
        }
        adapter.onClearClick(settingsItem, this$0.getBindingAdapterPosition());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        SettingsItem settingsItem;
        Intrinsics.checkNotNullParameter(item, "item");
        this.setting = item;
        MaterialTextView materialTextView = this.binding.textSettingName;
        SettingsItem settingsItem2 = null;
        if (item == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            settingsItem = null;
        } else {
            settingsItem = item;
        }
        materialTextView.setText(settingsItem.getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, item.getDescription().length() > 0, false, 2, null);
        this.binding.textSettingDescription.setText(item.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, true, false, 2, null);
        if (item instanceof SingleChoiceSetting) {
            Resources resources = this.binding.textSettingValue.getContext().getResources();
            SingleChoiceSetting singleChoiceSetting = (SingleChoiceSetting) item;
            int[] intArray = resources.getIntArray(singleChoiceSetting.getValuesId());
            Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
            int length = intArray.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (intArray[i] == SingleChoiceSetting.getSelectedValue$default(singleChoiceSetting, false, 1, null)) {
                    this.binding.textSettingValue.setText(resources.getStringArray(singleChoiceSetting.getChoicesId())[i]);
                    break;
                }
                i++;
            }
        } else if (item instanceof IntSingleChoiceSetting) {
            IntSingleChoiceSetting intSingleChoiceSetting = (IntSingleChoiceSetting) item;
            this.binding.textSettingValue.setText(intSingleChoiceSetting.getChoiceAt(IntSingleChoiceSetting.getSelectedValue$default(intSingleChoiceSetting, false, 1, null)));
        }
        CharSequence text = this.binding.textSettingValue.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        if (text.length() == 0) {
            ViewUtils viewUtils2 = ViewUtils.INSTANCE;
            MaterialTextView textSettingValue2 = this.binding.textSettingValue;
            Intrinsics.checkNotNullExpressionValue(textSettingValue2, "textSettingValue");
            ViewUtils.setVisible$default(viewUtils2, textSettingValue2, false, false, 2, null);
        }
        ViewUtils viewUtils3 = ViewUtils.INSTANCE;
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        SettingsItem settingsItem3 = this.setting;
        if (settingsItem3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            settingsItem3 = null;
        }
        ViewUtils.setVisible$default(viewUtils3, buttonClear, settingsItem3.getClearable(), false, 2, null);
        this.binding.buttonClear.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SingleChoiceViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleChoiceViewHolder.bind$lambda$0(SingleChoiceViewHolder.this, view);
            }
        });
        SettingsItem settingsItem4 = this.setting;
        if (settingsItem4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            settingsItem2 = settingsItem4;
        }
        setStyle(settingsItem2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsItem settingsItem = this.setting;
        SettingsItem settingsItem2 = null;
        if (settingsItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            settingsItem = null;
        }
        if (settingsItem.isEditable()) {
            SettingsItem settingsItem3 = this.setting;
            if (settingsItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
                settingsItem3 = null;
            }
            if (settingsItem3 instanceof SingleChoiceSetting) {
                SettingsAdapter adapter = getAdapter();
                SettingsItem settingsItem4 = this.setting;
                if (settingsItem4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setting");
                } else {
                    settingsItem2 = settingsItem4;
                }
                adapter.onSingleChoiceClick((SingleChoiceSetting) settingsItem2, getBindingAdapterPosition());
                return;
            }
            if (settingsItem3 instanceof IntSingleChoiceSetting) {
                SettingsAdapter adapter2 = getAdapter();
                SettingsItem settingsItem5 = this.setting;
                if (settingsItem5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setting");
                } else {
                    settingsItem2 = settingsItem5;
                }
                adapter2.onIntSingleChoiceClick((IntSingleChoiceSetting) settingsItem2, getBindingAdapterPosition());
            }
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsItem settingsItem = this.setting;
        SettingsItem settingsItem2 = null;
        if (settingsItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            settingsItem = null;
        }
        if (!settingsItem.isEditable()) {
            return false;
        }
        SettingsAdapter adapter = getAdapter();
        SettingsItem settingsItem3 = this.setting;
        if (settingsItem3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            settingsItem2 = settingsItem3;
        }
        return adapter.onLongClick(settingsItem2, getBindingAdapterPosition());
    }
}
