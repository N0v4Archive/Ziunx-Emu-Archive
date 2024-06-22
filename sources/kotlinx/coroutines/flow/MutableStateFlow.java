package kotlinx.coroutines.flow;

/* loaded from: classes.dex */
public interface MutableStateFlow extends StateFlow, MutableSharedFlow {
    @Override // kotlinx.coroutines.flow.StateFlow
    Object getValue();

    void setValue(Object obj);
}
