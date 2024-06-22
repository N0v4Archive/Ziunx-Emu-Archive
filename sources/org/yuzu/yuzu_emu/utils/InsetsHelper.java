package org.yuzu.yuzu_emu.utils;

import android.content.Context;
import android.content.res.Resources;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class InsetsHelper {
    public static final InsetsHelper INSTANCE = new InsetsHelper();

    private InsetsHelper() {
    }

    public final int getSystemGestureType(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_navBarInteractionMode", "integer", "android");
        if (identifier != 0) {
            return resources.getInteger(identifier);
        }
        return 0;
    }
}
