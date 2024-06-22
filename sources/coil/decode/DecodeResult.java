package coil.decode;

import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class DecodeResult {
    private final Drawable drawable;
    private final boolean isSampled;

    public DecodeResult(Drawable drawable, boolean z) {
        this.drawable = drawable;
        this.isSampled = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DecodeResult) {
            DecodeResult decodeResult = (DecodeResult) obj;
            if (Intrinsics.areEqual(this.drawable, decodeResult.drawable) && this.isSampled == decodeResult.isSampled) {
                return true;
            }
        }
        return false;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public int hashCode() {
        return (this.drawable.hashCode() * 31) + Boolean.hashCode(this.isSampled);
    }

    public final boolean isSampled() {
        return this.isSampled;
    }
}
