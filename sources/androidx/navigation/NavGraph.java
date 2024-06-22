package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayKt;
import androidx.navigation.NavDestination;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes.dex */
public class NavGraph extends NavDestination implements Iterable, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    private final SparseArrayCompat nodes;
    private int startDestId;
    private String startDestIdName;
    private String startDestinationRoute;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NavDestination findStartDestination(NavGraph navGraph) {
            Sequence generateSequence;
            Object last;
            Intrinsics.checkNotNullParameter(navGraph, "<this>");
            generateSequence = SequencesKt__SequencesKt.generateSequence(navGraph.findNode(navGraph.getStartDestinationId()), new Function1() { // from class: androidx.navigation.NavGraph$Companion$findStartDestination$1
                @Override // kotlin.jvm.functions.Function1
                public final NavDestination invoke(NavDestination it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (!(it instanceof NavGraph)) {
                        return null;
                    }
                    NavGraph navGraph2 = (NavGraph) it;
                    return navGraph2.findNode(navGraph2.getStartDestinationId());
                }
            });
            last = SequencesKt___SequencesKt.last(generateSequence);
            return (NavDestination) last;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavGraph(Navigator navGraphNavigator) {
        super(navGraphNavigator);
        Intrinsics.checkNotNullParameter(navGraphNavigator, "navGraphNavigator");
        this.nodes = new SparseArrayCompat();
    }

    private final void setStartDestinationId(int i) {
        if (i != getId()) {
            if (this.startDestinationRoute != null) {
                setStartDestinationRoute(null);
            }
            this.startDestId = i;
            this.startDestIdName = null;
            return;
        }
        throw new IllegalArgumentException(("Start destination " + i + " cannot use the same id as the graph " + this).toString());
    }

    private final void setStartDestinationRoute(String str) {
        boolean isBlank;
        int hashCode;
        if (str == null) {
            hashCode = 0;
        } else {
            if (!(!Intrinsics.areEqual(str, getRoute()))) {
                throw new IllegalArgumentException(("Start destination " + str + " cannot use the same route as the graph " + this).toString());
            }
            isBlank = StringsKt__StringsJVMKt.isBlank(str);
            if (!(!isBlank)) {
                throw new IllegalArgumentException("Cannot have an empty start destination route".toString());
            }
            hashCode = NavDestination.Companion.createRoute(str).hashCode();
        }
        this.startDestId = hashCode;
        this.startDestinationRoute = str;
    }

    public final void addDestination(NavDestination node) {
        Intrinsics.checkNotNullParameter(node, "node");
        int id = node.getId();
        if (!((id == 0 && node.getRoute() == null) ? false : true)) {
            throw new IllegalArgumentException("Destinations must have an id or route. Call setId(), setRoute(), or include an android:id or app:route in your navigation XML.".toString());
        }
        if (getRoute() != null && !(!Intrinsics.areEqual(r1, getRoute()))) {
            throw new IllegalArgumentException(("Destination " + node + " cannot have the same route as graph " + this).toString());
        }
        if (!(id != getId())) {
            throw new IllegalArgumentException(("Destination " + node + " cannot have the same id as graph " + this).toString());
        }
        NavDestination navDestination = (NavDestination) this.nodes.get(id);
        if (navDestination == node) {
            return;
        }
        if (!(node.getParent() == null)) {
            throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.".toString());
        }
        if (navDestination != null) {
            navDestination.setParent(null);
        }
        node.setParent(this);
        this.nodes.put(node.getId(), node);
    }

    @Override // androidx.navigation.NavDestination
    public boolean equals(Object obj) {
        Sequence asSequence;
        List mutableList;
        if (obj == null || !(obj instanceof NavGraph)) {
            return false;
        }
        asSequence = SequencesKt__SequencesKt.asSequence(SparseArrayKt.valueIterator(this.nodes));
        mutableList = SequencesKt___SequencesKt.toMutableList(asSequence);
        NavGraph navGraph = (NavGraph) obj;
        Iterator valueIterator = SparseArrayKt.valueIterator(navGraph.nodes);
        while (valueIterator.hasNext()) {
            mutableList.remove((NavDestination) valueIterator.next());
        }
        return super.equals(obj) && this.nodes.size() == navGraph.nodes.size() && getStartDestinationId() == navGraph.getStartDestinationId() && mutableList.isEmpty();
    }

    public final NavDestination findNode(int i) {
        return findNode(i, true);
    }

    public final NavDestination findNode(int i, boolean z) {
        NavDestination navDestination = (NavDestination) this.nodes.get(i);
        if (navDestination != null) {
            return navDestination;
        }
        if (!z || getParent() == null) {
            return null;
        }
        NavGraph parent = getParent();
        Intrinsics.checkNotNull(parent);
        return parent.findNode(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.navigation.NavDestination findNode(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 1
            if (r3 == 0) goto Lc
            boolean r1 = kotlin.text.StringsKt.isBlank(r3)
            if (r1 == 0) goto La
            goto Lc
        La:
            r1 = 0
            goto Ld
        Lc:
            r1 = r0
        Ld:
            if (r1 != 0) goto L14
            androidx.navigation.NavDestination r2 = r2.findNode(r3, r0)
            goto L15
        L14:
            r2 = 0
        L15:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavGraph.findNode(java.lang.String):androidx.navigation.NavDestination");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object] */
    public final NavDestination findNode(String route, boolean z) {
        Sequence asSequence;
        NavDestination navDestination;
        Intrinsics.checkNotNullParameter(route, "route");
        NavDestination navDestination2 = (NavDestination) this.nodes.get(NavDestination.Companion.createRoute(route).hashCode());
        if (navDestination2 == null) {
            asSequence = SequencesKt__SequencesKt.asSequence(SparseArrayKt.valueIterator(this.nodes));
            Iterator it = asSequence.iterator();
            while (true) {
                if (!it.hasNext()) {
                    navDestination = 0;
                    break;
                }
                navDestination = it.next();
                if (((NavDestination) navDestination).matchDeepLink(route) != null) {
                    break;
                }
            }
            navDestination2 = navDestination;
        }
        if (navDestination2 != null) {
            return navDestination2;
        }
        if (!z || getParent() == null) {
            return null;
        }
        NavGraph parent = getParent();
        Intrinsics.checkNotNull(parent);
        return parent.findNode(route);
    }

    @Override // androidx.navigation.NavDestination
    public String getDisplayName() {
        return getId() != 0 ? super.getDisplayName() : "the root navigation";
    }

    public final SparseArrayCompat getNodes() {
        return this.nodes;
    }

    public final String getStartDestDisplayName() {
        if (this.startDestIdName == null) {
            String str = this.startDestinationRoute;
            if (str == null) {
                str = String.valueOf(this.startDestId);
            }
            this.startDestIdName = str;
        }
        String str2 = this.startDestIdName;
        Intrinsics.checkNotNull(str2);
        return str2;
    }

    public final int getStartDestinationId() {
        return this.startDestId;
    }

    public final String getStartDestinationRoute() {
        return this.startDestinationRoute;
    }

    @Override // androidx.navigation.NavDestination
    public int hashCode() {
        int startDestinationId = getStartDestinationId();
        SparseArrayCompat sparseArrayCompat = this.nodes;
        int size = sparseArrayCompat.size();
        for (int i = 0; i < size; i++) {
            startDestinationId = (((startDestinationId * 31) + sparseArrayCompat.keyAt(i)) * 31) + ((NavDestination) sparseArrayCompat.valueAt(i)).hashCode();
        }
        return startDestinationId;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new NavGraph$iterator$1(this);
    }

    @Override // androidx.navigation.NavDestination
    public NavDestination.DeepLinkMatch matchDeepLink(NavDeepLinkRequest navDeepLinkRequest) {
        Comparable maxOrNull;
        List listOfNotNull;
        Comparable maxOrNull2;
        Intrinsics.checkNotNullParameter(navDeepLinkRequest, "navDeepLinkRequest");
        NavDestination.DeepLinkMatch matchDeepLink = super.matchDeepLink(navDeepLinkRequest);
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            NavDestination.DeepLinkMatch matchDeepLink2 = ((NavDestination) it.next()).matchDeepLink(navDeepLinkRequest);
            if (matchDeepLink2 != null) {
                arrayList.add(matchDeepLink2);
            }
        }
        maxOrNull = CollectionsKt___CollectionsKt.maxOrNull(arrayList);
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(matchDeepLink, (NavDestination.DeepLinkMatch) maxOrNull);
        maxOrNull2 = CollectionsKt___CollectionsKt.maxOrNull(listOfNotNull);
        return (NavDestination.DeepLinkMatch) maxOrNull2;
    }

    public final NavDestination.DeepLinkMatch matchDeepLinkExcludingChildren(NavDeepLinkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return super.matchDeepLink(request);
    }

    @Override // androidx.navigation.NavDestination
    public void onInflate(Context context, AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        super.onInflate(context, attrs);
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attrs, androidx.navigation.common.R$styleable.NavGraphNavigator);
        Intrinsics.checkNotNullExpressionValue(obtainAttributes, "context.resources.obtain…vGraphNavigator\n        )");
        setStartDestinationId(obtainAttributes.getResourceId(androidx.navigation.common.R$styleable.NavGraphNavigator_startDestination, 0));
        this.startDestIdName = NavDestination.Companion.getDisplayName(context, this.startDestId);
        Unit unit = Unit.INSTANCE;
        obtainAttributes.recycle();
    }

    @Override // androidx.navigation.NavDestination
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        NavDestination findNode = findNode(this.startDestinationRoute);
        if (findNode == null) {
            findNode = findNode(getStartDestinationId());
        }
        sb.append(" startDestination=");
        if (findNode == null) {
            String str2 = this.startDestinationRoute;
            if (str2 != null || (str2 = this.startDestIdName) != null) {
                sb.append(str2);
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
                return sb2;
            }
            str = "0x" + Integer.toHexString(this.startDestId);
        } else {
            sb.append("{");
            sb.append(findNode.toString());
            str = "}";
        }
        sb.append(str);
        String sb22 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb22, "sb.toString()");
        return sb22;
    }
}
