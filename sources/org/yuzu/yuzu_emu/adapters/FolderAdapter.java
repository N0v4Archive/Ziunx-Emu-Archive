package org.yuzu.yuzu_emu.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.FolderAdapter;
import org.yuzu.yuzu_emu.databinding.CardFolderBinding;
import org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment;
import org.yuzu.yuzu_emu.model.GameDir;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class FolderAdapter extends AbstractDiffAdapter {
    private final FragmentActivity activity;
    private final GamesViewModel gamesViewModel;

    /* loaded from: classes.dex */
    public final class FolderViewHolder extends AbstractViewHolder {
        private final CardFolderBinding binding;
        final /* synthetic */ FolderAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FolderViewHolder(FolderAdapter folderAdapter, CardFolderBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = folderAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$0(GameDir model, FolderAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            GameFolderPropertiesDialogFragment.Companion.newInstance(model).show(this$0.getActivity().getSupportFragmentManager(), "GameFolderPropertiesDialogFragment");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$1(FolderAdapter this$0, GameDir model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.getGamesViewModel().removeFolder(model);
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final GameDir model) {
            Intrinsics.checkNotNullParameter(model, "model");
            CardFolderBinding cardFolderBinding = this.binding;
            final FolderAdapter folderAdapter = this.this$0;
            cardFolderBinding.path.setText(Uri.parse(model.getUriString()).getPath());
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView path = cardFolderBinding.path;
            Intrinsics.checkNotNullExpressionValue(path, "path");
            ViewUtils.marquee$default(viewUtils, path, 0L, 1, null);
            cardFolderBinding.buttonEdit.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.FolderAdapter$FolderViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FolderAdapter.FolderViewHolder.bind$lambda$2$lambda$0(GameDir.this, folderAdapter, view);
                }
            });
            cardFolderBinding.buttonDelete.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.FolderAdapter$FolderViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FolderAdapter.FolderViewHolder.bind$lambda$2$lambda$1(FolderAdapter.this, model, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FolderAdapter(FragmentActivity activity, GamesViewModel gamesViewModel) {
        super(false, 1, null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(gamesViewModel, "gamesViewModel");
        this.activity = activity;
        this.gamesViewModel = gamesViewModel;
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final GamesViewModel getGamesViewModel() {
        return this.gamesViewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardFolderBinding inflate = CardFolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new FolderViewHolder(this, inflate);
    }
}
