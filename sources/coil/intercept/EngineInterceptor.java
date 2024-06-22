package coil.intercept;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.EventListener;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.memory.MemoryCacheService;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.RequestService;
import coil.util.Bitmaps;
import coil.util.DrawableUtils;
import coil.util.Logger;
import coil.util.Utils;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes.dex */
public final class EngineInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private final ImageLoader imageLoader;
    private final MemoryCacheService memoryCacheService;
    private final RequestService requestService;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class ExecuteResult {
        private final DataSource dataSource;
        private final String diskCacheKey;
        private final Drawable drawable;
        private final boolean isSampled;

        public ExecuteResult(Drawable drawable, boolean z, DataSource dataSource, String str) {
            this.drawable = drawable;
            this.isSampled = z;
            this.dataSource = dataSource;
            this.diskCacheKey = str;
        }

        public static /* synthetic */ ExecuteResult copy$default(ExecuteResult executeResult, Drawable drawable, boolean z, DataSource dataSource, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                drawable = executeResult.drawable;
            }
            if ((i & 2) != 0) {
                z = executeResult.isSampled;
            }
            if ((i & 4) != 0) {
                dataSource = executeResult.dataSource;
            }
            if ((i & 8) != 0) {
                str = executeResult.diskCacheKey;
            }
            return executeResult.copy(drawable, z, dataSource, str);
        }

        public final ExecuteResult copy(Drawable drawable, boolean z, DataSource dataSource, String str) {
            return new ExecuteResult(drawable, z, dataSource, str);
        }

        public final DataSource getDataSource() {
            return this.dataSource;
        }

        public final String getDiskCacheKey() {
            return this.diskCacheKey;
        }

        public final Drawable getDrawable() {
            return this.drawable;
        }

        public final boolean isSampled() {
            return this.isSampled;
        }
    }

    public EngineInterceptor(ImageLoader imageLoader, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.requestService = requestService;
        this.memoryCacheService = new MemoryCacheService(imageLoader, requestService, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap convertDrawableToBitmap(Drawable drawable, Options options, List list) {
        boolean contains;
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            contains = ArraysKt___ArraysKt.contains(Utils.getVALID_TRANSFORMATION_CONFIGS(), Bitmaps.getSafeConfig(bitmap));
            if (contains) {
                return bitmap;
            }
        }
        return DrawableUtils.INSTANCE.convertToBitmap(drawable, options.getConfig(), options.getSize(), options.getScale(), options.getAllowInexactSize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0097 -> B:10:0x009f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object decode(coil.fetch.SourceResult r9, coil.ComponentRegistry r10, coil.request.ImageRequest r11, java.lang.Object r12, coil.request.Options r13, coil.EventListener r14, kotlin.coroutines.Continuation r15) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.decode(coil.fetch.SourceResult, coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0158 A[Catch: all -> 0x007f, TryCatch #1 {all -> 0x007f, blocks: (B:25:0x0053, B:26:0x018c, B:39:0x0076, B:41:0x014d, B:43:0x0158, B:47:0x0190, B:49:0x0194, B:51:0x0209, B:52:0x020e), top: B:7:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0190 A[Catch: all -> 0x007f, TryCatch #1 {all -> 0x007f, blocks: (B:25:0x0053, B:26:0x018c, B:39:0x0076, B:41:0x014d, B:43:0x0158, B:47:0x0190, B:49:0x0194, B:51:0x0209, B:52:0x020e), top: B:7:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object execute(coil.request.ImageRequest r35, java.lang.Object r36, coil.request.Options r37, coil.EventListener r38, kotlin.coroutines.Continuation r39) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.execute(coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0091 -> B:10:0x0099). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fetch(coil.ComponentRegistry r9, coil.request.ImageRequest r10, java.lang.Object r11, coil.request.Options r12, coil.EventListener r13, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.fetch(coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    @Override // coil.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object intercept(coil.intercept.Interceptor.Chain r14, kotlin.coroutines.Continuation r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof coil.intercept.EngineInterceptor$intercept$1
            if (r0 == 0) goto L13
            r0 = r15
            coil.intercept.EngineInterceptor$intercept$1 r0 = (coil.intercept.EngineInterceptor$intercept$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.EngineInterceptor$intercept$1 r0 = new coil.intercept.EngineInterceptor$intercept$1
            r0.<init>(r13, r15)
        L18:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r13 = r0.L$1
            r14 = r13
            coil.intercept.Interceptor$Chain r14 = (coil.intercept.Interceptor.Chain) r14
            java.lang.Object r13 = r0.L$0
            coil.intercept.EngineInterceptor r13 = (coil.intercept.EngineInterceptor) r13
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Throwable -> L9b
            goto L9a
        L32:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L3a:
            kotlin.ResultKt.throwOnFailure(r15)
            coil.request.ImageRequest r6 = r14.getRequest()     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r15 = r6.getData()     // Catch: java.lang.Throwable -> L9b
            coil.size.Size r2 = r14.getSize()     // Catch: java.lang.Throwable -> L9b
            coil.EventListener r9 = coil.util.Utils.getEventListener(r14)     // Catch: java.lang.Throwable -> L9b
            coil.request.RequestService r4 = r13.requestService     // Catch: java.lang.Throwable -> L9b
            coil.request.Options r8 = r4.options(r6, r2)     // Catch: java.lang.Throwable -> L9b
            coil.size.Scale r4 = r8.getScale()     // Catch: java.lang.Throwable -> L9b
            r9.mapStart(r6, r15)     // Catch: java.lang.Throwable -> L9b
            coil.ImageLoader r5 = r13.imageLoader     // Catch: java.lang.Throwable -> L9b
            coil.ComponentRegistry r5 = r5.getComponents()     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r7 = r5.map(r15, r8)     // Catch: java.lang.Throwable -> L9b
            r9.mapEnd(r6, r7)     // Catch: java.lang.Throwable -> L9b
            coil.memory.MemoryCacheService r15 = r13.memoryCacheService     // Catch: java.lang.Throwable -> L9b
            coil.memory.MemoryCache$Key r10 = r15.newCacheKey(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L9b
            if (r10 == 0) goto L76
            coil.memory.MemoryCacheService r15 = r13.memoryCacheService     // Catch: java.lang.Throwable -> L9b
            coil.memory.MemoryCache$Value r15 = r15.getCacheValue(r6, r10, r2, r4)     // Catch: java.lang.Throwable -> L9b
            goto L77
        L76:
            r15 = 0
        L77:
            if (r15 == 0) goto L80
            coil.memory.MemoryCacheService r0 = r13.memoryCacheService     // Catch: java.lang.Throwable -> L9b
            coil.request.SuccessResult r13 = r0.newResult(r14, r6, r10, r15)     // Catch: java.lang.Throwable -> L9b
            return r13
        L80:
            kotlinx.coroutines.CoroutineDispatcher r15 = r6.getFetcherDispatcher()     // Catch: java.lang.Throwable -> L9b
            coil.intercept.EngineInterceptor$intercept$2 r2 = new coil.intercept.EngineInterceptor$intercept$2     // Catch: java.lang.Throwable -> L9b
            r12 = 0
            r4 = r2
            r5 = r13
            r11 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L9b
            r0.L$0 = r13     // Catch: java.lang.Throwable -> L9b
            r0.L$1 = r14     // Catch: java.lang.Throwable -> L9b
            r0.label = r3     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r15 = kotlinx.coroutines.BuildersKt.withContext(r15, r2, r0)     // Catch: java.lang.Throwable -> L9b
            if (r15 != r1) goto L9a
            return r1
        L9a:
            return r15
        L9b:
            r15 = move-exception
            boolean r0 = r15 instanceof java.util.concurrent.CancellationException
            if (r0 != 0) goto Lab
            coil.request.RequestService r13 = r13.requestService
            coil.request.ImageRequest r14 = r14.getRequest()
            coil.request.ErrorResult r13 = r13.errorResult(r14, r15)
            return r13
        Lab:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.intercept(coil.intercept.Interceptor$Chain, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object transform$coil_base_release(ExecuteResult executeResult, ImageRequest imageRequest, Options options, EventListener eventListener, Continuation continuation) {
        List transformations = imageRequest.getTransformations();
        return transformations.isEmpty() ? executeResult : ((executeResult.getDrawable() instanceof BitmapDrawable) || imageRequest.getAllowConversionToBitmap()) ? BuildersKt.withContext(imageRequest.getTransformationDispatcher(), new EngineInterceptor$transform$3(this, executeResult, options, transformations, eventListener, imageRequest, null), continuation) : executeResult;
    }
}
