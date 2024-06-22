package org.yuzu.yuzu_emu.adapters;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.yuzu.yuzu_emu.model.SelectableItem;

/* loaded from: classes.dex */
public abstract class AbstractSingleSelectionList extends AbstractListAdapter {
    private List currentList;
    private final DefaultSelection defaultSelection;
    private int selectedItem;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class DefaultSelection {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ DefaultSelection[] $VALUES;
        public static final DefaultSelection Start = new DefaultSelection("Start", 0);
        public static final DefaultSelection End = new DefaultSelection("End", 1);

        private static final /* synthetic */ DefaultSelection[] $values() {
            return new DefaultSelection[]{Start, End};
        }

        static {
            DefaultSelection[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private DefaultSelection(String str, int i) {
        }

        public static DefaultSelection valueOf(String str) {
            return (DefaultSelection) Enum.valueOf(DefaultSelection.class, str);
        }

        public static DefaultSelection[] values() {
            return (DefaultSelection[]) $VALUES.clone();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DefaultSelection.values().length];
            try {
                iArr[DefaultSelection.Start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DefaultSelection.End.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractSingleSelectionList(List currentList, DefaultSelection defaultSelection) {
        super(currentList);
        Intrinsics.checkNotNullParameter(currentList, "currentList");
        Intrinsics.checkNotNullParameter(defaultSelection, "defaultSelection");
        this.currentList = currentList;
        this.defaultSelection = defaultSelection;
        this.selectedItem = getDefaultSelection();
        findSelectedItem();
    }

    public /* synthetic */ AbstractSingleSelectionList(List list, DefaultSelection defaultSelection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? DefaultSelection.Start : defaultSelection);
    }

    private final void findSelectedItem() {
        int size = this.currentList.size();
        for (int i = 0; i < size; i++) {
            if (((SelectableItem) this.currentList.get(i)).getSelected()) {
                this.selectedItem = i;
                return;
            }
        }
    }

    private final int getDefaultSelection() {
        IntRange indices;
        IntRange indices2;
        int i = WhenMappings.$EnumSwitchMapping$0[this.defaultSelection.ordinal()];
        if (i == 1) {
            indices = CollectionsKt__CollectionsKt.getIndices(this.currentList);
            return indices.getFirst();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        indices2 = CollectionsKt__CollectionsKt.getIndices(this.currentList);
        return indices2.getLast();
    }

    public static /* synthetic */ void selectItem$default(AbstractSingleSelectionList abstractSingleSelectionList, int i, Function1 function1, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: selectItem");
        }
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        abstractSingleSelectionList.selectItem(i, function1);
    }

    @Override // org.yuzu.yuzu_emu.adapters.AbstractListAdapter
    public void addItem(SelectableItem item, int i, Function1 function1) {
        Intrinsics.checkNotNullParameter(item, "item");
        super.addItem((Object) item, i, function1);
        int i2 = this.selectedItem;
        if (i > i2 || i == -1) {
            return;
        }
        this.selectedItem = i2 + 1;
    }

    @Override // org.yuzu.yuzu_emu.adapters.AbstractListAdapter
    public final List getCurrentList() {
        return this.currentList;
    }

    public final void removeSelectableItem(int i, Function2 function2) {
        AbstractListAdapter.removeItem$default(this, i, null, 2, null);
        int i2 = this.selectedItem;
        if (i == i2) {
            int defaultSelection = getDefaultSelection();
            this.selectedItem = defaultSelection;
            ((SelectableItem) this.currentList.get(defaultSelection)).onSelectionStateChanged(true);
            AbstractListAdapter.onItemChanged$default(this, this.selectedItem, null, 2, null);
        } else if (i < i2) {
            this.selectedItem = i2 - 1;
        }
        if (function2 != null) {
            function2.invoke(Integer.valueOf(i), Integer.valueOf(this.selectedItem));
        }
    }

    @Override // org.yuzu.yuzu_emu.adapters.AbstractListAdapter
    public void replaceList(List newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        super.replaceList(newList);
        findSelectedItem();
    }

    public final void selectItem(int i, Function1 function1) {
        IntRange indices;
        IntRange indices2;
        int i2 = this.selectedItem;
        if (i == i2) {
            return;
        }
        this.selectedItem = i;
        indices = CollectionsKt__CollectionsKt.getIndices(this.currentList);
        if (indices.contains(this.selectedItem)) {
            ((SelectableItem) this.currentList.get(this.selectedItem)).onSelectionStateChanged(true);
        }
        indices2 = CollectionsKt__CollectionsKt.getIndices(this.currentList);
        if (indices2.contains(i2)) {
            ((SelectableItem) this.currentList.get(i2)).onSelectionStateChanged(false);
        }
        AbstractListAdapter.onItemChanged$default(this, i2, null, 2, null);
        AbstractListAdapter.onItemChanged$default(this, this.selectedItem, null, 2, null);
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    @Override // org.yuzu.yuzu_emu.adapters.AbstractListAdapter
    public final void setCurrentList(List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.currentList = list;
    }
}
