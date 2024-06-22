package androidx.navigation;

import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* loaded from: classes.dex */
public abstract class Navigator {
    private NavigatorState _state;
    private boolean isAttached;

    /* loaded from: classes.dex */
    public interface Extras {
    }

    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Name {
        String value();
    }

    public abstract NavDestination createDestination();

    /* JADX INFO: Access modifiers changed from: protected */
    public final NavigatorState getState() {
        NavigatorState navigatorState = this._state;
        if (navigatorState != null) {
            return navigatorState;
        }
        throw new IllegalStateException("You cannot access the Navigator's state until the Navigator is attached".toString());
    }

    public final boolean isAttached() {
        return this.isAttached;
    }

    public NavDestination navigate(NavDestination destination, Bundle bundle, NavOptions navOptions, Extras extras) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return destination;
    }

    public void navigate(List entries, final NavOptions navOptions, final Extras extras) {
        Sequence asSequence;
        Sequence map;
        Sequence filterNotNull;
        Intrinsics.checkNotNullParameter(entries, "entries");
        asSequence = CollectionsKt___CollectionsKt.asSequence(entries);
        map = SequencesKt___SequencesKt.map(asSequence, new Function1(navOptions, extras) { // from class: androidx.navigation.Navigator$navigate$1
            final /* synthetic */ NavOptions $navOptions;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final NavBackStackEntry invoke(NavBackStackEntry backStackEntry) {
                NavDestination navigate;
                Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
                NavDestination destination = backStackEntry.getDestination();
                if (!(destination instanceof NavDestination)) {
                    destination = null;
                }
                if (destination != null && (navigate = Navigator.this.navigate(destination, backStackEntry.getArguments(), this.$navOptions, null)) != null) {
                    return Intrinsics.areEqual(navigate, destination) ? backStackEntry : Navigator.this.getState().createBackStackEntry(navigate, navigate.addInDefaultArgs(backStackEntry.getArguments()));
                }
                return null;
            }
        });
        filterNotNull = SequencesKt___SequencesKt.filterNotNull(map);
        Iterator it = filterNotNull.iterator();
        while (it.hasNext()) {
            getState().push((NavBackStackEntry) it.next());
        }
    }

    public void onAttach(NavigatorState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this._state = state;
        this.isAttached = true;
    }

    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        NavDestination destination = backStackEntry.getDestination();
        if (!(destination instanceof NavDestination)) {
            destination = null;
        }
        if (destination == null) {
            return;
        }
        navigate(destination, null, NavOptionsBuilderKt.navOptions(new Function1() { // from class: androidx.navigation.Navigator$onLaunchSingleTop$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((NavOptionsBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(NavOptionsBuilder navOptions) {
                Intrinsics.checkNotNullParameter(navOptions, "$this$navOptions");
                navOptions.setLaunchSingleTop(true);
            }
        }), null);
        getState().onLaunchSingleTop(backStackEntry);
    }

    public void onRestoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
    }

    public Bundle onSaveState() {
        return null;
    }

    public void popBackStack(NavBackStackEntry popUpTo, boolean z) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        List list = (List) getState().getBackStack().getValue();
        if (!list.contains(popUpTo)) {
            throw new IllegalStateException(("popBackStack was called with " + popUpTo + " which does not exist in back stack " + list).toString());
        }
        ListIterator listIterator = list.listIterator(list.size());
        NavBackStackEntry navBackStackEntry = null;
        while (popBackStack()) {
            navBackStackEntry = (NavBackStackEntry) listIterator.previous();
            if (Intrinsics.areEqual(navBackStackEntry, popUpTo)) {
                break;
            }
        }
        if (navBackStackEntry != null) {
            getState().pop(navBackStackEntry, z);
        }
    }

    public boolean popBackStack() {
        return true;
    }
}
