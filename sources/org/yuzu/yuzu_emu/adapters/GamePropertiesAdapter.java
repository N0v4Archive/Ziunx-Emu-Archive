package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.adapters.GamePropertiesAdapter;
import org.yuzu.yuzu_emu.databinding.CardInstallableIconBinding;
import org.yuzu.yuzu_emu.databinding.CardSimpleOutlinedBinding;
import org.yuzu.yuzu_emu.model.GameProperty;
import org.yuzu.yuzu_emu.model.InstallableProperty;
import org.yuzu.yuzu_emu.model.SubmenuProperty;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class GamePropertiesAdapter extends AbstractListAdapter {
    private List properties;
    private final LifecycleOwner viewLifecycle;

    /* loaded from: classes.dex */
    public final class InstallablePropertyViewHolder extends AbstractViewHolder {
        private final CardInstallableIconBinding binding;
        final /* synthetic */ GamePropertiesAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InstallablePropertyViewHolder(GamePropertiesAdapter gamePropertiesAdapter, CardInstallableIconBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = gamePropertiesAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(InstallableProperty installableProperty, View view) {
            Intrinsics.checkNotNullParameter(installableProperty, "$installableProperty");
            Function0 install = installableProperty.getInstall();
            if (install != null) {
                install.invoke();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(InstallableProperty installableProperty, View view) {
            Intrinsics.checkNotNullParameter(installableProperty, "$installableProperty");
            Function0 export = installableProperty.getExport();
            if (export != null) {
                export.invoke();
            }
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(GameProperty model) {
            Intrinsics.checkNotNullParameter(model, "model");
            final InstallableProperty installableProperty = (InstallableProperty) model;
            this.binding.title.setText(installableProperty.getTitleId());
            this.binding.description.setText(installableProperty.getDescriptionId());
            ImageView imageView = this.binding.icon;
            imageView.setImageDrawable(ResourcesCompat.getDrawable(imageView.getContext().getResources(), installableProperty.getIconId(), this.binding.icon.getContext().getTheme()));
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            Button buttonInstall = this.binding.buttonInstall;
            Intrinsics.checkNotNullExpressionValue(buttonInstall, "buttonInstall");
            ViewUtils.setVisible$default(viewUtils, buttonInstall, installableProperty.getInstall() != null, false, 2, null);
            this.binding.buttonInstall.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.GamePropertiesAdapter$InstallablePropertyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GamePropertiesAdapter.InstallablePropertyViewHolder.bind$lambda$0(InstallableProperty.this, view);
                }
            });
            Button buttonExport = this.binding.buttonExport;
            Intrinsics.checkNotNullExpressionValue(buttonExport, "buttonExport");
            ViewUtils.setVisible$default(viewUtils, buttonExport, installableProperty.getExport() != null, false, 2, null);
            this.binding.buttonExport.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.GamePropertiesAdapter$InstallablePropertyViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GamePropertiesAdapter.InstallablePropertyViewHolder.bind$lambda$1(InstallableProperty.this, view);
                }
            });
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class PropertyType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ PropertyType[] $VALUES;
        public static final PropertyType Submenu = new PropertyType("Submenu", 0);
        public static final PropertyType Installable = new PropertyType("Installable", 1);

        private static final /* synthetic */ PropertyType[] $values() {
            return new PropertyType[]{Submenu, Installable};
        }

        static {
            PropertyType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private PropertyType(String str, int i) {
        }

        public static PropertyType valueOf(String str) {
            return (PropertyType) Enum.valueOf(PropertyType.class, str);
        }

        public static PropertyType[] values() {
            return (PropertyType[]) $VALUES.clone();
        }
    }

    /* loaded from: classes.dex */
    public final class SubmenuPropertyViewHolder extends AbstractViewHolder {
        private final CardSimpleOutlinedBinding binding;
        final /* synthetic */ GamePropertiesAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubmenuPropertyViewHolder(GamePropertiesAdapter gamePropertiesAdapter, CardSimpleOutlinedBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = gamePropertiesAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(SubmenuProperty submenuProperty, View view) {
            Intrinsics.checkNotNullParameter(submenuProperty, "$submenuProperty");
            submenuProperty.getAction().invoke();
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(GameProperty model) {
            Intrinsics.checkNotNullParameter(model, "model");
            final SubmenuProperty submenuProperty = (SubmenuProperty) model;
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.GamePropertiesAdapter$SubmenuPropertyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GamePropertiesAdapter.SubmenuPropertyViewHolder.bind$lambda$0(SubmenuProperty.this, view);
                }
            });
            this.binding.title.setText(submenuProperty.getTitleId());
            this.binding.description.setText(submenuProperty.getDescriptionId());
            ImageView imageView = this.binding.icon;
            imageView.setImageDrawable(ResourcesCompat.getDrawable(imageView.getContext().getResources(), submenuProperty.getIconId(), this.binding.icon.getContext().getTheme()));
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView details = this.binding.details;
            Intrinsics.checkNotNullExpressionValue(details, "details");
            ViewUtils.marquee$default(viewUtils, details, 0L, 1, null);
            if (submenuProperty.getDetails() != null) {
                MaterialTextView details2 = this.binding.details;
                Intrinsics.checkNotNullExpressionValue(details2, "details");
                ViewUtils.setVisible$default(viewUtils, details2, true, false, 2, null);
                this.binding.details.setText((CharSequence) submenuProperty.getDetails().invoke());
                return;
            }
            if (submenuProperty.getDetailsFlow() == null) {
                MaterialTextView details3 = this.binding.details;
                Intrinsics.checkNotNullExpressionValue(details3, "details");
                ViewUtils.setVisible$default(viewUtils, details3, false, false, 2, null);
                return;
            }
            MaterialTextView details4 = this.binding.details;
            Intrinsics.checkNotNullExpressionValue(details4, "details");
            ViewUtils.setVisible$default(viewUtils, details4, true, false, 2, null);
            StateFlow detailsFlow = submenuProperty.getDetailsFlow();
            LifecycleOwner lifecycleOwner = this.this$0.viewLifecycle;
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new GamePropertiesAdapter$SubmenuPropertyViewHolder$bind$$inlined$collect$default$1(lifecycleOwner, Lifecycle.State.CREATED, detailsFlow, null, this), 3, null);
        }

        public final CardSimpleOutlinedBinding getBinding() {
            return this.binding;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GamePropertiesAdapter(LifecycleOwner viewLifecycle, List properties) {
        super(properties);
        Intrinsics.checkNotNullParameter(viewLifecycle, "viewLifecycle");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.viewLifecycle = viewLifecycle;
        this.properties = properties;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (((GameProperty) this.properties.get(i)) instanceof SubmenuProperty ? PropertyType.Submenu : PropertyType.Installable).ordinal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (i == PropertyType.Submenu.ordinal()) {
            CardSimpleOutlinedBinding inflate = CardSimpleOutlinedBinding.inflate(from, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new SubmenuPropertyViewHolder(this, inflate);
        }
        CardInstallableIconBinding inflate2 = CardInstallableIconBinding.inflate(from, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new InstallablePropertyViewHolder(this, inflate2);
    }
}
