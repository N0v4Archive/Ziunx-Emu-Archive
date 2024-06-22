package coil.key;

import android.net.Uri;
import coil.request.Options;
import coil.util.Utils;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class UriKeyer implements Keyer {
    @Override // coil.key.Keyer
    public String key(Uri uri, Options options) {
        if (!Intrinsics.areEqual(uri.getScheme(), "android.resource")) {
            return uri.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(uri);
        sb.append('-');
        sb.append(Utils.getNightMode(options.getContext().getResources().getConfiguration()));
        return sb.toString();
    }
}
