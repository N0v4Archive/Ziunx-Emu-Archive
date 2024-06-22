package kotlinx.serialization.encoding;

import kotlinx.serialization.descriptors.SerialDescriptor;

/* loaded from: classes.dex */
public interface Decoder {
    CompositeDecoder beginStructure(SerialDescriptor serialDescriptor);

    boolean decodeBoolean();

    String decodeString();
}
