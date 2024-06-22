package coil.util;

import android.content.Context;
import coil.disk.DiskCache;
import java.io.File;
import kotlin.io.FilesKt__UtilsKt;

/* loaded from: classes.dex */
public final class SingletonDiskCache {
    public static final SingletonDiskCache INSTANCE = new SingletonDiskCache();
    private static DiskCache instance;

    private SingletonDiskCache() {
    }

    public final synchronized DiskCache get(Context context) {
        DiskCache diskCache;
        File resolve;
        diskCache = instance;
        if (diskCache == null) {
            DiskCache.Builder builder = new DiskCache.Builder();
            resolve = FilesKt__UtilsKt.resolve(Utils.getSafeCacheDir(context), "image_cache");
            diskCache = builder.directory(resolve).build();
            instance = diskCache;
        }
        return diskCache;
    }
}
