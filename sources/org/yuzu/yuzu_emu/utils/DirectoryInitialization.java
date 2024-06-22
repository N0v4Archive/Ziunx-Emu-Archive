package org.yuzu.yuzu_emu.utils;

import java.io.File;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.overlay.model.OverlayControl;

/* loaded from: classes.dex */
public final class DirectoryInitialization {
    public static final DirectoryInitialization INSTANCE = new DirectoryInitialization();
    private static boolean areDirectoriesReady;
    private static String userPath;

    private DirectoryInitialization() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String convertButtonId(String str) {
        OverlayControl overlayControl;
        int hashCode = str.hashCode();
        switch (hashCode) {
            case -914328123:
                if (str.equals("buttonToggle10")) {
                    overlayControl = OverlayControl.COMBINED_DPAD;
                    return overlayControl.getId();
                }
                return "";
            case -914328122:
                if (str.equals("buttonToggle11")) {
                    overlayControl = OverlayControl.STICK_L;
                    return overlayControl.getId();
                }
                return "";
            case -914328121:
                if (str.equals("buttonToggle12")) {
                    overlayControl = OverlayControl.STICK_R;
                    return overlayControl.getId();
                }
                return "";
            case -914328120:
                if (str.equals("buttonToggle13")) {
                    overlayControl = OverlayControl.BUTTON_STICK_L;
                    return overlayControl.getId();
                }
                return "";
            case -914328119:
                if (str.equals("buttonToggle14")) {
                    overlayControl = OverlayControl.BUTTON_STICK_R;
                    return overlayControl.getId();
                }
                return "";
            case -914328118:
                if (str.equals("buttonToggle15")) {
                    overlayControl = OverlayControl.BUTTON_HOME;
                    return overlayControl.getId();
                }
                return "";
            case -914328117:
                if (str.equals("buttonToggle16")) {
                    overlayControl = OverlayControl.BUTTON_CAPTURE;
                    return overlayControl.getId();
                }
                return "";
            default:
                switch (hashCode) {
                    case 109052874:
                        if (str.equals("buttonToggle0")) {
                            overlayControl = OverlayControl.BUTTON_A;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052875:
                        if (str.equals("buttonToggle1")) {
                            overlayControl = OverlayControl.BUTTON_B;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052876:
                        if (str.equals("buttonToggle2")) {
                            overlayControl = OverlayControl.BUTTON_X;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052877:
                        if (str.equals("buttonToggle3")) {
                            overlayControl = OverlayControl.BUTTON_Y;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052878:
                        if (str.equals("buttonToggle4")) {
                            overlayControl = OverlayControl.BUTTON_L;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052879:
                        if (str.equals("buttonToggle5")) {
                            overlayControl = OverlayControl.BUTTON_R;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052880:
                        if (str.equals("buttonToggle6")) {
                            overlayControl = OverlayControl.BUTTON_ZL;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052881:
                        if (str.equals("buttonToggle7")) {
                            overlayControl = OverlayControl.BUTTON_ZR;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052882:
                        if (str.equals("buttonToggle8")) {
                            overlayControl = OverlayControl.BUTTON_PLUS;
                            return overlayControl.getId();
                        }
                        return "";
                    case 109052883:
                        if (str.equals("buttonToggle9")) {
                            overlayControl = OverlayControl.BUTTON_MINUS;
                            return overlayControl.getId();
                        }
                        return "";
                    default:
                        return "";
                }
        }
    }

    private final void initializeInternalStorage() {
        try {
            File externalFilesDir = YuzuApplication.Companion.getAppContext().getExternalFilesDir(null);
            Intrinsics.checkNotNull(externalFilesDir);
            userPath = externalFilesDir.getCanonicalPath();
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            String str = userPath;
            Intrinsics.checkNotNull(str);
            nativeLibrary.setAppDirectory(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0aa2  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0b21 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x09fd  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0b2d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x091e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0b39 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0793  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0b51 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x06f2  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0b5e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0bbd  */
    /* JADX WARN: Removed duplicated region for block: B:329:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void migrateSettings() {
        /*
            Method dump skipped, instructions count: 3147
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.utils.DirectoryInitialization.migrateSettings():void");
    }

    public final boolean getAreDirectoriesReady() {
        return areDirectoriesReady;
    }

    public final String getUserDirectory() {
        if (areDirectoriesReady) {
            return userPath;
        }
        throw new IllegalStateException("Directory initialization is not ready!".toString());
    }

    public final void start() {
        if (areDirectoriesReady) {
            return;
        }
        initializeInternalStorage();
        NativeLibrary.INSTANCE.initializeSystem(false);
        NativeConfig.INSTANCE.initializeGlobalConfig();
        migrateSettings();
        areDirectoriesReady = true;
    }
}
