package com.google.android.material.internal;

import android.widget.Checkable;

/* loaded from: classes.dex */
public interface MaterialCheckable extends Checkable {

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(Object obj, boolean z);
    }

    int getId();

    void setInternalOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener);
}
