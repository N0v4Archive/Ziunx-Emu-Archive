package coil.fetch;

import android.net.Uri;
import coil.ImageLoader;
import coil.fetch.Fetcher;
import coil.request.Options;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ResourceUriFetcher implements Fetcher {
    public static final Companion Companion = new Companion(null);
    private final Uri data;
    private final Options options;

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
        private final boolean isApplicable(Uri uri) {
            return Intrinsics.areEqual(uri.getScheme(), "android.resource");
        }

        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri uri, Options options, ImageLoader imageLoader) {
            if (isApplicable(uri)) {
                return new ResourceUriFetcher(uri, options);
            }
            return null;
        }
    }

    public ResourceUriFetcher(Uri uri, Options options) {
        this.data = uri;
        this.options = options;
    }

    private final Void throwInvalidUriException(Uri uri) {
        throw new IllegalStateException("Invalid android.resource URI: " + uri);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r0);
     */
    @Override // coil.fetch.Fetcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object fetch(kotlin.coroutines.Continuation r12) {
        /*
            r11 = this;
            android.net.Uri r12 = r11.data
            java.lang.String r12 = r12.getAuthority()
            if (r12 == 0) goto Lf3
            boolean r0 = kotlin.text.StringsKt.isBlank(r12)
            r1 = 1
            r0 = r0 ^ r1
            if (r0 == 0) goto L11
            goto L12
        L11:
            r12 = 0
        L12:
            if (r12 == 0) goto Lf3
            android.net.Uri r0 = r11.data
            java.util.List r0 = r0.getPathSegments()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.lastOrNull(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto Le8
            java.lang.Integer r0 = kotlin.text.StringsKt.toIntOrNull(r0)
            if (r0 == 0) goto Le8
            int r0 = r0.intValue()
            coil.request.Options r2 = r11.options
            android.content.Context r2 = r2.getContext()
            java.lang.String r3 = r2.getPackageName()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r3)
            if (r3 == 0) goto L41
            android.content.res.Resources r3 = r2.getResources()
            goto L49
        L41:
            android.content.pm.PackageManager r3 = r2.getPackageManager()
            android.content.res.Resources r3 = r3.getResourcesForApplication(r12)
        L49:
            android.util.TypedValue r4 = new android.util.TypedValue
            r4.<init>()
            r3.getValue(r0, r4, r1)
            java.lang.CharSequence r1 = r4.string
            r6 = 47
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            r5 = r1
            int r4 = kotlin.text.StringsKt.lastIndexOf$default(r5, r6, r7, r8, r9, r10)
            int r5 = r1.length()
            java.lang.CharSequence r1 = r1.subSequence(r4, r5)
            java.lang.String r1 = r1.toString()
            android.webkit.MimeTypeMap r4 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r1 = coil.util.Utils.getMimeTypeFromUrl(r4, r1)
            java.lang.String r4 = "text/xml"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r4)
            if (r4 == 0) goto Lc3
            java.lang.String r1 = r2.getPackageName()
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r1)
            if (r12 == 0) goto L89
            android.graphics.drawable.Drawable r12 = coil.util.Contexts.getDrawableCompat(r2, r0)
            goto L8d
        L89:
            android.graphics.drawable.Drawable r12 = coil.util.Contexts.getXmlDrawableCompat(r2, r3, r0)
        L8d:
            r4 = r12
            boolean r12 = coil.util.Utils.isVector(r4)
            coil.fetch.DrawableResult r0 = new coil.fetch.DrawableResult
            if (r12 == 0) goto Lbd
            coil.util.DrawableUtils r3 = coil.util.DrawableUtils.INSTANCE
            coil.request.Options r1 = r11.options
            android.graphics.Bitmap$Config r5 = r1.getConfig()
            coil.request.Options r1 = r11.options
            coil.size.Size r6 = r1.getSize()
            coil.request.Options r1 = r11.options
            coil.size.Scale r7 = r1.getScale()
            coil.request.Options r11 = r11.options
            boolean r8 = r11.getAllowInexactSize()
            android.graphics.Bitmap r11 = r3.convertToBitmap(r4, r5, r6, r7, r8)
            android.content.res.Resources r1 = r2.getResources()
            android.graphics.drawable.BitmapDrawable r4 = new android.graphics.drawable.BitmapDrawable
            r4.<init>(r1, r11)
        Lbd:
            coil.decode.DataSource r11 = coil.decode.DataSource.DISK
            r0.<init>(r4, r12, r11)
            goto Le7
        Lc3:
            android.util.TypedValue r11 = new android.util.TypedValue
            r11.<init>()
            java.io.InputStream r3 = r3.openRawResource(r0, r11)
            coil.fetch.SourceResult r4 = new coil.fetch.SourceResult
            okio.Source r3 = okio.Okio.source(r3)
            okio.BufferedSource r3 = okio.Okio.buffer(r3)
            coil.decode.ResourceMetadata r5 = new coil.decode.ResourceMetadata
            int r11 = r11.density
            r5.<init>(r12, r0, r11)
            coil.decode.ImageSource r11 = coil.decode.ImageSources.create(r3, r2, r5)
            coil.decode.DataSource r12 = coil.decode.DataSource.DISK
            r4.<init>(r11, r1, r12)
            r0 = r4
        Le7:
            return r0
        Le8:
            android.net.Uri r12 = r11.data
            r11.throwInvalidUriException(r12)
            kotlin.KotlinNothingValueException r11 = new kotlin.KotlinNothingValueException
            r11.<init>()
            throw r11
        Lf3:
            android.net.Uri r12 = r11.data
            r11.throwInvalidUriException(r12)
            kotlin.KotlinNothingValueException r11 = new kotlin.KotlinNothingValueException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.ResourceUriFetcher.fetch(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
