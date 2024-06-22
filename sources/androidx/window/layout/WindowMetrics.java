package androidx.window.layout;

import android.graphics.Rect;
import androidx.core.view.WindowInsetsCompat;
import androidx.window.core.Bounds;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class WindowMetrics {
    private final Bounds _bounds;
    private final WindowInsetsCompat _windowInsetsCompat;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WindowMetrics(Rect bounds, WindowInsetsCompat insets) {
        this(new Bounds(bounds), insets);
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(insets, "insets");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ WindowMetrics(android.graphics.Rect r1, androidx.core.view.WindowInsetsCompat r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L12
            androidx.core.view.WindowInsetsCompat$Builder r2 = new androidx.core.view.WindowInsetsCompat$Builder
            r2.<init>()
            androidx.core.view.WindowInsetsCompat r2 = r2.build()
            java.lang.String r3 = "Builder().build()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L12:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.WindowMetrics.<init>(android.graphics.Rect, androidx.core.view.WindowInsetsCompat, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public WindowMetrics(Bounds _bounds, WindowInsetsCompat _windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(_bounds, "_bounds");
        Intrinsics.checkNotNullParameter(_windowInsetsCompat, "_windowInsetsCompat");
        this._bounds = _bounds;
        this._windowInsetsCompat = _windowInsetsCompat;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(WindowMetrics.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.window.layout.WindowMetrics");
        WindowMetrics windowMetrics = (WindowMetrics) obj;
        return Intrinsics.areEqual(this._bounds, windowMetrics._bounds) && Intrinsics.areEqual(this._windowInsetsCompat, windowMetrics._windowInsetsCompat);
    }

    public final Rect getBounds() {
        return this._bounds.toRect();
    }

    public int hashCode() {
        return (this._bounds.hashCode() * 31) + this._windowInsetsCompat.hashCode();
    }

    public String toString() {
        return "WindowMetrics( bounds=" + this._bounds + ", windowInsetsCompat=" + this._windowInsetsCompat + ')';
    }
}
