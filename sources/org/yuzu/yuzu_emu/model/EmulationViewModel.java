package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public final class EmulationViewModel extends ViewModel {
    private final MutableStateFlow _drawerOpen;
    private final MutableStateFlow _emulationStarted;
    private final MutableStateFlow _emulationStopped;
    private final MutableStateFlow _isEmulationStopping;
    private final MutableStateFlow _programChanged;
    private final MutableStateFlow _shaderMessage;
    private final MutableStateFlow _shaderProgress;
    private final MutableStateFlow _totalShaders;
    private final StateFlow drawerOpen;
    private final StateFlow emulationStopped;
    private final StateFlow programChanged;

    public EmulationViewModel() {
        Boolean bool = Boolean.FALSE;
        this._emulationStarted = StateFlowKt.MutableStateFlow(bool);
        this._isEmulationStopping = StateFlowKt.MutableStateFlow(bool);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._emulationStopped = MutableStateFlow;
        this.emulationStopped = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(-1);
        this._programChanged = MutableStateFlow2;
        this.programChanged = FlowKt.asStateFlow(MutableStateFlow2);
        this._shaderProgress = StateFlowKt.MutableStateFlow(0);
        this._totalShaders = StateFlowKt.MutableStateFlow(0);
        this._shaderMessage = StateFlowKt.MutableStateFlow("");
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._drawerOpen = MutableStateFlow3;
        this.drawerOpen = FlowKt.asStateFlow(MutableStateFlow3);
    }

    public final StateFlow getDrawerOpen() {
        return this.drawerOpen;
    }

    public final StateFlow getEmulationStarted() {
        return this._emulationStarted;
    }

    public final StateFlow getEmulationStopped() {
        return this.emulationStopped;
    }

    public final StateFlow getProgramChanged() {
        return this.programChanged;
    }

    public final StateFlow getShaderMessage() {
        return this._shaderMessage;
    }

    public final StateFlow getShaderProgress() {
        return this._shaderProgress;
    }

    public final StateFlow getTotalShaders() {
        return this._totalShaders;
    }

    public final StateFlow isEmulationStopping() {
        return this._isEmulationStopping;
    }

    public final void setDrawerOpen(boolean z) {
        this._drawerOpen.setValue(Boolean.valueOf(z));
    }

    public final void setEmulationStarted(boolean z) {
        this._emulationStarted.setValue(Boolean.valueOf(z));
    }

    public final void setEmulationStopped(boolean z) {
        if (z) {
            this._emulationStarted.setValue(Boolean.FALSE);
        }
        this._emulationStopped.setValue(Boolean.valueOf(z));
    }

    public final void setIsEmulationStopping(boolean z) {
        this._isEmulationStopping.setValue(Boolean.valueOf(z));
    }

    public final void setProgramChanged(int i) {
        this._programChanged.setValue(Integer.valueOf(i));
    }

    public final void setShaderMessage(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        this._shaderMessage.setValue(msg);
    }

    public final void setShaderProgress(int i) {
        this._shaderProgress.setValue(Integer.valueOf(i));
    }

    public final void setTotalShaders(int i) {
        this._totalShaders.setValue(Integer.valueOf(i));
    }

    public final void updateProgress(String msg, int i, int i2) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        setShaderMessage(msg);
        setShaderProgress(i);
        setTotalShaders(i2);
    }
}
