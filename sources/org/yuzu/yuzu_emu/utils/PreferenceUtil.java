package org.yuzu.yuzu_emu.utils;

import android.content.SharedPreferences;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class PreferenceUtil {
    public static final PreferenceUtil INSTANCE = new PreferenceUtil();

    private PreferenceUtil() {
    }

    public final void deletePreference(SharedPreferences sharedPreferences, String key) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        sharedPreferences.edit().remove(key).apply();
    }
}
