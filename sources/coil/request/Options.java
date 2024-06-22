package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import coil.size.Scale;
import coil.size.Size;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;

/* loaded from: classes.dex */
public final class Options {
    private final boolean allowInexactSize;
    private final boolean allowRgb565;
    private final ColorSpace colorSpace;
    private final Bitmap.Config config;
    private final Context context;
    private final String diskCacheKey;
    private final CachePolicy diskCachePolicy;
    private final Headers headers;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Parameters parameters;
    private final boolean premultipliedAlpha;
    private final Scale scale;
    private final Size size;
    private final Tags tags;

    public Options(Context context, Bitmap.Config config, ColorSpace colorSpace, Size size, Scale scale, boolean z, boolean z2, boolean z3, String str, Headers headers, Tags tags, Parameters parameters, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.context = context;
        this.config = config;
        this.colorSpace = colorSpace;
        this.size = size;
        this.scale = scale;
        this.allowInexactSize = z;
        this.allowRgb565 = z2;
        this.premultipliedAlpha = z3;
        this.diskCacheKey = str;
        this.headers = headers;
        this.tags = tags;
        this.parameters = parameters;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public final Options copy(Context context, Bitmap.Config config, ColorSpace colorSpace, Size size, Scale scale, boolean z, boolean z2, boolean z3, String str, Headers headers, Tags tags, Parameters parameters, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        return new Options(context, config, colorSpace, size, scale, z, z2, z3, str, headers, tags, parameters, cachePolicy, cachePolicy2, cachePolicy3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Options) {
            Options options = (Options) obj;
            if (Intrinsics.areEqual(this.context, options.context) && this.config == options.config && Intrinsics.areEqual(this.colorSpace, options.colorSpace) && Intrinsics.areEqual(this.size, options.size) && this.scale == options.scale && this.allowInexactSize == options.allowInexactSize && this.allowRgb565 == options.allowRgb565 && this.premultipliedAlpha == options.premultipliedAlpha && Intrinsics.areEqual(this.diskCacheKey, options.diskCacheKey) && Intrinsics.areEqual(this.headers, options.headers) && Intrinsics.areEqual(this.tags, options.tags) && Intrinsics.areEqual(this.parameters, options.parameters) && this.memoryCachePolicy == options.memoryCachePolicy && this.diskCachePolicy == options.diskCachePolicy && this.networkCachePolicy == options.networkCachePolicy) {
                return true;
            }
        }
        return false;
    }

    public final boolean getAllowInexactSize() {
        return this.allowInexactSize;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public final Bitmap.Config getConfig() {
        return this.config;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final boolean getPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final Size getSize() {
        return this.size;
    }

    public final Tags getTags() {
        return this.tags;
    }

    public int hashCode() {
        int hashCode = ((this.context.hashCode() * 31) + this.config.hashCode()) * 31;
        ColorSpace colorSpace = this.colorSpace;
        int hashCode2 = (((((((((((hashCode + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31) + this.size.hashCode()) * 31) + this.scale.hashCode()) * 31) + Boolean.hashCode(this.allowInexactSize)) * 31) + Boolean.hashCode(this.allowRgb565)) * 31) + Boolean.hashCode(this.premultipliedAlpha)) * 31;
        String str = this.diskCacheKey;
        return ((((((((((((hashCode2 + (str != null ? str.hashCode() : 0)) * 31) + this.headers.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.memoryCachePolicy.hashCode()) * 31) + this.diskCachePolicy.hashCode()) * 31) + this.networkCachePolicy.hashCode();
    }
}
