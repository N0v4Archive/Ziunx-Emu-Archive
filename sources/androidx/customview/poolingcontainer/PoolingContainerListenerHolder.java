package androidx.customview.poolingcontainer;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PoolingContainerListenerHolder {
    private final ArrayList listeners = new ArrayList();

    public final void onRelease() {
        int lastIndex;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this.listeners);
        if (-1 >= lastIndex) {
            return;
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.listeners.get(lastIndex));
        throw null;
    }
}
