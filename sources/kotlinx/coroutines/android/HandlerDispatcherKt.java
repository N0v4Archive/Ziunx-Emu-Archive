package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class HandlerDispatcherKt {
    public static final HandlerDispatcher Main;
    private static volatile Choreographer choreographer;

    static {
        Object m45constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(new HandlerContext(asHandler(Looper.getMainLooper(), true), null, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(ResultKt.createFailure(th));
        }
        Main = (HandlerDispatcher) (Result.m47isFailureimpl(m45constructorimpl) ? null : m45constructorimpl);
    }

    public static final Handler asHandler(Looper looper, boolean z) {
        if (!z) {
            return new Handler(looper);
        }
        Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) invoke;
    }
}
