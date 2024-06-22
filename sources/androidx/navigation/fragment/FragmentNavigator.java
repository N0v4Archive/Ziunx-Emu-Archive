package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorState;
import androidx.navigation.fragment.FragmentNavigator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Navigator.Name("fragment")
/* loaded from: classes.dex */
public class FragmentNavigator extends Navigator {
    private static final Companion Companion = new Companion(null);
    private final int containerId;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final LifecycleEventObserver fragmentObserver;
    private final Function1 fragmentViewObserver;
    private final List pendingOps;
    private final Set savedIds;

    /* loaded from: classes.dex */
    public static final class ClearEntryStateViewModel extends ViewModel {
        public WeakReference completeTransition;

        public final WeakReference getCompleteTransition() {
            WeakReference weakReference = this.completeTransition;
            if (weakReference != null) {
                return weakReference;
            }
            Intrinsics.throwUninitializedPropertyAccessException("completeTransition");
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            super.onCleared();
            Function0 function0 = (Function0) getCompleteTransition().get();
            if (function0 != null) {
                function0.invoke();
            }
        }

        public final void setCompleteTransition(WeakReference weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            this.completeTransition = weakReference;
        }
    }

    /* loaded from: classes.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static class Destination extends NavDestination {
        private String _className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Destination(Navigator fragmentNavigator) {
            super(fragmentNavigator);
            Intrinsics.checkNotNullParameter(fragmentNavigator, "fragmentNavigator");
        }

        @Override // androidx.navigation.NavDestination
        public boolean equals(Object obj) {
            return obj != null && (obj instanceof Destination) && super.equals(obj) && Intrinsics.areEqual(this._className, ((Destination) obj)._className);
        }

        public final String getClassName() {
            String str = this._className;
            if (str == null) {
                throw new IllegalStateException("Fragment class was not set".toString());
            }
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            return str;
        }

        @Override // androidx.navigation.NavDestination
        public int hashCode() {
            int hashCode = super.hashCode() * 31;
            String str = this._className;
            return hashCode + (str != null ? str.hashCode() : 0);
        }

        @Override // androidx.navigation.NavDestination
        public void onInflate(Context context, AttributeSet attrs) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(attrs, "attrs");
            super.onInflate(context, attrs);
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attrs, R$styleable.FragmentNavigator);
            Intrinsics.checkNotNullExpressionValue(obtainAttributes, "context.resources.obtain…leable.FragmentNavigator)");
            String string = obtainAttributes.getString(R$styleable.FragmentNavigator_android_name);
            if (string != null) {
                setClassName(string);
            }
            Unit unit = Unit.INSTANCE;
            obtainAttributes.recycle();
        }

        public final Destination setClassName(String className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this._className = className;
            return this;
        }

        @Override // androidx.navigation.NavDestination
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this._className;
            if (str == null) {
                str = "null";
            }
            sb.append(str);
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
            return sb2;
        }
    }

    public FragmentNavigator(Context context, FragmentManager fragmentManager, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = i;
        this.savedIds = new LinkedHashSet();
        this.pendingOps = new ArrayList();
        this.fragmentObserver = new LifecycleEventObserver() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FragmentNavigator.fragmentObserver$lambda$1(FragmentNavigator.this, lifecycleOwner, event);
            }
        };
        this.fragmentViewObserver = new FragmentNavigator$fragmentViewObserver$1(this);
    }

    private final void addPendingOps(final String str, boolean z, boolean z2) {
        if (z2) {
            CollectionsKt__MutableCollectionsKt.removeAll(this.pendingOps, new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$addPendingOps$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Pair it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(Intrinsics.areEqual(it.getFirst(), str));
                }
            });
        }
        this.pendingOps.add(TuplesKt.to(str, Boolean.valueOf(z)));
    }

    static /* synthetic */ void addPendingOps$default(FragmentNavigator fragmentNavigator, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addPendingOps");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        fragmentNavigator.addPendingOps(str, z, z2);
    }

    private final void attachObservers(final NavBackStackEntry navBackStackEntry, final Fragment fragment) {
        fragment.getViewLifecycleOwnerLiveData().observe(fragment, new FragmentNavigator$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$attachObservers$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((LifecycleOwner) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(LifecycleOwner lifecycleOwner) {
                Function1 function1;
                List pendingOps$navigation_fragment_release = FragmentNavigator.this.getPendingOps$navigation_fragment_release();
                Fragment fragment2 = fragment;
                boolean z = false;
                if (!(pendingOps$navigation_fragment_release instanceof Collection) || !pendingOps$navigation_fragment_release.isEmpty()) {
                    Iterator it = pendingOps$navigation_fragment_release.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), fragment2.getTag())) {
                            z = true;
                            break;
                        }
                    }
                }
                if (lifecycleOwner == null || z) {
                    return;
                }
                Lifecycle lifecycle = fragment.getViewLifecycleOwner().getLifecycle();
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    function1 = FragmentNavigator.this.fragmentViewObserver;
                    lifecycle.addObserver((LifecycleObserver) function1.invoke(navBackStackEntry));
                }
            }
        }));
        fragment.getLifecycle().addObserver(this.fragmentObserver);
    }

    private final FragmentTransaction createFragmentTransaction(NavBackStackEntry navBackStackEntry, NavOptions navOptions) {
        NavDestination destination = navBackStackEntry.getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.fragment.FragmentNavigator.Destination");
        Bundle arguments = navBackStackEntry.getArguments();
        String className = ((Destination) destination).getClassName();
        if (className.charAt(0) == '.') {
            className = this.context.getPackageName() + className;
        }
        Fragment instantiate = this.fragmentManager.getFragmentFactory().instantiate(this.context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(instantiate, "fragmentManager.fragment…t.classLoader, className)");
        instantiate.setArguments(arguments);
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "fragmentManager.beginTransaction()");
        int enterAnim = navOptions != null ? navOptions.getEnterAnim() : -1;
        int exitAnim = navOptions != null ? navOptions.getExitAnim() : -1;
        int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : -1;
        int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : -1;
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            if (enterAnim == -1) {
                enterAnim = 0;
            }
            if (exitAnim == -1) {
                exitAnim = 0;
            }
            if (popEnterAnim == -1) {
                popEnterAnim = 0;
            }
            beginTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim != -1 ? popExitAnim : 0);
        }
        beginTransaction.replace(this.containerId, instantiate, navBackStackEntry.getId());
        beginTransaction.setPrimaryNavigationFragment(instantiate);
        beginTransaction.setReorderingAllowed(true);
        return beginTransaction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fragmentObserver$lambda$1(FragmentNavigator this$0, LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            Fragment fragment = (Fragment) source;
            Object obj = null;
            for (Object obj2 : (Iterable) this$0.getState().getTransitionsInProgress().getValue()) {
                if (Intrinsics.areEqual(((NavBackStackEntry) obj2).getId(), fragment.getTag())) {
                    obj = obj2;
                }
            }
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
            if (navBackStackEntry != null) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentNavigator", "Marking transition complete for entry " + navBackStackEntry + " due to fragment " + source + " lifecycle reaching DESTROYED");
                }
                this$0.getState().markTransitionComplete(navBackStackEntry);
            }
        }
    }

    private final void navigate(NavBackStackEntry navBackStackEntry, NavOptions navOptions, Navigator.Extras extras) {
        Object lastOrNull;
        boolean isEmpty = ((List) getState().getBackStack().getValue()).isEmpty();
        if (navOptions != null && !isEmpty && navOptions.shouldRestoreState() && this.savedIds.remove(navBackStackEntry.getId())) {
            this.fragmentManager.restoreBackStack(navBackStackEntry.getId());
        } else {
            FragmentTransaction createFragmentTransaction = createFragmentTransaction(navBackStackEntry, navOptions);
            if (!isEmpty) {
                lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List) getState().getBackStack().getValue());
                NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) lastOrNull;
                if (navBackStackEntry2 != null) {
                    addPendingOps$default(this, navBackStackEntry2.getId(), false, false, 6, null);
                }
                addPendingOps$default(this, navBackStackEntry.getId(), false, false, 6, null);
                createFragmentTransaction.addToBackStack(navBackStackEntry.getId());
            }
            createFragmentTransaction.commit();
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentNavigator", "Calling pushWithTransition via navigate() on entry " + navBackStackEntry);
            }
        }
        getState().pushWithTransition(navBackStackEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAttach$lambda$3(NavigatorState state, FragmentNavigator this$0, FragmentManager fragmentManager, Fragment fragment) {
        Object obj;
        Intrinsics.checkNotNullParameter(state, "$state");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fragmentManager, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        List list = (List) state.getBackStack().getValue();
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (Intrinsics.areEqual(((NavBackStackEntry) obj).getId(), fragment.getTag())) {
                    break;
                }
            }
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentNavigator", "Attaching fragment " + fragment + " associated with entry " + navBackStackEntry + " to FragmentManager " + this$0.fragmentManager);
        }
        if (navBackStackEntry != null) {
            this$0.attachObservers(navBackStackEntry, fragment);
            this$0.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, state);
        }
    }

    public final void attachClearViewModel$navigation_fragment_release(final Fragment fragment, final NavBackStackEntry entry, final NavigatorState state) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(state, "state");
        ViewModelStore viewModelStore = fragment.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "fragment.viewModelStore");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(ClearEntryStateViewModel.class), new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$attachClearViewModel$viewModel$1$1
            @Override // kotlin.jvm.functions.Function1
            public final FragmentNavigator.ClearEntryStateViewModel invoke(CreationExtras initializer) {
                Intrinsics.checkNotNullParameter(initializer, "$this$initializer");
                return new FragmentNavigator.ClearEntryStateViewModel();
            }
        });
        ((ClearEntryStateViewModel) new ViewModelProvider(viewModelStore, initializerViewModelFactoryBuilder.build(), CreationExtras.Empty.INSTANCE).get(ClearEntryStateViewModel.class)).setCompleteTransition(new WeakReference(new Function0() { // from class: androidx.navigation.fragment.FragmentNavigator$attachClearViewModel$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m23invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m23invoke() {
                NavigatorState navigatorState = state;
                Fragment fragment2 = fragment;
                for (NavBackStackEntry navBackStackEntry : (Iterable) navigatorState.getTransitionsInProgress().getValue()) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentNavigator", "Marking transition complete for entry " + navBackStackEntry + " due to fragment " + fragment2 + " viewmodel being cleared");
                    }
                    navigatorState.markTransitionComplete(navBackStackEntry);
                }
            }
        }));
    }

    @Override // androidx.navigation.Navigator
    public Destination createDestination() {
        return new Destination(this);
    }

    public final List getPendingOps$navigation_fragment_release() {
        return this.pendingOps;
    }

    @Override // androidx.navigation.Navigator
    public void navigate(List entries, NavOptions navOptions, Navigator.Extras extras) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        if (this.fragmentManager.isStateSaved()) {
            Log.i("FragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return;
        }
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            navigate((NavBackStackEntry) it.next(), navOptions, extras);
        }
    }

    @Override // androidx.navigation.Navigator
    public void onAttach(final NavigatorState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onAttach(state);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentNavigator", "onAttach");
        }
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda1
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public final void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
                FragmentNavigator.onAttach$lambda$3(NavigatorState.this, this, fragmentManager, fragment);
            }
        });
        this.fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: androidx.navigation.fragment.FragmentNavigator$onAttach$2
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeCommitted(Fragment fragment, boolean z) {
                List plus;
                Object obj;
                Object obj2;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                plus = CollectionsKt___CollectionsKt.plus((Collection) NavigatorState.this.getBackStack().getValue(), (Iterable) NavigatorState.this.getTransitionsInProgress().getValue());
                ListIterator listIterator = plus.listIterator(plus.size());
                while (true) {
                    obj = null;
                    if (!listIterator.hasPrevious()) {
                        obj2 = null;
                        break;
                    } else {
                        obj2 = listIterator.previous();
                        if (Intrinsics.areEqual(((NavBackStackEntry) obj2).getId(), fragment.getTag())) {
                            break;
                        }
                    }
                }
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj2;
                boolean z2 = z && this.getPendingOps$navigation_fragment_release().isEmpty() && fragment.isRemoving();
                Iterator it = this.getPendingOps$navigation_fragment_release().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(((Pair) next).getFirst(), fragment.getTag())) {
                        obj = next;
                        break;
                    }
                }
                Pair pair = (Pair) obj;
                if (pair != null) {
                    this.getPendingOps$navigation_fragment_release().remove(pair);
                }
                if (!z2 && FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentNavigator", "OnBackStackChangedCommitted for fragment " + fragment + " associated with entry " + navBackStackEntry);
                }
                boolean z3 = pair != null && ((Boolean) pair.getSecond()).booleanValue();
                if (!z && !z3 && navBackStackEntry == null) {
                    throw new IllegalArgumentException(("The fragment " + fragment + " is unknown to the FragmentNavigator. Please use the navigate() function to add fragments to the FragmentNavigator managed FragmentManager.").toString());
                }
                if (navBackStackEntry != null) {
                    this.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, NavigatorState.this);
                    if (z2) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentNavigator", "OnBackStackChangedCommitted for fragment " + fragment + " popping associated entry " + navBackStackEntry + " via system back");
                        }
                        NavigatorState.this.popWithTransition(navBackStackEntry, false);
                    }
                }
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeStarted(Fragment fragment, boolean z) {
                Object obj;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                if (z) {
                    List list = (List) NavigatorState.this.getBackStack().getValue();
                    ListIterator listIterator = list.listIterator(list.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            obj = null;
                            break;
                        } else {
                            obj = listIterator.previous();
                            if (Intrinsics.areEqual(((NavBackStackEntry) obj).getId(), fragment.getTag())) {
                                break;
                            }
                        }
                    }
                    NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentNavigator", "OnBackStackChangedStarted for fragment " + fragment + " associated with entry " + navBackStackEntry);
                    }
                    if (navBackStackEntry != null) {
                        NavigatorState.this.prepareForTransition(navBackStackEntry);
                    }
                }
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
            }
        });
    }

    @Override // androidx.navigation.Navigator
    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        int lastIndex;
        Object orNull;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        if (this.fragmentManager.isStateSaved()) {
            Log.i("FragmentNavigator", "Ignoring onLaunchSingleTop() call: FragmentManager has already saved its state");
            return;
        }
        FragmentTransaction createFragmentTransaction = createFragmentTransaction(backStackEntry, null);
        List list = (List) getState().getBackStack().getValue();
        if (list.size() > 1) {
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            orNull = CollectionsKt___CollectionsKt.getOrNull(list, lastIndex - 1);
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) orNull;
            if (navBackStackEntry != null) {
                addPendingOps$default(this, navBackStackEntry.getId(), false, false, 6, null);
            }
            addPendingOps$default(this, backStackEntry.getId(), true, false, 4, null);
            this.fragmentManager.popBackStack(backStackEntry.getId(), 1);
            addPendingOps$default(this, backStackEntry.getId(), false, false, 2, null);
            createFragmentTransaction.addToBackStack(backStackEntry.getId());
        }
        createFragmentTransaction.commit();
        getState().onLaunchSingleTop(backStackEntry);
    }

    @Override // androidx.navigation.Navigator
    public void onRestoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        ArrayList<String> stringArrayList = savedState.getStringArrayList("androidx-nav-fragment:navigator:savedIds");
        if (stringArrayList != null) {
            this.savedIds.clear();
            CollectionsKt__MutableCollectionsKt.addAll(this.savedIds, stringArrayList);
        }
    }

    @Override // androidx.navigation.Navigator
    public Bundle onSaveState() {
        if (this.savedIds.isEmpty()) {
            return null;
        }
        return BundleKt.bundleOf(TuplesKt.to("androidx-nav-fragment:navigator:savedIds", new ArrayList(this.savedIds)));
    }

    @Override // androidx.navigation.Navigator
    public void popBackStack(NavBackStackEntry popUpTo, boolean z) {
        Object first;
        Object orNull;
        List<NavBackStackEntry> reversed;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        if (this.fragmentManager.isStateSaved()) {
            Log.i("FragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return;
        }
        List list = (List) getState().getBackStack().getValue();
        int indexOf = list.indexOf(popUpTo);
        List subList = list.subList(indexOf, list.size());
        first = CollectionsKt___CollectionsKt.first(list);
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) first;
        if (z) {
            reversed = CollectionsKt___CollectionsKt.reversed(subList);
            for (NavBackStackEntry navBackStackEntry2 : reversed) {
                if (Intrinsics.areEqual(navBackStackEntry2, navBackStackEntry)) {
                    Log.i("FragmentNavigator", "FragmentManager cannot save the state of the initial destination " + navBackStackEntry2);
                } else {
                    this.fragmentManager.saveBackStack(navBackStackEntry2.getId());
                    this.savedIds.add(navBackStackEntry2.getId());
                }
            }
        } else {
            this.fragmentManager.popBackStack(popUpTo.getId(), 1);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentNavigator", "Calling popWithTransition via popBackStack() on entry " + popUpTo + " with savedState " + z);
        }
        orNull = CollectionsKt___CollectionsKt.getOrNull(list, indexOf - 1);
        NavBackStackEntry navBackStackEntry3 = (NavBackStackEntry) orNull;
        if (navBackStackEntry3 != null) {
            addPendingOps$default(this, navBackStackEntry3.getId(), false, false, 6, null);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : subList) {
            if (!Intrinsics.areEqual(((NavBackStackEntry) obj).getId(), navBackStackEntry.getId())) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            addPendingOps$default(this, ((NavBackStackEntry) it.next()).getId(), true, false, 4, null);
        }
        getState().popWithTransition(popUpTo, z);
    }
}
