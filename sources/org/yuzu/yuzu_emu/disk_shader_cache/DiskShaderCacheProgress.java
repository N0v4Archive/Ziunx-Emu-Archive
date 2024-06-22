package org.yuzu.yuzu_emu.disk_shader_cache;

import androidx.annotation.Keep;
import androidx.lifecycle.ViewModelProvider;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.activities.EmulationActivity;
import org.yuzu.yuzu_emu.model.EmulationViewModel;
import org.yuzu.yuzu_emu.utils.Log;

@Keep
/* loaded from: classes.dex */
public final class DiskShaderCacheProgress {
    public static final DiskShaderCacheProgress INSTANCE = new DiskShaderCacheProgress();
    private static EmulationViewModel emulationViewModel;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public static final class LoadCallbackStage {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ LoadCallbackStage[] $VALUES;
        public static final LoadCallbackStage Prepare = new LoadCallbackStage("Prepare", 0);
        public static final LoadCallbackStage Build = new LoadCallbackStage("Build", 1);
        public static final LoadCallbackStage Complete = new LoadCallbackStage("Complete", 2);

        private static final /* synthetic */ LoadCallbackStage[] $values() {
            return new LoadCallbackStage[]{Prepare, Build, Complete};
        }

        static {
            LoadCallbackStage[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private LoadCallbackStage(String str, int i) {
        }

        public static LoadCallbackStage valueOf(String str) {
            return (LoadCallbackStage) Enum.valueOf(LoadCallbackStage.class, str);
        }

        public static LoadCallbackStage[] values() {
            return (LoadCallbackStage[]) $VALUES.clone();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadCallbackStage.values().length];
            try {
                iArr[LoadCallbackStage.Prepare.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LoadCallbackStage.Build.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LoadCallbackStage.Complete.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private DiskShaderCacheProgress() {
    }

    public static final void loadProgress(final int i, final int i2, final int i3) {
        final EmulationActivity emulationActivity = (EmulationActivity) NativeLibrary.sEmulationActivity.get();
        if (emulationActivity == null) {
            Log.INSTANCE.error("[DiskShaderCacheProgress] EmulationActivity not present");
        } else {
            emulationActivity.runOnUiThread(new Runnable() { // from class: org.yuzu.yuzu_emu.disk_shader_cache.DiskShaderCacheProgress$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DiskShaderCacheProgress.loadProgress$lambda$0(i, emulationActivity, i2, i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadProgress$lambda$0(int i, EmulationActivity emulationActivity, int i2, int i3) {
        int i4 = WhenMappings.$EnumSwitchMapping$0[LoadCallbackStage.values()[i].ordinal()];
        if (i4 == 1) {
            INSTANCE.prepareViewModel();
            return;
        }
        if (i4 != 2) {
            return;
        }
        EmulationViewModel emulationViewModel2 = emulationViewModel;
        if (emulationViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emulationViewModel");
            emulationViewModel2 = null;
        }
        String string = emulationActivity.getString(R$string.building_shaders);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        emulationViewModel2.updateProgress(string, i2, i3);
    }

    private final void prepareViewModel() {
        Object obj = NativeLibrary.sEmulationActivity.get();
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.yuzu.yuzu_emu.activities.EmulationActivity");
        emulationViewModel = (EmulationViewModel) new ViewModelProvider((EmulationActivity) obj).get(EmulationViewModel.class);
    }
}
