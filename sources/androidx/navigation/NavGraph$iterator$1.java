package androidx.navigation;

import androidx.collection.SparseArrayCompat;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class NavGraph$iterator$1 implements Iterator, KMappedMarker {
    private int index = -1;
    final /* synthetic */ NavGraph this$0;
    private boolean wentToNext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavGraph$iterator$1(NavGraph navGraph) {
        this.this$0 = navGraph;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index + 1 < this.this$0.getNodes().size();
    }

    @Override // java.util.Iterator
    public NavDestination next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.wentToNext = true;
        SparseArrayCompat nodes = this.this$0.getNodes();
        int i = this.index + 1;
        this.index = i;
        Object valueAt = nodes.valueAt(i);
        Intrinsics.checkNotNullExpressionValue(valueAt, "nodes.valueAt(++index)");
        return (NavDestination) valueAt;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.wentToNext) {
            throw new IllegalStateException("You must call next() before you can remove an element".toString());
        }
        SparseArrayCompat nodes = this.this$0.getNodes();
        ((NavDestination) nodes.valueAt(this.index)).setParent(null);
        nodes.removeAt(this.index);
        this.index--;
        this.wentToNext = false;
    }
}
