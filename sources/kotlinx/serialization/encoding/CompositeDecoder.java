package kotlinx.serialization.encoding;

import kotlinx.serialization.descriptors.SerialDescriptor;

/* loaded from: classes.dex */
public interface CompositeDecoder {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean decodeSequentially(CompositeDecoder compositeDecoder) {
            return false;
        }
    }

    boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i);

    int decodeElementIndex(SerialDescriptor serialDescriptor);

    boolean decodeSequentially();

    String decodeStringElement(SerialDescriptor serialDescriptor, int i);

    void endStructure(SerialDescriptor serialDescriptor);
}
