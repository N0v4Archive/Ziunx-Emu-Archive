package coil.decode;

import java.io.InputStream;

/* loaded from: classes.dex */
final class ExifInterfaceInputStream extends InputStream {
    private int availableBytes = 1073741824;
    private final InputStream delegate;

    public ExifInterfaceInputStream(InputStream inputStream) {
        this.delegate = inputStream;
    }

    private final int interceptBytesRead(int i) {
        if (i == -1) {
            this.availableBytes = 0;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.availableBytes;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // java.io.InputStream
    public int read() {
        return interceptBytesRead(this.delegate.read());
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return interceptBytesRead(this.delegate.read(bArr));
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return interceptBytesRead(this.delegate.read(bArr, i, i2));
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return this.delegate.skip(j);
    }
}
