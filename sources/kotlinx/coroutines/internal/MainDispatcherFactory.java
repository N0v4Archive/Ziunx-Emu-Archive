package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* loaded from: classes.dex */
public interface MainDispatcherFactory {
    MainCoroutineDispatcher createDispatcher(List list);

    int getLoadPriority();

    String hintOnError();
}
