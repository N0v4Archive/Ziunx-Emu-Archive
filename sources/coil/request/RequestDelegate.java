package coil.request;

import androidx.lifecycle.DefaultLifecycleObserver;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public abstract class RequestDelegate implements DefaultLifecycleObserver {
    private RequestDelegate() {
    }

    public /* synthetic */ RequestDelegate(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public void assertActive() {
    }

    public void complete() {
    }

    public void start() {
    }
}
