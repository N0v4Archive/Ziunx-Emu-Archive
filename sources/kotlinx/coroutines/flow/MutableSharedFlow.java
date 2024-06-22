package kotlinx.coroutines.flow;

/* loaded from: classes.dex */
public interface MutableSharedFlow extends SharedFlow, FlowCollector {
    StateFlow getSubscriptionCount();

    void resetReplayCache();

    boolean tryEmit(Object obj);
}
