package org.yuzu.yuzu_emu.model;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class InstallResult {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InstallResult[] $VALUES;
    public static final Companion Companion;

    /* renamed from: int, reason: not valid java name */
    private final int f11int;
    public static final InstallResult Success = new InstallResult("Success", 0, 0);
    public static final InstallResult Overwrite = new InstallResult("Overwrite", 1, 1);
    public static final InstallResult Failure = new InstallResult("Failure", 2, 2);
    public static final InstallResult BaseInstallAttempted = new InstallResult("BaseInstallAttempted", 3, 3);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InstallResult from(int i) {
            Object obj;
            Iterator<E> it = InstallResult.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((InstallResult) obj).getInt() == i) {
                    break;
                }
            }
            InstallResult installResult = (InstallResult) obj;
            return installResult == null ? InstallResult.Success : installResult;
        }
    }

    private static final /* synthetic */ InstallResult[] $values() {
        return new InstallResult[]{Success, Overwrite, Failure, BaseInstallAttempted};
    }

    static {
        InstallResult[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private InstallResult(String str, int i, int i2) {
        this.f11int = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static InstallResult valueOf(String str) {
        return (InstallResult) Enum.valueOf(InstallResult.class, str);
    }

    public static InstallResult[] values() {
        return (InstallResult[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f11int;
    }
}
