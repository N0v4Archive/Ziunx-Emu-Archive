package okio.internal;

/* loaded from: classes.dex */
final class EocdRecord {
    private final long centralDirectoryOffset;
    private final int commentByteCount;
    private final long entryCount;

    public EocdRecord(long j, long j2, int i) {
        this.entryCount = j;
        this.centralDirectoryOffset = j2;
        this.commentByteCount = i;
    }

    public final long getCentralDirectoryOffset() {
        return this.centralDirectoryOffset;
    }

    public final int getCommentByteCount() {
        return this.commentByteCount;
    }

    public final long getEntryCount() {
        return this.entryCount;
    }
}
