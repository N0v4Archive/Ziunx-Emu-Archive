package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.AbstractListAdapter;
import org.yuzu.yuzu_emu.adapters.AbstractSingleSelectionList;
import org.yuzu.yuzu_emu.adapters.DriverAdapter;
import org.yuzu.yuzu_emu.databinding.FragmentDriverManagerBinding;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.model.Driver;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.GpuDriverMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class DriverManagerFragment$getDriver$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $result;
    int label;
    final /* synthetic */ DriverManagerFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.fragments.DriverManagerFragment$getDriver$1$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ GpuDriverMetadata $driverData;
        int label;
        final /* synthetic */ DriverManagerFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DriverManagerFragment driverManagerFragment, GpuDriverMetadata gpuDriverMetadata, Continuation continuation) {
            super(2, continuation);
            this.this$0 = driverManagerFragment;
            this.$driverData = gpuDriverMetadata;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$driverData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FragmentDriverManagerBinding fragmentDriverManagerBinding;
            FragmentDriverManagerBinding binding;
            IntRange indices;
            DriverViewModel driverViewModel;
            FragmentDriverManagerBinding binding2;
            IntRange indices2;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            fragmentDriverManagerBinding = this.this$0._binding;
            if (fragmentDriverManagerBinding != null) {
                binding = this.this$0.getBinding();
                RecyclerView.Adapter adapter = binding.listDrivers.getAdapter();
                Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.yuzu.yuzu_emu.adapters.DriverAdapter");
                DriverAdapter driverAdapter = (DriverAdapter) adapter;
                AbstractListAdapter.addItem$default(driverAdapter, Driver.Companion.toDriver$default(Driver.Companion, this.$driverData, false, 1, null), 0, null, 6, null);
                indices = CollectionsKt__CollectionsKt.getIndices(driverAdapter.getCurrentList());
                AbstractSingleSelectionList.selectItem$default(driverAdapter, indices.getLast(), null, 2, null);
                driverViewModel = this.this$0.getDriverViewModel();
                driverViewModel.showClearButton(!StringSetting.DRIVER_PATH.getGlobal());
                binding2 = this.this$0.getBinding();
                RecyclerView recyclerView = binding2.listDrivers;
                indices2 = CollectionsKt__CollectionsKt.getIndices(driverAdapter.getCurrentList());
                recyclerView.smoothScrollToPosition(indices2.getLast());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriverManagerFragment$getDriver$1$1(Uri uri, DriverManagerFragment driverManagerFragment, Continuation continuation) {
        super(3, continuation);
        this.$result = uri;
        this.this$0 = driverManagerFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        return new DriverManagerFragment$getDriver$1$1(this.$result, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        DriverViewModel driverViewModel;
        Object obj2;
        DriverViewModel driverViewModel2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            GpuDriverHelper gpuDriverHelper = GpuDriverHelper.INSTANCE;
            String str = gpuDriverHelper.getDriverStoragePath() + FileUtil.INSTANCE.getFilename(this.$result);
            File file = new File(str);
            try {
                if (!gpuDriverHelper.copyDriverToInternalStorage(this.$result)) {
                    throw new IOException("Driver failed validation!");
                }
                GpuDriverMetadata metadataFromZip = gpuDriverHelper.getMetadataFromZip(file);
                driverViewModel = this.this$0.getDriverViewModel();
                Iterator it = driverViewModel.getDriverData().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it.next();
                    if (Intrinsics.areEqual(((Pair) obj2).getSecond(), metadataFromZip)) {
                        break;
                    }
                }
                if (((Pair) obj2) != null) {
                    String string = this.this$0.getString(R$string.driver_already_installed);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    return string;
                }
                driverViewModel2 = this.this$0.getDriverViewModel();
                driverViewModel2.onDriverAdded(new Pair(str, metadataFromZip));
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, metadataFromZip, null);
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (IOException unused) {
                if (file.exists()) {
                    file.delete();
                }
                String string2 = this.this$0.getString(R$string.select_gpu_driver_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                return string2;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return new Object();
    }
}
