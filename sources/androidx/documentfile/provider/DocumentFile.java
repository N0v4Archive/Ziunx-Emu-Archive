package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;

/* loaded from: classes.dex */
public abstract class DocumentFile {
    private final DocumentFile mParent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentFile(DocumentFile documentFile) {
        this.mParent = documentFile;
    }

    public static DocumentFile fromSingleUri(Context context, Uri uri) {
        return new SingleDocumentFile(null, context, uri);
    }

    public static DocumentFile fromTreeUri(Context context, Uri uri) {
        String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
        if (DocumentsContract.isDocumentUri(context, uri)) {
            treeDocumentId = DocumentsContract.getDocumentId(uri);
        }
        return new TreeDocumentFile(null, context, DocumentsContract.buildDocumentUriUsingTree(uri, treeDocumentId));
    }

    public abstract boolean exists();

    public abstract String getName();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract DocumentFile[] listFiles();
}
