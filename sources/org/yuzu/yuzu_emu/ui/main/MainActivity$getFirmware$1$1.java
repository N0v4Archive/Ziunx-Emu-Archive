package org.yuzu.yuzu_emu.ui.main;

import android.net.Uri;
import java.io.File;
import java.io.FilenameFilter;
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
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MainActivity$getFirmware$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ File $cacheFirmwareDir;
    final /* synthetic */ FilenameFilter $filterNCA;
    final /* synthetic */ File $firmwarePath;
    final /* synthetic */ Uri $result;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$getFirmware$1$1(Uri uri, File file, FilenameFilter filenameFilter, MainActivity mainActivity, File file2, Continuation continuation) {
        super(3, continuation);
        this.$result = uri;
        this.$cacheFirmwareDir = file;
        this.$filterNCA = filenameFilter;
        this.this$0 = mainActivity;
        this.$firmwarePath = file2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        MainActivity$getFirmware$1$1 mainActivity$getFirmware$1$1 = new MainActivity$getFirmware$1$1(this.$result, this.$cacheFirmwareDir, this.$filterNCA, this.this$0, this.$firmwarePath, continuation);
        mainActivity$getFirmware$1$1.L$0 = function2;
        return mainActivity$getFirmware$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object string;
        HomeViewModel homeViewModel;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        try {
            try {
                FileUtil fileUtil = FileUtil.INSTANCE;
                String uri = this.$result.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                fileUtil.unzipToInternalStorage(uri, this.$cacheFirmwareDir, function2);
                String[] list = this.$cacheFirmwareDir.list();
                int length = list != null ? list.length : -1;
                String[] list2 = this.$cacheFirmwareDir.list(this.$filterNCA);
                if (length != (list2 != null ? list2.length : -2)) {
                    string = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this.this$0, (r31 & 2) != 0 ? 0 : R$string.firmware_installed_failure, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.firmware_installed_failure_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                } else {
                    FilesKt__UtilsKt.deleteRecursively(this.$firmwarePath);
                    FilesKt__UtilsKt.copyRecursively$default(this.$cacheFirmwareDir, this.$firmwarePath, true, null, 4, null);
                    NativeLibrary.INSTANCE.initializeSystem(true);
                    homeViewModel = this.this$0.getHomeViewModel();
                    homeViewModel.setCheckKeys(true);
                    string = this.this$0.getString(R$string.save_file_imported_success);
                    Intrinsics.checkNotNull(string);
                }
            } catch (Exception e) {
                Log.INSTANCE.error("[MainActivity] Firmware install failed - " + e.getMessage());
                string = this.this$0.getString(R$string.fatal_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            }
            return string;
        } finally {
            FilesKt__UtilsKt.deleteRecursively(this.$cacheFirmwareDir);
        }
    }
}
