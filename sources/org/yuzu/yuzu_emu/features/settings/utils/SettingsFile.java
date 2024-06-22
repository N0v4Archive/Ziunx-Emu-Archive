package org.yuzu.yuzu_emu.features.settings.utils;

import android.net.Uri;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class SettingsFile {
    public static final SettingsFile INSTANCE = new SettingsFile();

    private SettingsFile() {
    }

    public final File getCustomSettingsFile(Game game) {
        Intrinsics.checkNotNullParameter(game, "game");
        return new File(DirectoryInitialization.INSTANCE.getUserDirectory() + "/config/custom/" + game.getSettingsName() + ".ini");
    }

    public final File getSettingsFile(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new File(DirectoryInitialization.INSTANCE.getUserDirectory() + "/config/" + fileName);
    }

    public final void loadCustomConfig(Game game) {
        Intrinsics.checkNotNullParameter(game, "game");
        FileUtil fileUtil = FileUtil.INSTANCE;
        Uri parse = Uri.parse(game.getPath());
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        NativeConfig.INSTANCE.initializePerGameConfig(game.getProgramId(), fileUtil.getFilename(parse));
    }
}
