package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public MutableCreationExtras(CreationExtras initialExtras) {
        Intrinsics.checkNotNullParameter(initialExtras, "initialExtras");
        getMap$lifecycle_viewmodel_release().putAll(initialExtras.getMap$lifecycle_viewmodel_release());
    }

    public /* synthetic */ MutableCreationExtras(CreationExtras creationExtras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CreationExtras.Empty.INSTANCE : creationExtras);
    }

    @Override // androidx.lifecycle.viewmodel.CreationExtras
    public Object get(CreationExtras.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getMap$lifecycle_viewmodel_release().get(key);
    }

    public final void set(CreationExtras.Key key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        getMap$lifecycle_viewmodel_release().put(key, obj);
    }
}
