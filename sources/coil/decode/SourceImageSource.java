package coil.decode;

import coil.decode.ImageSource;
import coil.util.Utils;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* loaded from: classes.dex */
public final class SourceImageSource extends ImageSource {
    private final File cacheDirectory;
    private Path file;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private BufferedSource source;

    public SourceImageSource(BufferedSource bufferedSource, File file, ImageSource.Metadata metadata) {
        super(null);
        this.cacheDirectory = file;
        this.metadata = metadata;
        this.source = bufferedSource;
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("cacheDirectory must be a directory.".toString());
        }
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
        Path path = this.file;
        if (path != null) {
            getFileSystem().delete(path);
        }
    }

    public FileSystem getFileSystem() {
        return FileSystem.SYSTEM;
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
        FileSystem fileSystem = getFileSystem();
        Path path = this.file;
        Intrinsics.checkNotNull(path);
        BufferedSource buffer = Okio.buffer(fileSystem.source(path));
        this.source = buffer;
        return buffer;
    }
}
