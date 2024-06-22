package org.yuzu.yuzu_emu.ui.main;

import android.net.Uri;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.text.StringsKt__StringsKt;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.InstallResult;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MainActivity$installContent$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ List $documents;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InstallResult.values().length];
            try {
                iArr[InstallResult.Success.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InstallResult.Overwrite.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InstallResult.BaseInstallAttempted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[InstallResult.Failure.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$installContent$1(List list, MainActivity mainActivity, Continuation continuation) {
        super(3, continuation);
        this.$documents = list;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        MainActivity$installContent$1 mainActivity$installContent$1 = new MainActivity$installContent$1(this.$documents, this.this$0, continuation);
        mainActivity$installContent$1.L$0 = function2;
        mainActivity$installContent$1.L$1 = function1;
        return mainActivity$installContent$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AddonViewModel addonViewModel;
        MessageDialogFragment.Companion companion;
        MainActivity mainActivity;
        int i;
        String str;
        int i2;
        CharSequence trim;
        String obj2;
        int i3;
        boolean z;
        int i4;
        String str2;
        Function0 function0;
        boolean z2;
        int i5;
        String str3;
        Function0 function02;
        int i6;
        MessageDialogFragment newInstance;
        CharSequence trim2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = (Function2) this.L$0;
        Function1 function1 = (Function1) this.L$1;
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        Ref$IntRef ref$IntRef2 = new Ref$IntRef();
        Ref$IntRef ref$IntRef3 = new Ref$IntRef();
        Ref$IntRef ref$IntRef4 = new Ref$IntRef();
        for (Uri uri : this.$documents) {
            function1.invoke(FileUtil.INSTANCE.getFilename(uri));
            InstallResult.Companion companion2 = InstallResult.Companion;
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
            int i7 = WhenMappings.$EnumSwitchMapping$0[companion2.from(nativeLibrary.installFileToNand(uri2, function2)).ordinal()];
            if (i7 == 1) {
                ref$IntRef.element++;
            } else if (i7 == 2) {
                ref$IntRef2.element++;
            } else if (i7 == 3) {
                ref$IntRef3.element++;
            } else if (i7 == 4) {
                ref$IntRef4.element++;
            }
        }
        addonViewModel = this.this$0.getAddonViewModel();
        addonViewModel.refreshAddons();
        String property = System.getProperty("line.separator");
        if (property == null) {
            property = "\n";
        }
        StringBuilder sb = new StringBuilder();
        int i8 = ref$IntRef.element;
        if (i8 > 0) {
            sb.append(this.this$0.getString(R$string.install_game_content_success_install, Boxing.boxInt(i8)));
            sb.append(property);
        }
        int i9 = ref$IntRef2.element;
        if (i9 > 0) {
            sb.append(this.this$0.getString(R$string.install_game_content_success_overwrite, Boxing.boxInt(i9)));
            sb.append(property);
        }
        int i10 = ref$IntRef3.element + ref$IntRef4.element;
        if (i10 > 0) {
            sb.append(property);
            sb.append(this.this$0.getString(R$string.install_game_content_failed_count, Boxing.boxInt(i10)));
            sb.append(property);
            if (ref$IntRef3.element > 0) {
                sb.append(property);
                sb.append(this.this$0.getString(R$string.install_game_content_failure_base));
                sb.append(property);
            }
            if (ref$IntRef4.element > 0) {
                sb.append(this.this$0.getString(R$string.install_game_content_failure_description));
                sb.append(property);
            }
            companion = MessageDialogFragment.Companion;
            mainActivity = this.this$0;
            i = R$string.install_game_content_failure;
            str = null;
            i2 = 0;
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            trim2 = StringsKt__StringsKt.trim(sb2);
            obj2 = trim2.toString();
            i3 = R$string.install_game_content_help_link;
            z = false;
            i4 = 0;
            str2 = null;
            function0 = null;
            z2 = false;
            i5 = 0;
            str3 = null;
            function02 = null;
            i6 = 16332;
        } else {
            companion = MessageDialogFragment.Companion;
            mainActivity = this.this$0;
            i = R$string.install_game_content_success;
            str = null;
            i2 = 0;
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
            trim = StringsKt__StringsKt.trim(sb3);
            obj2 = trim.toString();
            i3 = 0;
            z = false;
            i4 = 0;
            str2 = null;
            function0 = null;
            z2 = false;
            i5 = 0;
            str3 = null;
            function02 = null;
            i6 = 16364;
        }
        newInstance = companion.newInstance((r31 & 1) != 0 ? null : mainActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : str, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : obj2, (r31 & 32) != 0 ? 0 : i3, (r31 & 64) != 0 ? true : z, (r31 & 128) != 0 ? 0 : i4, (r31 & 256) != 0 ? "" : str2, (r31 & 512) != 0 ? null : function0, (r31 & 1024) != 0 ? false : z2, (r31 & 2048) == 0 ? i5 : 0, (r31 & 4096) == 0 ? str3 : "", (r31 & 8192) == 0 ? function02 : null);
        return newInstance;
    }
}
