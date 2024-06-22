package kotlinx.coroutines.internal;

import _COROUTINE.ArtificialStackFrames;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;

/* loaded from: classes.dex */
public abstract class StackTraceRecoveryKt {
    private static final StackTraceElement ARTIFICIAL_FRAME = new ArtificialStackFrames().coroutineBoundary();
    private static final String baseContinuationImplClassName;
    private static final String stackTraceRecoveryClassName;

    static {
        Object m45constructorimpl;
        Object m45constructorimpl2;
        try {
            Result.Companion companion = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(BaseContinuationImpl.class.getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m46exceptionOrNullimpl(m45constructorimpl) != null) {
            m45constructorimpl = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        baseContinuationImplClassName = (String) m45constructorimpl;
        try {
            m45constructorimpl2 = Result.m45constructorimpl(StackTraceRecoveryKt.class.getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            m45constructorimpl2 = Result.m45constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m46exceptionOrNullimpl(m45constructorimpl2) != null) {
            m45constructorimpl2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        stackTraceRecoveryClassName = (String) m45constructorimpl2;
    }

    public static final Throwable recoverStackTrace(Throwable th) {
        return th;
    }
}
