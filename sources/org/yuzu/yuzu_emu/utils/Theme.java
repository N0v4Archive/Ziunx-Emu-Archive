package org.yuzu.yuzu_emu.utils;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class Theme {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Theme[] $VALUES;
    public static final Companion Companion;
    public static final Theme Default = new Theme("Default", 0, 0);
    public static final Theme MaterialYou = new Theme("MaterialYou", 1, 1);

    /* renamed from: int, reason: not valid java name */
    private final int f13int;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Theme from(int i) {
            Object obj;
            Iterator<E> it = Theme.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Theme) obj).getInt() == i) {
                    break;
                }
            }
            Theme theme = (Theme) obj;
            return theme == null ? Theme.Default : theme;
        }
    }

    private static final /* synthetic */ Theme[] $values() {
        return new Theme[]{Default, MaterialYou};
    }

    static {
        Theme[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private Theme(String str, int i, int i2) {
        this.f13int = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static Theme valueOf(String str) {
        return (Theme) Enum.valueOf(Theme.class, str);
    }

    public static Theme[] values() {
        return (Theme[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f13int;
    }
}
