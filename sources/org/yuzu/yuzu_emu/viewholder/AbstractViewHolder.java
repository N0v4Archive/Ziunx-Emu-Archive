package org.yuzu.yuzu_emu.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractViewHolder(ViewBinding binding) {
        super(binding.getRoot());
        Intrinsics.checkNotNullParameter(binding, "binding");
    }

    public abstract void bind(Object obj);
}
