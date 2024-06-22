package kotlinx.coroutines.internal;

/* loaded from: classes.dex */
public interface ThreadSafeHeapNode {
    ThreadSafeHeap getHeap();

    int getIndex();

    void setHeap(ThreadSafeHeap threadSafeHeap);

    void setIndex(int i);
}
