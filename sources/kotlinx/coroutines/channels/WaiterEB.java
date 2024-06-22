package kotlinx.coroutines.channels;

import kotlinx.coroutines.Waiter;

/* loaded from: classes.dex */
final class WaiterEB {
    public final Waiter waiter;

    public WaiterEB(Waiter waiter) {
        this.waiter = waiter;
    }

    public String toString() {
        return "WaiterEB(" + this.waiter + ')';
    }
}
