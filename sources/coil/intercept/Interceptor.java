package coil.intercept;

import coil.request.ImageRequest;
import coil.size.Size;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Interceptor {

    /* loaded from: classes.dex */
    public interface Chain {
        ImageRequest getRequest();

        Size getSize();
    }

    Object intercept(Chain chain, Continuation continuation);
}
