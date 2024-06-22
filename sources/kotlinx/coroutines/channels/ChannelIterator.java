package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface ChannelIterator {
    Object hasNext(Continuation continuation);

    Object next();
}
