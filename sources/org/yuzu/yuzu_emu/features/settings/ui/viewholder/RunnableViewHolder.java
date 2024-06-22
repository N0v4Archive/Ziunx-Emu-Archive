package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.RunnableSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class RunnableViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private RunnableSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RunnableViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.RunnableViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.setting = (RunnableSetting) item;
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        ImageView icon = this.binding.icon;
        Intrinsics.checkNotNullExpressionValue(icon, "icon");
        RunnableSetting runnableSetting = this.setting;
        RunnableSetting runnableSetting2 = null;
        if (runnableSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            runnableSetting = null;
        }
        ViewUtils.setVisible$default(viewUtils, icon, runnableSetting.getIconId() != 0, false, 2, null);
        RunnableSetting runnableSetting3 = this.setting;
        if (runnableSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            runnableSetting3 = null;
        }
        if (runnableSetting3.getIconId() != 0) {
            ImageView imageView = this.binding.icon;
            Resources resources = imageView.getResources();
            RunnableSetting runnableSetting4 = this.setting;
            if (runnableSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
                runnableSetting4 = null;
            }
            imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, runnableSetting4.getIconId(), this.binding.icon.getContext().getTheme()));
        }
        MaterialTextView materialTextView = this.binding.textSettingName;
        RunnableSetting runnableSetting5 = this.setting;
        if (runnableSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            runnableSetting5 = null;
        }
        materialTextView.setText(runnableSetting5.getTitle());
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        RunnableSetting runnableSetting6 = this.setting;
        if (runnableSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            runnableSetting6 = null;
        }
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, runnableSetting6.getDescription().length() > 0, false, 2, null);
        this.binding.textSettingDescription.setText(item.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, false, false, 2, null);
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        ViewUtils.setVisible$default(viewUtils, buttonClear, false, false, 2, null);
        RunnableSetting runnableSetting7 = this.setting;
        if (runnableSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            runnableSetting2 = runnableSetting7;
        }
        setStyle(runnableSetting2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        RunnableSetting runnableSetting = this.setting;
        RunnableSetting runnableSetting2 = null;
        if (runnableSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            runnableSetting = null;
        }
        if (runnableSetting.isRunnable()) {
            RunnableSetting runnableSetting3 = this.setting;
            if (runnableSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
            } else {
                runnableSetting2 = runnableSetting3;
            }
            runnableSetting2.getRunnable().invoke();
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        return true;
    }
}
