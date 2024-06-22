package org.yuzu.yuzu_emu.model;

import android.content.SharedPreferences;
import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import androidx.preference.PreferenceManager;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.serialization.json.Json;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.utils.GameHelper;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GamesViewModel$reloadGames$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $directoriesChanged;
    final /* synthetic */ boolean $firstStartup;
    int label;
    final /* synthetic */ GamesViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.yuzu.yuzu_emu.model.GamesViewModel$reloadGames$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $directoriesChanged;
        final /* synthetic */ boolean $firstStartup;
        int label;
        final /* synthetic */ GamesViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, GamesViewModel gamesViewModel, boolean z2, Continuation continuation) {
            super(2, continuation);
            this.$firstStartup = z;
            this.this$0 = gamesViewModel;
            this.$directoriesChanged = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$firstStartup, this.this$0, this.$directoriesChanged, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AtomicBoolean atomicBoolean;
            MutableStateFlow mutableStateFlow;
            Set<String> emptySet;
            List list;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$firstStartup) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext());
                emptySet = SetsKt__SetsKt.emptySet();
                Set<String> stringSet = defaultSharedPreferences.getStringSet("Games", emptySet);
                Intrinsics.checkNotNull(stringSet);
                if (!stringSet.isEmpty()) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    for (String str : stringSet) {
                        try {
                            Json.Default r3 = Json.Default;
                            Intrinsics.checkNotNull(str);
                            r3.getSerializersModule();
                            Game game = (Game) r3.decodeFromString(Game.Companion.serializer(), str);
                            DocumentFile fromSingleUri = DocumentFile.fromSingleUri(YuzuApplication.Companion.getAppContext(), Uri.parse(game.getPath()));
                            if (Intrinsics.areEqual(fromSingleUri != null ? Boxing.boxBoolean(fromSingleUri.exists()) : null, Boxing.boxBoolean(true))) {
                                linkedHashSet.add(game);
                            }
                        } catch (Exception unused) {
                        }
                    }
                    GamesViewModel gamesViewModel = this.this$0;
                    list = CollectionsKt___CollectionsKt.toList(linkedHashSet);
                    gamesViewModel.setGames(list);
                }
            }
            this.this$0.setGames(GameHelper.INSTANCE.getGames());
            atomicBoolean = this.this$0.reloading;
            atomicBoolean.set(false);
            mutableStateFlow = this.this$0._isReloading;
            mutableStateFlow.setValue(Boxing.boxBoolean(false));
            if (this.$directoriesChanged) {
                this.this$0.setShouldSwapData(true);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GamesViewModel$reloadGames$1(boolean z, GamesViewModel gamesViewModel, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.$firstStartup = z;
        this.this$0 = gamesViewModel;
        this.$directoriesChanged = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GamesViewModel$reloadGames$1(this.$firstStartup, this.this$0, this.$directoriesChanged, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((GamesViewModel$reloadGames$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$firstStartup, this.this$0, this.$directoriesChanged, null);
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
