package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.CabinetLauncherDialogAdapter;
import org.yuzu.yuzu_emu.databinding.DialogListItemBinding;
import org.yuzu.yuzu_emu.model.AppletInfo;
import org.yuzu.yuzu_emu.model.CabinetMode;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class CabinetLauncherDialogAdapter extends AbstractListAdapter {
    private final Fragment fragment;

    /* loaded from: classes.dex */
    public final class CabinetModeViewHolder extends AbstractViewHolder {
        private final DialogListItemBinding binding;
        final /* synthetic */ CabinetLauncherDialogAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CabinetModeViewHolder(CabinetLauncherDialogAdapter cabinetLauncherDialogAdapter, DialogListItemBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = cabinetLauncherDialogAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(CabinetModeViewHolder this$0, CabinetMode model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.onClick(model);
        }

        private final void onClick(CabinetMode cabinetMode) {
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            AppletInfo appletInfo = AppletInfo.Cabinet;
            String appletLaunchPath = nativeLibrary.getAppletLaunchPath(appletInfo.getEntryId());
            nativeLibrary.setCurrentAppletId(appletInfo.getAppletId());
            nativeLibrary.setCabinetMode(cabinetMode.getId());
            String string = YuzuApplication.Companion.getAppContext().getString(R$string.cabinet_applet);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            FragmentKt.findNavController(this.this$0.getFragment()).navigate(HomeNavigationDirections.Companion.actionGlobalEmulationActivity$default(HomeNavigationDirections.Companion, new Game(string, appletLaunchPath, (String) null, (String) null, (String) null, false, 60, (DefaultConstructorMarker) null), false, 2, null));
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final CabinetMode model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ImageView imageView = this.binding.icon;
            imageView.setImageDrawable(ResourcesCompat.getDrawable(imageView.getContext().getResources(), model.getIconId(), this.binding.icon.getContext().getTheme()));
            this.binding.title.setText(model.getTitleId());
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.CabinetLauncherDialogAdapter$CabinetModeViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CabinetLauncherDialogAdapter.CabinetModeViewHolder.bind$lambda$0(CabinetLauncherDialogAdapter.CabinetModeViewHolder.this, model, view);
                }
            });
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public CabinetLauncherDialogAdapter(androidx.fragment.app.Fragment r4) {
        /*
            r3 = this;
            java.lang.String r0 = "fragment"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            org.yuzu.yuzu_emu.model.CabinetMode[] r0 = org.yuzu.yuzu_emu.model.CabinetMode.values()
            kotlin.enums.EnumEntries r1 = org.yuzu.yuzu_emu.model.CabinetMode.getEntries()
            int r1 = r1.size()
            r2 = 1
            java.lang.Object[] r0 = kotlin.collections.ArraysKt.copyOfRange(r0, r2, r1)
            java.util.List r0 = kotlin.collections.ArraysKt.toList(r0)
            r3.<init>(r0)
            r3.fragment = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.adapters.CabinetLauncherDialogAdapter.<init>(androidx.fragment.app.Fragment):void");
    }

    public final Fragment getFragment() {
        return this.fragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CabinetModeViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        DialogListItemBinding inflate = DialogListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new CabinetModeViewHolder(this, inflate);
    }
}
