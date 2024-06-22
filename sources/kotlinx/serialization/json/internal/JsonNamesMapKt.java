package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* loaded from: classes.dex */
public abstract class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key JsonDeserializationNamesKey = new DescriptorSchemaCache.Key();
    private static final DescriptorSchemaCache.Key JsonSerializationNamesKey = new DescriptorSchemaCache.Key();

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map buildDeserializationNamesMap(SerialDescriptor serialDescriptor, Json json) {
        Map emptyMap;
        Object singleOrNull;
        String[] names;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        namingStrategy(serialDescriptor, json);
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            List elementAnnotations = serialDescriptor.getElementAnnotations(i);
            ArrayList arrayList = new ArrayList();
            for (Object obj : elementAnnotations) {
                if (obj instanceof JsonNames) {
                    arrayList.add(obj);
                }
            }
            singleOrNull = CollectionsKt___CollectionsKt.singleOrNull(arrayList);
            JsonNames jsonNames = (JsonNames) singleOrNull;
            if (jsonNames != null && (names = jsonNames.names()) != null) {
                for (String str : names) {
                    buildDeserializationNamesMap$putOrThrow(linkedHashMap, serialDescriptor, str, i);
                }
            }
        }
        if (!linkedHashMap.isEmpty()) {
            return linkedHashMap;
        }
        emptyMap = MapsKt__MapsKt.emptyMap();
        return emptyMap;
    }

    private static final void buildDeserializationNamesMap$putOrThrow(Map map, SerialDescriptor serialDescriptor, String str, int i) {
        Object value;
        if (!map.containsKey(str)) {
            map.put(str, Integer.valueOf(i));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The suggested name '");
        sb.append(str);
        sb.append("' for property ");
        sb.append(serialDescriptor.getElementName(i));
        sb.append(" is already one of the names for property ");
        value = MapsKt__MapsKt.getValue(map, str);
        sb.append(serialDescriptor.getElementName(((Number) value).intValue()));
        sb.append(" in ");
        sb.append(serialDescriptor);
        throw new JsonException(sb.toString());
    }

    public static final Map deserializationNamesMap(final Json json, final SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return (Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(descriptor, JsonDeserializationNamesKey, new Function0() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$deserializationNamesMap$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map invoke() {
                Map buildDeserializationNamesMap;
                buildDeserializationNamesMap = JsonNamesMapKt.buildDeserializationNamesMap(SerialDescriptor.this, json);
                return buildDeserializationNamesMap;
            }
        });
    }

    public static final String getJsonElementName(SerialDescriptor serialDescriptor, Json json, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        namingStrategy(serialDescriptor, json);
        return serialDescriptor.getElementName(i);
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        namingStrategy(serialDescriptor, json);
        int elementIndex = serialDescriptor.getElementIndex(name);
        return (elementIndex == -3 && json.getConfiguration().getUseAlternativeNames()) ? getJsonNameIndex$getJsonNameIndexSlowPath(json, serialDescriptor, name) : elementIndex;
    }

    private static final int getJsonNameIndex$getJsonNameIndexSlowPath(Json json, SerialDescriptor serialDescriptor, String str) {
        Integer num = (Integer) deserializationNamesMap(json, serialDescriptor).get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static final JsonNamingStrategy namingStrategy(SerialDescriptor serialDescriptor, Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        if (!Intrinsics.areEqual(serialDescriptor.getKind(), StructureKind.CLASS.INSTANCE)) {
            return null;
        }
        json.getConfiguration().getNamingStrategy();
        return null;
    }
}
