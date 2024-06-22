package kotlin.io;

import java.io.File;

/* loaded from: classes.dex */
public abstract class FilesKt extends FilesKt__UtilsKt {
    public static /* bridge */ /* synthetic */ boolean deleteRecursively(File file) {
        return FilesKt__UtilsKt.deleteRecursively(file);
    }

    public static /* bridge */ /* synthetic */ byte[] readBytes(File file) {
        return FilesKt__FileReadWriteKt.readBytes(file);
    }

    public static /* bridge */ /* synthetic */ FileTreeWalk walkTopDown(File file) {
        return FilesKt__FileTreeWalkKt.walkTopDown(file);
    }
}
