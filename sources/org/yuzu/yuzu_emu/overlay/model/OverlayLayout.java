package org.yuzu.yuzu_emu.overlay.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class OverlayLayout {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OverlayLayout[] $VALUES;
    private final String id;
    public static final OverlayLayout Landscape = new OverlayLayout("Landscape", 0, "Landscape");
    public static final OverlayLayout Portrait = new OverlayLayout("Portrait", 1, "Portrait");
    public static final OverlayLayout Foldable = new OverlayLayout("Foldable", 2, "Foldable");

    private static final /* synthetic */ OverlayLayout[] $values() {
        return new OverlayLayout[]{Landscape, Portrait, Foldable};
    }

    static {
        OverlayLayout[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private OverlayLayout(String str, int i, String str2) {
        this.id = str2;
    }

    public static OverlayLayout valueOf(String str) {
        return (OverlayLayout) Enum.valueOf(OverlayLayout.class, str);
    }

    public static OverlayLayout[] values() {
        return (OverlayLayout[]) $VALUES.clone();
    }
}
