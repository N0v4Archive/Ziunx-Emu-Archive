package org.yuzu.yuzu_emu.utils;

import coil.key.Keyer;
import coil.request.Options;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class GameIconKeyer implements Keyer {
    @Override // coil.key.Keyer
    public String key(Game data, Options options) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(options, "options");
        return data.getPath();
    }
}
