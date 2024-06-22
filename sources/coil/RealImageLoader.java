package coil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import coil.EventListener;
import coil.decode.BitmapFactoryDecoder;
import coil.fetch.AssetUriFetcher;
import coil.fetch.BitmapFetcher;
import coil.fetch.ByteBufferFetcher;
import coil.fetch.ContentUriFetcher;
import coil.fetch.DrawableFetcher;
import coil.fetch.FileFetcher;
import coil.fetch.HttpUriFetcher;
import coil.fetch.ResourceUriFetcher;
import coil.intercept.EngineInterceptor;
import coil.key.FileKeyer;
import coil.key.UriKeyer;
import coil.map.ByteArrayMapper;
import coil.map.FileUriMapper;
import coil.map.HttpUrlMapper;
import coil.map.ResourceIntMapper;
import coil.map.ResourceUriMapper;
import coil.map.StringMapper;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.OneShotDisposable;
import coil.request.RequestService;
import coil.target.ViewTarget;
import coil.util.ImageLoaderOptions;
import coil.util.Logger;
import coil.util.SystemCallbacks;
import coil.util.Utils;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public final class RealImageLoader implements ImageLoader {
    public static final Companion Companion = new Companion(null);
    private final Lazy callFactoryLazy;
    private final ComponentRegistry componentRegistry;
    private final ComponentRegistry components;
    private final Context context;
    private final DefaultRequestOptions defaults;
    private final Lazy diskCache$delegate;
    private final Lazy diskCacheLazy;
    private final EventListener.Factory eventListenerFactory;
    private final List interceptors;
    private final AtomicBoolean isShutdown;
    private final Lazy memoryCache$delegate;
    private final Lazy memoryCacheLazy;
    private final ImageLoaderOptions options;
    private final RequestService requestService;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain().getImmediate()).plus(new RealImageLoader$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this)));
    private final SystemCallbacks systemCallbacks;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RealImageLoader(Context context, DefaultRequestOptions defaultRequestOptions, Lazy lazy, Lazy lazy2, Lazy lazy3, EventListener.Factory factory, ComponentRegistry componentRegistry, ImageLoaderOptions imageLoaderOptions, Logger logger) {
        List plus;
        this.context = context;
        this.defaults = defaultRequestOptions;
        this.memoryCacheLazy = lazy;
        this.diskCacheLazy = lazy2;
        this.callFactoryLazy = lazy3;
        this.eventListenerFactory = factory;
        this.componentRegistry = componentRegistry;
        this.options = imageLoaderOptions;
        SystemCallbacks systemCallbacks = new SystemCallbacks(this, context, imageLoaderOptions.getNetworkObserverEnabled());
        this.systemCallbacks = systemCallbacks;
        RequestService requestService = new RequestService(this, systemCallbacks, null);
        this.requestService = requestService;
        this.memoryCache$delegate = lazy;
        this.diskCache$delegate = lazy2;
        this.components = componentRegistry.newBuilder().add(new HttpUrlMapper(), HttpUrl.class).add(new StringMapper(), String.class).add(new FileUriMapper(), Uri.class).add(new ResourceUriMapper(), Uri.class).add(new ResourceIntMapper(), Integer.class).add(new ByteArrayMapper(), byte[].class).add(new UriKeyer(), Uri.class).add(new FileKeyer(imageLoaderOptions.getAddLastModifiedToFileCacheKey()), File.class).add(new HttpUriFetcher.Factory(lazy3, lazy2, imageLoaderOptions.getRespectCacheHeaders()), Uri.class).add(new FileFetcher.Factory(), File.class).add(new AssetUriFetcher.Factory(), Uri.class).add(new ContentUriFetcher.Factory(), Uri.class).add(new ResourceUriFetcher.Factory(), Uri.class).add(new DrawableFetcher.Factory(), Drawable.class).add(new BitmapFetcher.Factory(), Bitmap.class).add(new ByteBufferFetcher.Factory(), ByteBuffer.class).add(new BitmapFactoryDecoder.Factory(imageLoaderOptions.getBitmapFactoryMaxParallelism(), imageLoaderOptions.getBitmapFactoryExifOrientationPolicy())).build();
        plus = CollectionsKt___CollectionsKt.plus(getComponents().getInterceptors(), new EngineInterceptor(this, requestService, null));
        this.interceptors = plus;
        this.isShutdown = new AtomicBoolean(false);
        systemCallbacks.register();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x018e A[Catch: all -> 0x01ab, TryCatch #4 {all -> 0x01ab, blocks: (B:16:0x0188, B:18:0x018e, B:22:0x0199, B:24:0x019d), top: B:15:0x0188 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0199 A[Catch: all -> 0x01ab, TryCatch #4 {all -> 0x01ab, blocks: (B:16:0x0188, B:18:0x018e, B:22:0x0199, B:24:0x019d), top: B:15:0x0188 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0185 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ff A[Catch: all -> 0x01b7, TryCatch #1 {all -> 0x01b7, blocks: (B:62:0x00f9, B:64:0x00ff, B:66:0x0105, B:68:0x010d, B:70:0x0115, B:71:0x0127, B:73:0x012d, B:74:0x0130, B:76:0x0139, B:77:0x013c, B:81:0x0123, B:89:0x00cf, B:91:0x00db, B:93:0x00e0, B:97:0x01b1, B:98:0x01b6), top: B:88:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0115 A[Catch: all -> 0x01b7, TryCatch #1 {all -> 0x01b7, blocks: (B:62:0x00f9, B:64:0x00ff, B:66:0x0105, B:68:0x010d, B:70:0x0115, B:71:0x0127, B:73:0x012d, B:74:0x0130, B:76:0x0139, B:77:0x013c, B:81:0x0123, B:89:0x00cf, B:91:0x00db, B:93:0x00e0, B:97:0x01b1, B:98:0x01b6), top: B:88:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x012d A[Catch: all -> 0x01b7, TryCatch #1 {all -> 0x01b7, blocks: (B:62:0x00f9, B:64:0x00ff, B:66:0x0105, B:68:0x010d, B:70:0x0115, B:71:0x0127, B:73:0x012d, B:74:0x0130, B:76:0x0139, B:77:0x013c, B:81:0x0123, B:89:0x00cf, B:91:0x00db, B:93:0x00e0, B:97:0x01b1, B:98:0x01b6), top: B:88:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0139 A[Catch: all -> 0x01b7, TryCatch #1 {all -> 0x01b7, blocks: (B:62:0x00f9, B:64:0x00ff, B:66:0x0105, B:68:0x010d, B:70:0x0115, B:71:0x0127, B:73:0x012d, B:74:0x0130, B:76:0x0139, B:77:0x013c, B:81:0x0123, B:89:0x00cf, B:91:0x00db, B:93:0x00e0, B:97:0x01b1, B:98:0x01b6), top: B:88:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0155 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0123 A[Catch: all -> 0x01b7, TryCatch #1 {all -> 0x01b7, blocks: (B:62:0x00f9, B:64:0x00ff, B:66:0x0105, B:68:0x010d, B:70:0x0115, B:71:0x0127, B:73:0x012d, B:74:0x0130, B:76:0x0139, B:77:0x013c, B:81:0x0123, B:89:0x00cf, B:91:0x00db, B:93:0x00e0, B:97:0x01b1, B:98:0x01b6), top: B:88:0x00cf }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeMain(coil.request.ImageRequest r20, int r21, kotlin.coroutines.Continuation r22) {
        /*
            Method dump skipped, instructions count: 471
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.executeMain(coil.request.ImageRequest, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void onCancel(ImageRequest imageRequest, EventListener eventListener) {
        eventListener.onCancel(imageRequest);
        ImageRequest.Listener listener = imageRequest.getListener();
        if (listener != null) {
            listener.onCancel(imageRequest);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
    
        if (r4 != null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void onError(coil.request.ErrorResult r3, coil.target.Target r4, coil.EventListener r5) {
        /*
            r2 = this;
            coil.request.ImageRequest r2 = r3.getRequest()
            boolean r0 = r4 instanceof coil.transition.TransitionTarget
            if (r0 != 0) goto Lb
            if (r4 == 0) goto L37
            goto L1e
        Lb:
            coil.request.ImageRequest r0 = r3.getRequest()
            coil.transition.Transition$Factory r0 = r0.getTransitionFactory()
            r1 = r4
            coil.transition.TransitionTarget r1 = (coil.transition.TransitionTarget) r1
            coil.transition.Transition r0 = r0.create(r1, r3)
            boolean r1 = r0 instanceof coil.transition.NoneTransition
            if (r1 == 0) goto L26
        L1e:
            android.graphics.drawable.Drawable r0 = r3.getDrawable()
            r4.onError(r0)
            goto L37
        L26:
            coil.request.ImageRequest r4 = r3.getRequest()
            r5.transitionStart(r4, r0)
            r0.transition()
            coil.request.ImageRequest r4 = r3.getRequest()
            r5.transitionEnd(r4, r0)
        L37:
            r5.onError(r2, r3)
            coil.request.ImageRequest$Listener r4 = r2.getListener()
            if (r4 == 0) goto L43
            r4.onError(r2, r3)
        L43:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onError(coil.request.ErrorResult, coil.target.Target, coil.EventListener):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000b, code lost:
    
        if (r4 != null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void onSuccess(coil.request.SuccessResult r3, coil.target.Target r4, coil.EventListener r5) {
        /*
            r2 = this;
            coil.request.ImageRequest r2 = r3.getRequest()
            r3.getDataSource()
            boolean r0 = r4 instanceof coil.transition.TransitionTarget
            if (r0 != 0) goto Le
            if (r4 == 0) goto L3a
            goto L21
        Le:
            coil.request.ImageRequest r0 = r3.getRequest()
            coil.transition.Transition$Factory r0 = r0.getTransitionFactory()
            r1 = r4
            coil.transition.TransitionTarget r1 = (coil.transition.TransitionTarget) r1
            coil.transition.Transition r0 = r0.create(r1, r3)
            boolean r1 = r0 instanceof coil.transition.NoneTransition
            if (r1 == 0) goto L29
        L21:
            android.graphics.drawable.Drawable r0 = r3.getDrawable()
            r4.onSuccess(r0)
            goto L3a
        L29:
            coil.request.ImageRequest r4 = r3.getRequest()
            r5.transitionStart(r4, r0)
            r0.transition()
            coil.request.ImageRequest r4 = r3.getRequest()
            r5.transitionEnd(r4, r0)
        L3a:
            r5.onSuccess(r2, r3)
            coil.request.ImageRequest$Listener r4 = r2.getListener()
            if (r4 == 0) goto L46
            r4.onSuccess(r2, r3)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onSuccess(coil.request.SuccessResult, coil.target.Target, coil.EventListener):void");
    }

    @Override // coil.ImageLoader
    public Disposable enqueue(ImageRequest imageRequest) {
        Deferred async$default;
        async$default = BuildersKt__Builders_commonKt.async$default(this.scope, null, null, new RealImageLoader$enqueue$job$1(this, imageRequest, null), 3, null);
        return imageRequest.getTarget() instanceof ViewTarget ? Utils.getRequestManager(((ViewTarget) imageRequest.getTarget()).getView()).getDisposable(async$default) : new OneShotDisposable(async$default);
    }

    @Override // coil.ImageLoader
    public Object execute(ImageRequest imageRequest, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new RealImageLoader$execute$2(imageRequest, this, null), continuation);
    }

    @Override // coil.ImageLoader
    public ComponentRegistry getComponents() {
        return this.components;
    }

    public DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    public final Logger getLogger() {
        return null;
    }

    @Override // coil.ImageLoader
    public MemoryCache getMemoryCache() {
        return (MemoryCache) this.memoryCache$delegate.getValue();
    }

    public final void onTrimMemory$coil_base_release(int i) {
        MemoryCache memoryCache;
        Lazy lazy = this.memoryCacheLazy;
        if (lazy == null || (memoryCache = (MemoryCache) lazy.getValue()) == null) {
            return;
        }
        memoryCache.trimMemory(i);
    }
}
