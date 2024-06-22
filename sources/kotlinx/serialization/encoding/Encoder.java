package kotlinx.serialization.encoding;

import kotlinx.serialization.descriptors.SerialDescriptor;

/* loaded from: classes.dex */
public interface Encoder {
    CompositeEncoder beginStructure(SerialDescriptor serialDescriptor);

    void encodeBoolean(boolean z);

    void encodeString(String str);
}
