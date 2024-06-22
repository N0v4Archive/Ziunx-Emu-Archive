package org.yuzu.yuzu_emu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Rational;
import android.view.SurfaceView;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class FixedRatioSurfaceView extends SurfaceView {
    private float aspectRatio;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FixedRatioSurfaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedRatioSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ FixedRatioSurfaceView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        float size = View.MeasureSpec.getSize(i);
        float size2 = View.MeasureSpec.getSize(i2);
        float f = this.aspectRatio;
        if (f == 0.0f) {
            super.onMeasure(i, i2);
            return;
        }
        if (size / size2 < f) {
            float f2 = 2;
            float f3 = size2 / f2;
            float f4 = (size / f) / f2;
            i2 = View.MeasureSpec.makeMeasureSpec(((int) (f3 + f4)) - ((int) (f3 - f4)), 1073741824);
        } else {
            float f5 = 2;
            float f6 = size / f5;
            float f7 = (size2 * f) / f5;
            i = View.MeasureSpec.makeMeasureSpec(((int) (f6 + f7)) - ((int) (f6 - f7)), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public final void setAspectRatio(Rational rational) {
        this.aspectRatio = rational != null ? rational.floatValue() : 0.0f;
        requestLayout();
    }
}
