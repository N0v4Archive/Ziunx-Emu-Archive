package org.yuzu.yuzu_emu.model;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.yuzu.yuzu_emu.NativeLibrary;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AddonViewModel$refreshAddons$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ AddonViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.model.AddonViewModel$refreshAddons$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ AddonViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AddonViewModel addonViewModel, Continuation continuation) {
            super(2, continuation);
            this.this$0 = addonViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List mutableList;
            MutableStateFlow mutableStateFlow;
            AtomicBoolean atomicBoolean;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            Game game = this.this$0.getGame();
            Intrinsics.checkNotNull(game);
            String path = game.getPath();
            Game game2 = this.this$0.getGame();
            Intrinsics.checkNotNull(game2);
            Patch[] patchesForFile = nativeLibrary.getPatchesForFile(path, game2.getProgramId());
            if (patchesForFile == null) {
                patchesForFile = new Patch[0];
            }
            mutableList = ArraysKt___ArraysKt.toMutableList(patchesForFile);
            if (mutableList.size() > 1) {
                CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, new Comparator() { // from class: org.yuzu.yuzu_emu.model.AddonViewModel$refreshAddons$1$1$invokeSuspend$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(Object obj2, Object obj3) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(((Patch) obj2).getName(), ((Patch) obj3).getName());
                        return compareValues;
                    }
                });
            }
            mutableStateFlow = this.this$0._patchList;
            mutableStateFlow.setValue(mutableList);
            atomicBoolean = this.this$0.isRefreshing;
            atomicBoolean.set(false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonViewModel$refreshAddons$1(AddonViewModel addonViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = addonViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AddonViewModel$refreshAddons$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AddonViewModel$refreshAddons$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
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
