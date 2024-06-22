package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.EmptyIterator;

/* loaded from: classes.dex */
final class EmptySequence implements Sequence, DropTakeSequence {
    public static final EmptySequence INSTANCE = new EmptySequence();

    private EmptySequence() {
    }

    @Override // kotlin.sequences.DropTakeSequence
    public EmptySequence drop(int i) {
        return INSTANCE;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }
}
