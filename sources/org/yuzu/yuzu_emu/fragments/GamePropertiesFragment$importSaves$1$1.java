package org.yuzu.yuzu_emu.fragments;

import android.net.Uri;
import android.widget.Toast;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GamePropertiesFragment$importSaves$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ File $cacheSaveDir;
    final /* synthetic */ Uri $result;
    final /* synthetic */ File $savesFolder;
    int label;
    final /* synthetic */ GamePropertiesFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$importSaves$1$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref$ObjectRef $savesFolderFile;
        int label;
        final /* synthetic */ GamePropertiesFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref$ObjectRef ref$ObjectRef, GamePropertiesFragment gamePropertiesFragment, Continuation continuation) {
            super(2, continuation);
            this.$savesFolderFile = ref$ObjectRef;
            this.this$0 = gamePropertiesFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$savesFolderFile, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            HomeViewModel homeViewModel;
            MessageDialogFragment newInstance;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$savesFolderFile.element == null) {
                newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this.this$0.requireActivity(), (r31 & 2) != 0 ? 0 : R$string.save_file_invalid_zip_structure, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : R$string.save_file_invalid_zip_structure_description, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                newInstance.show(this.this$0.getParentFragmentManager(), "MessageDialogFragment");
            } else {
                Toast.makeText(YuzuApplication.Companion.getAppContext(), this.this$0.getString(R$string.save_file_imported_success), 1).show();
                homeViewModel = this.this$0.getHomeViewModel();
                homeViewModel.reloadPropertiesList(true);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GamePropertiesFragment$importSaves$1$1(Uri uri, File file, GamePropertiesFragment gamePropertiesFragment, File file2, Continuation continuation) {
        super(3, continuation);
        this.$result = uri;
        this.$cacheSaveDir = file;
        this.this$0 = gamePropertiesFragment;
        this.$savesFolder = file2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        return new GamePropertiesFragment$importSaves$1$1(this.$result, this.$cacheSaveDir, this.this$0, this.$savesFolder, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        GamePropertiesFragmentArgs args;
        boolean deleteRecursively;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FileUtil fileUtil = FileUtil.INSTANCE;
                String uri = this.$result.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                FileUtil.unzipToInternalStorage$default(fileUtil, uri, this.$cacheSaveDir, null, 4, null);
                File[] listFiles = this.$cacheSaveDir.listFiles();
                Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                if (listFiles != null) {
                    args = this.this$0.getArgs();
                    String programIdHex = args.getGame().getProgramIdHex();
                    int length = listFiles.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        File file = listFiles[i2];
                        if (file.isDirectory() && Intrinsics.areEqual(file.getName(), programIdHex)) {
                            ref$ObjectRef.element = file;
                            break;
                        }
                        i2++;
                    }
                }
                if (ref$ObjectRef.element != null) {
                    FilesKt__UtilsKt.deleteRecursively(this.$savesFolder);
                    this.$savesFolder.mkdir();
                    FilesKt__UtilsKt.copyRecursively$default((File) ref$ObjectRef.element, this.$savesFolder, false, null, 6, null);
                    FilesKt__UtilsKt.deleteRecursively((File) ref$ObjectRef.element);
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(ref$ObjectRef, this.this$0, null);
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
