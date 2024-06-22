package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.databinding.ListItemSettingSwitchBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;

/* loaded from: classes.dex */
public abstract class SettingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private final SettingsAdapter adapter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingViewHolder(View itemView, SettingsAdapter adapter) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.adapter = adapter;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bind(SettingsItem settingsItem);

    /* JADX INFO: Access modifiers changed from: protected */
    public final SettingsAdapter getAdapter() {
        return this.adapter;
    }

    public final void setStyle(boolean z, ListItemSettingBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        float f = z ? 1.0f : 0.5f;
        binding.textSettingName.setAlpha(f);
        binding.textSettingDescription.setAlpha(f);
        binding.textSettingValue.setAlpha(f);
        binding.buttonClear.setEnabled(z);
    }

    public final void setStyle(boolean z, ListItemSettingSwitchBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        binding.switchWidget.setEnabled(z);
        float f = z ? 1.0f : 0.5f;
        binding.textSettingName.setAlpha(f);
        binding.textSettingDescription.setAlpha(f);
        binding.buttonClear.setEnabled(z);
    }
}
