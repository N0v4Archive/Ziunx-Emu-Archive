package androidx.navigation;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public abstract class NavigatorState {
    private final MutableStateFlow _backStack;
    private final MutableStateFlow _transitionsInProgress;
    private final StateFlow backStack;
    private final ReentrantLock backStackLock = new ReentrantLock(true);
    private boolean isNavigating;
    private final StateFlow transitionsInProgress;

    public NavigatorState() {
        List emptyList;
        Set emptySet;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(emptyList);
        this._backStack = MutableStateFlow;
        emptySet = SetsKt__SetsKt.emptySet();
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(emptySet);
        this._transitionsInProgress = MutableStateFlow2;
        this.backStack = FlowKt.asStateFlow(MutableStateFlow);
        this.transitionsInProgress = FlowKt.asStateFlow(MutableStateFlow2);
    }

    public abstract NavBackStackEntry createBackStackEntry(NavDestination navDestination, Bundle bundle);

    public final StateFlow getBackStack() {
        return this.backStack;
    }

    public final StateFlow getTransitionsInProgress() {
        return this.transitionsInProgress;
    }

    public final boolean isNavigating() {
        return this.isNavigating;
    }

    public void markTransitionComplete(NavBackStackEntry entry) {
        Set minus;
        Intrinsics.checkNotNullParameter(entry, "entry");
        MutableStateFlow mutableStateFlow = this._transitionsInProgress;
        minus = SetsKt___SetsKt.minus((Set) mutableStateFlow.getValue(), entry);
        mutableStateFlow.setValue(minus);
    }

    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        List mutableList;
        int i;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.backStack.getValue());
            ListIterator listIterator = mutableList.listIterator(mutableList.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(((NavBackStackEntry) listIterator.previous()).getId(), backStackEntry.getId())) {
                    i = listIterator.nextIndex();
                    break;
                }
            }
            mutableList.set(i, backStackEntry);
            this._backStack.setValue(mutableList);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void onLaunchSingleTopWithTransition(NavBackStackEntry backStackEntry) {
        Set plus;
        Set plus2;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        List list = (List) this.backStack.getValue();
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) listIterator.previous();
            if (Intrinsics.areEqual(navBackStackEntry.getId(), backStackEntry.getId())) {
                MutableStateFlow mutableStateFlow = this._transitionsInProgress;
                plus = SetsKt___SetsKt.plus((Set) mutableStateFlow.getValue(), navBackStackEntry);
                plus2 = SetsKt___SetsKt.plus(plus, backStackEntry);
                mutableStateFlow.setValue(plus2);
                onLaunchSingleTop(backStackEntry);
                return;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    public void pop(NavBackStackEntry popUpTo, boolean z) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            MutableStateFlow mutableStateFlow = this._backStack;
            Iterable iterable = (Iterable) mutableStateFlow.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (!(!Intrinsics.areEqual((NavBackStackEntry) obj, popUpTo))) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            mutableStateFlow.setValue(arrayList);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void popWithTransition(NavBackStackEntry popUpTo, boolean z) {
        boolean z2;
        Set plus;
        Object obj;
        Set plus2;
        boolean z3;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Iterable iterable = (Iterable) this._transitionsInProgress.getValue();
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                if (((NavBackStackEntry) it.next()) == popUpTo) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        if (z2) {
            Iterable iterable2 = (Iterable) this.backStack.getValue();
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    if (((NavBackStackEntry) it2.next()) == popUpTo) {
                        z3 = false;
                        break;
                    }
                }
            }
            z3 = true;
            if (z3) {
                return;
            }
        }
        MutableStateFlow mutableStateFlow = this._transitionsInProgress;
        plus = SetsKt___SetsKt.plus((Set) mutableStateFlow.getValue(), popUpTo);
        mutableStateFlow.setValue(plus);
        List list = (List) this.backStack.getValue();
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
            if (!Intrinsics.areEqual(navBackStackEntry, popUpTo) && ((List) this.backStack.getValue()).lastIndexOf(navBackStackEntry) < ((List) this.backStack.getValue()).lastIndexOf(popUpTo)) {
                break;
            }
        }
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj;
        if (navBackStackEntry2 != null) {
            MutableStateFlow mutableStateFlow2 = this._transitionsInProgress;
            plus2 = SetsKt___SetsKt.plus((Set) mutableStateFlow2.getValue(), navBackStackEntry2);
            mutableStateFlow2.setValue(plus2);
        }
        pop(popUpTo, z);
    }

    public void prepareForTransition(NavBackStackEntry entry) {
        Set plus;
        Intrinsics.checkNotNullParameter(entry, "entry");
        MutableStateFlow mutableStateFlow = this._transitionsInProgress;
        plus = SetsKt___SetsKt.plus((Set) mutableStateFlow.getValue(), entry);
        mutableStateFlow.setValue(plus);
    }

    public void push(NavBackStackEntry backStackEntry) {
        List plus;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            MutableStateFlow mutableStateFlow = this._backStack;
            plus = CollectionsKt___CollectionsKt.plus((Collection) mutableStateFlow.getValue(), backStackEntry);
            mutableStateFlow.setValue(plus);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void pushWithTransition(NavBackStackEntry backStackEntry) {
        boolean z;
        Object lastOrNull;
        Set plus;
        Set plus2;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        Iterable iterable = (Iterable) this._transitionsInProgress.getValue();
        boolean z2 = true;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                if (((NavBackStackEntry) it.next()) == backStackEntry) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (z) {
            Iterable iterable2 = (Iterable) this.backStack.getValue();
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    if (((NavBackStackEntry) it2.next()) == backStackEntry) {
                        break;
                    }
                }
            }
            z2 = false;
            if (z2) {
                return;
            }
        }
        lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List) this.backStack.getValue());
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) lastOrNull;
        if (navBackStackEntry != null) {
            MutableStateFlow mutableStateFlow = this._transitionsInProgress;
            plus2 = SetsKt___SetsKt.plus((Set) mutableStateFlow.getValue(), navBackStackEntry);
            mutableStateFlow.setValue(plus2);
        }
        MutableStateFlow mutableStateFlow2 = this._transitionsInProgress;
        plus = SetsKt___SetsKt.plus((Set) mutableStateFlow2.getValue(), backStackEntry);
        mutableStateFlow2.setValue(plus);
        push(backStackEntry);
    }

    public final void setNavigating(boolean z) {
        this.isNavigating = z;
    }
}
