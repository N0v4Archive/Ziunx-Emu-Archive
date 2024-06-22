package androidx.window.core;

/* loaded from: classes.dex */
public final class BuildConfig {
    public static final BuildConfig INSTANCE = new BuildConfig();
    private static final VerificationMode verificationMode = VerificationMode.QUIET;

    private BuildConfig() {
    }

    public final VerificationMode getVerificationMode() {
        return verificationMode;
    }
}
