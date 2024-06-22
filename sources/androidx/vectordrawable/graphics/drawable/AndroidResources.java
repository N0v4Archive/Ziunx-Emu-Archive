package androidx.vectordrawable.graphics.drawable;

import android.R;

/* loaded from: classes.dex */
abstract class AndroidResources {
    static final int[] STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY = {R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};
    static final int[] STYLEABLE_VECTOR_DRAWABLE_GROUP = {R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};
    static final int[] STYLEABLE_VECTOR_DRAWABLE_PATH = {R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};
    static final int[] STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH = {R.attr.name, R.attr.pathData, R.attr.fillType};
    static final int[] STYLEABLE_ANIMATED_VECTOR_DRAWABLE = {R.attr.drawable};
    static final int[] STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET = {R.attr.name, R.attr.animation};
    public static final int[] STYLEABLE_ANIMATOR = {R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};
    public static final int[] STYLEABLE_ANIMATOR_SET = {R.attr.ordering};
    public static final int[] STYLEABLE_PROPERTY_VALUES_HOLDER = {R.attr.valueFrom, R.attr.valueTo, R.attr.valueType, R.attr.propertyName};
    public static final int[] STYLEABLE_KEYFRAME = {R.attr.value, R.attr.interpolator, R.attr.valueType, R.attr.fraction};
    public static final int[] STYLEABLE_PROPERTY_ANIMATOR = {R.attr.propertyName, R.attr.pathData, R.attr.propertyXName, R.attr.propertyYName};
    public static final int[] STYLEABLE_PATH_INTERPOLATOR = {R.attr.controlX1, R.attr.controlY1, R.attr.controlX2, R.attr.controlY2, R.attr.pathData};
}
