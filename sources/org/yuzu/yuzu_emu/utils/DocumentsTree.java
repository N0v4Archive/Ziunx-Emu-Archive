package org.yuzu.yuzu_emu.utils;

import android.net.Uri;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.MinimalDocumentFile;

/* loaded from: classes.dex */
public final class DocumentsTree {
    public static final Companion Companion = new Companion(null);
    private DocumentsNode root;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isNativePath(String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return (path.length() > 0) && path.charAt(0) == '/';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DocumentsNode {
        private final Map children;
        private boolean isDirectory;
        private boolean loaded;
        private String name;
        private DocumentsNode parent;
        private Uri uri;

        public DocumentsNode(MinimalDocumentFile document) {
            Intrinsics.checkNotNullParameter(document, "document");
            this.children = new HashMap();
            this.name = document.getFilename();
            this.uri = document.getUri();
            boolean isDirectory = document.isDirectory();
            this.isDirectory = isDirectory;
            this.loaded = !isDirectory;
        }

        public final Map getChildren() {
            return this.children;
        }

        public final boolean getLoaded() {
            return this.loaded;
        }

        public final String getName() {
            return this.name;
        }

        public final DocumentsNode getParent() {
            return this.parent;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final boolean isDirectory() {
            return this.isDirectory;
        }

        public final void setLoaded(boolean z) {
            this.loaded = z;
        }

        public final void setParent(DocumentsNode documentsNode) {
            this.parent = documentsNode;
        }
    }

    private final DocumentsNode find(DocumentsNode documentsNode, String str) {
        Intrinsics.checkNotNull(documentsNode);
        if (documentsNode.isDirectory() && !documentsNode.getLoaded()) {
            structTree(documentsNode);
        }
        return (DocumentsNode) documentsNode.getChildren().get(str);
    }

    private final DocumentsNode resolvePath(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, File.separator, false);
        DocumentsNode documentsNode = this.root;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            Intrinsics.checkNotNull(nextToken);
            if (!(nextToken.length() == 0) && (documentsNode = find(documentsNode, nextToken)) == null) {
                return null;
            }
        }
        return documentsNode;
    }

    private final void structTree(DocumentsNode documentsNode) {
        FileUtil fileUtil = FileUtil.INSTANCE;
        Uri uri = documentsNode.getUri();
        Intrinsics.checkNotNull(uri);
        for (MinimalDocumentFile minimalDocumentFile : fileUtil.listFiles(uri)) {
            DocumentsNode documentsNode2 = new DocumentsNode(minimalDocumentFile);
            documentsNode2.setParent(documentsNode);
            documentsNode.getChildren().put(documentsNode2.getName(), documentsNode2);
        }
        documentsNode.setLoaded(true);
    }

    public final boolean exists(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        return resolvePath(filepath) != null;
    }

    public final long getFileSize(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        DocumentsNode resolvePath = resolvePath(filepath);
        if (resolvePath == null || resolvePath.isDirectory()) {
            return 0L;
        }
        return FileUtil.getFileSize(String.valueOf(resolvePath.getUri()));
    }

    public final String getFilename(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        DocumentsNode resolvePath = resolvePath(filepath);
        if (resolvePath == null) {
            return filepath;
        }
        String name = resolvePath.getName();
        Intrinsics.checkNotNull(name);
        return name;
    }

    public final String getParentDirectory(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        DocumentsNode resolvePath = resolvePath(filepath);
        Intrinsics.checkNotNull(resolvePath);
        DocumentsNode parent = resolvePath.getParent();
        Uri uri = (parent == null || !parent.isDirectory()) ? resolvePath.getUri() : parent.getUri();
        Intrinsics.checkNotNull(uri);
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return uri2;
    }

    public final boolean isDirectory(String filepath) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        DocumentsNode resolvePath = resolvePath(filepath);
        return resolvePath != null && resolvePath.isDirectory();
    }

    public final int openContentUri(String filepath, String str) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        DocumentsNode resolvePath = resolvePath(filepath);
        if (resolvePath == null) {
            return -1;
        }
        return FileUtil.openContentUri(String.valueOf(resolvePath.getUri()), str);
    }
}
