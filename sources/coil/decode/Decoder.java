package coil.decode;

import coil.ImageLoader;
import coil.fetch.SourceResult;
import coil.request.Options;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface Decoder {

    /* loaded from: classes.dex */
    public interface Factory {
        Decoder create(SourceResult sourceResult, Options options, ImageLoader imageLoader);
    }

    Object decode(Continuation continuation);
}
