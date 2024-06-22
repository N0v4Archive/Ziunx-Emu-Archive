package org.yuzu.yuzu_emu.features.settings.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogInputProfilesBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.ui.NewInputProfileDialogFragment;

/* loaded from: classes.dex */
public final class InputProfileDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogInputProfilesBinding binding;
    private int position;
    private InputProfileSetting setting;
    private final Lazy settingsViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InputProfileDialogFragment newInstance(SettingsViewModel settingsViewModel, InputProfileSetting profileSetting, int i) {
            Intrinsics.checkNotNullParameter(settingsViewModel, "settingsViewModel");
            Intrinsics.checkNotNullParameter(profileSetting, "profileSetting");
            settingsViewModel.setClickedItem(profileSetting);
            Bundle bundle = new Bundle();
            bundle.putInt("Position", i);
            InputProfileDialogFragment inputProfileDialogFragment = new InputProfileDialogFragment();
            inputProfileDialogFragment.setArguments(bundle);
            return inputProfileDialogFragment;
        }
    }

    public InputProfileDialogFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$special$$inlined$activityViewModels$default$3
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
    public final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.position = requireArguments().getInt("Position");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogInputProfilesBinding inflate = DialogInputProfilesBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
        Intrinsics.checkNotNull(clickedItem, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting");
        this.setting = (InputProfileSetting) clickedItem;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NewProfileItem(new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onCreateDialog$options$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m100invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m100invoke() {
                SettingsViewModel settingsViewModel;
                InputProfileSetting inputProfileSetting;
                int i;
                NewInputProfileDialogFragment.Companion companion = NewInputProfileDialogFragment.Companion;
                settingsViewModel = InputProfileDialogFragment.this.getSettingsViewModel();
                inputProfileSetting = InputProfileDialogFragment.this.setting;
                if (inputProfileSetting == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setting");
                    inputProfileSetting = null;
                }
                i = InputProfileDialogFragment.this.position;
                companion.newInstance(settingsViewModel, inputProfileSetting, i).show(InputProfileDialogFragment.this.getParentFragmentManager(), "NewInputProfileDialogFragment");
                InputProfileDialogFragment.this.dismiss();
            }
        }));
        final Function0 function0 = new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onCreateDialog$options$1$onActionDismiss$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m104invoke();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final void m104invoke() {
                SettingsViewModel settingsViewModel;
                settingsViewModel = InputProfileDialogFragment.this.getSettingsViewModel();
                settingsViewModel.setReloadListAndNotifyDataset(true);
                InputProfileDialogFragment.this.dismiss();
            }
        };
        InputProfileSetting inputProfileSetting = this.setting;
        DialogInputProfilesBinding dialogInputProfilesBinding = null;
        if (inputProfileSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            inputProfileSetting = null;
        }
        for (final String str : inputProfileSetting.getProfileNames()) {
            arrayList.add(new ExistingProfileItem(str, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onCreateDialog$options$1$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m101invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m101invoke() {
                    SettingsViewModel settingsViewModel;
                    settingsViewModel = InputProfileDialogFragment.this.getSettingsViewModel();
                    settingsViewModel.setShouldShowDeleteProfileDialog(str);
                }
            }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onCreateDialog$options$1$2$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m102invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m102invoke() {
                    InputProfileSetting inputProfileSetting2;
                    inputProfileSetting2 = InputProfileDialogFragment.this.setting;
                    if (inputProfileSetting2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("setting");
                        inputProfileSetting2 = null;
                    }
                    if (!inputProfileSetting2.saveProfile(str)) {
                        Toast.makeText(InputProfileDialogFragment.this.requireContext(), R$string.failed_to_save_profile, 0).show();
                    }
                    function0.invoke();
                }
            }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputProfileDialogFragment$onCreateDialog$options$1$2$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m103invoke();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final void m103invoke() {
                    InputProfileSetting inputProfileSetting2;
                    inputProfileSetting2 = InputProfileDialogFragment.this.setting;
                    if (inputProfileSetting2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("setting");
                        inputProfileSetting2 = null;
                    }
                    if (!inputProfileSetting2.loadProfile(str)) {
                        Toast.makeText(InputProfileDialogFragment.this.requireContext(), R$string.failed_to_load_profile, 0).show();
                    }
                    function0.invoke();
                }
            }));
        }
        DialogInputProfilesBinding dialogInputProfilesBinding2 = this.binding;
        if (dialogInputProfilesBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogInputProfilesBinding2 = null;
        }
        RecyclerView recyclerView = dialogInputProfilesBinding2.listProfiles;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new InputProfileAdapter(arrayList));
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        DialogInputProfilesBinding dialogInputProfilesBinding3 = this.binding;
        if (dialogInputProfilesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogInputProfilesBinding = dialogInputProfilesBinding3;
        }
        AlertDialog create = materialAlertDialogBuilder.setView((View) dialogInputProfilesBinding.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogInputProfilesBinding dialogInputProfilesBinding = this.binding;
        if (dialogInputProfilesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogInputProfilesBinding = null;
        }
        RecyclerView root = dialogInputProfilesBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        StateFlow shouldShowDeleteProfileDialog = getSettingsViewModel().getShouldShowDeleteProfileDialog();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new InputProfileDialogFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, shouldShowDeleteProfileDialog, null, this), 3, null);
    }
}
