package kotlinx.serialization.descriptors;

import java.util.List;

/* loaded from: classes.dex */
public interface SerialDescriptor {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static boolean isInline(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static boolean isNullable(SerialDescriptor serialDescriptor) {
            return false;
        }
    }

    List getElementAnnotations(int i);

    SerialDescriptor getElementDescriptor(int i);

    int getElementIndex(String str);

    String getElementName(int i);

    int getElementsCount();

    SerialKind getKind();

    String getSerialName();

    boolean isElementOptional(int i);

    boolean isInline();

    boolean isNullable();
}
