package org.yuzu.yuzu_emu.features;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.YuzuApplicationKt;

/* loaded from: classes.dex */
public final class DocumentProvider extends DocumentsProvider {
    public static final Companion Companion = new Companion(null);
    private static final String[] DEFAULT_ROOT_PROJECTION = {"root_id", "mime_types", "flags", "icon", "title", "summary", "document_id", "available_bytes"};
    private static final String[] DEFAULT_DOCUMENT_PROJECTION = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size"};

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final String copyDocument(String str, String str2, String str3) {
        if (isChildDocument(str2, str)) {
            return copyDocument(str, str3);
        }
        throw new FileNotFoundException("Couldn't copy document '" + str + "' as its parent is not '" + str2 + "'");
    }

    private final File getBaseDirectory() {
        return new File(YuzuApplicationKt.getPublicFilesDir(YuzuApplication.Companion.getApplication()).getCanonicalPath());
    }

    private final String getDocumentId(File file) {
        String relativeString;
        relativeString = FilesKt__UtilsKt.toRelativeString(file, getBaseDirectory());
        return "root/" + relativeString;
    }

    private final File getFile(String str) {
        boolean startsWith$default;
        String drop;
        File resolve;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "root", false, 2, null);
        if (!startsWith$default) {
            throw new FileNotFoundException("'" + str + "' is not in any known root");
        }
        File baseDirectory = getBaseDirectory();
        drop = StringsKt___StringsKt.drop(str, 5);
        resolve = FilesKt__UtilsKt.resolve(baseDirectory, drop);
        if (resolve.exists()) {
            return resolve;
        }
        throw new FileNotFoundException(resolve.getAbsolutePath() + " (" + str + ") not found");
    }

    private final Object getTypeForFile(File file) {
        if (file.isDirectory()) {
            return "vnd.android.document/directory";
        }
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return getTypeForName(name);
    }

    private final Object getTypeForName(String str) {
        int lastIndexOf$default;
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, '.', 0, false, 6, (Object) null);
        if (lastIndexOf$default < 0) {
            return "application/octect-stream";
        }
        String substring = str.substring(lastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(substring);
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octect-stream";
    }

    private final MatrixCursor includeFile(MatrixCursor matrixCursor, String str, File file) {
        String name;
        String documentId = str == null ? file != null ? getDocumentId(file) : null : str;
        if (file == null) {
            Intrinsics.checkNotNull(str);
            file = getFile(str);
        }
        int i = (file.isDirectory() && file.canWrite()) ? 8 : file.canWrite() ? 1478 : 0;
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add("document_id", documentId);
        if (Intrinsics.areEqual(file, getBaseDirectory())) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            name = context.getString(R$string.app_name);
        } else {
            name = file.getName();
        }
        newRow.add("_display_name", name);
        newRow.add("_size", Long.valueOf(file.length()));
        newRow.add("mime_type", getTypeForFile(file));
        newRow.add("last_modified", Long.valueOf(file.lastModified()));
        newRow.add("flags", Integer.valueOf(i));
        if (Intrinsics.areEqual(file, getBaseDirectory())) {
            newRow.add("icon", Integer.valueOf(R$drawable.ic_yuzu));
        }
        return matrixCursor;
    }

    private final File resolveWithoutConflict(File file, String str) {
        File resolve;
        String substringAfterLast$default;
        String substringBeforeLast$default;
        File resolve2;
        resolve = FilesKt__UtilsKt.resolve(file, str);
        if (resolve.exists()) {
            substringAfterLast$default = StringsKt__StringsKt.substringAfterLast$default(str, '.', null, 2, null);
            substringBeforeLast$default = StringsKt__StringsKt.substringBeforeLast$default(str, '.', null, 2, null);
            int i = 1;
            while (resolve.exists()) {
                int i2 = i + 1;
                resolve2 = FilesKt__UtilsKt.resolve(file, substringBeforeLast$default + " (" + i + ")." + substringAfterLast$default);
                i = i2;
                resolve = resolve2;
            }
        }
        return resolve;
    }

    @Override // android.provider.DocumentsProvider
    public String copyDocument(String sourceDocumentId, String str) {
        Intrinsics.checkNotNullParameter(sourceDocumentId, "sourceDocumentId");
        Intrinsics.checkNotNull(str);
        File file = getFile(str);
        File file2 = getFile(sourceDocumentId);
        String name = file2.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        File resolveWithoutConflict = resolveWithoutConflict(file, name);
        try {
            if (!resolveWithoutConflict.createNewFile() || !resolveWithoutConflict.setWritable(true) || !resolveWithoutConflict.setReadable(true)) {
                throw new IOException("Couldn't create new file");
            }
            FileInputStream fileInputStream = new FileInputStream(file2);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(resolveWithoutConflict);
                try {
                    ByteStreamsKt.copyTo$default(fileInputStream, fileOutputStream, 0, 2, null);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    return getDocumentId(resolveWithoutConflict);
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Couldn't copy document '" + sourceDocumentId + "': " + e.getMessage());
        }
    }

    @Override // android.provider.DocumentsProvider
    public String createDocument(String str, String str2, String displayName) {
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNull(str);
        File resolveWithoutConflict = resolveWithoutConflict(getFile(str), displayName);
        try {
            if (Intrinsics.areEqual("vnd.android.document/directory", str2)) {
                if (!resolveWithoutConflict.mkdir()) {
                    throw new IOException("Failed to create directory");
                }
            } else if (!resolveWithoutConflict.createNewFile()) {
                throw new IOException("Failed to create file");
            }
            return getDocumentId(resolveWithoutConflict);
        } catch (IOException e) {
            throw new FileNotFoundException("Couldn't create document '" + resolveWithoutConflict.getPath() + "': " + e.getMessage());
        }
    }

    @Override // android.provider.DocumentsProvider
    public void deleteDocument(String str) {
        Intrinsics.checkNotNull(str);
        if (getFile(str).delete()) {
            return;
        }
        throw new FileNotFoundException("Couldn't delete document with ID '" + str + "'");
    }

    @Override // android.provider.DocumentsProvider
    public boolean isChildDocument(String str, String str2) {
        boolean startsWith$default;
        if (str2 == null) {
            return false;
        }
        Intrinsics.checkNotNull(str);
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str2, str, false, 2, null);
        return startsWith$default;
    }

    @Override // android.provider.DocumentsProvider
    public String moveDocument(String sourceDocumentId, String str, String str2) {
        Intrinsics.checkNotNullParameter(sourceDocumentId, "sourceDocumentId");
        try {
            Intrinsics.checkNotNull(str);
            String copyDocument = copyDocument(sourceDocumentId, str, str2);
            removeDocument(sourceDocumentId, str);
            return copyDocument;
        } catch (FileNotFoundException unused) {
            throw new FileNotFoundException("Couldn't move document '" + sourceDocumentId + "'");
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.provider.DocumentsProvider
    public ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) {
        ParcelFileDescriptor open = ParcelFileDescriptor.open(str != null ? getFile(str) : null, ParcelFileDescriptor.parseMode(str2));
        Intrinsics.checkNotNullExpressionValue(open, "open(...)");
        return open;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryChildDocuments(String str, String[] strArr, String str2) {
        if (strArr == null) {
            strArr = DEFAULT_DOCUMENT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        Intrinsics.checkNotNull(str);
        File[] listFiles = getFile(str).listFiles();
        Intrinsics.checkNotNull(listFiles);
        for (File file : listFiles) {
            matrixCursor = includeFile(matrixCursor, null, file);
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryDocument(String str, String[] strArr) {
        if (strArr == null) {
            strArr = DEFAULT_DOCUMENT_PROJECTION;
        }
        return includeFile(new MatrixCursor(strArr), str, null);
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryRoots(String[] strArr) {
        if (strArr == null) {
            strArr = DEFAULT_ROOT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add("root_id", "root");
        newRow.add("summary", null);
        newRow.add("flags", 17);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        newRow.add("title", context.getString(R$string.app_name));
        newRow.add("document_id", getDocumentId(getBaseDirectory()));
        newRow.add("mime_types", "*/*");
        newRow.add("available_bytes", Long.valueOf(getBaseDirectory().getFreeSpace()));
        newRow.add("icon", Integer.valueOf(R$drawable.ic_yuzu));
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public void removeDocument(String documentId, String str) {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNull(str);
        File file = getFile(str);
        File file2 = getFile(documentId);
        if (!Intrinsics.areEqual(file, file2) && file2.getParentFile() != null) {
            File parentFile = file2.getParentFile();
            Intrinsics.checkNotNull(parentFile);
            if (!Intrinsics.areEqual(parentFile, file)) {
                throw new FileNotFoundException("Couldn't delete document with ID '" + documentId + "'");
            }
        }
        if (file2.delete()) {
            return;
        }
        throw new FileNotFoundException("Couldn't delete document with ID '" + documentId + "'");
    }

    @Override // android.provider.DocumentsProvider
    public String renameDocument(String str, String str2) {
        File resolve;
        if (str2 == null) {
            throw new FileNotFoundException("Couldn't rename document '" + str + "' as the new name is null");
        }
        Intrinsics.checkNotNull(str);
        File file = getFile(str);
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            throw new FileNotFoundException("Couldn't rename document '" + str + "' as it has no parent");
        }
        resolve = FilesKt__UtilsKt.resolve(parentFile, str2);
        try {
            if (file.renameTo(resolve)) {
                return getDocumentId(resolve);
            }
            throw new FileNotFoundException("Couldn't rename document from '" + file.getName() + "' to '" + resolve.getName() + "'");
        } catch (Exception e) {
            throw new FileNotFoundException("Couldn't rename document from '" + file.getName() + "' to '" + resolve.getName() + "': " + e.getMessage());
        }
    }
}
