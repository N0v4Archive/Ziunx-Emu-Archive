package org.yuzu.yuzu_emu.applets.keyboard;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Keep;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.Serializable;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.activities.EmulationActivity;
import org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard;
import org.yuzu.yuzu_emu.applets.keyboard.ui.KeyboardDialogFragment;

@Keep
/* loaded from: classes.dex */
public final class SoftwareKeyboard {
    public static KeyboardData data;
    public static final SoftwareKeyboard INSTANCE = new SoftwareKeyboard();
    private static final Object dataLock = new Object();

    @Keep
    /* loaded from: classes.dex */
    public static final class KeyboardConfig implements Serializable {
        private boolean disable_cancel_button;
        private boolean enable_backspace_button;
        private boolean enable_return_button;
        private String guide_text;
        private String header_text;
        private int initial_cursor_position;
        private String initial_text;
        private int key_disable_flags;
        private short left_optional_symbol_key;
        private int max_text_length;
        private int min_text_length;
        private String ok_text;
        private int password_mode;
        private short right_optional_symbol_key;
        private String sub_text;
        private int text_draw_type;
        private int type;
        private boolean use_blur_background;

        public KeyboardConfig() {
            this(null, null, null, null, null, (short) 0, (short) 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, 262143, null);
        }

        public KeyboardConfig(String str, String str2, String str3, String str4, String str5, short s, short s2, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, boolean z3, boolean z4) {
            this.ok_text = str;
            this.header_text = str2;
            this.sub_text = str3;
            this.guide_text = str4;
            this.initial_text = str5;
            this.left_optional_symbol_key = s;
            this.right_optional_symbol_key = s2;
            this.max_text_length = i;
            this.min_text_length = i2;
            this.initial_cursor_position = i3;
            this.type = i4;
            this.password_mode = i5;
            this.text_draw_type = i6;
            this.key_disable_flags = i7;
            this.use_blur_background = z;
            this.enable_backspace_button = z2;
            this.enable_return_button = z3;
            this.disable_cancel_button = z4;
        }

        public /* synthetic */ KeyboardConfig(String str, String str2, String str3, String str4, String str5, short s, short s2, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, boolean z3, boolean z4, int i8, DefaultConstructorMarker defaultConstructorMarker) {
            this((i8 & 1) != 0 ? null : str, (i8 & 2) != 0 ? null : str2, (i8 & 4) != 0 ? null : str3, (i8 & 8) != 0 ? null : str4, (i8 & 16) == 0 ? str5 : null, (i8 & 32) != 0 ? (short) 0 : s, (i8 & 64) != 0 ? (short) 0 : s2, (i8 & 128) != 0 ? 0 : i, (i8 & 256) != 0 ? 0 : i2, (i8 & 512) != 0 ? 0 : i3, (i8 & 1024) != 0 ? 0 : i4, (i8 & 2048) != 0 ? 0 : i5, (i8 & 4096) != 0 ? 0 : i6, (i8 & 8192) != 0 ? 0 : i7, (i8 & 16384) != 0 ? false : z, (i8 & 32768) != 0 ? false : z2, (i8 & 65536) != 0 ? false : z3, (i8 & 131072) != 0 ? false : z4);
        }

        public final String component1() {
            return this.ok_text;
        }

        public final int component10() {
            return this.initial_cursor_position;
        }

        public final int component11() {
            return this.type;
        }

        public final int component12() {
            return this.password_mode;
        }

        public final int component13() {
            return this.text_draw_type;
        }

        public final int component14() {
            return this.key_disable_flags;
        }

        public final boolean component15() {
            return this.use_blur_background;
        }

        public final boolean component16() {
            return this.enable_backspace_button;
        }

        public final boolean component17() {
            return this.enable_return_button;
        }

        public final boolean component18() {
            return this.disable_cancel_button;
        }

        public final String component2() {
            return this.header_text;
        }

        public final String component3() {
            return this.sub_text;
        }

        public final String component4() {
            return this.guide_text;
        }

        public final String component5() {
            return this.initial_text;
        }

        public final short component6() {
            return this.left_optional_symbol_key;
        }

        public final short component7() {
            return this.right_optional_symbol_key;
        }

        public final int component8() {
            return this.max_text_length;
        }

        public final int component9() {
            return this.min_text_length;
        }

        public final KeyboardConfig copy(String str, String str2, String str3, String str4, String str5, short s, short s2, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, boolean z3, boolean z4) {
            return new KeyboardConfig(str, str2, str3, str4, str5, s, s2, i, i2, i3, i4, i5, i6, i7, z, z2, z3, z4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof KeyboardConfig)) {
                return false;
            }
            KeyboardConfig keyboardConfig = (KeyboardConfig) obj;
            return Intrinsics.areEqual(this.ok_text, keyboardConfig.ok_text) && Intrinsics.areEqual(this.header_text, keyboardConfig.header_text) && Intrinsics.areEqual(this.sub_text, keyboardConfig.sub_text) && Intrinsics.areEqual(this.guide_text, keyboardConfig.guide_text) && Intrinsics.areEqual(this.initial_text, keyboardConfig.initial_text) && this.left_optional_symbol_key == keyboardConfig.left_optional_symbol_key && this.right_optional_symbol_key == keyboardConfig.right_optional_symbol_key && this.max_text_length == keyboardConfig.max_text_length && this.min_text_length == keyboardConfig.min_text_length && this.initial_cursor_position == keyboardConfig.initial_cursor_position && this.type == keyboardConfig.type && this.password_mode == keyboardConfig.password_mode && this.text_draw_type == keyboardConfig.text_draw_type && this.key_disable_flags == keyboardConfig.key_disable_flags && this.use_blur_background == keyboardConfig.use_blur_background && this.enable_backspace_button == keyboardConfig.enable_backspace_button && this.enable_return_button == keyboardConfig.enable_return_button && this.disable_cancel_button == keyboardConfig.disable_cancel_button;
        }

        public final boolean getDisable_cancel_button() {
            return this.disable_cancel_button;
        }

        public final boolean getEnable_backspace_button() {
            return this.enable_backspace_button;
        }

        public final boolean getEnable_return_button() {
            return this.enable_return_button;
        }

        public final String getGuide_text() {
            return this.guide_text;
        }

        public final String getHeader_text() {
            return this.header_text;
        }

        public final int getInitial_cursor_position() {
            return this.initial_cursor_position;
        }

        public final String getInitial_text() {
            return this.initial_text;
        }

        public final int getKey_disable_flags() {
            return this.key_disable_flags;
        }

        public final short getLeft_optional_symbol_key() {
            return this.left_optional_symbol_key;
        }

        public final int getMax_text_length() {
            return this.max_text_length;
        }

        public final int getMin_text_length() {
            return this.min_text_length;
        }

        public final String getOk_text() {
            return this.ok_text;
        }

        public final int getPassword_mode() {
            return this.password_mode;
        }

        public final short getRight_optional_symbol_key() {
            return this.right_optional_symbol_key;
        }

        public final String getSub_text() {
            return this.sub_text;
        }

        public final int getText_draw_type() {
            return this.text_draw_type;
        }

        public final int getType() {
            return this.type;
        }

        public final boolean getUse_blur_background() {
            return this.use_blur_background;
        }

        public int hashCode() {
            String str = this.ok_text;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.header_text;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.sub_text;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.guide_text;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.initial_text;
            return ((((((((((((((((((((((((((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + Short.hashCode(this.left_optional_symbol_key)) * 31) + Short.hashCode(this.right_optional_symbol_key)) * 31) + Integer.hashCode(this.max_text_length)) * 31) + Integer.hashCode(this.min_text_length)) * 31) + Integer.hashCode(this.initial_cursor_position)) * 31) + Integer.hashCode(this.type)) * 31) + Integer.hashCode(this.password_mode)) * 31) + Integer.hashCode(this.text_draw_type)) * 31) + Integer.hashCode(this.key_disable_flags)) * 31) + Boolean.hashCode(this.use_blur_background)) * 31) + Boolean.hashCode(this.enable_backspace_button)) * 31) + Boolean.hashCode(this.enable_return_button)) * 31) + Boolean.hashCode(this.disable_cancel_button);
        }

        public final void setDisable_cancel_button(boolean z) {
            this.disable_cancel_button = z;
        }

        public final void setEnable_backspace_button(boolean z) {
            this.enable_backspace_button = z;
        }

        public final void setEnable_return_button(boolean z) {
            this.enable_return_button = z;
        }

        public final void setGuide_text(String str) {
            this.guide_text = str;
        }

        public final void setHeader_text(String str) {
            this.header_text = str;
        }

        public final void setInitial_cursor_position(int i) {
            this.initial_cursor_position = i;
        }

        public final void setInitial_text(String str) {
            this.initial_text = str;
        }

        public final void setKey_disable_flags(int i) {
            this.key_disable_flags = i;
        }

        public final void setLeft_optional_symbol_key(short s) {
            this.left_optional_symbol_key = s;
        }

        public final void setMax_text_length(int i) {
            this.max_text_length = i;
        }

        public final void setMin_text_length(int i) {
            this.min_text_length = i;
        }

        public final void setOk_text(String str) {
            this.ok_text = str;
        }

        public final void setPassword_mode(int i) {
            this.password_mode = i;
        }

        public final void setRight_optional_symbol_key(short s) {
            this.right_optional_symbol_key = s;
        }

        public final void setSub_text(String str) {
            this.sub_text = str;
        }

        public final void setText_draw_type(int i) {
            this.text_draw_type = i;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final void setUse_blur_background(boolean z) {
            this.use_blur_background = z;
        }

        public String toString() {
            String str = this.ok_text;
            String str2 = this.header_text;
            String str3 = this.sub_text;
            String str4 = this.guide_text;
            String str5 = this.initial_text;
            short s = this.left_optional_symbol_key;
            short s2 = this.right_optional_symbol_key;
            return "KeyboardConfig(ok_text=" + str + ", header_text=" + str2 + ", sub_text=" + str3 + ", guide_text=" + str4 + ", initial_text=" + str5 + ", left_optional_symbol_key=" + ((int) s) + ", right_optional_symbol_key=" + ((int) s2) + ", max_text_length=" + this.max_text_length + ", min_text_length=" + this.min_text_length + ", initial_cursor_position=" + this.initial_cursor_position + ", type=" + this.type + ", password_mode=" + this.password_mode + ", text_draw_type=" + this.text_draw_type + ", key_disable_flags=" + this.key_disable_flags + ", use_blur_background=" + this.use_blur_background + ", enable_backspace_button=" + this.enable_backspace_button + ", enable_return_button=" + this.enable_return_button + ", disable_cancel_button=" + this.disable_cancel_button + ")";
        }
    }

    @Keep
    /* loaded from: classes.dex */
    public static final class KeyboardData {
        private int result;
        private String text;

        public KeyboardData(int i, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.result = i;
            this.text = text;
        }

        public static /* synthetic */ KeyboardData copy$default(KeyboardData keyboardData, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = keyboardData.result;
            }
            if ((i2 & 2) != 0) {
                str = keyboardData.text;
            }
            return keyboardData.copy(i, str);
        }

        public final int component1() {
            return this.result;
        }

        public final String component2() {
            return this.text;
        }

        public final KeyboardData copy(int i, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new KeyboardData(i, text);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof KeyboardData)) {
                return false;
            }
            KeyboardData keyboardData = (KeyboardData) obj;
            return this.result == keyboardData.result && Intrinsics.areEqual(this.text, keyboardData.text);
        }

        public final int getResult() {
            return this.result;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return (Integer.hashCode(this.result) * 31) + this.text.hashCode();
        }

        public final void setResult(int i) {
            this.result = i;
        }

        public final void setText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.text = str;
        }

        public String toString() {
            return "KeyboardData(result=" + this.result + ", text=" + this.text + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class SwkbdPasswordMode {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ SwkbdPasswordMode[] $VALUES;
        public static final SwkbdPasswordMode Disabled = new SwkbdPasswordMode("Disabled", 0);
        public static final SwkbdPasswordMode Enabled = new SwkbdPasswordMode("Enabled", 1);

        private static final /* synthetic */ SwkbdPasswordMode[] $values() {
            return new SwkbdPasswordMode[]{Disabled, Enabled};
        }

        static {
            SwkbdPasswordMode[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private SwkbdPasswordMode(String str, int i) {
        }

        public static SwkbdPasswordMode valueOf(String str) {
            return (SwkbdPasswordMode) Enum.valueOf(SwkbdPasswordMode.class, str);
        }

        public static SwkbdPasswordMode[] values() {
            return (SwkbdPasswordMode[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class SwkbdResult {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ SwkbdResult[] $VALUES;
        public static final SwkbdResult Ok = new SwkbdResult("Ok", 0);
        public static final SwkbdResult Cancel = new SwkbdResult("Cancel", 1);

        private static final /* synthetic */ SwkbdResult[] $values() {
            return new SwkbdResult[]{Ok, Cancel};
        }

        static {
            SwkbdResult[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private SwkbdResult(String str, int i) {
        }

        public static SwkbdResult valueOf(String str) {
            return (SwkbdResult) Enum.valueOf(SwkbdResult.class, str);
        }

        public static SwkbdResult[] values() {
            return (SwkbdResult[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class SwkbdType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ SwkbdType[] $VALUES;
        public static final SwkbdType Normal = new SwkbdType("Normal", 0);
        public static final SwkbdType NumberPad = new SwkbdType("NumberPad", 1);
        public static final SwkbdType Qwerty = new SwkbdType("Qwerty", 2);
        public static final SwkbdType Unknown3 = new SwkbdType("Unknown3", 3);
        public static final SwkbdType Latin = new SwkbdType("Latin", 4);
        public static final SwkbdType SimplifiedChinese = new SwkbdType("SimplifiedChinese", 5);
        public static final SwkbdType TraditionalChinese = new SwkbdType("TraditionalChinese", 6);
        public static final SwkbdType Korean = new SwkbdType("Korean", 7);

        private static final /* synthetic */ SwkbdType[] $values() {
            return new SwkbdType[]{Normal, NumberPad, Qwerty, Unknown3, Latin, SimplifiedChinese, TraditionalChinese, Korean};
        }

        static {
            SwkbdType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private SwkbdType(String str, int i) {
        }

        public static SwkbdType valueOf(String str) {
            return (SwkbdType) Enum.valueOf(SwkbdType.class, str);
        }

        public static SwkbdType[] values() {
            return (SwkbdType[]) $VALUES.clone();
        }
    }

    private SoftwareKeyboard() {
    }

    public static final void executeInline(final KeyboardConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Object obj = NativeLibrary.sEmulationActivity.get();
        Intrinsics.checkNotNull(obj);
        ((EmulationActivity) obj).runOnUiThread(new Runnable() { // from class: org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SoftwareKeyboard.executeInline$lambda$2(SoftwareKeyboard.KeyboardConfig.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeInline$lambda$2(KeyboardConfig config) {
        Intrinsics.checkNotNullParameter(config, "$config");
        INSTANCE.executeInlineImpl(config);
    }

    private final void executeInlineImpl(KeyboardConfig keyboardConfig) {
        EmulationActivity emulationActivity = (EmulationActivity) NativeLibrary.sEmulationActivity.get();
        Intrinsics.checkNotNull(emulationActivity);
        final View findViewById = emulationActivity.findViewById(R$id.surface_input_overlay);
        Object systemService = findViewById.getContext().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).showSoftInput(findViewById, 2);
        Looper myLooper = Looper.myLooper();
        Intrinsics.checkNotNull(myLooper);
        final Handler handler = new Handler(myLooper);
        final int i = 500;
        handler.postDelayed(new Runnable() { // from class: org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard$executeInlineImpl$1
            @Override // java.lang.Runnable
            public void run() {
                int ime;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(findViewById);
                Intrinsics.checkNotNull(rootWindowInsets);
                ime = WindowInsets.Type.ime();
                if (rootWindowInsets.isVisible(ime)) {
                    handler.postDelayed(this, i);
                } else {
                    NativeLibrary.INSTANCE.submitInlineKeyboardInput(66);
                }
            }
        }, 500);
    }

    public static final KeyboardData executeNormal(final KeyboardConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Object obj = NativeLibrary.sEmulationActivity.get();
        Intrinsics.checkNotNull(obj);
        ((EmulationActivity) obj).runOnUiThread(new Runnable() { // from class: org.yuzu.yuzu_emu.applets.keyboard.SoftwareKeyboard$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SoftwareKeyboard.executeNormal$lambda$0(SoftwareKeyboard.KeyboardConfig.this);
            }
        });
        Object obj2 = dataLock;
        synchronized (obj2) {
            obj2.wait();
            Unit unit = Unit.INSTANCE;
        }
        return INSTANCE.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeNormal$lambda$0(KeyboardConfig config) {
        Intrinsics.checkNotNullParameter(config, "$config");
        INSTANCE.executeNormalImpl(config);
    }

    private final void executeNormalImpl(KeyboardConfig keyboardConfig) {
        EmulationActivity emulationActivity = (EmulationActivity) NativeLibrary.sEmulationActivity.get();
        setData(new KeyboardData(SwkbdResult.Cancel.ordinal(), ""));
        KeyboardDialogFragment newInstance = KeyboardDialogFragment.Companion.newInstance(keyboardConfig);
        Intrinsics.checkNotNull(emulationActivity);
        newInstance.show(emulationActivity.getSupportFragmentManager(), "KeyboardDialogFragment");
    }

    public final KeyboardData getData() {
        KeyboardData keyboardData = data;
        if (keyboardData != null) {
            return keyboardData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final Object getDataLock() {
        return dataLock;
    }

    public final void setData(KeyboardData keyboardData) {
        Intrinsics.checkNotNullParameter(keyboardData, "<set-?>");
        data = keyboardData;
    }
}
