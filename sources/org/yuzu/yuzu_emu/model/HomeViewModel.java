package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Pair;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public final class HomeViewModel extends ViewModel {
    private final MutableStateFlow _checkKeys;
    private final MutableStateFlow _contentToInstall;
    private final MutableStateFlow _gamesDirSelected;
    private final MutableStateFlow _navigationVisible;
    private final MutableStateFlow _openImportSaves;
    private final MutableStateFlow _reloadPropertiesList;
    private final MutableStateFlow _shouldPageForward;
    private final MutableStateFlow _statusBarShadeVisible;
    private final StateFlow checkKeys;
    private boolean navigatedToSetup;

    public HomeViewModel() {
        Boolean bool = Boolean.FALSE;
        this._navigationVisible = StateFlowKt.MutableStateFlow(new Pair(bool, bool));
        this._statusBarShadeVisible = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._shouldPageForward = StateFlowKt.MutableStateFlow(bool);
        this._gamesDirSelected = StateFlowKt.MutableStateFlow(bool);
        this._openImportSaves = StateFlowKt.MutableStateFlow(bool);
        this._contentToInstall = StateFlowKt.MutableStateFlow(null);
        this._reloadPropertiesList = StateFlowKt.MutableStateFlow(bool);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._checkKeys = MutableStateFlow;
        this.checkKeys = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final StateFlow getCheckKeys() {
        return this.checkKeys;
    }

    public final StateFlow getContentToInstall() {
        return FlowKt.asStateFlow(this._contentToInstall);
    }

    public final StateFlow getGamesDirSelected() {
        return FlowKt.asStateFlow(this._gamesDirSelected);
    }

    public final boolean getNavigatedToSetup() {
        return this.navigatedToSetup;
    }

    public final StateFlow getNavigationVisible() {
        return this._navigationVisible;
    }

    public final StateFlow getOpenImportSaves() {
        return FlowKt.asStateFlow(this._openImportSaves);
    }

    public final StateFlow getReloadPropertiesList() {
        return FlowKt.asStateFlow(this._reloadPropertiesList);
    }

    public final StateFlow getShouldPageForward() {
        return this._shouldPageForward;
    }

    public final StateFlow getStatusBarShadeVisible() {
        return this._statusBarShadeVisible;
    }

    public final void reloadPropertiesList(boolean z) {
        this._reloadPropertiesList.setValue(Boolean.valueOf(z));
    }

    public final void setCheckKeys(boolean z) {
        this._checkKeys.setValue(Boolean.valueOf(z));
    }

    public final void setContentToInstall(List list) {
        this._contentToInstall.setValue(list);
    }

    public final void setGamesDirSelected(boolean z) {
        this._gamesDirSelected.setValue(Boolean.valueOf(z));
    }

    public final void setNavigatedToSetup(boolean z) {
        this.navigatedToSetup = z;
    }

    public final void setNavigationVisibility(boolean z, boolean z2) {
        if (((Boolean) ((Pair) getNavigationVisible().getValue()).getFirst()).booleanValue() == z) {
            return;
        }
        this._navigationVisible.setValue(new Pair(Boolean.valueOf(z), Boolean.valueOf(z2)));
    }

    public final void setOpenImportSaves(boolean z) {
        this._openImportSaves.setValue(Boolean.valueOf(z));
    }

    public final void setShouldPageForward(boolean z) {
        this._shouldPageForward.setValue(Boolean.valueOf(z));
    }

    public final void setStatusBarShadeVisibility(boolean z) {
        if (((Boolean) getStatusBarShadeVisible().getValue()).booleanValue() == z) {
            return;
        }
        this._statusBarShadeVisible.setValue(Boolean.valueOf(z));
    }
}
