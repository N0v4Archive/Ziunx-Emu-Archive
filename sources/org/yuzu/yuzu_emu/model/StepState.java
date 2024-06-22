package org.yuzu.yuzu_emu.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class StepState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ StepState[] $VALUES;
    public static final StepState COMPLETE = new StepState("COMPLETE", 0);
    public static final StepState INCOMPLETE = new StepState("INCOMPLETE", 1);
    public static final StepState UNDEFINED = new StepState("UNDEFINED", 2);

    private static final /* synthetic */ StepState[] $values() {
        return new StepState[]{COMPLETE, INCOMPLETE, UNDEFINED};
    }

    static {
        StepState[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private StepState(String str, int i) {
    }

    public static StepState valueOf(String str) {
        return (StepState) Enum.valueOf(StepState.class, str);
    }

    public static StepState[] values() {
        return (StepState[]) $VALUES.clone();
    }
}
