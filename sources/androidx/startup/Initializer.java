package androidx.startup;

import android.content.Context;
import java.util.List;

/* loaded from: classes.dex */
public interface Initializer {
    Object create(Context context);

    List dependencies();
}
