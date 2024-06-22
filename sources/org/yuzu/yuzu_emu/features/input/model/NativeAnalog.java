package org.yuzu.yuzu_emu.features.input.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class NativeAnalog {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ NativeAnalog[] $VALUES;
    public static final Companion Companion;
    public static final NativeAnalog LStick = new NativeAnalog("LStick", 0, 0);
    public static final NativeAnalog RStick = new NativeAnalog("RStick", 1, 1);

    /* renamed from: int, reason: not valid java name */
    private final int f5int;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private static final /* synthetic */ NativeAnalog[] $values() {
        return new NativeAnalog[]{LStick, RStick};
    }

    static {
        NativeAnalog[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private NativeAnalog(String str, int i, int i2) {
        this.f5int = i2;
    }

    public static NativeAnalog valueOf(String str) {
        return (NativeAnalog) Enum.valueOf(NativeAnalog.class, str);
    }

    public static NativeAnalog[] values() {
        return (NativeAnalog[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f5int;
    }
}
