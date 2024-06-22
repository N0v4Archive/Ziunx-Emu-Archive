package androidx.core.app;

import android.content.res.Configuration;

/* loaded from: classes.dex */
public final class PictureInPictureModeChangedInfo {
    private final boolean mIsInPictureInPictureMode;
    private final Configuration mNewConfig;

    public PictureInPictureModeChangedInfo(boolean z) {
        this.mIsInPictureInPictureMode = z;
        this.mNewConfig = null;
    }

    public PictureInPictureModeChangedInfo(boolean z, Configuration configuration) {
        this.mIsInPictureInPictureMode = z;
        this.mNewConfig = configuration;
    }

    public boolean isInPictureInPictureMode() {
        return this.mIsInPictureInPictureMode;
    }
}
