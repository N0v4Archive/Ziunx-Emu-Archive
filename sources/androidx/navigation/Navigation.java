package androidx.navigation;

import android.view.View;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;

/* loaded from: classes.dex */
public final class Navigation {
    public static final Navigation INSTANCE = new Navigation();

    private Navigation() {
    }

    public static final NavController findNavController(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        NavController findViewNavController = INSTANCE.findViewNavController(view);
        if (findViewNavController != null) {
            return findViewNavController;
        }
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }

    private final NavController findViewNavController(View view) {
        Sequence generateSequence;
        Sequence mapNotNull;
        Object firstOrNull;
        generateSequence = SequencesKt__SequencesKt.generateSequence(view, new Function1() { // from class: androidx.navigation.Navigation$findViewNavController$1
            @Override // kotlin.jvm.functions.Function1
            public final View invoke(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object parent = it.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            }
        });
        mapNotNull = SequencesKt___SequencesKt.mapNotNull(generateSequence, new Function1() { // from class: androidx.navigation.Navigation$findViewNavController$2
            @Override // kotlin.jvm.functions.Function1
            public final NavController invoke(View it) {
                NavController viewNavController;
                Intrinsics.checkNotNullParameter(it, "it");
                viewNavController = Navigation.INSTANCE.getViewNavController(it);
                return viewNavController;
            }
        });
        firstOrNull = SequencesKt___SequencesKt.firstOrNull(mapNotNull);
        return (NavController) firstOrNull;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavController getViewNavController(View view) {
        Object tag = view.getTag(R$id.nav_controller_view_tag);
        if (tag instanceof WeakReference) {
            tag = ((WeakReference) tag).get();
        } else if (!(tag instanceof NavController)) {
            return null;
        }
        return (NavController) tag;
    }

    public static final void setViewNavController(View view, NavController navController) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTag(R$id.nav_controller_view_tag, navController);
    }
}
