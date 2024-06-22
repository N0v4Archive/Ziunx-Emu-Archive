package kotlinx.coroutines.channels;

import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class ClosedReceiveChannelException extends NoSuchElementException {
    public ClosedReceiveChannelException(String str) {
        super(str);
    }
}
