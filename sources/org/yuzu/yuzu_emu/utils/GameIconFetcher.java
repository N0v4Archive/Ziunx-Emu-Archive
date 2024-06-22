package org.yuzu.yuzu_emu.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.fetch.DrawableResult;
import coil.fetch.Fetcher;
import coil.request.Options;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class GameIconFetcher implements Fetcher {
    private final Game game;
    private final Options options;

    /* loaded from: classes.dex */
    public static final class Factory implements Fetcher.Factory {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Game data, Options options, ImageLoader imageLoader) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
            return new GameIconFetcher(data, options);
        }
    }

    public GameIconFetcher(Game game, Options options) {
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(options, "options");
        this.game = game;
        this.options = options;
    }

    private final Bitmap decodeGameIcon(String str) {
        byte[] icon = GameMetadata.INSTANCE.getIcon(str);
        return BitmapFactory.decodeByteArray(icon, 0, icon.length, new BitmapFactory.Options());
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(Continuation continuation) {
        Bitmap decodeGameIcon = decodeGameIcon(this.game.getPath());
        Intrinsics.checkNotNull(decodeGameIcon);
        Resources resources = this.options.getContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        return new DrawableResult(new BitmapDrawable(resources, decodeGameIcon), false, DataSource.DISK);
    }
}
