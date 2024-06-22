package coil.request;

import android.view.View;
import kotlinx.coroutines.Deferred;

/* loaded from: classes.dex */
public final class ViewTargetDisposable implements Disposable {
    private volatile Deferred job;
    private final View view;

    public ViewTargetDisposable(View view, Deferred deferred) {
        this.view = view;
        this.job = deferred;
    }

    public void setJob(Deferred deferred) {
        this.job = deferred;
    }
}
