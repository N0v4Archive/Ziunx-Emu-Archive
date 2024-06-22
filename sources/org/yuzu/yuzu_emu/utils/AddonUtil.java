package org.yuzu.yuzu_emu.utils;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* loaded from: classes.dex */
public final class AddonUtil {
    public static final AddonUtil INSTANCE = new AddonUtil();
    private static final List validAddonDirectories;

    static {
        List listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"cheats", "exefs", "romfs"});
        validAddonDirectories = listOf;
    }

    private AddonUtil() {
    }

    public final List getValidAddonDirectories() {
        return validAddonDirectories;
    }
}
