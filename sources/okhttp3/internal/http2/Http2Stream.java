package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class Http2Stream {
    public static final Companion Companion = new Companion(null);
    private final Http2Connection connection;
    private ErrorCode errorCode;
    private IOException errorException;
    private boolean hasResponseHeaders;
    private final ArrayDeque headersQueue;
    private final int id;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final StreamTimeout readTimeout;
    private final FramingSink sink;
    private final FramingSource source;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final StreamTimeout writeTimeout;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public final class FramingSink implements Sink {
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer;
        final /* synthetic */ Http2Stream this$0;
        private Headers trailers;

        public FramingSink(Http2Stream this$0, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.finished = z;
            this.sendBuffer = new Buffer();
        }

        private final void emitFrame(boolean z) {
            long min;
            boolean z2;
            Http2Stream http2Stream = this.this$0;
            synchronized (http2Stream) {
                http2Stream.getWriteTimeout$okhttp().enter();
                while (http2Stream.getWriteBytesTotal() >= http2Stream.getWriteBytesMaximum() && !getFinished() && !getClosed() && http2Stream.getErrorCode$okhttp() == null) {
                    try {
                        http2Stream.waitForIo$okhttp();
                    } finally {
                        http2Stream.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                    }
                }
                http2Stream.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                http2Stream.checkOutNotClosed$okhttp();
                min = Math.min(http2Stream.getWriteBytesMaximum() - http2Stream.getWriteBytesTotal(), this.sendBuffer.size());
                http2Stream.setWriteBytesTotal$okhttp(http2Stream.getWriteBytesTotal() + min);
                z2 = z && min == this.sendBuffer.size();
                Unit unit = Unit.INSTANCE;
            }
            this.this$0.getWriteTimeout$okhttp().enter();
            try {
                this.this$0.getConnection().writeData(this.this$0.getId(), z2, this.sendBuffer, min);
            } finally {
                http2Stream = this.this$0;
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Http2Stream http2Stream = this.this$0;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            Http2Stream http2Stream2 = this.this$0;
            synchronized (http2Stream2) {
                if (getClosed()) {
                    return;
                }
                boolean z = http2Stream2.getErrorCode$okhttp() == null;
                Unit unit = Unit.INSTANCE;
                if (!this.this$0.getSink$okhttp().finished) {
                    boolean z2 = this.sendBuffer.size() > 0;
                    if (this.trailers != null) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(false);
                        }
                        Http2Connection connection = this.this$0.getConnection();
                        int id = this.this$0.getId();
                        Headers headers = this.trailers;
                        Intrinsics.checkNotNull(headers);
                        connection.writeHeaders$okhttp(id, z, Util.toHeaderList(headers));
                    } else if (z2) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(true);
                        }
                    } else if (z) {
                        this.this$0.getConnection().writeData(this.this$0.getId(), true, null, 0L);
                    }
                }
                synchronized (this.this$0) {
                    setClosed(true);
                    Unit unit2 = Unit.INSTANCE;
                }
                this.this$0.getConnection().flush();
                this.this$0.cancelStreamIfNecessary$okhttp();
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            Http2Stream http2Stream = this.this$0;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            Http2Stream http2Stream2 = this.this$0;
            synchronized (http2Stream2) {
                http2Stream2.checkOutNotClosed$okhttp();
                Unit unit = Unit.INSTANCE;
            }
            while (this.sendBuffer.size() > 0) {
                emitFrame(false);
                this.this$0.getConnection().flush();
            }
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final boolean getFinished() {
            return this.finished;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.this$0.getWriteTimeout$okhttp();
        }

        @Override // okio.Sink
        public void write(Buffer source, long j) {
            Intrinsics.checkNotNullParameter(source, "source");
            Http2Stream http2Stream = this.this$0;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                this.sendBuffer.write(source, j);
                while (this.sendBuffer.size() >= 16384) {
                    emitFrame(false);
                }
            } else {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class FramingSource implements Source {
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;
        final /* synthetic */ Http2Stream this$0;
        private Headers trailers;

        public FramingSource(Http2Stream this$0, long j, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.maxByteCount = j;
            this.finished = z;
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
        }

        private final void updateConnectionFlowControl(long j) {
            Http2Stream http2Stream = this.this$0;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                this.this$0.getConnection().updateConnectionFlowControl$okhttp(j);
                return;
            }
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long size;
            Http2Stream http2Stream = this.this$0;
            synchronized (http2Stream) {
                setClosed$okhttp(true);
                size = getReadBuffer().size();
                getReadBuffer().clear();
                http2Stream.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            if (size > 0) {
                updateConnectionFlowControl(size);
            }
            this.this$0.cancelStreamIfNecessary$okhttp();
        }

        public final boolean getClosed$okhttp() {
            return this.closed;
        }

        public final boolean getFinished$okhttp() {
            return this.finished;
        }

        public final Buffer getReadBuffer() {
            return this.readBuffer;
        }

        public final Buffer getReceiveBuffer() {
            return this.receiveBuffer;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x00cf, code lost:
        
            throw new java.io.IOException("stream closed");
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long read(okio.Buffer r18, long r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                r2 = r19
                java.lang.String r4 = "sink"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 < 0) goto L13
                r6 = 1
                goto L14
            L13:
                r6 = 0
            L14:
                if (r6 == 0) goto Ldc
            L16:
                okhttp3.internal.http2.Http2Stream r6 = r0.this$0
                monitor-enter(r6)
                okhttp3.internal.http2.Http2Stream$StreamTimeout r9 = r6.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Ld9
                r9.enter()     // Catch: java.lang.Throwable -> Ld9
                okhttp3.internal.http2.ErrorCode r9 = r6.getErrorCode$okhttp()     // Catch: java.lang.Throwable -> Ld0
                if (r9 == 0) goto L39
                java.io.IOException r9 = r6.getErrorException$okhttp()     // Catch: java.lang.Throwable -> Ld0
                if (r9 != 0) goto L3a
                okhttp3.internal.http2.StreamResetException r9 = new okhttp3.internal.http2.StreamResetException     // Catch: java.lang.Throwable -> Ld0
                okhttp3.internal.http2.ErrorCode r10 = r6.getErrorCode$okhttp()     // Catch: java.lang.Throwable -> Ld0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Throwable -> Ld0
                r9.<init>(r10)     // Catch: java.lang.Throwable -> Ld0
                goto L3a
            L39:
                r9 = 0
            L3a:
                boolean r10 = r17.getClosed$okhttp()     // Catch: java.lang.Throwable -> Ld0
                if (r10 != 0) goto Lc8
                okio.Buffer r10 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld0
                long r10 = r10.size()     // Catch: java.lang.Throwable -> Ld0
                int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
                r11 = -1
                if (r10 <= 0) goto L9c
                okio.Buffer r10 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld0
                okio.Buffer r13 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld0
                long r13 = r13.size()     // Catch: java.lang.Throwable -> Ld0
                long r13 = java.lang.Math.min(r2, r13)     // Catch: java.lang.Throwable -> Ld0
                long r13 = r10.read(r1, r13)     // Catch: java.lang.Throwable -> Ld0
                long r15 = r6.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld0
                long r4 = r15 + r13
                r6.setReadBytesTotal$okhttp(r4)     // Catch: java.lang.Throwable -> Ld0
                long r4 = r6.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld0
                long r15 = r6.getReadBytesAcknowledged()     // Catch: java.lang.Throwable -> Ld0
                long r4 = r4 - r15
                if (r9 != 0) goto Lab
                okhttp3.internal.http2.Http2Connection r10 = r6.getConnection()     // Catch: java.lang.Throwable -> Ld0
                okhttp3.internal.http2.Settings r10 = r10.getOkHttpSettings()     // Catch: java.lang.Throwable -> Ld0
                int r10 = r10.getInitialWindowSize()     // Catch: java.lang.Throwable -> Ld0
                int r10 = r10 / 2
                long r7 = (long) r10     // Catch: java.lang.Throwable -> Ld0
                int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r7 < 0) goto Lab
                okhttp3.internal.http2.Http2Connection r7 = r6.getConnection()     // Catch: java.lang.Throwable -> Ld0
                int r8 = r6.getId()     // Catch: java.lang.Throwable -> Ld0
                r7.writeWindowUpdateLater$okhttp(r8, r4)     // Catch: java.lang.Throwable -> Ld0
                long r4 = r6.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld0
                r6.setReadBytesAcknowledged$okhttp(r4)     // Catch: java.lang.Throwable -> Ld0
                goto Lab
            L9c:
                boolean r4 = r17.getFinished$okhttp()     // Catch: java.lang.Throwable -> Ld0
                if (r4 != 0) goto Laa
                if (r9 != 0) goto Laa
                r6.waitForIo$okhttp()     // Catch: java.lang.Throwable -> Ld0
                r13 = r11
                r4 = 1
                goto Lac
            Laa:
                r13 = r11
            Lab:
                r4 = 0
            Lac:
                okhttp3.internal.http2.Http2Stream$StreamTimeout r5 = r6.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Ld9
                r5.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> Ld9
                kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Ld9
                monitor-exit(r6)
                if (r4 == 0) goto Lbc
                r4 = 0
                goto L16
            Lbc:
                int r1 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
                if (r1 == 0) goto Lc4
                r0.updateConnectionFlowControl(r13)
                return r13
            Lc4:
                if (r9 != 0) goto Lc7
                return r11
            Lc7:
                throw r9
            Lc8:
                java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> Ld0
                java.lang.String r1 = "stream closed"
                r0.<init>(r1)     // Catch: java.lang.Throwable -> Ld0
                throw r0     // Catch: java.lang.Throwable -> Ld0
            Ld0:
                r0 = move-exception
                okhttp3.internal.http2.Http2Stream$StreamTimeout r1 = r6.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Ld9
                r1.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> Ld9
                throw r0     // Catch: java.lang.Throwable -> Ld9
            Ld9:
                r0 = move-exception
                monitor-exit(r6)
                throw r0
            Ldc:
                java.lang.String r0 = "byteCount < 0: "
                java.lang.Long r1 = java.lang.Long.valueOf(r19)
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r1)
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        public final void receive$okhttp(BufferedSource source, long j) {
            boolean finished$okhttp;
            boolean z;
            boolean z2;
            long j2;
            Intrinsics.checkNotNullParameter(source, "source");
            Http2Stream http2Stream = this.this$0;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            while (j > 0) {
                synchronized (this.this$0) {
                    finished$okhttp = getFinished$okhttp();
                    z = true;
                    z2 = getReadBuffer().size() + j > this.maxByteCount;
                    Unit unit = Unit.INSTANCE;
                }
                if (z2) {
                    source.skip(j);
                    this.this$0.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (finished$okhttp) {
                    source.skip(j);
                    return;
                }
                long read = source.read(this.receiveBuffer, j);
                if (read == -1) {
                    throw new EOFException();
                }
                j -= read;
                Http2Stream http2Stream2 = this.this$0;
                synchronized (http2Stream2) {
                    if (getClosed$okhttp()) {
                        j2 = getReceiveBuffer().size();
                        getReceiveBuffer().clear();
                    } else {
                        if (getReadBuffer().size() != 0) {
                            z = false;
                        }
                        getReadBuffer().writeAll(getReceiveBuffer());
                        if (z) {
                            http2Stream2.notifyAll();
                        }
                        j2 = 0;
                    }
                }
                if (j2 > 0) {
                    updateConnectionFlowControl(j2);
                }
            }
        }

        public final void setClosed$okhttp(boolean z) {
            this.closed = z;
        }

        public final void setFinished$okhttp(boolean z) {
            this.finished = z;
        }

        public final void setTrailers(Headers headers) {
            this.trailers = headers;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.this$0.getReadTimeout$okhttp();
        }
    }

    /* loaded from: classes.dex */
    public final class StreamTimeout extends AsyncTimeout {
        final /* synthetic */ Http2Stream this$0;

        public StreamTimeout(Http2Stream this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        public final void exitAndThrowIfTimedOut() {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // okio.AsyncTimeout
        protected IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        protected void timedOut() {
            this.this$0.closeLater(ErrorCode.CANCEL);
            this.this$0.getConnection().sendDegradedPingLater$okhttp();
        }
    }

    public Http2Stream(int i, Http2Connection connection, boolean z, boolean z2, Headers headers) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.id = i;
        this.connection = connection;
        this.writeBytesMaximum = connection.getPeerSettings().getInitialWindowSize();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.headersQueue = arrayDeque;
        this.source = new FramingSource(this, connection.getOkHttpSettings().getInitialWindowSize(), z2);
        this.sink = new FramingSink(this, z);
        this.readTimeout = new StreamTimeout(this);
        this.writeTimeout = new StreamTimeout(this);
        boolean isLocallyInitiated = isLocallyInitiated();
        if (headers == null) {
            if (!isLocallyInitiated) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!isLocallyInitiated)) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(headers);
        }
    }

    private final boolean closeInternal(ErrorCode errorCode, IOException iOException) {
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            if (getErrorCode$okhttp() != null) {
                return false;
            }
            if (getSource$okhttp().getFinished$okhttp() && getSink$okhttp().getFinished()) {
                return false;
            }
            setErrorCode$okhttp(errorCode);
            setErrorException$okhttp(iOException);
            notifyAll();
            Unit unit = Unit.INSTANCE;
            this.connection.removeStream$okhttp(this.id);
            return true;
        }
    }

    public final void addBytesToWriteWindow(long j) {
        this.writeBytesMaximum += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public final void cancelStreamIfNecessary$okhttp() {
        boolean z;
        boolean isOpen;
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            z = !getSource$okhttp().getFinished$okhttp() && getSource$okhttp().getClosed$okhttp() && (getSink$okhttp().getFinished() || getSink$okhttp().getClosed());
            isOpen = isOpen();
            Unit unit = Unit.INSTANCE;
        }
        if (z) {
            close(ErrorCode.CANCEL, null);
        } else {
            if (isOpen) {
                return;
            }
            this.connection.removeStream$okhttp(this.id);
        }
    }

    public final void checkOutNotClosed$okhttp() {
        if (this.sink.getClosed()) {
            throw new IOException("stream closed");
        }
        if (this.sink.getFinished()) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            IOException iOException = this.errorException;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.errorCode;
            Intrinsics.checkNotNull(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    public final void close(ErrorCode rstStatusCode, IOException iOException) {
        Intrinsics.checkNotNullParameter(rstStatusCode, "rstStatusCode");
        if (closeInternal(rstStatusCode, iOException)) {
            this.connection.writeSynReset$okhttp(this.id, rstStatusCode);
        }
    }

    public final void closeLater(ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (closeInternal(errorCode, null)) {
            this.connection.writeSynResetLater$okhttp(this.id, errorCode);
        }
    }

    public final Http2Connection getConnection() {
        return this.connection;
    }

    public final synchronized ErrorCode getErrorCode$okhttp() {
        return this.errorCode;
    }

    public final IOException getErrorException$okhttp() {
        return this.errorException;
    }

    public final int getId() {
        return this.id;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final StreamTimeout getReadTimeout$okhttp() {
        return this.readTimeout;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011 A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:10:0x0011, B:15:0x0017, B:16:0x0022), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0017 A[Catch: all -> 0x0023, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:10:0x0011, B:15:0x0017, B:16:0x0022), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okio.Sink getSink() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.hasResponseHeaders     // Catch: java.lang.Throwable -> L23
            if (r0 != 0) goto Le
            boolean r0 = r2.isLocallyInitiated()     // Catch: java.lang.Throwable -> L23
            if (r0 == 0) goto Lc
            goto Le
        Lc:
            r0 = 0
            goto Lf
        Le:
            r0 = 1
        Lf:
            if (r0 == 0) goto L17
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L23
            monitor-exit(r2)
            okhttp3.internal.http2.Http2Stream$FramingSink r2 = r2.sink
            return r2
        L17:
            java.lang.String r0 = "reply before requesting the sink"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L23
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L23
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L23
            throw r1     // Catch: java.lang.Throwable -> L23
        L23:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.getSink():okio.Sink");
    }

    public final FramingSink getSink$okhttp() {
        return this.sink;
    }

    public final FramingSource getSource$okhttp() {
        return this.source;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final StreamTimeout getWriteTimeout$okhttp() {
        return this.writeTimeout;
    }

    public final boolean isLocallyInitiated() {
        return this.connection.getClient$okhttp() == ((this.id & 1) == 1);
    }

    public final synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.getFinished$okhttp() || this.source.getClosed$okhttp()) && (this.sink.getFinished() || this.sink.getClosed())) {
            if (this.hasResponseHeaders) {
                return false;
            }
        }
        return true;
    }

    public final Timeout readTimeout() {
        return this.readTimeout;
    }

    public final void receiveData(BufferedSource source, int i) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            this.source.receive$okhttp(source, i);
            return;
        }
        throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0051 A[Catch: all -> 0x006c, TryCatch #0 {, blocks: (B:10:0x0038, B:14:0x0040, B:16:0x0051, B:17:0x0058, B:24:0x0048), top: B:9:0x0038 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void receiveHeaders(okhttp3.Headers r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = "headers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r0 = okhttp3.internal.Util.assertionsEnabled
            if (r0 == 0) goto L37
            boolean r0 = java.lang.Thread.holdsLock(r2)
            if (r0 != 0) goto L10
            goto L37
        L10:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Thread "
            r4.append(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r0 = r0.getName()
            r4.append(r0)
            java.lang.String r0 = " MUST NOT hold lock on "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r3.<init>(r2)
            throw r3
        L37:
            monitor-enter(r2)
            boolean r0 = r2.hasResponseHeaders     // Catch: java.lang.Throwable -> L6c
            r1 = 1
            if (r0 == 0) goto L48
            if (r4 != 0) goto L40
            goto L48
        L40:
            okhttp3.internal.http2.Http2Stream$FramingSource r0 = r2.getSource$okhttp()     // Catch: java.lang.Throwable -> L6c
            r0.setTrailers(r3)     // Catch: java.lang.Throwable -> L6c
            goto L4f
        L48:
            r2.hasResponseHeaders = r1     // Catch: java.lang.Throwable -> L6c
            java.util.ArrayDeque r0 = r2.headersQueue     // Catch: java.lang.Throwable -> L6c
            r0.add(r3)     // Catch: java.lang.Throwable -> L6c
        L4f:
            if (r4 == 0) goto L58
            okhttp3.internal.http2.Http2Stream$FramingSource r3 = r2.getSource$okhttp()     // Catch: java.lang.Throwable -> L6c
            r3.setFinished$okhttp(r1)     // Catch: java.lang.Throwable -> L6c
        L58:
            boolean r3 = r2.isOpen()     // Catch: java.lang.Throwable -> L6c
            r2.notifyAll()     // Catch: java.lang.Throwable -> L6c
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L6c
            monitor-exit(r2)
            if (r3 != 0) goto L6b
            okhttp3.internal.http2.Http2Connection r3 = r2.connection
            int r2 = r2.id
            r3.removeStream$okhttp(r2)
        L6b:
            return
        L6c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.receiveHeaders(okhttp3.Headers, boolean):void");
    }

    public final synchronized void receiveRstStream(ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    public final void setErrorCode$okhttp(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public final void setErrorException$okhttp(IOException iOException) {
        this.errorException = iOException;
    }

    public final void setReadBytesAcknowledged$okhttp(long j) {
        this.readBytesAcknowledged = j;
    }

    public final void setReadBytesTotal$okhttp(long j) {
        this.readBytesTotal = j;
    }

    public final void setWriteBytesTotal$okhttp(long j) {
        this.writeBytesTotal = j;
    }

    public final synchronized Headers takeHeaders() {
        Object removeFirst;
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            try {
                waitForIo$okhttp();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!(!this.headersQueue.isEmpty())) {
            IOException iOException = this.errorException;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.errorCode;
            Intrinsics.checkNotNull(errorCode);
            throw new StreamResetException(errorCode);
        }
        removeFirst = this.headersQueue.removeFirst();
        Intrinsics.checkNotNullExpressionValue(removeFirst, "headersQueue.removeFirst()");
        return (Headers) removeFirst;
    }

    public final void waitForIo$okhttp() {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public final Timeout writeTimeout() {
        return this.writeTimeout;
    }
}
