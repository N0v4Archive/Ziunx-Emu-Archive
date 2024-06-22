package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.InstallableAdapter;
import org.yuzu.yuzu_emu.databinding.CardInstallableBinding;
import org.yuzu.yuzu_emu.model.Installable;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class InstallableAdapter extends AbstractListAdapter {

    /* loaded from: classes.dex */
    public final class InstallableViewHolder extends AbstractViewHolder {
        private final CardInstallableBinding binding;
        final /* synthetic */ InstallableAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InstallableViewHolder(InstallableAdapter installableAdapter, CardInstallableBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = installableAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(Installable model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            Function0 install = model.getInstall();
            if (install != null) {
                install.invoke();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(Installable model, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            Function0 export = model.getExport();
            if (export != null) {
                export.invoke();
            }
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final Installable model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.binding.title.setText(model.getTitleId());
            this.binding.description.setText(model.getDescriptionId());
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            Button buttonInstall = this.binding.buttonInstall;
            Intrinsics.checkNotNullExpressionValue(buttonInstall, "buttonInstall");
            ViewUtils.setVisible$default(viewUtils, buttonInstall, model.getInstall() != null, false, 2, null);
            this.binding.buttonInstall.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.InstallableAdapter$InstallableViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    InstallableAdapter.InstallableViewHolder.bind$lambda$0(Installable.this, view);
                }
            });
            Button buttonExport = this.binding.buttonExport;
            Intrinsics.checkNotNullExpressionValue(buttonExport, "buttonExport");
            ViewUtils.setVisible$default(viewUtils, buttonExport, model.getExport() != null, false, 2, null);
            this.binding.buttonExport.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.InstallableAdapter$InstallableViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    InstallableAdapter.InstallableViewHolder.bind$lambda$1(Installable.this, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstallableAdapter(List installables) {
        super(installables);
        Intrinsics.checkNotNullParameter(installables, "installables");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public InstallableViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardInstallableBinding inflate = CardInstallableBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new InstallableViewHolder(this, inflate);
    }
}
