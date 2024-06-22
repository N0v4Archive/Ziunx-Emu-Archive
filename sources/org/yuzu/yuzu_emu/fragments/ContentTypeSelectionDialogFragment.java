package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.PreferenceManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.model.AddonViewModel;
import org.yuzu.yuzu_emu.ui.main.MainActivity;

/* loaded from: classes.dex */
public final class ContentTypeSelectionDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private final Lazy addonViewModel$delegate;
    private int selectedItem;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ContentTypeSelectionDialogFragment() {
        final Function0 function0 = null;
        this.addonViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AddonViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ContentTypeSelectionDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ContentTypeSelectionDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.ContentTypeSelectionDialogFragment$special$$inlined$activityViewModels$default$3
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

    private final AddonViewModel getAddonViewModel() {
        return (AddonViewModel) this.addonViewModel$delegate.getValue();
    }

    private final SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(YuzuApplication.Companion.getAppContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(ContentTypeSelectionDialogFragment this$0, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mainActivity, "$mainActivity");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        if (this$0.selectedItem == 0) {
            mainActivity.getInstallGameUpdate().launch(new String[]{"*/*"});
        } else if (this$0.getPreferences().getBoolean("ModNoticeSeen", false)) {
            this$0.getAddonViewModel().showModInstallPicker(true);
        } else {
            this$0.getPreferences().edit().putBoolean("ModNoticeSeen", true).apply();
            this$0.getAddonViewModel().showModNoticeDialog(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(ContentTypeSelectionDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        this$0.selectedItem = i;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        String[] strArr = {getString(R$string.updates_and_dlc), getString(R$string.mods_and_cheats)};
        if (bundle != null) {
            this.selectedItem = bundle.getInt("SelectedItem");
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.yuzu.yuzu_emu.ui.main.MainActivity");
        final MainActivity mainActivity = (MainActivity) requireActivity;
        AlertDialog show = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.select_content_type).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.ContentTypeSelectionDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ContentTypeSelectionDialogFragment.onCreateDialog$lambda$0(ContentTypeSelectionDialogFragment.this, mainActivity, dialogInterface, i);
            }
        }).setSingleChoiceItems((CharSequence[]) strArr, 0, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.ContentTypeSelectionDialogFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ContentTypeSelectionDialogFragment.onCreateDialog$lambda$1(ContentTypeSelectionDialogFragment.this, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt("SelectedItem", this.selectedItem);
    }
}
