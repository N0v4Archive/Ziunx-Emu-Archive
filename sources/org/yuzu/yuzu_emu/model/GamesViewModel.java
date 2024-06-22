package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class GamesViewModel extends ViewModel {
    private final MutableStateFlow _folders;
    private final MutableStateFlow _games;
    private final MutableStateFlow _isReloading;
    private final MutableStateFlow _searchFocused;
    private final MutableStateFlow _searchedGames;
    private final MutableStateFlow _shouldScrollToTop;
    private final MutableStateFlow _shouldSwapData;
    private final StateFlow folders;
    private final AtomicBoolean reloading;

    public GamesViewModel() {
        List emptyList;
        List emptyList2;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this._games = StateFlowKt.MutableStateFlow(emptyList);
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        this._searchedGames = StateFlowKt.MutableStateFlow(emptyList2);
        Boolean bool = Boolean.FALSE;
        this._isReloading = StateFlowKt.MutableStateFlow(bool);
        this.reloading = new AtomicBoolean(false);
        this._shouldSwapData = StateFlowKt.MutableStateFlow(bool);
        this._shouldScrollToTop = StateFlowKt.MutableStateFlow(bool);
        this._searchFocused = StateFlowKt.MutableStateFlow(bool);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(new ArrayList());
        this._folders = MutableStateFlow;
        this.folders = FlowKt.asStateFlow(MutableStateFlow);
        NativeLibrary.INSTANCE.reloadKeys();
        getGameDirs$default(this, false, 1, null);
        reloadGames(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getGameDirs(boolean z) {
        List mutableList;
        GameDir[] gameDirs = NativeConfig.INSTANCE.getGameDirs();
        MutableStateFlow mutableStateFlow = this._folders;
        mutableList = ArraysKt___ArraysKt.toMutableList(gameDirs);
        mutableStateFlow.setValue(mutableList);
        if (z) {
            reloadGames$default(this, true, false, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void getGameDirs$default(GamesViewModel gamesViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        gamesViewModel.getGameDirs(z);
    }

    public static /* synthetic */ void reloadGames$default(GamesViewModel gamesViewModel, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        gamesViewModel.reloadGames(z, z2);
    }

    public final Job addFolder(GameDir gameDir) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(gameDir, "gameDir");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$addFolder$1(gameDir, this, null), 3, null);
        return launch$default;
    }

    public final StateFlow getFolders() {
        return this.folders;
    }

    public final StateFlow getGames() {
        return this._games;
    }

    public final StateFlow getSearchFocused() {
        return this._searchFocused;
    }

    public final StateFlow getSearchedGames() {
        return this._searchedGames;
    }

    public final StateFlow getShouldScrollToTop() {
        return this._shouldScrollToTop;
    }

    public final StateFlow getShouldSwapData() {
        return this._shouldSwapData;
    }

    public final StateFlow isReloading() {
        return this._isReloading;
    }

    public final void onCloseGameFoldersFragment() {
        NativeConfig.INSTANCE.saveGlobalConfig();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$onCloseGameFoldersFragment$1(this, null), 3, null);
    }

    public final Job onOpenGameFoldersFragment() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$onOpenGameFoldersFragment$1(this, null), 3, null);
        return launch$default;
    }

    public final void reloadGames(boolean z, boolean z2) {
        if (this.reloading.get()) {
            return;
        }
        this.reloading.set(true);
        this._isReloading.setValue(Boolean.TRUE);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$reloadGames$1(z2, this, z, null), 3, null);
    }

    public final Job removeFolder(GameDir gameDir) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(gameDir, "gameDir");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$removeFolder$1(this, gameDir, null), 3, null);
        return launch$default;
    }

    public final void setGames(List games) {
        Comparator compareBy;
        List sortedWith;
        Intrinsics.checkNotNullParameter(games, "games");
        compareBy = ComparisonsKt__ComparisonsKt.compareBy(new Function1() { // from class: org.yuzu.yuzu_emu.model.GamesViewModel$setGames$sortedList$1
            @Override // kotlin.jvm.functions.Function1
            public final Comparable invoke(Game it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String title = it.getTitle();
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String lowerCase = title.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                return lowerCase;
            }
        }, new Function1() { // from class: org.yuzu.yuzu_emu.model.GamesViewModel$setGames$sortedList$2
            @Override // kotlin.jvm.functions.Function1
            public final Comparable invoke(Game it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getPath();
            }
        });
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(games, compareBy);
        this._games.setValue(sortedWith);
    }

    public final void setSearchFocused(boolean z) {
        this._searchFocused.setValue(Boolean.valueOf(z));
    }

    public final void setSearchedGames(List games) {
        Intrinsics.checkNotNullParameter(games, "games");
        this._searchedGames.setValue(games);
    }

    public final void setShouldScrollToTop(boolean z) {
        this._shouldScrollToTop.setValue(Boolean.valueOf(z));
    }

    public final void setShouldSwapData(boolean z) {
        this._shouldSwapData.setValue(Boolean.valueOf(z));
    }

    public final Job updateGameDirs() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new GamesViewModel$updateGameDirs$1(this, null), 3, null);
        return launch$default;
    }
}
