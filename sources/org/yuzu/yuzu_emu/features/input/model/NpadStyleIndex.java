package org.yuzu.yuzu_emu.features.input.model;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.yuzu.yuzu_emu.R$string;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class NpadStyleIndex {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ NpadStyleIndex[] $VALUES;
    public static final Companion Companion;
    public static final NpadStyleIndex HandheldNES;
    public static final NpadStyleIndex N64;
    public static final NpadStyleIndex Pokeball;
    public static final NpadStyleIndex SNES;
    public static final NpadStyleIndex SegaGenesis;
    public static final NpadStyleIndex System;
    public static final NpadStyleIndex SystemExt;

    /* renamed from: int, reason: not valid java name */
    private final int f7int;
    private final int nameId;
    public static final NpadStyleIndex None = new NpadStyleIndex("None", 0, 0, 0, 2, null);
    public static final NpadStyleIndex Fullkey = new NpadStyleIndex("Fullkey", 1, 3, R$string.pro_controller);
    public static final NpadStyleIndex Handheld = new NpadStyleIndex("Handheld", 2, 4, R$string.handheld);
    public static final NpadStyleIndex JoyconDual = new NpadStyleIndex("JoyconDual", 4, 5, R$string.dual_joycons);
    public static final NpadStyleIndex JoyconLeft = new NpadStyleIndex("JoyconLeft", 5, 6, R$string.left_joycon);
    public static final NpadStyleIndex JoyconRight = new NpadStyleIndex("JoyconRight", 6, 7, R$string.right_joycon);
    public static final NpadStyleIndex GameCube = new NpadStyleIndex("GameCube", 7, 8, R$string.gamecube_controller);
    public static final NpadStyleIndex NES = new NpadStyleIndex("NES", 9, 10, 0, 2, null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NpadStyleIndex from(int i) {
            Object obj;
            Iterator<E> it = NpadStyleIndex.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((NpadStyleIndex) obj).getInt() == i) {
                    break;
                }
            }
            NpadStyleIndex npadStyleIndex = (NpadStyleIndex) obj;
            return npadStyleIndex == null ? NpadStyleIndex.None : npadStyleIndex;
        }
    }

    private static final /* synthetic */ NpadStyleIndex[] $values() {
        return new NpadStyleIndex[]{None, Fullkey, Handheld, HandheldNES, JoyconDual, JoyconLeft, JoyconRight, GameCube, Pokeball, NES, SNES, N64, SegaGenesis, SystemExt, System};
    }

    static {
        int i = 0;
        int i2 = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        HandheldNES = new NpadStyleIndex("HandheldNES", 3, 4, i, i2, defaultConstructorMarker);
        Pokeball = new NpadStyleIndex("Pokeball", 8, 9, i, i2, defaultConstructorMarker);
        int i3 = 0;
        int i4 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        SNES = new NpadStyleIndex("SNES", 10, 12, i3, i4, defaultConstructorMarker2);
        int i5 = 0;
        int i6 = 2;
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        N64 = new NpadStyleIndex("N64", 11, 13, i5, i6, defaultConstructorMarker3);
        SegaGenesis = new NpadStyleIndex("SegaGenesis", 12, 14, i3, i4, defaultConstructorMarker2);
        SystemExt = new NpadStyleIndex("SystemExt", 13, 32, i5, i6, defaultConstructorMarker3);
        System = new NpadStyleIndex("System", 14, 33, i3, i4, defaultConstructorMarker2);
        NpadStyleIndex[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private NpadStyleIndex(String str, int i, int i2, int i3) {
        this.f7int = i2;
        this.nameId = i3;
    }

    /* synthetic */ NpadStyleIndex(String str, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i4 & 2) != 0 ? 0 : i3);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static NpadStyleIndex valueOf(String str) {
        return (NpadStyleIndex) Enum.valueOf(NpadStyleIndex.class, str);
    }

    public static NpadStyleIndex[] values() {
        return (NpadStyleIndex[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f7int;
    }

    public final int getNameId() {
        return this.nameId;
    }
}
