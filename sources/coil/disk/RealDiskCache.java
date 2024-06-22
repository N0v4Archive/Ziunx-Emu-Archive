package coil.disk;

import coil.disk.DiskCache;
import coil.disk.DiskLruCache;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import okio.ByteString;
import okio.FileSystem;
import okio.Path;

/* loaded from: classes.dex */
public final class RealDiskCache implements DiskCache {
    public static final Companion Companion = new Companion(null);
    private final DiskLruCache cache;
    private final Path directory;
    private final FileSystem fileSystem;
    private final long maxSize;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class RealEditor implements DiskCache.Editor {
        private final DiskLruCache.Editor editor;

        public RealEditor(DiskLruCache.Editor editor) {
            this.editor = editor;
        }

        @Override // coil.disk.DiskCache.Editor
        public void abort() {
            this.editor.abort();
        }

        @Override // coil.disk.DiskCache.Editor
        public RealSnapshot commitAndGet() {
            DiskLruCache.Snapshot commitAndGet = this.editor.commitAndGet();
            if (commitAndGet != null) {
                return new RealSnapshot(commitAndGet);
            }
            return null;
        }

        @Override // coil.disk.DiskCache.Editor
        public Path getData() {
            return this.editor.file(1);
        }

        @Override // coil.disk.DiskCache.Editor
        public Path getMetadata() {
            return this.editor.file(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class RealSnapshot implements DiskCache.Snapshot {
        private final DiskLruCache.Snapshot snapshot;

        public RealSnapshot(DiskLruCache.Snapshot snapshot) {
            this.snapshot = snapshot;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.snapshot.close();
        }

        @Override // coil.disk.DiskCache.Snapshot
        public RealEditor closeAndEdit() {
            DiskLruCache.Editor closeAndEdit = this.snapshot.closeAndEdit();
            if (closeAndEdit != null) {
                return new RealEditor(closeAndEdit);
            }
            return null;
        }

        @Override // coil.disk.DiskCache.Snapshot
        public Path getData() {
            return this.snapshot.file(1);
        }

        @Override // coil.disk.DiskCache.Snapshot
        public Path getMetadata() {
            return this.snapshot.file(0);
        }
    }

    public RealDiskCache(long j, Path path, FileSystem fileSystem, CoroutineDispatcher coroutineDispatcher) {
        this.maxSize = j;
        this.directory = path;
        this.fileSystem = fileSystem;
        this.cache = new DiskLruCache(getFileSystem(), getDirectory(), coroutineDispatcher, getMaxSize(), 1, 2);
    }

    private final String hash(String str) {
        return ByteString.Companion.encodeUtf8(str).sha256().hex();
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Editor edit(String str) {
        DiskLruCache.Editor edit = this.cache.edit(hash(str));
        if (edit != null) {
            return new RealEditor(edit);
        }
        return null;
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Snapshot get(String str) {
        DiskLruCache.Snapshot snapshot = this.cache.get(hash(str));
        if (snapshot != null) {
            return new RealSnapshot(snapshot);
        }
        return null;
    }

    public Path getDirectory() {
        return this.directory;
    }

    @Override // coil.disk.DiskCache
    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    public long getMaxSize() {
        return this.maxSize;
    }
}
