package org.yuzu.yuzu_emu.overlay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.overlay.model.OverlayControlData;

/* loaded from: classes.dex */
public final class InputOverlayDrawableButton {
    private final NativeButton button;
    private int buttonPositionX;
    private int buttonPositionY;
    private int controlPositionX;
    private int controlPositionY;
    private final BitmapDrawable defaultStateBitmap;
    private final int height;
    private final OverlayControlData overlayControlData;
    private boolean pressedState;
    private final BitmapDrawable pressedStateBitmap;
    private int previousTouchX;
    private int previousTouchY;
    private int trackId;
    private final int width;

    public InputOverlayDrawableButton(Resources res, Bitmap defaultStateBitmap, Bitmap pressedStateBitmap, NativeButton button, OverlayControlData overlayControlData) {
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(defaultStateBitmap, "defaultStateBitmap");
        Intrinsics.checkNotNullParameter(pressedStateBitmap, "pressedStateBitmap");
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(overlayControlData, "overlayControlData");
        this.button = button;
        this.overlayControlData = overlayControlData;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(res, defaultStateBitmap);
        this.defaultStateBitmap = bitmapDrawable;
        this.pressedStateBitmap = new BitmapDrawable(res, pressedStateBitmap);
        this.trackId = -1;
        this.width = bitmapDrawable.getIntrinsicWidth();
        this.height = bitmapDrawable.getIntrinsicHeight();
    }

    private final BitmapDrawable getCurrentStateBitmapDrawable() {
        return this.pressedState ? this.pressedStateBitmap : this.defaultStateBitmap;
    }

    public final void draw(Canvas canvas) {
        BitmapDrawable currentStateBitmapDrawable = getCurrentStateBitmapDrawable();
        Intrinsics.checkNotNull(canvas);
        currentStateBitmapDrawable.draw(canvas);
    }

    public final Rect getBounds() {
        Rect bounds = this.defaultStateBitmap.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        return bounds;
    }

    public final NativeButton getButton() {
        return this.button;
    }

    public final int getHeight() {
        return this.height;
    }

    public final OverlayControlData getOverlayControlData() {
        return this.overlayControlData;
    }

    public final int getStatus() {
        return this.pressedState ? 1 : 0;
    }

    public final int getTrackId() {
        return this.trackId;
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
        if (action == 0) {
            this.previousTouchX = x;
            this.previousTouchY = y;
            this.controlPositionX = x - (this.width / 2);
            this.controlPositionY = y - (this.height / 2);
            return true;
        }
        if (action != 2) {
            return true;
        }
        int i = this.controlPositionX + (x - this.previousTouchX);
        this.controlPositionX = i;
        int i2 = this.controlPositionY + (y - this.previousTouchY);
        this.controlPositionY = i2;
        setBounds(i, i2, this.width + i, this.height + i2);
        this.previousTouchX = x;
        this.previousTouchY = y;
        return true;
    }

    public final void setBounds(int i, int i2, int i3, int i4) {
        this.defaultStateBitmap.setBounds(i, i2, i3, i4);
        this.pressedStateBitmap.setBounds(i, i2, i3, i4);
    }

    public final void setOpacity(int i) {
        this.defaultStateBitmap.setAlpha(i);
        this.pressedStateBitmap.setAlpha(i);
    }

    public final void setPosition(int i, int i2) {
        this.buttonPositionX = i;
        this.buttonPositionY = i2;
    }

    public final boolean updateStatus(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int actionIndex = event.getActionIndex();
        int x = (int) event.getX(actionIndex);
        int y = (int) event.getY(actionIndex);
        int pointerId = event.getPointerId(actionIndex);
        int action = event.getAction() & 255;
        boolean z = action == 0 || action == 5;
        boolean z2 = action == 1 || action == 6;
        if (z) {
            if (!getBounds().contains(x, y)) {
                return false;
            }
            this.pressedState = true;
            this.trackId = pointerId;
            return true;
        }
        if (!z2 || this.trackId != pointerId) {
            return false;
        }
        this.pressedState = false;
        this.trackId = -1;
        return true;
    }
}
