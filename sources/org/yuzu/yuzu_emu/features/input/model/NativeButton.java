package org.yuzu.yuzu_emu.features.input.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class NativeButton {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ NativeButton[] $VALUES;
    public static final Companion Companion;

    /* renamed from: int, reason: not valid java name */
    private final int f6int;
    public static final NativeButton A = new NativeButton("A", 0, 0);
    public static final NativeButton B = new NativeButton("B", 1, 1);
    public static final NativeButton X = new NativeButton("X", 2, 2);
    public static final NativeButton Y = new NativeButton("Y", 3, 3);
    public static final NativeButton LStick = new NativeButton("LStick", 4, 4);
    public static final NativeButton RStick = new NativeButton("RStick", 5, 5);
    public static final NativeButton L = new NativeButton("L", 6, 6);
    public static final NativeButton R = new NativeButton("R", 7, 7);
    public static final NativeButton ZL = new NativeButton("ZL", 8, 8);
    public static final NativeButton ZR = new NativeButton("ZR", 9, 9);
    public static final NativeButton Plus = new NativeButton("Plus", 10, 10);
    public static final NativeButton Minus = new NativeButton("Minus", 11, 11);
    public static final NativeButton DLeft = new NativeButton("DLeft", 12, 12);
    public static final NativeButton DUp = new NativeButton("DUp", 13, 13);
    public static final NativeButton DRight = new NativeButton("DRight", 14, 14);
    public static final NativeButton DDown = new NativeButton("DDown", 15, 15);
    public static final NativeButton SLLeft = new NativeButton("SLLeft", 16, 16);
    public static final NativeButton SRLeft = new NativeButton("SRLeft", 17, 17);
    public static final NativeButton Home = new NativeButton("Home", 18, 18);
    public static final NativeButton Capture = new NativeButton("Capture", 19, 19);
    public static final NativeButton SLRight = new NativeButton("SLRight", 20, 20);
    public static final NativeButton SRRight = new NativeButton("SRRight", 21, 21);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private static final /* synthetic */ NativeButton[] $values() {
        return new NativeButton[]{A, B, X, Y, LStick, RStick, L, R, ZL, ZR, Plus, Minus, DLeft, DUp, DRight, DDown, SLLeft, SRLeft, Home, Capture, SLRight, SRRight};
    }

    static {
        NativeButton[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private NativeButton(String str, int i, int i2) {
        this.f6int = i2;
    }

    public static NativeButton valueOf(String str) {
        return (NativeButton) Enum.valueOf(NativeButton.class, str);
    }

    public static NativeButton[] values() {
        return (NativeButton[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f6int;
    }
}
