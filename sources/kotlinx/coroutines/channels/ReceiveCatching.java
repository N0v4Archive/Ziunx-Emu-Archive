package kotlinx.coroutines.channels;

import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ReceiveCatching implements Waiter {
    public final CancellableContinuationImpl cont;

    public ReceiveCatching(CancellableContinuationImpl cancellableContinuationImpl) {
        this.cont = cancellableContinuationImpl;
    }

    @Override // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment segment, int i) {
        this.cont.invokeOnCancellation(segment, i);
    }
}
