package coil.network;

import coil.util.Time;
import coil.util.Utils;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public final class CacheStrategy {
    public static final Companion Companion = new Companion(null);
    private final CacheResponse cacheResponse;
    private final Request networkRequest;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isContentSpecificHeader(String str) {
            boolean equals;
            boolean equals2;
            boolean equals3;
            equals = StringsKt__StringsJVMKt.equals("Content-Length", str, true);
            if (equals) {
                return true;
            }
            equals2 = StringsKt__StringsJVMKt.equals("Content-Encoding", str, true);
            if (equals2) {
                return true;
            }
            equals3 = StringsKt__StringsJVMKt.equals("Content-Type", str, true);
            return equals3;
        }

        private final boolean isEndToEnd(String str) {
            boolean equals;
            boolean equals2;
            boolean equals3;
            boolean equals4;
            boolean equals5;
            boolean equals6;
            boolean equals7;
            boolean equals8;
            equals = StringsKt__StringsJVMKt.equals("Connection", str, true);
            if (!equals) {
                equals2 = StringsKt__StringsJVMKt.equals("Keep-Alive", str, true);
                if (!equals2) {
                    equals3 = StringsKt__StringsJVMKt.equals("Proxy-Authenticate", str, true);
                    if (!equals3) {
                        equals4 = StringsKt__StringsJVMKt.equals("Proxy-Authorization", str, true);
                        if (!equals4) {
                            equals5 = StringsKt__StringsJVMKt.equals("TE", str, true);
                            if (!equals5) {
                                equals6 = StringsKt__StringsJVMKt.equals("Trailers", str, true);
                                if (!equals6) {
                                    equals7 = StringsKt__StringsJVMKt.equals("Transfer-Encoding", str, true);
                                    if (!equals7) {
                                        equals8 = StringsKt__StringsJVMKt.equals("Upgrade", str, true);
                                        if (!equals8) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        public final Headers combineHeaders(Headers headers, Headers headers2) {
            int i;
            boolean equals;
            boolean startsWith$default;
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            for (0; i < size; i + 1) {
                String name = headers.name(i);
                String value = headers.value(i);
                equals = StringsKt__StringsJVMKt.equals("Warning", name, true);
                if (equals) {
                    startsWith$default = StringsKt__StringsJVMKt.startsWith$default(value, "1", false, 2, null);
                    i = startsWith$default ? i + 1 : 0;
                }
                if (isContentSpecificHeader(name) || !isEndToEnd(name) || headers2.get(name) == null) {
                    builder.add(name, value);
                }
            }
            int size2 = headers2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String name2 = headers2.name(i2);
                if (!isContentSpecificHeader(name2) && isEndToEnd(name2)) {
                    builder.add(name2, headers2.value(i2));
                }
            }
            return builder.build();
        }

        public final boolean isCacheable(Request request, CacheResponse cacheResponse) {
            return (request.cacheControl().noStore() || cacheResponse.getCacheControl().noStore() || Intrinsics.areEqual(cacheResponse.getResponseHeaders().get("Vary"), "*")) ? false : true;
        }

        public final boolean isCacheable(Request request, Response response) {
            return (request.cacheControl().noStore() || response.cacheControl().noStore() || Intrinsics.areEqual(response.headers().get("Vary"), "*")) ? false : true;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory {
        private int ageSeconds;
        private final CacheResponse cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private long receivedResponseMillis;
        private final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(Request request, CacheResponse cacheResponse) {
            boolean equals;
            boolean equals2;
            boolean equals3;
            boolean equals4;
            boolean equals5;
            this.request = request;
            this.cacheResponse = cacheResponse;
            this.ageSeconds = -1;
            if (cacheResponse != null) {
                this.sentRequestMillis = cacheResponse.getSentRequestAtMillis();
                this.receivedResponseMillis = cacheResponse.getReceivedResponseAtMillis();
                Headers responseHeaders = cacheResponse.getResponseHeaders();
                int size = responseHeaders.size();
                for (int i = 0; i < size; i++) {
                    String name = responseHeaders.name(i);
                    equals = StringsKt__StringsJVMKt.equals(name, "Date", true);
                    if (equals) {
                        this.servedDate = responseHeaders.getDate("Date");
                        this.servedDateString = responseHeaders.value(i);
                    } else {
                        equals2 = StringsKt__StringsJVMKt.equals(name, "Expires", true);
                        if (equals2) {
                            this.expires = responseHeaders.getDate("Expires");
                        } else {
                            equals3 = StringsKt__StringsJVMKt.equals(name, "Last-Modified", true);
                            if (equals3) {
                                this.lastModified = responseHeaders.getDate("Last-Modified");
                                this.lastModifiedString = responseHeaders.value(i);
                            } else {
                                equals4 = StringsKt__StringsJVMKt.equals(name, "ETag", true);
                                if (equals4) {
                                    this.etag = responseHeaders.value(i);
                                } else {
                                    equals5 = StringsKt__StringsJVMKt.equals(name, "Age", true);
                                    if (equals5) {
                                        this.ageSeconds = Utils.toNonNegativeInt(responseHeaders.value(i), -1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private final long cacheResponseAge() {
            Date date = this.servedDate;
            long max = date != null ? Math.max(0L, this.receivedResponseMillis - date.getTime()) : 0L;
            int i = this.ageSeconds;
            if (i != -1) {
                max = Math.max(max, TimeUnit.SECONDS.toMillis(i));
            }
            return max + (this.receivedResponseMillis - this.sentRequestMillis) + (Time.INSTANCE.currentMillis() - this.receivedResponseMillis);
        }

        private final long computeFreshnessLifetime() {
            CacheResponse cacheResponse = this.cacheResponse;
            Intrinsics.checkNotNull(cacheResponse);
            if (cacheResponse.getCacheControl().maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.maxAgeSeconds());
            }
            Date date = this.expires;
            if (date != null) {
                Date date2 = this.servedDate;
                long time = date.getTime() - (date2 != null ? date2.getTime() : this.receivedResponseMillis);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.lastModified == null || this.request.url().query() != null) {
                return 0L;
            }
            Date date3 = this.servedDate;
            long time2 = date3 != null ? date3.getTime() : this.sentRequestMillis;
            Date date4 = this.lastModified;
            Intrinsics.checkNotNull(date4);
            long time3 = time2 - date4.getTime();
            if (time3 > 0) {
                return time3 / 10;
            }
            return 0L;
        }

        private final boolean hasConditions(Request request) {
            return (request.header("If-Modified-Since") == null && request.header("If-None-Match") == null) ? false : true;
        }

        public final CacheStrategy compute() {
            String str;
            CacheResponse cacheResponse = null;
            byte b = 0;
            byte b2 = 0;
            byte b3 = 0;
            byte b4 = 0;
            byte b5 = 0;
            byte b6 = 0;
            byte b7 = 0;
            byte b8 = 0;
            byte b9 = 0;
            byte b10 = 0;
            byte b11 = 0;
            byte b12 = 0;
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, cacheResponse, b12 == true ? 1 : 0);
            }
            if (this.request.isHttps() && !this.cacheResponse.isTls()) {
                return new CacheStrategy(this.request, b11 == true ? 1 : 0, b10 == true ? 1 : 0);
            }
            CacheControl cacheControl = this.cacheResponse.getCacheControl();
            if (!CacheStrategy.Companion.isCacheable(this.request, this.cacheResponse)) {
                return new CacheStrategy(this.request, b9 == true ? 1 : 0, b8 == true ? 1 : 0);
            }
            CacheControl cacheControl2 = this.request.cacheControl();
            if (cacheControl2.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, b2 == true ? 1 : 0, b == true ? 1 : 0);
            }
            long cacheResponseAge = cacheResponseAge();
            long computeFreshnessLifetime = computeFreshnessLifetime();
            if (cacheControl2.maxAgeSeconds() != -1) {
                computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis(cacheControl2.maxAgeSeconds()));
            }
            long j = 0;
            long millis = cacheControl2.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis(cacheControl2.minFreshSeconds()) : 0L;
            if (!cacheControl.mustRevalidate() && cacheControl2.maxStaleSeconds() != -1) {
                j = TimeUnit.SECONDS.toMillis(cacheControl2.maxStaleSeconds());
            }
            if (!cacheControl.noCache() && cacheResponseAge + millis < computeFreshnessLifetime + j) {
                return new CacheStrategy(b7 == true ? 1 : 0, this.cacheResponse, b6 == true ? 1 : 0);
            }
            String str2 = this.etag;
            if (str2 != null) {
                Intrinsics.checkNotNull(str2);
                str = "If-None-Match";
            } else {
                str = "If-Modified-Since";
                if (this.lastModified != null) {
                    str2 = this.lastModifiedString;
                } else {
                    if (this.servedDate == null) {
                        return new CacheStrategy(this.request, b4 == true ? 1 : 0, b3 == true ? 1 : 0);
                    }
                    str2 = this.servedDateString;
                }
                Intrinsics.checkNotNull(str2);
            }
            return new CacheStrategy(this.request.newBuilder().addHeader(str, str2).build(), this.cacheResponse, b5 == true ? 1 : 0);
        }
    }

    private CacheStrategy(Request request, CacheResponse cacheResponse) {
        this.networkRequest = request;
        this.cacheResponse = cacheResponse;
    }

    public /* synthetic */ CacheStrategy(Request request, CacheResponse cacheResponse, DefaultConstructorMarker defaultConstructorMarker) {
        this(request, cacheResponse);
    }

    public final CacheResponse getCacheResponse() {
        return this.cacheResponse;
    }

    public final Request getNetworkRequest() {
        return this.networkRequest;
    }
}
