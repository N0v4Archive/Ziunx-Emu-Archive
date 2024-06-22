package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import com.google.android.material.card.MaterialCardView;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.AppletAdapter;
import org.yuzu.yuzu_emu.databinding.CardSimpleOutlinedBinding;
import org.yuzu.yuzu_emu.model.Applet;
import org.yuzu.yuzu_emu.model.AppletInfo;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class AppletAdapter extends AbstractListAdapter {
    private final FragmentActivity activity;

    /* loaded from: classes.dex */
    public final class AppletViewHolder extends AbstractViewHolder {
        private final CardSimpleOutlinedBinding binding;
        final /* synthetic */ AppletAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AppletViewHolder(AppletAdapter appletAdapter, CardSimpleOutlinedBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = appletAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(AppletViewHolder this$0, Applet model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.onClick(model);
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final Applet model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.binding.title.setText(model.getTitleId());
            this.binding.description.setText(model.getDescriptionId());
            ImageView imageView = this.binding.icon;
            imageView.setImageDrawable(ResourcesCompat.getDrawable(imageView.getContext().getResources(), model.getIconId(), this.binding.icon.getContext().getTheme()));
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.AppletAdapter$AppletViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppletAdapter.AppletViewHolder.bind$lambda$0(AppletAdapter.AppletViewHolder.this, model, view);
                }
            });
        }

        public final void onClick(Applet applet) {
            Intrinsics.checkNotNullParameter(applet, "applet");
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            String appletLaunchPath = nativeLibrary.getAppletLaunchPath(applet.getAppletInfo().getEntryId());
            if (appletLaunchPath.length() == 0) {
                Toast.makeText(this.binding.getRoot().getContext(), R$string.applets_error_applet, 0).show();
                return;
            }
            if (applet.getAppletInfo() == AppletInfo.Cabinet) {
                MaterialCardView root = this.binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                ViewKt.findNavController(root).navigate(R$id.action_appletLauncherFragment_to_cabinetLauncherDialogFragment);
                return;
            }
            nativeLibrary.setCurrentAppletId(applet.getAppletInfo().getAppletId());
            String string = YuzuApplication.Companion.getAppContext().getString(applet.getTitleId());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            NavDirections actionGlobalEmulationActivity$default = HomeNavigationDirections.Companion.actionGlobalEmulationActivity$default(HomeNavigationDirections.Companion, new Game(string, appletLaunchPath, (String) null, (String) null, (String) null, false, 60, (DefaultConstructorMarker) null), false, 2, null);
            MaterialCardView root2 = this.binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
            ViewKt.findNavController(root2).navigate(actionGlobalEmulationActivity$default);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppletAdapter(FragmentActivity activity, List applets) {
        super(applets);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(applets, "applets");
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AppletViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardSimpleOutlinedBinding inflate = CardSimpleOutlinedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new AppletViewHolder(this, inflate);
    }
}
