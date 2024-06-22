package coil.decode;

import java.io.Closeable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okio.BufferedSource;

/* loaded from: classes.dex */
public abstract class ImageSource implements Closeable {

    /* loaded from: classes.dex */
    public static abstract class Metadata {
    }

    private ImageSource() {
    }

    public /* synthetic */ ImageSource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Metadata getMetadata();

    public abstract BufferedSource source();
}
