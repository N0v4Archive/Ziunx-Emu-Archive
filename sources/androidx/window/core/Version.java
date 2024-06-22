package androidx.window.core;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes.dex */
public final class Version implements Comparable {
    private static final Version CURRENT;
    public static final Companion Companion = new Companion(null);
    private static final Version UNKNOWN = new Version(0, 0, 0, "");
    private static final Version VERSION_0_1 = new Version(0, 1, 0, "");
    private static final Version VERSION_1_0;
    private final Lazy bigInteger$delegate;
    private final String description;
    private final int major;
    private final int minor;
    private final int patch;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Version getVERSION_0_1() {
            return Version.VERSION_0_1;
        }

        public final Version parse(String str) {
            boolean isBlank;
            String group;
            if (str == null) {
                return null;
            }
            isBlank = StringsKt__StringsJVMKt.isBlank(str);
            if (isBlank) {
                return null;
            }
            Matcher matcher = Pattern.compile("(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?").matcher(str);
            if (!matcher.matches() || (group = matcher.group(1)) == null) {
                return null;
            }
            int parseInt = Integer.parseInt(group);
            String group2 = matcher.group(2);
            if (group2 == null) {
                return null;
            }
            int parseInt2 = Integer.parseInt(group2);
            String group3 = matcher.group(3);
            if (group3 == null) {
                return null;
            }
            int parseInt3 = Integer.parseInt(group3);
            String description = matcher.group(4) != null ? matcher.group(4) : "";
            Intrinsics.checkNotNullExpressionValue(description, "description");
            return new Version(parseInt, parseInt2, parseInt3, description, null);
        }
    }

    static {
        Version version = new Version(1, 0, 0, "");
        VERSION_1_0 = version;
        CURRENT = version;
    }

    private Version(int i, int i2, int i3, String str) {
        Lazy lazy;
        this.major = i;
        this.minor = i2;
        this.patch = i3;
        this.description = str;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.window.core.Version$bigInteger$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final BigInteger invoke() {
                return BigInteger.valueOf(Version.this.getMajor()).shiftLeft(32).or(BigInteger.valueOf(Version.this.getMinor())).shiftLeft(32).or(BigInteger.valueOf(Version.this.getPatch()));
            }
        });
        this.bigInteger$delegate = lazy;
    }

    public /* synthetic */ Version(int i, int i2, int i3, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str);
    }

    private final BigInteger getBigInteger() {
        Object value = this.bigInteger$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-bigInteger>(...)");
        return (BigInteger) value;
    }

    @Override // java.lang.Comparable
    public int compareTo(Version other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBigInteger().compareTo(other.getBigInteger());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Version)) {
            return false;
        }
        Version version = (Version) obj;
        return this.major == version.major && this.minor == version.minor && this.patch == version.patch;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public int hashCode() {
        return ((((527 + this.major) * 31) + this.minor) * 31) + this.patch;
    }

    public String toString() {
        boolean isBlank;
        String str;
        isBlank = StringsKt__StringsJVMKt.isBlank(this.description);
        if (!isBlank) {
            str = '-' + this.description;
        } else {
            str = "";
        }
        return this.major + '.' + this.minor + '.' + this.patch + str;
    }
}
