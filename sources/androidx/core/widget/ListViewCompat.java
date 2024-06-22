package androidx.core.widget;

import android.widget.ListView;

/* loaded from: classes.dex */
public abstract class ListViewCompat {

    /* loaded from: classes.dex */
    static class Api19Impl {
        static boolean canScrollList(ListView listView, int i) {
            return listView.canScrollList(i);
        }

        static void scrollListBy(ListView listView, int i) {
            listView.scrollListBy(i);
        }
    }

    public static boolean canScrollList(ListView listView, int i) {
        return Api19Impl.canScrollList(listView, i);
    }

    public static void scrollListBy(ListView listView, int i) {
        Api19Impl.scrollListBy(listView, i);
    }
}
