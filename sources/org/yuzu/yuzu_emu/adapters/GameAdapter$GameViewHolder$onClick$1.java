package org.yuzu.yuzu_emu.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
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
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.GameIconUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GameAdapter$GameViewHolder$onClick$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Game $game;
    int label;
    final /* synthetic */ GameAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.adapters.GameAdapter$GameViewHolder$onClick$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Game $game;
        Object L$0;
        int label;
        final /* synthetic */ GameAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Game game, GameAdapter gameAdapter, Continuation continuation) {
            super(2, continuation);
            this.$game = game;
            this.this$0 = gameAdapter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$game, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended;
            ShortcutInfoCompat.Builder builder;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ShortcutInfoCompat.Builder shortLabel = new ShortcutInfoCompat.Builder(YuzuApplication.Companion.getAppContext(), this.$game.getPath()).setShortLabel(this.$game.getTitle());
                GameIconUtils gameIconUtils = GameIconUtils.INSTANCE;
                AppCompatActivity appCompatActivity = this.this$0.activity;
                Game game = this.$game;
                this.L$0 = shortLabel;
                this.label = 1;
                Object shortcutIcon = gameIconUtils.getShortcutIcon(appCompatActivity, game, this);
                if (shortcutIcon == coroutine_suspended) {
                    return coroutine_suspended;
                }
                builder = shortLabel;
                obj = shortcutIcon;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                builder = (ShortcutInfoCompat.Builder) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            ShortcutInfoCompat build = builder.setIcon((IconCompat) obj).setIntent(this.$game.getLaunchIntent()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            ShortcutManagerCompat.pushDynamicShortcut(YuzuApplication.Companion.getAppContext(), build);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GameAdapter$GameViewHolder$onClick$1(Game game, GameAdapter gameAdapter, Continuation continuation) {
        super(2, continuation);
        this.$game = game;
        this.this$0 = gameAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GameAdapter$GameViewHolder$onClick$1(this.$game, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GameAdapter$GameViewHolder$onClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$game, this.this$0, null);
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
