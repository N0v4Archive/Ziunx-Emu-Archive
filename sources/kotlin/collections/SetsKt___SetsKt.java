package kotlin.collections;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class SetsKt___SetsKt extends SetsKt__SetsKt {
    public static Set minus(Set set, Object obj) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(set, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(set.size());
        LinkedHashSet linkedHashSet = new LinkedHashSet(mapCapacity);
        boolean z = false;
        for (Object obj2 : set) {
            boolean z2 = true;
            if (!z && Intrinsics.areEqual(obj2, obj)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    public static Set plus(Set set, Object obj) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(set, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(set.size() + 1);
        LinkedHashSet linkedHashSet = new LinkedHashSet(mapCapacity);
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }
}
