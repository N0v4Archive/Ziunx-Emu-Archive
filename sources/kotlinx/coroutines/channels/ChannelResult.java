package kotlinx.coroutines.channels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ChannelResult {
    public static final Companion Companion = new Companion(null);
    private static final Failed failed = new Failed();
    private final Object holder;

    /* loaded from: classes.dex */
    public static final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: closed-JP2dKIU, reason: not valid java name */
        public final Object m67closedJP2dKIU(Throwable th) {
            return ChannelResult.m58constructorimpl(new Closed(th));
        }

        /* renamed from: failure-PtdJZtk, reason: not valid java name */
        public final Object m68failurePtdJZtk() {
            return ChannelResult.m58constructorimpl(ChannelResult.failed);
        }

        /* renamed from: success-JP2dKIU, reason: not valid java name */
        public final Object m69successJP2dKIU(Object obj) {
            return ChannelResult.m58constructorimpl(obj);
        }
    }

    /* loaded from: classes.dex */
    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    private /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ChannelResult m57boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static Object m58constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m59equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).m66unboximpl());
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m60exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.cause;
        }
        return null;
    }

    /* renamed from: getOrNull-impl, reason: not valid java name */
    public static final Object m61getOrNullimpl(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m62hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m63isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m64isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m65toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m59equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m62hashCodeimpl(this.holder);
    }

    public String toString() {
        return m65toStringimpl(this.holder);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m66unboximpl() {
        return this.holder;
    }
}
