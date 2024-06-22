package org.yuzu.yuzu_emu.fragments;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.FragmentActivity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.GameIconUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GamePropertiesFragment$onViewCreated$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ShortcutManager $shortcutManager;
    int label;
    final /* synthetic */ GamePropertiesFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.fragments.GamePropertiesFragment$onViewCreated$2$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ShortcutManager $shortcutManager;
        Object L$0;
        int label;
        final /* synthetic */ GamePropertiesFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(GamePropertiesFragment gamePropertiesFragment, ShortcutManager shortcutManager, Continuation continuation) {
            super(2, continuation);
            this.this$0 = gamePropertiesFragment;
            this.$shortcutManager = shortcutManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$shortcutManager, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended;
            GamePropertiesFragmentArgs args;
            GamePropertiesFragmentArgs args2;
            GamePropertiesFragmentArgs args3;
            ShortcutInfo.Builder builder;
            GamePropertiesFragmentArgs args4;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context requireContext = this.this$0.requireContext();
                args = this.this$0.getArgs();
                ShortcutInfo.Builder builder2 = new ShortcutInfo.Builder(requireContext, args.getGame().getTitle());
                args2 = this.this$0.getArgs();
                ShortcutInfo.Builder shortLabel = builder2.setShortLabel(args2.getGame().getTitle());
                GameIconUtils gameIconUtils = GameIconUtils.INSTANCE;
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                args3 = this.this$0.getArgs();
                Game game = args3.getGame();
                this.L$0 = shortLabel;
                this.label = 1;
                Object shortcutIcon = gameIconUtils.getShortcutIcon(requireActivity, game, this);
                if (shortcutIcon == coroutine_suspended) {
                    return coroutine_suspended;
                }
                builder = shortLabel;
                obj = shortcutIcon;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                builder = (ShortcutInfo.Builder) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            ShortcutInfo.Builder icon = builder.setIcon(((IconCompat) obj).toIcon(this.this$0.requireContext()));
            args4 = this.this$0.getArgs();
            ShortcutInfo build = icon.setIntent(args4.getGame().getLaunchIntent()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            this.$shortcutManager.requestPinShortcut(build, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GamePropertiesFragment$onViewCreated$2$1(GamePropertiesFragment gamePropertiesFragment, ShortcutManager shortcutManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gamePropertiesFragment;
        this.$shortcutManager = shortcutManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GamePropertiesFragment$onViewCreated$2$1(this.this$0, this.$shortcutManager, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GamePropertiesFragment$onViewCreated$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$shortcutManager, null);
            this.label = 1;
            if (BuildersKt.withContext(io, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
