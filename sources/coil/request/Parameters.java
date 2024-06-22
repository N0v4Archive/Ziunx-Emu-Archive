package coil.request;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import coil.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class Parameters implements Iterable, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    public static final Parameters EMPTY = new Parameters();
    private final Map entries;

    /* loaded from: classes.dex */
    public static final class Builder {
        private final Map entries;

        public Builder(Parameters parameters) {
            Map mutableMap;
            mutableMap = MapsKt__MapsKt.toMutableMap(parameters.entries);
            this.entries = mutableMap;
        }

        public final Parameters build() {
            return new Parameters(Collections.toImmutableMap(this.entries), null);
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Parameters() {
        /*
            r1 = this;
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r1.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.request.Parameters.<init>():void");
    }

    private Parameters(Map map) {
        this.entries = map;
    }

    public /* synthetic */ Parameters(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Parameters) && Intrinsics.areEqual(this.entries, ((Parameters) obj).entries);
    }

    public int hashCode() {
        return this.entries.hashCode();
    }

    public final boolean isEmpty() {
        return this.entries.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        Map map = this.entries;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(entry.getValue());
            arrayList.add(TuplesKt.to(str, null));
        }
        return arrayList.iterator();
    }

    public final Map memoryCacheKeys() {
        Map emptyMap;
        if (isEmpty()) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return emptyMap;
        }
        Map map = this.entries;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return linkedHashMap;
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) it.next()).getValue());
        throw null;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "Parameters(entries=" + this.entries + ')';
    }
}
