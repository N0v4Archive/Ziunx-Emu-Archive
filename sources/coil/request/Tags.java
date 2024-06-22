package coil.request;

import coil.util.Collections;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class Tags {
    public static final Companion Companion = new Companion(null);
    public static final Tags EMPTY;
    private final Map tags;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Tags from(Map map) {
            return new Tags(Collections.toImmutableMap(map), null);
        }
    }

    static {
        Map emptyMap;
        emptyMap = MapsKt__MapsKt.emptyMap();
        EMPTY = new Tags(emptyMap);
    }

    private Tags(Map map) {
        this.tags = map;
    }

    public /* synthetic */ Tags(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    public final Map asMap() {
        return this.tags;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Tags) && Intrinsics.areEqual(this.tags, ((Tags) obj).tags);
    }

    public int hashCode() {
        return this.tags.hashCode();
    }

    public String toString() {
        return "Tags(tags=" + this.tags + ')';
    }
}
