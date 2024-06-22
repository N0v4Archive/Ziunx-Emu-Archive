package coil.fetch;

import coil.ImageLoader;
import coil.request.Options;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Fetcher {

    /* loaded from: classes.dex */
    public interface Factory {
        Fetcher create(Object obj, Options options, ImageLoader imageLoader);
    }

    Object fetch(Continuation continuation);
}
