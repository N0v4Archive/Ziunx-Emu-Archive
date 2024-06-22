package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z) {
            super(operation, signal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = loadAnimation;
            this.isAnimLoaded = true;
            return loadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.operation = operation;
            this.signal = signal;
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = this.operation.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return asOperationState == finalState || !(asOperationState == (state = SpecialEffectsController.Operation.State.VISIBLE) || finalState == state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z, boolean z2) {
            super(operation, signal);
            Object returnTransition;
            boolean z3;
            Object obj;
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (finalState == state) {
                Fragment fragment = operation.getFragment();
                returnTransition = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() == state) {
                Fragment fragment3 = operation.getFragment();
                z3 = z ? fragment3.getAllowReturnTransitionOverlap() : fragment3.getAllowEnterTransitionOverlap();
            } else {
                z3 = true;
            }
            this.isOverlapAllowed = z3;
            if (z2) {
                Fragment fragment4 = operation.getFragment();
                obj = z ? fragment4.getSharedElementReturnTransition() : fragment4.getSharedElementEnterTransition();
            } else {
                obj = null;
            }
            this.sharedElementTransition = obj;
        }

        private final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final Object getTransition() {
            return this.transition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final boolean isOverlapAllowed() {
            return this.isOverlapAllowed;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    private final void captureTransitioningViews(ArrayList arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = viewGroup.getChildAt(i);
                    if (child.getVisibility() == 0) {
                        Intrinsics.checkNotNullExpressionValue(child, "child");
                        captureTransitioningViews(arrayList, child);
                    }
                }
                return;
            }
            if (arrayList.contains(view)) {
                return;
            }
        } else if (arrayList.contains(view)) {
            return;
        }
        arrayList.add(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List awaitingContainerChanges, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController this$0) {
        Intrinsics.checkNotNullParameter(awaitingContainerChanges, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (awaitingContainerChanges.contains(operation)) {
            awaitingContainerChanges.remove(operation);
            this$0.applyContainerChanges(operation);
        }
    }

    private final void findNamedViews(Map map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(map, child);
                }
            }
        }
    }

    private final void retainMatchingViews(ArrayMap arrayMap, final Collection collection) {
        Set entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt__MutableCollectionsKt.retainAll(entries, new Function1() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$retainMatchingViews$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry entry) {
                boolean contains;
                Intrinsics.checkNotNullParameter(entry, "entry");
                contains = CollectionsKt___CollectionsKt.contains(collection, ViewCompat.getTransitionName((View) entry.getValue()));
                return Boolean.valueOf(contains);
            }
        });
    }

    private final void startAnimations(List list, List list2, boolean z, Map map) {
        StringBuilder sb;
        String str;
        Context context = getContainer().getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        Iterator it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            final AnimationInfo animationInfo = (AnimationInfo) it.next();
            if (!animationInfo.isVisibilityUnchanged()) {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation != null) {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Intrinsics.areEqual(map.get(operation), Boolean.TRUE)) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operation);
                            }
                            final View view = fragment.mView;
                            getContainer().startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$startAnimations$1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator anim) {
                                    Intrinsics.checkNotNullParameter(anim, "anim");
                                    DefaultSpecialEffectsController.this.getContainer().endViewTransition(view);
                                    if (z3) {
                                        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                                        View viewToAnimate = view;
                                        Intrinsics.checkNotNullExpressionValue(viewToAnimate, "viewToAnimate");
                                        finalState.applyState(viewToAnimate);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animator from operation " + operation + " has started.");
                            }
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public final void onCancel() {
                                    DefaultSpecialEffectsController.startAnimations$lambda$3(animator, operation);
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
            animationInfo.completeSpecialEffect();
        }
        for (final AnimationInfo animationInfo2 : arrayList) {
            final SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    sb = new StringBuilder();
                    sb.append("Ignoring Animation set on ");
                    sb.append(fragment2);
                    str = " as Animations cannot run alongside Transitions.";
                    sb.append(str);
                    Log.v("FragmentManager", sb.toString());
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    sb = new StringBuilder();
                    sb.append("Ignoring Animation set on ");
                    sb.append(fragment2);
                    str = " as Animations cannot run alongside Animators.";
                    sb.append(str);
                    Log.v("FragmentManager", sb.toString());
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context);
                if (animation2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Animation animation3 = animation2.animation;
                if (animation3 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation3);
                    animationInfo2.completeSpecialEffect();
                } else {
                    getContainer().startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, getContainer(), view2);
                    endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$startAnimations$3(operation2, this, view2, animationInfo2));
                    view2.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation2 + " has started.");
                    }
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        DefaultSpecialEffectsController.startAnimations$lambda$4(view2, this, animationInfo2, operation2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View view, DefaultSpecialEffectsController this$0, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        view.clearAnimation();
        this$0.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + operation + " has been cancelled.");
        }
    }

    private final Map startTransitions(List list, List list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        SpecialEffectsController.Operation operation3;
        View view;
        Object obj;
        Object obj2;
        View view2;
        Object obj3;
        LinkedHashMap linkedHashMap;
        Object obj4;
        SpecialEffectsController.Operation operation4;
        final ArrayList arrayList;
        View view3;
        Rect rect;
        Object obj5;
        View view4;
        final View view5;
        DefaultSpecialEffectsController defaultSpecialEffectsController = this;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj6 : list) {
            if (!((TransitionInfo) obj6).isVisibilityUnchanged()) {
                arrayList2.add(obj6);
            }
        }
        ArrayList<TransitionInfo> arrayList3 = new ArrayList();
        for (Object obj7 : arrayList2) {
            if (((TransitionInfo) obj7).getHandlingImpl() != null) {
                arrayList3.add(obj7);
            }
        }
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : arrayList3) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (!(fragmentTransitionImpl == null || handlingImpl == fragmentTransitionImpl)) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
            fragmentTransitionImpl = handlingImpl;
        }
        if (fragmentTransitionImpl == null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it.next();
                linkedHashMap2.put(transitionInfo2.getOperation(), Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            return linkedHashMap2;
        }
        View view6 = new View(getContainer().getContext());
        final Rect rect2 = new Rect();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayMap arrayMap = new ArrayMap();
        Iterator it2 = list.iterator();
        View view7 = null;
        Object obj8 = null;
        boolean z2 = false;
        while (it2.hasNext()) {
            TransitionInfo transitionInfo3 = (TransitionInfo) it2.next();
            if (!transitionInfo3.hasSharedElementTransition() || operation == null || operation2 == null) {
                rect = rect2;
                view6 = view6;
                arrayList4 = arrayList4;
                linkedHashMap2 = linkedHashMap2;
                view7 = view7;
                arrayMap = arrayMap;
                arrayList5 = arrayList5;
            } else {
                obj8 = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames, "lastIn.fragment.sharedElementSourceNames");
                ArrayList sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                View view8 = view7;
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames2, "firstOut.fragment.sharedElementSourceNames");
                ArrayList sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                int size = sharedElementTargetNames.size();
                View view9 = view6;
                int i = 0;
                while (i < size) {
                    int i2 = size;
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList arrayList6 = sharedElementTargetNames;
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    size = i2;
                    sharedElementTargetNames = arrayList6;
                }
                ArrayList sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames2, "lastIn.fragment.sharedElementTargetNames");
                if (z) {
                    obj5 = null;
                    operation.getFragment().getEnterTransitionCallback();
                    operation2.getFragment().getExitTransitionCallback();
                } else {
                    operation.getFragment().getExitTransitionCallback();
                    operation2.getFragment().getEnterTransitionCallback();
                    obj5 = null;
                }
                Pair pair = TuplesKt.to(obj5, obj5);
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component1());
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component2());
                int i3 = 0;
                for (int size2 = sharedElementSourceNames.size(); i3 < size2; size2 = size2) {
                    arrayMap.put((String) sharedElementSourceNames.get(i3), (String) sharedElementTargetNames2.get(i3));
                    i3++;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for (Iterator it3 = sharedElementTargetNames2.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v("FragmentManager", "Name: " + ((String) it3.next()));
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for (Iterator it4 = sharedElementSourceNames.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v("FragmentManager", "Name: " + ((String) it4.next()));
                    }
                }
                ArrayMap arrayMap2 = new ArrayMap();
                View view10 = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view10, "firstOut.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap2, view10);
                arrayMap2.retainAll(sharedElementSourceNames);
                arrayMap.retainAll(arrayMap2.keySet());
                final ArrayMap arrayMap3 = new ArrayMap();
                View view11 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view11, "lastIn.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap3, view11);
                arrayMap3.retainAll(sharedElementTargetNames2);
                arrayMap3.retainAll(arrayMap.values());
                FragmentTransition.retainValues(arrayMap, arrayMap3);
                Set keySet = arrayMap.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "sharedElementNameMapping.keys");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap2, keySet);
                Collection values = arrayMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "sharedElementNameMapping.values");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap3, values);
                if (arrayMap.isEmpty()) {
                    arrayList4.clear();
                    arrayList5.clear();
                    view7 = view8;
                    linkedHashMap2 = linkedHashMap3;
                    view6 = view9;
                    obj8 = null;
                } else {
                    ArrayMap arrayMap4 = arrayMap;
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap2, true);
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.startTransitions$lambda$9(SpecialEffectsController.Operation.this, operation, z, arrayMap3);
                        }
                    });
                    arrayList4.addAll(arrayMap2.values());
                    if (!sharedElementSourceNames.isEmpty()) {
                        view4 = (View) arrayMap2.get((String) sharedElementSourceNames.get(0));
                        fragmentTransitionImpl.setEpicenter(obj8, view4);
                    } else {
                        view4 = view8;
                    }
                    arrayList5.addAll(arrayMap3.values());
                    if ((!sharedElementTargetNames2.isEmpty()) && (view5 = (View) arrayMap3.get((String) sharedElementTargetNames2.get(0))) != null) {
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.startTransitions$lambda$10(FragmentTransitionImpl.this, view5, rect2);
                            }
                        });
                        z2 = true;
                    }
                    fragmentTransitionImpl.setSharedElementTargets(obj8, view9, arrayList4);
                    ArrayList arrayList7 = arrayList5;
                    rect = rect2;
                    fragmentTransitionImpl.scheduleRemoveTargets(obj8, null, null, null, null, obj8, arrayList7);
                    Boolean bool = Boolean.TRUE;
                    linkedHashMap3.put(operation, bool);
                    linkedHashMap3.put(operation2, bool);
                    view7 = view4;
                    arrayList4 = arrayList4;
                    arrayMap = arrayMap4;
                    arrayList5 = arrayList7;
                    view6 = view9;
                    linkedHashMap2 = linkedHashMap3;
                }
            }
            rect2 = rect;
        }
        View view12 = view7;
        ArrayMap arrayMap5 = arrayMap;
        ArrayList arrayList8 = arrayList5;
        ArrayList arrayList9 = arrayList4;
        Rect rect3 = rect2;
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        View view13 = view6;
        ArrayList arrayList10 = new ArrayList();
        Iterator it5 = list.iterator();
        Object obj9 = null;
        Object obj10 = null;
        while (it5.hasNext()) {
            TransitionInfo transitionInfo4 = (TransitionInfo) it5.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                operation3 = transitionInfo4.getOperation();
            } else {
                Object cloneTransition = fragmentTransitionImpl.cloneTransition(transitionInfo4.getTransition());
                operation3 = transitionInfo4.getOperation();
                boolean z3 = obj8 != null && (operation3 == operation || operation3 == operation2);
                if (cloneTransition != null) {
                    LinkedHashMap linkedHashMap5 = linkedHashMap4;
                    ArrayList arrayList11 = new ArrayList();
                    Iterator it6 = it5;
                    View view14 = operation3.getFragment().mView;
                    Object obj11 = obj8;
                    Intrinsics.checkNotNullExpressionValue(view14, "operation.fragment.mView");
                    defaultSpecialEffectsController.captureTransitioningViews(arrayList11, view14);
                    if (z3) {
                        arrayList11.removeAll(operation3 == operation ? CollectionsKt___CollectionsKt.toSet(arrayList9) : CollectionsKt___CollectionsKt.toSet(arrayList8));
                    }
                    if (arrayList11.isEmpty()) {
                        fragmentTransitionImpl.addTarget(cloneTransition, view13);
                        view2 = view13;
                        operation4 = operation3;
                        obj2 = obj9;
                        obj3 = obj10;
                        arrayList = arrayList11;
                        view = view12;
                        linkedHashMap = linkedHashMap5;
                        obj = obj11;
                        obj4 = cloneTransition;
                    } else {
                        fragmentTransitionImpl.addTargets(cloneTransition, arrayList11);
                        view = view12;
                        obj = obj11;
                        obj2 = obj9;
                        view2 = view13;
                        obj3 = obj10;
                        linkedHashMap = linkedHashMap5;
                        fragmentTransitionImpl.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList11, null, null, null, null);
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation4 = operation3;
                            list2.remove(operation4);
                            arrayList = arrayList11;
                            ArrayList arrayList12 = new ArrayList(arrayList);
                            arrayList12.remove(operation4.getFragment().mView);
                            obj4 = cloneTransition;
                            fragmentTransitionImpl.scheduleHideFragmentView(obj4, operation4.getFragment().mView, arrayList12);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.startTransitions$lambda$11(arrayList);
                                }
                            });
                        } else {
                            obj4 = cloneTransition;
                            operation4 = operation3;
                            arrayList = arrayList11;
                        }
                    }
                    if (operation4.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList10.addAll(arrayList);
                        if (z2) {
                            fragmentTransitionImpl.setEpicenter(obj4, rect3);
                        }
                        view3 = view;
                    } else {
                        view3 = view;
                        fragmentTransitionImpl.setEpicenter(obj4, view3);
                    }
                    linkedHashMap.put(operation4, Boolean.TRUE);
                    if (transitionInfo4.isOverlapAllowed()) {
                        obj10 = fragmentTransitionImpl.mergeTransitionsTogether(obj3, obj4, null);
                        obj9 = obj2;
                        linkedHashMap4 = linkedHashMap;
                        view12 = view3;
                        obj8 = obj;
                        view13 = view2;
                        defaultSpecialEffectsController = this;
                    } else {
                        obj9 = fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj4, null);
                        defaultSpecialEffectsController = this;
                        obj10 = obj3;
                        linkedHashMap4 = linkedHashMap;
                        view12 = view3;
                        obj8 = obj;
                        view13 = view2;
                    }
                    it5 = it6;
                } else if (!z3) {
                }
            }
            linkedHashMap4.put(operation3, Boolean.FALSE);
            transitionInfo4.completeSpecialEffect();
        }
        Object obj12 = obj9;
        LinkedHashMap linkedHashMap6 = linkedHashMap4;
        Object obj13 = obj8;
        Object mergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(obj10, obj12, obj13);
        if (mergeTransitionsInSequence == null) {
            return linkedHashMap6;
        }
        ArrayList<TransitionInfo> arrayList13 = new ArrayList();
        for (Object obj14 : list) {
            if (!((TransitionInfo) obj14).isVisibilityUnchanged()) {
                arrayList13.add(obj14);
            }
        }
        for (final TransitionInfo transitionInfo5 : arrayList13) {
            Object transition = transitionInfo5.getTransition();
            final SpecialEffectsController.Operation operation5 = transitionInfo5.getOperation();
            boolean z4 = obj13 != null && (operation5 == operation || operation5 == operation2);
            if (transition != null || z4) {
                if (ViewCompat.isLaidOut(getContainer())) {
                    fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo5.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.startTransitions$lambda$14$lambda$13(DefaultSpecialEffectsController.TransitionInfo.this, operation5);
                        }
                    });
                } else {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation5);
                    }
                    transitionInfo5.completeSpecialEffect();
                }
            }
        }
        if (!ViewCompat.isLaidOut(getContainer())) {
            return linkedHashMap6;
        }
        FragmentTransition.setViewVisibility(arrayList10, 4);
        ArrayList prepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList8);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", ">>>>> Beginning transition <<<<<");
            Log.v("FragmentManager", ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator it7 = arrayList9.iterator();
            while (it7.hasNext()) {
                Object sharedElementFirstOutViews = it7.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews, "sharedElementFirstOutViews");
                View view15 = (View) sharedElementFirstOutViews;
                Log.v("FragmentManager", "View: " + view15 + " Name: " + ViewCompat.getTransitionName(view15));
            }
            Log.v("FragmentManager", ">>>>> SharedElementLastInViews <<<<<");
            Iterator it8 = arrayList8.iterator();
            while (it8.hasNext()) {
                Object sharedElementLastInViews = it8.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews, "sharedElementLastInViews");
                View view16 = (View) sharedElementLastInViews;
                Log.v("FragmentManager", "View: " + view16 + " Name: " + ViewCompat.getTransitionName(view16));
            }
        }
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList9, arrayList8, prepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList10, 0);
        fragmentTransitionImpl.swapSharedElementTargets(obj13, arrayList9, arrayList8);
        return linkedHashMap6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl impl, View view, Rect lastInEpicenterRect) {
        Intrinsics.checkNotNullParameter(impl, "$impl");
        Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
        impl.getBoundsOnScreen(view, lastInEpicenterRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList transitioningViews) {
        Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
        FragmentTransition.setViewVisibility(transitioningViews, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Transition for operation " + operation + " has completed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap lastInViews) {
        Intrinsics.checkNotNullParameter(lastInViews, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), z, lastInViews, false);
    }

    private final void syncAnimations(List list) {
        Object last;
        last = CollectionsKt___CollectionsKt.last(list);
        Fragment fragment = ((SpecialEffectsController.Operation) last).getFragment();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) it.next();
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object] */
    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List operations, boolean z) {
        SpecialEffectsController.Operation operation;
        SpecialEffectsController.Operation operation2;
        final List mutableList;
        Intrinsics.checkNotNullParameter(operations, "operations");
        Iterator it = operations.iterator();
        while (true) {
            operation = null;
            if (!it.hasNext()) {
                operation2 = 0;
                break;
            }
            operation2 = it.next();
            SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) operation2;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = operation3.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (asOperationState == state && operation3.getFinalState() != state) {
                break;
            }
        }
        SpecialEffectsController.Operation operation4 = operation2;
        ListIterator listIterator = operations.listIterator(operations.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            ?? previous = listIterator.previous();
            SpecialEffectsController.Operation operation5 = (SpecialEffectsController.Operation) previous;
            SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.Companion;
            View view2 = operation5.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState2 = companion2.asOperationState(view2);
            SpecialEffectsController.Operation.State state2 = SpecialEffectsController.Operation.State.VISIBLE;
            if (asOperationState2 != state2 && operation5.getFinalState() == state2) {
                operation = previous;
                break;
            }
        }
        SpecialEffectsController.Operation operation6 = operation;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Executing operations from " + operation4 + " to " + operation6);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) operations);
        syncAnimations(operations);
        Iterator it2 = operations.iterator();
        while (it2.hasNext()) {
            final SpecialEffectsController.Operation operation7 = (SpecialEffectsController.Operation) it2.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            operation7.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(operation7, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            operation7.markStartedSpecialEffect(cancellationSignal2);
            arrayList2.add(new TransitionInfo(operation7, cancellationSignal2, z, !z ? operation7 != operation6 : operation7 != operation4));
            operation7.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultSpecialEffectsController.executeOperations$lambda$2(mutableList, operation7, this);
                }
            });
        }
        Map startTransitions = startTransitions(arrayList2, mutableList, z, operation4, operation6);
        startAnimations(arrayList, mutableList, startTransitions.containsValue(Boolean.TRUE), startTransitions);
        Iterator it3 = mutableList.iterator();
        while (it3.hasNext()) {
            applyContainerChanges((SpecialEffectsController.Operation) it3.next());
        }
        mutableList.clear();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Completed executing operations from " + operation4 + " to " + operation6);
        }
    }
}
