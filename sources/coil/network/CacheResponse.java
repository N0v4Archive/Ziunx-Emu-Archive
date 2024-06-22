package coil.network;

import coil.util.Utils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;

/* loaded from: classes.dex */
public final class CacheResponse {
    private final Lazy cacheControl$delegate;
    private final Lazy contentType$delegate;
    private final boolean isTls;
    private final long receivedResponseAtMillis;
    private final Headers responseHeaders;
    private final long sentRequestAtMillis;

    public CacheResponse(Response response) {
        Lazy lazy;
        Lazy lazy2;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        lazy = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: coil.network.CacheResponse$cacheControl$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CacheControl invoke() {
                return CacheControl.Companion.parse(CacheResponse.this.getResponseHeaders());
            }
        });
        this.cacheControl$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: coil.network.CacheResponse$contentType$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final MediaType invoke() {
                String str = CacheResponse.this.getResponseHeaders().get("Content-Type");
                if (str != null) {
                    return MediaType.Companion.parse(str);
                }
                return null;
            }
        });
        this.contentType$delegate = lazy2;
        this.sentRequestAtMillis = response.sentRequestAtMillis();
        this.receivedResponseAtMillis = response.receivedResponseAtMillis();
        this.isTls = response.handshake() != null;
        this.responseHeaders = response.headers();
    }

    public CacheResponse(BufferedSource bufferedSource) {
        Lazy lazy;
        Lazy lazy2;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        lazy = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: coil.network.CacheResponse$cacheControl$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CacheControl invoke() {
                return CacheControl.Companion.parse(CacheResponse.this.getResponseHeaders());
            }
        });
        this.cacheControl$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: coil.network.CacheResponse$contentType$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final MediaType invoke() {
                String str = CacheResponse.this.getResponseHeaders().get("Content-Type");
                if (str != null) {
                    return MediaType.Companion.parse(str);
                }
                return null;
            }
        });
        this.contentType$delegate = lazy2;
        this.sentRequestAtMillis = Long.parseLong(bufferedSource.readUtf8LineStrict());
        this.receivedResponseAtMillis = Long.parseLong(bufferedSource.readUtf8LineStrict());
        this.isTls = Integer.parseInt(bufferedSource.readUtf8LineStrict()) > 0;
        int parseInt = Integer.parseInt(bufferedSource.readUtf8LineStrict());
        Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < parseInt; i++) {
            Utils.addUnsafeNonAscii(builder, bufferedSource.readUtf8LineStrict());
        }
        this.responseHeaders = builder.build();
    }

    public final CacheControl getCacheControl() {
        return (CacheControl) this.cacheControl$delegate.getValue();
    }

    public final MediaType getContentType() {
        return (MediaType) this.contentType$delegate.getValue();
    }

    public final long getReceivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public final Headers getResponseHeaders() {
        return this.responseHeaders;
    }

    public final long getSentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public final boolean isTls() {
        return this.isTls;
    }

    public final void writeTo(BufferedSink bufferedSink) {
        bufferedSink.writeDecimalLong(this.sentRequestAtMillis).writeByte(10);
        bufferedSink.writeDecimalLong(this.receivedResponseAtMillis).writeByte(10);
        bufferedSink.writeDecimalLong(this.isTls ? 1L : 0L).writeByte(10);
        bufferedSink.writeDecimalLong(this.responseHeaders.size()).writeByte(10);
        int size = this.responseHeaders.size();
        for (int i = 0; i < size; i++) {
            bufferedSink.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10);
        }
    }
}
