package coil.intercept;

import coil.ComponentRegistry;
import coil.EventListener;
import coil.fetch.SourceResult;
import coil.request.ImageRequest;
import coil.request.Options;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class EngineInterceptor$execute$executeResult$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $components;
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Ref$ObjectRef $fetchResult;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ Ref$ObjectRef $options;
    final /* synthetic */ ImageRequest $request;
    int label;
    final /* synthetic */ EngineInterceptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$execute$executeResult$1(EngineInterceptor engineInterceptor, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, ImageRequest imageRequest, Object obj, Ref$ObjectRef ref$ObjectRef3, EventListener eventListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = engineInterceptor;
        this.$fetchResult = ref$ObjectRef;
        this.$components = ref$ObjectRef2;
        this.$request = imageRequest;
        this.$mappedData = obj;
        this.$options = ref$ObjectRef3;
        this.$eventListener = eventListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EngineInterceptor$execute$executeResult$1(this.this$0, this.$fetchResult, this.$components, this.$request, this.$mappedData, this.$options, this.$eventListener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((EngineInterceptor$execute$executeResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            EngineInterceptor engineInterceptor = this.this$0;
            SourceResult sourceResult = (SourceResult) this.$fetchResult.element;
            ComponentRegistry componentRegistry = (ComponentRegistry) this.$components.element;
            ImageRequest imageRequest = this.$request;
            Object obj2 = this.$mappedData;
            Options options = (Options) this.$options.element;
            EventListener eventListener = this.$eventListener;
            this.label = 1;
            obj = engineInterceptor.decode(sourceResult, componentRegistry, imageRequest, obj2, options, eventListener, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
