package androidx.emoji2.text;

import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
abstract class MetadataListReader {

    /* loaded from: classes.dex */
    private static class ByteBufferReader implements OpenTypeReader {
        private final ByteBuffer mByteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.mByteBuffer.position();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readTag() {
            return this.mByteBuffer.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long readUnsignedInt() {
            return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() {
            return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void skip(int i) {
            ByteBuffer byteBuffer = this.mByteBuffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class OffsetInfo {
        private final long mLength;
        private final long mStartOffset;

        OffsetInfo(long j, long j2) {
            this.mStartOffset = j;
            this.mLength = j2;
        }

        long getStartOffset() {
            return this.mStartOffset;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface OpenTypeReader {
        long getPosition();

        int readTag();

        long readUnsignedInt();

        int readUnsignedShort();

        void skip(int i);
    }

    private static OffsetInfo findOffsetInfo(OpenTypeReader openTypeReader) {
        long j;
        openTypeReader.skip(4);
        int readUnsignedShort = openTypeReader.readUnsignedShort();
        if (readUnsignedShort > 100) {
            throw new IOException("Cannot read metadata.");
        }
        openTypeReader.skip(6);
        int i = 0;
        while (true) {
            if (i >= readUnsignedShort) {
                j = -1;
                break;
            }
            int readTag = openTypeReader.readTag();
            openTypeReader.skip(4);
            j = openTypeReader.readUnsignedInt();
            openTypeReader.skip(4);
            if (1835365473 == readTag) {
                break;
            }
            i++;
        }
        if (j != -1) {
            openTypeReader.skip((int) (j - openTypeReader.getPosition()));
            openTypeReader.skip(12);
            long readUnsignedInt = openTypeReader.readUnsignedInt();
            for (int i2 = 0; i2 < readUnsignedInt; i2++) {
                int readTag2 = openTypeReader.readTag();
                long readUnsignedInt2 = openTypeReader.readUnsignedInt();
                long readUnsignedInt3 = openTypeReader.readUnsignedInt();
                if (1164798569 == readTag2 || 1701669481 == readTag2) {
                    return new OffsetInfo(readUnsignedInt2 + j, readUnsignedInt3);
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetadataList read(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position((int) findOffsetInfo(new ByteBufferReader(duplicate)).getStartOffset());
        return MetadataList.getRootAsMetadataList(duplicate);
    }

    static long toUnsignedInt(int i) {
        return i & 4294967295L;
    }

    static int toUnsignedShort(short s) {
        return s & 65535;
    }
}
