package okio.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.Sink;
import okio.Source;

/* loaded from: classes.dex */
public final class ResourceFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
    private final Lazy roots$delegate;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean keepPath(Path path) {
            boolean endsWith;
            endsWith = StringsKt__StringsJVMKt.endsWith(path.name(), ".class", true);
            return !endsWith;
        }

        public final Path getROOT() {
            return ResourceFileSystem.ROOT;
        }

        public final Path removeBase(Path path, Path base) {
            String removePrefix;
            String replace$default;
            Intrinsics.checkNotNullParameter(path, "<this>");
            Intrinsics.checkNotNullParameter(base, "base");
            String path2 = base.toString();
            Path root = getROOT();
            removePrefix = StringsKt__StringsKt.removePrefix(path.toString(), path2);
            replace$default = StringsKt__StringsJVMKt.replace$default(removePrefix, '\\', '/', false, 4, (Object) null);
            return root.resolve(replace$default);
        }

        public final List toClasspathRoots(ClassLoader classLoader) {
            List plus;
            Intrinsics.checkNotNullParameter(classLoader, "<this>");
            Enumeration<URL> resources = classLoader.getResources("");
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(\"\")");
            ArrayList<URL> list = Collections.list(resources);
            Intrinsics.checkNotNullExpressionValue(list, "list(this)");
            ArrayList arrayList = new ArrayList();
            for (URL it : list) {
                Companion companion = ResourceFileSystem.Companion;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Pair fileRoot = companion.toFileRoot(it);
                if (fileRoot != null) {
                    arrayList.add(fileRoot);
                }
            }
            Enumeration<URL> resources2 = classLoader.getResources("META-INF/MANIFEST.MF");
            Intrinsics.checkNotNullExpressionValue(resources2, "getResources(\"META-INF/MANIFEST.MF\")");
            ArrayList<URL> list2 = Collections.list(resources2);
            Intrinsics.checkNotNullExpressionValue(list2, "list(this)");
            ArrayList arrayList2 = new ArrayList();
            for (URL it2 : list2) {
                Companion companion2 = ResourceFileSystem.Companion;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                Pair jarRoot = companion2.toJarRoot(it2);
                if (jarRoot != null) {
                    arrayList2.add(jarRoot);
                }
            }
            plus = CollectionsKt___CollectionsKt.plus((Collection) arrayList, (Iterable) arrayList2);
            return plus;
        }

        public final Pair toFileRoot(URL url) {
            Intrinsics.checkNotNullParameter(url, "<this>");
            if (Intrinsics.areEqual(url.getProtocol(), "file")) {
                return TuplesKt.to(FileSystem.SYSTEM, Path.Companion.get$default(Path.Companion, new File(url.toURI()), false, 1, (Object) null));
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
        
            r0 = kotlin.text.StringsKt__StringsKt.lastIndexOf$default((java.lang.CharSequence) r10, "!", 0, false, 6, (java.lang.Object) null);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final kotlin.Pair toJarRoot(java.net.URL r10) {
            /*
                r9 = this;
                java.lang.String r0 = "<this>"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r10 = r10.toString()
                java.lang.String r0 = "toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)
                java.lang.String r0 = "jar:file:"
                r7 = 0
                r1 = 2
                r8 = 0
                boolean r0 = kotlin.text.StringsKt.startsWith$default(r10, r0, r7, r1, r8)
                if (r0 != 0) goto L1a
                return r8
            L1a:
                java.lang.String r2 = "!"
                r3 = 0
                r4 = 0
                r5 = 6
                r6 = 0
                r1 = r10
                int r0 = kotlin.text.StringsKt.lastIndexOf$default(r1, r2, r3, r4, r5, r6)
                r1 = -1
                if (r0 != r1) goto L29
                return r8
            L29:
                okio.Path$Companion r1 = okio.Path.Companion
                java.io.File r2 = new java.io.File
                r3 = 4
                java.lang.String r10 = r10.substring(r3, r0)
                java.lang.String r0 = "this as java.lang.String…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)
                java.net.URI r10 = java.net.URI.create(r10)
                r2.<init>(r10)
                r10 = 1
                okio.Path r10 = okio.Path.Companion.get$default(r1, r2, r7, r10, r8)
                okio.FileSystem r0 = okio.FileSystem.SYSTEM
                okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1 r1 = new kotlin.jvm.functions.Function1() { // from class: okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1
                    static {
                        /*
                            okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1 r0 = new okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1) okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1.INSTANCE okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1.<clinit>():void");
                    }

                    {
                        /*
                            r1 = this;
                            r0 = 1
                            r1.<init>(r0)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1.<init>():void");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final java.lang.Boolean invoke(okio.internal.ZipEntry r1) {
                        /*
                            r0 = this;
                            java.lang.String r0 = "entry"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                            okio.internal.ResourceFileSystem$Companion r0 = okio.internal.ResourceFileSystem.access$getCompanion$p()
                            okio.Path r1 = r1.getCanonicalPath()
                            boolean r0 = okio.internal.ResourceFileSystem.Companion.access$keepPath(r0, r1)
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1.invoke(okio.internal.ZipEntry):java.lang.Boolean");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ java.lang.Object invoke(java.lang.Object r1) {
                        /*
                            r0 = this;
                            okio.internal.ZipEntry r1 = (okio.internal.ZipEntry) r1
                            java.lang.Boolean r0 = r0.invoke(r1)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$Companion$toJarRoot$zip$1.invoke(java.lang.Object):java.lang.Object");
                    }
                }
                okio.ZipFileSystem r10 = okio.internal.ZipKt.openZip(r10, r0, r1)
                okio.Path r9 = r9.getROOT()
                kotlin.Pair r9 = kotlin.TuplesKt.to(r10, r9)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem.Companion.toJarRoot(java.net.URL):kotlin.Pair");
        }
    }

    public ResourceFileSystem(final ClassLoader classLoader, boolean z) {
        Lazy lazy;
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: okio.internal.ResourceFileSystem$roots$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List invoke() {
                return ResourceFileSystem.Companion.toClasspathRoots(classLoader);
            }
        });
        this.roots$delegate = lazy;
        if (z) {
            getRoots().size();
        }
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    private final List getRoots() {
        return (List) this.roots$delegate.getValue();
    }

    private final String toRelativePath(Path path) {
        return canonicalizeInternal(path).relativeTo(ROOT).toString();
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(Path source, Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void createDirectory(Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void delete(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public List list(Path dir) {
        List list;
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(dir, "dir");
        String relativePath = toRelativePath(dir);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (Pair pair : getRoots()) {
            FileSystem fileSystem = (FileSystem) pair.component1();
            Path path = (Path) pair.component2();
            try {
                List list2 = fileSystem.list(path.resolve(relativePath));
                ArrayList arrayList = new ArrayList();
                for (Object obj : list2) {
                    if (Companion.keepPath((Path) obj)) {
                        arrayList.add(obj);
                    }
                }
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(Companion.removeBase((Path) it.next(), path));
                }
                CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, arrayList2);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (z) {
            list = CollectionsKt___CollectionsKt.toList(linkedHashSet);
            return list;
        }
        throw new FileNotFoundException("file not found: " + dir);
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (!Companion.keepPath(path)) {
            return null;
        }
        String relativePath = toRelativePath(path);
        for (Pair pair : getRoots()) {
            FileMetadata metadataOrNull = ((FileSystem) pair.component1()).metadataOrNull(((Path) pair.component2()).resolve(relativePath));
            if (metadataOrNull != null) {
                return metadataOrNull;
            }
        }
        return null;
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (!Companion.keepPath(file)) {
            throw new FileNotFoundException("file not found: " + file);
        }
        String relativePath = toRelativePath(file);
        Iterator it = getRoots().iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            try {
                return ((FileSystem) pair.component1()).openReadOnly(((Path) pair.component2()).resolve(relativePath));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException("file not found: " + file);
    }

    @Override // okio.FileSystem
    public Sink sink(Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public Source source(Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (!Companion.keepPath(file)) {
            throw new FileNotFoundException("file not found: " + file);
        }
        String relativePath = toRelativePath(file);
        Iterator it = getRoots().iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            try {
                return ((FileSystem) pair.component1()).source(((Path) pair.component2()).resolve(relativePath));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException("file not found: " + file);
    }
}
