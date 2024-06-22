package kotlinx.serialization;

/* loaded from: classes.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(int i) {
        this("An unknown field for index " + i);
    }

    public UnknownFieldException(String str) {
        super(str);
    }
}
