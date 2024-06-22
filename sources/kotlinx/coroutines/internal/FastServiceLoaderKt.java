package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED = false;

    static {
        Object m45constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m45constructorimpl = Result.m45constructorimpl(ResultKt.createFailure(th));
        }
        Result.m48isSuccessimpl(m45constructorimpl);
    }

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }
}
