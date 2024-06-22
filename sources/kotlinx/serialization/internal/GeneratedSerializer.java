package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;

/* loaded from: classes.dex */
public interface GeneratedSerializer extends KSerializer {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static KSerializer[] typeParametersSerializers(GeneratedSerializer generatedSerializer) {
            return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
        }
    }

    KSerializer[] childSerializers();

    KSerializer[] typeParametersSerializers();
}
