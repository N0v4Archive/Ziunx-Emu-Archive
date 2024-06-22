package org.yuzu.yuzu_emu.fragments;

import androidx.fragment.app.FragmentActivity;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.GameVerificationResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GameInfoFragment$onViewCreated$1$7$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GameInfoFragment this$0;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GameVerificationResult.values().length];
            try {
                iArr[GameVerificationResult.Success.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[GameVerificationResult.Failed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[GameVerificationResult.NotImplemented.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GameInfoFragment$onViewCreated$1$7$1(GameInfoFragment gameInfoFragment, Continuation continuation) {
        super(3, continuation);
        this.this$0 = gameInfoFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        GameInfoFragment$onViewCreated$1$7$1 gameInfoFragment$onViewCreated$1$7$1 = new GameInfoFragment$onViewCreated$1$7$1(this.this$0, continuation);
        gameInfoFragment$onViewCreated$1$7$1.L$0 = function2;
        return gameInfoFragment$onViewCreated$1$7$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        GameInfoFragmentArgs args;
        MessageDialogFragment.Companion companion;
        FragmentActivity fragmentActivity;
        int i;
        String str;
        int i2;
        MessageDialogFragment newInstance;
        MessageDialogFragment newInstance2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        GameVerificationResult.Companion companion2 = GameVerificationResult.Companion;
        NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
        args = this.this$0.getArgs();
        int i3 = WhenMappings.$EnumSwitchMapping$0[companion2.from(nativeLibrary.verifyGameContents(args.getGame().getPath(), function2)).ordinal()];
        if (i3 == 1) {
            companion = MessageDialogFragment.Companion;
            fragmentActivity = null;
            i = R$string.verify_success;
            str = null;
            i2 = R$string.operation_completed_successfully;
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                newInstance2 = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : null, (r31 & 2) != 0 ? 0 : R$string.verify_no_result, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.verify_no_result_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                return newInstance2;
            }
            companion = MessageDialogFragment.Companion;
            fragmentActivity = null;
            i = R$string.verify_failure;
            str = null;
            i2 = R$string.verify_failure_description;
        }
        newInstance = companion.newInstance((r31 & 1) != 0 ? null : fragmentActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : str, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
        return newInstance;
    }
}
