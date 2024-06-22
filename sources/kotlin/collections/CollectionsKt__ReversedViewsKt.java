package kotlin.collections;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    public static List asReversed(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedListReadOnly(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        int lastIndex;
        int lastIndex2;
        int lastIndex3;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        if (new IntRange(0, lastIndex).contains(i)) {
            lastIndex3 = CollectionsKt__CollectionsKt.getLastIndex(list);
            return lastIndex3 - i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Element index ");
        sb.append(i);
        sb.append(" must be in range [");
        lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
        sb.append(new IntRange(0, lastIndex2));
        sb.append("].");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        int lastIndex;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        return lastIndex - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        if (new IntRange(0, list.size()).contains(i)) {
            return list.size() - i;
        }
        throw new IndexOutOfBoundsException("Position index " + i + " must be in range [" + new IntRange(0, list.size()) + "].");
    }
}
