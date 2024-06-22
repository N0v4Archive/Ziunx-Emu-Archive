package org.yuzu.yuzu_emu.features.input.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class InputType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InputType[] $VALUES;

    /* renamed from: int, reason: not valid java name */
    private final int f4int;
    public static final InputType None = new InputType("None", 0, 0);
    public static final InputType Button = new InputType("Button", 1, 1);
    public static final InputType Stick = new InputType("Stick", 2, 2);
    public static final InputType Motion = new InputType("Motion", 3, 3);
    public static final InputType Touch = new InputType("Touch", 4, 4);

    private static final /* synthetic */ InputType[] $values() {
        return new InputType[]{None, Button, Stick, Motion, Touch};
    }

    static {
        InputType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private InputType(String str, int i, int i2) {
        this.f4int = i2;
    }

    public static InputType valueOf(String str) {
        return (InputType) Enum.valueOf(InputType.class, str);
    }

    public static InputType[] values() {
        return (InputType[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f4int;
    }
}
