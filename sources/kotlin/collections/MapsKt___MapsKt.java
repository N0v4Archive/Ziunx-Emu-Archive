package kotlin.collections;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
    public static Sequence asSequence(Map map) {
        Sequence asSequence;
        Intrinsics.checkNotNullParameter(map, "<this>");
        asSequence = CollectionsKt___CollectionsKt.asSequence(map.entrySet());
        return asSequence;
    }
}
