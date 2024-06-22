package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
final class FakeDrag {
    private final RecyclerView mRecyclerView;
    private final ScrollEventAdapter mScrollEventAdapter;
    private final ViewPager2 mViewPager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FakeDrag(ViewPager2 viewPager2, ScrollEventAdapter scrollEventAdapter, RecyclerView recyclerView) {
        this.mViewPager = viewPager2;
        this.mScrollEventAdapter = scrollEventAdapter;
        this.mRecyclerView = recyclerView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFakeDragging() {
        return this.mScrollEventAdapter.isFakeDragging();
    }
}
