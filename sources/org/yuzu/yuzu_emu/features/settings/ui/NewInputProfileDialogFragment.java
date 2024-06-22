package org.yuzu.yuzu_emu.features.settings.ui;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogEditTextBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;

/* loaded from: classes.dex */
public final class NewInputProfileDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogEditTextBinding binding;
    private int position;
    private final Lazy settingsViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NewInputProfileDialogFragment newInstance(SettingsViewModel settingsViewModel, InputProfileSetting profileSetting, int i) {
            Intrinsics.checkNotNullParameter(settingsViewModel, "settingsViewModel");
            Intrinsics.checkNotNullParameter(profileSetting, "profileSetting");
            settingsViewModel.setClickedItem(profileSetting);
            Bundle bundle = new Bundle();
            bundle.putInt("Position", i);
            NewInputProfileDialogFragment newInputProfileDialogFragment = new NewInputProfileDialogFragment();
            newInputProfileDialogFragment.setArguments(bundle);
            return newInputProfileDialogFragment;
        }
    }

    public NewInputProfileDialogFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.NewInputProfileDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.NewInputProfileDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.NewInputProfileDialogFragment$special$$inlined$activityViewModels$default$3
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

    private final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(NewInputProfileDialogFragment this$0, InputProfileSetting setting, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(setting, "$setting");
        DialogEditTextBinding dialogEditTextBinding = this$0.binding;
        if (dialogEditTextBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogEditTextBinding = null;
        }
        String valueOf = String.valueOf(dialogEditTextBinding.editText.getText());
        if (!setting.isProfileNameValid(valueOf)) {
            Toast.makeText(this$0.requireContext(), R$string.invalid_profile_name, 0).show();
        } else if (setting.createProfile(valueOf)) {
            this$0.getSettingsViewModel().setAdapterItemChanged(this$0.position);
        } else {
            Toast.makeText(this$0.requireContext(), R$string.profile_name_already_exists, 0).show();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.position = requireArguments().getInt("Position");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogEditTextBinding inflate = DialogEditTextBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
        Intrinsics.checkNotNull(clickedItem, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting");
        final InputProfileSetting inputProfileSetting = (InputProfileSetting) clickedItem;
        DialogEditTextBinding dialogEditTextBinding = null;
        MaterialAlertDialogBuilder negativeButton = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.enter_profile_name).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.NewInputProfileDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                NewInputProfileDialogFragment.onCreateDialog$lambda$0(NewInputProfileDialogFragment.this, inputProfileSetting, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        DialogEditTextBinding dialogEditTextBinding2 = this.binding;
        if (dialogEditTextBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogEditTextBinding = dialogEditTextBinding2;
        }
        AlertDialog show = negativeButton.setView((View) dialogEditTextBinding.getRoot()).show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }
}
