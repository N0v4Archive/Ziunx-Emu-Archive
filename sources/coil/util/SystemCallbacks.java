package coil.util;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import coil.RealImageLoader;
import coil.network.EmptyNetworkObserver;
import coil.network.NetworkObserver;
import coil.network.NetworkObserverKt;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public final class SystemCallbacks implements ComponentCallbacks2, NetworkObserver.Listener {
    public static final Companion Companion = new Companion(null);
    private volatile boolean _isOnline;
    private final AtomicBoolean _isShutdown;
    private final Context context;
    private final WeakReference imageLoader;
    private final NetworkObserver networkObserver;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SystemCallbacks(RealImageLoader realImageLoader, Context context, boolean z) {
        NetworkObserver emptyNetworkObserver;
        this.context = context;
        this.imageLoader = new WeakReference(realImageLoader);
        if (z) {
            realImageLoader.getLogger();
            emptyNetworkObserver = NetworkObserverKt.NetworkObserver(context, this, null);
        } else {
            emptyNetworkObserver = new EmptyNetworkObserver();
        }
        this.networkObserver = emptyNetworkObserver;
        this._isOnline = emptyNetworkObserver.isOnline();
        this._isShutdown = new AtomicBoolean(false);
    }

    public final boolean isOnline() {
        return this._isOnline;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (((RealImageLoader) this.imageLoader.get()) == null) {
            shutdown();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // coil.network.NetworkObserver.Listener
    public void onConnectivityChange(boolean z) {
        Unit unit;
        RealImageLoader realImageLoader = (RealImageLoader) this.imageLoader.get();
        if (realImageLoader != null) {
            realImageLoader.getLogger();
            this._isOnline = z;
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            shutdown();
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        onTrimMemory(80);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        Unit unit;
        RealImageLoader realImageLoader = (RealImageLoader) this.imageLoader.get();
        if (realImageLoader != null) {
            realImageLoader.getLogger();
            realImageLoader.onTrimMemory$coil_base_release(i);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            shutdown();
        }
    }

    public final void register() {
        this.context.registerComponentCallbacks(this);
    }

    public final void shutdown() {
        if (this._isShutdown.getAndSet(true)) {
            return;
        }
        this.context.unregisterComponentCallbacks(this);
        this.networkObserver.shutdown();
    }
}
