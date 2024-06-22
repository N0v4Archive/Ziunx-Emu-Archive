package kotlinx.coroutines;

/* loaded from: classes.dex */
public interface ChildJob extends Job {
    void parentCancelled(ParentJob parentJob);
}
