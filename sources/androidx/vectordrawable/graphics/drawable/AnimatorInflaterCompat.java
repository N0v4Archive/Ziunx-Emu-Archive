package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;

/* loaded from: classes.dex */
public abstract class AnimatorInflaterCompat {
    public static Animator loadAnimator(Context context, int i) {
        return AnimatorInflater.loadAnimator(context, i);
    }
}
