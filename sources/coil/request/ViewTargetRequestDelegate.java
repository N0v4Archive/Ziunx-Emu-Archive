package coil.request;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import coil.ImageLoader;
import coil.target.ViewTarget;
import coil.util.Lifecycles;
import coil.util.Utils;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.Job;

/* loaded from: classes.dex */
public final class ViewTargetRequestDelegate extends RequestDelegate {
    private final ImageLoader imageLoader;
    private final ImageRequest initialRequest;
    private final Job job;
    private final Lifecycle lifecycle;
    private final ViewTarget target;

    public ViewTargetRequestDelegate(ImageLoader imageLoader, ImageRequest imageRequest, ViewTarget viewTarget, Lifecycle lifecycle, Job job) {
        super(null);
        this.imageLoader = imageLoader;
        this.initialRequest = imageRequest;
        this.target = viewTarget;
        this.lifecycle = lifecycle;
        this.job = job;
    }

    @Override // coil.request.RequestDelegate
    public void assertActive() {
        if (this.target.getView().isAttachedToWindow()) {
            return;
        }
        Utils.getRequestManager(this.target.getView()).setRequest(this);
        throw new CancellationException("'ViewTarget.view' must be attached to a window.");
    }

    public void dispose() {
        Job.DefaultImpls.cancel$default(this.job, null, 1, null);
        ViewTarget viewTarget = this.target;
        if (viewTarget instanceof LifecycleObserver) {
            this.lifecycle.removeObserver((LifecycleObserver) viewTarget);
        }
        this.lifecycle.removeObserver(this);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Utils.getRequestManager(this.target.getView()).dispose();
    }

    public final void restart() {
        this.imageLoader.enqueue(this.initialRequest);
    }

    @Override // coil.request.RequestDelegate
    public void start() {
        this.lifecycle.addObserver(this);
        ViewTarget viewTarget = this.target;
        if (viewTarget instanceof LifecycleObserver) {
            Lifecycles.removeAndAddObserver(this.lifecycle, (LifecycleObserver) viewTarget);
        }
        Utils.getRequestManager(this.target.getView()).setRequest(this);
    }
}
