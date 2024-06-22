package androidx.core.view;

import android.view.View;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* loaded from: classes.dex */
public abstract class ViewKt {
    public static final Sequence getAllViews(View view) {
        Sequence sequence;
        sequence = SequencesKt__SequenceBuilderKt.sequence(new ViewKt$allViews$1(view, null));
        return sequence;
    }
}
