package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class TakeWhileSequence implements Sequence {
    private final Function1 predicate;
    private final Sequence sequence;

    public TakeWhileSequence(Sequence sequence, Function1 predicate) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new TakeWhileSequence$iterator$1(this);
    }
}
