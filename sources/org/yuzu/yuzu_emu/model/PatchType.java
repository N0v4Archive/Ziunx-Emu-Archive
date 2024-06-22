package org.yuzu.yuzu_emu.model;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class PatchType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PatchType[] $VALUES;
    public static final Companion Companion;

    /* renamed from: int, reason: not valid java name */
    private final int f12int;
    public static final PatchType Update = new PatchType("Update", 0, 0);
    public static final PatchType DLC = new PatchType("DLC", 1, 1);
    public static final PatchType Mod = new PatchType("Mod", 2, 2);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PatchType from(int i) {
            Object obj;
            Iterator<E> it = PatchType.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((PatchType) obj).getInt() == i) {
                    break;
                }
            }
            PatchType patchType = (PatchType) obj;
            return patchType == null ? PatchType.Update : patchType;
        }
    }

    private static final /* synthetic */ PatchType[] $values() {
        return new PatchType[]{Update, DLC, Mod};
    }

    static {
        PatchType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private PatchType(String str, int i, int i2) {
        this.f12int = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static PatchType valueOf(String str) {
        return (PatchType) Enum.valueOf(PatchType.class, str);
    }

    public static PatchType[] values() {
        return (PatchType[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f12int;
    }
}
