package org.yuzu.yuzu_emu.utils;

import android.content.SharedPreferences;
import android.net.Uri;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.Json;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.model.GameDir;
import org.yuzu.yuzu_emu.model.MinimalDocumentFile;

/* loaded from: classes.dex */
public final class GameHelper {
    public static final GameHelper INSTANCE = new GameHelper();
    private static SharedPreferences preferences;

    private GameHelper() {
    }

    private final void addGamesRecursive(List list, MinimalDocumentFile[] minimalDocumentFileArr, int i) {
        Game game;
        if (i <= 0) {
            return;
        }
        for (MinimalDocumentFile minimalDocumentFile : minimalDocumentFileArr) {
            if (minimalDocumentFile.isDirectory()) {
                INSTANCE.addGamesRecursive(list, FileUtil.INSTANCE.listFiles(minimalDocumentFile.getUri()), i - 1);
            } else if (Game.Companion.getExtensions().contains(FileUtil.INSTANCE.getExtension(minimalDocumentFile.getUri())) && (game = INSTANCE.getGame(minimalDocumentFile.getUri(), true)) != null) {
                list.add(game);
            }
        }
    }

    public final Game getGame(Uri uri, boolean z) {
        int lastIndexOf$default;
        Intrinsics.checkNotNullParameter(uri, "uri");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        GameMetadata gameMetadata = GameMetadata.INSTANCE;
        SharedPreferences sharedPreferences = null;
        if (!gameMetadata.getIsValid(uri2)) {
            return null;
        }
        NativeLibrary.INSTANCE.addFileToFilesystemProvider(uri2);
        String title = gameMetadata.getTitle(uri2);
        String filename = title.length() == 0 ? FileUtil.INSTANCE.getFilename(uri) : title;
        String programId = gameMetadata.getProgramId(uri2);
        if (programId.length() == 0) {
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) filename, ".", 0, false, 6, (Object) null);
            programId = filename.substring(0, lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(programId, "substring(...)");
        }
        Game game = new Game(filename, uri2, programId, gameMetadata.getDeveloper(uri2), gameMetadata.getVersion(uri2, false), gameMetadata.getIsHomebrew(uri2));
        if (z) {
            SharedPreferences sharedPreferences2 = preferences;
            if (sharedPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
                sharedPreferences2 = null;
            }
            if (sharedPreferences2.getLong(game.getKeyAddedToLibraryTime(), 0L) == 0) {
                SharedPreferences sharedPreferences3 = preferences;
                if (sharedPreferences3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("preferences");
                } else {
                    sharedPreferences = sharedPreferences3;
                }
                sharedPreferences.edit().putLong(game.getKeyAddedToLibraryTime(), System.currentTimeMillis()).apply();
            }
        }
        return game;
    }

    public final List getGames() {
        List list;
        ArrayList<Game> arrayList = new ArrayList();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext());
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        preferences = defaultSharedPreferences;
        ArrayList arrayList2 = new ArrayList();
        SharedPreferences sharedPreferences = preferences;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString("game_path", "");
        String str = string != null ? string : "";
        if (str.length() > 0) {
            arrayList2.add(new GameDir(str, true));
            SharedPreferences sharedPreferences3 = preferences;
            if (sharedPreferences3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
                sharedPreferences3 = null;
            }
            sharedPreferences3.edit().remove("game_path").apply();
        }
        CollectionsKt__MutableCollectionsKt.addAll(arrayList2, NativeConfig.INSTANCE.getGameDirs());
        NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
        nativeLibrary.reloadKeys();
        GameMetadata.INSTANCE.resetMetadata();
        nativeLibrary.clearFilesystemProvider();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        for (Object obj : arrayList2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            GameDir gameDir = (GameDir) obj;
            Uri parse = Uri.parse(gameDir.getUriString());
            FileUtil fileUtil = FileUtil.INSTANCE;
            Intrinsics.checkNotNull(parse);
            if (fileUtil.isTreeUriValid(parse)) {
                INSTANCE.addGamesRecursive(arrayList, fileUtil.listFiles(parse), gameDir.getDeepScan() ? 3 : 1);
            } else {
                arrayList3.add(Integer.valueOf(i));
            }
            i = i2;
        }
        if (!arrayList3.isEmpty()) {
            Iterator it = arrayList3.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                arrayList2.remove(((Number) it.next()).intValue() - i3);
                i3++;
            }
        }
        NativeConfig.INSTANCE.setGameDirs((GameDir[]) arrayList2.toArray(new GameDir[0]));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Game game : arrayList) {
            Json.Default r5 = Json.Default;
            r5.getSerializersModule();
            linkedHashSet.add(r5.encodeToString(Game.Companion.serializer(), game));
        }
        SharedPreferences sharedPreferences4 = preferences;
        if (sharedPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            sharedPreferences2 = sharedPreferences4;
        }
        sharedPreferences2.edit().remove("Games").putStringSet("Games", linkedHashSet).apply();
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }
}
