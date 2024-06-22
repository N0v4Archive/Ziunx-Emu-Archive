package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavControllerViewModel;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt__ReversedViewsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public abstract class NavController {
    public static final Companion Companion = new Companion(null);
    private static boolean deepLinkSaveState = true;
    private final MutableStateFlow _currentBackStack;
    private final MutableSharedFlow _currentBackStackEntryFlow;
    private NavGraph _graph;
    private NavigatorProvider _navigatorProvider;
    private final MutableStateFlow _visibleEntries;
    private Activity activity;
    private Function1 addToBackStackHandler;
    private final ArrayDeque backQueue;
    private final List backStackEntriesToDispatch;
    private final Map backStackMap;
    private final Map backStackStates;
    private Parcelable[] backStackToRestore;
    private final Map childToParentEntries;
    private final Context context;
    private final StateFlow currentBackStack;
    private final Flow currentBackStackEntryFlow;
    private boolean deepLinkHandled;
    private int dispatchReentrantCount;
    private boolean enableOnBackPressedCallback;
    private final Map entrySavedState;
    private Lifecycle.State hostLifecycleState;
    private NavInflater inflater;
    private final LifecycleObserver lifecycleObserver;
    private LifecycleOwner lifecycleOwner;
    private final Lazy navInflater$delegate;
    private final Map navigatorState;
    private Bundle navigatorStateToRestore;
    private final OnBackPressedCallback onBackPressedCallback;
    private final CopyOnWriteArrayList onDestinationChangedListeners;
    private final Map parentToChildCount;
    private Function1 popFromBackStackHandler;
    private NavControllerViewModel viewModel;
    private final StateFlow visibleEntries;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NavControllerNavigatorState extends NavigatorState {
        private final Navigator navigator;
        final /* synthetic */ NavController this$0;

        public NavControllerNavigatorState(NavController navController, Navigator navigator) {
            Intrinsics.checkNotNullParameter(navigator, "navigator");
            this.this$0 = navController;
            this.navigator = navigator;
        }

        public final void addInternal(NavBackStackEntry backStackEntry) {
            Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
            super.push(backStackEntry);
        }

        @Override // androidx.navigation.NavigatorState
        public NavBackStackEntry createBackStackEntry(NavDestination destination, Bundle bundle) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            return NavBackStackEntry.Companion.create$default(NavBackStackEntry.Companion, this.this$0.getContext(), destination, bundle, this.this$0.getHostLifecycleState$navigation_runtime_release(), this.this$0.viewModel, null, null, 96, null);
        }

        @Override // androidx.navigation.NavigatorState
        public void markTransitionComplete(NavBackStackEntry entry) {
            List mutableList;
            NavControllerViewModel navControllerViewModel;
            Intrinsics.checkNotNullParameter(entry, "entry");
            boolean areEqual = Intrinsics.areEqual(this.this$0.entrySavedState.get(entry), Boolean.TRUE);
            super.markTransitionComplete(entry);
            this.this$0.entrySavedState.remove(entry);
            if (!this.this$0.backQueue.contains(entry)) {
                this.this$0.unlinkChildFromParent$navigation_runtime_release(entry);
                if (entry.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    entry.setMaxLifecycle(Lifecycle.State.DESTROYED);
                }
                ArrayDeque arrayDeque = this.this$0.backQueue;
                boolean z = true;
                if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
                    Iterator<E> it = arrayDeque.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (Intrinsics.areEqual(((NavBackStackEntry) it.next()).getId(), entry.getId())) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z && !areEqual && (navControllerViewModel = this.this$0.viewModel) != null) {
                    navControllerViewModel.clear(entry.getId());
                }
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
            } else {
                if (isNavigating()) {
                    return;
                }
                this.this$0.updateBackStackLifecycle$navigation_runtime_release();
                MutableStateFlow mutableStateFlow = this.this$0._currentBackStack;
                mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.this$0.backQueue);
                mutableStateFlow.tryEmit(mutableList);
            }
            this.this$0._visibleEntries.tryEmit(this.this$0.populateVisibleEntries$navigation_runtime_release());
        }

        @Override // androidx.navigation.NavigatorState
        public void pop(final NavBackStackEntry popUpTo, final boolean z) {
            Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
            Navigator navigator = this.this$0._navigatorProvider.getNavigator(popUpTo.getDestination().getNavigatorName());
            if (!Intrinsics.areEqual(navigator, this.navigator)) {
                Object obj = this.this$0.navigatorState.get(navigator);
                Intrinsics.checkNotNull(obj);
                ((NavControllerNavigatorState) obj).pop(popUpTo, z);
            } else {
                Function1 function1 = this.this$0.popFromBackStackHandler;
                if (function1 == null) {
                    this.this$0.popBackStackFromNavigator$navigation_runtime_release(popUpTo, new Function0() { // from class: androidx.navigation.NavController$NavControllerNavigatorState$pop$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m22invoke();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: collision with other method in class */
                        public final void m22invoke() {
                            super/*androidx.navigation.NavigatorState*/.pop(popUpTo, z);
                        }
                    });
                } else {
                    function1.invoke(popUpTo);
                    super.pop(popUpTo, z);
                }
            }
        }

        @Override // androidx.navigation.NavigatorState
        public void popWithTransition(NavBackStackEntry popUpTo, boolean z) {
            Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
            super.popWithTransition(popUpTo, z);
            this.this$0.entrySavedState.put(popUpTo, Boolean.valueOf(z));
        }

        @Override // androidx.navigation.NavigatorState
        public void prepareForTransition(NavBackStackEntry entry) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            super.prepareForTransition(entry);
            if (!this.this$0.backQueue.contains(entry)) {
                throw new IllegalStateException("Cannot transition entry that is not in the back stack");
            }
            entry.setMaxLifecycle(Lifecycle.State.STARTED);
        }

        @Override // androidx.navigation.NavigatorState
        public void push(NavBackStackEntry backStackEntry) {
            Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
            Navigator navigator = this.this$0._navigatorProvider.getNavigator(backStackEntry.getDestination().getNavigatorName());
            if (!Intrinsics.areEqual(navigator, this.navigator)) {
                Object obj = this.this$0.navigatorState.get(navigator);
                if (obj != null) {
                    ((NavControllerNavigatorState) obj).push(backStackEntry);
                    return;
                }
                throw new IllegalStateException(("NavigatorBackStack for " + backStackEntry.getDestination().getNavigatorName() + " should already be created").toString());
            }
            Function1 function1 = this.this$0.addToBackStackHandler;
            if (function1 != null) {
                function1.invoke(backStackEntry);
                addInternal(backStackEntry);
                return;
            }
            Log.i("NavController", "Ignoring add of destination " + backStackEntry.getDestination() + " outside of the call to navigate(). ");
        }
    }

    /* loaded from: classes.dex */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle);
    }

    public NavController(Context context) {
        Sequence generateSequence;
        Object obj;
        List emptyList;
        List emptyList2;
        Lazy lazy;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        generateSequence = SequencesKt__SequencesKt.generateSequence(context, new Function1() { // from class: androidx.navigation.NavController$activity$1
            @Override // kotlin.jvm.functions.Function1
            public final Context invoke(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it instanceof ContextWrapper) {
                    return ((ContextWrapper) it).getBaseContext();
                }
                return null;
            }
        });
        Iterator it = generateSequence.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((Context) obj) instanceof Activity) {
                    break;
                }
            }
        }
        this.activity = (Activity) obj;
        this.backQueue = new ArrayDeque();
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(emptyList);
        this._currentBackStack = MutableStateFlow;
        this.currentBackStack = FlowKt.asStateFlow(MutableStateFlow);
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(emptyList2);
        this._visibleEntries = MutableStateFlow2;
        this.visibleEntries = FlowKt.asStateFlow(MutableStateFlow2);
        this.childToParentEntries = new LinkedHashMap();
        this.parentToChildCount = new LinkedHashMap();
        this.backStackMap = new LinkedHashMap();
        this.backStackStates = new LinkedHashMap();
        this.onDestinationChangedListeners = new CopyOnWriteArrayList();
        this.hostLifecycleState = Lifecycle.State.INITIALIZED;
        this.lifecycleObserver = new LifecycleEventObserver() { // from class: androidx.navigation.NavController$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                NavController.lifecycleObserver$lambda$2(NavController.this, lifecycleOwner, event);
            }
        };
        this.onBackPressedCallback = new OnBackPressedCallback() { // from class: androidx.navigation.NavController$onBackPressedCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                NavController.this.popBackStack();
            }
        };
        this.enableOnBackPressedCallback = true;
        this._navigatorProvider = new NavigatorProvider();
        this.navigatorState = new LinkedHashMap();
        this.entrySavedState = new LinkedHashMap();
        NavigatorProvider navigatorProvider = this._navigatorProvider;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this._navigatorProvider.addNavigator(new ActivityNavigator(this.context));
        this.backStackEntriesToDispatch = new ArrayList();
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.navigation.NavController$navInflater$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final NavInflater invoke() {
                NavInflater navInflater;
                navInflater = NavController.this.inflater;
                return navInflater == null ? new NavInflater(NavController.this.getContext(), NavController.this._navigatorProvider) : navInflater;
            }
        });
        this.navInflater$delegate = lazy;
        MutableSharedFlow MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
        this._currentBackStackEntryFlow = MutableSharedFlow$default;
        this.currentBackStackEntryFlow = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0230, code lost:
    
        r19 = androidx.navigation.NavBackStackEntry.Companion;
        r0 = r32.context;
        r1 = r32._graph;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r2 = r32._graph;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
        r18 = androidx.navigation.NavBackStackEntry.Companion.create$default(r19, r0, r1, r2.addInDefaultArgs(r14), getHostLifecycleState$navigation_runtime_release(), r32.viewModel, null, null, 96, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x025a, code lost:
    
        r11.addFirst(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x025f, code lost:
    
        r0 = r11.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0267, code lost:
    
        if (r0.hasNext() == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0269, code lost:
    
        r1 = (androidx.navigation.NavBackStackEntry) r0.next();
        r2 = r32.navigatorState.get(r32._navigatorProvider.getNavigator(r1.getDestination().getNavigatorName()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0283, code lost:
    
        if (r2 == null) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0285, code lost:
    
        ((androidx.navigation.NavController.NavControllerNavigatorState) r2).addInternal(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02ae, code lost:
    
        throw new java.lang.IllegalStateException(("NavigatorBackStack for " + r33.getNavigatorName() + " should already be created").toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02af, code lost:
    
        r32.backQueue.addAll(r11);
        r32.backQueue.add(r8);
        r0 = kotlin.collections.CollectionsKt___CollectionsKt.plus(r11, r8);
        r0 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02c5, code lost:
    
        if (r0.hasNext() == false) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02c7, code lost:
    
        r1 = (androidx.navigation.NavBackStackEntry) r0.next();
        r2 = r1.getDestination().getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x02d5, code lost:
    
        if (r2 == null) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02d7, code lost:
    
        linkChildToParent(r1, getBackStackEntry(r2.getId()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02e3, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01fc, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0193, code lost:
    
        r12 = ((androidx.navigation.NavBackStackEntry) r11.first()).getDestination();
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0112, code lost:
    
        r12 = ((androidx.navigation.NavBackStackEntry) r11.first()).getDestination();
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x00e8, code lost:
    
        r13 = r0;
        r14 = r2;
        r9 = r3;
        r10 = r4;
        r11 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x00ae, code lost:
    
        r20 = r12;
        r0 = r13;
        r8 = r14;
        r2 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x007c, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x00ee, code lost:
    
        r9 = r3;
        r10 = r4;
        r11 = r5;
        r20 = r12;
        r8 = r14;
        r14 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0046, code lost:
    
        r5 = new kotlin.collections.ArrayDeque();
        r4 = true;
        r18 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0103, code lost:
    
        r10 = true;
        r11 = r5;
        r20 = r12;
        r8 = r14;
        r14 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
    
        if ((r33 instanceof androidx.navigation.NavGraph) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0052, code lost:
    
        r0 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r3 = r0.getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        if (r3 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005c, code lost:
    
        r0 = r13.listIterator(r36.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
    
        if (r0.hasPrevious() == false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006a, code lost:
    
        r1 = r0.previous();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0079, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(((androidx.navigation.NavBackStackEntry) r1).getDestination(), r3) == false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
    
        r1 = (androidx.navigation.NavBackStackEntry) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0080, code lost:
    
        if (r1 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0082, code lost:
    
        r20 = r12;
        r0 = r13;
        r2 = r15;
        r8 = r14;
        r1 = androidx.navigation.NavBackStackEntry.Companion.create$default(androidx.navigation.NavBackStackEntry.Companion, r32.context, r3, r34, getHostLifecycleState$navigation_runtime_release(), r32.viewModel, null, null, 96, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b3, code lost:
    
        r5.addFirst(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00bd, code lost:
    
        if ((r32.backQueue.isEmpty() ^ r4) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0010, code lost:
    
        if ((r12 instanceof androidx.navigation.FloatingWindow) == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00cb, code lost:
    
        if (((androidx.navigation.NavBackStackEntry) r32.backQueue.last()).getDestination() != r3) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cd, code lost:
    
        r13 = r0;
        r14 = r2;
        r9 = r3;
        r10 = r4;
        r11 = r5;
        popEntryFromBackStack$default(r32, (androidx.navigation.NavBackStackEntry) r32.backQueue.last(), false, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00f5, code lost:
    
        if (r9 == null) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00f7, code lost:
    
        if (r9 != r33) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00fa, code lost:
    
        r0 = r9;
        r4 = r10;
        r5 = r11;
        r15 = r14;
        r12 = r20;
        r14 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010d, code lost:
    
        if (r11.isEmpty() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x010f, code lost:
    
        r12 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011d, code lost:
    
        if (r12 == null) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0127, code lost:
    
        if (findDestination(r12.getId()) == r12) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0129, code lost:
    
        r12 = r12.getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x012d, code lost:
    
        if (r12 == null) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012f, code lost:
    
        if (r14 == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0135, code lost:
    
        if (r34.isEmpty() != r10) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
    
        if (r32.backQueue.isEmpty() != false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0137, code lost:
    
        r4 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x013a, code lost:
    
        if (r4 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013c, code lost:
    
        r15 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0140, code lost:
    
        r0 = r13.listIterator(r36.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x014c, code lost:
    
        if (r0.hasPrevious() == false) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x014e, code lost:
    
        r1 = r0.previous();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x015d, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(((androidx.navigation.NavBackStackEntry) r1).getDestination(), r12) == false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0162, code lost:
    
        r1 = (androidx.navigation.NavBackStackEntry) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0164, code lost:
    
        if (r1 != null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0166, code lost:
    
        r1 = androidx.navigation.NavBackStackEntry.Companion.create$default(androidx.navigation.NavBackStackEntry.Companion, r32.context, r12, r12.addInDefaultArgs(r15), getHostLifecycleState$navigation_runtime_release(), r32.viewModel, null, null, 96, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0186, code lost:
    
        r11.addFirst(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0160, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x013f, code lost:
    
        r15 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0139, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0028, code lost:
    
        if ((((androidx.navigation.NavBackStackEntry) r32.backQueue.last()).getDestination() instanceof androidx.navigation.FloatingWindow) == false) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x018e, code lost:
    
        if (r11.isEmpty() == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0190, code lost:
    
        r12 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a3, code lost:
    
        if (r32.backQueue.isEmpty() != false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01b3, code lost:
    
        if ((((androidx.navigation.NavBackStackEntry) r32.backQueue.last()).getDestination() instanceof androidx.navigation.NavGraph) == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b5, code lost:
    
        r0 = ((androidx.navigation.NavBackStackEntry) r32.backQueue.last()).getDestination();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0, "null cannot be cast to non-null type androidx.navigation.NavGraph");
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d0, code lost:
    
        if (((androidx.navigation.NavGraph) r0).findNode(r12.getId(), false) != null) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d2, code lost:
    
        popEntryFromBackStack$default(r32, (androidx.navigation.NavBackStackEntry) r32.backQueue.last(), false, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01e5, code lost:
    
        r0 = (androidx.navigation.NavBackStackEntry) r32.backQueue.firstOrNull();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01ed, code lost:
    
        if (r0 != null) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01ef, code lost:
    
        r0 = (androidx.navigation.NavBackStackEntry) r11.firstOrNull();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01f5, code lost:
    
        if (r0 == null) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01f7, code lost:
    
        r0 = r0.getDestination();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0044, code lost:
    
        if (popBackStackInternal$default(r32, ((androidx.navigation.NavBackStackEntry) r32.backQueue.last()).getDestination().getId(), true, false, 4, null) != false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0204, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, r32._graph) != false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0206, code lost:
    
        r0 = r13.listIterator(r36.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0212, code lost:
    
        if (r0.hasPrevious() == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0214, code lost:
    
        r1 = r0.previous();
        r2 = ((androidx.navigation.NavBackStackEntry) r1).getDestination();
        r3 = r32._graph;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0228, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r2, r3) == false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x022a, code lost:
    
        r18 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x022c, code lost:
    
        r18 = (androidx.navigation.NavBackStackEntry) r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x022e, code lost:
    
        if (r18 != null) goto L95;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addEntryToBackStack(androidx.navigation.NavDestination r33, android.os.Bundle r34, androidx.navigation.NavBackStackEntry r35, java.util.List r36) {
        /*
            Method dump skipped, instructions count: 740
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.addEntryToBackStack(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavBackStackEntry, java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void addEntryToBackStack$default(NavController navController, NavDestination navDestination, Bundle bundle, NavBackStackEntry navBackStackEntry, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addEntryToBackStack");
        }
        if ((i & 8) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        navController.addEntryToBackStack(navDestination, bundle, navBackStackEntry, list);
    }

    private final boolean clearBackStackInternal(int i) {
        Iterator it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            ((NavControllerNavigatorState) it.next()).setNavigating(true);
        }
        boolean restoreStateInternal = restoreStateInternal(i, null, NavOptionsBuilderKt.navOptions(new Function1() { // from class: androidx.navigation.NavController$clearBackStackInternal$restored$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((NavOptionsBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(NavOptionsBuilder navOptions) {
                Intrinsics.checkNotNullParameter(navOptions, "$this$navOptions");
                navOptions.setRestoreState(true);
            }
        }), null);
        Iterator it2 = this.navigatorState.values().iterator();
        while (it2.hasNext()) {
            ((NavControllerNavigatorState) it2.next()).setNavigating(false);
        }
        return restoreStateInternal && popBackStackInternal(i, true, false);
    }

    private final boolean dispatchOnDestinationChanged() {
        List<NavBackStackEntry> mutableList;
        List mutableList2;
        while (!this.backQueue.isEmpty() && (((NavBackStackEntry) this.backQueue.last()).getDestination() instanceof NavGraph)) {
            popEntryFromBackStack$default(this, (NavBackStackEntry) this.backQueue.last(), false, null, 6, null);
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry != null) {
            this.backStackEntriesToDispatch.add(navBackStackEntry);
        }
        this.dispatchReentrantCount++;
        updateBackStackLifecycle$navigation_runtime_release();
        int i = this.dispatchReentrantCount - 1;
        this.dispatchReentrantCount = i;
        if (i == 0) {
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.backStackEntriesToDispatch);
            this.backStackEntriesToDispatch.clear();
            for (NavBackStackEntry navBackStackEntry2 : mutableList) {
                Iterator it = this.onDestinationChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDestinationChangedListener) it.next()).onDestinationChanged(this, navBackStackEntry2.getDestination(), navBackStackEntry2.getArguments());
                }
                this._currentBackStackEntryFlow.tryEmit(navBackStackEntry2);
            }
            MutableStateFlow mutableStateFlow = this._currentBackStack;
            mutableList2 = CollectionsKt___CollectionsKt.toMutableList((Collection) this.backQueue);
            mutableStateFlow.tryEmit(mutableList2);
            this._visibleEntries.tryEmit(populateVisibleEntries$navigation_runtime_release());
        }
        return navBackStackEntry != null;
    }

    private final boolean executePopOperations(List list, NavDestination navDestination, boolean z, final boolean z2) {
        Sequence generateSequence;
        Sequence takeWhile;
        Sequence generateSequence2;
        Sequence<NavDestination> takeWhile2;
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        final ArrayDeque arrayDeque = new ArrayDeque();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Navigator navigator = (Navigator) it.next();
            final Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
            popBackStackInternal(navigator, (NavBackStackEntry) this.backQueue.last(), z2, new Function1() { // from class: androidx.navigation.NavController$executePopOperations$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((NavBackStackEntry) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(NavBackStackEntry entry) {
                    Intrinsics.checkNotNullParameter(entry, "entry");
                    Ref$BooleanRef.this.element = true;
                    ref$BooleanRef.element = true;
                    this.popEntryFromBackStack(entry, z2, arrayDeque);
                }
            });
            if (!ref$BooleanRef2.element) {
                break;
            }
        }
        if (z2) {
            if (!z) {
                generateSequence2 = SequencesKt__SequencesKt.generateSequence(navDestination, new Function1() { // from class: androidx.navigation.NavController$executePopOperations$2
                    @Override // kotlin.jvm.functions.Function1
                    public final NavDestination invoke(NavDestination destination) {
                        Intrinsics.checkNotNullParameter(destination, "destination");
                        NavGraph parent = destination.getParent();
                        boolean z3 = false;
                        if (parent != null && parent.getStartDestinationId() == destination.getId()) {
                            z3 = true;
                        }
                        if (z3) {
                            return destination.getParent();
                        }
                        return null;
                    }
                });
                takeWhile2 = SequencesKt___SequencesKt.takeWhile(generateSequence2, new Function1() { // from class: androidx.navigation.NavController$executePopOperations$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(NavDestination destination) {
                        Map map;
                        Intrinsics.checkNotNullParameter(destination, "destination");
                        map = NavController.this.backStackMap;
                        return Boolean.valueOf(!map.containsKey(Integer.valueOf(destination.getId())));
                    }
                });
                for (NavDestination navDestination2 : takeWhile2) {
                    Map map = this.backStackMap;
                    Integer valueOf = Integer.valueOf(navDestination2.getId());
                    NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) arrayDeque.firstOrNull();
                    map.put(valueOf, navBackStackEntryState != null ? navBackStackEntryState.getId() : null);
                }
            }
            if (!arrayDeque.isEmpty()) {
                NavBackStackEntryState navBackStackEntryState2 = (NavBackStackEntryState) arrayDeque.first();
                generateSequence = SequencesKt__SequencesKt.generateSequence(findDestination(navBackStackEntryState2.getDestinationId()), new Function1() { // from class: androidx.navigation.NavController$executePopOperations$5
                    @Override // kotlin.jvm.functions.Function1
                    public final NavDestination invoke(NavDestination destination) {
                        Intrinsics.checkNotNullParameter(destination, "destination");
                        NavGraph parent = destination.getParent();
                        boolean z3 = false;
                        if (parent != null && parent.getStartDestinationId() == destination.getId()) {
                            z3 = true;
                        }
                        if (z3) {
                            return destination.getParent();
                        }
                        return null;
                    }
                });
                takeWhile = SequencesKt___SequencesKt.takeWhile(generateSequence, new Function1() { // from class: androidx.navigation.NavController$executePopOperations$6
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(NavDestination destination) {
                        Map map2;
                        Intrinsics.checkNotNullParameter(destination, "destination");
                        map2 = NavController.this.backStackMap;
                        return Boolean.valueOf(!map2.containsKey(Integer.valueOf(destination.getId())));
                    }
                });
                Iterator it2 = takeWhile.iterator();
                while (it2.hasNext()) {
                    this.backStackMap.put(Integer.valueOf(((NavDestination) it2.next()).getId()), navBackStackEntryState2.getId());
                }
                this.backStackStates.put(navBackStackEntryState2.getId(), arrayDeque);
            }
        }
        updateOnBackPressedCallbackEnabled();
        return ref$BooleanRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0065 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean executeRestoreState(final java.util.List r12, final android.os.Bundle r13, androidx.navigation.NavOptions r14, androidx.navigation.Navigator.Extras r15) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r12.iterator()
        Le:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L27
            java.lang.Object r3 = r2.next()
            r4 = r3
            androidx.navigation.NavBackStackEntry r4 = (androidx.navigation.NavBackStackEntry) r4
            androidx.navigation.NavDestination r4 = r4.getDestination()
            boolean r4 = r4 instanceof androidx.navigation.NavGraph
            if (r4 != 0) goto Le
            r1.add(r3)
            goto Le
        L27:
            java.util.Iterator r1 = r1.iterator()
        L2b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L71
            java.lang.Object r2 = r1.next()
            androidx.navigation.NavBackStackEntry r2 = (androidx.navigation.NavBackStackEntry) r2
            java.lang.Object r3 = kotlin.collections.CollectionsKt.lastOrNull(r0)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L52
            java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r3)
            androidx.navigation.NavBackStackEntry r4 = (androidx.navigation.NavBackStackEntry) r4
            if (r4 == 0) goto L52
            androidx.navigation.NavDestination r4 = r4.getDestination()
            if (r4 == 0) goto L52
            java.lang.String r4 = r4.getNavigatorName()
            goto L53
        L52:
            r4 = 0
        L53:
            androidx.navigation.NavDestination r5 = r2.getDestination()
            java.lang.String r5 = r5.getNavigatorName()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L65
            r3.add(r2)
            goto L2b
        L65:
            androidx.navigation.NavBackStackEntry[] r2 = new androidx.navigation.NavBackStackEntry[]{r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.mutableListOf(r2)
            r0.add(r2)
            goto L2b
        L71:
            kotlin.jvm.internal.Ref$BooleanRef r1 = new kotlin.jvm.internal.Ref$BooleanRef
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L7a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Lb3
            java.lang.Object r2 = r0.next()
            java.util.List r2 = (java.util.List) r2
            androidx.navigation.NavigatorProvider r3 = r11._navigatorProvider
            java.lang.Object r4 = kotlin.collections.CollectionsKt.first(r2)
            androidx.navigation.NavBackStackEntry r4 = (androidx.navigation.NavBackStackEntry) r4
            androidx.navigation.NavDestination r4 = r4.getDestination()
            java.lang.String r4 = r4.getNavigatorName()
            androidx.navigation.Navigator r9 = r3.getNavigator(r4)
            kotlin.jvm.internal.Ref$IntRef r6 = new kotlin.jvm.internal.Ref$IntRef
            r6.<init>()
            androidx.navigation.NavController$executeRestoreState$3 r10 = new androidx.navigation.NavController$executeRestoreState$3
            r3 = r10
            r4 = r1
            r5 = r12
            r7 = r11
            r8 = r13
            r3.<init>()
            r3 = r11
            r4 = r9
            r5 = r2
            r6 = r14
            r7 = r15
            r8 = r10
            r3.navigateInternal(r4, r5, r6, r7, r8)
            goto L7a
        Lb3:
            boolean r11 = r1.element
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.executeRestoreState(java.util.List, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):boolean");
    }

    private final NavDestination findDestination(NavDestination navDestination, int i) {
        NavGraph parent;
        if (navDestination.getId() == i) {
            return navDestination;
        }
        if (navDestination instanceof NavGraph) {
            parent = (NavGraph) navDestination;
        } else {
            parent = navDestination.getParent();
            Intrinsics.checkNotNull(parent);
        }
        return parent.findNode(i);
    }

    private final String findInvalidDestinationDisplayNameInDeepLink(int[] iArr) {
        NavGraph navGraph;
        NavGraph navGraph2 = this._graph;
        int length = iArr.length;
        int i = 0;
        while (true) {
            NavDestination navDestination = null;
            if (i >= length) {
                return null;
            }
            int i2 = iArr[i];
            if (i == 0) {
                NavGraph navGraph3 = this._graph;
                Intrinsics.checkNotNull(navGraph3);
                if (navGraph3.getId() == i2) {
                    navDestination = this._graph;
                }
            } else {
                Intrinsics.checkNotNull(navGraph2);
                navDestination = navGraph2.findNode(i2);
            }
            if (navDestination == null) {
                return NavDestination.Companion.getDisplayName(this.context, i2);
            }
            if (i != iArr.length - 1 && (navDestination instanceof NavGraph)) {
                while (true) {
                    navGraph = (NavGraph) navDestination;
                    Intrinsics.checkNotNull(navGraph);
                    if (!(navGraph.findNode(navGraph.getStartDestinationId()) instanceof NavGraph)) {
                        break;
                    }
                    navDestination = navGraph.findNode(navGraph.getStartDestinationId());
                }
                navGraph2 = navGraph;
            }
            i++;
        }
    }

    private final int getDestinationCountOnBackStack() {
        ArrayDeque arrayDeque = this.backQueue;
        int i = 0;
        if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
            Iterator<E> it = arrayDeque.iterator();
            while (it.hasNext()) {
                if ((!(((NavBackStackEntry) it.next()).getDestination() instanceof NavGraph)) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    private final List instantiateBackStack(ArrayDeque arrayDeque) {
        NavDestination graph;
        ArrayList arrayList = new ArrayList();
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry == null || (graph = navBackStackEntry.getDestination()) == null) {
            graph = getGraph();
        }
        if (arrayDeque != null) {
            Iterator<E> it = arrayDeque.iterator();
            while (it.hasNext()) {
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) it.next();
                NavDestination findDestination = findDestination(graph, navBackStackEntryState.getDestinationId());
                if (findDestination == null) {
                    throw new IllegalStateException(("Restore State failed: destination " + NavDestination.Companion.getDisplayName(this.context, navBackStackEntryState.getDestinationId()) + " cannot be found from the current destination " + graph).toString());
                }
                arrayList.add(navBackStackEntryState.instantiate(this.context, findDestination, getHostLifecycleState$navigation_runtime_release(), this.viewModel));
                graph = findDestination;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Incorrect condition in loop: B:26:0x0062 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean launchSingleTopInternal(androidx.navigation.NavDestination r6, android.os.Bundle r7) {
        /*
            r5 = this;
            androidx.navigation.NavBackStackEntry r0 = r5.getCurrentBackStackEntry()
            boolean r1 = r6 instanceof androidx.navigation.NavGraph
            if (r1 == 0) goto L16
            androidx.navigation.NavGraph$Companion r1 = androidx.navigation.NavGraph.Companion
            r2 = r6
            androidx.navigation.NavGraph r2 = (androidx.navigation.NavGraph) r2
            androidx.navigation.NavDestination r1 = r1.findStartDestination(r2)
            int r1 = r1.getId()
            goto L1a
        L16:
            int r1 = r6.getId()
        L1a:
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L2c
            androidx.navigation.NavDestination r0 = r0.getDestination()
            if (r0 == 0) goto L2c
            int r0 = r0.getId()
            if (r1 != r0) goto L2c
            r0 = r2
            goto L2d
        L2c:
            r0 = r3
        L2d:
            if (r0 != 0) goto L30
            return r3
        L30:
            kotlin.collections.ArrayDeque r0 = new kotlin.collections.ArrayDeque
            r0.<init>()
            kotlin.collections.ArrayDeque r1 = r5.backQueue
            int r4 = r1.size()
            java.util.ListIterator r1 = r1.listIterator(r4)
        L3f:
            boolean r4 = r1.hasPrevious()
            if (r4 == 0) goto L5b
            java.lang.Object r4 = r1.previous()
            androidx.navigation.NavBackStackEntry r4 = (androidx.navigation.NavBackStackEntry) r4
            androidx.navigation.NavDestination r4 = r4.getDestination()
            if (r4 != r6) goto L53
            r4 = r2
            goto L54
        L53:
            r4 = r3
        L54:
            if (r4 == 0) goto L3f
            int r6 = r1.nextIndex()
            goto L5c
        L5b:
            r6 = -1
        L5c:
            kotlin.collections.ArrayDeque r1 = r5.backQueue
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r1 < r6) goto L80
            kotlin.collections.ArrayDeque r1 = r5.backQueue
            java.lang.Object r1 = r1.removeLast()
            androidx.navigation.NavBackStackEntry r1 = (androidx.navigation.NavBackStackEntry) r1
            r5.unlinkChildFromParent$navigation_runtime_release(r1)
            androidx.navigation.NavBackStackEntry r3 = new androidx.navigation.NavBackStackEntry
            androidx.navigation.NavDestination r4 = r1.getDestination()
            android.os.Bundle r4 = r4.addInDefaultArgs(r7)
            r3.<init>(r1, r4)
            r0.addFirst(r3)
            goto L5c
        L80:
            java.util.Iterator r6 = r0.iterator()
        L84:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto Lab
            java.lang.Object r7 = r6.next()
            androidx.navigation.NavBackStackEntry r7 = (androidx.navigation.NavBackStackEntry) r7
            androidx.navigation.NavDestination r1 = r7.getDestination()
            androidx.navigation.NavGraph r1 = r1.getParent()
            if (r1 == 0) goto La5
            int r1 = r1.getId()
            androidx.navigation.NavBackStackEntry r1 = r5.getBackStackEntry(r1)
            r5.linkChildToParent(r7, r1)
        La5:
            kotlin.collections.ArrayDeque r1 = r5.backQueue
            r1.add(r7)
            goto L84
        Lab:
            java.util.Iterator r6 = r0.iterator()
        Laf:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto Lcd
            java.lang.Object r7 = r6.next()
            androidx.navigation.NavBackStackEntry r7 = (androidx.navigation.NavBackStackEntry) r7
            androidx.navigation.NavigatorProvider r0 = r5._navigatorProvider
            androidx.navigation.NavDestination r1 = r7.getDestination()
            java.lang.String r1 = r1.getNavigatorName()
            androidx.navigation.Navigator r0 = r0.getNavigator(r1)
            r0.onLaunchSingleTop(r7)
            goto Laf
        Lcd:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.launchSingleTopInternal(androidx.navigation.NavDestination, android.os.Bundle):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$2(NavController this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        this$0.hostLifecycleState = event.getTargetState();
        if (this$0._graph != null) {
            Iterator<E> it = this$0.backQueue.iterator();
            while (it.hasNext()) {
                ((NavBackStackEntry) it.next()).handleLifecycleEvent(event);
            }
        }
    }

    private final void linkChildToParent(NavBackStackEntry navBackStackEntry, NavBackStackEntry navBackStackEntry2) {
        this.childToParentEntries.put(navBackStackEntry, navBackStackEntry2);
        if (this.parentToChildCount.get(navBackStackEntry2) == null) {
            this.parentToChildCount.put(navBackStackEntry2, new AtomicInteger(0));
        }
        Object obj = this.parentToChildCount.get(navBackStackEntry2);
        Intrinsics.checkNotNull(obj);
        ((AtomicInteger) obj).incrementAndGet();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00f4 A[LOOP:1: B:22:0x00ee->B:24:0x00f4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void navigate(final androidx.navigation.NavDestination r22, android.os.Bundle r23, androidx.navigation.NavOptions r24, androidx.navigation.Navigator.Extras r25) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.navigate(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):void");
    }

    private final void navigateInternal(Navigator navigator, List list, NavOptions navOptions, Navigator.Extras extras, Function1 function1) {
        this.addToBackStackHandler = function1;
        navigator.navigate(list, navOptions, extras);
        this.addToBackStackHandler = null;
    }

    private final void onGraphCreated(Bundle bundle) {
        Activity activity;
        ArrayList<String> stringArrayList;
        Bundle bundle2 = this.navigatorStateToRestore;
        if (bundle2 != null && (stringArrayList = bundle2.getStringArrayList("android-support-nav:controller:navigatorState:names")) != null) {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                String name = it.next();
                NavigatorProvider navigatorProvider = this._navigatorProvider;
                Intrinsics.checkNotNullExpressionValue(name, "name");
                Navigator navigator = navigatorProvider.getNavigator(name);
                Bundle bundle3 = bundle2.getBundle(name);
                if (bundle3 != null) {
                    navigator.onRestoreState(bundle3);
                }
            }
        }
        Parcelable[] parcelableArr = this.backStackToRestore;
        boolean z = false;
        if (parcelableArr != null) {
            for (Parcelable parcelable : parcelableArr) {
                Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type androidx.navigation.NavBackStackEntryState");
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) parcelable;
                NavDestination findDestination = findDestination(navBackStackEntryState.getDestinationId());
                if (findDestination == null) {
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + NavDestination.Companion.getDisplayName(this.context, navBackStackEntryState.getDestinationId()) + " cannot be found from the current destination " + getCurrentDestination());
                }
                NavBackStackEntry instantiate = navBackStackEntryState.instantiate(this.context, findDestination, getHostLifecycleState$navigation_runtime_release(), this.viewModel);
                Navigator navigator2 = this._navigatorProvider.getNavigator(findDestination.getNavigatorName());
                Map map = this.navigatorState;
                Object obj = map.get(navigator2);
                if (obj == null) {
                    obj = new NavControllerNavigatorState(this, navigator2);
                    map.put(navigator2, obj);
                }
                this.backQueue.add(instantiate);
                ((NavControllerNavigatorState) obj).addInternal(instantiate);
                NavGraph parent = instantiate.getDestination().getParent();
                if (parent != null) {
                    linkChildToParent(instantiate, getBackStackEntry(parent.getId()));
                }
            }
            updateOnBackPressedCallbackEnabled();
            this.backStackToRestore = null;
        }
        Collection values = this._navigatorProvider.getNavigators().values();
        ArrayList<Navigator> arrayList = new ArrayList();
        for (Object obj2 : values) {
            if (!((Navigator) obj2).isAttached()) {
                arrayList.add(obj2);
            }
        }
        for (Navigator navigator3 : arrayList) {
            Map map2 = this.navigatorState;
            Object obj3 = map2.get(navigator3);
            if (obj3 == null) {
                obj3 = new NavControllerNavigatorState(this, navigator3);
                map2.put(navigator3, obj3);
            }
            navigator3.onAttach((NavControllerNavigatorState) obj3);
        }
        if (this._graph == null || !this.backQueue.isEmpty()) {
            dispatchOnDestinationChanged();
            return;
        }
        if (!this.deepLinkHandled && (activity = this.activity) != null) {
            Intrinsics.checkNotNull(activity);
            if (handleDeepLink(activity.getIntent())) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        NavGraph navGraph = this._graph;
        Intrinsics.checkNotNull(navGraph);
        navigate(navGraph, bundle, (NavOptions) null, (Navigator.Extras) null);
    }

    public static /* synthetic */ boolean popBackStack$default(NavController navController, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popBackStack");
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return navController.popBackStack(str, z, z2);
    }

    private final void popBackStackInternal(Navigator navigator, NavBackStackEntry navBackStackEntry, boolean z, Function1 function1) {
        this.popFromBackStackHandler = function1;
        navigator.popBackStack(navBackStackEntry, z);
        this.popFromBackStackHandler = null;
    }

    private final boolean popBackStackInternal(int i, boolean z, boolean z2) {
        List reversed;
        NavDestination navDestination;
        if (this.backQueue.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        reversed = CollectionsKt___CollectionsKt.reversed(this.backQueue);
        Iterator it = reversed.iterator();
        while (true) {
            if (!it.hasNext()) {
                navDestination = null;
                break;
            }
            navDestination = ((NavBackStackEntry) it.next()).getDestination();
            Navigator navigator = this._navigatorProvider.getNavigator(navDestination.getNavigatorName());
            if (z || navDestination.getId() != i) {
                arrayList.add(navigator);
            }
            if (navDestination.getId() == i) {
                break;
            }
        }
        if (navDestination != null) {
            return executePopOperations(arrayList, navDestination, z, z2);
        }
        Log.i("NavController", "Ignoring popBackStack to destination " + NavDestination.Companion.getDisplayName(this.context, i) + " as it was not found on the current back stack");
        return false;
    }

    private final boolean popBackStackInternal(String str, boolean z, boolean z2) {
        Object obj;
        if (this.backQueue.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = this.backQueue;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
            boolean hasRoute = navBackStackEntry.getDestination().hasRoute(str, navBackStackEntry.getArguments());
            if (z || !hasRoute) {
                arrayList.add(this._navigatorProvider.getNavigator(navBackStackEntry.getDestination().getNavigatorName()));
            }
            if (hasRoute) {
                break;
            }
        }
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj;
        NavDestination destination = navBackStackEntry2 != null ? navBackStackEntry2.getDestination() : null;
        if (destination != null) {
            return executePopOperations(arrayList, destination, z, z2);
        }
        Log.i("NavController", "Ignoring popBackStack to route " + str + " as it was not found on the current back stack");
        return false;
    }

    static /* synthetic */ boolean popBackStackInternal$default(NavController navController, int i, boolean z, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popBackStackInternal");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return navController.popBackStackInternal(i, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void popEntryFromBackStack(NavBackStackEntry navBackStackEntry, boolean z, ArrayDeque arrayDeque) {
        NavControllerViewModel navControllerViewModel;
        StateFlow transitionsInProgress;
        Set set;
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) this.backQueue.last();
        if (!Intrinsics.areEqual(navBackStackEntry2, navBackStackEntry)) {
            throw new IllegalStateException(("Attempted to pop " + navBackStackEntry.getDestination() + ", which is not the top of the back stack (" + navBackStackEntry2.getDestination() + ')').toString());
        }
        this.backQueue.removeLast();
        NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(getNavigatorProvider().getNavigator(navBackStackEntry2.getDestination().getNavigatorName()));
        boolean z2 = true;
        if (!((navControllerNavigatorState == null || (transitionsInProgress = navControllerNavigatorState.getTransitionsInProgress()) == null || (set = (Set) transitionsInProgress.getValue()) == null || !set.contains(navBackStackEntry2)) ? false : true) && !this.parentToChildCount.containsKey(navBackStackEntry2)) {
            z2 = false;
        }
        Lifecycle.State currentState = navBackStackEntry2.getLifecycle().getCurrentState();
        Lifecycle.State state = Lifecycle.State.CREATED;
        if (currentState.isAtLeast(state)) {
            if (z) {
                navBackStackEntry2.setMaxLifecycle(state);
                arrayDeque.addFirst(new NavBackStackEntryState(navBackStackEntry2));
            }
            if (z2) {
                navBackStackEntry2.setMaxLifecycle(state);
            } else {
                navBackStackEntry2.setMaxLifecycle(Lifecycle.State.DESTROYED);
                unlinkChildFromParent$navigation_runtime_release(navBackStackEntry2);
            }
        }
        if (z || z2 || (navControllerViewModel = this.viewModel) == null) {
            return;
        }
        navControllerViewModel.clear(navBackStackEntry2.getId());
    }

    static /* synthetic */ void popEntryFromBackStack$default(NavController navController, NavBackStackEntry navBackStackEntry, boolean z, ArrayDeque arrayDeque, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: popEntryFromBackStack");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            arrayDeque = new ArrayDeque();
        }
        navController.popEntryFromBackStack(navBackStackEntry, z, arrayDeque);
    }

    private final boolean restoreStateInternal(int i, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        if (!this.backStackMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        final String str = (String) this.backStackMap.get(Integer.valueOf(i));
        CollectionsKt__MutableCollectionsKt.removeAll(this.backStackMap.values(), new Function1() { // from class: androidx.navigation.NavController$restoreStateInternal$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str2) {
                return Boolean.valueOf(Intrinsics.areEqual(str2, str));
            }
        });
        return executeRestoreState(instantiateBackStack((ArrayDeque) TypeIntrinsics.asMutableMap(this.backStackStates).remove(str)), bundle, navOptions, extras);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
    
        if (getDestinationCountOnBackStack() > 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateOnBackPressedCallbackEnabled() {
        /*
            r2 = this;
            androidx.activity.OnBackPressedCallback r0 = r2.onBackPressedCallback
            boolean r1 = r2.enableOnBackPressedCallback
            if (r1 == 0) goto Le
            int r2 = r2.getDestinationCountOnBackStack()
            r1 = 1
            if (r2 <= r1) goto Le
            goto Lf
        Le:
            r1 = 0
        Lf:
            r0.setEnabled(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.updateOnBackPressedCallbackEnabled():void");
    }

    public void addOnDestinationChangedListener(OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.add(listener);
        if (!this.backQueue.isEmpty()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.last();
            listener.onDestinationChanged(this, navBackStackEntry.getDestination(), navBackStackEntry.getArguments());
        }
    }

    public final NavDestination findDestination(int i) {
        NavDestination navDestination;
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        Intrinsics.checkNotNull(navGraph);
        if (navGraph.getId() == i) {
            return this._graph;
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.backQueue.lastOrNull();
        if (navBackStackEntry == null || (navDestination = navBackStackEntry.getDestination()) == null) {
            navDestination = this._graph;
            Intrinsics.checkNotNull(navDestination);
        }
        return findDestination(navDestination, i);
    }

    public NavBackStackEntry getBackStackEntry(int i) {
        Object obj;
        ArrayDeque arrayDeque = this.backQueue;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            if (((NavBackStackEntry) obj).getDestination().getId() == i) {
                break;
            }
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException(("No destination with ID " + i + " is on the NavController's back stack. The current destination is " + getCurrentDestination()).toString());
    }

    public final Context getContext() {
        return this.context;
    }

    public NavBackStackEntry getCurrentBackStackEntry() {
        return (NavBackStackEntry) this.backQueue.lastOrNull();
    }

    public NavDestination getCurrentDestination() {
        NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            return currentBackStackEntry.getDestination();
        }
        return null;
    }

    public NavGraph getGraph() {
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()".toString());
        }
        Intrinsics.checkNotNull(navGraph, "null cannot be cast to non-null type androidx.navigation.NavGraph");
        return navGraph;
    }

    public final Lifecycle.State getHostLifecycleState$navigation_runtime_release() {
        return this.lifecycleOwner == null ? Lifecycle.State.CREATED : this.hostLifecycleState;
    }

    public NavInflater getNavInflater() {
        return (NavInflater) this.navInflater$delegate.getValue();
    }

    public NavigatorProvider getNavigatorProvider() {
        return this._navigatorProvider;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
    
        if ((r0.length == 0) != false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handleDeepLink(android.content.Intent r20) {
        /*
            Method dump skipped, instructions count: 505
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.handleDeepLink(android.content.Intent):boolean");
    }

    public void navigate(int i) {
        navigate(i, null);
    }

    public void navigate(int i, Bundle bundle) {
        navigate(i, bundle, null);
    }

    public void navigate(int i, Bundle bundle, NavOptions navOptions) {
        navigate(i, bundle, navOptions, (Navigator.Extras) null);
    }

    public void navigate(int i, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        int i2;
        NavDestination destination = this.backQueue.isEmpty() ? this._graph : ((NavBackStackEntry) this.backQueue.last()).getDestination();
        if (destination == null) {
            throw new IllegalStateException("No current destination found. Ensure a navigation graph has been set for NavController " + this + '.');
        }
        NavAction action = destination.getAction(i);
        Bundle bundle2 = null;
        if (action != null) {
            if (navOptions == null) {
                navOptions = action.getNavOptions();
            }
            i2 = action.getDestinationId();
            Bundle defaultArguments = action.getDefaultArguments();
            if (defaultArguments != null) {
                bundle2 = new Bundle();
                bundle2.putAll(defaultArguments);
            }
        } else {
            i2 = i;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putAll(bundle);
        }
        if (i2 == 0 && navOptions != null && (navOptions.getPopUpToId() != -1 || navOptions.getPopUpToRoute() != null)) {
            if (navOptions.getPopUpToRoute() != null) {
                String popUpToRoute = navOptions.getPopUpToRoute();
                Intrinsics.checkNotNull(popUpToRoute);
                popBackStack$default(this, popUpToRoute, navOptions.isPopUpToInclusive(), false, 4, null);
                return;
            } else {
                if (navOptions.getPopUpToId() != -1) {
                    popBackStack(navOptions.getPopUpToId(), navOptions.isPopUpToInclusive());
                    return;
                }
                return;
            }
        }
        if (!(i2 != 0)) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo".toString());
        }
        NavDestination findDestination = findDestination(i2);
        if (findDestination != null) {
            navigate(findDestination, bundle2, navOptions, extras);
            return;
        }
        NavDestination.Companion companion = NavDestination.Companion;
        String displayName = companion.getDisplayName(this.context, i2);
        if (action == null) {
            throw new IllegalArgumentException("Navigation action/destination " + displayName + " cannot be found from the current destination " + destination);
        }
        throw new IllegalArgumentException(("Navigation destination " + displayName + " referenced from action " + companion.getDisplayName(this.context, i) + " cannot be found from the current destination " + destination).toString());
    }

    public void navigate(NavDirections directions) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        navigate(directions.getActionId(), directions.getArguments(), null);
    }

    public boolean popBackStack() {
        if (this.backQueue.isEmpty()) {
            return false;
        }
        NavDestination currentDestination = getCurrentDestination();
        Intrinsics.checkNotNull(currentDestination);
        return popBackStack(currentDestination.getId(), true);
    }

    public boolean popBackStack(int i, boolean z) {
        return popBackStack(i, z, false);
    }

    public boolean popBackStack(int i, boolean z, boolean z2) {
        return popBackStackInternal(i, z, z2) && dispatchOnDestinationChanged();
    }

    public final boolean popBackStack(String route, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStackInternal(route, z, z2) && dispatchOnDestinationChanged();
    }

    public final void popBackStackFromNavigator$navigation_runtime_release(NavBackStackEntry popUpTo, Function0 onComplete) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        int indexOf = this.backQueue.indexOf(popUpTo);
        if (indexOf < 0) {
            Log.i("NavController", "Ignoring pop of " + popUpTo + " as it was not found on the current back stack");
            return;
        }
        int i = indexOf + 1;
        if (i != this.backQueue.size()) {
            popBackStackInternal(((NavBackStackEntry) this.backQueue.get(i)).getDestination().getId(), true, false);
        }
        popEntryFromBackStack$default(this, popUpTo, false, null, 6, null);
        onComplete.invoke();
        updateOnBackPressedCallbackEnabled();
        dispatchOnDestinationChanged();
    }

    public final List populateVisibleEntries$navigation_runtime_release() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            Iterable iterable = (Iterable) ((NavControllerNavigatorState) it.next()).getTransitionsInProgress().getValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : iterable) {
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                if ((arrayList.contains(navBackStackEntry) || navBackStackEntry.getMaxLifecycle().isAtLeast(Lifecycle.State.STARTED)) ? false : true) {
                    arrayList2.add(obj);
                }
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        ArrayDeque arrayDeque = this.backQueue;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayDeque) {
            NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj2;
            if (!arrayList.contains(navBackStackEntry2) && navBackStackEntry2.getMaxLifecycle().isAtLeast(Lifecycle.State.STARTED)) {
                arrayList3.add(obj2);
            }
        }
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!(((NavBackStackEntry) obj3).getDestination() instanceof NavGraph)) {
                arrayList4.add(obj3);
            }
        }
        return arrayList4;
    }

    public void removeOnDestinationChangedListener(OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.remove(listener);
    }

    public void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(this.context.getClassLoader());
        this.navigatorStateToRestore = bundle.getBundle("android-support-nav:controller:navigatorState");
        this.backStackToRestore = bundle.getParcelableArray("android-support-nav:controller:backStack");
        this.backStackStates.clear();
        int[] intArray = bundle.getIntArray("android-support-nav:controller:backStackDestIds");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("android-support-nav:controller:backStackIds");
        if (intArray != null && stringArrayList != null) {
            int length = intArray.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                this.backStackMap.put(Integer.valueOf(intArray[i]), stringArrayList.get(i2));
                i++;
                i2++;
            }
        }
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("android-support-nav:controller:backStackStates");
        if (stringArrayList2 != null) {
            for (String id : stringArrayList2) {
                Parcelable[] parcelableArray = bundle.getParcelableArray("android-support-nav:controller:backStackStates:" + id);
                if (parcelableArray != null) {
                    Map map = this.backStackStates;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    ArrayDeque arrayDeque = new ArrayDeque(parcelableArray.length);
                    Iterator it = ArrayIteratorKt.iterator(parcelableArray);
                    while (it.hasNext()) {
                        Parcelable parcelable = (Parcelable) it.next();
                        Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type androidx.navigation.NavBackStackEntryState");
                        arrayDeque.add((NavBackStackEntryState) parcelable);
                    }
                    map.put(id, arrayDeque);
                }
            }
        }
        this.deepLinkHandled = bundle.getBoolean("android-support-nav:controller:deepLinkHandled");
    }

    public Bundle saveState() {
        Bundle bundle;
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : this._navigatorProvider.getNavigators().entrySet()) {
            String str = (String) entry.getKey();
            Bundle onSaveState = ((Navigator) entry.getValue()).onSaveState();
            if (onSaveState != null) {
                arrayList.add(str);
                bundle2.putBundle(str, onSaveState);
            }
        }
        if (!arrayList.isEmpty()) {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        } else {
            bundle = null;
        }
        if (!this.backQueue.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[this.backQueue.size()];
            Iterator<E> it = this.backQueue.iterator();
            int i = 0;
            while (it.hasNext()) {
                parcelableArr[i] = new NavBackStackEntryState((NavBackStackEntry) it.next());
                i++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (!this.backStackMap.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            int[] iArr = new int[this.backStackMap.size()];
            ArrayList<String> arrayList2 = new ArrayList<>();
            int i2 = 0;
            for (Map.Entry entry2 : this.backStackMap.entrySet()) {
                int intValue = ((Number) entry2.getKey()).intValue();
                String str2 = (String) entry2.getValue();
                iArr[i2] = intValue;
                arrayList2.add(str2);
                i2++;
            }
            bundle.putIntArray("android-support-nav:controller:backStackDestIds", iArr);
            bundle.putStringArrayList("android-support-nav:controller:backStackIds", arrayList2);
        }
        if (!this.backStackStates.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            ArrayList<String> arrayList3 = new ArrayList<>();
            for (Map.Entry entry3 : this.backStackStates.entrySet()) {
                String str3 = (String) entry3.getKey();
                ArrayDeque arrayDeque = (ArrayDeque) entry3.getValue();
                arrayList3.add(str3);
                Parcelable[] parcelableArr2 = new Parcelable[arrayDeque.size()];
                int i3 = 0;
                for (Object obj : arrayDeque) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    parcelableArr2[i3] = (NavBackStackEntryState) obj;
                    i3 = i4;
                }
                bundle.putParcelableArray("android-support-nav:controller:backStackStates:" + str3, parcelableArr2);
            }
            bundle.putStringArrayList("android-support-nav:controller:backStackStates", arrayList3);
        }
        if (this.deepLinkHandled) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", this.deepLinkHandled);
        }
        return bundle;
    }

    public void setGraph(int i) {
        setGraph(getNavInflater().inflate(i), (Bundle) null);
    }

    public void setGraph(int i, Bundle bundle) {
        setGraph(getNavInflater().inflate(i), bundle);
    }

    public void setGraph(NavGraph graph, Bundle bundle) {
        List list;
        List<NavDestination> asReversed;
        Intrinsics.checkNotNullParameter(graph, "graph");
        if (!Intrinsics.areEqual(this._graph, graph)) {
            NavGraph navGraph = this._graph;
            if (navGraph != null) {
                for (Integer id : new ArrayList(this.backStackMap.keySet())) {
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    clearBackStackInternal(id.intValue());
                }
                popBackStackInternal$default(this, navGraph.getId(), true, false, 4, null);
            }
            this._graph = graph;
            onGraphCreated(bundle);
            return;
        }
        int size = graph.getNodes().size();
        for (int i = 0; i < size; i++) {
            NavDestination navDestination = (NavDestination) graph.getNodes().valueAt(i);
            NavGraph navGraph2 = this._graph;
            Intrinsics.checkNotNull(navGraph2);
            int keyAt = navGraph2.getNodes().keyAt(i);
            NavGraph navGraph3 = this._graph;
            Intrinsics.checkNotNull(navGraph3);
            navGraph3.getNodes().replace(keyAt, navDestination);
        }
        for (NavBackStackEntry navBackStackEntry : this.backQueue) {
            list = SequencesKt___SequencesKt.toList(NavDestination.Companion.getHierarchy(navBackStackEntry.getDestination()));
            asReversed = CollectionsKt__ReversedViewsKt.asReversed(list);
            NavDestination navDestination2 = this._graph;
            Intrinsics.checkNotNull(navDestination2);
            for (NavDestination navDestination3 : asReversed) {
                if (!Intrinsics.areEqual(navDestination3, this._graph) || !Intrinsics.areEqual(navDestination2, graph)) {
                    if (navDestination2 instanceof NavGraph) {
                        navDestination2 = ((NavGraph) navDestination2).findNode(navDestination3.getId());
                        Intrinsics.checkNotNull(navDestination2);
                    }
                }
            }
            navBackStackEntry.setDestination(navDestination2);
        }
    }

    public void setLifecycleOwner(LifecycleOwner owner) {
        Lifecycle lifecycle;
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (Intrinsics.areEqual(owner, this.lifecycleOwner)) {
            return;
        }
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.removeObserver(this.lifecycleObserver);
        }
        this.lifecycleOwner = owner;
        owner.getLifecycle().addObserver(this.lifecycleObserver);
    }

    public void setViewModelStore(ViewModelStore viewModelStore) {
        Intrinsics.checkNotNullParameter(viewModelStore, "viewModelStore");
        NavControllerViewModel navControllerViewModel = this.viewModel;
        NavControllerViewModel.Companion companion = NavControllerViewModel.Companion;
        if (Intrinsics.areEqual(navControllerViewModel, companion.getInstance(viewModelStore))) {
            return;
        }
        if (!this.backQueue.isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call".toString());
        }
        this.viewModel = companion.getInstance(viewModelStore);
    }

    public final NavBackStackEntry unlinkChildFromParent$navigation_runtime_release(NavBackStackEntry child) {
        Intrinsics.checkNotNullParameter(child, "child");
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.childToParentEntries.remove(child);
        if (navBackStackEntry == null) {
            return null;
        }
        AtomicInteger atomicInteger = (AtomicInteger) this.parentToChildCount.get(navBackStackEntry);
        Integer valueOf = atomicInteger != null ? Integer.valueOf(atomicInteger.decrementAndGet()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntry.getDestination().getNavigatorName()));
            if (navControllerNavigatorState != null) {
                navControllerNavigatorState.markTransitionComplete(navBackStackEntry);
            }
            this.parentToChildCount.remove(navBackStackEntry);
        }
        return navBackStackEntry;
    }

    public final void updateBackStackLifecycle$navigation_runtime_release() {
        List<NavBackStackEntry> mutableList;
        Object last;
        List<NavBackStackEntry> reversed;
        Object first;
        Object removeFirst;
        Object firstOrNull;
        StateFlow transitionsInProgress;
        Set set;
        List reversed2;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.backQueue);
        if (mutableList.isEmpty()) {
            return;
        }
        last = CollectionsKt___CollectionsKt.last(mutableList);
        NavDestination destination = ((NavBackStackEntry) last).getDestination();
        ArrayList arrayList = new ArrayList();
        if (destination instanceof FloatingWindow) {
            reversed2 = CollectionsKt___CollectionsKt.reversed(mutableList);
            Iterator it = reversed2.iterator();
            while (it.hasNext()) {
                NavDestination destination2 = ((NavBackStackEntry) it.next()).getDestination();
                arrayList.add(destination2);
                if (!(destination2 instanceof FloatingWindow) && !(destination2 instanceof NavGraph)) {
                    break;
                }
            }
        }
        HashMap hashMap = new HashMap();
        reversed = CollectionsKt___CollectionsKt.reversed(mutableList);
        for (NavBackStackEntry navBackStackEntry : reversed) {
            Lifecycle.State maxLifecycle = navBackStackEntry.getMaxLifecycle();
            NavDestination destination3 = navBackStackEntry.getDestination();
            if (destination == null || destination3.getId() != destination.getId()) {
                if (true ^ arrayList.isEmpty()) {
                    int id = destination3.getId();
                    first = CollectionsKt___CollectionsKt.first((List) arrayList);
                    if (id == ((NavDestination) first).getId()) {
                        removeFirst = CollectionsKt__MutableCollectionsKt.removeFirst(arrayList);
                        NavDestination navDestination = (NavDestination) removeFirst;
                        if (maxLifecycle == Lifecycle.State.RESUMED) {
                            navBackStackEntry.setMaxLifecycle(Lifecycle.State.STARTED);
                        } else {
                            Lifecycle.State state = Lifecycle.State.STARTED;
                            if (maxLifecycle != state) {
                                hashMap.put(navBackStackEntry, state);
                            }
                        }
                        NavGraph parent = navDestination.getParent();
                        if (parent != null && !arrayList.contains(parent)) {
                            arrayList.add(parent);
                        }
                    }
                }
                navBackStackEntry.setMaxLifecycle(Lifecycle.State.CREATED);
            } else {
                Lifecycle.State state2 = Lifecycle.State.RESUMED;
                if (maxLifecycle != state2) {
                    NavControllerNavigatorState navControllerNavigatorState = (NavControllerNavigatorState) this.navigatorState.get(getNavigatorProvider().getNavigator(navBackStackEntry.getDestination().getNavigatorName()));
                    if (!Intrinsics.areEqual((navControllerNavigatorState == null || (transitionsInProgress = navControllerNavigatorState.getTransitionsInProgress()) == null || (set = (Set) transitionsInProgress.getValue()) == null) ? null : Boolean.valueOf(set.contains(navBackStackEntry)), Boolean.TRUE)) {
                        AtomicInteger atomicInteger = (AtomicInteger) this.parentToChildCount.get(navBackStackEntry);
                        if (!(atomicInteger != null && atomicInteger.get() == 0)) {
                            hashMap.put(navBackStackEntry, state2);
                        }
                    }
                    hashMap.put(navBackStackEntry, Lifecycle.State.STARTED);
                }
                firstOrNull = CollectionsKt___CollectionsKt.firstOrNull(arrayList);
                NavDestination navDestination2 = (NavDestination) firstOrNull;
                if (navDestination2 != null && navDestination2.getId() == destination3.getId()) {
                    CollectionsKt__MutableCollectionsKt.removeFirst(arrayList);
                }
                destination = destination.getParent();
            }
        }
        for (NavBackStackEntry navBackStackEntry2 : mutableList) {
            Lifecycle.State state3 = (Lifecycle.State) hashMap.get(navBackStackEntry2);
            if (state3 != null) {
                navBackStackEntry2.setMaxLifecycle(state3);
            } else {
                navBackStackEntry2.updateState();
            }
        }
    }
}
