package okio.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;

/* loaded from: classes.dex */
public abstract class ZipKt {
    private static final Map buildIndex(List list) {
        Map mutableMapOf;
        List<ZipEntry> sortedWith;
        Path path = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
        mutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null)));
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipKt$buildIndex$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(((ZipEntry) obj).getCanonicalPath(), ((ZipEntry) obj2).getCanonicalPath());
                return compareValues;
            }
        });
        for (ZipEntry zipEntry : sortedWith) {
            if (((ZipEntry) mutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) mutableMapOf.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null);
                        mutableMapOf.put(parent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return mutableMapOf;
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        int checkRadix;
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String num = Integer.toString(i, checkRadix);
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        sb.append(num);
        return sb.toString();
    }

    public static final ZipFileSystem openZip(Path zipPath, FileSystem fileSystem, Function1 predicate) {
        BufferedSource buffer;
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        FileHandle openReadOnly = fileSystem.openReadOnly(zipPath);
        try {
            long size = openReadOnly.size() - 22;
            if (size < 0) {
                throw new IOException("not a zip: size=" + openReadOnly.size());
            }
            long max = Math.max(size - 65536, 0L);
            do {
                BufferedSource buffer2 = Okio.buffer(openReadOnly.source(size));
                try {
                    if (buffer2.readIntLe() == 101010256) {
                        EocdRecord readEocdRecord = readEocdRecord(buffer2);
                        String readUtf8 = buffer2.readUtf8(readEocdRecord.getCommentByteCount());
                        buffer2.close();
                        long j = size - 20;
                        if (j > 0) {
                            buffer = Okio.buffer(openReadOnly.source(j));
                            try {
                                if (buffer.readIntLe() == 117853008) {
                                    int readIntLe = buffer.readIntLe();
                                    long readLongLe = buffer.readLongLe();
                                    if (buffer.readIntLe() != 1 || readIntLe != 0) {
                                        throw new IOException("unsupported zip: spanned");
                                    }
                                    buffer = Okio.buffer(openReadOnly.source(readLongLe));
                                    try {
                                        int readIntLe2 = buffer.readIntLe();
                                        if (readIntLe2 != 101075792) {
                                            throw new IOException("bad zip: expected " + getHex(101075792) + " but was " + getHex(readIntLe2));
                                        }
                                        readEocdRecord = readZip64EocdRecord(buffer, readEocdRecord);
                                        Unit unit = Unit.INSTANCE;
                                        CloseableKt.closeFinally(buffer, null);
                                    } finally {
                                    }
                                }
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(buffer, null);
                            } finally {
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        buffer = Okio.buffer(openReadOnly.source(readEocdRecord.getCentralDirectoryOffset()));
                        try {
                            long entryCount = readEocdRecord.getEntryCount();
                            for (long j2 = 0; j2 < entryCount; j2++) {
                                ZipEntry readEntry = readEntry(buffer);
                                if (readEntry.getOffset() >= readEocdRecord.getCentralDirectoryOffset()) {
                                    throw new IOException("bad zip: local file header offset >= central directory offset");
                                }
                                if (((Boolean) predicate.invoke(readEntry)).booleanValue()) {
                                    arrayList.add(readEntry);
                                }
                            }
                            Unit unit3 = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer, null);
                            ZipFileSystem zipFileSystem = new ZipFileSystem(zipPath, fileSystem, buildIndex(arrayList), readUtf8);
                            CloseableKt.closeFinally(openReadOnly, null);
                            return zipFileSystem;
                        } finally {
                            try {
                                throw th;
                            } finally {
                                CloseableKt.closeFinally(buffer, th);
                            }
                        }
                    }
                    buffer2.close();
                    size--;
                } catch (Throwable th) {
                    buffer2.close();
                    throw th;
                }
            } while (size >= max);
            throw new IOException("not a zip: end of central directory signature not found");
        } finally {
        }
    }

    public static final ZipEntry readEntry(final BufferedSource bufferedSource) {
        boolean contains$default;
        Ref$LongRef ref$LongRef;
        long j;
        boolean endsWith$default;
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != 33639248) {
            throw new IOException("bad zip: expected " + getHex(33639248) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(4L);
        int readShortLe = bufferedSource.readShortLe() & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(readShortLe));
        }
        int readShortLe2 = bufferedSource.readShortLe() & 65535;
        Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & 65535, bufferedSource.readShortLe() & 65535);
        long readIntLe2 = bufferedSource.readIntLe() & 4294967295L;
        final Ref$LongRef ref$LongRef2 = new Ref$LongRef();
        ref$LongRef2.element = bufferedSource.readIntLe() & 4294967295L;
        final Ref$LongRef ref$LongRef3 = new Ref$LongRef();
        ref$LongRef3.element = bufferedSource.readIntLe() & 4294967295L;
        int readShortLe3 = bufferedSource.readShortLe() & 65535;
        int readShortLe4 = bufferedSource.readShortLe() & 65535;
        int readShortLe5 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(8L);
        Ref$LongRef ref$LongRef4 = new Ref$LongRef();
        ref$LongRef4.element = bufferedSource.readIntLe() & 4294967295L;
        String readUtf8 = bufferedSource.readUtf8(readShortLe3);
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) readUtf8, (char) 0, false, 2, (Object) null);
        if (contains$default) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (ref$LongRef3.element == 4294967295L) {
            j = 8 + 0;
            ref$LongRef = ref$LongRef4;
        } else {
            ref$LongRef = ref$LongRef4;
            j = 0;
        }
        if (ref$LongRef2.element == 4294967295L) {
            j += 8;
        }
        final Ref$LongRef ref$LongRef5 = ref$LongRef;
        if (ref$LongRef5.element == 4294967295L) {
            j += 8;
        }
        final long j2 = j;
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        readExtra(bufferedSource, readShortLe4, new Function2() { // from class: okio.internal.ZipKt$readEntry$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, long j3) {
                if (i == 1) {
                    Ref$BooleanRef ref$BooleanRef2 = Ref$BooleanRef.this;
                    if (ref$BooleanRef2.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    ref$BooleanRef2.element = true;
                    if (j3 < j2) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    Ref$LongRef ref$LongRef6 = ref$LongRef3;
                    long j4 = ref$LongRef6.element;
                    if (j4 == 4294967295L) {
                        j4 = bufferedSource.readLongLe();
                    }
                    ref$LongRef6.element = j4;
                    Ref$LongRef ref$LongRef7 = ref$LongRef2;
                    ref$LongRef7.element = ref$LongRef7.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                    Ref$LongRef ref$LongRef8 = ref$LongRef5;
                    ref$LongRef8.element = ref$LongRef8.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                }
            }
        });
        if (j2 > 0 && !ref$BooleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        String readUtf82 = bufferedSource.readUtf8(readShortLe5);
        Path resolve = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null).resolve(readUtf8);
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(readUtf8, "/", false, 2, null);
        return new ZipEntry(resolve, endsWith$default, readUtf82, readIntLe2, ref$LongRef2.element, ref$LongRef3.element, readShortLe2, dosDateTimeToEpochMillis, ref$LongRef5.element);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) {
        int readShortLe = bufferedSource.readShortLe() & 65535;
        int readShortLe2 = bufferedSource.readShortLe() & 65535;
        long readShortLe3 = bufferedSource.readShortLe() & 65535;
        if (readShortLe3 != (bufferedSource.readShortLe() & 65535) || readShortLe != 0 || readShortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(readShortLe3, 4294967295L & bufferedSource.readIntLe(), bufferedSource.readShortLe() & 65535);
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2 function2) {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int readShortLe = bufferedSource.readShortLe() & 65535;
            long readShortLe2 = bufferedSource.readShortLe() & 65535;
            long j2 = j - 4;
            if (j2 < readShortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(readShortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
            long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
            if (size2 < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + readShortLe);
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - readShortLe2;
        }
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata basicMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    private static final FileMetadata readOrSkipLocalHeader(final BufferedSource bufferedSource, FileMetadata fileMetadata) {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : null;
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != 67324752) {
            throw new IOException("bad zip: expected " + getHex(67324752) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(2L);
        int readShortLe = bufferedSource.readShortLe() & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(readShortLe));
        }
        bufferedSource.skip(18L);
        int readShortLe2 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(bufferedSource.readShortLe() & 65535);
        if (fileMetadata == null) {
            bufferedSource.skip(readShortLe2);
            return null;
        }
        readExtra(bufferedSource, readShortLe2, new Function2() { // from class: okio.internal.ZipKt$readOrSkipLocalHeader$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, long j) {
                if (i == 21589) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    int readByte = BufferedSource.this.readByte() & 255;
                    boolean z = (readByte & 1) == 1;
                    boolean z2 = (readByte & 2) == 2;
                    boolean z3 = (readByte & 4) == 4;
                    BufferedSource bufferedSource2 = BufferedSource.this;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        ref$ObjectRef.element = Long.valueOf(bufferedSource2.readIntLe() * 1000);
                    }
                    if (z2) {
                        ref$ObjectRef2.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                    if (z3) {
                        ref$ObjectRef3.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                }
            }
        });
        return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), null, fileMetadata.getSize(), (Long) ref$ObjectRef3.element, (Long) ref$ObjectRef.element, (Long) ref$ObjectRef2.element, null, 128, null);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) {
        bufferedSource.skip(12L);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe != bufferedSource.readLongLe() || readIntLe != 0 || readIntLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }
}
