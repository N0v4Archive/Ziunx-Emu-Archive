package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.DriverAdapter;
import org.yuzu.yuzu_emu.databinding.CardDriverOptionBinding;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.model.Driver;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class DriverAdapter extends AbstractSingleSelectionList {
    private final DriverViewModel driverViewModel;

    /* loaded from: classes.dex */
    public final class DriverViewHolder extends AbstractViewHolder {
        private final CardDriverOptionBinding binding;
        final /* synthetic */ DriverAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DriverViewHolder(DriverAdapter driverAdapter, CardDriverOptionBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = driverAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$0(final DriverAdapter this$0, DriverViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.selectItem(this$1.getBindingAdapterPosition(), new Function1() { // from class: org.yuzu.yuzu_emu.adapters.DriverAdapter$DriverViewHolder$bind$1$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    DriverViewModel driverViewModel;
                    DriverViewModel driverViewModel2;
                    driverViewModel = DriverAdapter.this.driverViewModel;
                    driverViewModel.onDriverSelected(i);
                    driverViewModel2 = DriverAdapter.this.driverViewModel;
                    driverViewModel2.showClearButton(!StringSetting.DRIVER_PATH.getGlobal());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$1(final DriverAdapter this$0, DriverViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.removeSelectableItem(this$1.getBindingAdapterPosition(), new Function2() { // from class: org.yuzu.yuzu_emu.adapters.DriverAdapter$DriverViewHolder$bind$1$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i, int i2) {
                    DriverViewModel driverViewModel;
                    DriverViewModel driverViewModel2;
                    driverViewModel = DriverAdapter.this.driverViewModel;
                    driverViewModel.onDriverRemoved(i, i2);
                    driverViewModel2 = DriverAdapter.this.driverViewModel;
                    driverViewModel2.showClearButton(!StringSetting.DRIVER_PATH.getGlobal());
                }
            });
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(Driver model) {
            Intrinsics.checkNotNullParameter(model, "model");
            CardDriverOptionBinding cardDriverOptionBinding = this.binding;
            final DriverAdapter driverAdapter = this.this$0;
            cardDriverOptionBinding.radioButton.setChecked(model.getSelected());
            cardDriverOptionBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.DriverAdapter$DriverViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DriverAdapter.DriverViewHolder.bind$lambda$2$lambda$0(DriverAdapter.this, this, view);
                }
            });
            cardDriverOptionBinding.buttonDelete.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.DriverAdapter$DriverViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DriverAdapter.DriverViewHolder.bind$lambda$2$lambda$1(DriverAdapter.this, this, view);
                }
            });
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView title = cardDriverOptionBinding.title;
            Intrinsics.checkNotNullExpressionValue(title, "title");
            ViewUtils.marquee$default(viewUtils, title, 0L, 1, null);
            MaterialTextView version = cardDriverOptionBinding.version;
            Intrinsics.checkNotNullExpressionValue(version, "version");
            ViewUtils.marquee$default(viewUtils, version, 0L, 1, null);
            MaterialTextView description = cardDriverOptionBinding.description;
            Intrinsics.checkNotNullExpressionValue(description, "description");
            ViewUtils.marquee$default(viewUtils, description, 0L, 1, null);
            cardDriverOptionBinding.title.setText(model.getTitle());
            cardDriverOptionBinding.version.setText(model.getVersion());
            cardDriverOptionBinding.description.setText(model.getDescription());
            Button buttonDelete = cardDriverOptionBinding.buttonDelete;
            Intrinsics.checkNotNullExpressionValue(buttonDelete, "buttonDelete");
            ViewUtils.setVisible$default(viewUtils, buttonDelete, !Intrinsics.areEqual(model.getTitle(), this.binding.getRoot().getContext().getString(R$string.system_gpu_driver)), false, 2, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriverAdapter(DriverViewModel driverViewModel) {
        super((List) driverViewModel.getDriverList().getValue(), null, 2, null);
        Intrinsics.checkNotNullParameter(driverViewModel, "driverViewModel");
        this.driverViewModel = driverViewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DriverViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardDriverOptionBinding inflate = CardDriverOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new DriverViewHolder(this, inflate);
    }
}
