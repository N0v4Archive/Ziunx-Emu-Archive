package org.yuzu.yuzu_emu.model;

/* loaded from: classes.dex */
public interface SelectableItem {
    boolean getSelected();

    void onSelectionStateChanged(boolean z);
}
