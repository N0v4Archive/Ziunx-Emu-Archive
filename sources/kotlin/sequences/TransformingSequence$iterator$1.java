package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class TransformingSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    final /* synthetic */ TransformingSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransformingSequence$iterator$1(TransformingSequence transformingSequence) {
        Sequence sequence;
        this.this$0 = transformingSequence;
        sequence = transformingSequence.sequence;
        this.iterator = sequence.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        Function1 function1;
        function1 = this.this$0.transformer;
        return function1.invoke(this.iterator.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
