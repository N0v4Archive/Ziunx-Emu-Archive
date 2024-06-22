package coil.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import coil.ImageLoader;
import coil.size.Dimension;
import coil.size.Scale;
import coil.size.Size;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.util.Bitmaps;
import coil.util.HardwareBitmapService;
import coil.util.HardwareBitmaps;
import coil.util.Logger;
import coil.util.Requests;
import coil.util.SystemCallbacks;
import coil.util.Utils;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* loaded from: classes.dex */
public final class RequestService {
    private final HardwareBitmapService hardwareBitmapService;
    private final ImageLoader imageLoader;
    private final SystemCallbacks systemCallbacks;

    public RequestService(ImageLoader imageLoader, SystemCallbacks systemCallbacks, Logger logger) {
        this.imageLoader = imageLoader;
        this.systemCallbacks = systemCallbacks;
        this.hardwareBitmapService = HardwareBitmaps.HardwareBitmapService(logger);
    }

    private final boolean isConfigValidForHardwareAllocation(ImageRequest imageRequest, Size size) {
        return isConfigValidForHardware(imageRequest, imageRequest.getBitmapConfig()) && this.hardwareBitmapService.allowHardwareMainThread(size);
    }

    private final boolean isConfigValidForTransformations(ImageRequest imageRequest) {
        boolean contains;
        if (!imageRequest.getTransformations().isEmpty()) {
            contains = ArraysKt___ArraysKt.contains(Utils.getVALID_TRANSFORMATION_CONFIGS(), imageRequest.getBitmapConfig());
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    public final boolean allowHardwareWorkerThread(Options options) {
        return !Bitmaps.isHardware(options.getConfig()) || this.hardwareBitmapService.allowHardwareWorkerThread();
    }

    public final ErrorResult errorResult(ImageRequest imageRequest, Throwable th) {
        Drawable error;
        if (!(th instanceof NullRequestDataException) || (error = imageRequest.getFallback()) == null) {
            error = imageRequest.getError();
        }
        return new ErrorResult(error, imageRequest, th);
    }

    public final boolean isConfigValidForHardware(ImageRequest imageRequest, Bitmap.Config config) {
        if (!Bitmaps.isHardware(config)) {
            return true;
        }
        if (!imageRequest.getAllowHardware()) {
            return false;
        }
        Target target = imageRequest.getTarget();
        if (target instanceof ViewTarget) {
            View view = ((ViewTarget) target).getView();
            if (view.isAttachedToWindow() && !view.isHardwareAccelerated()) {
                return false;
            }
        }
        return true;
    }

    public final Options options(ImageRequest imageRequest, Size size) {
        Bitmap.Config bitmapConfig = isConfigValidForTransformations(imageRequest) && isConfigValidForHardwareAllocation(imageRequest, size) ? imageRequest.getBitmapConfig() : Bitmap.Config.ARGB_8888;
        CachePolicy networkCachePolicy = this.systemCallbacks.isOnline() ? imageRequest.getNetworkCachePolicy() : CachePolicy.DISABLED;
        boolean z = imageRequest.getAllowRgb565() && imageRequest.getTransformations().isEmpty() && bitmapConfig != Bitmap.Config.ALPHA_8;
        Dimension width = size.getWidth();
        Dimension.Undefined undefined = Dimension.Undefined.INSTANCE;
        return new Options(imageRequest.getContext(), bitmapConfig, imageRequest.getColorSpace(), size, (Intrinsics.areEqual(width, undefined) || Intrinsics.areEqual(size.getHeight(), undefined)) ? Scale.FIT : imageRequest.getScale(), Requests.getAllowInexactSize(imageRequest), z, imageRequest.getPremultipliedAlpha(), imageRequest.getDiskCacheKey(), imageRequest.getHeaders(), imageRequest.getTags(), imageRequest.getParameters(), imageRequest.getMemoryCachePolicy(), imageRequest.getDiskCachePolicy(), networkCachePolicy);
    }

    public final RequestDelegate requestDelegate(ImageRequest imageRequest, Job job) {
        Lifecycle lifecycle = imageRequest.getLifecycle();
        Target target = imageRequest.getTarget();
        return target instanceof ViewTarget ? new ViewTargetRequestDelegate(this.imageLoader, imageRequest, (ViewTarget) target, lifecycle, job) : new BaseRequestDelegate(lifecycle, job);
    }
}
