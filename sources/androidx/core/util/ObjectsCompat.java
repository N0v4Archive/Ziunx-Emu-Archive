package androidx.core.util;

import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ObjectsCompat {

    /* loaded from: classes.dex */
    static class Api19Impl {
        static boolean equals(Object obj, Object obj2) {
            return Objects.equals(obj, obj2);
        }

        static int hash(Object... objArr) {
            return Objects.hash(objArr);
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        return Api19Impl.equals(obj, obj2);
    }

    public static int hash(Object... objArr) {
        return Api19Impl.hash(objArr);
    }

    public static Object requireNonNull(Object obj) {
        obj.getClass();
        return obj;
    }

    public static Object requireNonNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }
}
