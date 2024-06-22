package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator() { // from class: androidx.fragment.app.BackStackState.1
        @Override // android.os.Parcelable.Creator
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    };
    final List mFragments;
    final List mTransactions;

    BackStackState(Parcel parcel) {
        this.mFragments = parcel.createStringArrayList();
        this.mTransactions = parcel.createTypedArrayList(BackStackRecordState.CREATOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackStackState(List list, List list2) {
        this.mFragments = list;
        this.mTransactions = list2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List instantiate(FragmentManager fragmentManager, Map map) {
        HashMap hashMap = new HashMap(this.mFragments.size());
        for (String str : this.mFragments) {
            Fragment fragment = (Fragment) map.get(str);
            if (fragment != null) {
                hashMap.put(fragment.mWho, fragment);
            } else {
                Bundle savedState = fragmentManager.getFragmentStore().setSavedState(str, null);
                if (savedState != null) {
                    ClassLoader classLoader = fragmentManager.getHost().getContext().getClassLoader();
                    Fragment instantiate = ((FragmentState) savedState.getParcelable("state")).instantiate(fragmentManager.getFragmentFactory(), classLoader);
                    instantiate.mSavedFragmentState = savedState;
                    if (savedState.getBundle("savedInstanceState") == null) {
                        instantiate.mSavedFragmentState.putBundle("savedInstanceState", new Bundle());
                    }
                    Bundle bundle = savedState.getBundle("arguments");
                    if (bundle != null) {
                        bundle.setClassLoader(classLoader);
                    }
                    instantiate.setArguments(bundle);
                    hashMap.put(instantiate.mWho, instantiate);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mTransactions.iterator();
        while (it.hasNext()) {
            arrayList.add(((BackStackRecordState) it.next()).instantiate(fragmentManager, hashMap));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mFragments);
        parcel.writeTypedList(this.mTransactions);
    }
}
