package coil.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class ImageViewTarget extends GenericViewTarget<ImageView> {
    private final ImageView view;

    public ImageViewTarget(ImageView imageView) {
        this.view = imageView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImageViewTarget) && Intrinsics.areEqual(getView(), ((ImageViewTarget) obj).getView());
    }

    @Override // coil.target.GenericViewTarget
    public Drawable getDrawable() {
        return getView().getDrawable();
    }

    @Override // coil.target.ViewTarget
    public ImageView getView() {
        return this.view;
    }

    public int hashCode() {
        return getView().hashCode();
    }

    @Override // coil.target.GenericViewTarget
    public void setDrawable(Drawable drawable) {
        getView().setImageDrawable(drawable);
    }
}
