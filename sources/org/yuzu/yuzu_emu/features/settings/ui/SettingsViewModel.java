package org.yuzu.yuzu_emu.features.settings.ui;

import androidx.lifecycle.ViewModel;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.InputHandler;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class SettingsViewModel extends ViewModel {
    private final MutableStateFlow _adapterItemChanged;
    private final MutableStateFlow _datasetChanged;
    private final MutableStateFlow _reloadListAndNotifyDataset;
    private final MutableStateFlow _shouldNavigateBack;
    private final MutableStateFlow _shouldRecreate;
    private final MutableStateFlow _shouldReloadSettingsList;
    private final MutableStateFlow _shouldShowDeleteProfileDialog;
    private final MutableStateFlow _shouldShowResetInputDialog;
    private final MutableStateFlow _shouldShowResetSettingsDialog;
    private final MutableStateFlow _sliderProgress;
    private final MutableStateFlow _sliderTextValue;
    private SettingsItem clickedItem;
    private int currentDevice;
    private final StateFlow datasetChanged;
    private Game game;
    private final StateFlow reloadListAndNotifyDataset;
    private final StateFlow shouldShowDeleteProfileDialog;
    private final StateFlow shouldShowResetInputDialog;

    public SettingsViewModel() {
        Boolean bool = Boolean.FALSE;
        this._shouldRecreate = StateFlowKt.MutableStateFlow(bool);
        this._shouldNavigateBack = StateFlowKt.MutableStateFlow(bool);
        this._shouldShowResetSettingsDialog = StateFlowKt.MutableStateFlow(bool);
        this._shouldReloadSettingsList = StateFlowKt.MutableStateFlow(bool);
        this._sliderProgress = StateFlowKt.MutableStateFlow(-1);
        this._sliderTextValue = StateFlowKt.MutableStateFlow("");
        this._adapterItemChanged = StateFlowKt.MutableStateFlow(-1);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._datasetChanged = MutableStateFlow;
        this.datasetChanged = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._reloadListAndNotifyDataset = MutableStateFlow2;
        this.reloadListAndNotifyDataset = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow("");
        this._shouldShowDeleteProfileDialog = MutableStateFlow3;
        this.shouldShowDeleteProfileDialog = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._shouldShowResetInputDialog = MutableStateFlow4;
        this.shouldShowResetInputDialog = FlowKt.asStateFlow(MutableStateFlow4);
    }

    public final StateFlow getAdapterItemChanged() {
        return this._adapterItemChanged;
    }

    public final SettingsItem getClickedItem() {
        return this.clickedItem;
    }

    public final int getCurrentDevice() {
        return this.currentDevice;
    }

    public final ParamPackage getCurrentDeviceParams(ParamPackage defaultParams) {
        Intrinsics.checkNotNullParameter(defaultParams, "defaultParams");
        try {
            return (ParamPackage) InputHandler.INSTANCE.getRegisteredControllers().get(this.currentDevice);
        } catch (IndexOutOfBoundsException unused) {
            return defaultParams;
        }
    }

    public final StateFlow getDatasetChanged() {
        return this.datasetChanged;
    }

    public final Game getGame() {
        return this.game;
    }

    public final StateFlow getReloadListAndNotifyDataset() {
        return this.reloadListAndNotifyDataset;
    }

    public final StateFlow getShouldNavigateBack() {
        return this._shouldNavigateBack;
    }

    public final StateFlow getShouldRecreate() {
        return this._shouldRecreate;
    }

    public final StateFlow getShouldReloadSettingsList() {
        return this._shouldReloadSettingsList;
    }

    public final StateFlow getShouldShowDeleteProfileDialog() {
        return this.shouldShowDeleteProfileDialog;
    }

    public final StateFlow getShouldShowResetInputDialog() {
        return this.shouldShowResetInputDialog;
    }

    public final StateFlow getShouldShowResetSettingsDialog() {
        return this._shouldShowResetSettingsDialog;
    }

    public final StateFlow getSliderProgress() {
        return this._sliderProgress;
    }

    public final StateFlow getSliderTextValue() {
        return this._sliderTextValue;
    }

    public final void setAdapterItemChanged(int i) {
        this._adapterItemChanged.setValue(Integer.valueOf(i));
    }

    public final void setClickedItem(SettingsItem settingsItem) {
        this.clickedItem = settingsItem;
    }

    public final void setCurrentDevice(int i) {
        this.currentDevice = i;
    }

    public final void setDatasetChanged(boolean z) {
        this._datasetChanged.setValue(Boolean.valueOf(z));
    }

    public final void setGame(Game game) {
        this.game = game;
    }

    public final void setReloadListAndNotifyDataset(boolean z) {
        this._reloadListAndNotifyDataset.setValue(Boolean.valueOf(z));
    }

    public final void setShouldNavigateBack(boolean z) {
        this._shouldNavigateBack.setValue(Boolean.valueOf(z));
    }

    public final void setShouldRecreate(boolean z) {
        this._shouldRecreate.setValue(Boolean.valueOf(z));
    }

    public final void setShouldReloadSettingsList(boolean z) {
        this._shouldReloadSettingsList.setValue(Boolean.valueOf(z));
    }

    public final void setShouldShowDeleteProfileDialog(String profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        this._shouldShowDeleteProfileDialog.setValue(profile);
    }

    public final void setShouldShowResetInputDialog(boolean z) {
        this._shouldShowResetInputDialog.setValue(Boolean.valueOf(z));
    }

    public final void setShouldShowResetSettingsDialog(boolean z) {
        this._shouldShowResetSettingsDialog.setValue(Boolean.valueOf(z));
    }

    public final void setSliderProgress(float f) {
        this._sliderProgress.setValue(Integer.valueOf((int) f));
    }

    public final void setSliderTextValue(float f, String units) {
        Intrinsics.checkNotNullParameter(units, "units");
        int i = (int) f;
        this._sliderProgress.setValue(Integer.valueOf(i));
        MutableStateFlow mutableStateFlow = this._sliderTextValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.value_with_units);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(i), units}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        mutableStateFlow.setValue(format);
    }
}
