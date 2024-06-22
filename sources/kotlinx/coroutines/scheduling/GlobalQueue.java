package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.internal.LockFreeTaskQueue;

/* loaded from: classes.dex */
public final class GlobalQueue extends LockFreeTaskQueue {
    public GlobalQueue() {
        super(false);
    }
}
