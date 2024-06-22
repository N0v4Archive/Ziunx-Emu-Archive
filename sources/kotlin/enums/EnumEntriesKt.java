package kotlin.enums;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class EnumEntriesKt {
    public static final EnumEntries enumEntries(Enum[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        return new EnumEntriesList(entries);
    }
}
