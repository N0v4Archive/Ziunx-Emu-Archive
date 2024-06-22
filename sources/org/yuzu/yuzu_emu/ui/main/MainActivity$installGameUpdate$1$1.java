package org.yuzu.yuzu_emu.ui.main;

import android.net.Uri;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.model.HomeViewModel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MainActivity$installGameUpdate$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ List $documents;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$installGameUpdate$1$1(List list, MainActivity mainActivity, Continuation continuation) {
        super(3, continuation);
        this.$documents = list;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Function2 function2, Function1 function1, Continuation continuation) {
        return new MainActivity$installGameUpdate$1$1(this.$documents, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        MessageDialogFragment newInstance;
        HomeViewModel homeViewModel;
        AddonViewModel addonViewModel;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Iterator it = this.$documents.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            Uri uri = (Uri) it.next();
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            addonViewModel = this.this$0.getAddonViewModel();
            Game game = addonViewModel.getGame();
            Intrinsics.checkNotNull(game);
            String programId = game.getProgramId();
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
            if (!nativeLibrary.doesUpdateMatchProgram(programId, uri2)) {
                z = false;
                break;
            }
        }
        if (z) {
            homeViewModel = this.this$0.getHomeViewModel();
            homeViewModel.setContentToInstall(this.$documents);
            return Unit.INSTANCE;
        }
        MessageDialogFragment.Companion companion = MessageDialogFragment.Companion;
        final MainActivity mainActivity = this.this$0;
        int i = R$string.content_install_notice;
        int i2 = R$string.content_install_notice_description;
        final List list = this.$documents;
        newInstance = companion.newInstance((r31 & 1) != 0 ? null : mainActivity, (r31 & 2) != 0 ? 0 : i, (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : i2, (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$installGameUpdate$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m195invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m195invoke() {
                HomeViewModel homeViewModel2;
                homeViewModel2 = MainActivity.this.getHomeViewModel();
                homeViewModel2.setContentToInstall(list);
            }
        }, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? new Function0() { // from class: org.yuzu.yuzu_emu.ui.main.MainActivity$installGameUpdate$1$1.2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m196invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m196invoke() {
            }
        } : null);
        return newInstance;
    }
}
