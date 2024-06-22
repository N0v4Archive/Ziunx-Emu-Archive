package kotlin.enums;

import java.io.Serializable;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class EnumEntriesList extends AbstractList implements EnumEntries, Serializable {
    private final Enum[] entries;

    public EnumEntriesList(Enum[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.entries = entries;
    }

    public boolean contains(Enum element) {
        Object orNull;
        Intrinsics.checkNotNullParameter(element, "element");
        orNull = ArraysKt___ArraysKt.getOrNull(this.entries, element.ordinal());
        return ((Enum) orNull) == element;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return contains((Enum) obj);
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Enum get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.entries.length);
        return this.entries[i];
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.entries.length;
    }

    public int indexOf(Enum element) {
        Object orNull;
        Intrinsics.checkNotNullParameter(element, "element");
        int ordinal = element.ordinal();
        orNull = ArraysKt___ArraysKt.getOrNull(this.entries, ordinal);
        if (((Enum) orNull) == element) {
            return ordinal;
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return indexOf((Enum) obj);
        }
        return -1;
    }

    public int lastIndexOf(Enum element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return indexOf((Object) element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return lastIndexOf((Enum) obj);
        }
        return -1;
    }
}
