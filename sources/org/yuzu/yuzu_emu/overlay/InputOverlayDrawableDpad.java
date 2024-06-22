package org.yuzu.yuzu_emu.overlay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;

/* loaded from: classes.dex */
public final class InputOverlayDrawableDpad {
    public static final Companion Companion = new Companion(null);
    private int controlPositionX;
    private int controlPositionY;
    private final BitmapDrawable defaultStateBitmap;
    private final NativeButton down;
    private boolean downButtonState;
    private final int height;
    private final NativeButton left;
    private boolean leftButtonState;
    private final BitmapDrawable pressedOneDirectionStateBitmap;
    private final BitmapDrawable pressedTwoDirectionsStateBitmap;
    private int previousTouchX;
    private int previousTouchY;
    private final NativeButton right;
    private boolean rightButtonState;
    private int trackId;
    private final NativeButton up;
    private boolean upButtonState;
    private final int width;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public InputOverlayDrawableDpad(Resources res, Bitmap defaultStateBitmap, Bitmap pressedOneDirectionStateBitmap, Bitmap pressedTwoDirectionsStateBitmap) {
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(defaultStateBitmap, "defaultStateBitmap");
        Intrinsics.checkNotNullParameter(pressedOneDirectionStateBitmap, "pressedOneDirectionStateBitmap");
        Intrinsics.checkNotNullParameter(pressedTwoDirectionsStateBitmap, "pressedTwoDirectionsStateBitmap");
        this.up = NativeButton.DUp;
        this.down = NativeButton.DDown;
        this.left = NativeButton.DLeft;
        this.right = NativeButton.DRight;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(res, defaultStateBitmap);
        this.defaultStateBitmap = bitmapDrawable;
        this.pressedOneDirectionStateBitmap = new BitmapDrawable(res, pressedOneDirectionStateBitmap);
        this.pressedTwoDirectionsStateBitmap = new BitmapDrawable(res, pressedTwoDirectionsStateBitmap);
        this.width = bitmapDrawable.getIntrinsicWidth();
        this.height = bitmapDrawable.getIntrinsicHeight();
        this.trackId = -1;
    }

    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int i = this.controlPositionX + (this.width / 2);
        int i2 = this.controlPositionY + (this.height / 2);
        boolean z = this.upButtonState;
        if (z && !this.leftButtonState && !this.rightButtonState) {
            this.pressedOneDirectionStateBitmap.draw(canvas);
            return;
        }
        boolean z2 = this.downButtonState;
        if (z2 && !this.leftButtonState && !this.rightButtonState) {
            canvas.save();
            canvas.rotate(180.0f, i, i2);
            this.pressedOneDirectionStateBitmap.draw(canvas);
            canvas.restore();
            return;
        }
        boolean z3 = this.leftButtonState;
        if (z3 && !z && !z2) {
            canvas.save();
            canvas.rotate(270.0f, i, i2);
            this.pressedOneDirectionStateBitmap.draw(canvas);
            canvas.restore();
            return;
        }
        boolean z4 = this.rightButtonState;
        if (z4 && !z && !z2) {
            canvas.save();
            canvas.rotate(90.0f, i, i2);
            this.pressedOneDirectionStateBitmap.draw(canvas);
            canvas.restore();
            return;
        }
        if (z && z3 && !z4) {
            this.pressedTwoDirectionsStateBitmap.draw(canvas);
            return;
        }
        if (z && !z3 && z4) {
            canvas.save();
            canvas.rotate(90.0f, i, i2);
            this.pressedTwoDirectionsStateBitmap.draw(canvas);
            canvas.restore();
            return;
        }
        if (z2 && !z3 && z4) {
            canvas.save();
            canvas.rotate(180.0f, i, i2);
            this.pressedTwoDirectionsStateBitmap.draw(canvas);
            canvas.restore();
            return;
        }
        if (!z2 || !z3 || z4) {
            this.defaultStateBitmap.draw(canvas);
            return;
        }
        canvas.save();
        canvas.rotate(270.0f, i, i2);
        this.pressedTwoDirectionsStateBitmap.draw(canvas);
        canvas.restore();
    }

    public final Rect getBounds() {
        Rect bounds = this.defaultStateBitmap.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        return bounds;
    }

    public final NativeButton getDown() {
        return this.down;
    }

    public final int getDownStatus() {
        return this.downButtonState ? 1 : 0;
    }

    public final int getHeight() {
        return this.height;
    }

    public final NativeButton getLeft() {
        return this.left;
    }

    public final int getLeftStatus() {
        return this.leftButtonState ? 1 : 0;
    }

    public final NativeButton getRight() {
        return this.right;
    }

    public final int getRightStatus() {
        return this.rightButtonState ? 1 : 0;
    }

    public final int getTrackId() {
        return this.trackId;
    }

    public final NativeButton getUp() {
        return this.up;
    }

    public final int getUpStatus() {
        return this.upButtonState ? 1 : 0;
    }

    public final int getWidth() {
        return this.width;
    }

    public final boolean onConfigureTouch(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int actionIndex = event.getActionIndex();
        int x = (int) event.getX(actionIndex);
        int y = (int) event.getY(actionIndex);
        int action = event.getAction();
        if (action != 0) {
            if (action != 2) {
                return true;
            }
            int i = this.controlPositionX + (x - this.previousTouchX);
            this.controlPositionX = i;
            int i2 = this.controlPositionY + (y - this.previousTouchY);
            this.controlPositionY = i2;
            setBounds(i, i2, this.width + i, this.height + i2);
        }
        this.previousTouchX = x;
        this.previousTouchY = y;
        return true;
    }

    public final void setBounds(int i, int i2, int i3, int i4) {
        this.defaultStateBitmap.setBounds(i, i2, i3, i4);
        this.pressedOneDirectionStateBitmap.setBounds(i, i2, i3, i4);
        this.pressedTwoDirectionsStateBitmap.setBounds(i, i2, i3, i4);
    }

    public final void setOpacity(int i) {
        this.defaultStateBitmap.setAlpha(i);
        this.pressedOneDirectionStateBitmap.setAlpha(i);
        this.pressedTwoDirectionsStateBitmap.setAlpha(i);
    }

    public final void setPosition(int i, int i2) {
        this.controlPositionX = i;
        this.controlPositionY = i2;
    }

    public final boolean updateStatus(MotionEvent event, boolean z) {
        Intrinsics.checkNotNullParameter(event, "event");
        int actionIndex = event.getActionIndex();
        int x = (int) event.getX(actionIndex);
        int y = (int) event.getY(actionIndex);
        int pointerId = event.getPointerId(actionIndex);
        int action = event.getAction() & 255;
        boolean z2 = action == 0 || action == 5;
        boolean z3 = action == 1 || action == 6;
        if (z2) {
            if (!getBounds().contains(x, y)) {
                return false;
            }
            this.trackId = pointerId;
        }
        if (z3) {
            if (this.trackId != pointerId) {
                return false;
            }
            this.trackId = -1;
            this.upButtonState = false;
            this.downButtonState = false;
            this.leftButtonState = false;
            this.rightButtonState = false;
            return true;
        }
        if (this.trackId == -1) {
            return false;
        }
        if (!z && !z2) {
            return false;
        }
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            if (this.trackId == event.getPointerId(i)) {
                float x2 = event.getX(i);
                float centerX = (x2 - getBounds().centerX()) / (getBounds().right - getBounds().centerX());
                float y2 = (event.getY(i) - getBounds().centerY()) / (getBounds().bottom - getBounds().centerY());
                boolean z4 = this.upButtonState;
                boolean z5 = this.downButtonState;
                boolean z6 = this.leftButtonState;
                boolean z7 = this.rightButtonState;
                boolean z8 = y2 < -0.5f;
                this.upButtonState = z8;
                boolean z9 = y2 > 0.5f;
                this.downButtonState = z9;
                boolean z10 = centerX < -0.5f;
                this.leftButtonState = z10;
                boolean z11 = centerX > 0.5f;
                this.rightButtonState = z11;
                return (z4 == z8 && z5 == z9 && z6 == z10 && z7 == z11) ? false : true;
            }
        }
        return false;
    }
}
