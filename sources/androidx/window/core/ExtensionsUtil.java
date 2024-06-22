package androidx.window.core;

import android.util.Log;
import androidx.window.extensions.WindowExtensionsProvider;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes.dex */
public final class ExtensionsUtil {
    public static final ExtensionsUtil INSTANCE = new ExtensionsUtil();
    private static final String TAG = Reflection.getOrCreateKotlinClass(ExtensionsUtil.class).getSimpleName();

    private ExtensionsUtil() {
    }

    public final int getSafeVendorApiLevel() {
        String str;
        String str2;
        try {
            return WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
        } catch (NoClassDefFoundError unused) {
            if (BuildConfig.INSTANCE.getVerificationMode() != VerificationMode.LOG) {
                return 0;
            }
            str = TAG;
            str2 = "Embedding extension version not found";
            Log.d(str, str2);
            return 0;
        } catch (UnsupportedOperationException unused2) {
            if (BuildConfig.INSTANCE.getVerificationMode() != VerificationMode.LOG) {
                return 0;
            }
            str = TAG;
            str2 = "Stub Extension";
            Log.d(str, str2);
            return 0;
        }
    }
}
