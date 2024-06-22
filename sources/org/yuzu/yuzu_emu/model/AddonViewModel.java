package org.yuzu.yuzu_emu.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.utils.NativeConfig;

/* loaded from: classes.dex */
public final class AddonViewModel extends ViewModel {
    private final MutableStateFlow _addonToDelete;
    private final MutableStateFlow _patchList = StateFlowKt.MutableStateFlow(new ArrayList());
    private final MutableStateFlow _showModInstallPicker;
    private final MutableStateFlow _showModNoticeDialog;
    private final StateFlow addonToDelete;
    private Game game;
    private final AtomicBoolean isRefreshing;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PatchType.values().length];
            try {
                iArr[PatchType.Update.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PatchType.DLC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PatchType.Mod.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AddonViewModel() {
        Boolean bool = Boolean.FALSE;
        this._showModInstallPicker = StateFlowKt.MutableStateFlow(bool);
        this._showModNoticeDialog = StateFlowKt.MutableStateFlow(bool);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._addonToDelete = MutableStateFlow;
        this.addonToDelete = FlowKt.asStateFlow(MutableStateFlow);
        this.isRefreshing = new AtomicBoolean(false);
    }

    public final StateFlow getAddonList() {
        return FlowKt.asStateFlow(this._patchList);
    }

    public final StateFlow getAddonToDelete() {
        return this.addonToDelete;
    }

    public final Game getGame() {
        return this.game;
    }

    public final StateFlow getShowModInstallPicker() {
        return FlowKt.asStateFlow(this._showModInstallPicker);
    }

    public final StateFlow getShowModNoticeDialog() {
        return FlowKt.asStateFlow(this._showModNoticeDialog);
    }

    public final void onCloseAddons() {
        if (((List) this._patchList.getValue()).isEmpty()) {
            return;
        }
        NativeConfig nativeConfig = NativeConfig.INSTANCE;
        Game game = this.game;
        Intrinsics.checkNotNull(game);
        String programId = game.getProgramId();
        Iterable iterable = (Iterable) this._patchList.getValue();
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            if (!it.hasNext()) {
                nativeConfig.setDisabledAddons(programId, (String[]) arrayList.toArray(new String[0]));
                NativeConfig.INSTANCE.saveGlobalConfig();
                ((List) this._patchList.getValue()).clear();
                this.game = null;
                return;
            }
            Patch patch = (Patch) it.next();
            String name = patch.getEnabled() ? null : patch.getName();
            if (name != null) {
                arrayList.add(name);
            }
        }
    }

    public final void onDeleteAddon(Patch patch) {
        Intrinsics.checkNotNullParameter(patch, "patch");
        int i = WhenMappings.$EnumSwitchMapping$0[PatchType.Companion.from(patch.getType()).ordinal()];
        if (i == 1) {
            NativeLibrary.INSTANCE.removeUpdate(patch.getProgramId());
        } else if (i == 2) {
            NativeLibrary.INSTANCE.removeDLC(patch.getProgramId());
        } else if (i == 3) {
            NativeLibrary.INSTANCE.removeMod(patch.getProgramId(), patch.getName());
        }
        refreshAddons();
    }

    public final void onOpenAddons(Game game) {
        Intrinsics.checkNotNullParameter(game, "game");
        this.game = game;
        refreshAddons();
    }

    public final void refreshAddons() {
        if (this.isRefreshing.get() || this.game == null) {
            return;
        }
        this.isRefreshing.set(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AddonViewModel$refreshAddons$1(this, null), 3, null);
    }

    public final void setAddonToDelete(Patch patch) {
        this._addonToDelete.setValue(patch);
    }

    public final void showModInstallPicker(boolean z) {
        this._showModInstallPicker.setValue(Boolean.valueOf(z));
    }

    public final void showModNoticeDialog(boolean z) {
        this._showModNoticeDialog.setValue(Boolean.valueOf(z));
    }
}
