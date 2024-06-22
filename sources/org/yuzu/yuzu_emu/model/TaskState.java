package org.yuzu.yuzu_emu.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class TaskState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TaskState[] $VALUES;
    public static final TaskState Completed = new TaskState("Completed", 0);
    public static final TaskState Failed = new TaskState("Failed", 1);
    public static final TaskState Cancelled = new TaskState("Cancelled", 2);

    private static final /* synthetic */ TaskState[] $values() {
        return new TaskState[]{Completed, Failed, Cancelled};
    }

    static {
        TaskState[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private TaskState(String str, int i) {
    }

    public static TaskState valueOf(String str) {
        return (TaskState) Enum.valueOf(TaskState.class, str);
    }

    public static TaskState[] values() {
        return (TaskState[]) $VALUES.clone();
    }
}
