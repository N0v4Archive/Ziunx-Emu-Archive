package coil.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;

/* renamed from: coil.util.-Collections, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class Collections {
    public static final List toImmutableList(List list) {
        List emptyList;
        Object first;
        int size = list.size();
        if (size == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        if (size != 1) {
            return java.util.Collections.unmodifiableList(new ArrayList(list));
        }
        first = CollectionsKt___CollectionsKt.first(list);
        return java.util.Collections.singletonList(first);
    }

    public static final Map toImmutableMap(Map map) {
        Map emptyMap;
        Object first;
        int size = map.size();
        if (size == 0) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return emptyMap;
        }
        if (size != 1) {
            return java.util.Collections.unmodifiableMap(new LinkedHashMap(map));
        }
        first = CollectionsKt___CollectionsKt.first(map.entrySet());
        Map.Entry entry = (Map.Entry) first;
        return java.util.Collections.singletonMap(entry.getKey(), entry.getValue());
    }
}
