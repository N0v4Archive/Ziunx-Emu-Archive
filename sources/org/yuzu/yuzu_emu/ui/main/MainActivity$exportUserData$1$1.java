package org.yuzu.yuzu_emu.ui.main;

import android.net.Uri;
import java.io.BufferedOutputStream;
import java.io.File;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.model.TaskState;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MainActivity$exportUserData$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $result;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainActivity this$0;

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
                iArr[TaskState.Failed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TaskState.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$exportUserData$1$1(MainActivity mainActivity, Uri uri, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mainActivity;
        this.$result = uri;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        MainActivity$exportUserData$1$1 mainActivity$exportUserData$1$1 = new MainActivity$exportUserData$1$1(this.this$0, this.$result, continuation);
        mainActivity$exportUserData$1$1.L$0 = function2;
        return mainActivity$exportUserData$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        FileUtil fileUtil = FileUtil.INSTANCE;
        DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
        String userDirectory = directoryInitialization.getUserDirectory();
        Intrinsics.checkNotNull(userDirectory);
        File file = new File(userDirectory);
        String userDirectory2 = directoryInitialization.getUserDirectory();
        Intrinsics.checkNotNull(userDirectory2);
        int i2 = WhenMappings.$EnumSwitchMapping$0[fileUtil.zipFromInternalStorage(file, userDirectory2, new BufferedOutputStream(this.this$0.getContentResolver().openOutputStream(this.$result)), function2, false).ordinal()];
        if (i2 == 1) {
            String string = this.this$0.getString(R$string.user_data_export_success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (i2 == 2) {
            i = R$string.export_failed;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R$string.user_data_export_cancelled;
        }
        return Boxing.boxInt(i);
    }
}
