package kotlin.reflect;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class KClasses {
    public static final Object cast(KClass kClass, Object obj) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        if (kClass.isInstance(obj)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.reflect.KClasses.cast");
            return obj;
        }
        throw new ClassCastException("Value cannot be cast to " + kClass.getQualifiedName());
    }
}
