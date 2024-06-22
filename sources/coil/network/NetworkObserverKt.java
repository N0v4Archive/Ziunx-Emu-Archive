package coil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.core.content.ContextCompat;
import coil.network.NetworkObserver;
import coil.util.Contexts;
import coil.util.Logger;
import coil.util.Logs;

/* loaded from: classes.dex */
public abstract class NetworkObserverKt {
    public static final NetworkObserver NetworkObserver(Context context, NetworkObserver.Listener listener, Logger logger) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
        if (connectivityManager == null || !Contexts.isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            if (logger != null && logger.getLevel() <= 5) {
                logger.log("NetworkObserver", 5, "Unable to register network observer.", null);
            }
            return new EmptyNetworkObserver();
        }
        try {
            return new RealNetworkObserver(connectivityManager, listener);
        } catch (Exception e) {
            if (logger != null) {
                Logs.log(logger, "NetworkObserver", new RuntimeException("Failed to register network observer.", e));
            }
            return new EmptyNetworkObserver();
        }
    }
}
