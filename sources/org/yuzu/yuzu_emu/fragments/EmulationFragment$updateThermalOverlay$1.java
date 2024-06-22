package org.yuzu.yuzu_emu.fragments;

import android.os.Handler;
import android.os.PowerManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding;
import org.yuzu.yuzu_emu.model.EmulationViewModel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class EmulationFragment$updateThermalOverlay$1 extends Lambda implements Function0 {
    final /* synthetic */ EmulationFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmulationFragment$updateThermalOverlay$1(EmulationFragment emulationFragment) {
        super(0);
        this.this$0 = emulationFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m144invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m144invoke() {
        EmulationViewModel emulationViewModel;
        EmulationViewModel emulationViewModel2;
        PowerManager powerManager;
        String str;
        FragmentEmulationBinding fragmentEmulationBinding;
        Handler handler;
        final Function0 function0;
        FragmentEmulationBinding binding;
        emulationViewModel = this.this$0.getEmulationViewModel();
        if (((Boolean) emulationViewModel.getEmulationStarted().getValue()).booleanValue()) {
            emulationViewModel2 = this.this$0.getEmulationViewModel();
            if (((Boolean) emulationViewModel2.isEmulationStopping().getValue()).booleanValue()) {
                return;
            }
            powerManager = this.this$0.powerManager;
            if (powerManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("powerManager");
                powerManager = null;
            }
            switch (powerManager.getCurrentThermalStatus()) {
                case 1:
                    str = "😥";
                    break;
                case 2:
                    str = "🥵";
                    break;
                case 3:
                    str = "🔥";
                    break;
                case 4:
                case 5:
                case 6:
                    str = "☢️";
                    break;
                default:
                    str = "🙂";
                    break;
            }
            fragmentEmulationBinding = this.this$0._binding;
            if (fragmentEmulationBinding != null) {
                binding = this.this$0.getBinding();
                binding.showThermalsText.setText(str);
            }
            handler = EmulationFragment.thermalStatsUpdateHandler;
            function0 = this.this$0.thermalStatsUpdater;
            Intrinsics.checkNotNull(function0);
            handler.postDelayed(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$updateThermalOverlay$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment$updateThermalOverlay$1.invoke$lambda$0(Function0.this);
                }
            }, 1000L);
        }
    }
}
