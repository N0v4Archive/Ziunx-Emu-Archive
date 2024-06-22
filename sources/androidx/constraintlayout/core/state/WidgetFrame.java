package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class WidgetFrame {
    public ConstraintWidget widget;
    public int left = 0;
    public int top = 0;
    public int right = 0;
    public int bottom = 0;
    public float pivotX = Float.NaN;
    public float pivotY = Float.NaN;
    public float rotationX = Float.NaN;
    public float rotationY = Float.NaN;
    public float rotationZ = Float.NaN;
    public float translationX = Float.NaN;
    public float translationY = Float.NaN;
    public float translationZ = Float.NaN;
    public float scaleX = Float.NaN;
    public float scaleY = Float.NaN;
    public float alpha = Float.NaN;
    public float interpolatedPos = Float.NaN;
    public int visibility = 0;
    public final HashMap mCustom = new HashMap();
    public String name = null;

    public WidgetFrame(ConstraintWidget constraintWidget) {
        this.widget = null;
        this.widget = constraintWidget;
    }
}
