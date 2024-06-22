package coil.map;

import coil.request.Options;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class ByteArrayMapper implements Mapper {
    @Override // coil.map.Mapper
    public ByteBuffer map(byte[] bArr, Options options) {
        return ByteBuffer.wrap(bArr);
    }
}
