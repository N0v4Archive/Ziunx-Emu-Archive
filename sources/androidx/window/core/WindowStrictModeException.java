package androidx.window.core;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class WindowStrictModeException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowStrictModeException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
