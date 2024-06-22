package org.yuzu.yuzu_emu.model;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class GameVerificationResult {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ GameVerificationResult[] $VALUES;
    public static final Companion Companion;

    /* renamed from: int, reason: not valid java name */
    private final int f10int;
    public static final GameVerificationResult Success = new GameVerificationResult("Success", 0, 0);
    public static final GameVerificationResult Failed = new GameVerificationResult("Failed", 1, 1);
    public static final GameVerificationResult NotImplemented = new GameVerificationResult("NotImplemented", 2, 2);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GameVerificationResult from(int i) {
            Object obj;
            Iterator<E> it = GameVerificationResult.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((GameVerificationResult) obj).getInt() == i) {
                    break;
                }
            }
            GameVerificationResult gameVerificationResult = (GameVerificationResult) obj;
            return gameVerificationResult == null ? GameVerificationResult.Success : gameVerificationResult;
        }
    }

    private static final /* synthetic */ GameVerificationResult[] $values() {
        return new GameVerificationResult[]{Success, Failed, NotImplemented};
    }

    static {
        GameVerificationResult[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion(null);
    }

    private GameVerificationResult(String str, int i, int i2) {
        this.f10int = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static GameVerificationResult valueOf(String str) {
        return (GameVerificationResult) Enum.valueOf(GameVerificationResult.class, str);
    }

    public static GameVerificationResult[] values() {
        return (GameVerificationResult[]) $VALUES.clone();
    }

    public final int getInt() {
        return this.f10int;
    }
}
