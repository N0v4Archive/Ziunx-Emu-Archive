package coil.util;

/* renamed from: coil.util.-HardwareBitmaps, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class HardwareBitmaps {
    private static final boolean IS_DEVICE_BLOCKED = false;

    public static final HardwareBitmapService HardwareBitmapService(Logger logger) {
        return IS_DEVICE_BLOCKED ? new ImmutableHardwareBitmapService(false) : new ImmutableHardwareBitmapService(true);
    }
}
