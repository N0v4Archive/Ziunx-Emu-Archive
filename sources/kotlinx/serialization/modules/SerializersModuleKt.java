package kotlinx.serialization.modules;

import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;

/* loaded from: classes.dex */
public abstract class SerializersModuleKt {
    private static final SerializersModule EmptySerializersModule;

    static {
        Map emptyMap;
        Map emptyMap2;
        Map emptyMap3;
        Map emptyMap4;
        Map emptyMap5;
        emptyMap = MapsKt__MapsKt.emptyMap();
        emptyMap2 = MapsKt__MapsKt.emptyMap();
        emptyMap3 = MapsKt__MapsKt.emptyMap();
        emptyMap4 = MapsKt__MapsKt.emptyMap();
        emptyMap5 = MapsKt__MapsKt.emptyMap();
        EmptySerializersModule = new SerialModuleImpl(emptyMap, emptyMap2, emptyMap3, emptyMap4, emptyMap5);
    }

    public static final SerializersModule getEmptySerializersModule() {
        return EmptySerializersModule;
    }
}
