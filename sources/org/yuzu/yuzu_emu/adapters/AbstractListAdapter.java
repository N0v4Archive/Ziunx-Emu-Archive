package org.yuzu.yuzu_emu.adapters;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public abstract class AbstractListAdapter extends RecyclerView.Adapter {
    private List currentList;

    public AbstractListAdapter(List currentList) {
        Intrinsics.checkNotNullParameter(currentList, "currentList");
        this.currentList = currentList;
    }

    public static /* synthetic */ void addItem$default(AbstractListAdapter abstractListAdapter, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItem");
        }
        if ((i2 & 2) != 0) {
            i = -1;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        abstractListAdapter.addItem(obj, i, function1);
    }

    public static /* synthetic */ void onItemChanged$default(AbstractListAdapter abstractListAdapter, int i, Function1 function1, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onItemChanged");
        }
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        abstractListAdapter.onItemChanged(i, function1);
    }

    public static /* synthetic */ void removeItem$default(AbstractListAdapter abstractListAdapter, int i, Function1 function1, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeItem");
        }
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        abstractListAdapter.removeItem(i, function1);
    }

    public void addItem(Object item, int i, Function1 function1) {
        List mutableList;
        Intrinsics.checkNotNullParameter(item, "item");
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) getCurrentList());
        if (i == -1) {
            mutableList.add(item);
            setCurrentList(mutableList);
            i = getCurrentList().size() - 1;
        } else {
            mutableList.add(i, item);
            setCurrentList(mutableList);
        }
        onItemAdded(i, function1);
    }

    public List getCurrentList() {
        return this.currentList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getCurrentList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AbstractViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(getCurrentList().get(i));
    }

    protected final void onItemAdded(int i, Function1 function1) {
        notifyItemInserted(i);
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    protected final void onItemChanged(int i, Function1 function1) {
        notifyItemChanged(i);
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    protected final void onItemRemoved(int i, Function1 function1) {
        notifyItemRemoved(i);
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    public final void removeItem(int i, Function1 function1) {
        List mutableList;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) getCurrentList());
        mutableList.remove(i);
        setCurrentList(mutableList);
        onItemRemoved(i, function1);
    }

    public void replaceList(List newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        setCurrentList(newList);
        notifyDataSetChanged();
    }

    public void setCurrentList(List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.currentList = list;
    }
}
