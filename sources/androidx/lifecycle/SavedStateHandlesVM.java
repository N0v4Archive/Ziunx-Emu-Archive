package androidx.lifecycle;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class SavedStateHandlesVM extends ViewModel {
    private final Map handles = new LinkedHashMap();

    public final Map getHandles() {
        return this.handles;
    }
}
