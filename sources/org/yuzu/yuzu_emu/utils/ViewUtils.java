package org.yuzu.yuzu_emu.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ViewUtils {
    public static final ViewUtils INSTANCE = new ViewUtils();

    private ViewUtils() {
    }

    public static /* synthetic */ void hideView$default(ViewUtils viewUtils, View view, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 300;
        }
        viewUtils.hideView(view, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hideView$lambda$4(View view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        view.setVisibility(4);
    }

    public static /* synthetic */ void marquee$default(ViewUtils viewUtils, TextView textView, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 3000;
        }
        viewUtils.marquee(textView, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void marquee$lambda$6(TextView this_marquee) {
        Intrinsics.checkNotNullParameter(this_marquee, "$this_marquee");
        this_marquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this_marquee.setSelected(true);
    }

    public static /* synthetic */ void setVisible$default(ViewUtils viewUtils, View view, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        viewUtils.setVisible(view, z, z2);
    }

    public static /* synthetic */ void showView$default(ViewUtils viewUtils, View view, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 300;
        }
        viewUtils.showView(view, j);
    }

    public final void hideView(final View view, long j) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getVisibility() == 4) {
            return;
        }
        view.setAlpha(1.0f);
        view.setClickable(false);
        ViewPropertyAnimator animate = view.animate();
        animate.setDuration(j);
        animate.alpha(0.0f);
        animate.withEndAction(new Runnable() { // from class: org.yuzu.yuzu_emu.utils.ViewUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ViewUtils.hideView$lambda$4(view);
            }
        }).start();
    }

    public final void marquee(final TextView textView, long j) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setEllipsize(null);
        textView.setMarqueeRepeatLimit(-1);
        textView.setSingleLine(true);
        textView.postDelayed(new Runnable() { // from class: org.yuzu.yuzu_emu.utils.ViewUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ViewUtils.marquee$lambda$6(textView);
            }
        }, j);
    }

    public final void setVisible(View view, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 0 : z2 ? 8 : 4);
    }

    public final void showView(View view, long j) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.setClickable(true);
        ViewPropertyAnimator animate = view.animate();
        animate.setDuration(j);
        animate.alpha(1.0f);
        animate.start();
    }

    public final void updateMargins(View view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (i != -1) {
            marginLayoutParams.leftMargin = i;
        }
        if (i2 != -1) {
            marginLayoutParams.topMargin = i2;
        }
        if (i3 != -1) {
            marginLayoutParams.rightMargin = i3;
        }
        if (i4 != -1) {
            marginLayoutParams.bottomMargin = i4;
        }
        view.setLayoutParams(marginLayoutParams);
    }
}
