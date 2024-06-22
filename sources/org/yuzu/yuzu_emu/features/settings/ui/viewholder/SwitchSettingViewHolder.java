package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import android.widget.CompoundButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingSwitchBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SwitchSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SwitchSettingViewHolder extends SettingViewHolder {
    private final ListItemSettingSwitchBinding binding;
    private SwitchSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SwitchSettingViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingSwitchBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SwitchSettingViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingSwitchBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(SwitchSettingViewHolder this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(compoundButton, "<anonymous parameter 0>");
        SettingsAdapter adapter = this$0.getAdapter();
        SwitchSetting switchSetting = this$0.setting;
        if (switchSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting = null;
        }
        adapter.onBooleanClick(switchSetting, this$0.binding.switchWidget.isChecked(), this$0.getBindingAdapterPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$1(SwitchSettingViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        SwitchSetting switchSetting = this$0.setting;
        if (switchSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting = null;
        }
        adapter.onClearClick(switchSetting, this$0.getBindingAdapterPosition());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        SwitchSetting switchSetting = (SwitchSetting) item;
        this.setting = switchSetting;
        MaterialTextView materialTextView = this.binding.textSettingName;
        SwitchSetting switchSetting2 = null;
        if (switchSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting = null;
        }
        materialTextView.setText(switchSetting.getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        SwitchSetting switchSetting3 = this.setting;
        if (switchSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting3 = null;
        }
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, switchSetting3.getDescription().length() > 0, false, 2, null);
        MaterialTextView materialTextView2 = this.binding.textSettingDescription;
        SwitchSetting switchSetting4 = this.setting;
        if (switchSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting4 = null;
        }
        materialTextView2.setText(switchSetting4.getDescription());
        this.binding.switchWidget.setOnCheckedChangeListener(null);
        MaterialSwitch materialSwitch = this.binding.switchWidget;
        SwitchSetting switchSetting5 = this.setting;
        if (switchSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting5 = null;
        }
        SwitchSetting switchSetting6 = this.setting;
        if (switchSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting6 = null;
        }
        materialSwitch.setChecked(switchSetting5.getIsChecked(switchSetting6.getNeedsRuntimeGlobal()));
        this.binding.switchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SwitchSettingViewHolder$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SwitchSettingViewHolder.bind$lambda$0(SwitchSettingViewHolder.this, compoundButton, z);
            }
        });
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        SwitchSetting switchSetting7 = this.setting;
        if (switchSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting7 = null;
        }
        ViewUtils.setVisible$default(viewUtils, buttonClear, switchSetting7.getClearable(), false, 2, null);
        this.binding.buttonClear.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SwitchSettingViewHolder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwitchSettingViewHolder.bind$lambda$1(SwitchSettingViewHolder.this, view);
            }
        });
        SwitchSetting switchSetting8 = this.setting;
        if (switchSetting8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            switchSetting2 = switchSetting8;
        }
        setStyle(switchSetting2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SwitchSetting switchSetting = this.setting;
        if (switchSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting = null;
        }
        if (switchSetting.isEditable()) {
            this.binding.switchWidget.toggle();
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SwitchSetting switchSetting = this.setting;
        SwitchSetting switchSetting2 = null;
        if (switchSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            switchSetting = null;
        }
        if (!switchSetting.isEditable()) {
            return false;
        }
        SettingsAdapter adapter = getAdapter();
        SwitchSetting switchSetting3 = this.setting;
        if (switchSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            switchSetting2 = switchSetting3;
        }
        return adapter.onLongClick(switchSetting2, getBindingAdapterPosition());
    }
}
