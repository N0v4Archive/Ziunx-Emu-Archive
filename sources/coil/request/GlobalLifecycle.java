package coil.request;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes.dex */
public final class GlobalLifecycle extends Lifecycle {
    public static final GlobalLifecycle INSTANCE = new GlobalLifecycle();
    private static final LifecycleOwner owner = new LifecycleOwner() { // from class: coil.request.GlobalLifecycle$$ExternalSyntheticLambda0
        @Override // androidx.lifecycle.LifecycleOwner
        public final Lifecycle getLifecycle() {
            Lifecycle lifecycle;
            lifecycle = GlobalLifecycle.INSTANCE;
            return lifecycle;
        }
    };

    private GlobalLifecycle() {
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver lifecycleObserver) {
        if (!(lifecycleObserver instanceof DefaultLifecycleObserver)) {
            throw new IllegalArgumentException((lifecycleObserver + " must implement androidx.lifecycle.DefaultLifecycleObserver.").toString());
        }
        DefaultLifecycleObserver defaultLifecycleObserver = (DefaultLifecycleObserver) lifecycleObserver;
        LifecycleOwner lifecycleOwner = owner;
        defaultLifecycleObserver.onCreate(lifecycleOwner);
        defaultLifecycleObserver.onStart(lifecycleOwner);
        defaultLifecycleObserver.onResume(lifecycleOwner);
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State getCurrentState() {
        return Lifecycle.State.RESUMED;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver lifecycleObserver) {
    }

    public String toString() {
        return "coil.request.GlobalLifecycle";
    }
}
