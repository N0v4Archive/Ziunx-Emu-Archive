package org.yuzu.yuzu_emu.features.settings.ui;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.Slider;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogEditTextBinding;
import org.yuzu.yuzu_emu.databinding.DialogSliderBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.AnalogDirection;
import org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ButtonInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.StringInputSetting;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class SettingsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final Companion Companion = new Companion(null);
    private DialogInterface.OnClickListener defaultCancelListener = new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$$ExternalSyntheticLambda2
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            SettingsDialogFragment.defaultCancelListener$lambda$0(SettingsDialogFragment.this, dialogInterface, i);
        }
    };
    private int position;
    private final Lazy settingsViewModel$delegate;
    private DialogSliderBinding sliderBinding;
    private DialogEditTextBinding stringInputBinding;
    private int type;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
        
            if (r6 != 7) goto L16;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment newInstance(org.yuzu.yuzu_emu.features.settings.ui.SettingsViewModel r4, org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem r5, int r6, int r7) {
            /*
                r3 = this;
                java.lang.String r3 = "settingsViewModel"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                java.lang.String r3 = "clickedItem"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
                if (r6 == 0) goto L44
                r3 = 1
                if (r6 == r3) goto L44
                r0 = 3
                if (r6 == r0) goto L1c
                r3 = 4
                if (r6 == r3) goto L44
                r3 = 6
                if (r6 == r3) goto L44
                r3 = 7
                if (r6 == r3) goto L44
                goto L29
            L1c:
                r0 = r5
                org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting r0 = (org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting) r0
                r1 = 0
                r2 = 0
                int r3 = org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting.getSelectedValue$default(r0, r1, r3, r2)
                float r3 = (float) r3
                r4.setSliderProgress(r3)
            L29:
                r4.setClickedItem(r5)
                android.os.Bundle r3 = new android.os.Bundle
                r3.<init>()
                java.lang.String r4 = "Type"
                r3.putInt(r4, r6)
                java.lang.String r4 = "Position"
                r3.putInt(r4, r7)
                org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment r4 = new org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment
                r4.<init>()
                r4.setArguments(r3)
                return r4
            L44:
                java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
                java.lang.String r4 = "[SettingsDialogFragment] Incompatible type!"
                r3.<init>(r4)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment.Companion.newInstance(org.yuzu.yuzu_emu.features.settings.ui.SettingsViewModel, org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem, int, int):org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment");
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnalogDirection.values().length];
            try {
                iArr[AnalogDirection.Up.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnalogDirection.Down.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnalogDirection.Left.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnalogDirection.Right.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SettingsDialogFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$special$$inlined$activityViewModels$default$3
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

    private final void closeDialog() {
        getSettingsViewModel().setAdapterItemChanged(this.position);
        getSettingsViewModel().setClickedItem(null);
        getSettingsViewModel().setSliderProgress(-1.0f);
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void defaultCancelListener$lambda$0(SettingsDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeDialog();
    }

    private final int getSelectionForSingleChoiceValue(SingleChoiceSetting singleChoiceSetting) {
        int selectedValue$default = SingleChoiceSetting.getSelectedValue$default(singleChoiceSetting, false, 1, null);
        int valuesId = singleChoiceSetting.getValuesId();
        if (valuesId <= 0) {
            return selectedValue$default;
        }
        int[] intArray = requireContext().getResources().getIntArray(valuesId);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int length = intArray.length;
        for (int i = 0; i < length; i++) {
            if (intArray[i] == selectedValue$default) {
                return i;
            }
        }
        return -1;
    }

    private final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    private final int getValueForSingleChoiceSelection(SingleChoiceSetting singleChoiceSetting, int i) {
        int valuesId = singleChoiceSetting.getValuesId();
        if (valuesId <= 0) {
            return i;
        }
        int[] intArray = requireContext().getResources().getIntArray(valuesId);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        return intArray[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(SettingsDialogFragment this$0, DialogInterface dialogInterface, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        SettingsItem clickedItem = this$0.getSettingsViewModel().getClickedItem();
        if (clickedItem instanceof AnalogInputSetting) {
            NativeInput nativeInput = NativeInput.INSTANCE;
            AnalogInputSetting analogInputSetting = (AnalogInputSetting) clickedItem;
            ParamPackage stickParam = nativeInput.getStickParam(analogInputSetting.getPlayerIndex(), analogInputSetting.getNativeAnalog());
            if (!Intrinsics.areEqual(stickParam.get("engine", ""), "analog_from_button")) {
                nativeInput.setStickParam(analogInputSetting.getPlayerIndex(), analogInputSetting.getNativeAnalog(), new ParamPackage(null, 1, null));
                this$0.getSettingsViewModel().setDatasetChanged(true);
                return;
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[analogInputSetting.getAnalogDirection().ordinal()];
            if (i2 == 1) {
                str = "up";
            } else if (i2 == 2) {
                str = "down";
            } else if (i2 != 3) {
                if (i2 == 4) {
                    str = "right";
                }
                nativeInput.setStickParam(analogInputSetting.getPlayerIndex(), analogInputSetting.getNativeAnalog(), stickParam);
            } else {
                str = "left";
            }
            stickParam.erase(str);
            nativeInput.setStickParam(analogInputSetting.getPlayerIndex(), analogInputSetting.getNativeAnalog(), stickParam);
        } else if (clickedItem instanceof ButtonInputSetting) {
            ButtonInputSetting buttonInputSetting = (ButtonInputSetting) clickedItem;
            NativeInput.INSTANCE.setButtonParam(buttonInputSetting.getPlayerIndex(), buttonInputSetting.getNativeButton(), new ParamPackage(null, 1, null));
        } else {
            SettingsItem clickedItem2 = this$0.getSettingsViewModel().getClickedItem();
            Intrinsics.checkNotNull(clickedItem2);
            clickedItem2.getSetting().reset();
        }
        this$0.getSettingsViewModel().setAdapterItemChanged(this$0.position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$3$lambda$2(SettingsDialogFragment this$0, SliderSetting item, Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(slider, "<anonymous parameter 0>");
        this$0.getSettingsViewModel().setSliderTextValue(f, item.getUnits());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
        if (clickedItem instanceof SingleChoiceSetting) {
            SettingsItem clickedItem2 = getSettingsViewModel().getClickedItem();
            Intrinsics.checkNotNull(clickedItem2, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting");
            SingleChoiceSetting singleChoiceSetting = (SingleChoiceSetting) clickedItem2;
            singleChoiceSetting.setSelectedValue(getValueForSingleChoiceSelection(singleChoiceSetting, i));
        } else if (clickedItem instanceof IntSingleChoiceSetting) {
            SettingsItem clickedItem3 = getSettingsViewModel().getClickedItem();
            Intrinsics.checkNotNull(clickedItem3, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting");
            IntSingleChoiceSetting intSingleChoiceSetting = (IntSingleChoiceSetting) clickedItem3;
            intSingleChoiceSetting.setSelectedValue(intSingleChoiceSetting.getValueAt(i));
        } else if (clickedItem instanceof SliderSetting) {
            SettingsItem clickedItem4 = getSettingsViewModel().getClickedItem();
            Intrinsics.checkNotNull(clickedItem4, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting");
            ((SliderSetting) clickedItem4).setSelectedValue(((Number) getSettingsViewModel().getSliderProgress().getValue()).intValue());
        } else if (clickedItem instanceof StringInputSetting) {
            SettingsItem clickedItem5 = getSettingsViewModel().getClickedItem();
            Intrinsics.checkNotNull(clickedItem5, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.StringInputSetting");
            StringInputSetting stringInputSetting = (StringInputSetting) clickedItem5;
            DialogEditTextBinding dialogEditTextBinding = this.stringInputBinding;
            if (dialogEditTextBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stringInputBinding");
                dialogEditTextBinding = null;
            }
            Object text = dialogEditTextBinding.editText.getText();
            if (text == null) {
                text = "";
            }
            stringInputSetting.setSelectedValue(text.toString());
        }
        closeDialog();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.type = requireArguments().getInt("Type");
        this.position = requireArguments().getInt("Position");
        if (getSettingsViewModel().getClickedItem() == null) {
            dismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        MaterialAlertDialogBuilder negativeButton;
        MaterialAlertDialogBuilder title;
        View root;
        int i = this.type;
        ViewBinding viewBinding = null;
        if (i == -1) {
            negativeButton = new MaterialAlertDialogBuilder(requireContext()).setMessage(R$string.reset_setting_confirmation).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SettingsDialogFragment.onCreateDialog$lambda$1(SettingsDialogFragment.this, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        } else {
            if (i == 5) {
                SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
                Intrinsics.checkNotNull(clickedItem, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.StringSingleChoiceSetting");
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(clickedItem);
                new MaterialAlertDialogBuilder(requireContext());
                throw null;
            }
            if (i != 9) {
                if (i == 11) {
                    DialogEditTextBinding inflate = DialogEditTextBinding.inflate(getLayoutInflater());
                    Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                    this.stringInputBinding = inflate;
                    SettingsItem clickedItem2 = getSettingsViewModel().getClickedItem();
                    Intrinsics.checkNotNull(clickedItem2, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.StringInputSetting");
                    StringInputSetting stringInputSetting = (StringInputSetting) clickedItem2;
                    DialogEditTextBinding dialogEditTextBinding = this.stringInputBinding;
                    if (dialogEditTextBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("stringInputBinding");
                        dialogEditTextBinding = null;
                    }
                    dialogEditTextBinding.editText.setText(StringInputSetting.getSelectedValue$default(stringInputSetting, false, 1, null));
                    title = new MaterialAlertDialogBuilder(requireContext()).setTitle((CharSequence) stringInputSetting.getTitle());
                    DialogEditTextBinding dialogEditTextBinding2 = this.stringInputBinding;
                    if (dialogEditTextBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("stringInputBinding");
                    } else {
                        viewBinding = dialogEditTextBinding2;
                    }
                    root = viewBinding.getRoot();
                } else if (i == 2) {
                    SettingsItem clickedItem3 = getSettingsViewModel().getClickedItem();
                    Intrinsics.checkNotNull(clickedItem3, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting");
                    SingleChoiceSetting singleChoiceSetting = (SingleChoiceSetting) clickedItem3;
                    negativeButton = new MaterialAlertDialogBuilder(requireContext()).setTitle((CharSequence) singleChoiceSetting.getTitle()).setSingleChoiceItems(singleChoiceSetting.getChoicesId(), getSelectionForSingleChoiceValue(singleChoiceSetting), (DialogInterface.OnClickListener) this);
                } else {
                    if (i != 3) {
                        Dialog onCreateDialog = super.onCreateDialog(bundle);
                        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "onCreateDialog(...)");
                        return onCreateDialog;
                    }
                    DialogSliderBinding inflate2 = DialogSliderBinding.inflate(getLayoutInflater());
                    Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
                    this.sliderBinding = inflate2;
                    SettingsItem clickedItem4 = getSettingsViewModel().getClickedItem();
                    Intrinsics.checkNotNull(clickedItem4, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting");
                    final SliderSetting sliderSetting = (SliderSetting) clickedItem4;
                    getSettingsViewModel().setSliderTextValue(SliderSetting.getSelectedValue$default(sliderSetting, false, 1, null), sliderSetting.getUnits());
                    DialogSliderBinding dialogSliderBinding = this.sliderBinding;
                    if (dialogSliderBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sliderBinding");
                        dialogSliderBinding = null;
                    }
                    Slider slider = dialogSliderBinding.slider;
                    slider.setValueFrom(sliderSetting.getMin());
                    slider.setValueTo(sliderSetting.getMax());
                    slider.setValue(((Number) getSettingsViewModel().getSliderProgress().getValue()).intValue());
                    slider.addOnChangeListener(new BaseOnChangeListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsDialogFragment$$ExternalSyntheticLambda1
                        @Override // com.google.android.material.slider.BaseOnChangeListener
                        public final void onValueChange(Slider slider2, float f, boolean z) {
                            SettingsDialogFragment.onCreateDialog$lambda$3$lambda$2(SettingsDialogFragment.this, sliderSetting, slider2, f, z);
                        }
                    });
                    title = new MaterialAlertDialogBuilder(requireContext()).setTitle((CharSequence) sliderSetting.getTitle());
                    DialogSliderBinding dialogSliderBinding2 = this.sliderBinding;
                    if (dialogSliderBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sliderBinding");
                    } else {
                        viewBinding = dialogSliderBinding2;
                    }
                    root = viewBinding.getRoot();
                }
                negativeButton = title.setView(root).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) this).setNegativeButton(R.string.cancel, this.defaultCancelListener);
            } else {
                SettingsItem clickedItem5 = getSettingsViewModel().getClickedItem();
                Intrinsics.checkNotNull(clickedItem5, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting");
                IntSingleChoiceSetting intSingleChoiceSetting = (IntSingleChoiceSetting) clickedItem5;
                negativeButton = new MaterialAlertDialogBuilder(requireContext()).setTitle((CharSequence) intSingleChoiceSetting.getTitle()).setSingleChoiceItems((CharSequence[]) intSingleChoiceSetting.getChoices(), intSingleChoiceSetting.getSelectedValueIndex(), (DialogInterface.OnClickListener) this);
            }
        }
        AlertDialog create = negativeButton.create();
        Intrinsics.checkNotNull(create);
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        int i = this.type;
        ViewBinding viewBinding = null;
        if (i == 3) {
            DialogSliderBinding dialogSliderBinding = this.sliderBinding;
            if (dialogSliderBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sliderBinding");
            } else {
                viewBinding = dialogSliderBinding;
            }
            return viewBinding.getRoot();
        }
        if (i != 11) {
            return super.onCreateView(inflater, viewGroup, bundle);
        }
        DialogEditTextBinding dialogEditTextBinding = this.stringInputBinding;
        if (dialogEditTextBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stringInputBinding");
        } else {
            viewBinding = dialogEditTextBinding;
        }
        return viewBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (this.type == 3) {
            StateFlow sliderTextValue = getSettingsViewModel().getSliderTextValue();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
            Lifecycle.State state = Lifecycle.State.CREATED;
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new SettingsDialogFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, state, sliderTextValue, null, this), 3, null);
            StateFlow sliderProgress = getSettingsViewModel().getSliderProgress();
            LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new SettingsDialogFragment$onViewCreated$$inlined$collect$default$2(viewLifecycleOwner2, state, sliderProgress, null, this), 3, null);
        }
    }
}
