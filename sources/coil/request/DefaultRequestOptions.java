package coil.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import coil.size.Precision;
import coil.transition.Transition;
import coil.util.Utils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes.dex */
public final class DefaultRequestOptions {
    private final boolean allowHardware;
    private final boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final CoroutineDispatcher decoderDispatcher;
    private final CachePolicy diskCachePolicy;
    private final Drawable error;
    private final Drawable fallback;
    private final CoroutineDispatcher fetcherDispatcher;
    private final CoroutineDispatcher interceptorDispatcher;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Drawable placeholder;
    private final Precision precision;
    private final CoroutineDispatcher transformationDispatcher;
    private final Transition.Factory transitionFactory;

    public DefaultRequestOptions(CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, CoroutineDispatcher coroutineDispatcher3, CoroutineDispatcher coroutineDispatcher4, Transition.Factory factory, Precision precision, Bitmap.Config config, boolean z, boolean z2, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.interceptorDispatcher = coroutineDispatcher;
        this.fetcherDispatcher = coroutineDispatcher2;
        this.decoderDispatcher = coroutineDispatcher3;
        this.transformationDispatcher = coroutineDispatcher4;
        this.transitionFactory = factory;
        this.precision = precision;
        this.bitmapConfig = config;
        this.allowHardware = z;
        this.allowRgb565 = z2;
        this.placeholder = drawable;
        this.error = drawable2;
        this.fallback = drawable3;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public /* synthetic */ DefaultRequestOptions(CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, CoroutineDispatcher coroutineDispatcher3, CoroutineDispatcher coroutineDispatcher4, Transition.Factory factory, Precision precision, Bitmap.Config config, boolean z, boolean z2, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dispatchers.getMain().getImmediate() : coroutineDispatcher, (i & 2) != 0 ? Dispatchers.getIO() : coroutineDispatcher2, (i & 4) != 0 ? Dispatchers.getIO() : coroutineDispatcher3, (i & 8) != 0 ? Dispatchers.getIO() : coroutineDispatcher4, (i & 16) != 0 ? Transition.Factory.NONE : factory, (i & 32) != 0 ? Precision.AUTOMATIC : precision, (i & 64) != 0 ? Utils.getDEFAULT_BITMAP_CONFIG() : config, (i & 128) != 0 ? true : z, (i & 256) != 0 ? false : z2, (i & 512) != 0 ? null : drawable, (i & 1024) != 0 ? null : drawable2, (i & 2048) == 0 ? drawable3 : null, (i & 4096) != 0 ? CachePolicy.ENABLED : cachePolicy, (i & 8192) != 0 ? CachePolicy.ENABLED : cachePolicy2, (i & 16384) != 0 ? CachePolicy.ENABLED : cachePolicy3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultRequestOptions) {
            DefaultRequestOptions defaultRequestOptions = (DefaultRequestOptions) obj;
            if (Intrinsics.areEqual(this.interceptorDispatcher, defaultRequestOptions.interceptorDispatcher) && Intrinsics.areEqual(this.fetcherDispatcher, defaultRequestOptions.fetcherDispatcher) && Intrinsics.areEqual(this.decoderDispatcher, defaultRequestOptions.decoderDispatcher) && Intrinsics.areEqual(this.transformationDispatcher, defaultRequestOptions.transformationDispatcher) && Intrinsics.areEqual(this.transitionFactory, defaultRequestOptions.transitionFactory) && this.precision == defaultRequestOptions.precision && this.bitmapConfig == defaultRequestOptions.bitmapConfig && this.allowHardware == defaultRequestOptions.allowHardware && this.allowRgb565 == defaultRequestOptions.allowRgb565 && Intrinsics.areEqual(this.placeholder, defaultRequestOptions.placeholder) && Intrinsics.areEqual(this.error, defaultRequestOptions.error) && Intrinsics.areEqual(this.fallback, defaultRequestOptions.fallback) && this.memoryCachePolicy == defaultRequestOptions.memoryCachePolicy && this.diskCachePolicy == defaultRequestOptions.diskCachePolicy && this.networkCachePolicy == defaultRequestOptions.networkCachePolicy) {
                return true;
            }
        }
        return false;
    }

    public final boolean getAllowHardware() {
        return this.allowHardware;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public final CoroutineDispatcher getDecoderDispatcher() {
        return this.decoderDispatcher;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final Drawable getError() {
        return this.error;
    }

    public final Drawable getFallback() {
        return this.fallback;
    }

    public final CoroutineDispatcher getFetcherDispatcher() {
        return this.fetcherDispatcher;
    }

    public final CoroutineDispatcher getInterceptorDispatcher() {
        return this.interceptorDispatcher;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final Drawable getPlaceholder() {
        return this.placeholder;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final CoroutineDispatcher getTransformationDispatcher() {
        return this.transformationDispatcher;
    }

    public final Transition.Factory getTransitionFactory() {
        return this.transitionFactory;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((this.interceptorDispatcher.hashCode() * 31) + this.fetcherDispatcher.hashCode()) * 31) + this.decoderDispatcher.hashCode()) * 31) + this.transformationDispatcher.hashCode()) * 31) + this.transitionFactory.hashCode()) * 31) + this.precision.hashCode()) * 31) + this.bitmapConfig.hashCode()) * 31) + Boolean.hashCode(this.allowHardware)) * 31) + Boolean.hashCode(this.allowRgb565)) * 31;
        Drawable drawable = this.placeholder;
        int hashCode2 = (hashCode + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Drawable drawable2 = this.error;
        int hashCode3 = (hashCode2 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Drawable drawable3 = this.fallback;
        return ((((((hashCode3 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31) + this.memoryCachePolicy.hashCode()) * 31) + this.diskCachePolicy.hashCode()) * 31) + this.networkCachePolicy.hashCode();
    }
}
