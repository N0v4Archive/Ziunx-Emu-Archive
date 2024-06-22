package coil.request;

import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public abstract class ImageResult {
    private ImageResult() {
    }

    public /* synthetic */ ImageResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Drawable getDrawable();

    public abstract ImageRequest getRequest();
}
