package coil.fetch;

import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.ImageSource;
import coil.decode.ImageSources;
import coil.fetch.Fetcher;
import coil.request.Options;
import java.io.File;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt__UtilsKt;
import okio.Path;

/* loaded from: classes.dex */
public final class FileFetcher implements Fetcher {
    private final File data;

    /* loaded from: classes.dex */
    public static final class Factory implements Fetcher.Factory {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(File file, Options options, ImageLoader imageLoader) {
            return new FileFetcher(file);
        }
    }

    public FileFetcher(File file) {
        this.data = file;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(Continuation continuation) {
        String extension;
        ImageSource create$default = ImageSources.create$default(Path.Companion.get$default(Path.Companion, this.data, false, 1, (Object) null), null, null, null, 14, null);
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        extension = FilesKt__UtilsKt.getExtension(this.data);
        return new SourceResult(create$default, singleton.getMimeTypeFromExtension(extension), DataSource.DISK);
    }
}
