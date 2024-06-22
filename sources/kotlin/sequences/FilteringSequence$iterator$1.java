package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class FilteringSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    private Object nextItem;
    private int nextState;
    final /* synthetic */ FilteringSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        Sequence sequence;
        this.this$0 = filteringSequence;
        sequence = filteringSequence.sequence;
        this.iterator = sequence.iterator();
        this.nextState = -1;
    }

    private final void calcNext() {
        int i;
        Function1 function1;
        boolean z;
        while (true) {
            if (!this.iterator.hasNext()) {
                i = 0;
                break;
            }
            Object next = this.iterator.next();
            function1 = this.this$0.predicate;
            boolean booleanValue = ((Boolean) function1.invoke(next)).booleanValue();
            z = this.this$0.sendWhen;
            if (booleanValue == z) {
                this.nextItem = next;
                i = 1;
                break;
            }
        }
        this.nextState = i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.nextItem;
        this.nextItem = null;
        this.nextState = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
