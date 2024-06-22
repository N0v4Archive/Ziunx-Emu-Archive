package org.yuzu.yuzu_emu.model;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* loaded from: classes.dex */
final class DriverViewModel$isInteractionAllowed$1 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DriverViewModel$isInteractionAllowed$1(Continuation continuation) {
        super(4, continuation);
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), (Continuation) obj4);
    }

    public final Object invoke(boolean z, boolean z2, boolean z3, Continuation continuation) {
        DriverViewModel$isInteractionAllowed$1 driverViewModel$isInteractionAllowed$1 = new DriverViewModel$isInteractionAllowed$1(continuation);
        driverViewModel$isInteractionAllowed$1.Z$0 = z;
        driverViewModel$isInteractionAllowed$1.Z$1 = z2;
        driverViewModel$isInteractionAllowed$1.Z$2 = z3;
        return driverViewModel$isInteractionAllowed$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boxing.boxBoolean((this.Z$0 || !this.Z$1 || this.Z$2) ? false : true);
    }
}
