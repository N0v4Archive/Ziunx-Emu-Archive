package coil.fetch;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.fetch.Fetcher;
import coil.request.Options;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public final class BitmapFetcher implements Fetcher {
    private final Bitmap data;
    private final Options options;

    /* loaded from: classes.dex */
    public static final class Factory implements Fetcher.Factory {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Bitmap bitmap, Options options, ImageLoader imageLoader) {
            return new BitmapFetcher(bitmap, options);
        }
    }

    public BitmapFetcher(Bitmap bitmap, Options options) {
        this.data = bitmap;
        this.options = options;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(Continuation continuation) {
        return new DrawableResult(new BitmapDrawable(this.options.getContext().getResources(), this.data), false, DataSource.MEMORY);
    }
}
