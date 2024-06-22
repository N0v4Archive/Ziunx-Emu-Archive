package kotlinx.serialization;

/* loaded from: classes.dex */
public interface StringFormat extends SerialFormat {
    Object decodeFromString(DeserializationStrategy deserializationStrategy, String str);

    String encodeToString(SerializationStrategy serializationStrategy, Object obj);
}
