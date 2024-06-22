package coil.request;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlinx.coroutines.Job;

/* loaded from: classes.dex */
public final class BaseRequestDelegate extends RequestDelegate {
    private final Job job;
    private final Lifecycle lifecycle;

    public BaseRequestDelegate(Lifecycle lifecycle, Job job) {
        super(null);
        this.lifecycle = lifecycle;
        this.job = job;
    }

    @Override // coil.request.RequestDelegate
    public void complete() {
        this.lifecycle.removeObserver(this);
    }

    public void dispose() {
        Job.DefaultImpls.cancel$default(this.job, null, 1, null);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        dispose();
    }

    @Override // coil.request.RequestDelegate
    public void start() {
        this.lifecycle.addObserver(this);
    }
}
