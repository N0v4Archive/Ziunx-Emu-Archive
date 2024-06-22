package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.customview.widget.ViewDragHelper;

/* loaded from: classes.dex */
final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior sheetBehavior;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RightSheetDelegate(SideSheetBehavior sideSheetBehavior) {
        this.sheetBehavior = sideSheetBehavior;
    }

    private boolean isReleasedCloseToOriginEdge(View view) {
        return view.getLeft() > (getHiddenOffset() - getExpandedOffset()) / 2;
    }

    private boolean isSwipeSignificant(float f, float f2) {
        return SheetUtils.isSwipeMostlyHorizontal(f, f2) && f2 > ((float) this.sheetBehavior.getSignificantVelocityThreshold());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public float calculateSlideOffset(int i) {
        float hiddenOffset = getHiddenOffset();
        return (hiddenOffset - i) / (hiddenOffset - getExpandedOffset());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int calculateTargetStateOnViewReleased(View view, float f, float f2) {
        if (f < 0.0f) {
            return 3;
        }
        if (shouldHide(view, f)) {
            if (!isSwipeSignificant(f, f2) && !isReleasedCloseToOriginEdge(view)) {
                return 3;
            }
        } else if (f == 0.0f || !SheetUtils.isSwipeMostlyHorizontal(f, f2)) {
            int left = view.getLeft();
            if (Math.abs(left - getExpandedOffset()) < Math.abs(left - getHiddenOffset())) {
                return 3;
            }
        }
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getExpandedOffset() {
        return Math.max(0, (getHiddenOffset() - this.sheetBehavior.getChildWidth()) - this.sheetBehavior.getInnerMargin());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getHiddenOffset() {
        return this.sheetBehavior.getParentWidth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getOuterEdge(View view) {
        return view.getLeft() - this.sheetBehavior.getInnerMargin();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getSheetEdge() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public boolean isSettling(View view, int i, boolean z) {
        int outerEdgeOffsetForState = this.sheetBehavior.getOuterEdgeOffsetForState(i);
        ViewDragHelper viewDragHelper = this.sheetBehavior.getViewDragHelper();
        return viewDragHelper != null && (!z ? !viewDragHelper.smoothSlideViewTo(view, outerEdgeOffsetForState, view.getTop()) : !viewDragHelper.settleCapturedViewAt(outerEdgeOffsetForState, view.getTop()));
    }

    boolean shouldHide(View view, float f) {
        return Math.abs(((float) view.getRight()) + (f * this.sheetBehavior.getHideFriction())) > this.sheetBehavior.getHideThreshold();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.sidesheet.SheetDelegate
    public void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        int parentWidth = this.sheetBehavior.getParentWidth();
        if (i <= parentWidth) {
            marginLayoutParams.rightMargin = parentWidth - i;
        }
    }
}
