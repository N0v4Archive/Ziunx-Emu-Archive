package okhttp3;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public interface CookieJar {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final CookieJar NO_COOKIES = new Companion.NoCookies();

    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* loaded from: classes.dex */
        private static final class NoCookies implements CookieJar {
            @Override // okhttp3.CookieJar
            public List loadForRequest(HttpUrl url) {
                List emptyList;
                Intrinsics.checkNotNullParameter(url, "url");
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList;
            }

            @Override // okhttp3.CookieJar
            public void saveFromResponse(HttpUrl url, List cookies) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(cookies, "cookies");
            }
        }

        private Companion() {
        }
    }

    List loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List list);
}
