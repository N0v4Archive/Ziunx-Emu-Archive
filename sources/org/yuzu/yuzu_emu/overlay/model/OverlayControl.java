package org.yuzu.yuzu_emu.overlay.model;

import android.content.res.Resources;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$integer;
import org.yuzu.yuzu_emu.YuzuApplication;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class OverlayControl {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OverlayControl[] $VALUES;
    public static final Companion Companion;
    private static final Lazy map$delegate;
    private final Pair defaultFoldablePositionResources;
    private final Pair defaultLandscapePositionResources;
    private final Pair defaultPortraitPositionResources;
    private final boolean defaultVisibility;
    private final String id;
    public static final OverlayControl BUTTON_A = new OverlayControl("BUTTON_A", 0, "button_a", true, new Pair(Integer.valueOf(R$integer.BUTTON_A_X), Integer.valueOf(R$integer.BUTTON_A_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_A_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_A_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_A_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_A_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_B = new OverlayControl("BUTTON_B", 1, "button_b", true, new Pair(Integer.valueOf(R$integer.BUTTON_B_X), Integer.valueOf(R$integer.BUTTON_B_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_B_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_B_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_B_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_B_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_X = new OverlayControl("BUTTON_X", 2, "button_x", true, new Pair(Integer.valueOf(R$integer.BUTTON_X_X), Integer.valueOf(R$integer.BUTTON_X_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_X_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_X_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_X_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_X_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_Y = new OverlayControl("BUTTON_Y", 3, "button_y", true, new Pair(Integer.valueOf(R$integer.BUTTON_Y_X), Integer.valueOf(R$integer.BUTTON_Y_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_Y_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_Y_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_Y_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_Y_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_PLUS = new OverlayControl("BUTTON_PLUS", 4, "button_plus", true, new Pair(Integer.valueOf(R$integer.BUTTON_PLUS_X), Integer.valueOf(R$integer.BUTTON_PLUS_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_PLUS_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_PLUS_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_PLUS_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_PLUS_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_MINUS = new OverlayControl("BUTTON_MINUS", 5, "button_minus", true, new Pair(Integer.valueOf(R$integer.BUTTON_MINUS_X), Integer.valueOf(R$integer.BUTTON_MINUS_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_MINUS_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_MINUS_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_MINUS_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_MINUS_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_HOME = new OverlayControl("BUTTON_HOME", 6, "button_home", false, new Pair(Integer.valueOf(R$integer.BUTTON_HOME_X), Integer.valueOf(R$integer.BUTTON_HOME_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_HOME_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_HOME_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_HOME_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_HOME_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_CAPTURE = new OverlayControl("BUTTON_CAPTURE", 7, "button_capture", false, new Pair(Integer.valueOf(R$integer.BUTTON_CAPTURE_X), Integer.valueOf(R$integer.BUTTON_CAPTURE_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_CAPTURE_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_CAPTURE_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_CAPTURE_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_CAPTURE_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_L = new OverlayControl("BUTTON_L", 8, "button_l", true, new Pair(Integer.valueOf(R$integer.BUTTON_L_X), Integer.valueOf(R$integer.BUTTON_L_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_L_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_L_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_L_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_L_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_R = new OverlayControl("BUTTON_R", 9, "button_r", true, new Pair(Integer.valueOf(R$integer.BUTTON_R_X), Integer.valueOf(R$integer.BUTTON_R_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_R_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_R_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_R_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_R_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_ZL = new OverlayControl("BUTTON_ZL", 10, "button_zl", true, new Pair(Integer.valueOf(R$integer.BUTTON_ZL_X), Integer.valueOf(R$integer.BUTTON_ZL_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_ZL_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_ZL_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_ZL_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_ZL_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_ZR = new OverlayControl("BUTTON_ZR", 11, "button_zr", true, new Pair(Integer.valueOf(R$integer.BUTTON_ZR_X), Integer.valueOf(R$integer.BUTTON_ZR_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_ZR_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_ZR_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_ZR_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_ZR_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_STICK_L = new OverlayControl("BUTTON_STICK_L", 12, "button_stick_l", true, new Pair(Integer.valueOf(R$integer.BUTTON_STICK_L_X), Integer.valueOf(R$integer.BUTTON_STICK_L_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_STICK_L_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_STICK_L_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_STICK_L_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_STICK_L_Y_FOLDABLE)));
    public static final OverlayControl BUTTON_STICK_R = new OverlayControl("BUTTON_STICK_R", 13, "button_stick_r", true, new Pair(Integer.valueOf(R$integer.BUTTON_STICK_R_X), Integer.valueOf(R$integer.BUTTON_STICK_R_Y)), new Pair(Integer.valueOf(R$integer.BUTTON_STICK_R_X_PORTRAIT), Integer.valueOf(R$integer.BUTTON_STICK_R_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.BUTTON_STICK_R_X_FOLDABLE), Integer.valueOf(R$integer.BUTTON_STICK_R_Y_FOLDABLE)));
    public static final OverlayControl STICK_L = new OverlayControl("STICK_L", 14, "stick_l", true, new Pair(Integer.valueOf(R$integer.STICK_L_X), Integer.valueOf(R$integer.STICK_L_Y)), new Pair(Integer.valueOf(R$integer.STICK_L_X_PORTRAIT), Integer.valueOf(R$integer.STICK_L_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.STICK_L_X_FOLDABLE), Integer.valueOf(R$integer.STICK_L_Y_FOLDABLE)));
    public static final OverlayControl STICK_R = new OverlayControl("STICK_R", 15, "stick_r", true, new Pair(Integer.valueOf(R$integer.STICK_R_X), Integer.valueOf(R$integer.STICK_R_Y)), new Pair(Integer.valueOf(R$integer.STICK_R_X_PORTRAIT), Integer.valueOf(R$integer.STICK_R_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.STICK_R_X_FOLDABLE), Integer.valueOf(R$integer.STICK_R_Y_FOLDABLE)));
    public static final OverlayControl COMBINED_DPAD = new OverlayControl("COMBINED_DPAD", 16, "combined_dpad", true, new Pair(Integer.valueOf(R$integer.COMBINED_DPAD_X), Integer.valueOf(R$integer.COMBINED_DPAD_Y)), new Pair(Integer.valueOf(R$integer.COMBINED_DPAD_X_PORTRAIT), Integer.valueOf(R$integer.COMBINED_DPAD_Y_PORTRAIT)), new Pair(Integer.valueOf(R$integer.COMBINED_DPAD_X_FOLDABLE), Integer.valueOf(R$integer.COMBINED_DPAD_Y_FOLDABLE)));

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OverlayControl from(String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            return (OverlayControl) getMap().get(id);
        }

        public final HashMap getMap() {
            return (HashMap) OverlayControl.map$delegate.getValue();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverlayLayout.values().length];
            try {
                iArr[OverlayLayout.Landscape.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OverlayLayout.Portrait.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OverlayLayout.Foldable.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ OverlayControl[] $values() {
        return new OverlayControl[]{BUTTON_A, BUTTON_B, BUTTON_X, BUTTON_Y, BUTTON_PLUS, BUTTON_MINUS, BUTTON_HOME, BUTTON_CAPTURE, BUTTON_L, BUTTON_R, BUTTON_ZL, BUTTON_ZR, BUTTON_STICK_L, BUTTON_STICK_R, STICK_L, STICK_R, COMBINED_DPAD};
    }

    static {
        Lazy lazy;
        OverlayControl[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: org.yuzu.yuzu_emu.overlay.model.OverlayControl$Companion$map$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap invoke() {
                HashMap hashMap = new HashMap();
                for (OverlayControl overlayControl : OverlayControl.getEntries()) {
                    hashMap.put(overlayControl.getId(), overlayControl);
                }
                return hashMap;
            }
        });
        map$delegate = lazy;
    }

    private OverlayControl(String str, int i, String str2, boolean z, Pair pair, Pair pair2, Pair pair3) {
        this.id = str2;
        this.defaultVisibility = z;
        this.defaultLandscapePositionResources = pair;
        this.defaultPortraitPositionResources = pair2;
        this.defaultFoldablePositionResources = pair3;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static OverlayControl valueOf(String str) {
        return (OverlayControl) Enum.valueOf(OverlayControl.class, str);
    }

    public static OverlayControl[] values() {
        return (OverlayControl[]) $VALUES.clone();
    }

    public final Pair getDefaultPositionForLayout(OverlayLayout layout) {
        Pair pair;
        Intrinsics.checkNotNullParameter(layout, "layout");
        Resources resources = YuzuApplication.Companion.getAppContext().getResources();
        int i = WhenMappings.$EnumSwitchMapping$0[layout.ordinal()];
        if (i == 1) {
            pair = new Pair(Integer.valueOf(resources.getInteger(((Number) this.defaultLandscapePositionResources.getFirst()).intValue())), Integer.valueOf(resources.getInteger(((Number) this.defaultLandscapePositionResources.getSecond()).intValue())));
        } else if (i == 2) {
            pair = new Pair(Integer.valueOf(resources.getInteger(((Number) this.defaultPortraitPositionResources.getFirst()).intValue())), Integer.valueOf(resources.getInteger(((Number) this.defaultPortraitPositionResources.getSecond()).intValue())));
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            pair = new Pair(Integer.valueOf(resources.getInteger(((Number) this.defaultFoldablePositionResources.getFirst()).intValue())), Integer.valueOf(resources.getInteger(((Number) this.defaultFoldablePositionResources.getSecond()).intValue())));
        }
        double d = 1000;
        return new Pair(Double.valueOf(((Number) pair.getFirst()).intValue() / d), Double.valueOf(((Number) pair.getSecond()).intValue() / d));
    }

    public final boolean getDefaultVisibility() {
        return this.defaultVisibility;
    }

    public final String getId() {
        return this.id;
    }

    public final OverlayControlData toOverlayControlData() {
        return new OverlayControlData(this.id, this.defaultVisibility, getDefaultPositionForLayout(OverlayLayout.Landscape), getDefaultPositionForLayout(OverlayLayout.Portrait), getDefaultPositionForLayout(OverlayLayout.Foldable));
    }
}
