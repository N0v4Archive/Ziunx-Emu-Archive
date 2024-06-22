package coil.fetch;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.decode.AssetMetadata;
import coil.decode.DataSource;
import coil.decode.ImageSources;
import coil.fetch.Fetcher;
import coil.request.Options;
import coil.util.Utils;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import okio.Okio;

/* loaded from: classes.dex */
public final class AssetUriFetcher implements Fetcher {
    private final Uri data;
    private final Options options;

    /* loaded from: classes.dex */
    public static final class Factory implements Fetcher.Factory {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri uri, Options options, ImageLoader imageLoader) {
            if (Utils.isAssetUri(uri)) {
                return new AssetUriFetcher(uri, options);
            }
            return null;
        }
    }

    public AssetUriFetcher(Uri uri, Options options) {
        this.data = uri;
        this.options = options;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(Continuation continuation) {
        List drop;
        String joinToString$default;
        drop = CollectionsKt___CollectionsKt.drop(this.data.getPathSegments(), 1);
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(drop, "/", null, null, 0, null, null, 62, null);
        return new SourceResult(ImageSources.create(Okio.buffer(Okio.source(this.options.getContext().getAssets().open(joinToString$default))), this.options.getContext(), new AssetMetadata(joinToString$default)), Utils.getMimeTypeFromUrl(MimeTypeMap.getSingleton(), joinToString$default), DataSource.DISK);
    }
}
