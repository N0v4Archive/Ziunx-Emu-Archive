package coil.intercept;

import coil.EventListener;
import coil.intercept.Interceptor;
import coil.request.ImageRequest;
import coil.request.NullRequestData;
import coil.size.Size;
import java.util.List;

/* loaded from: classes.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final EventListener eventListener;
    private final int index;
    private final ImageRequest initialRequest;
    private final List interceptors;
    private final boolean isPlaceholderCached;
    private final ImageRequest request;
    private final Size size;

    public RealInterceptorChain(ImageRequest imageRequest, List list, int i, ImageRequest imageRequest2, Size size, EventListener eventListener, boolean z) {
        this.initialRequest = imageRequest;
        this.interceptors = list;
        this.index = i;
        this.request = imageRequest2;
        this.size = size;
        this.eventListener = eventListener;
        this.isPlaceholderCached = z;
    }

    private final void checkRequest(ImageRequest imageRequest, Interceptor interceptor) {
        if (!(imageRequest.getContext() == this.initialRequest.getContext())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's context.").toString());
        }
        if (!(imageRequest.getData() != NullRequestData.INSTANCE)) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot set the request's data to null.").toString());
        }
        if (!(imageRequest.getTarget() == this.initialRequest.getTarget())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's target.").toString());
        }
        if (!(imageRequest.getLifecycle() == this.initialRequest.getLifecycle())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's lifecycle.").toString());
        }
        if (imageRequest.getSizeResolver() == this.initialRequest.getSizeResolver()) {
            return;
        }
        throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's size resolver. Use `Interceptor.Chain.withSize` instead.").toString());
    }

    private final RealInterceptorChain copy(int i, ImageRequest imageRequest, Size size) {
        return new RealInterceptorChain(this.initialRequest, this.interceptors, i, imageRequest, size, this.eventListener, this.isPlaceholderCached);
    }

    static /* synthetic */ RealInterceptorChain copy$default(RealInterceptorChain realInterceptorChain, int i, ImageRequest imageRequest, Size size, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = realInterceptorChain.index;
        }
        if ((i2 & 2) != 0) {
            imageRequest = realInterceptorChain.getRequest();
        }
        if ((i2 & 4) != 0) {
            size = realInterceptorChain.getSize();
        }
        return realInterceptorChain.copy(i, imageRequest, size);
    }

    public final EventListener getEventListener() {
        return this.eventListener;
    }

    @Override // coil.intercept.Interceptor.Chain
    public ImageRequest getRequest() {
        return this.request;
    }

    @Override // coil.intercept.Interceptor.Chain
    public Size getSize() {
        return this.size;
    }

    public final boolean isPlaceholderCached() {
        return this.isPlaceholderCached;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object proceed(coil.request.ImageRequest r12, kotlin.coroutines.Continuation r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof coil.intercept.RealInterceptorChain$proceed$1
            if (r0 == 0) goto L13
            r0 = r13
            coil.intercept.RealInterceptorChain$proceed$1 r0 = (coil.intercept.RealInterceptorChain$proceed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.RealInterceptorChain$proceed$1 r0 = new coil.intercept.RealInterceptorChain$proceed$1
            r0.<init>(r11, r13)
        L18:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r11 = r0.L$1
            coil.intercept.Interceptor r11 = (coil.intercept.Interceptor) r11
            java.lang.Object r12 = r0.L$0
            coil.intercept.RealInterceptorChain r12 = (coil.intercept.RealInterceptorChain) r12
            kotlin.ResultKt.throwOnFailure(r13)
            r10 = r13
            r13 = r11
            r11 = r12
            r12 = r10
            goto L74
        L35:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3d:
            kotlin.ResultKt.throwOnFailure(r13)
            int r13 = r11.index
            if (r13 <= 0) goto L50
            java.util.List r2 = r11.interceptors
            int r13 = r13 - r3
            java.lang.Object r13 = r2.get(r13)
            coil.intercept.Interceptor r13 = (coil.intercept.Interceptor) r13
            r11.checkRequest(r12, r13)
        L50:
            java.util.List r13 = r11.interceptors
            int r2 = r11.index
            java.lang.Object r13 = r13.get(r2)
            coil.intercept.Interceptor r13 = (coil.intercept.Interceptor) r13
            int r2 = r11.index
            int r5 = r2 + 1
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r11
            r6 = r12
            coil.intercept.RealInterceptorChain r12 = copy$default(r4, r5, r6, r7, r8, r9)
            r0.L$0 = r11
            r0.L$1 = r13
            r0.label = r3
            java.lang.Object r12 = r13.intercept(r12, r0)
            if (r12 != r1) goto L74
            return r1
        L74:
            coil.request.ImageResult r12 = (coil.request.ImageResult) r12
            coil.request.ImageRequest r0 = r12.getRequest()
            r11.checkRequest(r0, r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.RealInterceptorChain.proceed(coil.request.ImageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
