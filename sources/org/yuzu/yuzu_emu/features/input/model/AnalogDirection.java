package org.yuzu.yuzu_emu.features.input.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class AnalogDirection {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AnalogDirection[] $VALUES;

    /* renamed from: int, reason: not valid java name */
    private final int f2int;
    private final String param;
    public static final AnalogDirection Up = new AnalogDirection("Up", 0, 0, "up");
    public static final AnalogDirection Down = new AnalogDirection("Down", 1, 1, "down");
    public static final AnalogDirection Left = new AnalogDirection("Left", 2, 2, "left");
    public static final AnalogDirection Right = new AnalogDirection("Right", 3, 3, "right");

    private static final /* synthetic */ AnalogDirection[] $values() {
        return new AnalogDirection[]{Up, Down, Left, Right};
    }

    static {
        AnalogDirection[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private AnalogDirection(String str, int i, int i2, String str2) {
        this.f2int = i2;
        this.param = str2;
    }

    public static AnalogDirection valueOf(String str) {
        return (AnalogDirection) Enum.valueOf(AnalogDirection.class, str);
    }

    public static AnalogDirection[] values() {
        return (AnalogDirection[]) $VALUES.clone();
    }

    public final String getParam() {
        return this.param;
    }
}
