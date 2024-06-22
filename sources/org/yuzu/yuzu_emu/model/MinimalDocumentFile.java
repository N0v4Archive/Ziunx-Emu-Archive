package org.yuzu.yuzu_emu.model;

import android.net.Uri;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class MinimalDocumentFile {
    private final String filename;
    private final boolean isDirectory;
    private final Uri uri;

    public MinimalDocumentFile(String filename, String mimeType, Uri uri) {
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.filename = filename;
        this.uri = uri;
        this.isDirectory = Intrinsics.areEqual(mimeType, "vnd.android.document/directory");
    }

    public final String getFilename() {
        return this.filename;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }
}
