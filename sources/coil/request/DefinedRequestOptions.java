package coil.request;

import android.graphics.Bitmap;
import androidx.lifecycle.Lifecycle;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.SizeResolver;
import coil.transition.Transition;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

/* loaded from: classes.dex */
public final class DefinedRequestOptions {
    private final Boolean allowHardware;
    private final Boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final CoroutineDispatcher decoderDispatcher;
    private final CachePolicy diskCachePolicy;
    private final CoroutineDispatcher fetcherDispatcher;
    private final CoroutineDispatcher interceptorDispatcher;
    private final Lifecycle lifecycle;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Precision precision;
    private final Scale scale;
    private final SizeResolver sizeResolver;
    private final CoroutineDispatcher transformationDispatcher;
    private final Transition.Factory transitionFactory;

    public DefinedRequestOptions(Lifecycle lifecycle, SizeResolver sizeResolver, Scale scale, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, CoroutineDispatcher coroutineDispatcher3, CoroutineDispatcher coroutineDispatcher4, Transition.Factory factory, Precision precision, Bitmap.Config config, Boolean bool, Boolean bool2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.lifecycle = lifecycle;
        this.sizeResolver = sizeResolver;
        this.scale = scale;
        this.interceptorDispatcher = coroutineDispatcher;
        this.fetcherDispatcher = coroutineDispatcher2;
        this.decoderDispatcher = coroutineDispatcher3;
        this.transformationDispatcher = coroutineDispatcher4;
        this.transitionFactory = factory;
        this.precision = precision;
        this.bitmapConfig = config;
        this.allowHardware = bool;
        this.allowRgb565 = bool2;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefinedRequestOptions) {
            DefinedRequestOptions definedRequestOptions = (DefinedRequestOptions) obj;
            if (Intrinsics.areEqual(this.lifecycle, definedRequestOptions.lifecycle) && Intrinsics.areEqual(this.sizeResolver, definedRequestOptions.sizeResolver) && this.scale == definedRequestOptions.scale && Intrinsics.areEqual(this.interceptorDispatcher, definedRequestOptions.interceptorDispatcher) && Intrinsics.areEqual(this.fetcherDispatcher, definedRequestOptions.fetcherDispatcher) && Intrinsics.areEqual(this.decoderDispatcher, definedRequestOptions.decoderDispatcher) && Intrinsics.areEqual(this.transformationDispatcher, definedRequestOptions.transformationDispatcher) && Intrinsics.areEqual(this.transitionFactory, definedRequestOptions.transitionFactory) && this.precision == definedRequestOptions.precision && this.bitmapConfig == definedRequestOptions.bitmapConfig && Intrinsics.areEqual(this.allowHardware, definedRequestOptions.allowHardware) && Intrinsics.areEqual(this.allowRgb565, definedRequestOptions.allowRgb565) && this.memoryCachePolicy == definedRequestOptions.memoryCachePolicy && this.diskCachePolicy == definedRequestOptions.diskCachePolicy && this.networkCachePolicy == definedRequestOptions.networkCachePolicy) {
                return true;
            }
        }
        return false;
    }

    public final Boolean getAllowHardware() {
        return this.allowHardware;
    }

    public final Boolean getAllowRgb565() {
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

    public final CoroutineDispatcher getFetcherDispatcher() {
        return this.fetcherDispatcher;
    }

    public final CoroutineDispatcher getInterceptorDispatcher() {
        return this.interceptorDispatcher;
    }

    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final SizeResolver getSizeResolver() {
        return this.sizeResolver;
    }

    public final CoroutineDispatcher getTransformationDispatcher() {
        return this.transformationDispatcher;
    }

    public final Transition.Factory getTransitionFactory() {
        return this.transitionFactory;
    }

    public int hashCode() {
        Lifecycle lifecycle = this.lifecycle;
        int hashCode = (lifecycle != null ? lifecycle.hashCode() : 0) * 31;
        SizeResolver sizeResolver = this.sizeResolver;
        int hashCode2 = (hashCode + (sizeResolver != null ? sizeResolver.hashCode() : 0)) * 31;
        Scale scale = this.scale;
        int hashCode3 = (hashCode2 + (scale != null ? scale.hashCode() : 0)) * 31;
        CoroutineDispatcher coroutineDispatcher = this.interceptorDispatcher;
        int hashCode4 = (hashCode3 + (coroutineDispatcher != null ? coroutineDispatcher.hashCode() : 0)) * 31;
        CoroutineDispatcher coroutineDispatcher2 = this.fetcherDispatcher;
        int hashCode5 = (hashCode4 + (coroutineDispatcher2 != null ? coroutineDispatcher2.hashCode() : 0)) * 31;
        CoroutineDispatcher coroutineDispatcher3 = this.decoderDispatcher;
        int hashCode6 = (hashCode5 + (coroutineDispatcher3 != null ? coroutineDispatcher3.hashCode() : 0)) * 31;
        CoroutineDispatcher coroutineDispatcher4 = this.transformationDispatcher;
        int hashCode7 = (hashCode6 + (coroutineDispatcher4 != null ? coroutineDispatcher4.hashCode() : 0)) * 31;
        Transition.Factory factory = this.transitionFactory;
        int hashCode8 = (hashCode7 + (factory != null ? factory.hashCode() : 0)) * 31;
        Precision precision = this.precision;
        int hashCode9 = (hashCode8 + (precision != null ? precision.hashCode() : 0)) * 31;
        Bitmap.Config config = this.bitmapConfig;
        int hashCode10 = (hashCode9 + (config != null ? config.hashCode() : 0)) * 31;
        Boolean bool = this.allowHardware;
        int hashCode11 = (hashCode10 + (bool != null ? bool.hashCode() : 0)) * 31;
        Boolean bool2 = this.allowRgb565;
        int hashCode12 = (hashCode11 + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy = this.memoryCachePolicy;
        int hashCode13 = (hashCode12 + (cachePolicy != null ? cachePolicy.hashCode() : 0)) * 31;
        CachePolicy cachePolicy2 = this.diskCachePolicy;
        int hashCode14 = (hashCode13 + (cachePolicy2 != null ? cachePolicy2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy3 = this.networkCachePolicy;
        return hashCode14 + (cachePolicy3 != null ? cachePolicy3.hashCode() : 0);
    }
}
