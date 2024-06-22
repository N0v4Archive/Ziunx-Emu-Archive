package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
abstract class MapsKt__MapWithDefaultKt {
    public static final Object getOrImplicitDefaultNullable(Map map, Object obj) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }
}
