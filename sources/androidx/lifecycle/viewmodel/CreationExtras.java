package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class CreationExtras {
    private final Map map = new LinkedHashMap();

    /* loaded from: classes.dex */
    public static final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public Object get(Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }
    }

    /* loaded from: classes.dex */
    public interface Key {
    }

    public abstract Object get(Key key);

    public final Map getMap$lifecycle_viewmodel_release() {
        return this.map;
    }
}
