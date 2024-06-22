package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ListAdapter extends RecyclerView.Adapter {
    final AsyncListDiffer mDiffer;
    private final AsyncListDiffer.ListListener mListener;

    /* JADX INFO: Access modifiers changed from: protected */
    public ListAdapter(AsyncDifferConfig asyncDifferConfig) {
        AsyncListDiffer.ListListener listListener = new AsyncListDiffer.ListListener() { // from class: androidx.recyclerview.widget.ListAdapter.1
            @Override // androidx.recyclerview.widget.AsyncListDiffer.ListListener
            public void onCurrentListChanged(List list, List list2) {
                ListAdapter.this.onCurrentListChanged(list, list2);
            }
        };
        this.mListener = listListener;
        AsyncListDiffer asyncListDiffer = new AsyncListDiffer(new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.mDiffer = asyncListDiffer;
        asyncListDiffer.addListListener(listListener);
    }

    public List getCurrentList() {
        return this.mDiffer.getCurrentList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDiffer.getCurrentList().size();
    }

    public void onCurrentListChanged(List list, List list2) {
    }

    public void submitList(List list) {
        this.mDiffer.submitList(list);
    }

    public void submitList(List list, Runnable runnable) {
        this.mDiffer.submitList(list, runnable);
    }
}
