package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import androidx.window.layout.WindowLayoutInfo;

/* loaded from: classes.dex */
public interface ExtensionInterfaceCompat {

    /* loaded from: classes.dex */
    public interface ExtensionCallbackInterface {
        void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo);
    }

    void onWindowLayoutChangeListenerAdded(Activity activity);

    void onWindowLayoutChangeListenerRemoved(Activity activity);

    void setExtensionCallback(ExtensionCallbackInterface extensionCallbackInterface);
}
