package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
    public static final Key Key = Key.$$INSTANCE;

    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key {
        static final /* synthetic */ Key $$INSTANCE = new Key();

        private Key() {
        }
    }

    void handleException(CoroutineContext coroutineContext, Throwable th);
}
