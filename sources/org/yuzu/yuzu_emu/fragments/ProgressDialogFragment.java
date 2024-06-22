package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogProgressBarBinding;
import org.yuzu.yuzu_emu.model.TaskViewModel;

/* loaded from: classes.dex */
public final class ProgressDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private final int PROGRESS_BAR_RESOLUTION = 1000;
    private DialogProgressBarBinding binding;
    private final Lazy taskViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ProgressDialogFragment newInstance$default(Companion companion, FragmentActivity fragmentActivity, int i, boolean z, Function3 function3, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                z = false;
            }
            return companion.newInstance(fragmentActivity, i, z, function3);
        }

        public final ProgressDialogFragment newInstance(FragmentActivity activity, int i, boolean z, Function3 task) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(task, "task");
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
            Bundle bundle = new Bundle();
            ((TaskViewModel) new ViewModelProvider(activity).get(TaskViewModel.class)).setTask(task);
            bundle.putInt("Title", i);
            bundle.putBoolean("Cancellable", z);
            progressDialogFragment.setArguments(bundle);
            return progressDialogFragment;
        }
    }

    public ProgressDialogFragment() {
        final Function0 function0 = null;
        this.taskViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(TaskViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ProgressDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ProgressDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ProgressDialogFragment$special$$inlined$activityViewModels$default$3
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

    /* JADX INFO: Access modifiers changed from: private */
    public final TaskViewModel getTaskViewModel() {
        return (TaskViewModel) this.taskViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$5(AlertDialog alertDialog, ProgressDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        alertDialog.setTitle(this$0.getString(R$string.cancelling));
        DialogProgressBarBinding dialogProgressBarBinding = this$0.binding;
        if (dialogProgressBarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogProgressBarBinding = null;
        }
        dialogProgressBarBinding.progressBar.setIndeterminate(true);
        this$0.getTaskViewModel().setCancelled(true);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        int i = requireArguments().getInt("Title");
        boolean z = requireArguments().getBoolean("Cancellable");
        DialogProgressBarBinding inflate = DialogProgressBarBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.progressBar.setIndeterminate(true);
        MaterialAlertDialogBuilder title = new MaterialAlertDialogBuilder(requireContext()).setTitle(i);
        DialogProgressBarBinding dialogProgressBarBinding = this.binding;
        if (dialogProgressBarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogProgressBarBinding = null;
        }
        MaterialAlertDialogBuilder view = title.setView((View) dialogProgressBarBinding.getRoot());
        Intrinsics.checkNotNullExpressionValue(view, "setView(...)");
        if (z) {
            view.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        }
        AlertDialog create = view.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.setCanceledOnTouchOutside(false);
        if (!((Boolean) getTaskViewModel().isRunning().getValue()).booleanValue()) {
            getTaskViewModel().runTask();
        }
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
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        Intrinsics.checkNotNull(dialog, "null cannot be cast to non-null type androidx.appcompat.app.AlertDialog");
        final AlertDialog alertDialog = (AlertDialog) dialog;
        alertDialog.getButton(-2).setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.ProgressDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProgressDialogFragment.onResume$lambda$5(AlertDialog.this, this, view);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        DialogProgressBarBinding dialogProgressBarBinding = this.binding;
        if (dialogProgressBarBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogProgressBarBinding = null;
        }
        dialogProgressBarBinding.message.setSelected(true);
        StateFlow isComplete = getTaskViewModel().isComplete();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Lifecycle.State state = Lifecycle.State.CREATED;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new ProgressDialogFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, isComplete, null, this), 3, null);
        StateFlow cancelled = getTaskViewModel().getCancelled();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new ProgressDialogFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, cancelled, null, this), 3, null);
        StateFlow progress = getTaskViewModel().getProgress();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new ProgressDialogFragment$onViewCreated$$inlined$collect$default$3(viewLifecycleOwner3, state, progress, null, this), 3, null);
        StateFlow message = getTaskViewModel().getMessage();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner4), null, null, new ProgressDialogFragment$onViewCreated$$inlined$collect$default$4(viewLifecycleOwner4, state, message, null, this), 3, null);
    }
}
