package kotlin.jvm.internal;

import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class ArrayIteratorKt {
    public static final Iterator iterator(Object[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return new ArrayIterator(array);
    }
}
