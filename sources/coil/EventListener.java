package coil;

import android.graphics.Bitmap;
import coil.EventListener;
import coil.decode.DecodeResult;
import coil.decode.Decoder;
import coil.fetch.FetchResult;
import coil.fetch.Fetcher;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.SuccessResult;
import coil.size.Size;
import coil.transition.Transition;

/* loaded from: classes.dex */
public interface EventListener extends ImageRequest.Listener {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final EventListener NONE = new EventListener() { // from class: coil.EventListener$Companion$NONE$1
        @Override // coil.EventListener
        public void decodeEnd(ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
            EventListener.DefaultImpls.decodeEnd(this, imageRequest, decoder, options, decodeResult);
        }

        @Override // coil.EventListener
        public void decodeStart(ImageRequest imageRequest, Decoder decoder, Options options) {
            EventListener.DefaultImpls.decodeStart(this, imageRequest, decoder, options);
        }

        @Override // coil.EventListener
        public void fetchEnd(ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
            EventListener.DefaultImpls.fetchEnd(this, imageRequest, fetcher, options, fetchResult);
        }

        @Override // coil.EventListener
        public void fetchStart(ImageRequest imageRequest, Fetcher fetcher, Options options) {
            EventListener.DefaultImpls.fetchStart(this, imageRequest, fetcher, options);
        }

        @Override // coil.EventListener
        public void keyEnd(ImageRequest imageRequest, String str) {
            EventListener.DefaultImpls.keyEnd(this, imageRequest, str);
        }

        @Override // coil.EventListener
        public void keyStart(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.keyStart(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public void mapEnd(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.mapEnd(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public void mapStart(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.mapStart(this, imageRequest, obj);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onCancel(ImageRequest imageRequest) {
            EventListener.DefaultImpls.onCancel(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onError(ImageRequest imageRequest, ErrorResult errorResult) {
            EventListener.DefaultImpls.onError(this, imageRequest, errorResult);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onStart(ImageRequest imageRequest) {
            EventListener.DefaultImpls.onStart(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onSuccess(ImageRequest imageRequest, SuccessResult successResult) {
            EventListener.DefaultImpls.onSuccess(this, imageRequest, successResult);
        }

        @Override // coil.EventListener
        public void resolveSizeEnd(ImageRequest imageRequest, Size size) {
            EventListener.DefaultImpls.resolveSizeEnd(this, imageRequest, size);
        }

        @Override // coil.EventListener
        public void resolveSizeStart(ImageRequest imageRequest) {
            EventListener.DefaultImpls.resolveSizeStart(this, imageRequest);
        }

        @Override // coil.EventListener
        public void transformEnd(ImageRequest imageRequest, Bitmap bitmap) {
            EventListener.DefaultImpls.transformEnd(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public void transformStart(ImageRequest imageRequest, Bitmap bitmap) {
            EventListener.DefaultImpls.transformStart(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public void transitionEnd(ImageRequest imageRequest, Transition transition) {
            EventListener.DefaultImpls.transitionEnd(this, imageRequest, transition);
        }

        @Override // coil.EventListener
        public void transitionStart(ImageRequest imageRequest, Transition transition) {
            EventListener.DefaultImpls.transitionStart(this, imageRequest, transition);
        }
    };

    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static void decodeEnd(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
        }

        public static void decodeStart(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options) {
        }

        public static void fetchEnd(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
        }

        public static void fetchStart(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options) {
        }

        public static void keyEnd(EventListener eventListener, ImageRequest imageRequest, String str) {
        }

        public static void keyStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void mapEnd(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void mapStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void onCancel(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void onError(EventListener eventListener, ImageRequest imageRequest, ErrorResult errorResult) {
        }

        public static void onStart(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void onSuccess(EventListener eventListener, ImageRequest imageRequest, SuccessResult successResult) {
        }

        public static void resolveSizeEnd(EventListener eventListener, ImageRequest imageRequest, Size size) {
        }

        public static void resolveSizeStart(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void transformEnd(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
        }

        public static void transformStart(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
        }

        public static void transitionEnd(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
        }

        public static void transitionStart(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
        }
    }

    /* loaded from: classes.dex */
    public interface Factory {
        public static final Companion Companion = Companion.$$INSTANCE;
        public static final Factory NONE = new Factory() { // from class: coil.EventListener$Factory$$ExternalSyntheticLambda0
            @Override // coil.EventListener.Factory
            public final EventListener create(ImageRequest imageRequest) {
                EventListener m33NONE$lambda0;
                m33NONE$lambda0 = EventListener.Factory.DefaultImpls.m33NONE$lambda0(imageRequest);
                return m33NONE$lambda0;
            }
        };

        /* loaded from: classes.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }

        /* loaded from: classes.dex */
        public static final class DefaultImpls {
            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: NONE$lambda-0, reason: not valid java name */
            public static EventListener m33NONE$lambda0(ImageRequest imageRequest) {
                return EventListener.NONE;
            }
        }

        EventListener create(ImageRequest imageRequest);
    }

    void decodeEnd(ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult);

    void decodeStart(ImageRequest imageRequest, Decoder decoder, Options options);

    void fetchEnd(ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult);

    void fetchStart(ImageRequest imageRequest, Fetcher fetcher, Options options);

    void keyEnd(ImageRequest imageRequest, String str);

    void keyStart(ImageRequest imageRequest, Object obj);

    void mapEnd(ImageRequest imageRequest, Object obj);

    void mapStart(ImageRequest imageRequest, Object obj);

    @Override // coil.request.ImageRequest.Listener
    void onCancel(ImageRequest imageRequest);

    @Override // coil.request.ImageRequest.Listener
    void onError(ImageRequest imageRequest, ErrorResult errorResult);

    @Override // coil.request.ImageRequest.Listener
    void onStart(ImageRequest imageRequest);

    @Override // coil.request.ImageRequest.Listener
    void onSuccess(ImageRequest imageRequest, SuccessResult successResult);

    void resolveSizeEnd(ImageRequest imageRequest, Size size);

    void resolveSizeStart(ImageRequest imageRequest);

    void transformEnd(ImageRequest imageRequest, Bitmap bitmap);

    void transformStart(ImageRequest imageRequest, Bitmap bitmap);

    void transitionEnd(ImageRequest imageRequest, Transition transition);

    void transitionStart(ImageRequest imageRequest, Transition transition);
}
