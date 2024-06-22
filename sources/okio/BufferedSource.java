package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    boolean exhausted();

    Buffer getBuffer();

    InputStream inputStream();

    BufferedSource peek();

    long readAll(Sink sink);

    byte readByte();

    byte[] readByteArray(long j);

    ByteString readByteString(long j);

    long readHexadecimalUnsignedLong();

    int readInt();

    int readIntLe();

    long readLongLe();

    short readShort();

    short readShortLe();

    String readUtf8(long j);

    String readUtf8LineStrict();

    String readUtf8LineStrict(long j);

    boolean request(long j);

    void require(long j);

    void skip(long j);
}
