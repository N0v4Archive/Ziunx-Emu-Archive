package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.StringInputSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class StringInputViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private StringInputSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public StringInputViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.StringInputViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(StringInputViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        StringInputSetting stringInputSetting = this$0.setting;
        if (stringInputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting = null;
        }
        adapter.onClearClick(stringInputSetting, this$0.getBindingAdapterPosition());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        StringInputSetting stringInputSetting = (StringInputSetting) item;
        this.setting = stringInputSetting;
        MaterialTextView materialTextView = this.binding.textSettingName;
        StringInputSetting stringInputSetting2 = null;
        if (stringInputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting = null;
        }
        materialTextView.setText(stringInputSetting.getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        StringInputSetting stringInputSetting3 = this.setting;
        if (stringInputSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting3 = null;
        }
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, stringInputSetting3.getDescription().length() > 0, false, 2, null);
        MaterialTextView materialTextView2 = this.binding.textSettingDescription;
        StringInputSetting stringInputSetting4 = this.setting;
        if (stringInputSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting4 = null;
        }
        materialTextView2.setText(stringInputSetting4.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, true, false, 2, null);
        MaterialTextView materialTextView3 = this.binding.textSettingValue;
        StringInputSetting stringInputSetting5 = this.setting;
        if (stringInputSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting5 = null;
        }
        materialTextView3.setText(StringInputSetting.getSelectedValue$default(stringInputSetting5, false, 1, null));
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        StringInputSetting stringInputSetting6 = this.setting;
        if (stringInputSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting6 = null;
        }
        ViewUtils.setVisible$default(viewUtils, buttonClear, stringInputSetting6.getClearable(), false, 2, null);
        this.binding.buttonClear.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.StringInputViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StringInputViewHolder.bind$lambda$0(StringInputViewHolder.this, view);
            }
        });
        StringInputSetting stringInputSetting7 = this.setting;
        if (stringInputSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            stringInputSetting2 = stringInputSetting7;
        }
        setStyle(stringInputSetting2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        StringInputSetting stringInputSetting = this.setting;
        StringInputSetting stringInputSetting2 = null;
        if (stringInputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting = null;
        }
        if (stringInputSetting.isEditable()) {
            SettingsAdapter adapter = getAdapter();
            StringInputSetting stringInputSetting3 = this.setting;
            if (stringInputSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
            } else {
                stringInputSetting2 = stringInputSetting3;
            }
            adapter.onStringInputClick(stringInputSetting2, getBindingAdapterPosition());
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        StringInputSetting stringInputSetting = this.setting;
        StringInputSetting stringInputSetting2 = null;
        if (stringInputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            stringInputSetting = null;
        }
        if (!stringInputSetting.isEditable()) {
            return false;
        }
        SettingsAdapter adapter = getAdapter();
        StringInputSetting stringInputSetting3 = this.setting;
        if (stringInputSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            stringInputSetting2 = stringInputSetting3;
        }
        return adapter.onLongClick(stringInputSetting2, getBindingAdapterPosition());
    }
}
