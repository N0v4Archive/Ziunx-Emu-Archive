package org.yuzu.yuzu_emu.utils;

import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import org.yuzu.yuzu_emu.R$style;
import org.yuzu.yuzu_emu.features.settings.model.AbstractBooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.BooleanSetting;
import org.yuzu.yuzu_emu.features.settings.model.IntSetting;
import org.yuzu.yuzu_emu.ui.main.ThemeProvider;

/* loaded from: classes.dex */
public final class ThemeHelper {
    public static final ThemeHelper INSTANCE = new ThemeHelper();

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Theme.values().length];
            try {
                iArr[Theme.Default.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Theme.MaterialYou.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ThemeHelper() {
    }

    private final boolean isNightMode(AppCompatActivity appCompatActivity) {
        int i = appCompatActivity.getResources().getConfiguration().uiMode & 48;
        return i != 16 && i == 32;
    }

    private final void setDarkModeSystemBars(WindowInsetsControllerCompat windowInsetsControllerCompat) {
        windowInsetsControllerCompat.setAppearanceLightStatusBars(false);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(false);
    }

    private final void setLightModeSystemBars(WindowInsetsControllerCompat windowInsetsControllerCompat) {
        windowInsetsControllerCompat.setAppearanceLightStatusBars(true);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(true);
    }

    public final int getColorWithOpacity(int i, float f) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(f * Color.alpha(i));
        return Color.argb(roundToInt, Color.red(i), Color.green(i), Color.blue(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setCorrectTheme(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ThemeProvider themeProvider = (ThemeProvider) activity;
        int themeId = themeProvider.getThemeId();
        setTheme(activity);
        if (themeId != themeProvider.getThemeId()) {
            activity.recreate();
        }
    }

    public final void setTheme(AppCompatActivity activity) {
        int i;
        Intrinsics.checkNotNullParameter(activity, "activity");
        setThemeMode(activity);
        int i2 = WhenMappings.$EnumSwitchMapping$0[Theme.Companion.from(AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.THEME, false, 1, null)).ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                if (Build.VERSION.SDK_INT >= 31) {
                    i = R$style.Theme_Yuzu_Main_MaterialYou;
                    activity.setTheme(i);
                }
            }
            if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.BLACK_BACKGROUNDS, false, 1, null) || !isNightMode(activity)) {
            }
            activity.setTheme(R$style.ThemeOverlay_Yuzu_Dark);
            return;
        }
        i = R$style.Theme_Yuzu_Main;
        activity.setTheme(i);
        if (AbstractBooleanSetting.DefaultImpls.getBoolean$default(BooleanSetting.BLACK_BACKGROUNDS, false, 1, null)) {
        }
    }

    public final void setThemeMode(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int int$default = AbstractIntSetting.DefaultImpls.getInt$default(IntSetting.THEME_MODE, false, 1, null);
        activity.getDelegate().setLocalNightMode(int$default);
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
        Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(...)");
        if (int$default != -1) {
            if (int$default != 1) {
                if (int$default != 2) {
                    return;
                }
                setDarkModeSystemBars(insetsController);
                return;
            }
            setLightModeSystemBars(insetsController);
        }
        boolean isNightMode = isNightMode(activity);
        if (isNightMode) {
            if (!isNightMode) {
                return;
            }
            setDarkModeSystemBars(insetsController);
            return;
        }
        setLightModeSystemBars(insetsController);
    }
}
