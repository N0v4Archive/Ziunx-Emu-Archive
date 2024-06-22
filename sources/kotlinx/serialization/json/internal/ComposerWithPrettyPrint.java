package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* loaded from: classes.dex */
public final class ComposerWithPrettyPrint extends Composer {
    private final Json json;
    private int level;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerWithPrettyPrint(JsonWriter writer, Json json) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void indent() {
        setWritingFirst(true);
        this.level++;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void nextItem() {
        setWritingFirst(false);
        print("\n");
        int i = this.level;
        for (int i2 = 0; i2 < i; i2++) {
            print(this.json.getConfiguration().getPrettyPrintIndent());
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void space() {
        print(' ');
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void unIndent() {
        this.level--;
    }
}
