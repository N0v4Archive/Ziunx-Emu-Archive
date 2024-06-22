package coil.decode;

import coil.decode.ImageSource;
import coil.util.Utils;
import java.io.Closeable;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* loaded from: classes.dex */
public final class FileImageSource extends ImageSource {
    private final Closeable closeable;
    private final String diskCacheKey;
    private final Path file;
    private final FileSystem fileSystem;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private BufferedSource source;

    public FileImageSource(Path path, FileSystem fileSystem, String str, Closeable closeable, ImageSource.Metadata metadata) {
        super(null);
        this.file = path;
        this.fileSystem = fileSystem;
        this.diskCacheKey = str;
        this.closeable = closeable;
        this.metadata = metadata;
    }

    private final void assertNotClosed() {
        if (!(!this.isClosed)) {
            throw new IllegalStateException("closed".toString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.isClosed = true;
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            Utils.closeQuietly(bufferedSource);
        }
        Closeable closeable = this.closeable;
        if (closeable != null) {
            Utils.closeQuietly(closeable);
        }
    }

    public final String getDiskCacheKey$coil_base_release() {
        return this.diskCacheKey;
    }

    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    @Override // coil.decode.ImageSource
    public ImageSource.Metadata getMetadata() {
        return this.metadata;
    }

    @Override // coil.decode.ImageSource
    public synchronized BufferedSource source() {
        assertNotClosed();
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            return bufferedSource;
        }
        BufferedSource buffer = Okio.buffer(getFileSystem().source(this.file));
        this.source = buffer;
        return buffer;
    }
}
