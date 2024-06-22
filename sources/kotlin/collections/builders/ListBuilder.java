package kotlin.collections.builders;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class ListBuilder extends AbstractMutableList implements RandomAccess, Serializable {
    private static final Companion Companion = new Companion(null);
    private static final ListBuilder Empty;
    private Object[] array;
    private final ListBuilder backing;
    private boolean isReadOnly;
    private int length;
    private int offset;
    private final ListBuilder root;

    /* loaded from: classes.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Itr implements ListIterator, KMappedMarker {
        private int expectedModCount;
        private int index;
        private int lastIndex;
        private final ListBuilder list;

        public Itr(ListBuilder list, int i) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this.index = i;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) list).modCount;
        }

        private final void checkForComodification() {
            if (((AbstractList) this.list).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            checkForComodification();
            ListBuilder listBuilder = this.list;
            int i = this.index;
            this.index = i + 1;
            listBuilder.add(i, obj);
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < this.list.length;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            checkForComodification();
            if (this.index >= this.list.length) {
                throw new NoSuchElementException();
            }
            int i = this.index;
            this.index = i + 1;
            this.lastIndex = i;
            return this.list.array[this.list.offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            checkForComodification();
            int i = this.index;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            int i2 = i - 1;
            this.index = i2;
            this.lastIndex = i2;
            return this.list.array[this.list.offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            checkForComodification();
            int i = this.lastIndex;
            if (!(i != -1)) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
            }
            this.list.remove(i);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            checkForComodification();
            int i = this.lastIndex;
            if (!(i != -1)) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
            }
            this.list.set(i, obj);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public ListBuilder(int i) {
        this(ListBuilderKt.arrayOfUninitializedElements(i), 0, 0, false, null, null);
    }

    private ListBuilder(Object[] objArr, int i, int i2, boolean z, ListBuilder listBuilder, ListBuilder listBuilder2) {
        this.array = objArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
        if (listBuilder != null) {
            ((AbstractList) this).modCount = ((AbstractList) listBuilder).modCount;
        }
    }

    private final void addAllInternal(int i, Collection collection, int i2) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAllInternal(i, collection, i2);
            this.array = this.backing.array;
            this.length += i2;
        } else {
            insertAtInternal(i, i2);
            Iterator it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    private final void addAtInternal(int i, Object obj) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder == null) {
            insertAtInternal(i, 1);
            this.array[i] = obj;
        } else {
            listBuilder.addAtInternal(i, obj);
            this.array = this.backing.array;
            this.length++;
        }
    }

    private final void checkForComodification() {
        ListBuilder listBuilder = this.root;
        if (listBuilder != null && ((AbstractList) listBuilder).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    private final void checkIsMutable() {
        if (isEffectivelyReadOnly()) {
            throw new UnsupportedOperationException();
        }
    }

    private final boolean contentEquals(List list) {
        boolean subarrayContentEquals;
        subarrayContentEquals = ListBuilderKt.subarrayContentEquals(this.array, this.offset, this.length, list);
        return subarrayContentEquals;
    }

    private final void ensureCapacityInternal(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.array;
        if (i > objArr.length) {
            this.array = ListBuilderKt.copyOfUninitializedElements(this.array, kotlin.collections.AbstractList.Companion.newCapacity$kotlin_stdlib(objArr.length, i));
        }
    }

    private final void ensureExtraCapacity(int i) {
        ensureCapacityInternal(this.length + i);
    }

    private final void insertAtInternal(int i, int i2) {
        ensureExtraCapacity(i2);
        Object[] objArr = this.array;
        ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i + i2, i, this.offset + this.length);
        this.length += i2;
    }

    private final boolean isEffectivelyReadOnly() {
        ListBuilder listBuilder;
        return this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly);
    }

    private final void registerModification() {
        ((AbstractList) this).modCount++;
    }

    private final Object removeAtInternal(int i) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.removeAtInternal(i);
        }
        Object[] objArr = this.array;
        Object obj = objArr[i];
        ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i, i + 1, this.offset + this.length);
        ListBuilderKt.resetAt(this.array, (this.offset + this.length) - 1);
        this.length--;
        return obj;
    }

    private final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            registerModification();
        }
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.removeRangeInternal(i, i2);
        } else {
            Object[] objArr = this.array;
            ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i, i + i2, this.length);
            Object[] objArr2 = this.array;
            int i3 = this.length;
            ListBuilderKt.resetRange(objArr2, i3 - i2, i3);
        }
        this.length -= i2;
    }

    private final int retainOrRemoveAllInternal(int i, int i2, Collection collection, boolean z) {
        int i3;
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            i3 = listBuilder.retainOrRemoveAllInternal(i, i2, collection, z);
        } else {
            int i4 = 0;
            int i5 = 0;
            while (i4 < i2) {
                int i6 = i + i4;
                if (collection.contains(this.array[i6]) == z) {
                    Object[] objArr = this.array;
                    i4++;
                    objArr[i5 + i] = objArr[i6];
                    i5++;
                } else {
                    i4++;
                }
            }
            int i7 = i2 - i5;
            Object[] objArr2 = this.array;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i + i5, i2 + i, this.length);
            Object[] objArr3 = this.array;
            int i8 = this.length;
            ListBuilderKt.resetRange(objArr3, i8 - i7, i8);
            i3 = i7;
        }
        if (i3 > 0) {
            registerModification();
        }
        this.length -= i3;
        return i3;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        checkIsMutable();
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        addAtInternal(this.offset + i, obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        checkIsMutable();
        checkForComodification();
        addAtInternal(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        int size = elements.size();
        addAllInternal(this.offset + i, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        checkForComodification();
        int size = elements.size();
        addAllInternal(this.offset + this.length, elements, size);
        return size > 0;
    }

    public final List build() {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        checkIsMutable();
        this.isReadOnly = true;
        return this.length > 0 ? this : Empty;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        checkIsMutable();
        checkForComodification();
        removeRangeInternal(this.offset, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        checkForComodification();
        return obj == this || ((obj instanceof List) && contentEquals((List) obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return this.array[this.offset + i];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        checkForComodification();
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int subarrayContentHashCode;
        checkForComodification();
        subarrayContentHashCode = ListBuilderKt.subarrayContentHashCode(this.array, this.offset, this.length);
        return subarrayContentHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        checkForComodification();
        for (int i = 0; i < this.length; i++) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        checkForComodification();
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        checkForComodification();
        for (int i = this.length - 1; i >= 0; i--) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int i) {
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        return new Itr(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        checkIsMutable();
        checkForComodification();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            remove(indexOf);
        }
        return indexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        checkForComodification();
        return retainOrRemoveAllInternal(this.offset, this.length, elements, false) > 0;
    }

    @Override // kotlin.collections.AbstractMutableList
    public Object removeAt(int i) {
        checkIsMutable();
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return removeAtInternal(this.offset + i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        checkForComodification();
        return retainOrRemoveAllInternal(this.offset, this.length, elements, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        checkIsMutable();
        checkForComodification();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        Object[] objArr = this.array;
        int i2 = this.offset;
        Object obj2 = objArr[i2 + i];
        objArr[i2 + i] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public List subList(int i, int i2) {
        kotlin.collections.AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
        Object[] objArr = this.array;
        int i3 = this.offset + i;
        int i4 = i2 - i;
        boolean z = this.isReadOnly;
        ListBuilder listBuilder = this.root;
        return new ListBuilder(objArr, i3, i4, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        Object[] copyOfRange;
        checkForComodification();
        Object[] objArr = this.array;
        int i = this.offset;
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(objArr, i, this.length + i);
        return copyOfRange;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] destination) {
        Object[] terminateCollectionToArray;
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkForComodification();
        int length = destination.length;
        int i = this.length;
        if (length < i) {
            Object[] objArr = this.array;
            int i2 = this.offset;
            Object[] copyOfRange = Arrays.copyOfRange(objArr, i2, i + i2, destination.getClass());
            Intrinsics.checkNotNullExpressionValue(copyOfRange, "copyOfRange(...)");
            return copyOfRange;
        }
        Object[] objArr2 = this.array;
        int i3 = this.offset;
        ArraysKt___ArraysJvmKt.copyInto(objArr2, destination, 0, i3, i + i3);
        terminateCollectionToArray = CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(this.length, destination);
        return terminateCollectionToArray;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        String subarrayContentToString;
        checkForComodification();
        subarrayContentToString = ListBuilderKt.subarrayContentToString(this.array, this.offset, this.length, this);
        return subarrayContentToString;
    }
}
