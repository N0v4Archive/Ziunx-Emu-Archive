package org.yuzu.yuzu_emu.utils;

import android.widget.ImageView;
import coil.ComponentRegistry;
import coil.ImageLoader;
import coil.memory.MemoryCache;
import coil.request.ImageRequest;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.GameIconFetcher;

/* loaded from: classes.dex */
public final class GameIconUtils {
    public static final GameIconUtils INSTANCE = new GameIconUtils();
    private static final ImageLoader imageLoader;

    static {
        ImageLoader.Builder builder = new ImageLoader.Builder(YuzuApplication.Companion.getAppContext());
        ComponentRegistry.Builder builder2 = new ComponentRegistry.Builder();
        builder2.add(new GameIconKeyer(), Game.class);
        builder2.add(new GameIconFetcher.Factory(), Game.class);
        imageLoader = builder.components(builder2.build()).memoryCache(new Function0() { // from class: org.yuzu.yuzu_emu.utils.GameIconUtils$imageLoader$2
            @Override // kotlin.jvm.functions.Function0
            public final MemoryCache invoke() {
                return new MemoryCache.Builder(YuzuApplication.Companion.getAppContext()).maxSizePercent(0.25d).build();
            }
        }).build();
    }

    private GameIconUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getGameIcon(androidx.lifecycle.LifecycleOwner r7, org.yuzu.yuzu_emu.model.Game r8, kotlin.coroutines.Continuation r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof org.yuzu.yuzu_emu.utils.GameIconUtils$getGameIcon$1
            if (r0 == 0) goto L13
            r0 = r9
            org.yuzu.yuzu_emu.utils.GameIconUtils$getGameIcon$1 r0 = (org.yuzu.yuzu_emu.utils.GameIconUtils$getGameIcon$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.yuzu.yuzu_emu.utils.GameIconUtils$getGameIcon$1 r0 = new org.yuzu.yuzu_emu.utils.GameIconUtils$getGameIcon$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5c
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            coil.request.ImageRequest$Builder r6 = new coil.request.ImageRequest$Builder
            org.yuzu.yuzu_emu.YuzuApplication$Companion r1 = org.yuzu.yuzu_emu.YuzuApplication.Companion
            android.content.Context r1 = r1.getAppContext()
            r6.<init>(r1)
            coil.request.ImageRequest$Builder r6 = r6.data(r8)
            coil.request.ImageRequest$Builder r6 = r6.lifecycle(r7)
            int r7 = org.yuzu.yuzu_emu.R$drawable.default_icon
            coil.request.ImageRequest$Builder r6 = r6.error(r7)
            coil.request.ImageRequest r6 = r6.build()
            coil.ImageLoader r7 = org.yuzu.yuzu_emu.utils.GameIconUtils.imageLoader
            r0.label = r2
            java.lang.Object r6 = r7.execute(r6, r0)
            if (r6 != r9) goto L5c
            return r9
        L5c:
            coil.request.ImageResult r6 = (coil.request.ImageResult) r6
            android.graphics.drawable.Drawable r0 = r6.getDrawable()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1 = 0
            r2 = 0
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            r4 = 3
            r5 = 0
            android.graphics.Bitmap r6 = androidx.core.graphics.drawable.DrawableKt.toBitmap$default(r0, r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.utils.GameIconUtils.getGameIcon(androidx.lifecycle.LifecycleOwner, org.yuzu.yuzu_emu.model.Game, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getShortcutIcon(androidx.lifecycle.LifecycleOwner r7, org.yuzu.yuzu_emu.model.Game r8, kotlin.coroutines.Continuation r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof org.yuzu.yuzu_emu.utils.GameIconUtils$getShortcutIcon$1
            if (r0 == 0) goto L13
            r0 = r9
            org.yuzu.yuzu_emu.utils.GameIconUtils$getShortcutIcon$1 r0 = (org.yuzu.yuzu_emu.utils.GameIconUtils$getShortcutIcon$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.yuzu.yuzu_emu.utils.GameIconUtils$getShortcutIcon$1 r0 = new org.yuzu.yuzu_emu.utils.GameIconUtils$getShortcutIcon$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            android.graphics.drawable.LayerDrawable r7 = (android.graphics.drawable.LayerDrawable) r7
            java.lang.Object r8 = r0.L$0
            android.graphics.drawable.LayerDrawable r8 = (android.graphics.drawable.LayerDrawable) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6b
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            org.yuzu.yuzu_emu.YuzuApplication$Companion r9 = org.yuzu.yuzu_emu.YuzuApplication.Companion
            android.content.Context r9 = r9.getAppContext()
            android.content.res.Resources r9 = r9.getResources()
            int r2 = org.yuzu.yuzu_emu.R$drawable.shortcut
            r4 = 0
            android.graphics.drawable.Drawable r9 = androidx.core.content.res.ResourcesCompat.getDrawable(r9, r2, r4)
            java.lang.String r2 = "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r2)
            android.graphics.drawable.LayerDrawable r9 = (android.graphics.drawable.LayerDrawable) r9
            int r2 = org.yuzu.yuzu_emu.R$id.shortcut_foreground
            r0.L$0 = r9
            r0.L$1 = r9
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r6 = r6.getGameIcon(r7, r8, r0)
            if (r6 != r1) goto L67
            return r1
        L67:
            r7 = r9
            r8 = r7
            r9 = r6
            r6 = r2
        L6b:
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            org.yuzu.yuzu_emu.YuzuApplication$Companion r0 = org.yuzu.yuzu_emu.YuzuApplication.Companion
            android.content.Context r1 = r0.getAppContext()
            android.content.res.Resources r1 = r1.getResources()
            java.lang.String r2 = "getResources(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            r2.<init>(r1, r9)
            r7.setDrawableByLayerId(r6, r2)
            android.content.Context r6 = r0.getAppContext()
            android.content.res.Resources r6 = r6.getResources()
            int r7 = org.yuzu.yuzu_emu.R$dimen.icon_inset
            int r5 = r6.getDimensionPixelSize(r7)
            r1 = 1
            r0 = r8
            r2 = r5
            r3 = r5
            r4 = r5
            r0.setLayerInset(r1, r2, r3, r4, r5)
            r1 = 0
            r2 = 0
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            r4 = 3
            r5 = 0
            android.graphics.Bitmap r6 = androidx.core.graphics.drawable.DrawableKt.toBitmap$default(r0, r1, r2, r3, r4, r5)
            androidx.core.graphics.drawable.IconCompat r6 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r6)
            java.lang.String r7 = "createWithAdaptiveBitmap(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.utils.GameIconUtils.getShortcutIcon(androidx.lifecycle.LifecycleOwner, org.yuzu.yuzu_emu.model.Game, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void loadGameIcon(Game game, ImageView imageView) {
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        imageLoader.enqueue(new ImageRequest.Builder(YuzuApplication.Companion.getAppContext()).data(game).target(imageView).error(R$drawable.default_icon).build());
    }
}
