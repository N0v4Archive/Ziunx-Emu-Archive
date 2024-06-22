package kotlinx.coroutines.scheduling;

/* loaded from: classes.dex */
final class TaskContextImpl implements TaskContext {
    private final int taskMode;

    public TaskContextImpl(int i) {
        this.taskMode = i;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }
}
