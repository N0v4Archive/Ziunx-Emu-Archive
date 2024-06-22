package org.yuzu.yuzu_emu.model;

import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.GpuDriverMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class DriverViewModel$onLaunchGame$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ File $selectedDriverFile;
    final /* synthetic */ GpuDriverMetadata $selectedDriverMetadata;
    int label;
    final /* synthetic */ DriverViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.model.DriverViewModel$onLaunchGame$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ File $selectedDriverFile;
        final /* synthetic */ GpuDriverMetadata $selectedDriverMetadata;
        int label;
        final /* synthetic */ DriverViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(GpuDriverMetadata gpuDriverMetadata, DriverViewModel driverViewModel, File file, Continuation continuation) {
            super(2, continuation);
            this.$selectedDriverMetadata = gpuDriverMetadata;
            this.this$0 = driverViewModel;
            this.$selectedDriverFile = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$selectedDriverMetadata, this.this$0, this.$selectedDriverFile, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$selectedDriverMetadata.getName() != null && this.$selectedDriverFile.exists()) {
                GpuDriverHelper.INSTANCE.installCustomDriver(this.$selectedDriverFile);
            } else {
                GpuDriverHelper.INSTANCE.installDefaultDriver();
            }
            this.this$0.setDriverReady();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriverViewModel$onLaunchGame$1(GpuDriverMetadata gpuDriverMetadata, DriverViewModel driverViewModel, File file, Continuation continuation) {
        super(2, continuation);
        this.$selectedDriverMetadata = gpuDriverMetadata;
        this.this$0 = driverViewModel;
        this.$selectedDriverFile = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DriverViewModel$onLaunchGame$1(this.$selectedDriverMetadata, this.this$0, this.$selectedDriverFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DriverViewModel$onLaunchGame$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$selectedDriverMetadata, this.this$0, this.$selectedDriverFile, null);
            this.label = 1;
            if (BuildersKt.withContext(io, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
