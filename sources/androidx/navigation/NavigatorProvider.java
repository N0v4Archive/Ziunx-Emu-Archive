package androidx.navigation;

import androidx.navigation.Navigator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class NavigatorProvider {
    public static final Companion Companion = new Companion(null);
    private static final Map annotationNames = new LinkedHashMap();
    private final Map _navigators = new LinkedHashMap();

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getNameForNavigator$navigation_common_release(Class navigatorClass) {
            Intrinsics.checkNotNullParameter(navigatorClass, "navigatorClass");
            String str = (String) NavigatorProvider.annotationNames.get(navigatorClass);
            if (str == null) {
                Navigator.Name name = (Navigator.Name) navigatorClass.getAnnotation(Navigator.Name.class);
                str = name != null ? name.value() : null;
                if (!validateName$navigation_common_release(str)) {
                    throw new IllegalArgumentException(("No @Navigator.Name annotation found for " + navigatorClass.getSimpleName()).toString());
                }
                NavigatorProvider.annotationNames.put(navigatorClass, str);
            }
            Intrinsics.checkNotNull(str);
            return str;
        }

        public final boolean validateName$navigation_common_release(String str) {
            if (str != null) {
                return str.length() > 0;
            }
            return false;
        }
    }

    public final Navigator addNavigator(Navigator navigator) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        return addNavigator(Companion.getNameForNavigator$navigation_common_release(navigator.getClass()), navigator);
    }

    public Navigator addNavigator(String name, Navigator navigator) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        if (!Companion.validateName$navigation_common_release(name)) {
            throw new IllegalArgumentException("navigator name cannot be an empty string".toString());
        }
        Navigator navigator2 = (Navigator) this._navigators.get(name);
        if (Intrinsics.areEqual(navigator2, navigator)) {
            return navigator;
        }
        boolean z = false;
        if (navigator2 != null && navigator2.isAttached()) {
            z = true;
        }
        if (!(!z)) {
            throw new IllegalStateException(("Navigator " + navigator + " is replacing an already attached " + navigator2).toString());
        }
        if (!navigator.isAttached()) {
            return (Navigator) this._navigators.put(name, navigator);
        }
        throw new IllegalStateException(("Navigator " + navigator + " is already attached to another NavController").toString());
    }

    public Navigator getNavigator(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (!Companion.validateName$navigation_common_release(name)) {
            throw new IllegalArgumentException("navigator name cannot be an empty string".toString());
        }
        Navigator navigator = (Navigator) this._navigators.get(name);
        if (navigator != null) {
            return navigator;
        }
        throw new IllegalStateException("Could not find Navigator with name \"" + name + "\". You must call NavController.addNavigator() for each navigation type.");
    }

    public final Map getNavigators() {
        Map map;
        map = MapsKt__MapsKt.toMap(this._navigators);
        return map;
    }
}
