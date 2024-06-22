package org.yuzu.yuzu_emu.overlay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;

/* loaded from: classes.dex */
public final class InputOverlayDrawableJoystick {
    private final BitmapDrawable boundsBoxBitmap;
    private final NativeButton button;
    private int controlPositionX;
    private int controlPositionY;
    private final BitmapDrawable defaultStateInnerBitmap;
    private final int height;
    private final NativeAnalog joystick;
    private int opacity;
    private Rect origBounds;
    private final BitmapDrawable outerBitmap;
    private final String prefId;
    private boolean pressedState;
    private final BitmapDrawable pressedStateInnerBitmap;
    private int previousTouchX;
    private int previousTouchY;
    private int trackId;
    private Rect virtBounds;
    private final int width;
    private float xAxis;
    private float yAxis;

    public InputOverlayDrawableJoystick(Resources res, Bitmap bitmapOuter, Bitmap bitmapInnerDefault, Bitmap bitmapInnerPressed, Rect rectOuter, Rect rectInner, NativeAnalog joystick, NativeButton button, String prefId) {
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(bitmapOuter, "bitmapOuter");
        Intrinsics.checkNotNullParameter(bitmapInnerDefault, "bitmapInnerDefault");
        Intrinsics.checkNotNullParameter(bitmapInnerPressed, "bitmapInnerPressed");
        Intrinsics.checkNotNullParameter(rectOuter, "rectOuter");
        Intrinsics.checkNotNullParameter(rectInner, "rectInner");
        Intrinsics.checkNotNullParameter(joystick, "joystick");
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(prefId, "prefId");
        this.joystick = joystick;
        this.button = button;
        this.prefId = prefId;
        this.trackId = -1;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(res, bitmapOuter);
        this.outerBitmap = bitmapDrawable;
        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(res, bitmapInnerDefault);
        this.defaultStateInnerBitmap = bitmapDrawable2;
        BitmapDrawable bitmapDrawable3 = new BitmapDrawable(res, bitmapInnerPressed);
        this.pressedStateInnerBitmap = bitmapDrawable3;
        BitmapDrawable bitmapDrawable4 = new BitmapDrawable(res, bitmapOuter);
        this.boundsBoxBitmap = bitmapDrawable4;
        this.width = bitmapOuter.getWidth();
        this.height = bitmapOuter.getHeight();
        setBounds(rectOuter);
        bitmapDrawable2.setBounds(rectInner);
        bitmapDrawable3.setBounds(rectInner);
        this.virtBounds = getBounds();
        Rect copyBounds = bitmapDrawable.copyBounds();
        Intrinsics.checkNotNullExpressionValue(copyBounds, "copyBounds(...)");
        this.origBounds = copyBounds;
        bitmapDrawable4.setAlpha(0);
        bitmapDrawable4.setBounds(this.virtBounds);
        setInnerBounds();
    }

    private final BitmapDrawable getCurrentStateBitmapDrawable() {
        return this.pressedState ? this.pressedStateInnerBitmap : this.defaultStateInnerBitmap;
    }

    private final void setInnerBounds() {
        int centerX = this.virtBounds.centerX() + ((int) (this.xAxis * (this.virtBounds.width() / 2)));
        int centerY = this.virtBounds.centerY() + ((int) (this.yAxis * (this.virtBounds.height() / 2)));
        if (centerX > this.virtBounds.centerX() + (this.virtBounds.width() / 2)) {
            centerX = this.virtBounds.centerX() + (this.virtBounds.width() / 2);
        }
        if (centerX < this.virtBounds.centerX() - (this.virtBounds.width() / 2)) {
            centerX = this.virtBounds.centerX() - (this.virtBounds.width() / 2);
        }
        if (centerY > this.virtBounds.centerY() + (this.virtBounds.height() / 2)) {
            centerY = this.virtBounds.centerY() + (this.virtBounds.height() / 2);
        }
        if (centerY < this.virtBounds.centerY() - (this.virtBounds.height() / 2)) {
            centerY = this.virtBounds.centerY() - (this.virtBounds.height() / 2);
        }
        int width = this.pressedStateInnerBitmap.getBounds().width() / 2;
        int height = this.pressedStateInnerBitmap.getBounds().height() / 2;
        this.defaultStateInnerBitmap.setBounds(centerX - width, centerY - height, centerX + width, centerY + height);
        this.pressedStateInnerBitmap.setBounds(this.defaultStateInnerBitmap.getBounds());
    }

    public final void draw(Canvas canvas) {
        BitmapDrawable bitmapDrawable = this.outerBitmap;
        Intrinsics.checkNotNull(canvas);
        bitmapDrawable.draw(canvas);
        getCurrentStateBitmapDrawable().draw(canvas);
        this.boundsBoxBitmap.draw(canvas);
    }

    public final Rect getBounds() {
        Rect bounds = this.outerBitmap.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        return bounds;
    }

    public final NativeButton getButton() {
        return this.button;
    }

    public final int getButtonStatus() {
        return 0;
    }

    public final NativeAnalog getJoystick() {
        return this.joystick;
    }

    public final String getPrefId() {
        return this.prefId;
    }

    public final float getRealYAxis() {
        return -this.yAxis;
    }

    public final int getTrackId() {
        return this.trackId;
    }

    public final float getXAxis() {
        return this.xAxis;
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
        } else if (action == 2) {
            this.controlPositionX += x - this.previousTouchX;
            this.controlPositionY += y - this.previousTouchY;
            setBounds(new Rect(this.controlPositionX, this.controlPositionY, this.outerBitmap.getIntrinsicWidth() + this.controlPositionX, this.outerBitmap.getIntrinsicHeight() + this.controlPositionY));
            this.virtBounds = new Rect(this.controlPositionX, this.controlPositionY, this.outerBitmap.getIntrinsicWidth() + this.controlPositionX, this.outerBitmap.getIntrinsicHeight() + this.controlPositionY);
            setInnerBounds();
            setBounds(new Rect(new Rect(this.controlPositionX, this.controlPositionY, this.outerBitmap.getIntrinsicWidth() + this.controlPositionX, this.outerBitmap.getIntrinsicHeight() + this.controlPositionY)));
            this.previousTouchX = x;
            this.previousTouchY = y;
        }
        Rect copyBounds = this.outerBitmap.copyBounds();
        Intrinsics.checkNotNullExpressionValue(copyBounds, "copyBounds(...)");
        this.origBounds = copyBounds;
        return true;
    }

    public final void setBounds(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.outerBitmap.setBounds(bounds);
    }

    public final void setOpacity(int i) {
        this.opacity = i;
        this.defaultStateInnerBitmap.setAlpha(i);
        this.pressedStateInnerBitmap.setAlpha(i);
        if (this.trackId == -1) {
            this.outerBitmap.setAlpha(i);
            this.boundsBoxBitmap.setAlpha(0);
        } else {
            this.outerBitmap.setAlpha(0);
            this.boundsBoxBitmap.setAlpha(i);
        }
    }

    public final void setPosition(int i, int i2) {
        this.controlPositionX = i;
        this.controlPositionY = i2;
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
            this.outerBitmap.setAlpha(0);
            this.boundsBoxBitmap.setAlpha(this.opacity);
            if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.JOYSTICK_REL_CENTER, false, 1, null)) {
                Rect rect = this.virtBounds;
                rect.offset(x - rect.centerX(), y - this.virtBounds.centerY());
            }
            this.boundsBoxBitmap.setBounds(this.virtBounds);
            this.trackId = pointerId;
        }
        if (z2) {
            if (this.trackId != pointerId) {
                return false;
            }
            this.pressedState = false;
            this.xAxis = 0.0f;
            this.yAxis = 0.0f;
            this.outerBitmap.setAlpha(this.opacity);
            this.boundsBoxBitmap.setAlpha(0);
            Rect rect2 = this.origBounds;
            this.virtBounds = new Rect(rect2.left, rect2.top, rect2.right, rect2.bottom);
            Rect rect3 = this.origBounds;
            setBounds(new Rect(rect3.left, rect3.top, rect3.right, rect3.bottom));
            setInnerBounds();
            this.trackId = -1;
            return true;
        }
        if (this.trackId == -1) {
            return false;
        }
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            if (this.trackId == event.getPointerId(i)) {
                float x2 = event.getX(i);
                float centerX = (x2 - r1.centerX()) / (r1.right - this.virtBounds.centerX());
                float y2 = (event.getY(i) - this.virtBounds.centerY()) / (this.virtBounds.bottom - this.virtBounds.centerY());
                float f = this.xAxis;
                float f2 = this.yAxis;
                float atan2 = (float) Math.atan2(y2, centerX);
                float sqrt = (float) Math.sqrt((centerX * centerX) + (y2 * y2));
                if (sqrt > 1.0f) {
                    sqrt = 1.0f;
                }
                double d = atan2;
                this.xAxis = ((float) Math.cos(d)) * sqrt;
                this.yAxis = ((float) Math.sin(d)) * sqrt;
                setInnerBounds();
                if (!(f == this.xAxis)) {
                    if (!(f2 == this.yAxis)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
