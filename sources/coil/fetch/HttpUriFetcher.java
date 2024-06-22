package coil.fetch;

import android.net.Uri;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.ImageSource;
import coil.decode.ImageSources;
import coil.disk.DiskCache;
import coil.fetch.Fetcher;
import coil.network.CacheResponse;
import coil.network.CacheStrategy;
import coil.request.Options;
import coil.util.Utils;
import java.io.IOException;
import java.util.Map;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;

/* loaded from: classes.dex */
public final class HttpUriFetcher implements Fetcher {
    private final Lazy callFactory;
    private final Lazy diskCache;
    private final Options options;
    private final boolean respectCacheHeaders;
    private final String url;
    public static final Companion Companion = new Companion(null);
    private static final CacheControl CACHE_CONTROL_FORCE_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().noStore().build();
    private static final CacheControl CACHE_CONTROL_NO_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().onlyIfCached().build();

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements Fetcher.Factory {
        private final Lazy callFactory;
        private final Lazy diskCache;
        private final boolean respectCacheHeaders;

        public Factory(Lazy lazy, Lazy lazy2, boolean z) {
            this.callFactory = lazy;
            this.diskCache = lazy2;
            this.respectCacheHeaders = z;
        }

        private final boolean isApplicable(Uri uri) {
            return Intrinsics.areEqual(uri.getScheme(), "http") || Intrinsics.areEqual(uri.getScheme(), "https");
        }

        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri uri, Options options, ImageLoader imageLoader) {
            if (isApplicable(uri)) {
                return new HttpUriFetcher(uri.toString(), options, this.callFactory, this.diskCache, this.respectCacheHeaders);
            }
            return null;
        }
    }

    public HttpUriFetcher(String str, Options options, Lazy lazy, Lazy lazy2, boolean z) {
        this.url = str;
        this.options = options;
        this.callFactory = lazy;
        this.diskCache = lazy2;
        this.respectCacheHeaders = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeNetworkRequest(okhttp3.Request r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            if (r0 == 0) goto L13
            r0 = r6
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = (coil.fetch.HttpUriFetcher$executeNetworkRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = new coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L72
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = coil.util.Utils.isMainThread()
            if (r6 == 0) goto L5d
            coil.request.Options r6 = r4.options
            coil.request.CachePolicy r6 = r6.getNetworkCachePolicy()
            boolean r6 = r6.getReadEnabled()
            if (r6 != 0) goto L57
            kotlin.Lazy r4 = r4.callFactory
            java.lang.Object r4 = r4.getValue()
            okhttp3.Call$Factory r4 = (okhttp3.Call.Factory) r4
            okhttp3.Call r4 = r4.newCall(r5)
            okhttp3.Response r4 = r4.execute()
            goto L75
        L57:
            android.os.NetworkOnMainThreadException r4 = new android.os.NetworkOnMainThreadException
            r4.<init>()
            throw r4
        L5d:
            kotlin.Lazy r4 = r4.callFactory
            java.lang.Object r4 = r4.getValue()
            okhttp3.Call$Factory r4 = (okhttp3.Call.Factory) r4
            okhttp3.Call r4 = r4.newCall(r5)
            r0.label = r3
            java.lang.Object r6 = coil.util.Calls.await(r4, r0)
            if (r6 != r1) goto L72
            return r1
        L72:
            r4 = r6
            okhttp3.Response r4 = (okhttp3.Response) r4
        L75:
            boolean r5 = r4.isSuccessful()
            if (r5 != 0) goto L92
            int r5 = r4.code()
            r6 = 304(0x130, float:4.26E-43)
            if (r5 == r6) goto L92
            okhttp3.ResponseBody r5 = r4.body()
            if (r5 == 0) goto L8c
            coil.util.Utils.closeQuietly(r5)
        L8c:
            coil.network.HttpException r5 = new coil.network.HttpException
            r5.<init>(r4)
            throw r5
        L92:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.executeNetworkRequest(okhttp3.Request, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getDiskCacheKey() {
        String diskCacheKey = this.options.getDiskCacheKey();
        return diskCacheKey == null ? this.url : diskCacheKey;
    }

    private final FileSystem getFileSystem() {
        Object value = this.diskCache.getValue();
        Intrinsics.checkNotNull(value);
        return ((DiskCache) value).getFileSystem();
    }

    private final boolean isCacheable(Request request, Response response) {
        return this.options.getDiskCachePolicy().getWriteEnabled() && (!this.respectCacheHeaders || CacheStrategy.Companion.isCacheable(request, response));
    }

    private final Request newRequest() {
        CacheControl cacheControl;
        Request.Builder headers = new Request.Builder().url(this.url).headers(this.options.getHeaders());
        for (Map.Entry entry : this.options.getTags().asMap().entrySet()) {
            Object key = entry.getKey();
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.lang.Class<kotlin.Any>");
            headers.tag((Class) key, entry.getValue());
        }
        boolean readEnabled = this.options.getDiskCachePolicy().getReadEnabled();
        boolean readEnabled2 = this.options.getNetworkCachePolicy().getReadEnabled();
        if (!readEnabled2 && readEnabled) {
            cacheControl = CacheControl.FORCE_CACHE;
        } else {
            if (!readEnabled2 || readEnabled) {
                if (!readEnabled2 && !readEnabled) {
                    cacheControl = CACHE_CONTROL_NO_NETWORK_NO_CACHE;
                }
                return headers.build();
            }
            cacheControl = this.options.getDiskCachePolicy().getWriteEnabled() ? CacheControl.FORCE_NETWORK : CACHE_CONTROL_FORCE_NETWORK_NO_CACHE;
        }
        headers.cacheControl(cacheControl);
        return headers.build();
    }

    private final DiskCache.Snapshot readFromDiskCache() {
        DiskCache diskCache;
        if (!this.options.getDiskCachePolicy().getReadEnabled() || (diskCache = (DiskCache) this.diskCache.getValue()) == null) {
            return null;
        }
        return diskCache.get(getDiskCacheKey());
    }

    private final ResponseBody requireBody(Response response) {
        ResponseBody body = response.body();
        if (body != null) {
            return body;
        }
        throw new IllegalStateException("response body == null".toString());
    }

    private final CacheResponse toCacheResponse(DiskCache.Snapshot snapshot) {
        CacheResponse cacheResponse;
        try {
            BufferedSource buffer = Okio.buffer(getFileSystem().source(snapshot.getMetadata()));
            try {
                cacheResponse = new CacheResponse(buffer);
                th = null;
            } catch (Throwable th) {
                th = th;
                cacheResponse = null;
            }
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    } else {
                        ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
                    }
                }
            }
            if (th != null) {
                throw th;
            }
            Intrinsics.checkNotNull(cacheResponse);
            return cacheResponse;
        } catch (IOException unused) {
            return null;
        }
    }

    private final DataSource toDataSource(Response response) {
        return response.networkResponse() != null ? DataSource.NETWORK : DataSource.DISK;
    }

    private final ImageSource toImageSource(DiskCache.Snapshot snapshot) {
        return ImageSources.create(snapshot.getData(), getFileSystem(), getDiskCacheKey(), snapshot);
    }

    private final ImageSource toImageSource(ResponseBody responseBody) {
        return ImageSources.create(responseBody.source(), this.options.getContext());
    }

    private final DiskCache.Snapshot writeToDiskCache(DiskCache.Snapshot snapshot, Request request, Response response, CacheResponse cacheResponse) {
        DiskCache.Editor edit;
        Unit unit;
        Object obj;
        Throwable th = null;
        if (!isCacheable(request, response)) {
            if (snapshot != null) {
                Utils.closeQuietly(snapshot);
            }
            return null;
        }
        if (snapshot != null) {
            edit = snapshot.closeAndEdit();
        } else {
            DiskCache diskCache = (DiskCache) this.diskCache.getValue();
            edit = diskCache != null ? diskCache.edit(getDiskCacheKey()) : null;
        }
        try {
            if (edit == null) {
                return null;
            }
            try {
                if (response.code() != 304 || cacheResponse == null) {
                    BufferedSink buffer = Okio.buffer(getFileSystem().sink(edit.getMetadata(), false));
                    try {
                        new CacheResponse(response).writeTo(buffer);
                        unit = Unit.INSTANCE;
                        th = null;
                    } catch (Throwable th2) {
                        th = th2;
                        unit = null;
                    }
                    if (buffer != null) {
                        try {
                            buffer.close();
                        } catch (Throwable th3) {
                            if (th == null) {
                                th = th3;
                            } else {
                                ExceptionsKt__ExceptionsKt.addSuppressed(th, th3);
                            }
                        }
                    }
                    if (th != null) {
                        throw th;
                    }
                    Intrinsics.checkNotNull(unit);
                    BufferedSink buffer2 = Okio.buffer(getFileSystem().sink(edit.getData(), false));
                    try {
                        ResponseBody body = response.body();
                        Intrinsics.checkNotNull(body);
                        obj = Long.valueOf(body.source().readAll(buffer2));
                    } catch (Throwable th4) {
                        th = th4;
                        obj = null;
                    }
                    if (buffer2 != null) {
                        try {
                            buffer2.close();
                        } catch (Throwable th5) {
                            if (th == null) {
                                th = th5;
                            } else {
                                ExceptionsKt__ExceptionsKt.addSuppressed(th, th5);
                            }
                        }
                    }
                    if (th != null) {
                        throw th;
                    }
                } else {
                    Response build = response.newBuilder().headers(CacheStrategy.Companion.combineHeaders(cacheResponse.getResponseHeaders(), response.headers())).build();
                    BufferedSink buffer3 = Okio.buffer(getFileSystem().sink(edit.getMetadata(), false));
                    try {
                        new CacheResponse(build).writeTo(buffer3);
                        obj = Unit.INSTANCE;
                    } catch (Throwable th6) {
                        th = th6;
                        obj = null;
                    }
                    if (buffer3 != null) {
                        try {
                            buffer3.close();
                        } catch (Throwable th7) {
                            if (th == null) {
                                th = th7;
                            } else {
                                ExceptionsKt__ExceptionsKt.addSuppressed(th, th7);
                            }
                        }
                    }
                    if (th != null) {
                        throw th;
                    }
                }
                Intrinsics.checkNotNull(obj);
                return edit.commitAndGet();
            } catch (Exception e) {
                Utils.abortQuietly(edit);
                throw e;
            }
        } finally {
            Utils.closeQuietly(response);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0123 A[Catch: Exception -> 0x019c, TRY_ENTER, TryCatch #1 {Exception -> 0x019c, blocks: (B:44:0x0123, B:46:0x0131, B:47:0x0135, B:49:0x013f, B:51:0x0147, B:53:0x015f), top: B:42:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013f A[Catch: Exception -> 0x019c, TryCatch #1 {Exception -> 0x019c, blocks: (B:44:0x0123, B:46:0x0131, B:47:0x0135, B:49:0x013f, B:51:0x0147, B:53:0x015f), top: B:42:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v10 */
    @Override // coil.fetch.Fetcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object fetch(kotlin.coroutines.Continuation r13) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.fetch(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r1 != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getMimeType$coil_base_release(java.lang.String r4, okhttp3.MediaType r5) {
        /*
            r3 = this;
            r3 = 0
            if (r5 == 0) goto L8
            java.lang.String r5 = r5.toString()
            goto L9
        L8:
            r5 = r3
        L9:
            r0 = 2
            if (r5 == 0) goto L15
            java.lang.String r1 = "text/plain"
            r2 = 0
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r5, r1, r2, r0, r3)
            if (r1 == 0) goto L20
        L15:
            android.webkit.MimeTypeMap r1 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r4 = coil.util.Utils.getMimeTypeFromUrl(r1, r4)
            if (r4 == 0) goto L20
            return r4
        L20:
            if (r5 == 0) goto L28
            r4 = 59
            java.lang.String r3 = kotlin.text.StringsKt.substringBefore$default(r5, r4, r3, r0, r3)
        L28:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.getMimeType$coil_base_release(java.lang.String, okhttp3.MediaType):java.lang.String");
    }
}
