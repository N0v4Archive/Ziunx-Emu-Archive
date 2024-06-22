package kotlinx.serialization;

import kotlinx.serialization.encoding.Encoder;

/* loaded from: classes.dex */
public interface SerializationStrategy {
    void serialize(Encoder encoder, Object obj);
}
