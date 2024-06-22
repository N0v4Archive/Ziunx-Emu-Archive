package androidx.navigation;

import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* loaded from: classes.dex */
public final class NavArgsLazy implements Lazy {
    private final Function0 argumentProducer;
    private NavArgs cached;
    private final KClass navArgsClass;

    public NavArgsLazy(KClass navArgsClass, Function0 argumentProducer) {
        Intrinsics.checkNotNullParameter(navArgsClass, "navArgsClass");
        Intrinsics.checkNotNullParameter(argumentProducer, "argumentProducer");
        this.navArgsClass = navArgsClass;
        this.argumentProducer = argumentProducer;
    }

    @Override // kotlin.Lazy
    public NavArgs getValue() {
        NavArgs navArgs = this.cached;
        if (navArgs != null) {
            return navArgs;
        }
        Bundle bundle = (Bundle) this.argumentProducer.invoke();
        Method method = (Method) NavArgsLazyKt.getMethodMap().get(this.navArgsClass);
        if (method == null) {
            Class javaClass = JvmClassMappingKt.getJavaClass(this.navArgsClass);
            Class[] methodSignature = NavArgsLazyKt.getMethodSignature();
            method = javaClass.getMethod("fromBundle", (Class[]) Arrays.copyOf(methodSignature, methodSignature.length));
            NavArgsLazyKt.getMethodMap().put(this.navArgsClass, method);
            Intrinsics.checkNotNullExpressionValue(method, "navArgsClass.java.getMet…hod\n                    }");
        }
        Object invoke = method.invoke(null, bundle);
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type Args of androidx.navigation.NavArgsLazy");
        NavArgs navArgs2 = (NavArgs) invoke;
        this.cached = navArgs2;
        return navArgs2;
    }
}
