package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MenuHostHelper {
    private final Runnable mOnInvalidateMenuCallback;
    private final CopyOnWriteArrayList mMenuProviders = new CopyOnWriteArrayList();
    private final Map mProviderToLifecycleContainers = new HashMap();

    public MenuHostHelper(Runnable runnable) {
        this.mOnInvalidateMenuCallback = runnable;
    }

    public void addMenuProvider(MenuProvider menuProvider) {
        this.mMenuProviders.add(menuProvider);
        this.mOnInvalidateMenuCallback.run();
    }

    public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).onCreateMenu(menu, menuInflater);
        }
    }

    public void onMenuClosed(Menu menu) {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).onMenuClosed(menu);
        }
    }

    public boolean onMenuItemSelected(MenuItem menuItem) {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            if (((MenuProvider) it.next()).onMenuItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void onPrepareMenu(Menu menu) {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).onPrepareMenu(menu);
        }
    }

    public void removeMenuProvider(MenuProvider menuProvider) {
        this.mMenuProviders.remove(menuProvider);
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mProviderToLifecycleContainers.remove(menuProvider));
        this.mOnInvalidateMenuCallback.run();
    }
}
