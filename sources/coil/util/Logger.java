package coil.util;

/* loaded from: classes.dex */
public interface Logger {
    int getLevel();

    void log(String str, int i, String str2, Throwable th);
}
