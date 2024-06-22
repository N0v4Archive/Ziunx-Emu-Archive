package org.yuzu.yuzu_emu.ui.main;

import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.StringsKt__StringsKt;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.DriverViewModel;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MainActivity$importUserData$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Uri $result;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$importUserData$1$1(MainActivity mainActivity, Uri uri, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mainActivity;
        this.$result = uri;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        MainActivity$importUserData$1$1 mainActivity$importUserData$1$1 = new MainActivity$importUserData$1$1(this.this$0, this.$result, continuation);
        mainActivity$importUserData$1$1.L$0 = function2;
        return mainActivity$importUserData$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MessageDialogFragment newInstance;
        GamesViewModel gamesViewModel;
        DriverViewModel driverViewModel;
        MessageDialogFragment newInstance2;
        CharSequence trim;
        String obj2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(this.this$0.getContentResolver().openInputStream(this.$result)));
        Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            do {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    Intrinsics.checkNotNull(nextEntry);
                    ref$ObjectRef.element = nextEntry;
                } else {
                    nextEntry = null;
                }
                if (nextEntry == null) {
                    break;
                }
                Object obj3 = ref$ObjectRef.element;
                Intrinsics.checkNotNull(obj3);
                String name = ((ZipEntry) obj3).getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                trim = StringsKt__StringsKt.trim(name);
                obj2 = trim.toString();
                if (Intrinsics.areEqual(obj2, "/config/config.ini")) {
                    break;
                }
            } while (!Intrinsics.areEqual(obj2, "config/config.ini"));
            ref$BooleanRef.element = true;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipInputStream, null);
            if (!ref$BooleanRef.element) {
                newInstance2 = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this.this$0, (r31 & 2) != 0 ? 0 : R$string.invalid_yuzu_backup, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.user_data_import_failed_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                return newInstance2;
            }
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            nativeConfig.unloadGlobalConfig();
            DirectoryInitialization directoryInitialization = DirectoryInitialization.INSTANCE;
            String userDirectory = directoryInitialization.getUserDirectory();
            Intrinsics.checkNotNull(userDirectory);
            FilesKt__UtilsKt.deleteRecursively(new File(userDirectory));
            try {
                FileUtil fileUtil = FileUtil.INSTANCE;
                String uri = this.$result.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                String userDirectory2 = directoryInitialization.getUserDirectory();
                Intrinsics.checkNotNull(userDirectory2);
                fileUtil.unzipToInternalStorage(uri, new File(userDirectory2), function2);
                NativeLibrary.INSTANCE.initializeSystem(true);
                nativeConfig.initializeGlobalConfig();
                gamesViewModel = this.this$0.getGamesViewModel();
                GamesViewModel.reloadGames$default(gamesViewModel, false, false, 2, null);
                driverViewModel = this.this$0.getDriverViewModel();
                driverViewModel.reloadDriverData();
                String string = this.this$0.getString(R$string.user_data_import_success);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            } catch (Exception unused) {
                newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this.this$0, (r31 & 2) != 0 ? 0 : R$string.import_failed, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.user_data_import_failed_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                return newInstance;
            }
        } finally {
        }
    }
}
