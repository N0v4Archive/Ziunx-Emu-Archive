package androidx.window.layout.adapter.extensions;

import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ExtensionWindowBackendApi0 implements WindowBackend {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerLayoutChangeCallback$lambda$0(Consumer callback) {
        List emptyList;
        Intrinsics.checkNotNullParameter(callback, "$callback");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        callback.accept(new WindowLayoutInfo(emptyList));
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, final Consumer callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        executor.execute(new Runnable() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExtensionWindowBackendApi0.registerLayoutChangeCallback$lambda$0(Consumer.this);
            }
        });
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
    }
}
