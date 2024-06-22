package androidx.core.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* loaded from: classes.dex */
public abstract class PathInterpolatorCompat {

    /* loaded from: classes.dex */
    static class Api21Impl {
        static Interpolator createPathInterpolator(float f, float f2) {
            return new PathInterpolator(f, f2);
        }

        static Interpolator createPathInterpolator(float f, float f2, float f3, float f4) {
            return new PathInterpolator(f, f2, f3, f4);
        }

        static Interpolator createPathInterpolator(Path path) {
            return new PathInterpolator(path);
        }
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        return Api21Impl.createPathInterpolator(f, f2, f3, f4);
    }

    public static Interpolator create(Path path) {
        return Api21Impl.createPathInterpolator(path);
    }
}
