package coil.intercept;

import coil.EventListener;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.memory.MemoryCache;
import coil.memory.MemoryCacheService;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.SuccessResult;
import coil.util.Utils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class EngineInterceptor$intercept$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ MemoryCache.Key $cacheKey;
    final /* synthetic */ Interceptor.Chain $chain;
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ Options $options;
    final /* synthetic */ ImageRequest $request;
    int label;
    final /* synthetic */ EngineInterceptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$intercept$2(EngineInterceptor engineInterceptor, ImageRequest imageRequest, Object obj, Options options, EventListener eventListener, MemoryCache.Key key, Interceptor.Chain chain, Continuation continuation) {
        super(2, continuation);
        this.this$0 = engineInterceptor;
        this.$request = imageRequest;
        this.$mappedData = obj;
        this.$options = options;
        this.$eventListener = eventListener;
        this.$cacheKey = key;
        this.$chain = chain;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EngineInterceptor$intercept$2(this.this$0, this.$request, this.$mappedData, this.$options, this.$eventListener, this.$cacheKey, this.$chain, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((EngineInterceptor$intercept$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        MemoryCacheService memoryCacheService;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            EngineInterceptor engineInterceptor = this.this$0;
            ImageRequest imageRequest = this.$request;
            Object obj2 = this.$mappedData;
            Options options = this.$options;
            EventListener eventListener = this.$eventListener;
            this.label = 1;
            obj = engineInterceptor.execute(imageRequest, obj2, options, eventListener, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        EngineInterceptor.ExecuteResult executeResult = (EngineInterceptor.ExecuteResult) obj;
        memoryCacheService = this.this$0.memoryCacheService;
        return new SuccessResult(executeResult.getDrawable(), this.$request, executeResult.getDataSource(), memoryCacheService.setCacheValue(this.$cacheKey, this.$request, executeResult) ? this.$cacheKey : null, executeResult.getDiskCacheKey(), executeResult.isSampled(), Utils.isPlaceholderCached(this.$chain));
    }
}
