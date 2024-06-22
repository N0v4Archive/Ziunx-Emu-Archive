package androidx.core.view;

import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* loaded from: classes.dex */
public abstract class ViewGroupKt {
    public static final Sequence getChildren(final ViewGroup viewGroup) {
        return new Sequence() { // from class: androidx.core.view.ViewGroupKt$children$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return ViewGroupKt.iterator(viewGroup);
            }
        };
    }

    public static final Sequence getDescendants(ViewGroup viewGroup) {
        Sequence sequence;
        sequence = SequencesKt__SequenceBuilderKt.sequence(new ViewGroupKt$descendants$1(viewGroup, null));
        return sequence;
    }

    public static final Iterator iterator(ViewGroup viewGroup) {
        return new ViewGroupKt$iterator$1(viewGroup);
    }
}
