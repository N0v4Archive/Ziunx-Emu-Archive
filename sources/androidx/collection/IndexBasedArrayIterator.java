package androidx.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class IndexBasedArrayIterator implements Iterator {
    private boolean mCanRemove;
    private int mIndex;
    private int mSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndexBasedArrayIterator(int i) {
        this.mSize = i;
    }

    protected abstract Object elementAt(int i);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.mIndex < this.mSize;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object elementAt = elementAt(this.mIndex);
        this.mIndex++;
        this.mCanRemove = true;
        return elementAt;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.mCanRemove) {
            throw new IllegalStateException();
        }
        int i = this.mIndex - 1;
        this.mIndex = i;
        removeAt(i);
        this.mSize--;
        this.mCanRemove = false;
    }

    protected abstract void removeAt(int i);
}
