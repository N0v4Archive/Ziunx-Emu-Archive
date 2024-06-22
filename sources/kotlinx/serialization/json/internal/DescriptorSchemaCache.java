package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* loaded from: classes.dex */
public final class DescriptorSchemaCache {
    private final Map map = CreateMapForCacheKt.createMapForCache(16);

    /* loaded from: classes.dex */
    public static final class Key {
    }

    public final Object get(SerialDescriptor descriptor, Key key) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Map map = (Map) this.map.get(descriptor);
        Object obj = map != null ? map.get(key) : null;
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public final Object getOrPut(SerialDescriptor descriptor, Key key, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Object obj = get(descriptor, key);
        if (obj != null) {
            return obj;
        }
        Object invoke = defaultValue.invoke();
        set(descriptor, key, invoke);
        return invoke;
    }

    public final void set(SerialDescriptor descriptor, Key key, Object value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Map map = this.map;
        Object obj = map.get(descriptor);
        if (obj == null) {
            obj = CreateMapForCacheKt.createMapForCache(2);
            map.put(descriptor, obj);
        }
        ((Map) obj).put(key, value);
    }
}
