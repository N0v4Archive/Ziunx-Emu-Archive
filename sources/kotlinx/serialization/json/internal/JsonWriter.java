package kotlinx.serialization.json.internal;

/* loaded from: classes.dex */
public interface JsonWriter {
    void write(String str);

    void writeChar(char c);

    void writeQuoted(String str);
}
