package org.yuzu.yuzu_emu.fragments;

import android.os.Handler;
import com.google.android.material.textview.MaterialTextView;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.databinding.FragmentEmulationBinding;
import org.yuzu.yuzu_emu.model.EmulationViewModel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class EmulationFragment$updateShowFpsOverlay$1 extends Lambda implements Function0 {
    final /* synthetic */ int $FPS;
    final /* synthetic */ EmulationFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmulationFragment$updateShowFpsOverlay$1(EmulationFragment emulationFragment, int i) {
        super(0);
        this.this$0 = emulationFragment;
        this.$FPS = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m143invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m143invoke() {
        EmulationViewModel emulationViewModel;
        EmulationViewModel emulationViewModel2;
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
            NativeLibrary nativeLibrary = NativeLibrary.INSTANCE;
            double[] perfStats = nativeLibrary.getPerfStats();
            String cpuBackend = nativeLibrary.getCpuBackend();
            String gpuDriver = nativeLibrary.getGpuDriver();
            fragmentEmulationBinding = this.this$0._binding;
            if (fragmentEmulationBinding != null) {
                binding = this.this$0.getBinding();
                MaterialTextView materialTextView = binding.showFpsText;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("FPS: %.1f\n%s/%s", Arrays.copyOf(new Object[]{Double.valueOf(perfStats[this.$FPS]), cpuBackend, gpuDriver}, 3));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                materialTextView.setText(format);
            }
            handler = EmulationFragment.perfStatsUpdateHandler;
            function0 = this.this$0.perfStatsUpdater;
            Intrinsics.checkNotNull(function0);
            handler.postDelayed(new Runnable() { // from class: org.yuzu.yuzu_emu.fragments.EmulationFragment$updateShowFpsOverlay$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EmulationFragment$updateShowFpsOverlay$1.invoke$lambda$0(Function0.this);
                }
            }, 800L);
        }
    }
}
