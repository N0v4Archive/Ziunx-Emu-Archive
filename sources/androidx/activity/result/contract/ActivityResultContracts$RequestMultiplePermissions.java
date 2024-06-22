package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* loaded from: classes.dex */
public final class ActivityResultContracts$RequestMultiplePermissions extends ActivityResultContract {
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent createIntent$activity_release(String[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intent putExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", input);
            Intrinsics.checkNotNullExpressionValue(putExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
            return putExtra;
        }
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, String[] input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        return Companion.createIntent$activity_release(input);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public ActivityResultContract.SynchronousResult getSynchronousResult(Context context, String[] input) {
        int mapCapacity;
        int coerceAtLeast;
        Map emptyMap;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        boolean z = true;
        if (input.length == 0) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return new ActivityResultContract.SynchronousResult(emptyMap);
        }
        int length = input.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (!(ContextCompat.checkSelfPermission(context, input[i]) == 0)) {
                z = false;
                break;
            }
            i++;
        }
        if (!z) {
            return null;
        }
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(input.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (String str : input) {
            Pair pair = TuplesKt.to(str, Boolean.TRUE);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return new ActivityResultContract.SynchronousResult(linkedHashMap);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public Map parseResult(int i, Intent intent) {
        Map emptyMap;
        List filterNotNull;
        List zip;
        Map map;
        Map emptyMap2;
        Map emptyMap3;
        if (i != -1) {
            emptyMap3 = MapsKt__MapsKt.emptyMap();
            return emptyMap3;
        }
        if (intent == null) {
            emptyMap2 = MapsKt__MapsKt.emptyMap();
            return emptyMap2;
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra == null || stringArrayExtra == null) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return emptyMap;
        }
        ArrayList arrayList = new ArrayList(intArrayExtra.length);
        for (int i2 : intArrayExtra) {
            arrayList.add(Boolean.valueOf(i2 == 0));
        }
        filterNotNull = ArraysKt___ArraysKt.filterNotNull(stringArrayExtra);
        zip = CollectionsKt___CollectionsKt.zip(filterNotNull, arrayList);
        map = MapsKt__MapsKt.toMap(zip);
        return map;
    }
}
