package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class InputProfileViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private InputProfileSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public InputProfileViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.InputProfileViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        InputProfileSetting inputProfileSetting = (InputProfileSetting) item;
        this.setting = inputProfileSetting;
        MaterialTextView materialTextView = this.binding.textSettingName;
        InputProfileSetting inputProfileSetting2 = null;
        if (inputProfileSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputProfileSetting = null;
        }
        materialTextView.setText(inputProfileSetting.getTitle());
        MaterialTextView materialTextView2 = this.binding.textSettingValue;
        InputProfileSetting inputProfileSetting3 = this.setting;
        if (inputProfileSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            inputProfileSetting2 = inputProfileSetting3;
        }
        String currentProfile = inputProfileSetting2.getCurrentProfile();
        if (currentProfile.length() == 0) {
            currentProfile = this.binding.getRoot().getContext().getString(R$string.not_set);
            Intrinsics.checkNotNullExpressionValue(currentProfile, "getString(...)");
        }
        materialTextView2.setText(currentProfile);
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, false, false, 2, null);
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        ViewUtils.setVisible$default(viewUtils, buttonClear, false, false, 2, null);
        ImageView icon = this.binding.icon;
        Intrinsics.checkNotNullExpressionValue(icon, "icon");
        ViewUtils.setVisible$default(viewUtils, icon, false, false, 2, null);
        MaterialButton buttonClear2 = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear2, "buttonClear");
        ViewUtils.setVisible$default(viewUtils, buttonClear2, false, false, 2, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsAdapter adapter = getAdapter();
        InputProfileSetting inputProfileSetting = this.setting;
        if (inputProfileSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputProfileSetting = null;
        }
        adapter.onInputProfileClick(inputProfileSetting, getBindingAdapterPosition());
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        return false;
    }
}
