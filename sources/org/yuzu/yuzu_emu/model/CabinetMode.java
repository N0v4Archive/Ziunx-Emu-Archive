package org.yuzu.yuzu_emu.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class CabinetMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CabinetMode[] $VALUES;
    private final int iconId;
    private final int id;
    private final int titleId;
    public static final CabinetMode None = new CabinetMode("None", 0, -1, 0, 0, 6, null);
    public static final CabinetMode StartNicknameAndOwnerSettings = new CabinetMode("StartNicknameAndOwnerSettings", 1, 0, R$string.cabinet_nickname_and_owner, R$drawable.ic_edit);
    public static final CabinetMode StartGameDataEraser = new CabinetMode("StartGameDataEraser", 2, 1, R$string.cabinet_game_data_eraser, R$drawable.ic_refresh);
    public static final CabinetMode StartRestorer = new CabinetMode("StartRestorer", 3, 2, R$string.cabinet_restorer, R$drawable.ic_restore);
    public static final CabinetMode StartFormatter = new CabinetMode("StartFormatter", 4, 3, R$string.cabinet_formatter, R$drawable.ic_clear);

    private static final /* synthetic */ CabinetMode[] $values() {
        return new CabinetMode[]{None, StartNicknameAndOwnerSettings, StartGameDataEraser, StartRestorer, StartFormatter};
    }

    static {
        CabinetMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private CabinetMode(String str, int i, int i2, int i3, int i4) {
        this.id = i2;
        this.titleId = i3;
        this.iconId = i4;
    }

    /* synthetic */ CabinetMode(String str, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i5 & 2) != 0 ? 0 : i3, (i5 & 4) != 0 ? 0 : i4);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static CabinetMode valueOf(String str) {
        return (CabinetMode) Enum.valueOf(CabinetMode.class, str);
    }

    public static CabinetMode[] values() {
        return (CabinetMode[]) $VALUES.clone();
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final int getId() {
        return this.id;
    }

    public final int getTitleId() {
        return this.titleId;
    }
}
