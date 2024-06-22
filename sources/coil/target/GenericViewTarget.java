package coil.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import coil.transition.TransitionTarget;

/* loaded from: classes.dex */
public abstract class GenericViewTarget<T extends View> implements ViewTarget, TransitionTarget, DefaultLifecycleObserver {
    private boolean isStarted;

    public abstract Drawable getDrawable();

    @Override // coil.target.Target
    public void onError(Drawable drawable) {
        updateDrawable(drawable);
    }

    @Override // coil.target.Target
    public void onStart(Drawable drawable) {
        updateDrawable(drawable);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
        this.isStarted = true;
        updateAnimation();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
        this.isStarted = false;
        updateAnimation();
    }

    @Override // coil.target.Target
    public void onSuccess(Drawable drawable) {
        updateDrawable(drawable);
    }

    public abstract void setDrawable(Drawable drawable);

    protected final void updateAnimation() {
        Object drawable = getDrawable();
        Animatable animatable = drawable instanceof Animatable ? (Animatable) drawable : null;
        if (animatable == null) {
            return;
        }
        if (this.isStarted) {
            animatable.start();
        } else {
            animatable.stop();
        }
    }

    protected final void updateDrawable(Drawable drawable) {
        Object drawable2 = getDrawable();
        Animatable animatable = drawable2 instanceof Animatable ? (Animatable) drawable2 : null;
        if (animatable != null) {
            animatable.stop();
        }
        setDrawable(drawable);
        updateAnimation();
    }
}
