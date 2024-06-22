package org.yuzu.yuzu_emu.utils;

import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;

/* loaded from: classes.dex */
public final class GpuDriverHelper {
    public static final GpuDriverHelper INSTANCE = new GpuDriverHelper();
    private static String driverInstallationPath;
    private static String fileRedirectionPath;
    private static String hookLibPath;

    private GpuDriverHelper() {
    }

    public static /* synthetic */ String[] getSystemDriverInfo$default(GpuDriverHelper gpuDriverHelper, Surface surface, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            surface = new Surface(new SurfaceTexture(true));
        }
        if ((i & 2) != 0) {
            str = hookLibPath;
            Intrinsics.checkNotNull(str);
        }
        return gpuDriverHelper.getSystemDriverInfo(surface, str);
    }

    public final boolean copyDriverToInternalStorage(Uri driverUri) {
        Intrinsics.checkNotNullParameter(driverUri, "driverUri");
        initializeDirectories();
        File copyUriToInternalStorage$default = FileUtil.copyUriToInternalStorage$default(FileUtil.INSTANCE, driverUri, getDriverStoragePath(), null, 4, null);
        if (copyUriToInternalStorage$default == null) {
            return false;
        }
        GpuDriverMetadata metadataFromZip = getMetadataFromZip(copyUriToInternalStorage$default);
        if (metadataFromZip.getName() == null) {
            copyUriToInternalStorage$default.delete();
            return false;
        }
        if (metadataFromZip.getMinApi() <= Build.VERSION.SDK_INT) {
            return true;
        }
        copyUriToInternalStorage$default.delete();
        return false;
    }

    public final GpuDriverMetadata getCustomDriverSettingData() {
        return getMetadataFromZip(new File(AbstractStringSetting.DefaultImpls.getString$default(StringSetting.DRIVER_PATH, false, 1, null)));
    }

    public final String getDriverStoragePath() {
        String userDirectory = DirectoryInitialization.INSTANCE.getUserDirectory();
        Intrinsics.checkNotNull(userDirectory);
        return userDirectory + "/gpu_drivers/";
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        r6 = kotlin.collections.CollectionsKt___CollectionsKt.distinct(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
    
        r6 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList((java.util.Collection) r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List getDrivers() {
        /*
            r6 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r6 = r6.getDriverStoragePath()
            r0.<init>(r6)
            java.io.File[] r6 = r0.listFiles()
            if (r6 == 0) goto L54
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r6.length
            r2 = 0
        L16:
            if (r2 >= r1) goto L3c
            r3 = r6[r2]
            org.yuzu.yuzu_emu.utils.GpuDriverHelper r4 = org.yuzu.yuzu_emu.utils.GpuDriverHelper.INSTANCE
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            org.yuzu.yuzu_emu.utils.GpuDriverMetadata r4 = r4.getMetadataFromZip(r3)
            java.lang.String r5 = r4.getName()
            if (r5 == 0) goto L33
            kotlin.Pair r5 = new kotlin.Pair
            java.lang.String r3 = r3.getPath()
            r5.<init>(r3, r4)
            goto L34
        L33:
            r5 = 0
        L34:
            if (r5 == 0) goto L39
            r0.add(r5)
        L39:
            int r2 = r2 + 1
            goto L16
        L3c:
            org.yuzu.yuzu_emu.utils.GpuDriverHelper$getDrivers$$inlined$sortedByDescending$1 r6 = new org.yuzu.yuzu_emu.utils.GpuDriverHelper$getDrivers$$inlined$sortedByDescending$1
            r6.<init>()
            java.util.List r6 = kotlin.collections.CollectionsKt.sortedWith(r0, r6)
            if (r6 == 0) goto L54
            java.util.List r6 = kotlin.collections.CollectionsKt.distinct(r6)
            if (r6 == 0) goto L54
            java.util.List r6 = kotlin.collections.CollectionsKt.toMutableList(r6)
            if (r6 == 0) goto L54
            goto L59
        L54:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L59:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.utils.GpuDriverHelper.getDrivers():java.util.List");
    }

    public final GpuDriverMetadata getInstalledCustomDriverData() {
        return new GpuDriverMetadata(new File(driverInstallationPath + "meta.json"));
    }

    public final GpuDriverMetadata getMetadataFromZip(File driver) {
        ZipFile zipFile;
        boolean contains$default;
        Intrinsics.checkNotNullParameter(driver, "driver");
        try {
            zipFile = new ZipFile(driver);
        } catch (FileNotFoundException | ZipException unused) {
        }
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory()) {
                    String name = nextElement.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String lowerCase = name.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    contains$default = StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) ".json", false, 2, (Object) null);
                    if (contains$default) {
                        InputStream inputStream = zipFile.getInputStream(nextElement);
                        try {
                            Intrinsics.checkNotNull(inputStream);
                            GpuDriverMetadata gpuDriverMetadata = new GpuDriverMetadata(inputStream, nextElement.getSize());
                            CloseableKt.closeFinally(inputStream, null);
                            CloseableKt.closeFinally(zipFile, null);
                            return gpuDriverMetadata;
                        } finally {
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipFile, null);
            return new GpuDriverMetadata();
        } finally {
        }
    }

    public final native String[] getSystemDriverInfo(Surface surface, String str);

    public final void initializeDirectories() {
        String str = fileRedirectionPath;
        Intrinsics.checkNotNull(str);
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = driverInstallationPath;
        Intrinsics.checkNotNull(str2);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(getDriverStoragePath());
        if (file3.exists()) {
            return;
        }
        file3.mkdirs();
    }

    public final void initializeDriverParameters() {
        try {
            YuzuApplication.Companion companion = YuzuApplication.Companion;
            File externalFilesDir = companion.getAppContext().getExternalFilesDir(null);
            Intrinsics.checkNotNull(externalFilesDir);
            fileRedirectionPath = externalFilesDir.getCanonicalPath() + "/gpu/vk_file_redirect/";
            driverInstallationPath = companion.getAppContext().getFilesDir().getCanonicalPath() + "/gpu_driver/";
            initializeDirectories();
            hookLibPath = companion.getAppContext().getApplicationInfo().nativeLibraryDir + "/";
            NativeLibrary.INSTANCE.initializeGpuDriver(hookLibPath, driverInstallationPath, getInstalledCustomDriverData().getLibraryName(), fileRedirectionPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean installCustomDriver(File driver) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        installDefaultDriver();
        initializeDirectories();
        if (getMetadataFromZip(driver).getName() == null) {
            driver.delete();
            return false;
        }
        try {
            FileUtil fileUtil = FileUtil.INSTANCE;
            String path = driver.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            String str = driverInstallationPath;
            Intrinsics.checkNotNull(str);
            FileUtil.unzipToInternalStorage$default(fileUtil, path, new File(str), null, 4, null);
            initializeDriverParameters();
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public final void installDefaultDriver() {
        String str = driverInstallationPath;
        Intrinsics.checkNotNull(str);
        FilesKt.deleteRecursively(new File(str));
        initializeDriverParameters();
    }

    public final native boolean supportsCustomDriverLoading();
}
