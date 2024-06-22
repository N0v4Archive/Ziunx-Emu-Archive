package coil.fetch;

import coil.decode.DataSource;
import coil.decode.ImageSource;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class SourceResult extends FetchResult {
    private final DataSource dataSource;
    private final String mimeType;
    private final ImageSource source;

    public SourceResult(ImageSource imageSource, String str, DataSource dataSource) {
        super(null);
        this.source = imageSource;
        this.mimeType = str;
        this.dataSource = dataSource;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SourceResult) {
            SourceResult sourceResult = (SourceResult) obj;
            if (Intrinsics.areEqual(this.source, sourceResult.source) && Intrinsics.areEqual(this.mimeType, sourceResult.mimeType) && this.dataSource == sourceResult.dataSource) {
                return true;
            }
        }
        return false;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    public final ImageSource getSource() {
        return this.source;
    }

    public int hashCode() {
        int hashCode = this.source.hashCode() * 31;
        String str = this.mimeType;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.dataSource.hashCode();
    }
}
