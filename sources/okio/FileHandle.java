package okio;

import java.io.Closeable;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class FileHandle implements Closeable {
    private boolean closed;
    private int openStreamCount;
    private final boolean readWrite;

    /* loaded from: classes.dex */
    private static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle, long j) {
            Intrinsics.checkNotNullParameter(fileHandle, "fileHandle");
            this.fileHandle = fileHandle;
            this.position = j;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            synchronized (this.fileHandle) {
                FileHandle fileHandle = this.fileHandle;
                fileHandle.openStreamCount--;
                if (this.fileHandle.openStreamCount == 0 && this.fileHandle.closed) {
                    Unit unit = Unit.INSTANCE;
                    this.fileHandle.protectedClose();
                }
            }
        }

        @Override // okio.Source
        public long read(Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!(!this.closed)) {
                throw new IllegalStateException("closed".toString());
            }
            long readNoCloseCheck = this.fileHandle.readNoCloseCheck(this.position, sink, j);
            if (readNoCloseCheck != -1) {
                this.position += readNoCloseCheck;
            }
            return readNoCloseCheck;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return Timeout.NONE;
        }
    }

    public FileHandle(boolean z) {
        this.readWrite = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long readNoCloseCheck(long j, Buffer buffer, long j2) {
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
        }
        long j3 = j + j2;
        long j4 = j;
        while (true) {
            if (j4 >= j3) {
                break;
            }
            Segment writableSegment$okio = buffer.writableSegment$okio(1);
            int protectedRead = protectedRead(j4, writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j3 - j4, 8192 - r9));
            if (protectedRead == -1) {
                if (writableSegment$okio.pos == writableSegment$okio.limit) {
                    buffer.head = writableSegment$okio.pop();
                    SegmentPool.recycle(writableSegment$okio);
                }
                if (j == j4) {
                    return -1L;
                }
            } else {
                writableSegment$okio.limit += protectedRead;
                long j5 = protectedRead;
                j4 += j5;
                buffer.setSize$okio(buffer.size() + j5);
            }
        }
        return j4 - j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.openStreamCount != 0) {
                return;
            }
            Unit unit = Unit.INSTANCE;
            protectedClose();
        }
    }

    protected abstract void protectedClose();

    protected abstract int protectedRead(long j, byte[] bArr, int i, int i2);

    protected abstract long protectedSize();

    public final long size() {
        synchronized (this) {
            if (!(!this.closed)) {
                throw new IllegalStateException("closed".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        return protectedSize();
    }

    public final Source source(long j) {
        synchronized (this) {
            if (!(!this.closed)) {
                throw new IllegalStateException("closed".toString());
            }
            this.openStreamCount++;
        }
        return new FileHandleSource(this, j);
    }
}
