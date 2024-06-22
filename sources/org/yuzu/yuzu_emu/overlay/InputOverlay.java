package org.yuzu.yuzu_emu.overlay;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.features.input.model.NpadStyleIndex;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.overlay.model.OverlayControl;
import org.yuzu.yuzu_emu.overlay.model.OverlayControlData;
import org.yuzu.yuzu_emu.overlay.model.OverlayLayout;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class InputOverlay extends SurfaceView implements View.OnTouchListener {
    public static final Companion Companion = new Companion(null);
    private static final List overlayLayoutVersions;
    private InputOverlayDrawableButton buttonBeingConfigured;
    private InputOverlayDrawableDpad dpadBeingConfigured;
    private boolean inEditMode;
    private InputOverlayDrawableJoystick joystickBeingConfigured;
    private OverlayLayout layout;
    private final Set overlayButtons;
    private final Set overlayDpads;
    private final Set overlayJoysticks;
    private WindowInsets windowInsets;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Bitmap getBitmap(Context context, int i, float f) {
            Drawable drawable = ContextCompat.getDrawable(context, i);
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.VectorDrawable");
            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
            Bitmap createBitmap = Bitmap.createBitmap((int) (vectorDrawable.getIntrinsicWidth() * f), (int) (vectorDrawable.getIntrinsicHeight() * f), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float min = (f * Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)) / Math.max(createBitmap.getWidth(), createBitmap.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap, (int) (createBitmap.getWidth() * min), (int) (createBitmap.getHeight() * min), true);
            Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(...)");
            Canvas canvas = new Canvas(createScaledBitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
            return createScaledBitmap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00ca  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00f1  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00dd  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final kotlin.Pair getSafeScreenSize(android.content.Context r7, kotlin.Pair r8) {
            /*
                Method dump skipped, instructions count: 261
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.overlay.InputOverlay.Companion.getSafeScreenSize(android.content.Context, kotlin.Pair):kotlin.Pair");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final InputOverlayDrawableButton initializeOverlayButton(Context context, Pair pair, int i, int i2, NativeButton nativeButton, OverlayControlData overlayControlData, Pair pair2) {
            float f;
            Resources resources = context.getResources();
            String id = overlayControlData.getId();
            if (Intrinsics.areEqual(id, OverlayControl.BUTTON_HOME.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_CAPTURE.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_PLUS.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_MINUS.getId())) {
                f = 0.07f;
            } else {
                if (Intrinsics.areEqual(id, OverlayControl.BUTTON_L.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_R.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_ZL.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_ZR.getId())) {
                    f = 0.26f;
                } else {
                    f = Intrinsics.areEqual(id, OverlayControl.BUTTON_STICK_L.getId()) ? true : Intrinsics.areEqual(id, OverlayControl.BUTTON_STICK_R.getId()) ? 0.155f : 0.11f;
                }
            }
            float int$default = (f * (AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_SCALE, false, 1, null) + 50)) / 100.0f;
            Bitmap bitmap = getBitmap(context, i, int$default);
            Bitmap bitmap2 = getBitmap(context, i2, int$default);
            Intrinsics.checkNotNull(resources);
            InputOverlayDrawableButton inputOverlayDrawableButton = new InputOverlayDrawableButton(resources, bitmap, bitmap2, nativeButton, overlayControlData);
            Point point = (Point) pair.getFirst();
            Point point2 = (Point) pair.getSecond();
            int doubleValue = (int) ((((Number) pair2.getFirst()).doubleValue() * point2.x) + point.x);
            int doubleValue2 = (int) ((((Number) pair2.getSecond()).doubleValue() * point2.y) + point.y);
            int width = inputOverlayDrawableButton.getWidth() / 2;
            int i3 = doubleValue - width;
            int height = inputOverlayDrawableButton.getHeight() / 2;
            int i4 = doubleValue2 - height;
            inputOverlayDrawableButton.setBounds(i3, i4, doubleValue + width, doubleValue2 + height);
            inputOverlayDrawableButton.setPosition(i3, i4);
            inputOverlayDrawableButton.setOpacity((AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_OPACITY, false, 1, null) * 255) / 100);
            return inputOverlayDrawableButton;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final InputOverlayDrawableDpad initializeOverlayDpad(Context context, Pair pair, int i, int i2, int i3, Pair pair2) {
            Resources resources = context.getResources();
            float int$default = ((AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_SCALE, false, 1, null) + 50) * 0.25f) / 100.0f;
            Bitmap bitmap = getBitmap(context, i, int$default);
            Bitmap bitmap2 = getBitmap(context, i2, int$default);
            Bitmap bitmap3 = getBitmap(context, i3, int$default);
            Intrinsics.checkNotNull(resources);
            InputOverlayDrawableDpad inputOverlayDrawableDpad = new InputOverlayDrawableDpad(resources, bitmap, bitmap2, bitmap3);
            Point point = (Point) pair.getFirst();
            Point point2 = (Point) pair.getSecond();
            int doubleValue = (int) ((((Number) pair2.getFirst()).doubleValue() * point2.x) + point.x);
            int doubleValue2 = (int) ((((Number) pair2.getSecond()).doubleValue() * point2.y) + point.y);
            int width = inputOverlayDrawableDpad.getWidth() / 2;
            int i4 = doubleValue - width;
            int height = inputOverlayDrawableDpad.getHeight() / 2;
            int i5 = doubleValue2 - height;
            inputOverlayDrawableDpad.setBounds(i4, i5, doubleValue + width, doubleValue2 + height);
            inputOverlayDrawableDpad.setPosition(i4, i5);
            inputOverlayDrawableDpad.setOpacity((AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_OPACITY, false, 1, null) * 255) / 100);
            return inputOverlayDrawableDpad;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final InputOverlayDrawableJoystick initializeOverlayJoystick(Context context, Pair pair, int i, int i2, int i3, NativeAnalog nativeAnalog, NativeButton nativeButton, OverlayControlData overlayControlData, Pair pair2) {
            Resources resources = context.getResources();
            Bitmap bitmap = getBitmap(context, i, ((AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_SCALE, false, 1, null) + 50) * 0.3f) / 100.0f);
            Bitmap bitmap2 = getBitmap(context, i2, 1.0f);
            Bitmap bitmap3 = getBitmap(context, i3, 1.0f);
            Point point = (Point) pair.getFirst();
            Point point2 = (Point) pair.getSecond();
            int doubleValue = (int) ((((Number) pair2.getFirst()).doubleValue() * point2.x) + point.x);
            int doubleValue2 = (int) ((((Number) pair2.getSecond()).doubleValue() * point2.y) + point.y);
            int width = bitmap.getWidth();
            int i4 = width / 2;
            Rect rect = new Rect(doubleValue - i4, doubleValue2 - i4, doubleValue + i4, i4 + doubleValue2);
            int i5 = (int) (width / 1.66f);
            Rect rect2 = new Rect(0, 0, i5, i5);
            Intrinsics.checkNotNull(resources);
            InputOverlayDrawableJoystick inputOverlayDrawableJoystick = new InputOverlayDrawableJoystick(resources, bitmap, bitmap2, bitmap3, rect, rect2, nativeAnalog, nativeButton, overlayControlData.getId());
            inputOverlayDrawableJoystick.setPosition(doubleValue, doubleValue2);
            inputOverlayDrawableJoystick.setOpacity((AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.OVERLAY_OPACITY, false, 1, null) * 255) / 100);
            return inputOverlayDrawableJoystick;
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[NpadStyleIndex.values().length];
            try {
                iArr[NpadStyleIndex.Handheld.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OverlayLayout.values().length];
            try {
                iArr2[OverlayLayout.Landscape.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[OverlayLayout.Portrait.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[OverlayLayout.Foldable.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        List listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{1, 1, 1});
        overlayLayoutVersions = listOf;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.overlayButtons = new HashSet();
        this.overlayDpads = new HashSet();
        this.overlayJoysticks = new HashSet();
        this.layout = OverlayLayout.Landscape;
    }

    private final void addOverlayControls(OverlayLayout overlayLayout) {
        OverlayControlData[] overlayControlDataArr;
        Set set;
        Object initializeOverlayDpad;
        Companion companion;
        Context context;
        int i;
        int i2;
        int i3;
        NativeAnalog nativeAnalog;
        NativeButton nativeButton;
        Pair pair;
        Set set2;
        Companion companion2;
        Context context2;
        int i4;
        int i5;
        NativeButton nativeButton2;
        Companion companion3 = Companion;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        Pair safeScreenSize = companion3.getSafeScreenSize(context3, new Pair(Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight())));
        OverlayControlData[] overlayControlData = NativeConfig.INSTANCE.getOverlayControlData();
        int length = overlayControlData.length;
        int i6 = 0;
        while (i6 < length) {
            OverlayControlData overlayControlData2 = overlayControlData[i6];
            if (overlayControlData2.getEnabled()) {
                Pair positionFromLayout = overlayControlData2.positionFromLayout(overlayLayout);
                String id = overlayControlData2.getId();
                if (Intrinsics.areEqual(id, OverlayControl.BUTTON_A.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_a;
                    i5 = R$drawable.facebutton_a_depressed;
                    nativeButton2 = NativeButton.A;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_B.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_b;
                    i5 = R$drawable.facebutton_b_depressed;
                    nativeButton2 = NativeButton.B;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_X.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_x;
                    i5 = R$drawable.facebutton_x_depressed;
                    nativeButton2 = NativeButton.X;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_Y.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_y;
                    i5 = R$drawable.facebutton_y_depressed;
                    nativeButton2 = NativeButton.Y;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_PLUS.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_plus;
                    i5 = R$drawable.facebutton_plus_depressed;
                    nativeButton2 = NativeButton.Plus;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_MINUS.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_minus;
                    i5 = R$drawable.facebutton_minus_depressed;
                    nativeButton2 = NativeButton.Minus;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_HOME.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_home;
                    i5 = R$drawable.facebutton_home_depressed;
                    nativeButton2 = NativeButton.Home;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_CAPTURE.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.facebutton_screenshot;
                    i5 = R$drawable.facebutton_screenshot_depressed;
                    nativeButton2 = NativeButton.Capture;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_L.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.l_shoulder;
                    i5 = R$drawable.l_shoulder_depressed;
                    nativeButton2 = NativeButton.L;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_R.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.r_shoulder;
                    i5 = R$drawable.r_shoulder_depressed;
                    nativeButton2 = NativeButton.R;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_ZL.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.zl_trigger;
                    i5 = R$drawable.zl_trigger_depressed;
                    nativeButton2 = NativeButton.ZL;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_ZR.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.zr_trigger;
                    i5 = R$drawable.zr_trigger_depressed;
                    nativeButton2 = NativeButton.ZR;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_STICK_L.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.button_l3;
                    i5 = R$drawable.button_l3_depressed;
                    nativeButton2 = NativeButton.LStick;
                } else if (Intrinsics.areEqual(id, OverlayControl.BUTTON_STICK_R.getId())) {
                    set2 = this.overlayButtons;
                    companion2 = Companion;
                    context2 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    i4 = R$drawable.button_r3;
                    i5 = R$drawable.button_r3_depressed;
                    nativeButton2 = NativeButton.RStick;
                } else {
                    if (Intrinsics.areEqual(id, OverlayControl.STICK_L.getId())) {
                        Set set3 = this.overlayJoysticks;
                        companion = Companion;
                        context = getContext();
                        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                        i = R$drawable.joystick_range;
                        i2 = R$drawable.joystick;
                        i3 = R$drawable.joystick_depressed;
                        nativeAnalog = NativeAnalog.LStick;
                        pair = safeScreenSize;
                        overlayControlDataArr = overlayControlData;
                        set = set3;
                        nativeButton = NativeButton.LStick;
                    } else {
                        overlayControlDataArr = overlayControlData;
                        if (Intrinsics.areEqual(id, OverlayControl.STICK_R.getId())) {
                            set = this.overlayJoysticks;
                            companion = Companion;
                            context = getContext();
                            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                            i = R$drawable.joystick_range;
                            i2 = R$drawable.joystick;
                            i3 = R$drawable.joystick_depressed;
                            nativeAnalog = NativeAnalog.RStick;
                            nativeButton = NativeButton.RStick;
                            pair = safeScreenSize;
                        } else if (Intrinsics.areEqual(id, OverlayControl.COMBINED_DPAD.getId())) {
                            set = this.overlayDpads;
                            Companion companion4 = Companion;
                            Context context4 = getContext();
                            Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                            initializeOverlayDpad = companion4.initializeOverlayDpad(context4, safeScreenSize, R$drawable.dpad_standard, R$drawable.dpad_standard_cardinal_depressed, R$drawable.dpad_standard_diagonal_depressed, positionFromLayout);
                            set.add(initializeOverlayDpad);
                            i6++;
                            overlayControlData = overlayControlDataArr;
                        } else {
                            i6++;
                            overlayControlData = overlayControlDataArr;
                        }
                    }
                    initializeOverlayDpad = companion.initializeOverlayJoystick(context, pair, i, i2, i3, nativeAnalog, nativeButton, overlayControlData2, positionFromLayout);
                    set.add(initializeOverlayDpad);
                    i6++;
                    overlayControlData = overlayControlDataArr;
                }
                set2.add(companion2.initializeOverlayButton(context2, safeScreenSize, i4, i5, nativeButton2, overlayControlData2, positionFromLayout));
            }
            overlayControlDataArr = overlayControlData;
            i6++;
            overlayControlData = overlayControlDataArr;
        }
    }

    private final void checkForNewControls(OverlayControlData[] overlayControlDataArr) {
        OverlayControlData overlayControlData;
        ArrayList arrayList = new ArrayList();
        Iterator<E> it = OverlayControl.getEntries().iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            OverlayControl overlayControl = (OverlayControl) it.next();
            int length = overlayControlDataArr.length;
            while (true) {
                if (i >= length) {
                    overlayControlData = null;
                    break;
                }
                overlayControlData = overlayControlDataArr[i];
                if (Intrinsics.areEqual(overlayControlData.getId(), overlayControl.getId())) {
                    break;
                } else {
                    i++;
                }
            }
            if (overlayControlData == null) {
                arrayList.add(overlayControl.toOverlayControlData());
            }
        }
        if (!arrayList.isEmpty()) {
            NativeConfig nativeConfig = NativeConfig.INSTANCE;
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(overlayControlDataArr);
            spreadBuilder.addSpread(arrayList.toArray(new OverlayControlData[0]));
            nativeConfig.setOverlayControlData((OverlayControlData[]) spreadBuilder.toArray(new OverlayControlData[spreadBuilder.size()]));
            nativeConfig.saveGlobalConfig();
        }
    }

    private final void defaultOverlayPositionByLayout(OverlayLayout overlayLayout) {
        OverlayControlData[] overlayControlData = NativeConfig.INSTANCE.getOverlayControlData();
        for (OverlayControlData overlayControlData2 : overlayControlData) {
            OverlayControl from = OverlayControl.Companion.from(overlayControlData2.getId());
            if (from != null) {
                Pair defaultPositionForLayout = from.getDefaultPositionForLayout(overlayLayout);
                int i = WhenMappings.$EnumSwitchMapping$1[overlayLayout.ordinal()];
                if (i == 1) {
                    overlayControlData2.setLandscapePosition(defaultPositionForLayout);
                } else if (i == 2) {
                    overlayControlData2.setPortraitPosition(defaultPositionForLayout);
                } else if (i == 3) {
                    overlayControlData2.setFoldablePosition(defaultPositionForLayout);
                }
            }
        }
        NativeConfig.INSTANCE.setOverlayControlData(overlayControlData);
    }

    private final boolean isTouchInputConsumed(int i) {
        Iterator it = this.overlayButtons.iterator();
        while (it.hasNext()) {
            if (((InputOverlayDrawableButton) it.next()).getTrackId() == i) {
                return true;
            }
        }
        Iterator it2 = this.overlayDpads.iterator();
        while (it2.hasNext()) {
            if (((InputOverlayDrawableDpad) it2.next()).getTrackId() == i) {
                return true;
            }
        }
        Iterator it3 = this.overlayJoysticks.iterator();
        while (it3.hasNext()) {
            if (((InputOverlayDrawableJoystick) it3.next()).getTrackId() == i) {
                return true;
            }
        }
        return false;
    }

    private final boolean onTouchWhileEditing(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int x = (int) motionEvent.getX(actionIndex);
        int y = (int) motionEvent.getY(actionIndex);
        Iterator it = this.overlayButtons.iterator();
        while (true) {
            if (!it.hasNext()) {
                for (InputOverlayDrawableDpad inputOverlayDrawableDpad : this.overlayDpads) {
                    int action = motionEvent.getAction() & 255;
                    if (action != 0) {
                        if (action != 1) {
                            if (action == 2) {
                                InputOverlayDrawableDpad inputOverlayDrawableDpad2 = this.dpadBeingConfigured;
                                if (inputOverlayDrawableDpad2 != null) {
                                    Intrinsics.checkNotNull(inputOverlayDrawableDpad2);
                                    inputOverlayDrawableDpad2.onConfigureTouch(motionEvent);
                                }
                            } else if (action != 5) {
                                if (action != 6) {
                                }
                            }
                        }
                        if (this.dpadBeingConfigured == inputOverlayDrawableDpad) {
                            String id = OverlayControl.COMBINED_DPAD.getId();
                            InputOverlayDrawableDpad inputOverlayDrawableDpad3 = this.dpadBeingConfigured;
                            Intrinsics.checkNotNull(inputOverlayDrawableDpad3);
                            int centerX = inputOverlayDrawableDpad3.getBounds().centerX();
                            InputOverlayDrawableDpad inputOverlayDrawableDpad4 = this.dpadBeingConfigured;
                            Intrinsics.checkNotNull(inputOverlayDrawableDpad4);
                            saveControlPosition(id, centerX, inputOverlayDrawableDpad4.getBounds().centerY(), this.layout);
                            this.dpadBeingConfigured = null;
                        }
                    }
                    if (this.buttonBeingConfigured == null && inputOverlayDrawableDpad.getBounds().contains(x, y)) {
                        this.dpadBeingConfigured = inputOverlayDrawableDpad;
                        Intrinsics.checkNotNull(inputOverlayDrawableDpad);
                        inputOverlayDrawableDpad.onConfigureTouch(motionEvent);
                    }
                }
                for (InputOverlayDrawableJoystick inputOverlayDrawableJoystick : this.overlayJoysticks) {
                    int action2 = motionEvent.getAction();
                    if (action2 != 0) {
                        if (action2 != 1) {
                            if (action2 == 2) {
                                InputOverlayDrawableJoystick inputOverlayDrawableJoystick2 = this.joystickBeingConfigured;
                                if (inputOverlayDrawableJoystick2 != null) {
                                    Intrinsics.checkNotNull(inputOverlayDrawableJoystick2);
                                    inputOverlayDrawableJoystick2.onConfigureTouch(motionEvent);
                                    invalidate();
                                }
                            } else if (action2 != 5) {
                                if (action2 != 6) {
                                }
                            }
                        }
                        InputOverlayDrawableJoystick inputOverlayDrawableJoystick3 = this.joystickBeingConfigured;
                        if (inputOverlayDrawableJoystick3 != null) {
                            Intrinsics.checkNotNull(inputOverlayDrawableJoystick3);
                            String prefId = inputOverlayDrawableJoystick3.getPrefId();
                            InputOverlayDrawableJoystick inputOverlayDrawableJoystick4 = this.joystickBeingConfigured;
                            Intrinsics.checkNotNull(inputOverlayDrawableJoystick4);
                            int centerX2 = inputOverlayDrawableJoystick4.getBounds().centerX();
                            InputOverlayDrawableJoystick inputOverlayDrawableJoystick5 = this.joystickBeingConfigured;
                            Intrinsics.checkNotNull(inputOverlayDrawableJoystick5);
                            saveControlPosition(prefId, centerX2, inputOverlayDrawableJoystick5.getBounds().centerY(), this.layout);
                            this.joystickBeingConfigured = null;
                        }
                    }
                    if (this.joystickBeingConfigured == null && inputOverlayDrawableJoystick.getBounds().contains(x, y)) {
                        this.joystickBeingConfigured = inputOverlayDrawableJoystick;
                        Intrinsics.checkNotNull(inputOverlayDrawableJoystick);
                        inputOverlayDrawableJoystick.onConfigureTouch(motionEvent);
                    }
                }
                return true;
            }
            InputOverlayDrawableButton inputOverlayDrawableButton = (InputOverlayDrawableButton) it.next();
            int action3 = motionEvent.getAction() & 255;
            if (action3 != 0) {
                if (action3 != 1) {
                    if (action3 == 2) {
                        InputOverlayDrawableButton inputOverlayDrawableButton2 = this.buttonBeingConfigured;
                        if (inputOverlayDrawableButton2 != null) {
                            Intrinsics.checkNotNull(inputOverlayDrawableButton2);
                            inputOverlayDrawableButton2.onConfigureTouch(motionEvent);
                            break;
                        }
                    } else if (action3 != 5) {
                        if (action3 != 6) {
                        }
                    }
                }
                InputOverlayDrawableButton inputOverlayDrawableButton3 = this.buttonBeingConfigured;
                if (inputOverlayDrawableButton3 == inputOverlayDrawableButton) {
                    Intrinsics.checkNotNull(inputOverlayDrawableButton3);
                    String id2 = inputOverlayDrawableButton3.getOverlayControlData().getId();
                    InputOverlayDrawableButton inputOverlayDrawableButton4 = this.buttonBeingConfigured;
                    Intrinsics.checkNotNull(inputOverlayDrawableButton4);
                    int centerX3 = inputOverlayDrawableButton4.getBounds().centerX();
                    InputOverlayDrawableButton inputOverlayDrawableButton5 = this.buttonBeingConfigured;
                    Intrinsics.checkNotNull(inputOverlayDrawableButton5);
                    saveControlPosition(id2, centerX3, inputOverlayDrawableButton5.getBounds().centerY(), this.layout);
                    this.buttonBeingConfigured = null;
                }
            }
            if (this.buttonBeingConfigured == null && inputOverlayDrawableButton.getBounds().contains(x, y)) {
                this.buttonBeingConfigured = inputOverlayDrawableButton;
                Intrinsics.checkNotNull(inputOverlayDrawableButton);
                inputOverlayDrawableButton.onConfigureTouch(motionEvent);
            }
        }
        invalidate();
        return true;
    }

    private final void playHaptics(MotionEvent motionEvent) {
        if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.HAPTIC_FEEDBACK, false, 1, null)) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 5) {
                        if (actionMasked != 6) {
                            return;
                        }
                    }
                }
                performHapticFeedback(8);
                return;
            }
            performHapticFeedback(1);
        }
    }

    private final void populateDefaultConfig() {
        int collectionSizeOrDefault;
        EnumEntries entries = OverlayControl.getEntries();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(entries, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<E> it = entries.iterator();
        while (it.hasNext()) {
            arrayList.add(((OverlayControl) it.next()).toOverlayControlData());
        }
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        nativeConfig.setOverlayControlData((OverlayControlData[]) arrayList.toArray(new OverlayControlData[0]));
        nativeConfig.saveGlobalConfig();
    }

    private final void saveControlPosition(String str, int i, int i2, OverlayLayout overlayLayout) {
        OverlayControlData overlayControlData;
        Companion companion = Companion;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Pair safeScreenSize = companion.getSafeScreenSize(context, new Pair(Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight())));
        Point point = (Point) safeScreenSize.getFirst();
        Point point2 = (Point) safeScreenSize.getSecond();
        OverlayControlData[] overlayControlData2 = NativeConfig.INSTANCE.getOverlayControlData();
        int length = overlayControlData2.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                overlayControlData = null;
                break;
            }
            overlayControlData = overlayControlData2[i3];
            if (Intrinsics.areEqual(overlayControlData.getId(), str)) {
                break;
            } else {
                i3++;
            }
        }
        Pair pair = new Pair(Double.valueOf((i - point.x) / point2.x), Double.valueOf((i2 - point.y) / point2.y));
        int i4 = WhenMappings.$EnumSwitchMapping$1[overlayLayout.ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                if (i4 == 3 && overlayControlData != null) {
                    overlayControlData.setFoldablePosition(pair);
                }
            } else if (overlayControlData != null) {
                overlayControlData.setPortraitPosition(pair);
            }
        } else if (overlayControlData != null) {
            overlayControlData.setLandscapePosition(pair);
        }
        NativeConfig.INSTANCE.setOverlayControlData(overlayControlData2);
    }

    @Override // android.view.SurfaceView, android.view.View
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.draw(canvas);
        Iterator it = this.overlayButtons.iterator();
        while (it.hasNext()) {
            ((InputOverlayDrawableButton) it.next()).draw(canvas);
        }
        Iterator it2 = this.overlayDpads.iterator();
        while (it2.hasNext()) {
            ((InputOverlayDrawableDpad) it2.next()).draw(canvas);
        }
        Iterator it3 = this.overlayJoysticks.iterator();
        while (it3.hasNext()) {
            ((InputOverlayDrawableJoystick) it3.next()).draw(canvas);
        }
    }

    public final OverlayLayout getLayout() {
        return this.layout;
    }

    @Override // android.view.View
    public boolean isInEditMode() {
        return this.inEditMode;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WindowInsets rootWindowInsets = getRootWindowInsets();
        Intrinsics.checkNotNullExpressionValue(rootWindowInsets, "getRootWindowInsets(...)");
        this.windowInsets = rootWindowInsets;
        OverlayControlData[] overlayControlData = NativeConfig.INSTANCE.getOverlayControlData();
        if (overlayControlData.length == 0) {
            populateDefaultConfig();
        } else {
            checkForNewControls(overlayControlData);
        }
        refreshControls();
        setOnTouchListener(this);
        setWillNotDraw(false);
        requestFocus();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.inEditMode) {
            return onTouchWhileEditing(event);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[NativeInput.INSTANCE.getStyleIndex(0).ordinal()] == 1 ? 8 : 0;
        boolean z = false;
        for (InputOverlayDrawableButton inputOverlayDrawableButton : this.overlayButtons) {
            if (inputOverlayDrawableButton.updateStatus(event)) {
                NativeInput.INSTANCE.onOverlayButtonEvent(i, inputOverlayDrawableButton.getButton(), inputOverlayDrawableButton.getStatus());
                playHaptics(event);
                z = true;
            }
        }
        for (InputOverlayDrawableDpad inputOverlayDrawableDpad : this.overlayDpads) {
            if (inputOverlayDrawableDpad.updateStatus(event, AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.DPAD_SLIDE, false, 1, null))) {
                NativeInput nativeInput = NativeInput.INSTANCE;
                nativeInput.onOverlayButtonEvent(i, inputOverlayDrawableDpad.getUp(), inputOverlayDrawableDpad.getUpStatus());
                nativeInput.onOverlayButtonEvent(i, inputOverlayDrawableDpad.getDown(), inputOverlayDrawableDpad.getDownStatus());
                nativeInput.onOverlayButtonEvent(i, inputOverlayDrawableDpad.getLeft(), inputOverlayDrawableDpad.getLeftStatus());
                nativeInput.onOverlayButtonEvent(i, inputOverlayDrawableDpad.getRight(), inputOverlayDrawableDpad.getRightStatus());
                playHaptics(event);
                z = true;
            }
        }
        for (InputOverlayDrawableJoystick inputOverlayDrawableJoystick : this.overlayJoysticks) {
            if (inputOverlayDrawableJoystick.updateStatus(event)) {
                NativeInput nativeInput2 = NativeInput.INSTANCE;
                nativeInput2.onOverlayJoystickEvent(i, inputOverlayDrawableJoystick.getJoystick(), inputOverlayDrawableJoystick.getXAxis(), inputOverlayDrawableJoystick.getRealYAxis());
                nativeInput2.onOverlayButtonEvent(i, inputOverlayDrawableJoystick.getButton(), inputOverlayDrawableJoystick.getButtonStatus());
                playHaptics(event);
                z = true;
            }
        }
        if (z) {
            invalidate();
        }
        if (!AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.TOUCHSCREEN, false, 1, null)) {
            return true;
        }
        int actionIndex = event.getActionIndex();
        int x = (int) event.getX(actionIndex);
        int y = (int) event.getY(actionIndex);
        int pointerId = event.getPointerId(actionIndex);
        int action = event.getAction() & 255;
        boolean z2 = action == 0 || action == 5;
        boolean z3 = action == 2;
        boolean z4 = action == 1 || action == 6;
        if (z2 && !isTouchInputConsumed(pointerId)) {
            NativeInput.INSTANCE.onTouchPressed(pointerId, x, y);
        }
        if (z3) {
            int pointerCount = event.getPointerCount();
            for (int i2 = 0; i2 < pointerCount; i2++) {
                int pointerId2 = event.getPointerId(i2);
                if (!isTouchInputConsumed(pointerId2)) {
                    NativeInput.INSTANCE.onTouchMoved(pointerId2, event.getX(i2), event.getY(i2));
                }
            }
        }
        if (z4 && !isTouchInputConsumed(pointerId)) {
            NativeInput.INSTANCE.onTouchReleased(pointerId);
        }
        return true;
    }

    public final void refreshControls() {
        this.overlayButtons.clear();
        this.overlayDpads.clear();
        this.overlayJoysticks.clear();
        if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.SHOW_INPUT_OVERLAY, false, 1, null)) {
            addOverlayControls(this.layout);
        }
        invalidate();
    }

    public final void resetLayoutVisibilityAndPlacement() {
        boolean z;
        defaultOverlayPositionByLayout(this.layout);
        OverlayControlData[] overlayControlData = NativeConfig.INSTANCE.getOverlayControlData();
        for (OverlayControlData overlayControlData2 : overlayControlData) {
            OverlayControl from = OverlayControl.Companion.from(overlayControlData2.getId());
            if (from != null) {
                z = true;
                if (from.getDefaultVisibility()) {
                    overlayControlData2.setEnabled(z);
                }
            }
            z = false;
            overlayControlData2.setEnabled(z);
        }
        NativeConfig.INSTANCE.setOverlayControlData(overlayControlData);
        refreshControls();
    }

    public final void setIsInEditMode(boolean z) {
        this.inEditMode = z;
    }

    public final void setLayout(OverlayLayout overlayLayout) {
        Intrinsics.checkNotNullParameter(overlayLayout, "<set-?>");
        this.layout = overlayLayout;
    }
}
