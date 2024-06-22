package org.yuzu.yuzu_emu.features.settings.ui.viewholder;

import android.view.View;
import android.widget.Button;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.ListItemSettingInputBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.InputSetting;
import org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter;

/* loaded from: classes.dex */
public final class InputViewHolder extends SettingViewHolder {
    private final ListItemSettingInputBinding binding;
    private InputSetting setting;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public InputViewHolder(org.yuzu.yuzu_emu.databinding.ListItemSettingInputBinding r3, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.InputViewHolder.<init>(org.yuzu.yuzu_emu.databinding.ListItemSettingInputBinding, org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(InputViewHolder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsAdapter adapter = this$0.getAdapter();
        Button buttonOptions = this$0.binding.buttonOptions;
        Intrinsics.checkNotNullExpressionValue(buttonOptions, "buttonOptions");
        InputSetting inputSetting = this$0.setting;
        if (inputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputSetting = null;
        }
        adapter.onInputOptionsClick(buttonOptions, inputSetting, this$0.getBindingAdapterPosition());
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0072, code lost:
    
        if (r13.has("axis_y") == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0075, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b4, code lost:
    
        if (r13.has("axis") == false) goto L18;
     */
    @Override // org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bind(org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem r13) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.viewholder.InputViewHolder.bind(org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsAdapter adapter = getAdapter();
        InputSetting inputSetting = this.setting;
        if (inputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputSetting = null;
        }
        adapter.onInputClick(inputSetting, getBindingAdapterPosition());
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View clicked) {
        Intrinsics.checkNotNullParameter(clicked, "clicked");
        SettingsAdapter adapter = getAdapter();
        InputSetting inputSetting = this.setting;
        if (inputSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputSetting = null;
        }
        return adapter.onLongClick(inputSetting, getBindingAdapterPosition());
    }
}
