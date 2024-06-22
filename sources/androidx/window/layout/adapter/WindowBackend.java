package androidx.window.layout.adapter;

import android.content.Context;
import androidx.core.util.Consumer;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface WindowBackend {
    void registerLayoutChangeCallback(Context context, Executor executor, Consumer consumer);

    void unregisterLayoutChangeCallback(Consumer consumer);
}
