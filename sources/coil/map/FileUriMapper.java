package coil.map;

import android.net.Uri;
import coil.request.Options;
import coil.util.Utils;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes.dex */
public final class FileUriMapper implements Mapper {
    private final boolean isApplicable(Uri uri) {
        boolean startsWith$default;
        if (Utils.isAssetUri(uri)) {
            return false;
        }
        String scheme = uri.getScheme();
        if (!(scheme == null || Intrinsics.areEqual(scheme, "file"))) {
            return false;
        }
        String path = uri.getPath();
        if (path == null) {
            path = "";
        }
        startsWith$default = StringsKt__StringsKt.startsWith$default((CharSequence) path, '/', false, 2, (Object) null);
        return startsWith$default && Utils.getFirstPathSegment(uri) != null;
    }

    @Override // coil.map.Mapper
    public File map(Uri uri, Options options) {
        if (!isApplicable(uri)) {
            return null;
        }
        if (uri.getScheme() != null) {
            uri = uri.buildUpon().scheme(null).build();
        }
        return new File(uri.toString());
    }
}
