package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes.dex */
public final class TaskViewModel extends ViewModel {
    private final MutableStateFlow _cancelled;
    private final MutableStateFlow _isComplete;
    private final MutableStateFlow _isRunning;
    private final MutableStateFlow _maxProgress;
    private final MutableStateFlow _message;
    private final MutableStateFlow _progress;
    private final MutableStateFlow _result = StateFlowKt.MutableStateFlow(new Object());
    private final StateFlow maxProgress;
    private final StateFlow message;
    private final StateFlow progress;
    public Function3 task;

    public TaskViewModel() {
        Boolean bool = Boolean.FALSE;
        this._isComplete = StateFlowKt.MutableStateFlow(bool);
        this._isRunning = StateFlowKt.MutableStateFlow(bool);
        this._cancelled = StateFlowKt.MutableStateFlow(bool);
        Double valueOf = Double.valueOf(0.0d);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(valueOf);
        this._progress = MutableStateFlow;
        this.progress = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(valueOf);
        this._maxProgress = MutableStateFlow2;
        this.maxProgress = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow("");
        this._message = MutableStateFlow3;
        this.message = FlowKt.asStateFlow(MutableStateFlow3);
    }

    public final void clear() {
        this._result.setValue(new Object());
        MutableStateFlow mutableStateFlow = this._isComplete;
        Boolean bool = Boolean.FALSE;
        mutableStateFlow.setValue(bool);
        this._isRunning.setValue(bool);
        this._cancelled.setValue(bool);
        MutableStateFlow mutableStateFlow2 = this._progress;
        Double valueOf = Double.valueOf(0.0d);
        mutableStateFlow2.setValue(valueOf);
        this._maxProgress.setValue(valueOf);
        this._message.setValue("");
    }

    public final StateFlow getCancelled() {
        return this._cancelled;
    }

    public final StateFlow getMaxProgress() {
        return this.maxProgress;
    }

    public final StateFlow getMessage() {
        return this.message;
    }

    public final StateFlow getProgress() {
        return this.progress;
    }

    public final StateFlow getResult() {
        return this._result;
    }

    public final Function3 getTask() {
        Function3 function3 = this.task;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("task");
        return null;
    }

    public final StateFlow isComplete() {
        return this._isComplete;
    }

    public final StateFlow isRunning() {
        return this._isRunning;
    }

    public final void runTask() {
        if (((Boolean) isRunning().getValue()).booleanValue()) {
            return;
        }
        this._isRunning.setValue(Boolean.TRUE);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new TaskViewModel$runTask$1(this, null), 2, null);
    }

    public final void setCancelled(boolean z) {
        this._cancelled.setValue(Boolean.valueOf(z));
    }

    public final void setTask(Function3 function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.task = function3;
    }
}
