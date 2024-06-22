package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class ActivityResultContract {

    /* loaded from: classes.dex */
    public static final class SynchronousResult {
        private final Object value;

        public SynchronousResult(Object obj) {
            this.value = obj;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    public abstract Intent createIntent(Context context, Object obj);

    public SynchronousResult getSynchronousResult(Context context, Object obj) {
        Intrinsics.checkNotNullParameter(context, "context");
        return null;
    }

    public abstract Object parseResult(int i, Intent intent);
}
