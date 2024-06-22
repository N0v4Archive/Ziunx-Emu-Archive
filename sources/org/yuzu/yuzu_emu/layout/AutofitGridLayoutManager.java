package org.yuzu.yuzu_emu.layout;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.yuzu.yuzu_emu.R$dimen;

/* loaded from: classes.dex */
public final class AutofitGridLayoutManager extends GridLayoutManager {
    private int columnWidth;
    private boolean isColumnWidthChanged;
    private int lastHeight;
    private int lastWidth;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutofitGridLayoutManager(Context context, int i) {
        super(context, 1);
        Intrinsics.checkNotNullParameter(context, "context");
        this.isColumnWidthChanged = true;
        setColumnWidth(checkedColumnWidth(context, i));
    }

    private final int checkedColumnWidth(Context context, int i) {
        return i <= 0 ? context.getResources().getDimensionPixelSize(R$dimen.spacing_xtralarge) : i;
    }

    private final void setColumnWidth(int i) {
        if (i <= 0 || i == this.columnWidth) {
            return;
        }
        this.columnWidth = i;
        this.isColumnWidthChanged = true;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int paddingTop;
        int paddingBottom;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(recycler, "recycler");
        Intrinsics.checkNotNullParameter(state, "state");
        int width = getWidth();
        int height = getHeight();
        if (this.columnWidth > 0 && width > 0 && height > 0 && (this.isColumnWidthChanged || this.lastWidth != width || this.lastHeight != height)) {
            if (getOrientation() == 1) {
                paddingTop = width - getPaddingRight();
                paddingBottom = getPaddingLeft();
            } else {
                paddingTop = height - getPaddingTop();
                paddingBottom = getPaddingBottom();
            }
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(1, (paddingTop - paddingBottom) / this.columnWidth);
            setSpanCount(coerceAtLeast);
            this.isColumnWidthChanged = false;
        }
        this.lastWidth = width;
        this.lastHeight = height;
        super.onLayoutChildren(recycler, state);
    }
}
