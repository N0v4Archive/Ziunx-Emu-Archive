package org.yuzu.yuzu_emu.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.MinimalDocumentFile;
import org.yuzu.yuzu_emu.model.TaskState;

/* loaded from: classes.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public static /* synthetic */ void copyFilesTo$default(FileUtil fileUtil, DocumentFile documentFile, File file, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new Function2() { // from class: org.yuzu.yuzu_emu.utils.FileUtil$copyFilesTo$1
                public final Boolean invoke(long j, long j2) {
                    return Boolean.FALSE;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).longValue(), ((Number) obj3).longValue());
                }
            };
        }
        fileUtil.copyFilesTo(documentFile, file, function2);
    }

    public static /* synthetic */ File copyUriToInternalStorage$default(FileUtil fileUtil, Uri uri, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return fileUtil.copyUriToInternalStorage(uri, str, str2);
    }

    private final Context getContext() {
        return YuzuApplication.Companion.getAppContext();
    }

    public static final long getFileSize(String path) {
        long j;
        Intrinsics.checkNotNullParameter(path, "path");
        FileUtil fileUtil = INSTANCE;
        Cursor cursor = null;
        try {
            try {
                cursor = fileUtil.getContext().getContentResolver().query(Uri.parse(path), new String[]{"_size"}, null, null, null);
                Intrinsics.checkNotNull(cursor);
                cursor.moveToNext();
                j = cursor.getLong(0);
                fileUtil.closeQuietly(cursor);
            } catch (Exception e) {
                Log.INSTANCE.error("[FileUtil]: Cannot get file size, error: " + e.getMessage());
                INSTANCE.closeQuietly(cursor);
                j = 0;
            }
            return j;
        } catch (Throwable th) {
            INSTANCE.closeQuietly(cursor);
            throw th;
        }
    }

    public static final int openContentUri(String path, String str) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            Uri parse = Uri.parse(path);
            ContentResolver contentResolver = INSTANCE.getContext().getContentResolver();
            Intrinsics.checkNotNull(str);
            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(parse, str);
            if (openFileDescriptor != null) {
                int detachFd = openFileDescriptor.detachFd();
                openFileDescriptor.close();
                return detachFd;
            }
            Log.INSTANCE.error("[FileUtil]: Cannot get the file descriptor from uri: " + path);
            return -1;
        } catch (Exception e) {
            Log.INSTANCE.error("[FileUtil]: Cannot open content uri, error: " + e.getMessage());
            return -1;
        }
    }

    public static /* synthetic */ void unzipToInternalStorage$default(FileUtil fileUtil, String str, File file, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = new Function2() { // from class: org.yuzu.yuzu_emu.utils.FileUtil$unzipToInternalStorage$1
                public final Boolean invoke(long j, long j2) {
                    return Boolean.FALSE;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).longValue(), ((Number) obj3).longValue());
                }
            };
        }
        fileUtil.unzipToInternalStorage(str, file, function2);
    }

    public static /* synthetic */ TaskState zipFromInternalStorage$default(FileUtil fileUtil, File file, String str, BufferedOutputStream bufferedOutputStream, Function2 function2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = new Function2() { // from class: org.yuzu.yuzu_emu.utils.FileUtil$zipFromInternalStorage$1
                public final Boolean invoke(long j, long j2) {
                    return Boolean.FALSE;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).longValue(), ((Number) obj3).longValue());
                }
            };
        }
        Function2 function22 = function2;
        if ((i & 16) != 0) {
            z = true;
        }
        return fileUtil.zipFromInternalStorage(file, str, bufferedOutputStream, function22, z);
    }

    public final void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public final void copyFilesTo(DocumentFile documentFile, File file, Function2 progressCallback) {
        Intrinsics.checkNotNullParameter(documentFile, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(progressCallback, "progressCallback");
        file.mkdirs();
        if (!documentFile.isDirectory() || !file.isDirectory()) {
            throw new IllegalStateException("[FileUtil] Tried to copy a folder into a file or vice versa");
        }
        long length = documentFile.listFiles().length;
        DocumentFile[] listFiles = documentFile.listFiles();
        Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
        long j = 0;
        for (DocumentFile documentFile2 : listFiles) {
            if (((Boolean) progressCallback.invoke(Long.valueOf(length), Long.valueOf(j))).booleanValue()) {
                return;
            }
            String name = documentFile2.getName();
            Intrinsics.checkNotNull(name);
            File file2 = new File(file, name);
            if (documentFile2.isDirectory()) {
                file2.mkdirs();
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(YuzuApplication.Companion.getAppContext(), documentFile2.getUri());
                if (fromTreeUri != null) {
                    FileUtil fileUtil = INSTANCE;
                    Intrinsics.checkNotNull(fromTreeUri);
                    copyFilesTo$default(fileUtil, fromTreeUri, file2, null, 2, null);
                }
            } else {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(YuzuApplication.Companion.getAppContext().getContentResolver().openInputStream(documentFile2.getUri()));
                try {
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        ByteStreamsKt.copyTo$default(bufferedInputStream, fileOutputStream, 0, 2, null);
                        CloseableKt.closeFinally(fileOutputStream, null);
                        CloseableKt.closeFinally(bufferedInputStream, null);
                    } finally {
                    }
                } finally {
                }
            }
            j++;
        }
    }

    public final File copyUriToInternalStorage(Uri sourceUri, String destinationParentPath, String destinationFilename) {
        String str;
        Intrinsics.checkNotNullParameter(sourceUri, "sourceUri");
        Intrinsics.checkNotNullParameter(destinationParentPath, "destinationParentPath");
        Intrinsics.checkNotNullParameter(destinationFilename, "destinationFilename");
        try {
            if (Intrinsics.areEqual(destinationFilename, "")) {
                str = getFilename(sourceUri);
            } else {
                str = "/" + destinationFilename;
            }
            InputStream openInputStream = getContext().getContentResolver().openInputStream(sourceUri);
            Intrinsics.checkNotNull(openInputStream);
            File file = new File(destinationParentPath + str);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    ByteStreamsKt.copyTo$default(openInputStream, fileOutputStream, 0, 2, null);
                    CloseableKt.closeFinally(openInputStream, null);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    return file;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        } catch (IOException | NullPointerException unused) {
            return null;
        }
    }

    public final boolean exists(String str, boolean z) {
        Cursor cursor = null;
        try {
            cursor = getContext().getContentResolver().query(Uri.parse(str), new String[]{"document_id"}, null, null, null);
            Intrinsics.checkNotNull(cursor);
            return cursor.getCount() > 0;
        } catch (Exception e) {
            if (!z) {
                Log.INSTANCE.info("[FileUtil] Cannot find file from given path, error: " + e.getMessage());
            }
            return false;
        } finally {
            closeQuietly(cursor);
        }
    }

    public final String getExtension(Uri uri) {
        int lastIndexOf$default;
        Intrinsics.checkNotNullParameter(uri, "uri");
        String filename = getFilename(uri);
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) filename, ".", 0, false, 6, (Object) null);
        String substring = filename.substring(lastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String lowerCase = substring.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public final String getFilename(Uri uri) {
        String str;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Cursor cursor = null;
        try {
            try {
                cursor = YuzuApplication.Companion.getAppContext().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                Intrinsics.checkNotNull(cursor);
                cursor.moveToNext();
                str = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
            } catch (Exception e) {
                Log.INSTANCE.error("[FileUtil]: Cannot get file size, error: " + e.getMessage());
                closeQuietly(cursor);
                str = "";
            }
            return str;
        } finally {
            closeQuietly(cursor);
        }
    }

    public final InputStream getInputStream(String path) {
        boolean contains$default;
        Intrinsics.checkNotNullParameter(path, "path");
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) path, (CharSequence) "content://", false, 2, (Object) null);
        if (!contains$default) {
            return new FileInputStream(new File(path));
        }
        Uri parse = Uri.parse(path);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        return inputStream(parse);
    }

    public final String getStringFromFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        byte[] readBytes = FilesKt.readBytes(file);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return new String(readBytes, UTF_8);
    }

    public final String getStringFromInputStream(InputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        byte[] readBytes = ByteStreamsKt.readBytes(stream);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return new String(readBytes, UTF_8);
    }

    public final InputStream inputStream(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        InputStream openInputStream = YuzuApplication.Companion.getAppContext().getContentResolver().openInputStream(uri);
        Intrinsics.checkNotNull(openInputStream);
        return openInputStream;
    }

    public final boolean isDirectory(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = getContext().getContentResolver().query(Uri.parse(path), new String[]{"mime_type"}, null, null, null);
                Intrinsics.checkNotNull(cursor);
                cursor.moveToNext();
                z = Intrinsics.areEqual(cursor.getString(0), "vnd.android.document/directory");
            } catch (Exception e) {
                Log.INSTANCE.error("[FileUtil]: Cannot list files, error: " + e.getMessage());
            }
            return z;
        } finally {
            closeQuietly(cursor);
        }
    }

    public final boolean isRootTreeUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        List<String> pathSegments = uri.getPathSegments();
        return pathSegments.size() == 2 && Intrinsics.areEqual("tree", pathSegments.get(0));
    }

    public final boolean isTreeUriValid(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ContentResolver contentResolver = getContext().getContentResolver();
        String[] strArr = {"document_id", "_display_name", "mime_type"};
        try {
            String treeDocumentId = isRootTreeUri(uri) ? DocumentsContract.getTreeDocumentId(uri) : DocumentsContract.getDocumentId(uri);
            Intrinsics.checkNotNull(treeDocumentId);
            contentResolver.query(DocumentsContract.buildChildDocumentsUriUsingTree(uri, treeDocumentId), strArr, null, null, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final MinimalDocumentFile[] listFiles(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ContentResolver contentResolver = getContext().getContentResolver();
        String[] strArr = {"document_id", "_display_name", "mime_type"};
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                String treeDocumentId = isRootTreeUri(uri) ? DocumentsContract.getTreeDocumentId(uri) : DocumentsContract.getDocumentId(uri);
                Intrinsics.checkNotNull(treeDocumentId);
                cursor = contentResolver.query(DocumentsContract.buildChildDocumentsUriUsingTree(uri, treeDocumentId), strArr, null, null, null);
                while (true) {
                    Intrinsics.checkNotNull(cursor);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Uri buildDocumentUriUsingTree = DocumentsContract.buildDocumentUriUsingTree(uri, string);
                    Intrinsics.checkNotNull(string2);
                    Intrinsics.checkNotNull(string3);
                    Intrinsics.checkNotNull(buildDocumentUriUsingTree);
                    arrayList.add(new MinimalDocumentFile(string2, string3, buildDocumentUriUsingTree));
                }
            } catch (Exception e) {
                Log.INSTANCE.error("[FileUtil]: Cannot list file error: " + e.getMessage());
            }
            closeQuietly(cursor);
            return (MinimalDocumentFile[]) arrayList.toArray(new MinimalDocumentFile[0]);
        } catch (Throwable th) {
            closeQuietly(cursor);
            throw th;
        }
    }

    public final void unzipToInternalStorage(String path, File destDir, Function2 progressCallback) {
        boolean startsWith$default;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(destDir, "destDir");
        Intrinsics.checkNotNullParameter(progressCallback, "progressCallback");
        ZipInputStream zipInputStream = new ZipInputStream(getInputStream(path));
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            long j = 0;
            while (nextEntry != null) {
                nextEntry = zipInputStream.getNextEntry();
                j++;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipInputStream, null);
            zipInputStream = new ZipInputStream(getInputStream(path));
            try {
                ZipEntry nextEntry2 = zipInputStream.getNextEntry();
                for (long j2 = 0; nextEntry2 != null && !((Boolean) progressCallback.invoke(Long.valueOf(j), Long.valueOf(j2))).booleanValue(); j2++) {
                    File file = new File(destDir, nextEntry2.getName());
                    File parentFile = nextEntry2.isDirectory() ? file : file.getParentFile();
                    String canonicalPath = file.getCanonicalPath();
                    Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
                    startsWith$default = StringsKt__StringsJVMKt.startsWith$default(canonicalPath, destDir.getCanonicalPath() + File.separator, false, 2, null);
                    if (!startsWith$default) {
                        throw new SecurityException("Zip file attempted path traversal! " + nextEntry2.getName());
                    }
                    if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + parentFile);
                    }
                    if (!nextEntry2.isDirectory()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            ByteStreamsKt.copyTo$default(zipInputStream, fileOutputStream, 0, 2, null);
                            CloseableKt.closeFinally(fileOutputStream, null);
                        } finally {
                        }
                    }
                    nextEntry2 = zipInputStream.getNextEntry();
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(zipInputStream, null);
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final TaskState zipFromInternalStorage(File inputFile, String rootDir, BufferedOutputStream outputStream, Function2 progressCallback, boolean z) {
        int count;
        String removePrefix;
        String removePrefix2;
        Intrinsics.checkNotNullParameter(inputFile, "inputFile");
        Intrinsics.checkNotNullParameter(rootDir, "rootDir");
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        Intrinsics.checkNotNullParameter(progressCallback, "progressCallback");
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            if (!z) {
                try {
                    zipOutputStream.setMethod(8);
                    zipOutputStream.setLevel(0);
                } finally {
                }
            }
            Ref$LongRef ref$LongRef = new Ref$LongRef();
            count = SequencesKt___SequencesKt.count(FilesKt.walkTopDown(inputFile));
            long j = count;
            for (File file : FilesKt.walkTopDown(inputFile)) {
                if (((Boolean) progressCallback.invoke(Long.valueOf(j), Long.valueOf(ref$LongRef.element))).booleanValue()) {
                    TaskState taskState = TaskState.Cancelled;
                    CloseableKt.closeFinally(zipOutputStream, null);
                    return taskState;
                }
                if (!file.isDirectory()) {
                    String absolutePath = file.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                    removePrefix = StringsKt__StringsKt.removePrefix(absolutePath, rootDir);
                    removePrefix2 = StringsKt__StringsKt.removePrefix(removePrefix, "/");
                    zipOutputStream.putNextEntry(new ZipEntry(removePrefix2));
                    if (file.isFile()) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            ByteStreamsKt.copyTo$default(fileInputStream, zipOutputStream, 0, 2, null);
                            CloseableKt.closeFinally(fileInputStream, null);
                        } finally {
                        }
                    }
                    ref$LongRef.element++;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, null);
            return TaskState.Completed;
        } catch (Exception e) {
            Log.INSTANCE.error("[FileUtil] Failed creating zip file - " + e.getMessage());
            return TaskState.Failed;
        }
    }
}
