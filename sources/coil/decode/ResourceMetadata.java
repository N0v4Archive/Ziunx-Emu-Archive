package coil.decode;

import coil.decode.ImageSource;

/* loaded from: classes.dex */
public final class ResourceMetadata extends ImageSource.Metadata {
    private final int density;
    private final String packageName;
    private final int resId;

    public ResourceMetadata(String str, int i, int i2) {
        this.packageName = str;
        this.resId = i;
        this.density = i2;
    }

    public final int getDensity() {
        return this.density;
    }
}
