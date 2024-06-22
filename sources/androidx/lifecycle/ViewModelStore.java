package androidx.lifecycle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class ViewModelStore {
    private final Map map = new LinkedHashMap();

    public final void clear() {
        Iterator it = this.map.values().iterator();
        while (it.hasNext()) {
            ((ViewModel) it.next()).clear();
        }
        this.map.clear();
    }

    public final ViewModel get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (ViewModel) this.map.get(key);
    }

    public final Set keys() {
        return new HashSet(this.map.keySet());
    }

    public final void put(String key, ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        ViewModel viewModel2 = (ViewModel) this.map.put(key, viewModel);
        if (viewModel2 != null) {
            viewModel2.onCleared();
        }
    }
}
