package coil.decode;

import android.net.Uri;
import coil.decode.ImageSource;

/* loaded from: classes.dex */
public final class ContentMetadata extends ImageSource.Metadata {
    private final Uri uri;

    public ContentMetadata(Uri uri) {
        this.uri = uri;
    }
}
