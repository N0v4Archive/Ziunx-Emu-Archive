package coil.key;

import coil.request.Options;
import java.io.File;

/* loaded from: classes.dex */
public final class FileKeyer implements Keyer {
    private final boolean addLastModifiedToFileCacheKey;

    public FileKeyer(boolean z) {
        this.addLastModifiedToFileCacheKey = z;
    }

    @Override // coil.key.Keyer
    public String key(File file, Options options) {
        if (!this.addLastModifiedToFileCacheKey) {
            return file.getPath();
        }
        return file.getPath() + ':' + file.lastModified();
    }
}
