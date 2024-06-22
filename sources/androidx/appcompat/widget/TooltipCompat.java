package androidx.appcompat.widget;

import android.view.View;

/* loaded from: classes.dex */
public abstract class TooltipCompat {

    /* loaded from: classes.dex */
    static class Api26Impl {
        static void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        Api26Impl.setTooltipText(view, charSequence);
    }
}
