package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SubmenuSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SubmenuViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private SubmenuSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SubmenuViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.SubmenuViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.setting = (SubmenuSetting) item;
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        ImageView icon = this.binding.icon;
        Intrinsics.checkNotNullExpressionValue(icon, "icon");
        SubmenuSetting submenuSetting = this.setting;
        SubmenuSetting submenuSetting2 = null;
        if (submenuSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            submenuSetting = null;
        }
        ViewUtils.setVisible$default(viewUtils, icon, submenuSetting.getIconId() != 0, false, 2, null);
        SubmenuSetting submenuSetting3 = this.setting;
        if (submenuSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            submenuSetting3 = null;
        }
        if (submenuSetting3.getIconId() != 0) {
            ImageView imageView = this.binding.icon;
            Resources resources = imageView.getResources();
            SubmenuSetting submenuSetting4 = this.setting;
            if (submenuSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
                submenuSetting4 = null;
            }
            imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, submenuSetting4.getIconId(), this.binding.icon.getContext().getTheme()));
        }
        MaterialTextView materialTextView = this.binding.textSettingName;
        SubmenuSetting submenuSetting5 = this.setting;
        if (submenuSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            submenuSetting5 = null;
        }
        materialTextView.setText(submenuSetting5.getTitle());
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        SubmenuSetting submenuSetting6 = this.setting;
        if (submenuSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            submenuSetting6 = null;
        }
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, submenuSetting6.getDescription().length() > 0, false, 2, null);
        MaterialTextView materialTextView2 = this.binding.textSettingDescription;
        SubmenuSetting submenuSetting7 = this.setting;
        if (submenuSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            submenuSetting2 = submenuSetting7;
        }
        materialTextView2.setText(submenuSetting2.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, false, false, 2, null);
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        ViewUtils.setVisible$default(viewUtils, buttonClear, false, false, 2, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsAdapter adapter = getAdapter();
        SubmenuSetting submenuSetting = this.setting;
        if (submenuSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            submenuSetting = null;
        }
        adapter.onSubmenuClick(submenuSetting);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        return true;
    }
}
