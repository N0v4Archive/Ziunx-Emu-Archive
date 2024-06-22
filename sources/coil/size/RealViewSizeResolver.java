package coil.size;

import android.view.View;
import coil.size.ViewSizeResolver;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class RealViewSizeResolver implements ViewSizeResolver {
    private final boolean subtractPadding;
    private final View view;

    public RealViewSizeResolver(View view, boolean z) {
        this.view = view;
        this.subtractPadding = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RealViewSizeResolver) {
            RealViewSizeResolver realViewSizeResolver = (RealViewSizeResolver) obj;
            if (Intrinsics.areEqual(getView(), realViewSizeResolver.getView()) && getSubtractPadding() == realViewSizeResolver.getSubtractPadding()) {
                return true;
            }
        }
        return false;
    }

    @Override // coil.size.ViewSizeResolver
    public boolean getSubtractPadding() {
        return this.subtractPadding;
    }

    @Override // coil.size.ViewSizeResolver
    public View getView() {
        return this.view;
    }

    public int hashCode() {
        return (getView().hashCode() * 31) + Boolean.hashCode(getSubtractPadding());
    }

    @Override // coil.size.SizeResolver
    public Object size(Continuation continuation) {
        return ViewSizeResolver.DefaultImpls.size(this, continuation);
    }
}
