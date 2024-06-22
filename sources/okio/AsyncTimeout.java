package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    public static final Companion Companion = new Companion(null);
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final long IDLE_TIMEOUT_NANOS;
    private static AsyncTimeout head;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
            synchronized (AsyncTimeout.class) {
                if (!asyncTimeout.inQueue) {
                    return false;
                }
                asyncTimeout.inQueue = false;
                for (AsyncTimeout asyncTimeout2 = AsyncTimeout.head; asyncTimeout2 != null; asyncTimeout2 = asyncTimeout2.next) {
                    if (asyncTimeout2.next == asyncTimeout) {
                        asyncTimeout2.next = asyncTimeout.next;
                        asyncTimeout.next = null;
                        return false;
                    }
                }
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:17:0x005b A[Catch: all -> 0x009b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000b, B:8:0x0014, B:9:0x0024, B:12:0x0030, B:13:0x0039, B:14:0x004a, B:15:0x0052, B:17:0x005b, B:19:0x006b, B:22:0x0070, B:24:0x0080, B:25:0x0085, B:33:0x0043, B:34:0x0089, B:35:0x008e, B:36:0x008f, B:37:0x009a), top: B:3:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0080 A[Catch: all -> 0x009b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000b, B:8:0x0014, B:9:0x0024, B:12:0x0030, B:13:0x0039, B:14:0x004a, B:15:0x0052, B:17:0x005b, B:19:0x006b, B:22:0x0070, B:24:0x0080, B:25:0x0085, B:33:0x0043, B:34:0x0089, B:35:0x008e, B:36:0x008f, B:37:0x009a), top: B:3:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0070 A[EDGE_INSN: B:29:0x0070->B:22:0x0070 BREAK  A[LOOP:0: B:15:0x0052->B:19:0x006b], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void scheduleTimeout(okio.AsyncTimeout r5, long r6, boolean r8) {
            /*
                r4 = this;
                java.lang.Class<okio.AsyncTimeout> r4 = okio.AsyncTimeout.class
                monitor-enter(r4)
                boolean r0 = okio.AsyncTimeout.access$getInQueue$p(r5)     // Catch: java.lang.Throwable -> L9b
                r1 = 1
                r0 = r0 ^ r1
                if (r0 == 0) goto L8f
                okio.AsyncTimeout.access$setInQueue$p(r5, r1)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout r0 = okio.AsyncTimeout.access$getHead$cp()     // Catch: java.lang.Throwable -> L9b
                if (r0 != 0) goto L24
                okio.AsyncTimeout r0 = new okio.AsyncTimeout     // Catch: java.lang.Throwable -> L9b
                r0.<init>()     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout.access$setHead$cp(r0)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout$Watchdog r0 = new okio.AsyncTimeout$Watchdog     // Catch: java.lang.Throwable -> L9b
                r0.<init>()     // Catch: java.lang.Throwable -> L9b
                r0.start()     // Catch: java.lang.Throwable -> L9b
            L24:
                long r0 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L9b
                r2 = 0
                int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r2 == 0) goto L3e
                if (r8 == 0) goto L3e
                long r2 = r5.deadlineNanoTime()     // Catch: java.lang.Throwable -> L9b
                long r2 = r2 - r0
                long r6 = java.lang.Math.min(r6, r2)     // Catch: java.lang.Throwable -> L9b
            L39:
                long r6 = r6 + r0
                okio.AsyncTimeout.access$setTimeoutAt$p(r5, r6)     // Catch: java.lang.Throwable -> L9b
                goto L4a
            L3e:
                if (r2 == 0) goto L41
                goto L39
            L41:
                if (r8 == 0) goto L89
                long r6 = r5.deadlineNanoTime()     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout.access$setTimeoutAt$p(r5, r6)     // Catch: java.lang.Throwable -> L9b
            L4a:
                long r6 = okio.AsyncTimeout.access$remainingNanos(r5, r0)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout r8 = okio.AsyncTimeout.access$getHead$cp()     // Catch: java.lang.Throwable -> L9b
            L52:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout r2 = okio.AsyncTimeout.access$getNext$p(r8)     // Catch: java.lang.Throwable -> L9b
                if (r2 == 0) goto L70
                okio.AsyncTimeout r2 = okio.AsyncTimeout.access$getNext$p(r8)     // Catch: java.lang.Throwable -> L9b
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Throwable -> L9b
                long r2 = okio.AsyncTimeout.access$remainingNanos(r2, r0)     // Catch: java.lang.Throwable -> L9b
                int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r2 >= 0) goto L6b
                goto L70
            L6b:
                okio.AsyncTimeout r8 = okio.AsyncTimeout.access$getNext$p(r8)     // Catch: java.lang.Throwable -> L9b
                goto L52
            L70:
                okio.AsyncTimeout r6 = okio.AsyncTimeout.access$getNext$p(r8)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout.access$setNext$p(r5, r6)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout.access$setNext$p(r8, r5)     // Catch: java.lang.Throwable -> L9b
                okio.AsyncTimeout r5 = okio.AsyncTimeout.access$getHead$cp()     // Catch: java.lang.Throwable -> L9b
                if (r8 != r5) goto L85
                java.lang.Class<okio.AsyncTimeout> r5 = okio.AsyncTimeout.class
                r5.notify()     // Catch: java.lang.Throwable -> L9b
            L85:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L9b
                monitor-exit(r4)
                return
            L89:
                java.lang.AssertionError r5 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L9b
                r5.<init>()     // Catch: java.lang.Throwable -> L9b
                throw r5     // Catch: java.lang.Throwable -> L9b
            L8f:
                java.lang.String r5 = "Unbalanced enter/exit"
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L9b
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L9b
                r6.<init>(r5)     // Catch: java.lang.Throwable -> L9b
                throw r6     // Catch: java.lang.Throwable -> L9b
            L9b:
                r5 = move-exception
                monitor-exit(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Companion.scheduleTimeout(okio.AsyncTimeout, long, boolean):void");
        }

        public final AsyncTimeout awaitTimeout$okio() {
            AsyncTimeout asyncTimeout = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout);
            AsyncTimeout asyncTimeout2 = asyncTimeout.next;
            long nanoTime = System.nanoTime();
            if (asyncTimeout2 == null) {
                AsyncTimeout.class.wait(AsyncTimeout.IDLE_TIMEOUT_MILLIS);
                AsyncTimeout asyncTimeout3 = AsyncTimeout.head;
                Intrinsics.checkNotNull(asyncTimeout3);
                if (asyncTimeout3.next != null || System.nanoTime() - nanoTime < AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                    return null;
                }
                return AsyncTimeout.head;
            }
            long remainingNanos = asyncTimeout2.remainingNanos(nanoTime);
            if (remainingNanos > 0) {
                long j = remainingNanos / 1000000;
                AsyncTimeout.class.wait(j, (int) (remainingNanos - (1000000 * j)));
                return null;
            }
            AsyncTimeout asyncTimeout4 = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout4);
            asyncTimeout4.next = asyncTimeout2.next;
            asyncTimeout2.next = null;
            return asyncTimeout2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AsyncTimeout awaitTimeout$okio;
            while (true) {
                try {
                    synchronized (AsyncTimeout.class) {
                        awaitTimeout$okio = AsyncTimeout.Companion.awaitTimeout$okio();
                        if (awaitTimeout$okio == AsyncTimeout.head) {
                            AsyncTimeout.head = null;
                            return;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    if (awaitTimeout$okio != null) {
                        awaitTimeout$okio.timedOut();
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    public final IOException access$newTimeoutException(IOException iOException) {
        return newTimeoutException(iOException);
    }

    public final void enter() {
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    public final boolean exit() {
        return Companion.cancelScheduledTimeout(this);
    }

    protected IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final Sink sink(final Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new Sink() { // from class: okio.AsyncTimeout$sink$1
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.close();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    if (!asyncTimeout.exit()) {
                        throw e;
                    }
                    throw asyncTimeout.access$newTimeoutException(e);
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.flush();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    if (!asyncTimeout.exit()) {
                        throw e;
                    }
                    throw asyncTimeout.access$newTimeoutException(e);
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Sink
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ')';
            }

            @Override // okio.Sink
            public void write(Buffer source, long j) {
                Intrinsics.checkNotNullParameter(source, "source");
                _UtilKt.checkOffsetAndCount(source.size(), 0L, j);
                while (true) {
                    long j2 = 0;
                    if (j <= 0) {
                        return;
                    }
                    Segment segment = source.head;
                    while (true) {
                        Intrinsics.checkNotNull(segment);
                        if (j2 >= 65536) {
                            break;
                        }
                        j2 += segment.limit - segment.pos;
                        if (j2 >= j) {
                            j2 = j;
                            break;
                        }
                        segment = segment.next;
                    }
                    AsyncTimeout asyncTimeout = AsyncTimeout.this;
                    Sink sink2 = sink;
                    asyncTimeout.enter();
                    try {
                        sink2.write(source, j2);
                        Unit unit = Unit.INSTANCE;
                        if (asyncTimeout.exit()) {
                            throw asyncTimeout.access$newTimeoutException(null);
                        }
                        j -= j2;
                    } catch (IOException e) {
                        if (!asyncTimeout.exit()) {
                            throw e;
                        }
                        throw asyncTimeout.access$newTimeoutException(e);
                    } finally {
                        asyncTimeout.exit();
                    }
                }
            }
        };
    }

    public final Source source(final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new Source() { // from class: okio.AsyncTimeout$source$1
            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    source2.close();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                } catch (IOException e) {
                    if (!asyncTimeout.exit()) {
                        throw e;
                    }
                    throw asyncTimeout.access$newTimeoutException(e);
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source
            public long read(Buffer sink, long j) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    long read = source2.read(sink, j);
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(null);
                    }
                    return read;
                } catch (IOException e) {
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.access$newTimeoutException(e);
                    }
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ')';
            }
        };
    }

    protected void timedOut() {
    }
}
