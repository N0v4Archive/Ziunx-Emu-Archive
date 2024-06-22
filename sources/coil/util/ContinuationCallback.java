package coil.util;

import java.io.IOException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: classes.dex */
final class ContinuationCallback implements Callback, Function1 {
    private final Call call;
    private final CancellableContinuation continuation;

    public ContinuationCallback(Call call, CancellableContinuation cancellableContinuation) {
        this.call = call;
        this.continuation = cancellableContinuation;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public void invoke(Throwable th) {
        try {
            this.call.cancel();
        } catch (Throwable unused) {
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        if (call.isCanceled()) {
            return;
        }
        CancellableContinuation cancellableContinuation = this.continuation;
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m45constructorimpl(ResultKt.createFailure(iOException)));
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        this.continuation.resumeWith(Result.m45constructorimpl(response));
    }
}
