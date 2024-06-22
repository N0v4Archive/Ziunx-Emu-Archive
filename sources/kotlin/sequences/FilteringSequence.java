package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class FilteringSequence implements Sequence {
    private final Function1 predicate;
    private final boolean sendWhen;
    private final Sequence sequence;

    public FilteringSequence(Sequence sequence, boolean z, Function1 predicate) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        this.sequence = sequence;
        this.sendWhen = z;
        this.predicate = predicate;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new FilteringSequence$iterator$1(this);
    }
}
