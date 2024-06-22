package coil.target;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public interface Target {
    void onError(Drawable drawable);

    void onStart(Drawable drawable);

    void onSuccess(Drawable drawable);
}
