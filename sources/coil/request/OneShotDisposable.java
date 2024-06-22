package coil.request;

import kotlinx.coroutines.Deferred;

/* loaded from: classes.dex */
public final class OneShotDisposable implements Disposable {
    private final Deferred job;

    public OneShotDisposable(Deferred deferred) {
        this.job = deferred;
    }
}
