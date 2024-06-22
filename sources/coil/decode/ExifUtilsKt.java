package coil.decode;

import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt__SetsKt;

/* loaded from: classes.dex */
public abstract class ExifUtilsKt {
    private static final Set RESPECT_PERFORMANCE_MIME_TYPES;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExifOrientationPolicy.values().length];
            iArr[ExifOrientationPolicy.RESPECT_PERFORMANCE.ordinal()] = 1;
            iArr[ExifOrientationPolicy.IGNORE.ordinal()] = 2;
            iArr[ExifOrientationPolicy.RESPECT_ALL.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Set of;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{"image/jpeg", "image/webp", "image/heic", "image/heif"});
        RESPECT_PERFORMANCE_MIME_TYPES = of;
    }

    public static final boolean isRotated(ExifData exifData) {
        return exifData.getRotationDegrees() > 0;
    }

    public static final boolean isSwapped(ExifData exifData) {
        return exifData.getRotationDegrees() == 90 || exifData.getRotationDegrees() == 270;
    }

    public static final boolean supports(ExifOrientationPolicy exifOrientationPolicy, String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[exifOrientationPolicy.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return false;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
        } else if (str == null || !RESPECT_PERFORMANCE_MIME_TYPES.contains(str)) {
            return false;
        }
        return true;
    }
}
