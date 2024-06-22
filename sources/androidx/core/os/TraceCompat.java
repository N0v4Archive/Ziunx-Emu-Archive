package androidx.core.os;

import android.os.Trace;

/* loaded from: classes.dex */
public abstract class TraceCompat {

    /* loaded from: classes.dex */
    static class Api18Impl {
        static void beginSection(String str) {
            Trace.beginSection(str);
        }

        static void endSection() {
            Trace.endSection();
        }
    }

    public static void beginSection(String str) {
        Api18Impl.beginSection(str);
    }

    public static void endSection() {
        Api18Impl.endSection();
    }
}
