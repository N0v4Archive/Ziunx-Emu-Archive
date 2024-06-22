package coil.memory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import coil.EventListener;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.DecodeUtils;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.memory.MemoryCache;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.RequestService;
import coil.request.SuccessResult;
import coil.size.Dimension;
import coil.size.Scale;
import coil.size.Size;
import coil.size.Sizes;
import coil.util.Bitmaps;
import coil.util.Logger;
import coil.util.Requests;
import coil.util.Utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* loaded from: classes.dex */
public final class MemoryCacheService {
    public static final Companion Companion = new Companion(null);
    private final ImageLoader imageLoader;
    private final RequestService requestService;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MemoryCacheService(ImageLoader imageLoader, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.requestService = requestService;
    }

    private final String getDiskCacheKey(MemoryCache.Value value) {
        Object obj = value.getExtras().get("coil#disk_cache_key");
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    private final boolean isSampled(MemoryCache.Value value) {
        Object obj = value.getExtras().get("coil#is_sampled");
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final boolean isSizeValid(ImageRequest imageRequest, MemoryCache.Key key, MemoryCache.Value value, Size size, Scale scale) {
        double coerceAtMost;
        boolean isSampled = isSampled(value);
        if (Sizes.isOriginal(size)) {
            return !isSampled;
        }
        String str = (String) key.getExtras().get("coil#transformation_size");
        if (str != null) {
            return Intrinsics.areEqual(str, size.toString());
        }
        int width = value.getBitmap().getWidth();
        int height = value.getBitmap().getHeight();
        Dimension width2 = size.getWidth();
        int i = width2 instanceof Dimension.Pixels ? ((Dimension.Pixels) width2).px : Integer.MAX_VALUE;
        Dimension height2 = size.getHeight();
        int i2 = height2 instanceof Dimension.Pixels ? ((Dimension.Pixels) height2).px : Integer.MAX_VALUE;
        double computeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, height, i, i2, scale);
        boolean allowInexactSize = Requests.getAllowInexactSize(imageRequest);
        if (allowInexactSize) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(computeSizeMultiplier, 1.0d);
            if (Math.abs(i - (width * coerceAtMost)) <= 1.0d || Math.abs(i2 - (coerceAtMost * height)) <= 1.0d) {
                return true;
            }
        } else if ((Utils.isMinOrMax(i) || Math.abs(i - width) <= 1) && (Utils.isMinOrMax(i2) || Math.abs(i2 - height) <= 1)) {
            return true;
        }
        if ((computeSizeMultiplier == 1.0d) || allowInexactSize) {
            return computeSizeMultiplier <= 1.0d || !isSampled;
        }
        return false;
    }

    public final MemoryCache.Value getCacheValue(ImageRequest imageRequest, MemoryCache.Key key, Size size, Scale scale) {
        if (!imageRequest.getMemoryCachePolicy().getReadEnabled()) {
            return null;
        }
        MemoryCache memoryCache = this.imageLoader.getMemoryCache();
        MemoryCache.Value value = memoryCache != null ? memoryCache.get(key) : null;
        if (value == null || !isCacheValueValid$coil_base_release(imageRequest, key, value, size, scale)) {
            return null;
        }
        return value;
    }

    public final boolean isCacheValueValid$coil_base_release(ImageRequest imageRequest, MemoryCache.Key key, MemoryCache.Value value, Size size, Scale scale) {
        if (this.requestService.isConfigValidForHardware(imageRequest, Bitmaps.getSafeConfig(value.getBitmap()))) {
            return isSizeValid(imageRequest, key, value, size, scale);
        }
        return false;
    }

    public final MemoryCache.Key newCacheKey(ImageRequest imageRequest, Object obj, Options options, EventListener eventListener) {
        Map mutableMap;
        MemoryCache.Key memoryCacheKey = imageRequest.getMemoryCacheKey();
        if (memoryCacheKey != null) {
            return memoryCacheKey;
        }
        eventListener.keyStart(imageRequest, obj);
        String key = this.imageLoader.getComponents().key(obj, options);
        eventListener.keyEnd(imageRequest, key);
        if (key == null) {
            return null;
        }
        List transformations = imageRequest.getTransformations();
        Map memoryCacheKeys = imageRequest.getParameters().memoryCacheKeys();
        if (transformations.isEmpty() && memoryCacheKeys.isEmpty()) {
            return new MemoryCache.Key(key, null, 2, null);
        }
        mutableMap = MapsKt__MapsKt.toMutableMap(memoryCacheKeys);
        if (!transformations.isEmpty()) {
            List transformations2 = imageRequest.getTransformations();
            if (transformations2.size() > 0) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(transformations2.get(0));
                StringBuilder sb = new StringBuilder();
                sb.append("coil#transformation_");
                sb.append(0);
                throw null;
            }
            mutableMap.put("coil#transformation_size", options.getSize().toString());
        }
        return new MemoryCache.Key(key, mutableMap);
    }

    public final SuccessResult newResult(Interceptor.Chain chain, ImageRequest imageRequest, MemoryCache.Key key, MemoryCache.Value value) {
        return new SuccessResult(new BitmapDrawable(imageRequest.getContext().getResources(), value.getBitmap()), imageRequest, DataSource.MEMORY_CACHE, key, getDiskCacheKey(value), isSampled(value), Utils.isPlaceholderCached(chain));
    }

    public final boolean setCacheValue(MemoryCache.Key key, ImageRequest imageRequest, EngineInterceptor.ExecuteResult executeResult) {
        MemoryCache memoryCache;
        Bitmap bitmap;
        if (imageRequest.getMemoryCachePolicy().getWriteEnabled() && (memoryCache = this.imageLoader.getMemoryCache()) != null && key != null) {
            Drawable drawable = executeResult.getDrawable();
            BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
            if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("coil#is_sampled", Boolean.valueOf(executeResult.isSampled()));
                String diskCacheKey = executeResult.getDiskCacheKey();
                if (diskCacheKey != null) {
                    linkedHashMap.put("coil#disk_cache_key", diskCacheKey);
                }
                memoryCache.set(key, new MemoryCache.Value(bitmap, linkedHashMap));
                return true;
            }
        }
        return false;
    }
}
