package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import java.util.Map;

/* loaded from: classes.dex */
public class ChangeBounds extends Transition {
    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY;
    private static final Property BOTTOM_RIGHT_PROPERTY;
    private static final Property DRAWABLE_ORIGIN_PROPERTY;
    private static final Property POSITION_PROPERTY;
    private static final Property TOP_LEFT_ONLY_PROPERTY;
    private static final Property TOP_LEFT_PROPERTY;
    private static final String[] sTransitionProperties = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private int[] mTempLocation = new int[2];
    private boolean mResizeClip = false;
    private boolean mReparent = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private View mView;

        ViewBounds(View view) {
            this.mView = view;
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }

        void setBottomRight(PointF pointF) {
            this.mRight = Math.round(pointF.x);
            this.mBottom = Math.round(pointF.y);
            int i = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = i;
            if (this.mTopLeftCalls == i) {
                setLeftTopRightBottom();
            }
        }

        void setTopLeft(PointF pointF) {
            this.mLeft = Math.round(pointF.x);
            this.mTop = Math.round(pointF.y);
            int i = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = i;
            if (i == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }
    }

    static {
        Class<PointF> cls = PointF.class;
        DRAWABLE_ORIGIN_PROPERTY = new Property(cls, "boundsOrigin") { // from class: androidx.transition.ChangeBounds.1
            private Rect mBounds = new Rect();

            @Override // android.util.Property
            public PointF get(Drawable drawable) {
                drawable.copyBounds(this.mBounds);
                Rect rect = this.mBounds;
                return new PointF(rect.left, rect.top);
            }

            @Override // android.util.Property
            public void set(Drawable drawable, PointF pointF) {
                drawable.copyBounds(this.mBounds);
                this.mBounds.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
                drawable.setBounds(this.mBounds);
            }
        };
        String str = "topLeft";
        TOP_LEFT_PROPERTY = new Property(cls, str) { // from class: androidx.transition.ChangeBounds.2
            @Override // android.util.Property
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            @Override // android.util.Property
            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.setTopLeft(pointF);
            }
        };
        String str2 = "bottomRight";
        BOTTOM_RIGHT_PROPERTY = new Property(cls, str2) { // from class: androidx.transition.ChangeBounds.3
            @Override // android.util.Property
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            @Override // android.util.Property
            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.setBottomRight(pointF);
            }
        };
        BOTTOM_RIGHT_ONLY_PROPERTY = new Property(cls, str2) { // from class: androidx.transition.ChangeBounds.4
            @Override // android.util.Property
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            public void set(View view, PointF pointF) {
                ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
            }
        };
        TOP_LEFT_ONLY_PROPERTY = new Property(cls, str) { // from class: androidx.transition.ChangeBounds.5
            @Override // android.util.Property
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            public void set(View view, PointF pointF) {
                ViewUtils.setLeftTopRightBottom(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
            }
        };
        POSITION_PROPERTY = new Property(cls, "position") { // from class: androidx.transition.ChangeBounds.6
            @Override // android.util.Property
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            public void set(View view, PointF pointF) {
                int round = Math.round(pointF.x);
                int round2 = Math.round(pointF.y);
                ViewUtils.setLeftTopRightBottom(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
            }
        };
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (!ViewCompat.isLaidOut(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
        if (this.mReparent) {
            transitionValues.view.getLocationInWindow(this.mTempLocation);
            transitionValues.values.put("android:changeBounds:windowX", Integer.valueOf(this.mTempLocation[0]));
            transitionValues.values.put("android:changeBounds:windowY", Integer.valueOf(this.mTempLocation[1]));
        }
        if (this.mResizeClip) {
            transitionValues.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
        }
    }

    private boolean parentMatches(View view, View view2) {
        if (!this.mReparent) {
            return true;
        }
        TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
        if (matchedTransitionValues == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == matchedTransitionValues.view) {
            return true;
        }
        return false;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(final ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        final View view;
        int i2;
        ObjectAnimator objectAnimator;
        Animator mergeAnimators;
        Path path;
        Property property;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map map = transitionValues.values;
        Map map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        final View view2 = transitionValues2.view;
        if (!parentMatches(viewGroup2, viewGroup3)) {
            int intValue = ((Integer) transitionValues.values.get("android:changeBounds:windowX")).intValue();
            int intValue2 = ((Integer) transitionValues.values.get("android:changeBounds:windowY")).intValue();
            int intValue3 = ((Integer) transitionValues2.values.get("android:changeBounds:windowX")).intValue();
            int intValue4 = ((Integer) transitionValues2.values.get("android:changeBounds:windowY")).intValue();
            if (intValue == intValue3 && intValue2 == intValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.mTempLocation);
            Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(createBitmap));
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
            final float transitionAlpha = ViewUtils.getTransitionAlpha(view2);
            ViewUtils.setTransitionAlpha(view2, 0.0f);
            ViewUtils.getOverlay(viewGroup).add(bitmapDrawable);
            PathMotion pathMotion = getPathMotion();
            int[] iArr = this.mTempLocation;
            int i3 = iArr[0];
            int i4 = iArr[1];
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, pathMotion.getPath(intValue - i3, intValue2 - i4, intValue3 - i3, intValue4 - i4)));
            ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ChangeBounds.10
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ViewUtils.getOverlay(viewGroup).remove(bitmapDrawable);
                    ViewUtils.setTransitionAlpha(view2, transitionAlpha);
                }
            });
            return ofPropertyValuesHolder;
        }
        Rect rect = (Rect) transitionValues.values.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) transitionValues2.values.get("android:changeBounds:bounds");
        int i5 = rect.left;
        final int i6 = rect2.left;
        int i7 = rect.top;
        final int i8 = rect2.top;
        int i9 = rect.right;
        final int i10 = rect2.right;
        int i11 = rect.bottom;
        final int i12 = rect2.bottom;
        int i13 = i9 - i5;
        int i14 = i11 - i7;
        int i15 = i10 - i6;
        int i16 = i12 - i8;
        Rect rect3 = (Rect) transitionValues.values.get("android:changeBounds:clip");
        final Rect rect4 = (Rect) transitionValues2.values.get("android:changeBounds:clip");
        if ((i13 == 0 || i14 == 0) && (i15 == 0 || i16 == 0)) {
            i = 0;
        } else {
            i = (i5 == i6 && i7 == i8) ? 0 : 1;
            if (i9 != i10 || i11 != i12) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        if (i <= 0) {
            return null;
        }
        if (this.mResizeClip) {
            view = view2;
            ViewUtils.setLeftTopRightBottom(view, i5, i7, Math.max(i13, i15) + i5, Math.max(i14, i16) + i7);
            ObjectAnimator ofPointF = (i5 == i6 && i7 == i8) ? null : ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath(i5, i7, i6, i8));
            if (rect3 == null) {
                i2 = 0;
                rect3 = new Rect(0, 0, i13, i14);
            } else {
                i2 = 0;
            }
            Rect rect5 = rect4 == null ? new Rect(i2, i2, i15, i16) : rect4;
            if (rect3.equals(rect5)) {
                objectAnimator = null;
            } else {
                ViewCompat.setClipBounds(view, rect3);
                ObjectAnimator ofObject = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, rect3, rect5);
                ofObject.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ChangeBounds.8
                    private boolean mIsCanceled;

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        this.mIsCanceled = true;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (this.mIsCanceled) {
                            return;
                        }
                        ViewCompat.setClipBounds(view, rect4);
                        ViewUtils.setLeftTopRightBottom(view, i6, i8, i10, i12);
                    }
                });
                objectAnimator = ofObject;
            }
            mergeAnimators = TransitionUtils.mergeAnimators(ofPointF, objectAnimator);
        } else {
            view = view2;
            ViewUtils.setLeftTopRightBottom(view, i5, i7, i9, i11);
            if (i == 2) {
                if (i13 == i15 && i14 == i16) {
                    path = getPathMotion().getPath(i5, i7, i6, i8);
                    property = POSITION_PROPERTY;
                } else {
                    ViewBounds viewBounds = new ViewBounds(view);
                    ObjectAnimator ofPointF2 = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, getPathMotion().getPath(i5, i7, i6, i8));
                    ObjectAnimator ofPointF3 = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath(i9, i11, i10, i12));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(ofPointF2, ofPointF3);
                    animatorSet.addListener(new AnimatorListenerAdapter(viewBounds) { // from class: androidx.transition.ChangeBounds.7
                        private ViewBounds mViewBounds;
                        final /* synthetic */ ViewBounds val$viewBounds;

                        {
                            this.val$viewBounds = viewBounds;
                            this.mViewBounds = viewBounds;
                        }
                    });
                    mergeAnimators = animatorSet;
                }
            } else if (i5 == i6 && i7 == i8) {
                path = getPathMotion().getPath(i9, i11, i10, i12);
                property = BOTTOM_RIGHT_ONLY_PROPERTY;
            } else {
                path = getPathMotion().getPath(i5, i7, i6, i8);
                property = TOP_LEFT_ONLY_PROPERTY;
            }
            mergeAnimators = ObjectAnimatorUtils.ofPointF(view, property, path);
        }
        if (view.getParent() instanceof ViewGroup) {
            final ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ViewGroupUtils.suppressLayout(viewGroup4, true);
            addListener(new TransitionListenerAdapter() { // from class: androidx.transition.ChangeBounds.9
                boolean mCanceled = false;

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionCancel(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, false);
                    this.mCanceled = true;
                }

                @Override // androidx.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    if (!this.mCanceled) {
                        ViewGroupUtils.suppressLayout(viewGroup4, false);
                    }
                    transition.removeListener(this);
                }

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionPause(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, false);
                }

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionResume(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, true);
                }
            });
        }
        return mergeAnimators;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }
}
