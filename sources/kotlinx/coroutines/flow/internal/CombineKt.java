package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes.dex */
public abstract class CombineKt {
    public static final Object combineInternal(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
        Object coroutine_suspended;
        Object flowScope = FlowCoroutineKt.flowScope(new CombineKt$combineInternal$2(flowArr, function0, function3, flowCollector, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return flowScope == coroutine_suspended ? flowScope : Unit.INSTANCE;
    }
}
