package org.yuzu.yuzu_emu.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class GpuDriverMetadata {
    public static final Companion Companion = new Companion(null);
    private String author;
    private String description;
    private String libraryName;
    private int minApi;
    private String name;
    private String vendor;
    private String version;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public GpuDriverMetadata() {
    }

    public GpuDriverMetadata(File metadataFile) {
        Intrinsics.checkNotNullParameter(metadataFile, "metadataFile");
        if (metadataFile.length() > 500000) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(FileUtil.INSTANCE.getStringFromFile(metadataFile));
            this.name = jSONObject.getString("name");
            this.description = jSONObject.getString("description");
            this.author = jSONObject.getString("author");
            this.vendor = jSONObject.getString("vendor");
            this.version = jSONObject.getString("driverVersion");
            this.minApi = jSONObject.getInt("minApi");
            this.libraryName = jSONObject.getString("libraryName");
        } catch (IOException | JSONException unused) {
        }
    }

    public GpuDriverMetadata(InputStream metadataStream, long j) {
        Intrinsics.checkNotNullParameter(metadataStream, "metadataStream");
        if (j > 500000) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(FileUtil.INSTANCE.getStringFromInputStream(metadataStream));
            this.name = jSONObject.getString("name");
            this.description = jSONObject.getString("description");
            this.author = jSONObject.getString("author");
            this.vendor = jSONObject.getString("vendor");
            this.version = jSONObject.getString("driverVersion");
            this.minApi = jSONObject.getInt("minApi");
            this.libraryName = jSONObject.getString("libraryName");
        } catch (IOException | JSONException unused) {
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GpuDriverMetadata)) {
            return false;
        }
        GpuDriverMetadata gpuDriverMetadata = (GpuDriverMetadata) obj;
        return Intrinsics.areEqual(gpuDriverMetadata.name, this.name) && Intrinsics.areEqual(gpuDriverMetadata.description, this.description) && Intrinsics.areEqual(gpuDriverMetadata.author, this.author) && Intrinsics.areEqual(gpuDriverMetadata.vendor, this.vendor) && Intrinsics.areEqual(gpuDriverMetadata.version, this.version) && gpuDriverMetadata.minApi == this.minApi && Intrinsics.areEqual(gpuDriverMetadata.libraryName, this.libraryName);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getLibraryName() {
        return this.libraryName;
    }

    public final int getMinApi() {
        return this.minApi;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.description;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.author;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.vendor;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.version;
        int hashCode5 = (((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.minApi) * 31;
        String str6 = this.libraryName;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        String trimMargin$default;
        String trimIndent;
        trimMargin$default = StringsKt__IndentKt.trimMargin$default("\n            Name - " + this.name + "\n            Description - " + this.description + "\n            Author - " + this.author + "\n            Vendor - " + this.vendor + "\n            Version - " + this.version + "\n            Min API - " + this.minApi + "\n            Library Name - " + this.libraryName + "\n        ", null, 1, null);
        trimIndent = StringsKt__IndentKt.trimIndent(trimMargin$default);
        return trimIndent;
    }
}
