package coil.decode;

import android.graphics.Paint;
import androidx.exifinterface.media.ExifInterface;
import okio.BufferedSource;

/* loaded from: classes.dex */
public final class ExifUtils {
    public static final ExifUtils INSTANCE = new ExifUtils();
    private static final Paint PAINT = new Paint(3);

    private ExifUtils() {
    }

    public final ExifData getExifData(String str, BufferedSource bufferedSource, ExifOrientationPolicy exifOrientationPolicy) {
        if (!ExifUtilsKt.supports(exifOrientationPolicy, str)) {
            return ExifData.NONE;
        }
        ExifInterface exifInterface = new ExifInterface(new ExifInterfaceInputStream(bufferedSource.peek().inputStream()));
        return new ExifData(exifInterface.isFlipped(), exifInterface.getRotationDegrees());
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:
    
        if ((r0.top == 0.0f) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap reverseTransformations(android.graphics.Bitmap r7, coil.decode.ExifData r8) {
        /*
            r6 = this;
            boolean r6 = r8.isFlipped()
            if (r6 != 0) goto Ld
            boolean r6 = coil.decode.ExifUtilsKt.isRotated(r8)
            if (r6 != 0) goto Ld
            return r7
        Ld:
            android.graphics.Matrix r6 = new android.graphics.Matrix
            r6.<init>()
            int r0 = r7.getWidth()
            float r0 = (float) r0
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            int r2 = r7.getHeight()
            float r2 = (float) r2
            float r2 = r2 / r1
            boolean r1 = r8.isFlipped()
            if (r1 == 0) goto L2d
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
            r6.postScale(r1, r3, r0, r2)
        L2d:
            boolean r1 = coil.decode.ExifUtilsKt.isRotated(r8)
            if (r1 == 0) goto L3b
            int r1 = r8.getRotationDegrees()
            float r1 = (float) r1
            r6.postRotate(r1, r0, r2)
        L3b:
            android.graphics.RectF r0 = new android.graphics.RectF
            int r1 = r7.getWidth()
            float r1 = (float) r1
            int r2 = r7.getHeight()
            float r2 = (float) r2
            r3 = 0
            r0.<init>(r3, r3, r1, r2)
            r6.mapRect(r0)
            float r1 = r0.left
            int r2 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r4 = 1
            r5 = 0
            if (r2 != 0) goto L58
            r2 = r4
            goto L59
        L58:
            r2 = r5
        L59:
            if (r2 == 0) goto L65
            float r2 = r0.top
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L62
            goto L63
        L62:
            r4 = r5
        L63:
            if (r4 != 0) goto L6c
        L65:
            float r1 = -r1
            float r0 = r0.top
            float r0 = -r0
            r6.postTranslate(r1, r0)
        L6c:
            boolean r8 = coil.decode.ExifUtilsKt.isSwapped(r8)
            java.lang.String r0 = "createBitmap(width, height, config)"
            if (r8 == 0) goto L7d
            int r8 = r7.getHeight()
            int r1 = r7.getWidth()
            goto L85
        L7d:
            int r8 = r7.getWidth()
            int r1 = r7.getHeight()
        L85:
            android.graphics.Bitmap$Config r2 = coil.util.Bitmaps.getSafeConfig(r7)
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r8, r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
            android.graphics.Canvas r0 = new android.graphics.Canvas
            r0.<init>(r8)
            android.graphics.Paint r1 = coil.decode.ExifUtils.PAINT
            r0.drawBitmap(r7, r6, r1)
            r7.recycle()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.decode.ExifUtils.reverseTransformations(android.graphics.Bitmap, coil.decode.ExifData):android.graphics.Bitmap");
    }
}
