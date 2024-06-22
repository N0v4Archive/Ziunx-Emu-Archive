package com.google.android.material.carousel;

import android.view.View;

/* loaded from: classes.dex */
public abstract class CarouselStrategy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getChildMaskPercentage(float f, float f2, float f3) {
        return 1.0f - ((f - f3) / (f2 - f3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view);
}
