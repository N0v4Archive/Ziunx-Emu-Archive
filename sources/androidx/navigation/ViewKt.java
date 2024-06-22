package androidx.navigation;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class ViewKt {
    public static final NavController findNavController(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return Navigation.findNavController(view);
    }
}
