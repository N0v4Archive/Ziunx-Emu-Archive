package coil.disk;

import coil.util.FileSystems;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;

/* loaded from: classes.dex */
public final class DiskLruCache implements Closeable, Flushable {
    public static final Companion Companion = new Companion(null);
    private static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    private final int appVersion;
    private final CoroutineScope cleanupScope;
    private boolean closed;
    private final Path directory;
    private final DiskLruCache$fileSystem$1 fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap lruEntries;
    private final long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private int operationsSinceRewrite;
    private long size;
    private final int valueCount;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public final class Editor {
        private boolean closed;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = new boolean[DiskLruCache.this.valueCount];
        }

        private final void complete(boolean z) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                    diskLruCache.completeEdit(this, z);
                }
                this.closed = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void abort() {
            complete(false);
        }

        public final void commit() {
            complete(true);
        }

        public final Snapshot commitAndGet() {
            Snapshot snapshot;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                commit();
                snapshot = diskLruCache.get(this.entry.getKey());
            }
            return snapshot;
        }

        public final void detach() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                this.entry.setZombie(true);
            }
        }

        public final Path file(int i) {
            Path path;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                this.written[i] = true;
                Object obj = this.entry.getDirtyFiles().get(i);
                FileSystems.createFile(diskLruCache.fileSystem, (Path) obj);
                path = (Path) obj;
            }
            return path;
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final boolean[] getWritten() {
            return this.written;
        }
    }

    /* loaded from: classes.dex */
    public final class Entry {
        private final ArrayList cleanFiles;
        private Editor currentEditor;
        private final ArrayList dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSnapshotCount;
        private boolean readable;
        private boolean zombie;

        public Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new ArrayList(DiskLruCache.this.valueCount);
            this.dirtyFiles = new ArrayList(DiskLruCache.this.valueCount);
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            int i = DiskLruCache.this.valueCount;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(i2);
                this.cleanFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.append(".tmp");
                this.dirtyFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.setLength(length);
            }
        }

        public final ArrayList getCleanFiles() {
            return this.cleanFiles;
        }

        public final Editor getCurrentEditor() {
            return this.currentEditor;
        }

        public final ArrayList getDirtyFiles() {
            return this.dirtyFiles;
        }

        public final String getKey() {
            return this.key;
        }

        public final long[] getLengths() {
            return this.lengths;
        }

        public final int getLockingSnapshotCount() {
            return this.lockingSnapshotCount;
        }

        public final boolean getReadable() {
            return this.readable;
        }

        public final boolean getZombie() {
            return this.zombie;
        }

        public final void setCurrentEditor(Editor editor) {
            this.currentEditor = editor;
        }

        public final void setLengths(List list) {
            if (list.size() != DiskLruCache.this.valueCount) {
                throw new IOException("unexpected journal line: " + list);
            }
            try {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this.lengths[i] = Long.parseLong((String) list.get(i));
                }
            } catch (NumberFormatException unused) {
                throw new IOException("unexpected journal line: " + list);
            }
        }

        public final void setLockingSnapshotCount(int i) {
            this.lockingSnapshotCount = i;
        }

        public final void setReadable(boolean z) {
            this.readable = z;
        }

        public final void setZombie(boolean z) {
            this.zombie = z;
        }

        public final Snapshot snapshot() {
            if (!this.readable || this.currentEditor != null || this.zombie) {
                return null;
            }
            ArrayList arrayList = this.cleanFiles;
            DiskLruCache diskLruCache = DiskLruCache.this;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (!diskLruCache.fileSystem.exists((Path) arrayList.get(i))) {
                    try {
                        diskLruCache.removeEntry(this);
                    } catch (IOException unused) {
                    }
                    return null;
                }
            }
            this.lockingSnapshotCount++;
            return new Snapshot(this);
        }

        public final void writeLengths(BufferedSink bufferedSink) {
            for (long j : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(j);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class Snapshot implements Closeable {
        private boolean closed;
        private final Entry entry;

        public Snapshot(Entry entry) {
            this.entry = entry;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                this.entry.setLockingSnapshotCount(r1.getLockingSnapshotCount() - 1);
                if (this.entry.getLockingSnapshotCount() == 0 && this.entry.getZombie()) {
                    diskLruCache.removeEntry(this.entry);
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        public final Editor closeAndEdit() {
            Editor edit;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                close();
                edit = diskLruCache.edit(this.entry.getKey());
            }
            return edit;
        }

        public final Path file(int i) {
            if (!this.closed) {
                return (Path) this.entry.getCleanFiles().get(i);
            }
            throw new IllegalStateException("snapshot is closed".toString());
        }
    }

    /* JADX WARN: Type inference failed for: r4v7, types: [coil.disk.DiskLruCache$fileSystem$1] */
    public DiskLruCache(final FileSystem fileSystem, Path path, CoroutineDispatcher coroutineDispatcher, long j, int i, int i2) {
        this.directory = path;
        this.maxSize = j;
        this.appVersion = i;
        this.valueCount = i2;
        if (!(j > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (!(i2 > 0)) {
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        this.journalFile = path.resolve("journal");
        this.journalFileTmp = path.resolve("journal.tmp");
        this.journalFileBackup = path.resolve("journal.bkp");
        this.lruEntries = new LinkedHashMap(0, 0.75f, true);
        this.cleanupScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(coroutineDispatcher.limitedParallelism(1)));
        this.fileSystem = new ForwardingFileSystem(fileSystem) { // from class: coil.disk.DiskLruCache$fileSystem$1
            @Override // okio.ForwardingFileSystem, okio.FileSystem
            public Sink sink(Path path2, boolean z) {
                Path parent = path2.parent();
                if (parent != null) {
                    createDirectories(parent);
                }
                return super.sink(path2, z);
            }
        };
    }

    private final void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void completeEdit(Editor editor, boolean z) {
        Entry entry = editor.getEntry();
        if (!Intrinsics.areEqual(entry.getCurrentEditor(), editor)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        int i = 0;
        if (!z || entry.getZombie()) {
            int i2 = this.valueCount;
            while (i < i2) {
                delete((Path) entry.getDirtyFiles().get(i));
                i++;
            }
        } else {
            int i3 = this.valueCount;
            for (int i4 = 0; i4 < i3; i4++) {
                if (editor.getWritten()[i4] && !exists((Path) entry.getDirtyFiles().get(i4))) {
                    editor.abort();
                    return;
                }
            }
            int i5 = this.valueCount;
            while (i < i5) {
                Path path = (Path) entry.getDirtyFiles().get(i);
                Path path2 = (Path) entry.getCleanFiles().get(i);
                if (exists(path)) {
                    atomicMove(path, path2);
                } else {
                    FileSystems.createFile(this.fileSystem, (Path) entry.getCleanFiles().get(i));
                }
                long j = entry.getLengths()[i];
                Long size = metadata(path2).getSize();
                long longValue = size != null ? size.longValue() : 0L;
                entry.getLengths()[i] = longValue;
                this.size = (this.size - j) + longValue;
                i++;
            }
        }
        entry.setCurrentEditor(null);
        if (entry.getZombie()) {
            removeEntry(entry);
            return;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        if (!z && !entry.getReadable()) {
            this.lruEntries.remove(entry.getKey());
            bufferedSink.writeUtf8("REMOVE");
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            bufferedSink.writeByte(10);
            bufferedSink.flush();
            if (this.size <= this.maxSize || journalRewriteRequired()) {
                launchCleanup();
            }
        }
        entry.setReadable(true);
        bufferedSink.writeUtf8("CLEAN");
        bufferedSink.writeByte(32);
        bufferedSink.writeUtf8(entry.getKey());
        entry.writeLengths(bufferedSink);
        bufferedSink.writeByte(10);
        bufferedSink.flush();
        if (this.size <= this.maxSize) {
        }
        launchCleanup();
    }

    private final void delete() {
        close();
        FileSystems.deleteContents(this.fileSystem, this.directory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean journalRewriteRequired() {
        return this.operationsSinceRewrite >= 2000;
    }

    private final void launchCleanup() {
        BuildersKt__Builders_commonKt.launch$default(this.cleanupScope, null, null, new DiskLruCache$launchCleanup$1(this, null), 3, null);
    }

    private final BufferedSink newJournalWriter() {
        return Okio.buffer(new FaultHidingSink(appendingSink(this.journalFile), new Function1() { // from class: coil.disk.DiskLruCache$newJournalWriter$faultHidingSink$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((IOException) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        }));
    }

    private final void processJournal() {
        Iterator it = this.lruEntries.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int i = 0;
            if (entry.getCurrentEditor() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    j += entry.getLengths()[i];
                    i++;
                }
            } else {
                entry.setCurrentEditor(null);
                int i3 = this.valueCount;
                while (i < i3) {
                    delete((Path) entry.getCleanFiles().get(i));
                    delete((Path) entry.getDirtyFiles().get(i));
                    i++;
                }
                it.remove();
            }
        }
        this.size = j;
    }

    private final void readJournal() {
        Unit unit;
        BufferedSource buffer = Okio.buffer(source(this.journalFile));
        Throwable th = null;
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (Intrinsics.areEqual("libcore.io.DiskLruCache", readUtf8LineStrict) && Intrinsics.areEqual("1", readUtf8LineStrict2) && Intrinsics.areEqual(String.valueOf(this.appVersion), readUtf8LineStrict3) && Intrinsics.areEqual(String.valueOf(this.valueCount), readUtf8LineStrict4)) {
                int i = 0;
                if (!(readUtf8LineStrict5.length() > 0)) {
                    while (true) {
                        try {
                            readJournalLine(buffer.readUtf8LineStrict());
                            i++;
                        } catch (EOFException unused) {
                            this.operationsSinceRewrite = i - this.lruEntries.size();
                            if (buffer.exhausted()) {
                                this.journalWriter = newJournalWriter();
                            } else {
                                writeJournal();
                            }
                            unit = Unit.INSTANCE;
                            if (buffer != null) {
                                try {
                                    buffer.close();
                                } catch (Throwable th2) {
                                    if (th == null) {
                                        th = th2;
                                    } else {
                                        ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
                                    }
                                }
                            }
                            if (th != null) {
                                throw th;
                            }
                            Intrinsics.checkNotNull(unit);
                            return;
                        }
                    }
                }
            }
            throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict3 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + ']');
        } catch (Throwable th3) {
            th = th3;
            unit = null;
        }
    }

    private final void readJournalLine(String str) {
        int indexOf$default;
        int indexOf$default2;
        String substring;
        boolean startsWith$default;
        boolean startsWith$default2;
        boolean startsWith$default3;
        List split$default;
        boolean startsWith$default4;
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf$default + 1;
        indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ' ', i, false, 4, (Object) null);
        if (indexOf$default2 == -1) {
            substring = str.substring(i);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            if (indexOf$default == 6) {
                startsWith$default4 = StringsKt__StringsJVMKt.startsWith$default(str, "REMOVE", false, 2, null);
                if (startsWith$default4) {
                    this.lruEntries.remove(substring);
                    return;
                }
            }
        } else {
            substring = str.substring(i, indexOf$default2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        LinkedHashMap linkedHashMap = this.lruEntries;
        Object obj = linkedHashMap.get(substring);
        if (obj == null) {
            obj = new Entry(substring);
            linkedHashMap.put(substring, obj);
        }
        Entry entry = (Entry) obj;
        if (indexOf$default2 != -1 && indexOf$default == 5) {
            startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(str, "CLEAN", false, 2, null);
            if (startsWith$default3) {
                String substring2 = str.substring(indexOf$default2 + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                split$default = StringsKt__StringsKt.split$default((CharSequence) substring2, new char[]{' '}, false, 0, 6, (Object) null);
                entry.setReadable(true);
                entry.setCurrentEditor(null);
                entry.setLengths(split$default);
                return;
            }
        }
        if (indexOf$default2 == -1 && indexOf$default == 5) {
            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(str, "DIRTY", false, 2, null);
            if (startsWith$default2) {
                entry.setCurrentEditor(new Editor(entry));
                return;
            }
        }
        if (indexOf$default2 == -1 && indexOf$default == 4) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "READ", false, 2, null);
            if (startsWith$default) {
                return;
            }
        }
        throw new IOException("unexpected journal line: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeEntry(Entry entry) {
        BufferedSink bufferedSink;
        if (entry.getLockingSnapshotCount() > 0 && (bufferedSink = this.journalWriter) != null) {
            bufferedSink.writeUtf8("DIRTY");
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            bufferedSink.writeByte(10);
            bufferedSink.flush();
        }
        if (entry.getLockingSnapshotCount() > 0 || entry.getCurrentEditor() != null) {
            entry.setZombie(true);
            return true;
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            delete((Path) entry.getCleanFiles().get(i2));
            this.size -= entry.getLengths()[i2];
            entry.getLengths()[i2] = 0;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink2 = this.journalWriter;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8("REMOVE");
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey());
            bufferedSink2.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey());
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return true;
    }

    private final boolean removeOldestEntry() {
        for (Entry entry : this.lruEntries.values()) {
            if (!entry.getZombie()) {
                removeEntry(entry);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trimToSize() {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final void validateKey(String str) {
        if (LEGAL_KEY_PATTERN.matches(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void writeJournal() {
        Unit unit;
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        BufferedSink buffer = Okio.buffer(sink(this.journalFileTmp, false));
        Throwable th = null;
        try {
            buffer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            buffer.writeUtf8("1").writeByte(10);
            buffer.writeDecimalLong(this.appVersion).writeByte(10);
            buffer.writeDecimalLong(this.valueCount).writeByte(10);
            buffer.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.getCurrentEditor() != null) {
                    buffer.writeUtf8("DIRTY");
                    buffer.writeByte(32);
                    buffer.writeUtf8(entry.getKey());
                } else {
                    buffer.writeUtf8("CLEAN");
                    buffer.writeByte(32);
                    buffer.writeUtf8(entry.getKey());
                    entry.writeLengths(buffer);
                }
                buffer.writeByte(10);
            }
            unit = Unit.INSTANCE;
        } catch (Throwable th2) {
            unit = null;
            th = th2;
        }
        if (buffer != null) {
            try {
                buffer.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    ExceptionsKt__ExceptionsKt.addSuppressed(th, th3);
                }
            }
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(unit);
        if (exists(this.journalFile)) {
            atomicMove(this.journalFile, this.journalFileBackup);
            atomicMove(this.journalFileTmp, this.journalFile);
            delete(this.journalFileBackup);
        } else {
            atomicMove(this.journalFileTmp, this.journalFile);
        }
        this.journalWriter = newJournalWriter();
        this.operationsSinceRewrite = 0;
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.initialized && !this.closed) {
            Object[] array = this.lruEntries.values().toArray(new Entry[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            for (Entry entry : (Entry[]) array) {
                Editor currentEditor = entry.getCurrentEditor();
                if (currentEditor != null) {
                    currentEditor.detach();
                }
            }
            trimToSize();
            CoroutineScopeKt.cancel$default(this.cleanupScope, null, 1, null);
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.close();
            this.journalWriter = null;
            this.closed = true;
            return;
        }
        this.closed = true;
    }

    public final synchronized Editor edit(String str) {
        checkNotClosed();
        validateKey(str);
        initialize();
        Entry entry = (Entry) this.lruEntries.get(str);
        if ((entry != null ? entry.getCurrentEditor() : null) != null) {
            return null;
        }
        if (entry != null && entry.getLockingSnapshotCount() != 0) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.writeUtf8("DIRTY");
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(str);
            bufferedSink.writeByte(10);
            bufferedSink.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(str);
                this.lruEntries.put(str, entry);
            }
            Editor editor = new Editor(entry);
            entry.setCurrentEditor(editor);
            return editor;
        }
        launchCleanup();
        return null;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    public final synchronized Snapshot get(String str) {
        Snapshot snapshot;
        checkNotClosed();
        validateKey(str);
        initialize();
        Entry entry = (Entry) this.lruEntries.get(str);
        if (entry != null && (snapshot = entry.snapshot()) != null) {
            this.operationsSinceRewrite++;
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.writeUtf8("READ");
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(str);
            bufferedSink.writeByte(10);
            if (journalRewriteRequired()) {
                launchCleanup();
            }
            return snapshot;
        }
        return null;
    }

    public final synchronized void initialize() {
        if (this.initialized) {
            return;
        }
        delete(this.journalFileTmp);
        if (exists(this.journalFileBackup)) {
            if (exists(this.journalFile)) {
                delete(this.journalFileBackup);
            } else {
                atomicMove(this.journalFileBackup, this.journalFile);
            }
        }
        if (exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException unused) {
                try {
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
        }
        writeJournal();
        this.initialized = true;
    }
}
