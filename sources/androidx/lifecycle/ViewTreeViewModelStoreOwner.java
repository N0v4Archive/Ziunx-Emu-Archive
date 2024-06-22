package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R$id;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public abstract class ViewTreeViewModelStoreOwner {
    public static final void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R$id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }
}
