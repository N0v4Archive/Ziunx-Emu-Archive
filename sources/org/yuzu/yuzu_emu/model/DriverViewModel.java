package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.features.settings.model.AbstractStringSetting;
import org.yuzu.yuzu_emu.features.settings.model.StringSetting;
import org.yuzu.yuzu_emu.features.settings.utils.SettingsFile;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.GpuDriverMetadata;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class DriverViewModel extends ViewModel {
    private final MutableStateFlow _areDriversLoading;
    private final MutableStateFlow _driverList;
    private final MutableStateFlow _isDeletingDrivers;
    private final MutableStateFlow _isDriverReady;
    private final MutableStateFlow _selectedDriverTitle;
    private final MutableStateFlow _showClearButton;
    private List driverData;
    private final List driversToDelete;
    private final StateFlow isInteractionAllowed;
    private final StateFlow showClearButton;

    public DriverViewModel() {
        List emptyList;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._areDriversLoading = MutableStateFlow;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._isDriverReady = MutableStateFlow2;
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isDeletingDrivers = MutableStateFlow3;
        this.isInteractionAllowed = FlowKt.stateIn(FlowKt.combine(MutableStateFlow, MutableStateFlow2, MutableStateFlow3, new DriverViewModel$isInteractionAllowed$1(null)), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 0L, 3, null), bool);
        this.driverData = GpuDriverHelper.INSTANCE.getDrivers();
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this._driverList = StateFlowKt.MutableStateFlow(emptyList);
        this._selectedDriverTitle = StateFlowKt.MutableStateFlow("");
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._showClearButton = MutableStateFlow4;
        this.showClearButton = FlowKt.asStateFlow(MutableStateFlow4);
        this.driversToDelete = new ArrayList();
        updateDriverList();
        updateDriverNameForGame(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDriverReady() {
        this._isDriverReady.setValue(Boolean.TRUE);
        updateName();
    }

    private final void updateName() {
        MutableStateFlow mutableStateFlow = this._selectedDriverTitle;
        String name = GpuDriverHelper.INSTANCE.getCustomDriverSettingData().getName();
        if (name == null) {
            name = YuzuApplication.Companion.getAppContext().getString(R$string.system_gpu_driver);
            Intrinsics.checkNotNullExpressionValue(name, "getString(...)");
        }
        mutableStateFlow.setValue(name);
    }

    public final List getDriverData() {
        return this.driverData;
    }

    public final StateFlow getDriverList() {
        return this._driverList;
    }

    public final StateFlow getSelectedDriverTitle() {
        return this._selectedDriverTitle;
    }

    public final StateFlow getShowClearButton() {
        return this.showClearButton;
    }

    public final StateFlow isInteractionAllowed() {
        return this.isInteractionAllowed;
    }

    public final void onCloseDriverManager(Game game) {
        this._isDeletingDrivers.setValue(Boolean.TRUE);
        updateDriverNameForGame(game);
        if (game == null) {
            NativeConfig.INSTANCE.saveGlobalConfig();
        } else {
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            nativeConfig.savePerGameConfig();
            nativeConfig.unloadPerGameConfig();
            nativeConfig.reloadGlobalConfig();
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DriverViewModel$onCloseDriverManager$1(this, null), 3, null);
    }

    public final void onDriverAdded(Pair driver) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        if (this.driversToDelete.contains(driver.getFirst())) {
            this.driversToDelete.remove(driver.getFirst());
        }
        this.driverData.add(driver);
        onDriverSelected(this.driverData.size());
    }

    public final void onDriverRemoved(int i, int i2) {
        int i3 = i - 1;
        this.driversToDelete.add(((Pair) this.driverData.get(i3)).getFirst());
        this.driverData.remove(i3);
        onDriverSelected(i2);
    }

    public final void onDriverSelected(int i) {
        if (i == 0) {
            StringSetting.DRIVER_PATH.setString("");
        } else {
            StringSetting.DRIVER_PATH.setString((String) ((Pair) this.driverData.get(i - 1)).getFirst());
        }
    }

    public final void onLaunchGame() {
        this._isDriverReady.setValue(Boolean.FALSE);
        File file = new File(AbstractStringSetting.DefaultImpls.getString$default(StringSetting.DRIVER_PATH, false, 1, null));
        GpuDriverHelper gpuDriverHelper = GpuDriverHelper.INSTANCE;
        GpuDriverMetadata customDriverSettingData = gpuDriverHelper.getCustomDriverSettingData();
        if (Intrinsics.areEqual(gpuDriverHelper.getInstalledCustomDriverData(), customDriverSettingData)) {
            setDriverReady();
        } else {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DriverViewModel$onLaunchGame$1(customDriverSettingData, this, file, null), 3, null);
        }
    }

    public final void onOpenDriverManager(Game game) {
        if (game != null) {
            SettingsFile.INSTANCE.loadCustomConfig(game);
        }
        updateDriverList();
    }

    public final void reloadDriverData() {
        this._areDriversLoading.setValue(Boolean.TRUE);
        this.driverData = GpuDriverHelper.INSTANCE.getDrivers();
        updateDriverList();
        this._areDriversLoading.setValue(Boolean.FALSE);
    }

    public final void showClearButton(boolean z) {
        this._showClearButton.setValue(Boolean.valueOf(z));
    }

    public final void updateDriverList() {
        String str;
        List mutableListOf;
        String str2;
        GpuDriverHelper gpuDriverHelper = GpuDriverHelper.INSTANCE;
        GpuDriverMetadata customDriverSettingData = gpuDriverHelper.getCustomDriverSettingData();
        String[] systemDriverInfo$default = GpuDriverHelper.getSystemDriverInfo$default(gpuDriverHelper, null, null, 3, null);
        Driver[] driverArr = new Driver[1];
        boolean areEqual = Intrinsics.areEqual(customDriverSettingData, new GpuDriverMetadata());
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.system_gpu_driver);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str3 = "";
        if (systemDriverInfo$default == null || (str = systemDriverInfo$default[0]) == null) {
            str = "";
        }
        if (systemDriverInfo$default != null && (str2 = systemDriverInfo$default[1]) != null) {
            str3 = str2;
        }
        driverArr[0] = new Driver(areEqual, string, str, str3);
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(driverArr);
        for (Pair pair : this.driverData) {
            mutableListOf.add(Driver.Companion.toDriver((GpuDriverMetadata) pair.getSecond(), Intrinsics.areEqual(pair.getSecond(), customDriverSettingData)));
        }
        this._driverList.setValue(mutableListOf);
    }

    public final void updateDriverNameForGame(Game game) {
        if (GpuDriverHelper.INSTANCE.supportsCustomDriverLoading()) {
            if (game != null) {
                NativeConfig nativeConfig = NativeConfig.INSTANCE;
                if (!nativeConfig.isPerGameConfigLoaded()) {
                    SettingsFile.INSTANCE.loadCustomConfig(game);
                    updateName();
                    nativeConfig.unloadPerGameConfig();
                    nativeConfig.reloadGlobalConfig();
                    return;
                }
            }
            updateName();
        }
    }
}
