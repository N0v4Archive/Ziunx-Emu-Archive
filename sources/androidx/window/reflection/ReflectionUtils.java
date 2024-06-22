package androidx.window.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* loaded from: classes.dex */
public final class ReflectionUtils {
    public static final ReflectionUtils INSTANCE = new ReflectionUtils();

    private ReflectionUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
    
        if (r4 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        r4 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
    
        r5.append(r4);
        android.util.Log.e("ReflectionGuard", r5.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        if (r4 != null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean validateReflection$window_release(java.lang.String r4, kotlin.jvm.functions.Function0 r5) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "ReflectionGuard"
            java.lang.String r2 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r2)
            r2 = 0
            java.lang.Object r5 = r5.invoke()     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            boolean r5 = r5.booleanValue()     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            if (r5 != 0) goto L1b
            if (r4 == 0) goto L1b
            android.util.Log.e(r1, r4)     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
        L1b:
            r2 = r5
            goto L41
        L1d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = "NoSuchMethod: "
            r5.append(r3)
            if (r4 != 0) goto L37
            goto L36
        L2a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = "ClassNotFound: "
            r5.append(r3)
            if (r4 != 0) goto L37
        L36:
            r4 = r0
        L37:
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.e(r1, r4)
        L41:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.reflection.ReflectionUtils.validateReflection$window_release(java.lang.String, kotlin.jvm.functions.Function0):boolean");
    }

    public final boolean checkIsPresent$window_release(Function0 classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        try {
            classLoader.invoke();
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public final boolean doesReturn$window_release(Method method, Class clazz) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return method.getReturnType().equals(clazz);
    }

    public final boolean doesReturn$window_release(Method method, KClass clazz) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return doesReturn$window_release(method, JvmClassMappingKt.getJavaClass(clazz));
    }

    public final boolean isPublic$window_release(Method method) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        return Modifier.isPublic(method.getModifiers());
    }
}
