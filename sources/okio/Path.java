package okio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._PathKt;

/* loaded from: classes.dex */
public final class Path implements Comparable {
    public static final Companion Companion = new Companion(null);
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }

        public final Path get(File file, boolean z) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            String file2 = file.toString();
            Intrinsics.checkNotNullExpressionValue(file2, "toString()");
            return get(file2, z);
        }

        public final Path get(String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return _PathKt.commonToPath(str, z);
        }

        public final Path get(java.nio.file.Path path, boolean z) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get(path.toString(), z);
        }
    }

    static {
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        DIRECTORY_SEPARATOR = separator;
    }

    public Path(ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
    }

    @Override // java.lang.Comparable
    public int compareTo(Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBytes$okio().compareTo(other.getBytes$okio());
    }

    public boolean equals(Object obj) {
        return (obj instanceof Path) && Intrinsics.areEqual(((Path) obj).getBytes$okio(), getBytes$okio());
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int rootLength;
        rootLength = _PathKt.rootLength(this);
        if (rootLength == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, rootLength));
    }

    public final List getSegmentsBytes() {
        int rootLength;
        ArrayList arrayList = new ArrayList();
        rootLength = _PathKt.rootLength(this);
        if (rootLength == -1) {
            rootLength = 0;
        } else if (rootLength < getBytes$okio().size() && getBytes$okio().getByte(rootLength) == ((byte) 92)) {
            rootLength++;
        }
        int size = getBytes$okio().size();
        int i = rootLength;
        while (rootLength < size) {
            if (getBytes$okio().getByte(rootLength) == ((byte) 47) || getBytes$okio().getByte(rootLength) == ((byte) 92)) {
                arrayList.add(getBytes$okio().substring(i, rootLength));
                i = rootLength + 1;
            }
            rootLength++;
        }
        if (i < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(i, getBytes$okio().size()));
        }
        return arrayList;
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public final boolean isAbsolute() {
        int rootLength;
        rootLength = _PathKt.rootLength(this);
        return rootLength != -1;
    }

    public final String name() {
        return nameBytes().utf8();
    }

    public final ByteString nameBytes() {
        int indexOfLastSlash;
        indexOfLastSlash = _PathKt.getIndexOfLastSlash(this);
        return indexOfLastSlash != -1 ? ByteString.substring$default(getBytes$okio(), indexOfLastSlash + 1, 0, 2, null) : (volumeLetter() == null || getBytes$okio().size() != 2) ? getBytes$okio() : ByteString.EMPTY;
    }

    public final Path parent() {
        ByteString byteString;
        ByteString byteString2;
        ByteString byteString3;
        boolean lastSegmentIsDotDot;
        int indexOfLastSlash;
        Path path;
        ByteString byteString4;
        ByteString byteString5;
        ByteString bytes$okio = getBytes$okio();
        byteString = _PathKt.DOT;
        if (Intrinsics.areEqual(bytes$okio, byteString)) {
            return null;
        }
        ByteString bytes$okio2 = getBytes$okio();
        byteString2 = _PathKt.SLASH;
        if (Intrinsics.areEqual(bytes$okio2, byteString2)) {
            return null;
        }
        ByteString bytes$okio3 = getBytes$okio();
        byteString3 = _PathKt.BACKSLASH;
        if (Intrinsics.areEqual(bytes$okio3, byteString3)) {
            return null;
        }
        lastSegmentIsDotDot = _PathKt.lastSegmentIsDotDot(this);
        if (lastSegmentIsDotDot) {
            return null;
        }
        indexOfLastSlash = _PathKt.getIndexOfLastSlash(this);
        if (indexOfLastSlash != 2 || volumeLetter() == null) {
            if (indexOfLastSlash == 1) {
                ByteString bytes$okio4 = getBytes$okio();
                byteString5 = _PathKt.BACKSLASH;
                if (bytes$okio4.startsWith(byteString5)) {
                    return null;
                }
            }
            if (indexOfLastSlash != -1 || volumeLetter() == null) {
                if (indexOfLastSlash == -1) {
                    byteString4 = _PathKt.DOT;
                    return new Path(byteString4);
                }
                if (indexOfLastSlash != 0) {
                    return new Path(ByteString.substring$default(getBytes$okio(), 0, indexOfLastSlash, 1, null));
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
            } else {
                if (getBytes$okio().size() == 2) {
                    return null;
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
            }
        } else {
            if (getBytes$okio().size() == 3) {
                return null;
            }
            path = new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
        }
        return path;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007f, code lost:
    
        r9 = okio.internal._PathKt.getSlash(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okio.Path relativeTo(okio.Path r9) {
        /*
            r8 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            okio.Path r0 = r8.getRoot()
            okio.Path r1 = r9.getRoot()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            java.lang.String r1 = " and "
            if (r0 == 0) goto Lda
            java.util.List r0 = r8.getSegmentsBytes()
            java.util.List r2 = r9.getSegmentsBytes()
            int r3 = r0.size()
            int r4 = r2.size()
            int r3 = java.lang.Math.min(r3, r4)
            r4 = 0
            r5 = r4
        L2b:
            if (r5 >= r3) goto L3e
            java.lang.Object r6 = r0.get(r5)
            java.lang.Object r7 = r2.get(r5)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 == 0) goto L3e
            int r5 = r5 + 1
            goto L2b
        L3e:
            r6 = 1
            if (r5 != r3) goto L5d
            okio.ByteString r3 = r8.getBytes$okio()
            int r3 = r3.size()
            okio.ByteString r7 = r9.getBytes$okio()
            int r7 = r7.size()
            if (r3 != r7) goto L5d
            okio.Path$Companion r8 = okio.Path.Companion
            java.lang.String r9 = "."
            r0 = 0
            okio.Path r8 = okio.Path.Companion.get$default(r8, r9, r4, r6, r0)
            goto Lb8
        L5d:
            int r3 = r2.size()
            java.util.List r3 = r2.subList(r5, r3)
            okio.ByteString r7 = okio.internal._PathKt.access$getDOT_DOT$p()
            int r3 = r3.indexOf(r7)
            r7 = -1
            if (r3 != r7) goto L71
            goto L72
        L71:
            r6 = r4
        L72:
            if (r6 == 0) goto Lb9
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.ByteString r9 = okio.internal._PathKt.access$getSlash(r9)
            if (r9 != 0) goto L8b
            okio.ByteString r9 = okio.internal._PathKt.access$getSlash(r8)
            if (r9 != 0) goto L8b
            java.lang.String r8 = okio.Path.DIRECTORY_SEPARATOR
            okio.ByteString r9 = okio.internal._PathKt.access$toSlash(r8)
        L8b:
            int r8 = r2.size()
            r2 = r5
        L90:
            if (r2 >= r8) goto L9f
            okio.ByteString r3 = okio.internal._PathKt.access$getDOT_DOT$p()
            r1.write(r3)
            r1.write(r9)
            int r2 = r2 + 1
            goto L90
        L9f:
            int r8 = r0.size()
        La3:
            if (r5 >= r8) goto Lb4
            java.lang.Object r2 = r0.get(r5)
            okio.ByteString r2 = (okio.ByteString) r2
            r1.write(r2)
            r1.write(r9)
            int r5 = r5 + 1
            goto La3
        Lb4:
            okio.Path r8 = okio.internal._PathKt.toPath(r1, r4)
        Lb8:
            return r8
        Lb9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Impossible relative path to resolve: "
            r0.append(r2)
            r0.append(r8)
            r0.append(r1)
            r0.append(r9)
            java.lang.String r8 = r0.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        Lda:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Paths of different roots cannot be relative to each other: "
            r0.append(r2)
            r0.append(r8)
            r0.append(r1)
            r0.append(r9)
            java.lang.String r8 = r0.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Path.relativeTo(okio.Path):okio.Path");
    }

    public final Path resolve(String child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(child), false), false);
    }

    public final Path resolve(Path child, boolean z) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, child, z);
    }

    public final File toFile() {
        return new File(toString());
    }

    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "get(toString())");
        return path;
    }

    public String toString() {
        return getBytes$okio().utf8();
    }

    public final Character volumeLetter() {
        ByteString byteString;
        ByteString bytes$okio = getBytes$okio();
        byteString = _PathKt.SLASH;
        boolean z = false;
        if (ByteString.indexOf$default(bytes$okio, byteString, 0, 2, null) != -1 || getBytes$okio().size() < 2 || getBytes$okio().getByte(1) != ((byte) 58)) {
            return null;
        }
        char c = (char) getBytes$okio().getByte(0);
        if (!('a' <= c && c < '{')) {
            if ('A' <= c && c < '[') {
                z = true;
            }
            if (!z) {
                return null;
            }
        }
        return Character.valueOf(c);
    }
}
