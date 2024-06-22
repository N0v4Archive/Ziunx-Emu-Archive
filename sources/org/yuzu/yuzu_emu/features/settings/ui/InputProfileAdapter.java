package org.yuzu.yuzu_emu.features.settings.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.AbstractListAdapter;
import org.yuzu.yuzu_emu.databinding.ListItemInputProfileBinding;
import org.yuzu.yuzu_emu.features.settings.ui.InputProfileAdapter;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class InputProfileAdapter extends AbstractListAdapter {

    /* loaded from: classes.dex */
    public final class InputProfileViewHolder extends AbstractViewHolder {
        private final ListItemInputProfileBinding binding;
        final /* synthetic */ InputProfileAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InputProfileViewHolder(InputProfileAdapter inputProfileAdapter, ListItemInputProfileBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = inputProfileAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(ProfileItem model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            ((ExistingProfileItem) model).getDeleteProfile().invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(ProfileItem model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            ((ExistingProfileItem) model).getSaveProfile().invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(ProfileItem model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            ((ExistingProfileItem) model).getLoadProfile().invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$3(ProfileItem model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            ((NewProfileItem) model).getCreateNewProfile().invoke();
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final ProfileItem model) {
            Intrinsics.checkNotNullParameter(model, "model");
            if (model instanceof ExistingProfileItem) {
                this.binding.title.setText(model.getName());
                this.binding.buttonNew.setVisibility(8);
                this.binding.buttonDelete.setVisibility(0);
                this.binding.buttonDelete.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileAdapter$InputProfileViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        InputProfileAdapter.InputProfileViewHolder.bind$lambda$0(ProfileItem.this, view);
                    }
                });
                this.binding.buttonSave.setVisibility(0);
                this.binding.buttonSave.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileAdapter$InputProfileViewHolder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        InputProfileAdapter.InputProfileViewHolder.bind$lambda$1(ProfileItem.this, view);
                    }
                });
                this.binding.buttonLoad.setVisibility(0);
                this.binding.buttonLoad.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileAdapter$InputProfileViewHolder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        InputProfileAdapter.InputProfileViewHolder.bind$lambda$2(ProfileItem.this, view);
                    }
                });
                return;
            }
            if (model instanceof NewProfileItem) {
                this.binding.title.setText(model.getName());
                this.binding.buttonNew.setVisibility(0);
                this.binding.buttonNew.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileAdapter$InputProfileViewHolder$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        InputProfileAdapter.InputProfileViewHolder.bind$lambda$3(ProfileItem.this, view);
                    }
                });
                this.binding.buttonSave.setVisibility(8);
                this.binding.buttonDelete.setVisibility(8);
                this.binding.buttonLoad.setVisibility(8);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputProfileAdapter(List options) {
        super(options);
        Intrinsics.checkNotNullParameter(options, "options");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemInputProfileBinding inflate = ListItemInputProfileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new InputProfileViewHolder(this, inflate);
    }
}
