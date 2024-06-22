package androidx.navigation.fragment;

import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavigatorState;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FragmentNavigator$fragmentViewObserver$1 extends Lambda implements Function1 {
    final /* synthetic */ FragmentNavigator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentNavigator$fragmentViewObserver$1(FragmentNavigator fragmentNavigator) {
        super(1);
        this.this$0 = fragmentNavigator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(FragmentNavigator this$0, NavBackStackEntry entry, LifecycleOwner owner, Lifecycle.Event event) {
        NavigatorState state;
        NavigatorState state2;
        NavigatorState state3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(entry, "$entry");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_RESUME) {
            state2 = this$0.getState();
            if (((List) state2.getBackStack().getValue()).contains(entry)) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentNavigator", "Marking transition complete for entry " + entry + " due to fragment " + owner + " view lifecycle reaching RESUMED");
                }
                state3 = this$0.getState();
                state3.markTransitionComplete(entry);
            }
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentNavigator", "Marking transition complete for entry " + entry + " due to fragment " + owner + " view lifecycle reaching DESTROYED");
            }
            state = this$0.getState();
            state.markTransitionComplete(entry);
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final LifecycleEventObserver invoke(final NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        final FragmentNavigator fragmentNavigator = this.this$0;
        return new LifecycleEventObserver() { // from class: androidx.navigation.fragment.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FragmentNavigator$fragmentViewObserver$1.invoke$lambda$0(FragmentNavigator.this, entry, lifecycleOwner, event);
            }
        };
    }
}
