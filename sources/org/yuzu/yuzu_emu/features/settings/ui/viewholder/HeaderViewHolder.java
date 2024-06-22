package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingsHeaderBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;

/* loaded from: classes.dex */
public final class HeaderViewHolder extends SettingViewHolder {
    private final ListItemSettingsHeaderBinding binding;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public HeaderViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingsHeaderBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
        /*
            r2 = this;
            java.lang.String r0 = "binding"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "adapter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.google.android.material.textview.MaterialTextView r0 = r3.getRoot()
            java.lang.String r1 = "getRoot(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0, r4)
            r2.binding = r3
            android.view.View r2 = r2.itemView
            r3 = 0
            r2.setOnClickListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.HeaderViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingsHeaderBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    public void bind(SettingsItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.binding.textHeaderName.setText(item.getTitle());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        return true;
    }
}
