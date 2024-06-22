package androidx.core.app;

import androidx.core.util.Consumer;

/* loaded from: classes.dex */
public interface OnPictureInPictureModeChangedProvider {
    void addOnPictureInPictureModeChangedListener(Consumer consumer);

    void removeOnPictureInPictureModeChangedListener(Consumer consumer);
}
