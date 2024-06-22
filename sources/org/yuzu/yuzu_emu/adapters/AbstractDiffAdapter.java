package org.yuzu.yuzu_emu.adapters;

import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public abstract class AbstractDiffAdapter extends ListAdapter {

    /* loaded from: classes.dex */
    private static final class DiffCallback extends DiffUtil.ItemCallback {
        private final boolean exact;

        public DiffCallback(boolean z) {
            this.exact = z;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(Object oldItem, Object newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(Object oldItem, Object newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return this.exact ? oldItem == newItem : Intrinsics.areEqual(oldItem, newItem);
        }
    }

    public AbstractDiffAdapter(boolean z) {
        super(new AsyncDifferConfig.Builder(new DiffCallback(z)).build());
    }

    public /* synthetic */ AbstractDiffAdapter(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AbstractViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Object obj = getCurrentList().get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        holder.bind(obj);
    }
}
