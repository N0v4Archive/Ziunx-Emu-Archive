package coil.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import coil.request.DefaultRequestOptions;
import coil.request.ImageRequest;
import coil.size.DisplaySizeResolver;
import coil.size.Precision;
import coil.size.ViewSizeResolver;
import coil.target.ViewTarget;
import kotlin.NoWhenBranchMatchedException;

/* renamed from: coil.util.-Requests, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class Requests {
    private static final DefaultRequestOptions DEFAULT_REQUEST_OPTIONS = new DefaultRequestOptions(null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, 32767, null);

    /* renamed from: coil.util.-Requests$WhenMappings */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Precision.values().length];
            iArr[Precision.EXACT.ordinal()] = 1;
            iArr[Precision.INEXACT.ordinal()] = 2;
            iArr[Precision.AUTOMATIC.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean getAllowInexactSize(ImageRequest imageRequest) {
        int i = WhenMappings.$EnumSwitchMapping$0[imageRequest.getPrecision().ordinal()];
        if (i == 1) {
            return false;
        }
        if (i != 2) {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            if ((imageRequest.getDefined().getSizeResolver() != null || !(imageRequest.getSizeResolver() instanceof DisplaySizeResolver)) && (!(imageRequest.getTarget() instanceof ViewTarget) || !(imageRequest.getSizeResolver() instanceof ViewSizeResolver) || !(((ViewTarget) imageRequest.getTarget()).getView() instanceof ImageView) || ((ViewTarget) imageRequest.getTarget()).getView() != ((ViewSizeResolver) imageRequest.getSizeResolver()).getView())) {
                return false;
            }
        }
        return true;
    }

    public static final DefaultRequestOptions getDEFAULT_REQUEST_OPTIONS() {
        return DEFAULT_REQUEST_OPTIONS;
    }

    public static final Drawable getDrawableCompat(ImageRequest imageRequest, Drawable drawable, Integer num, Drawable drawable2) {
        if (drawable != null) {
            return drawable;
        }
        if (num == null) {
            return drawable2;
        }
        if (num.intValue() == 0) {
            return null;
        }
        return Contexts.getDrawableCompat(imageRequest.getContext(), num.intValue());
    }
}
