package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class GeneratorSequence$iterator$1 implements Iterator, KMappedMarker {
    private Object nextItem;
    private int nextState = -2;
    final /* synthetic */ GeneratorSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0 = generatorSequence;
    }

    private final void calcNext() {
        Function1 function1;
        Object invoke;
        Function0 function0;
        if (this.nextState == -2) {
            function0 = this.this$0.getInitialValue;
            invoke = function0.invoke();
        } else {
            function1 = this.this$0.getNextValue;
            Object obj = this.nextItem;
            Intrinsics.checkNotNull(obj);
            invoke = function1.invoke(obj);
        }
        this.nextItem = invoke;
        this.nextState = invoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState < 0) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.nextItem;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
        this.nextState = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
