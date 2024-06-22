package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* loaded from: classes.dex */
public abstract class JsonSchemaCacheKt {
    public static final DescriptorSchemaCache getSchemaCache(Json json) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        return json.get_schemaCache$kotlinx_serialization_json();
    }
}
