package org.yuzu.yuzu_emu.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class AppletInfo {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AppletInfo[] $VALUES;
    public static final AppletInfo Application;
    public static final AppletInfo Auth;
    public static final AppletInfo DataErase;
    public static final AppletInfo Error;
    public static final AppletInfo LoginShare;
    public static final AppletInfo MyPage;
    public static final AppletInfo NetConnect;
    public static final AppletInfo OfflineWeb;
    public static final AppletInfo OverlayDisplay;
    public static final AppletInfo ProfileSelect;
    public static final AppletInfo QLaunch;
    public static final AppletInfo Settings;
    public static final AppletInfo Shop;
    public static final AppletInfo SoftwareKeyboard;
    public static final AppletInfo Starter;
    public static final AppletInfo Web;
    private final int appletId;
    private final long entryId;
    public static final AppletInfo None = new AppletInfo("None", 0, 0, 0, 2, null);
    public static final AppletInfo Cabinet = new AppletInfo("Cabinet", 6, 11, 72057594037932034L);
    public static final AppletInfo Controller = new AppletInfo("Controller", 7, 12, 0, 2, null);
    public static final AppletInfo MiiEdit = new AppletInfo("MiiEdit", 13, 18, 72057594037932041L);
    public static final AppletInfo PhotoViewer = new AppletInfo("PhotoViewer", 16, 21, 72057594037932045L);
    public static final AppletInfo WebAuth = new AppletInfo("WebAuth", 20, 25, 0, 2, null);

    private static final /* synthetic */ AppletInfo[] $values() {
        return new AppletInfo[]{None, Application, OverlayDisplay, QLaunch, Starter, Auth, Cabinet, Controller, DataErase, Error, NetConnect, ProfileSelect, SoftwareKeyboard, MiiEdit, Web, Shop, PhotoViewer, Settings, OfflineWeb, LoginShare, WebAuth, MyPage};
    }

    static {
        long j = 0;
        int i = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Application = new AppletInfo("Application", 1, 1, j, i, defaultConstructorMarker);
        long j2 = 0;
        int i2 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        OverlayDisplay = new AppletInfo("OverlayDisplay", 2, 2, j2, i2, defaultConstructorMarker2);
        QLaunch = new AppletInfo("QLaunch", 3, 3, j, i, defaultConstructorMarker);
        Starter = new AppletInfo("Starter", 4, 4, j2, i2, defaultConstructorMarker2);
        Auth = new AppletInfo("Auth", 5, 10, j, i, defaultConstructorMarker);
        long j3 = 0;
        int i3 = 2;
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        DataErase = new AppletInfo("DataErase", 8, 13, j3, i3, defaultConstructorMarker3);
        long j4 = 0;
        int i4 = 2;
        DefaultConstructorMarker defaultConstructorMarker4 = null;
        Error = new AppletInfo("Error", 9, 14, j4, i4, defaultConstructorMarker4);
        long j5 = 0;
        int i5 = 2;
        DefaultConstructorMarker defaultConstructorMarker5 = null;
        NetConnect = new AppletInfo("NetConnect", 10, 15, j5, i5, defaultConstructorMarker5);
        ProfileSelect = new AppletInfo("ProfileSelect", 11, 16, j4, i4, defaultConstructorMarker4);
        SoftwareKeyboard = new AppletInfo("SoftwareKeyboard", 12, 17, j5, i5, defaultConstructorMarker5);
        long j6 = 0;
        int i6 = 2;
        DefaultConstructorMarker defaultConstructorMarker6 = null;
        Web = new AppletInfo("Web", 14, 19, j6, i6, defaultConstructorMarker6);
        Shop = new AppletInfo("Shop", 15, 20, j3, i3, defaultConstructorMarker3);
        Settings = new AppletInfo("Settings", 17, 22, j6, i6, defaultConstructorMarker6);
        OfflineWeb = new AppletInfo("OfflineWeb", 18, 23, j3, i3, defaultConstructorMarker3);
        long j7 = 0;
        int i7 = 2;
        DefaultConstructorMarker defaultConstructorMarker7 = null;
        LoginShare = new AppletInfo("LoginShare", 19, 24, j7, i7, defaultConstructorMarker7);
        MyPage = new AppletInfo("MyPage", 21, 26, j7, i7, defaultConstructorMarker7);
        AppletInfo[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private AppletInfo(String str, int i, int i2, long j) {
        this.appletId = i2;
        this.entryId = j;
    }

    /* synthetic */ AppletInfo(String str, int i, int i2, long j, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 2) != 0 ? 0L : j);
    }

    public static AppletInfo valueOf(String str) {
        return (AppletInfo) Enum.valueOf(AppletInfo.class, str);
    }

    public static AppletInfo[] values() {
        return (AppletInfo[]) $VALUES.clone();
    }

    public final int getAppletId() {
        return this.appletId;
    }

    public final long getEntryId() {
        return this.entryId;
    }
}
