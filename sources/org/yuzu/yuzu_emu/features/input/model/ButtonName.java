package org.yuzu.yuzu_emu.features.input.model;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class ButtonName {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ButtonName[] $VALUES;
    public static final Companion Companion;

    /* renamed from: int, reason: not valid java name */
    private final int f3int;
    public static final ButtonName Invalid = new ButtonName("Invalid", 0, 1);
    public static final ButtonName Engine = new ButtonName("Engine", 1, 2);
    public static final ButtonName Value = new ButtonName("Value", 2, 3);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ButtonName from(int i) {
            Object obj;
            Iterator<E> it = ButtonName.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((ButtonName) obj).getInt() == i) {
                    break;
                }
            }
            ButtonName buttonName = (ButtonName) obj;
            return buttonName == null ? ButtonName.Invalid : buttonName;
        }
    }

    private static final /* synthetic */ ButtonName[] $values() {
        return new ButtonName[]{Invalid, Engine, Value};
    }

    static {
        ButtonName[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private ButtonName(String str, int i, int i2) {
        this.f3int = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static ButtonName valueOf(String str) {
        return (ButtonName) Enum.valueOf(ButtonName.class, str);
    }

    public static ButtonName[] values() {
        return (ButtonName[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f3int;
    }
}
