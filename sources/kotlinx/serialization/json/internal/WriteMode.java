package kotlinx.serialization.json.internal;

/* loaded from: classes.dex */
public enum WriteMode {
    OBJ('{', '}'),
    LIST('[', ']'),
    MAP('{', '}'),
    POLY_OBJ('[', ']');

    public final char begin;
    public final char end;

    WriteMode(char c, char c2) {
        this.begin = c;
        this.end = c2;
    }
}
