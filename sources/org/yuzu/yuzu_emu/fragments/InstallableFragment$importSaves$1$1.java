package org.yuzu.yuzu_emu.fragments;

import android.content.res.Resources;
import android.net.Uri;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import java.io.File;
import java.math.BigInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$plurals;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class InstallableFragment$importSaves$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ File $cacheSaveDir;
    final /* synthetic */ Uri $result;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ InstallableFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.fragments.InstallableFragment$importSaves$1$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref$IntRef $failedImports;
        final /* synthetic */ Ref$IntRef $successfulImports;
        int label;
        final /* synthetic */ InstallableFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref$IntRef ref$IntRef, InstallableFragment installableFragment, Ref$IntRef ref$IntRef2, Continuation continuation) {
            super(2, continuation);
            this.$successfulImports = ref$IntRef;
            this.this$0 = installableFragment;
            this.$failedImports = ref$IntRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$successfulImports, this.this$0, this.$failedImports, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String quantityString;
            String str;
            MessageDialogFragment.Companion companion;
            FragmentActivity requireActivity;
            int i;
            String str2;
            int i2;
            int i3;
            boolean z;
            int i4;
            String str3;
            Function0 function0;
            boolean z2;
            int i5;
            String str4;
            Function0 function02;
            int i6;
            MessageDialogFragment newInstance;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$successfulImports.element == 0) {
                companion = MessageDialogFragment.Companion;
                requireActivity = this.this$0.requireActivity();
                i = R$string.save_file_invalid_zip_structure;
                str2 = null;
                i2 = R$string.save_file_invalid_zip_structure_description;
                str = null;
                i3 = 0;
                z = false;
                i4 = 0;
                str3 = null;
                function0 = null;
                z2 = false;
                i5 = 0;
                str4 = null;
                function02 = null;
                i6 = 16372;
            } else {
                if (this.$failedImports.element > 0) {
                    Resources resources = this.this$0.requireContext().getResources();
                    int i7 = R$plurals.saves_import_success;
                    int i8 = this.$successfulImports.element;
                    String quantityString2 = resources.getQuantityString(i7, i8, Boxing.boxInt(i8));
                    Resources resources2 = this.this$0.requireContext().getResources();
                    int i9 = R$plurals.saves_import_failed;
                    int i10 = this.$failedImports.element;
                    quantityString = "\n                            " + quantityString2 + "\n                            " + resources2.getQuantityString(i9, i10, Boxing.boxInt(i10)) + "\n                            ";
                } else {
                    Resources resources3 = this.this$0.requireContext().getResources();
                    int i11 = R$plurals.saves_import_success;
                    int i12 = this.$successfulImports.element;
                    quantityString = resources3.getQuantityString(i11, i12, Boxing.boxInt(i12));
                    Intrinsics.checkNotNull(quantityString);
                }
                str = quantityString;
                companion = MessageDialogFragment.Companion;
                requireActivity = this.this$0.requireActivity();
                i = R$string.import_complete;
                str2 = null;
                i2 = 0;
                i3 = 0;
                z = false;
                i4 = 0;
                str3 = null;
                function0 = null;
                z2 = false;
                i5 = 0;
                str4 = null;
                function02 = null;
                i6 = 16364;
            }
            newInstance = companion.newInstance((r31 & 1) != 0 ? null : requireActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : str2, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : str, (r31 & 32) != 0 ? 0 : i3, (r31 & 64) != 0 ? true : z, (r31 & 128) != 0 ? 0 : i4, (r31 & 256) != 0 ? "" : str3, (r31 & 512) != 0 ? null : function0, (r31 & 1024) != 0 ? false : z2, (r31 & 2048) == 0 ? i5 : 0, (r31 & 4096) == 0 ? str4 : "", (r31 & 8192) == 0 ? function02 : null);
            newInstance.show(this.this$0.getParentFragmentManager(), "MessageDialogFragment");
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstallableFragment$importSaves$1$1(Uri uri, File file, InstallableFragment installableFragment, Continuation continuation) {
        super(3, continuation);
        this.$result = uri;
        this.$cacheSaveDir = file;
        this.this$0 = installableFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        InstallableFragment$importSaves$1$1 installableFragment$importSaves$1$1 = new InstallableFragment$importSaves$1$1(this.$result, this.$cacheSaveDir, this.this$0, continuation);
        installableFragment$importSaves$1$1.L$0 = function2;
        return installableFragment$importSaves$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        boolean deleteRecursively;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function2 function2 = (Function2) this.L$0;
                FileUtil fileUtil = FileUtil.INSTANCE;
                String uri = this.$result.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                fileUtil.unzipToInternalStorage(uri, this.$cacheSaveDir, function2);
                File[] listFiles = this.$cacheSaveDir.listFiles();
                Ref$IntRef ref$IntRef = new Ref$IntRef();
                Ref$IntRef ref$IntRef2 = new Ref$IntRef();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isDirectory()) {
                            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
                            String bigInteger = new BigInteger(file.getName(), 16).toString();
                            Intrinsics.checkNotNullExpressionValue(bigInteger, "toString(...)");
                            String savePath = nativeLibrary.getSavePath(bigInteger);
                            if (savePath.length() == 0) {
                                ref$IntRef2.element++;
                            } else {
                                File file2 = new File(DirectoryInitialization.INSTANCE.getUserDirectory() + "/nand" + savePath);
                                FilesKt__UtilsKt.deleteRecursively(file2);
                                file2.mkdir();
                                Intrinsics.checkNotNull(file);
                                FilesKt__UtilsKt.copyRecursively$default(file, file2, true, null, 4, null);
                                ref$IntRef.element++;
                            }
                        }
                    }
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(ref$IntRef, this.this$0, ref$IntRef2, null);
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            deleteRecursively = FilesKt__UtilsKt.deleteRecursively(this.$cacheSaveDir);
            return Boxing.boxBoolean(deleteRecursively);
        } catch (Exception unused) {
            Toast.makeText(YuzuApplication.Companion.getAppContext(), this.this$0.getString(R$string.fatal_error), 1).show();
            return Unit.INSTANCE;
        }
    }
}
