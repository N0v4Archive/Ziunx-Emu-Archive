package coil.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import okio.FileSystem;
import okio.Path;

/* renamed from: coil.util.-FileSystems, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class FileSystems {
    public static final void createFile(FileSystem fileSystem, Path path) {
        if (fileSystem.exists(path)) {
            return;
        }
        Utils.closeQuietly(fileSystem.sink(path));
    }

    public static final void deleteContents(FileSystem fileSystem, Path path) {
        try {
            IOException iOException = null;
            for (Path path2 : fileSystem.list(path)) {
                try {
                    if (fileSystem.metadata(path2).isDirectory()) {
                        deleteContents(fileSystem, path2);
                    }
                    fileSystem.delete(path2);
                } catch (IOException e) {
                    if (iOException == null) {
                        iOException = e;
                    }
                }
            }
            if (iOException != null) {
                throw iOException;
            }
        } catch (FileNotFoundException unused) {
        }
    }
}
