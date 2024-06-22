package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.DateTimeSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class DateTimeViewHolder extends SettingViewHolder {
    private final ListItemSettingBinding binding;
    private DateTimeSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DateTimeViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.DateTimeViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(DateTimeViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        DateTimeSetting dateTimeSetting = this$0.setting;
        if (dateTimeSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            dateTimeSetting = null;
        }
        adapter.onClearClick(dateTimeSetting, this$0.getBindingAdapterPosition());
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.setting = (DateTimeSetting) item;
        this.binding.textSettingName.setText(item.getTitle());
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        MaterialTextView textSettingDescription = this.binding.textSettingDescription;
        Intrinsics.checkNotNullExpressionValue(textSettingDescription, "textSettingDescription");
        ViewUtils.setVisible$default(viewUtils, textSettingDescription, item.getDescription().length() > 0, false, 2, null);
        this.binding.textSettingDescription.setText(item.getDescription());
        MaterialTextView textSettingValue = this.binding.textSettingValue;
        Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
        ViewUtils.setVisible$default(viewUtils, textSettingValue, true, false, 2, null);
        DateTimeSetting dateTimeSetting = this.setting;
        DateTimeSetting dateTimeSetting2 = null;
        if (dateTimeSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            dateTimeSetting = null;
        }
        this.binding.textSettingValue.setText(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(DateTimeSetting.getValue$default(dateTimeSetting, false, 1, null) * 1000), ZoneId.of("UTC"))));
        MaterialButton buttonClear = this.binding.buttonClear;
        Intrinsics.checkNotNullExpressionValue(buttonClear, "buttonClear");
        DateTimeSetting dateTimeSetting3 = this.setting;
        if (dateTimeSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            dateTimeSetting3 = null;
        }
        ViewUtils.setVisible$default(viewUtils, buttonClear, dateTimeSetting3.getClearable(), false, 2, null);
        this.binding.buttonClear.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.viewholder.DateTimeViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DateTimeViewHolder.bind$lambda$0(DateTimeViewHolder.this, view);
            }
        });
        DateTimeSetting dateTimeSetting4 = this.setting;
        if (dateTimeSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            dateTimeSetting2 = dateTimeSetting4;
        }
        setStyle(dateTimeSetting2.isEditable(), this.binding);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        DateTimeSetting dateTimeSetting = this.setting;
        DateTimeSetting dateTimeSetting2 = null;
        if (dateTimeSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            dateTimeSetting = null;
        }
        if (dateTimeSetting.isEditable()) {
            SettingsAdapter adapter = getAdapter();
            DateTimeSetting dateTimeSetting3 = this.setting;
            if (dateTimeSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setting");
            } else {
                dateTimeSetting2 = dateTimeSetting3;
            }
            adapter.onDateTimeClick(dateTimeSetting2, getBindingAdapterPosition());
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        DateTimeSetting dateTimeSetting = this.setting;
        DateTimeSetting dateTimeSetting2 = null;
        if (dateTimeSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            dateTimeSetting = null;
        }
        if (!dateTimeSetting.isEditable()) {
            return false;
        }
        SettingsAdapter adapter = getAdapter();
        DateTimeSetting dateTimeSetting3 = this.setting;
        if (dateTimeSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            dateTimeSetting2 = dateTimeSetting3;
        }
        return adapter.onLongClick(dateTimeSetting2, getBindingAdapterPosition());
    }
}
