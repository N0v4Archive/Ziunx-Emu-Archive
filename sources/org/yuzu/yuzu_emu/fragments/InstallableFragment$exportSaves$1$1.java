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
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.model.TaskState;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class InstallableFragment$exportSaves$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $result;
    int label;
    final /* synthetic */ InstallableFragment this$0;

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
    public InstallableFragment$exportSaves$1$1(InstallableFragment installableFragment, Uri uri, Continuation continuation) {
        super(3, continuation);
        this.this$0 = installableFragment;
        this.$result = uri;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        return new InstallableFragment$exportSaves$1$1(this.this$0, this.$result, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InstallableFragment installableFragment;
        int i;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        File file = new File(this.this$0.requireContext().getCacheDir().getPath() + "/saves/");
        file.mkdir();
        DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
        String userDirectory = directoryInitialization.getUserDirectory();
        NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
        File file2 = new File(userDirectory + "/nand" + nativeLibrary.getDefaultProfileSaveDataRoot(false));
        if (file2.exists()) {
            FilesKt__UtilsKt.copyRecursively$default(file2, file, false, null, 6, null);
        }
        File file3 = new File(directoryInitialization.getUserDirectory() + "/nand" + nativeLibrary.getDefaultProfileSaveDataRoot(true));
        if (file3.exists()) {
            FilesKt__UtilsKt.copyRecursively$default(file3, file, false, null, 6, null);
        }
        File[] listFiles = file.listFiles();
        if ((listFiles != null ? listFiles.length : 0) == 0) {
            FilesKt__UtilsKt.deleteRecursively(file);
            String string = this.this$0.getString(R$string.no_save_data_found);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        FileUtil fileUtil = FileUtil.INSTANCE;
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        TaskState zipFromInternalStorage$default = FileUtil.zipFromInternalStorage$default(fileUtil, file, path, new BufferedOutputStream(this.this$0.requireContext().getContentResolver().openOutputStream(this.$result)), null, false, 24, null);
        FilesKt__UtilsKt.deleteRecursively(file);
        int i2 = WhenMappings.$EnumSwitchMapping$0[zipFromInternalStorage$default.ordinal()];
        if (i2 == 1) {
            installableFragment = this.this$0;
            i = R$string.export_success;
        } else {
            if (i2 != 2 && i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            installableFragment = this.this$0;
            i = R$string.export_failed;
        }
        String string2 = installableFragment.getString(i);
        Intrinsics.checkNotNull(string2);
        return string2;
    }
}
