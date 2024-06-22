package org.yuzu.yuzu_emu.features.settings.model;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;

/* loaded from: classes.dex */
public final class Settings {
    public static final Settings INSTANCE = new Settings();
    private static final List overlayLayoutSuffixes;
    private static final List overlayPreferences;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class EmulationOrientation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ EmulationOrientation[] $VALUES;
        public static final Companion Companion;

        /* renamed from: int, reason: not valid java name */
        private final int f8int;
        public static final EmulationOrientation Unspecified = new EmulationOrientation("Unspecified", 0, 0);
        public static final EmulationOrientation SensorLandscape = new EmulationOrientation("SensorLandscape", 1, 5);
        public static final EmulationOrientation Landscape = new EmulationOrientation("Landscape", 2, 1);
        public static final EmulationOrientation ReverseLandscape = new EmulationOrientation("ReverseLandscape", 3, 2);
        public static final EmulationOrientation SensorPortrait = new EmulationOrientation("SensorPortrait", 4, 6);
        public static final EmulationOrientation Portrait = new EmulationOrientation("Portrait", 5, 4);
        public static final EmulationOrientation ReversePortrait = new EmulationOrientation("ReversePortrait", 6, 3);

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final EmulationOrientation from(int i) {
                Object obj;
                Iterator<E> it = EmulationOrientation.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((EmulationOrientation) obj).getInt() == i) {
                        break;
                    }
                }
                EmulationOrientation emulationOrientation = (EmulationOrientation) obj;
                return emulationOrientation == null ? EmulationOrientation.Unspecified : emulationOrientation;
            }
        }

        private static final /* synthetic */ EmulationOrientation[] $values() {
            return new EmulationOrientation[]{Unspecified, SensorLandscape, Landscape, ReverseLandscape, SensorPortrait, Portrait, ReversePortrait};
        }

        static {
            EmulationOrientation[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new Companion(null);
        }

        private EmulationOrientation(String str, int i, int i2) {
            this.f8int = i2;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static EmulationOrientation valueOf(String str) {
            return (EmulationOrientation) Enum.valueOf(EmulationOrientation.class, str);
        }

        public static EmulationOrientation[] values() {
            return (EmulationOrientation[]) $VALUES.clone();
        }

        public final int getInt() {
            return this.f8int;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class EmulationVerticalAlignment {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ EmulationVerticalAlignment[] $VALUES;
        public static final Companion Companion;

        /* renamed from: int, reason: not valid java name */
        private final int f9int;
        public static final EmulationVerticalAlignment Top = new EmulationVerticalAlignment("Top", 0, 1);
        public static final EmulationVerticalAlignment Center = new EmulationVerticalAlignment("Center", 1, 0);
        public static final EmulationVerticalAlignment Bottom = new EmulationVerticalAlignment("Bottom", 2, 2);

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final EmulationVerticalAlignment from(int i) {
                Object obj;
                Iterator<E> it = EmulationVerticalAlignment.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((EmulationVerticalAlignment) obj).getInt() == i) {
                        break;
                    }
                }
                EmulationVerticalAlignment emulationVerticalAlignment = (EmulationVerticalAlignment) obj;
                return emulationVerticalAlignment == null ? EmulationVerticalAlignment.Center : emulationVerticalAlignment;
            }
        }

        private static final /* synthetic */ EmulationVerticalAlignment[] $values() {
            return new EmulationVerticalAlignment[]{Top, Center, Bottom};
        }

        static {
            EmulationVerticalAlignment[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new Companion(null);
        }

        private EmulationVerticalAlignment(String str, int i, int i2) {
            this.f9int = i2;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static EmulationVerticalAlignment valueOf(String str) {
            return (EmulationVerticalAlignment) Enum.valueOf(EmulationVerticalAlignment.class, str);
        }

        public static EmulationVerticalAlignment[] values() {
            return (EmulationVerticalAlignment[]) $VALUES.clone();
        }

        public final int getInt() {
            return this.f9int;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class MenuTag {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ MenuTag[] $VALUES;
        public static final MenuTag SECTION_INPUT_PLAYER_EIGHT;
        public static final MenuTag SECTION_INPUT_PLAYER_FIVE;
        public static final MenuTag SECTION_INPUT_PLAYER_FOUR;
        public static final MenuTag SECTION_INPUT_PLAYER_SEVEN;
        public static final MenuTag SECTION_INPUT_PLAYER_SIX;
        public static final MenuTag SECTION_INPUT_PLAYER_THREE;
        private final int titleId;
        public static final MenuTag SECTION_ROOT = new MenuTag("SECTION_ROOT", 0, R$string.advanced_settings);
        public static final MenuTag SECTION_SYSTEM = new MenuTag("SECTION_SYSTEM", 1, R$string.preferences_system);
        public static final MenuTag SECTION_RENDERER = new MenuTag("SECTION_RENDERER", 2, R$string.preferences_graphics);
        public static final MenuTag SECTION_AUDIO = new MenuTag("SECTION_AUDIO", 3, R$string.preferences_audio);
        public static final MenuTag SECTION_INPUT = new MenuTag("SECTION_INPUT", 4, R$string.preferences_controls);
        public static final MenuTag SECTION_INPUT_PLAYER_ONE = new MenuTag("SECTION_INPUT_PLAYER_ONE", 5, 0, 1, null);
        public static final MenuTag SECTION_INPUT_PLAYER_TWO = new MenuTag("SECTION_INPUT_PLAYER_TWO", 6, 0, 1, null);
        public static final MenuTag SECTION_THEME = new MenuTag("SECTION_THEME", 13, R$string.preferences_theme);
        public static final MenuTag SECTION_DEBUG = new MenuTag("SECTION_DEBUG", 14, R$string.preferences_debug);

        private static final /* synthetic */ MenuTag[] $values() {
            return new MenuTag[]{SECTION_ROOT, SECTION_SYSTEM, SECTION_RENDERER, SECTION_AUDIO, SECTION_INPUT, SECTION_INPUT_PLAYER_ONE, SECTION_INPUT_PLAYER_TWO, SECTION_INPUT_PLAYER_THREE, SECTION_INPUT_PLAYER_FOUR, SECTION_INPUT_PLAYER_FIVE, SECTION_INPUT_PLAYER_SIX, SECTION_INPUT_PLAYER_SEVEN, SECTION_INPUT_PLAYER_EIGHT, SECTION_THEME, SECTION_DEBUG};
        }

        static {
            int i = 0;
            int i2 = 1;
            DefaultConstructorMarker defaultConstructorMarker = null;
            SECTION_INPUT_PLAYER_THREE = new MenuTag("SECTION_INPUT_PLAYER_THREE", 7, i, i2, defaultConstructorMarker);
            int i3 = 0;
            int i4 = 1;
            DefaultConstructorMarker defaultConstructorMarker2 = null;
            SECTION_INPUT_PLAYER_FOUR = new MenuTag("SECTION_INPUT_PLAYER_FOUR", 8, i3, i4, defaultConstructorMarker2);
            SECTION_INPUT_PLAYER_FIVE = new MenuTag("SECTION_INPUT_PLAYER_FIVE", 9, i, i2, defaultConstructorMarker);
            SECTION_INPUT_PLAYER_SIX = new MenuTag("SECTION_INPUT_PLAYER_SIX", 10, i3, i4, defaultConstructorMarker2);
            SECTION_INPUT_PLAYER_SEVEN = new MenuTag("SECTION_INPUT_PLAYER_SEVEN", 11, i, i2, defaultConstructorMarker);
            SECTION_INPUT_PLAYER_EIGHT = new MenuTag("SECTION_INPUT_PLAYER_EIGHT", 12, i3, i4, defaultConstructorMarker2);
            MenuTag[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private MenuTag(String str, int i, int i2) {
            this.titleId = i2;
        }

        /* synthetic */ MenuTag(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i3 & 1) != 0 ? 0 : i2);
        }

        public static MenuTag valueOf(String str) {
            return (MenuTag) Enum.valueOf(MenuTag.class, str);
        }

        public static MenuTag[] values() {
            return (MenuTag[]) $VALUES.clone();
        }

        public final int getTitleId() {
            return this.titleId;
        }
    }

    static {
        List listOf;
        List listOf2;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"buttonToggle0", "buttonToggle1", "buttonToggle2", "buttonToggle3", "buttonToggle4", "buttonToggle5", "buttonToggle6", "buttonToggle7", "buttonToggle8", "buttonToggle9", "buttonToggle10", "buttonToggle11", "buttonToggle12", "buttonToggle15", "buttonToggle16", "buttonToggle13", "buttonToggle14"});
        overlayPreferences = listOf;
        listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"_Landscape", "_Portrait", "_Foldable"});
        overlayLayoutSuffixes = listOf2;
    }

    private Settings() {
    }

    public final List getOverlayPreferences() {
        return overlayPreferences;
    }

    public final String getPlayerString(int i) {
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.preferences_player, Integer.valueOf(i));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
