package androidx.window;

import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class SafeWindowExtensionsProvider {
    private final ClassLoader loader;

    public SafeWindowExtensionsProvider(ClassLoader loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        this.loader = loader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class getWindowExtensionsProviderClass() {
        Class<?> loadClass = this.loader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
        return loadClass;
    }

    private final boolean isWindowExtensionsPresent() {
        return ReflectionUtils.INSTANCE.checkIsPresent$window_release(new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsPresent$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Class invoke() {
                ClassLoader classLoader;
                classLoader = SafeWindowExtensionsProvider.this.loader;
                Class<?> loadClass = classLoader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
                Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
                return loadClass;
            }
        });
    }

    public final Class getWindowExtensionsClass$window_release() {
        Class<?> loadClass = this.loader.loadClass("androidx.window.extensions.WindowExtensions");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE….WINDOW_EXTENSIONS_CLASS)");
        return loadClass;
    }

    public final boolean isWindowExtensionsValid$window_release() {
        return isWindowExtensionsPresent() && ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Class windowExtensionsProviderClass;
                windowExtensionsProviderClass = SafeWindowExtensionsProvider.this.getWindowExtensionsProviderClass();
                boolean z = false;
                Method getWindowExtensionsMethod = windowExtensionsProviderClass.getDeclaredMethod("getWindowExtensions", new Class[0]);
                Class windowExtensionsClass$window_release = SafeWindowExtensionsProvider.this.getWindowExtensionsClass$window_release();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getWindowExtensionsMethod, "getWindowExtensionsMethod");
                if (reflectionUtils.doesReturn$window_release(getWindowExtensionsMethod, windowExtensionsClass$window_release) && reflectionUtils.isPublic$window_release(getWindowExtensionsMethod)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }
}
