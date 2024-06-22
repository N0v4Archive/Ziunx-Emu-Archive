package org.yuzu.yuzu_emu;

import android.R;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Surface;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.activities.EmulationActivity;
import org.yuzu.yuzu_emu.model.Patch;
import org.yuzu.yuzu_emu.utils.DocumentsTree;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.Log;

/* loaded from: classes.dex */
public final class NativeLibrary {
    private static final Object coreErrorAlertLock;
    public static final NativeLibrary INSTANCE = new NativeLibrary();
    public static WeakReference sEmulationActivity = new WeakReference(null);

    static {
        try {
            System.loadLibrary("yuzu-android");
            coreErrorAlertLock = new Object();
        } catch (UnsatisfiedLinkError e) {
            throw new IllegalStateException(("[NativeLibrary] " + e).toString());
        }
    }

    private NativeLibrary() {
    }

    @Keep
    public static final boolean exists(String str) {
        DocumentsTree.Companion companion = DocumentsTree.Companion;
        Intrinsics.checkNotNull(str);
        if (!companion.isNativePath(str)) {
            return FileUtil.INSTANCE.exists(str, true);
        }
        DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
        Intrinsics.checkNotNull(documentsTree);
        return documentsTree.exists(str);
    }

    @Keep
    public static final void exitEmulationActivity(int i) {
        int i2;
        int i3;
        if (i == 5) {
            i2 = R$string.loader_error_video_core;
            i3 = R$string.loader_error_video_core_description;
        } else {
            i2 = R$string.loader_error_encrypted;
            i3 = R$string.loader_error_encrypted_roms_description;
            if (!INSTANCE.reloadKeys()) {
                i3 = R$string.loader_error_encrypted_keys_description;
            }
        }
        final EmulationActivity emulationActivity = (EmulationActivity) sEmulationActivity.get();
        if (emulationActivity == null) {
            Log.INSTANCE.warning("[NativeLibrary] EmulationActivity is null, can't exit.");
            return;
        }
        final MaterialAlertDialogBuilder onDismissListener = new MaterialAlertDialogBuilder(emulationActivity).setTitle(i2).setMessage((CharSequence) Html.fromHtml(emulationActivity.getString(i3), 0)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.NativeLibrary$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i4) {
                EmulationActivity.this.finish();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.yuzu.yuzu_emu.NativeLibrary$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                EmulationActivity.this.finish();
            }
        });
        Intrinsics.checkNotNullExpressionValue(onDismissListener, "setOnDismissListener(...)");
        emulationActivity.runOnUiThread(new Runnable() { // from class: org.yuzu.yuzu_emu.NativeLibrary$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NativeLibrary.exitEmulationActivity$lambda$5(MaterialAlertDialogBuilder.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exitEmulationActivity$lambda$5(MaterialAlertDialogBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "$builder");
        AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.show();
        View findViewById = create.findViewById(R.id.message);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Keep
    public static final String getFilename(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (DocumentsTree.Companion.isNativePath(path)) {
            DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
            Intrinsics.checkNotNull(documentsTree);
            return documentsTree.getFilename(path);
        }
        FileUtil fileUtil = FileUtil.INSTANCE;
        Uri parse = Uri.parse(path);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        return fileUtil.getFilename(parse);
    }

    @Keep
    public static final String getParentDirectory(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (!DocumentsTree.Companion.isNativePath(path)) {
            return path;
        }
        DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
        Intrinsics.checkNotNull(documentsTree);
        return documentsTree.getParentDirectory(path);
    }

    @Keep
    public static final long getSize(String str) {
        DocumentsTree.Companion companion = DocumentsTree.Companion;
        Intrinsics.checkNotNull(str);
        if (!companion.isNativePath(str)) {
            return FileUtil.getFileSize(str);
        }
        DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
        Intrinsics.checkNotNull(documentsTree);
        return documentsTree.getFileSize(str);
    }

    @Keep
    public static final boolean isDirectory(String str) {
        DocumentsTree.Companion companion = DocumentsTree.Companion;
        Intrinsics.checkNotNull(str);
        if (!companion.isNativePath(str)) {
            return FileUtil.INSTANCE.isDirectory(str);
        }
        DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
        Intrinsics.checkNotNull(documentsTree);
        return documentsTree.isDirectory(str);
    }

    @Keep
    public static final void onEmulationStarted() {
        Object obj = sEmulationActivity.get();
        Intrinsics.checkNotNull(obj);
        ((EmulationActivity) obj).onEmulationStarted();
    }

    @Keep
    public static final void onEmulationStopped(int i) {
        Object obj = sEmulationActivity.get();
        Intrinsics.checkNotNull(obj);
        ((EmulationActivity) obj).onEmulationStopped(i);
    }

    @Keep
    public static final void onProgramChanged(int i) {
        Object obj = sEmulationActivity.get();
        Intrinsics.checkNotNull(obj);
        ((EmulationActivity) obj).onProgramChanged(i);
    }

    @Keep
    public static final int openContentUri(String str, String str2) {
        DocumentsTree.Companion companion = DocumentsTree.Companion;
        Intrinsics.checkNotNull(str);
        if (!companion.isNativePath(str)) {
            return FileUtil.openContentUri(str, str2);
        }
        DocumentsTree documentsTree = YuzuApplication.Companion.getDocumentsTree();
        Intrinsics.checkNotNull(documentsTree);
        return documentsTree.openContentUri(str, str2);
    }

    public final native void addFileToFilesystemProvider(String str);

    public final native void applySettings();

    public final native boolean areKeysPresent();

    public final void clearEmulationActivity() {
        Log.INSTANCE.debug("[NativeLibrary] Unregistering EmulationActivity.");
        sEmulationActivity.clear();
    }

    public final native void clearFilesystemProvider();

    public final native boolean doesUpdateMatchProgram(String str, String str2);

    public final native String getAppletLaunchPath(long j);

    public final native String getCpuBackend();

    public final native String getDefaultProfileSaveDataRoot(boolean z);

    public final native String getGpuDriver();

    public final native Patch[] getPatchesForFile(String str, String str2);

    public final native double[] getPerfStats();

    public final native String getSavePath(String str);

    public final native void initializeGpuDriver(String str, String str2, String str3, String str4);

    public final native void initializeSystem(boolean z);

    public final native int installFileToNand(String str, Function2 function2);

    public final native boolean isFirmwareAvailable();

    public final native boolean isPaused();

    public final native boolean isRunning();

    public final native void logDeviceInfo();

    public final native void logSettings();

    public final native void pauseEmulation();

    public final native boolean reloadKeys();

    public final native void removeDLC(String str);

    public final native void removeMod(String str, String str2);

    public final native void removeUpdate(String str);

    public final native void run(String str, int i, boolean z);

    public final native void setAppDirectory(String str);

    public final native void setCabinetMode(int i);

    public final native void setCurrentAppletId(int i);

    public final void setEmulationActivity(EmulationActivity emulationActivity) {
        Log.INSTANCE.debug("[NativeLibrary] Registering EmulationActivity.");
        sEmulationActivity = new WeakReference(emulationActivity);
    }

    public final native void stopEmulation();

    public final native void submitInlineKeyboardInput(int i);

    public final native void submitInlineKeyboardText(String str);

    public final native void surfaceChanged(Surface surface);

    public final native void unpauseEmulation();

    public final native int verifyGameContents(String str, Function2 function2);

    public final native String[] verifyInstalledContents(Function2 function2);
}
