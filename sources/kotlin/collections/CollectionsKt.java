package kotlin.collections;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class CollectionsKt extends CollectionsKt___CollectionsKt {
    public static /* bridge */ /* synthetic */ int collectionSizeOrDefault(Iterable iterable, int i) {
        return CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, i);
    }

    public static /* bridge */ /* synthetic */ int getLastIndex(List list) {
        return CollectionsKt__CollectionsKt.getLastIndex(list);
    }

    public static /* bridge */ /* synthetic */ Set intersect(Iterable iterable, Iterable iterable2) {
        return CollectionsKt___CollectionsKt.intersect(iterable, iterable2);
    }

    public static /* bridge */ /* synthetic */ Appendable joinTo$default(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        Appendable joinTo;
        joinTo = CollectionsKt___CollectionsKt.joinTo(iterable, appendable, (r14 & 2) != 0 ? ", " : charSequence, (r14 & 4) != 0 ? "" : charSequence2, (r14 & 8) == 0 ? charSequence3 : "", (r14 & 16) != 0 ? -1 : i, (r14 & 32) != 0 ? "..." : charSequence4, (r14 & 64) != 0 ? null : function1);
        return joinTo;
    }

    public static /* bridge */ /* synthetic */ Comparable minOrNull(Iterable iterable) {
        return CollectionsKt___CollectionsKt.minOrNull(iterable);
    }

    public static /* bridge */ /* synthetic */ void throwIndexOverflow() {
        CollectionsKt__CollectionsKt.throwIndexOverflow();
    }

    public static /* bridge */ /* synthetic */ int[] toIntArray(Collection collection) {
        return CollectionsKt___CollectionsKt.toIntArray(collection);
    }

    public static /* bridge */ /* synthetic */ List toList(Iterable iterable) {
        return CollectionsKt___CollectionsKt.toList(iterable);
    }
}
