package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.extensions.ExtensionWindowBackend;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes.dex */
public interface WindowInfoTracker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* loaded from: classes.dex */
    public static final class Companion {
        private static final boolean DEBUG = false;
        private static WindowInfoTrackerDecorator decorator;
        private static final Lazy extensionBackend$delegate;
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = Reflection.getOrCreateKotlinClass(WindowInfoTracker.class).getSimpleName();

        static {
            Lazy lazy;
            lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.window.layout.WindowInfoTracker$Companion$extensionBackend$2
                @Override // kotlin.jvm.functions.Function0
                public final WindowBackend invoke() {
                    boolean z;
                    String str;
                    WindowLayoutComponent windowLayoutComponent;
                    try {
                        ClassLoader loader = WindowInfoTracker.class.getClassLoader();
                        SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = loader != null ? new SafeWindowLayoutComponentProvider(loader, new ConsumerAdapter(loader)) : null;
                        if (safeWindowLayoutComponentProvider == null || (windowLayoutComponent = safeWindowLayoutComponentProvider.getWindowLayoutComponent()) == null) {
                            return null;
                        }
                        ExtensionWindowBackend.Companion companion = ExtensionWindowBackend.Companion;
                        Intrinsics.checkNotNullExpressionValue(loader, "loader");
                        return companion.newInstance(windowLayoutComponent, new ConsumerAdapter(loader));
                    } catch (Throwable unused) {
                        z = WindowInfoTracker.Companion.DEBUG;
                        if (!z) {
                            return null;
                        }
                        str = WindowInfoTracker.Companion.TAG;
                        Log.d(str, "Failed to load WindowExtensions");
                        return null;
                    }
                }
            });
            extensionBackend$delegate = lazy;
            decorator = EmptyDecorator.INSTANCE;
        }

        private Companion() {
        }

        public final WindowBackend getExtensionBackend$window_release() {
            return (WindowBackend) extensionBackend$delegate.getValue();
        }

        public final WindowInfoTracker getOrCreate(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WindowBackend extensionBackend$window_release = getExtensionBackend$window_release();
            if (extensionBackend$window_release == null) {
                extensionBackend$window_release = SidecarWindowBackend.Companion.getInstance(context);
            }
            return decorator.decorate(new WindowInfoTrackerImpl(WindowMetricsCalculatorCompat.INSTANCE, extensionBackend$window_release));
        }
    }

    Flow windowLayoutInfo(Activity activity);
}
