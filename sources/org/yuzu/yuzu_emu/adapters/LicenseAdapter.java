package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.LicenseAdapter;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.fragments.LicenseBottomSheetDialogFragment;
import org.yuzu.yuzu_emu.model.License;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class LicenseAdapter extends AbstractListAdapter {
    private final AppCompatActivity activity;

    /* loaded from: classes.dex */
    public final class LicenseViewHolder extends AbstractViewHolder {
        private final ListItemSettingBinding binding;
        final /* synthetic */ LicenseAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LicenseViewHolder(LicenseAdapter licenseAdapter, ListItemSettingBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = licenseAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1$lambda$0(LicenseViewHolder this$0, License model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.onClick(model);
        }

        private final void onClick(License license) {
            LicenseBottomSheetDialogFragment.Companion.newInstance(license).show(this.this$0.activity.getSupportFragmentManager(), "LicenseBottomSheetDialogFragment");
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final License model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ListItemSettingBinding listItemSettingBinding = this.binding;
            listItemSettingBinding.textSettingName.setText(listItemSettingBinding.getRoot().getContext().getString(model.getTitleId()));
            listItemSettingBinding.textSettingDescription.setText(listItemSettingBinding.getRoot().getContext().getString(model.getDescriptionId()));
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView textSettingValue = listItemSettingBinding.textSettingValue;
            Intrinsics.checkNotNullExpressionValue(textSettingValue, "textSettingValue");
            ViewUtils.setVisible$default(viewUtils, textSettingValue, false, false, 2, null);
            listItemSettingBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.LicenseAdapter$LicenseViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LicenseAdapter.LicenseViewHolder.bind$lambda$1$lambda$0(LicenseAdapter.LicenseViewHolder.this, model, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LicenseAdapter(AppCompatActivity activity, List licenses) {
        super(licenses);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(licenses, "licenses");
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LicenseViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemSettingBinding inflate = ListItemSettingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new LicenseViewHolder(this, inflate);
    }
}
