package kotlin;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class LazyThreadSafetyMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LazyThreadSafetyMode[] $VALUES;
    public static final LazyThreadSafetyMode SYNCHRONIZED = new LazyThreadSafetyMode("SYNCHRONIZED", 0);
    public static final LazyThreadSafetyMode PUBLICATION = new LazyThreadSafetyMode("PUBLICATION", 1);
    public static final LazyThreadSafetyMode NONE = new LazyThreadSafetyMode("NONE", 2);

    private static final /* synthetic */ LazyThreadSafetyMode[] $values() {
        return new LazyThreadSafetyMode[]{SYNCHRONIZED, PUBLICATION, NONE};
    }

    static {
        LazyThreadSafetyMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private LazyThreadSafetyMode(String str, int i) {
    }

    public static LazyThreadSafetyMode valueOf(String str) {
        return (LazyThreadSafetyMode) Enum.valueOf(LazyThreadSafetyMode.class, str);
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[]) $VALUES.clone();
    }
}
