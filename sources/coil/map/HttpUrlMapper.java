package coil.map;

import coil.request.Options;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public final class HttpUrlMapper implements Mapper {
    @Override // coil.map.Mapper
    public String map(HttpUrl httpUrl, Options options) {
        return httpUrl.toString();
    }
}
