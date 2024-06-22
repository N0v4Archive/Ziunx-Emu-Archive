package kotlin.io;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class FileWalkDirection {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FileWalkDirection[] $VALUES;
    public static final FileWalkDirection TOP_DOWN = new FileWalkDirection("TOP_DOWN", 0);
    public static final FileWalkDirection BOTTOM_UP = new FileWalkDirection("BOTTOM_UP", 1);

    private static final /* synthetic */ FileWalkDirection[] $values() {
        return new FileWalkDirection[]{TOP_DOWN, BOTTOM_UP};
    }

    static {
        FileWalkDirection[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private FileWalkDirection(String str, int i) {
    }

    public static FileWalkDirection valueOf(String str) {
        return (FileWalkDirection) Enum.valueOf(FileWalkDirection.class, str);
    }

    public static FileWalkDirection[] values() {
        return (FileWalkDirection[]) $VALUES.clone();
    }
}
