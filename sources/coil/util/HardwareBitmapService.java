package coil.util;

import coil.size.Size;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public abstract class HardwareBitmapService {
    private HardwareBitmapService() {
    }

    public /* synthetic */ HardwareBitmapService(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean allowHardwareMainThread(Size size);

    public abstract boolean allowHardwareWorkerThread();
}
