package kotlinx.coroutines;

/* loaded from: classes.dex */
public interface ChildHandle extends DisposableHandle {
    boolean childCancelled(Throwable th);

    Job getParent();
}
