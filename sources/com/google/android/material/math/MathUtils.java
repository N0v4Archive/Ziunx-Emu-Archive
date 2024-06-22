package com.google.android.material.math;

/* loaded from: classes.dex */
public abstract class MathUtils {
    public static float dist(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f3 - f, f4 - f2);
    }
}
