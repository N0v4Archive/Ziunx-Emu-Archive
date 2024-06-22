package org.yuzu.yuzu_emu.fragments;

import androidx.documentfile.provider.DocumentFile;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AddonsFragment$installAddon$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ MessageDialogFragment $errorMessage;
    final /* synthetic */ DocumentFile $externalAddonDirectory;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AddonsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonsFragment$installAddon$1$1(DocumentFile documentFile, AddonsFragment addonsFragment, MessageDialogFragment messageDialogFragment, Continuation continuation) {
        super(3, continuation);
        this.$externalAddonDirectory = documentFile;
        this.this$0 = addonsFragment;
        this.$errorMessage = messageDialogFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        AddonsFragment$installAddon$1$1 addonsFragment$installAddon$1$1 = new AddonsFragment$installAddon$1$1(this.$externalAddonDirectory, this.this$0, this.$errorMessage, continuation);
        addonsFragment$installAddon$1$1.L$0 = function2;
        return addonsFragment$installAddon$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AddonsFragmentArgs args;
        AddonViewModel addonViewModel;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        String name = this.$externalAddonDirectory.getName();
        args = this.this$0.getArgs();
        try {
            FileUtil.INSTANCE.copyFilesTo(this.$externalAddonDirectory, new File(args.getGame().getAddonDir() + name), function2);
            addonViewModel = this.this$0.getAddonViewModel();
            addonViewModel.refreshAddons();
            String string = this.this$0.getString(R$string.addon_installed_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        } catch (Exception unused) {
            return this.$errorMessage;
        }
    }
}
