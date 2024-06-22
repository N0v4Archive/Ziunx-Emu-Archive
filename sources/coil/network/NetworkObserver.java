package coil.network;

/* loaded from: classes.dex */
public interface NetworkObserver {

    /* loaded from: classes.dex */
    public interface Listener {
        void onConnectivityChange(boolean z);
    }

    boolean isOnline();

    void shutdown();
}
