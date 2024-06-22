package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContract;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class ActivityResultContracts$OpenDocumentTree extends ActivityResultContract {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        if (uri != null) {
            intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        }
        return intent;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final ActivityResultContract.SynchronousResult getSynchronousResult(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        return null;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Uri parseResult(int i, Intent intent) {
        if (!(i == -1)) {
            intent = null;
        }
        if (intent != null) {
            return intent.getData();
        }
        return null;
    }
}
