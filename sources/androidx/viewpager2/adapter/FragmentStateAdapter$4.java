package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes.dex */
class FragmentStateAdapter$4 implements LifecycleEventObserver {
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ Runnable val$runnable;

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.val$handler.removeCallbacks(this.val$runnable);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
