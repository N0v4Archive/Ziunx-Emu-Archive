package coil.network;

import okhttp3.Response;

/* loaded from: classes.dex */
public final class HttpException extends RuntimeException {
    private final Response response;

    public HttpException(Response response) {
        super("HTTP " + response.code() + ": " + response.message());
        this.response = response;
    }
}
