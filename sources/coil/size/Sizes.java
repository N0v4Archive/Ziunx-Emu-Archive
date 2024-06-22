package coil.size;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: coil.size.-Sizes, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class Sizes {
    public static final boolean isOriginal(Size size) {
        return Intrinsics.areEqual(size, Size.ORIGINAL);
    }
}
