package kotlin.reflect;

/* loaded from: classes.dex */
public interface KClass extends KDeclarationContainer {
    String getQualifiedName();

    String getSimpleName();

    boolean isInstance(Object obj);
}
