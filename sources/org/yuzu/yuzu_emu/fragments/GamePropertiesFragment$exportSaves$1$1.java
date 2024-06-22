package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import java.io.BufferedOutputStream;
import java.io.File;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.model.TaskState;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GamePropertiesFragment$exportSaves$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $result;
    int label;
    final /* synthetic */ GamePropertiesFragment this$0;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TaskState.values().length];
            try {
                iArr[TaskState.Completed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TaskState.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TaskState.Failed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GamePropertiesFragment$exportSaves$1$1(GamePropertiesFragment gamePropertiesFragment, Uri uri, Continuation continuation) {
        super(3, continuation);
        this.this$0 = gamePropertiesFragment;
        this.$result = uri;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        return new GamePropertiesFragment$exportSaves$1$1(this.this$0, this.$result, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        GamePropertiesFragmentArgs args;
        String replaceAfterLast$default;
        GamePropertiesFragment gamePropertiesFragment;
        int i;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        args = this.this$0.getArgs();
        String saveDir = args.getGame().getSaveDir();
        FileUtil fileUtil = FileUtil.INSTANCE;
        File file = new File(saveDir);
        replaceAfterLast$default = StringsKt__StringsKt.replaceAfterLast$default(saveDir, "/", "", null, 4, null);
        int i2 = WhenMappings.$EnumSwitchMapping$0[FileUtil.zipFromInternalStorage$default(fileUtil, file, replaceAfterLast$default, new BufferedOutputStream(this.this$0.requireContext().getContentResolver().openOutputStream(this.$result)), null, false, 8, null).ordinal()];
        if (i2 == 1) {
            gamePropertiesFragment = this.this$0;
            i = R$string.export_success;
        } else {
            if (i2 != 2 && i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            gamePropertiesFragment = this.this$0;
            i = R$string.export_failed;
        }
        String string = gamePropertiesFragment.getString(i);
        Intrinsics.checkNotNull(string);
        return string;
    }
}
