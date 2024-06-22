package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class MessageDialogViewModel extends ViewModel {
    private Function0 negativeAction;
    private Function0 positiveAction;

    public final void clear() {
        this.positiveAction = null;
        this.negativeAction = null;
    }

    public final Function0 getNegativeAction() {
        return this.negativeAction;
    }

    public final Function0 getPositiveAction() {
        return this.positiveAction;
    }

    public final void setNegativeAction(Function0 function0) {
        this.negativeAction = function0;
    }

    public final void setPositiveAction(Function0 function0) {
        this.positiveAction = function0;
    }
}
