package org.yuzu.yuzu_emu.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;

/* loaded from: classes.dex */
public final class MemoryUtil {
    public static final MemoryUtil INSTANCE = new MemoryUtil();

    private MemoryUtil() {
    }

    public static /* synthetic */ String bytesToSizeUnit$default(MemoryUtil memoryUtil, float f, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return memoryUtil.bytesToSizeUnit(f, z);
    }

    private final Context getContext() {
        return YuzuApplication.Companion.getAppContext();
    }

    private final String getHundredths(float f) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ROOT, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final String bytesToSizeUnit(float f, boolean z) {
        String string;
        if (f < 1024.0f) {
            string = getContext().getString(R$string.memory_formatted, getHundredths(f), getContext().getString(R$string.memory_byte_shorthand));
        } else if (f < 1048576.0f) {
            Context context = getContext();
            int i = R$string.memory_formatted;
            Object[] objArr = new Object[2];
            float f2 = f / 1024.0f;
            objArr[0] = z ? Float.valueOf((float) Math.ceil(f2)) : getHundredths(f2);
            objArr[1] = getContext().getString(R$string.memory_kilobyte);
            string = context.getString(i, objArr);
        } else if (f < 1.0737418E9f) {
            Context context2 = getContext();
            int i2 = R$string.memory_formatted;
            Object[] objArr2 = new Object[2];
            float f3 = f / 1048576.0f;
            objArr2[0] = z ? Float.valueOf((float) Math.ceil(f3)) : getHundredths(f3);
            objArr2[1] = getContext().getString(R$string.memory_megabyte);
            string = context2.getString(i2, objArr2);
        } else if (f < 1.0995116E12f) {
            Context context3 = getContext();
            int i3 = R$string.memory_formatted;
            Object[] objArr3 = new Object[2];
            float f4 = f / 1.0737418E9f;
            objArr3[0] = z ? Float.valueOf((float) Math.ceil(f4)) : getHundredths(f4);
            objArr3[1] = getContext().getString(R$string.memory_gigabyte);
            string = context3.getString(i3, objArr3);
        } else if (f < 1.1258999E15f) {
            Context context4 = getContext();
            int i4 = R$string.memory_formatted;
            Object[] objArr4 = new Object[2];
            float f5 = f / 1.0995116E12f;
            objArr4[0] = z ? Float.valueOf((float) Math.ceil(f5)) : getHundredths(f5);
            objArr4[1] = getContext().getString(R$string.memory_terabyte);
            string = context4.getString(i4, objArr4);
        } else if (f < 1.1529215E18f) {
            Context context5 = getContext();
            int i5 = R$string.memory_formatted;
            Object[] objArr5 = new Object[2];
            float f6 = f / 1.1258999E15f;
            objArr5[0] = z ? Float.valueOf((float) Math.ceil(f6)) : getHundredths(f6);
            objArr5[1] = getContext().getString(R$string.memory_petabyte);
            string = context5.getString(i5, objArr5);
        } else {
            Context context6 = getContext();
            int i6 = R$string.memory_formatted;
            Object[] objArr6 = new Object[2];
            float f7 = f / 1.1529215E18f;
            objArr6[0] = z ? Float.valueOf((float) Math.ceil(f7)) : getHundredths(f7);
            objArr6[1] = getContext().getString(R$string.memory_exabyte);
            string = context6.getString(i6, objArr6);
        }
        Intrinsics.checkNotNull(string);
        return string;
    }

    public final String getDeviceRAM() {
        return bytesToSizeUnit(getTotalMemory(), true);
    }

    public final float getTotalMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        Object systemService = getContext().getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        return (float) (Build.VERSION.SDK_INT >= 34 ? memoryInfo.advertisedMem : memoryInfo.totalMem);
    }

    public final boolean isLessThan(int i, float f) {
        if (!(f == 1024.0f)) {
            if (!(f == 1048576.0f)) {
                if (!(f == 1.0737418E9f)) {
                    if (!(f == 1.0995116E12f)) {
                        if (!(f == 1.1258999E15f)) {
                            if (f == 1.1529215E18f) {
                                if (getTotalMemory() / 1.1529215E18f < i) {
                                    return true;
                                }
                            } else if (getTotalMemory() < 1024.0f && getTotalMemory() < i) {
                                return true;
                            }
                        } else if (getTotalMemory() < 1.1529215E18f && getTotalMemory() / 1.1258999E15f < i) {
                            return true;
                        }
                    } else if (getTotalMemory() < 1.1258999E15f && getTotalMemory() / 1.0995116E12f < i) {
                        return true;
                    }
                } else if (getTotalMemory() < 1.0995116E12f && getTotalMemory() / 1.0737418E9f < i) {
                    return true;
                }
            } else if (getTotalMemory() < 1.0737418E9f && getTotalMemory() / 1048576.0f < i) {
                return true;
            }
        } else if (getTotalMemory() < 1048576.0f && getTotalMemory() < i) {
            return true;
        }
        return false;
    }
}
