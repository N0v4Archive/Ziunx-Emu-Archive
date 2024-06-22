package coil.size;

import android.content.Context;
import android.util.DisplayMetrics;
import coil.size.Dimension;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class DisplaySizeResolver implements SizeResolver {
    private final Context context;

    public DisplaySizeResolver(Context context) {
        this.context = context;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DisplaySizeResolver) && Intrinsics.areEqual(this.context, ((DisplaySizeResolver) obj).context);
    }

    public int hashCode() {
        return this.context.hashCode();
    }

    @Override // coil.size.SizeResolver
    public Object size(Continuation continuation) {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        Dimension.Pixels Dimension = Dimensions.Dimension(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels));
        return new Size(Dimension, Dimension);
    }
}
