package kotlinx.serialization.internal;

import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorKt;
import kotlinx.serialization.descriptors.SerialKind;

/* loaded from: classes.dex */
public abstract class PluginGeneratedSerialDescriptorKt {
    public static final int hashCodeImpl(SerialDescriptor serialDescriptor, SerialDescriptor[] typeParams) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(typeParams, "typeParams");
        int hashCode = (serialDescriptor.getSerialName().hashCode() * 31) + Arrays.hashCode(typeParams);
        Iterable elementDescriptors = SerialDescriptorKt.getElementDescriptors(serialDescriptor);
        Iterator it = elementDescriptors.iterator();
        int i = 1;
        int i2 = 1;
        while (true) {
            int i3 = 0;
            if (!it.hasNext()) {
                break;
            }
            int i4 = i2 * 31;
            String serialName = ((SerialDescriptor) it.next()).getSerialName();
            if (serialName != null) {
                i3 = serialName.hashCode();
            }
            i2 = i4 + i3;
        }
        Iterator it2 = elementDescriptors.iterator();
        while (it2.hasNext()) {
            int i5 = i * 31;
            SerialKind kind = ((SerialDescriptor) it2.next()).getKind();
            i = i5 + (kind != null ? kind.hashCode() : 0);
        }
        return (((hashCode * 31) + i2) * 31) + i;
    }
}
