package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface ThreadContextElement extends CoroutineContext.Element {
    void restoreThreadContext(CoroutineContext coroutineContext, Object obj);

    Object updateThreadContext(CoroutineContext coroutineContext);
}
