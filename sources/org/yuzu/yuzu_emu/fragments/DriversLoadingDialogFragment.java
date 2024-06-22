package org.yuzu.yuzu_emu.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogProgressBarBinding;
import org.yuzu.yuzu_emu.model.DriverViewModel;

/* loaded from: classes.dex */
public final class DriversLoadingDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogProgressBarBinding binding;
    private final Lazy driverViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DriversLoadingDialogFragment() {
        final Function0 function0 = null;
        this.driverViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DriverViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriversLoadingDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriversLoadingDialogFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.DriversLoadingDialogFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final DriverViewModel getDriverViewModel() {
        return (DriverViewModel) this.driverViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogProgressBarBinding inflate = DialogProgressBarBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogProgressBarBinding dialogProgressBarBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.progressBar.setIndeterminate(true);
        setCancelable(false);
        MaterialAlertDialogBuilder title = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.loading);
        DialogProgressBarBinding dialogProgressBarBinding2 = this.binding;
        if (dialogProgressBarBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogProgressBarBinding = dialogProgressBarBinding2;
        }
        AlertDialog create = title.setView((View) dialogProgressBarBinding.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogProgressBarBinding dialogProgressBarBinding = this.binding;
        if (dialogProgressBarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogProgressBarBinding = null;
        }
        LinearLayout root = dialogProgressBarBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        StateFlow isInteractionAllowed = getDriverViewModel().isInteractionAllowed();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new DriversLoadingDialogFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, isInteractionAllowed, null, this), 3, null);
    }
}
