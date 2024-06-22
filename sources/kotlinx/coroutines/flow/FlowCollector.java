package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface FlowCollector {
    Object emit(Object obj, Continuation continuation);
}
