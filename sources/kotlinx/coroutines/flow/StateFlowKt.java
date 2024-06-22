package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public abstract class StateFlowKt {
    private static final Symbol NONE = new Symbol("NONE");
    private static final Symbol PENDING = new Symbol("PENDING");

    public static final MutableStateFlow MutableStateFlow(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(obj);
    }

    public static final Flow fuseStateFlow(StateFlow stateFlow, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        boolean z = false;
        if (i >= 0 && i < 2) {
            z = true;
        }
        return ((z || i == -2) && bufferOverflow == BufferOverflow.DROP_OLDEST) ? stateFlow : SharedFlowKt.fuseSharedFlow(stateFlow, coroutineContext, i, bufferOverflow);
    }
}
