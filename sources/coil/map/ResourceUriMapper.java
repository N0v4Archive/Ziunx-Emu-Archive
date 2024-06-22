package coil.map;

import android.content.res.Resources;
import android.net.Uri;
import coil.request.Options;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes.dex */
public final class ResourceUriMapper implements Mapper {
    private final boolean isApplicable(Uri uri) {
        boolean z;
        boolean isBlank;
        if (!Intrinsics.areEqual(uri.getScheme(), "android.resource")) {
            return false;
        }
        String authority = uri.getAuthority();
        if (authority != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(authority);
            if (!isBlank) {
                z = false;
                return !z && uri.getPathSegments().size() == 2;
            }
        }
        z = true;
        if (z) {
            return false;
        }
    }

    @Override // coil.map.Mapper
    public Uri map(Uri uri, Options options) {
        if (!isApplicable(uri)) {
            return null;
        }
        String authority = uri.getAuthority();
        if (authority == null) {
            authority = "";
        }
        Resources resourcesForApplication = options.getContext().getPackageManager().getResourcesForApplication(authority);
        List<String> pathSegments = uri.getPathSegments();
        int identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
        if (!(identifier != 0)) {
            throw new IllegalStateException(("Invalid android.resource URI: " + uri).toString());
        }
        Uri parse = Uri.parse("android.resource://" + authority + '/' + identifier);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
        return parse;
    }
}
