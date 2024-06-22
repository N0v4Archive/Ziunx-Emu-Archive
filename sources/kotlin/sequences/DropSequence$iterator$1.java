package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class DropSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    private int left;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DropSequence$iterator$1(DropSequence dropSequence) {
        Sequence sequence;
        int i;
        sequence = dropSequence.sequence;
        this.iterator = sequence.iterator();
        i = dropSequence.count;
        this.left = i;
    }

    private final void drop() {
        while (this.left > 0 && this.iterator.hasNext()) {
            this.iterator.next();
            this.left--;
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        drop();
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        drop();
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
