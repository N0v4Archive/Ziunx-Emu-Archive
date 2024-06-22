package org.yuzu.yuzu_emu.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import androidx.preference.PreferenceManager;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.adapters.GameAdapter;
import org.yuzu.yuzu_emu.databinding.CardGameBinding;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.utils.GameIconUtils;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class GameAdapter extends AbstractDiffAdapter {
    private final AppCompatActivity activity;

    /* loaded from: classes.dex */
    public final class GameViewHolder extends AbstractViewHolder {
        private final CardGameBinding binding;
        final /* synthetic */ GameAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GameViewHolder(GameAdapter gameAdapter, CardGameBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = gameAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(GameViewHolder this$0, Game model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.onClick(model);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean bind$lambda$1(GameViewHolder this$0, Game model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            return this$0.onLongClick(model);
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final Game model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.binding.imageGameScreen.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GameIconUtils gameIconUtils = GameIconUtils.INSTANCE;
            ShapeableImageView imageGameScreen = this.binding.imageGameScreen;
            Intrinsics.checkNotNullExpressionValue(imageGameScreen, "imageGameScreen");
            gameIconUtils.loadGameIcon(model, imageGameScreen);
            this.binding.textGameTitle.setText(new Regex("[\\t\\n\\r]+").replace(model.getTitle(), " "));
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView textGameTitle = this.binding.textGameTitle;
            Intrinsics.checkNotNullExpressionValue(textGameTitle, "textGameTitle");
            ViewUtils.marquee$default(viewUtils, textGameTitle, 0L, 1, null);
            this.binding.cardGame.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.GameAdapter$GameViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameAdapter.GameViewHolder.bind$lambda$0(GameAdapter.GameViewHolder.this, model, view);
                }
            });
            this.binding.cardGame.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.yuzu.yuzu_emu.adapters.GameAdapter$GameViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    boolean bind$lambda$1;
                    bind$lambda$1 = GameAdapter.GameViewHolder.bind$lambda$1(GameAdapter.GameViewHolder.this, model, view);
                    return bind$lambda$1;
                }
            });
        }

        public final void onClick(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            YuzuApplication.Companion companion = YuzuApplication.Companion;
            DocumentFile fromSingleUri = DocumentFile.fromSingleUri(companion.getAppContext(), Uri.parse(game.getPath()));
            if (!(fromSingleUri != null && fromSingleUri.exists())) {
                Toast.makeText(companion.getAppContext(), R$string.loader_error_file_not_found, 1).show();
                GamesViewModel.reloadGames$default((GamesViewModel) new ViewModelProvider(this.this$0.activity).get(GamesViewModel.class), true, false, 2, null);
                return;
            }
            PreferenceManager.getDefaultSharedPreferences(companion.getAppContext()).edit().putLong(game.getKeyLastPlayedTime(), System.currentTimeMillis()).apply();
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.activity), null, null, new GameAdapter$GameViewHolder$onClick$1(game, this.this$0, null), 3, null);
            NavDirections actionGlobalEmulationActivity = HomeNavigationDirections.Companion.actionGlobalEmulationActivity(game, true);
            FrameLayout root = this.binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            ViewKt.findNavController(root).navigate(actionGlobalEmulationActivity);
        }

        public final boolean onLongClick(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            NavDirections actionGlobalPerGamePropertiesFragment = HomeNavigationDirections.Companion.actionGlobalPerGamePropertiesFragment(game);
            FrameLayout root = this.binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            ViewKt.findNavController(root).navigate(actionGlobalPerGamePropertiesFragment);
            return true;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GameAdapter(AppCompatActivity activity) {
        super(false);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardGameBinding inflate = CardGameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new GameViewHolder(this, inflate);
    }
}
