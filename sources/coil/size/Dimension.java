package coil.size;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public abstract class Dimension {

    /* loaded from: classes.dex */
    public static final class Pixels extends Dimension {
        public final int px;

        public Pixels(int i) {
            super(null);
            this.px = i;
            if (!(i > 0)) {
                throw new IllegalArgumentException("px must be > 0.".toString());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Pixels) && this.px == ((Pixels) obj).px;
        }

        public int hashCode() {
            return this.px;
        }

        public String toString() {
            return String.valueOf(this.px);
        }
    }

    /* loaded from: classes.dex */
    public static final class Undefined extends Dimension {
        public static final Undefined INSTANCE = new Undefined();

        private Undefined() {
            super(null);
        }

        public String toString() {
            return "Dimension.Undefined";
        }
    }

    private Dimension() {
    }

    public /* synthetic */ Dimension(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
