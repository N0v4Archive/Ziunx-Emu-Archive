package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;

/* loaded from: classes.dex */
public interface KSerializer extends SerializationStrategy, DeserializationStrategy {
    @Override // kotlinx.serialization.DeserializationStrategy
    SerialDescriptor getDescriptor();
}
