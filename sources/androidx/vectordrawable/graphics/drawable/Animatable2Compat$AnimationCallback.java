package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public abstract class Animatable2Compat$AnimationCallback {
    Animatable2.AnimationCallback mPlatformCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Animatable2.AnimationCallback getPlatformCallback() {
        if (this.mPlatformCallback == null) {
            this.mPlatformCallback = new Animatable2.AnimationCallback() { // from class: androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback.1
                @Override // android.graphics.drawable.Animatable2.AnimationCallback
                public void onAnimationEnd(Drawable drawable) {
                    Animatable2Compat$AnimationCallback.this.onAnimationEnd(drawable);
                }

                @Override // android.graphics.drawable.Animatable2.AnimationCallback
                public void onAnimationStart(Drawable drawable) {
                    Animatable2Compat$AnimationCallback.this.onAnimationStart(drawable);
                }
            };
        }
        return this.mPlatformCallback;
    }

    public void onAnimationEnd(Drawable drawable) {
    }

    public void onAnimationStart(Drawable drawable) {
    }
}
