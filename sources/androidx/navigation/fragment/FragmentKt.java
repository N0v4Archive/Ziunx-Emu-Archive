package androidx.navigation.fragment;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class FragmentKt {
    public static final NavController findNavController(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return NavHostFragment.Companion.findNavController(fragment);
    }
}
