package androidx.core.app;

import android.content.res.Configuration;

/* loaded from: classes.dex */
public final class MultiWindowModeChangedInfo {
    private final boolean mIsInMultiWindowMode;
    private final Configuration mNewConfig;

    public MultiWindowModeChangedInfo(boolean z) {
        this.mIsInMultiWindowMode = z;
        this.mNewConfig = null;
    }

    public MultiWindowModeChangedInfo(boolean z, Configuration configuration) {
        this.mIsInMultiWindowMode = z;
        this.mNewConfig = configuration;
    }

    public boolean isInMultiWindowMode() {
        return this.mIsInMultiWindowMode;
    }
}
