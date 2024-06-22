package kotlinx.coroutines;

import java.util.concurrent.CancellationException;

/* loaded from: classes.dex */
public interface ParentJob extends Job {
    CancellationException getChildJobCancellationCause();
}
