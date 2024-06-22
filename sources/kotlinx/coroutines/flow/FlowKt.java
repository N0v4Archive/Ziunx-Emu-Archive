package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;

/* loaded from: classes.dex */
public abstract class FlowKt {
    public static final SharedFlow asSharedFlow(MutableSharedFlow mutableSharedFlow) {
        return FlowKt__ShareKt.asSharedFlow(mutableSharedFlow);
    }

    public static final StateFlow asStateFlow(MutableStateFlow mutableStateFlow) {
        return FlowKt__ShareKt.asStateFlow(mutableStateFlow);
    }

    public static final Flow buffer(Flow flow, int i, BufferOverflow bufferOverflow) {
        return FlowKt__ContextKt.buffer(flow, i, bufferOverflow);
    }

    public static final Flow callbackFlow(Function2 function2) {
        return FlowKt__BuildersKt.callbackFlow(function2);
    }

    public static final Object collect(Flow flow, Continuation continuation) {
        return FlowKt__CollectKt.collect(flow, continuation);
    }

    public static final Object collectLatest(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__CollectKt.collectLatest(flow, function2, continuation);
    }

    public static final Flow combine(Flow flow, Flow flow2, Flow flow3, Function4 function4) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, function4);
    }

    public static final Flow distinctUntilChanged(Flow flow) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow);
    }

    public static final Flow dropWhile(Flow flow, Function2 function2) {
        return FlowKt__LimitKt.dropWhile(flow, function2);
    }

    public static final Object emitAll(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        return FlowKt__ChannelsKt.emitAll(flowCollector, receiveChannel, continuation);
    }

    public static final void ensureActive(FlowCollector flowCollector) {
        FlowKt__EmittersKt.ensureActive(flowCollector);
    }

    public static final Object first(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__ReduceKt.first(flow, function2, continuation);
    }

    public static final Flow flow(Function2 function2) {
        return FlowKt__BuildersKt.flow(function2);
    }

    public static final Flow flowOf(Object obj) {
        return FlowKt__BuildersKt.flowOf(obj);
    }

    public static final Flow flowOn(Flow flow, CoroutineContext coroutineContext) {
        return FlowKt__ContextKt.flowOn(flow, coroutineContext);
    }

    public static final Flow mapLatest(Flow flow, Function2 function2) {
        return FlowKt__MergeKt.mapLatest(flow, function2);
    }

    public static final StateFlow stateIn(Flow flow, CoroutineScope coroutineScope, SharingStarted sharingStarted, Object obj) {
        return FlowKt__ShareKt.stateIn(flow, coroutineScope, sharingStarted, obj);
    }

    public static final Flow transformLatest(Flow flow, Function3 function3) {
        return FlowKt__MergeKt.transformLatest(flow, function3);
    }
}
