package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.AddonAdapter;
import org.yuzu.yuzu_emu.databinding.ListItemAddonBinding;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.Patch;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class AddonAdapter extends AbstractDiffAdapter {
    private final AddonViewModel addonViewModel;

    /* loaded from: classes.dex */
    public final class AddonViewHolder extends AbstractViewHolder {
        private final ListItemAddonBinding binding;
        final /* synthetic */ AddonAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AddonViewHolder(AddonAdapter addonAdapter, ListItemAddonBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = addonAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(AddonViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.binding.addonCheckbox.setChecked(!r0.isChecked());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(Patch model, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(model, "$model");
            model.setEnabled(z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(AddonAdapter this$0, Patch model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.getAddonViewModel().setAddonToDelete(model);
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final Patch model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.AddonAdapter$AddonViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddonAdapter.AddonViewHolder.bind$lambda$0(AddonAdapter.AddonViewHolder.this, view);
                }
            });
            this.binding.title.setText(model.getName());
            this.binding.version.setText(model.getVersion());
            this.binding.addonCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.yuzu.yuzu_emu.adapters.AddonAdapter$AddonViewHolder$$ExternalSyntheticLambda1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AddonAdapter.AddonViewHolder.bind$lambda$1(Patch.this, compoundButton, z);
                }
            });
            this.binding.addonCheckbox.setChecked(model.getEnabled());
            Button button = this.binding.buttonDelete;
            final AddonAdapter addonAdapter = this.this$0;
            button.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.AddonAdapter$AddonViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddonAdapter.AddonViewHolder.bind$lambda$2(AddonAdapter.this, model, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonAdapter(AddonViewModel addonViewModel) {
        super(false, 1, null);
        Intrinsics.checkNotNullParameter(addonViewModel, "addonViewModel");
        this.addonViewModel = addonViewModel;
    }

    public final AddonViewModel getAddonViewModel() {
        return this.addonViewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AddonViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemAddonBinding inflate = ListItemAddonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new AddonViewHolder(this, inflate);
    }
}
