package org.yuzu.yuzu_emu.features.settings.ui;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.List;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogMappingBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.YuzuPhysicalDevice;
import org.yuzu.yuzu_emu.features.input.model.NativeAnalog;
import org.yuzu.yuzu_emu.features.input.model.NativeButton;
import org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ButtonInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.InputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ModifierInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.utils.InputHandler;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class InputDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogMappingBinding binding;
    private boolean inputAccepted;
    private InputSetting inputSetting;
    private int position;
    private final Lazy settingsViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InputDialogFragment newInstance(SettingsViewModel inputMappingViewModel, InputSetting setting, int i) {
            Intrinsics.checkNotNullParameter(inputMappingViewModel, "inputMappingViewModel");
            Intrinsics.checkNotNullParameter(setting, "setting");
            inputMappingViewModel.setClickedItem(setting);
            Bundle bundle = new Bundle();
            bundle.putInt("Position", i);
            InputDialogFragment inputDialogFragment = new InputDialogFragment();
            inputDialogFragment.setArguments(bundle);
            return inputDialogFragment;
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NativeAnalog.values().length];
            try {
                iArr[NativeAnalog.LStick.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NativeAnalog.RStick.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public InputDialogFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$special$$inlined$activityViewModels$default$3
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

    private final ParamPackage adjustAnalogParam(ParamPackage paramPackage, ParamPackage paramPackage2, String str) {
        if (paramPackage.has("axis_x") && paramPackage.has("axis_y")) {
            return paramPackage;
        }
        if (!paramPackage2.has("engine") || paramPackage2.has("axis_x") || paramPackage2.has("axis_y")) {
            paramPackage2.clear();
            paramPackage2.set("engine", "analog_from_button");
        }
        paramPackage2.set(str, paramPackage.serialize());
        return paramPackage2;
    }

    private final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    private final boolean isInputAcceptable(ParamPackage paramPackage) {
        if (InputHandler.INSTANCE.getRegisteredControllers().size() == 1 || paramPackage.has("motion")) {
            return true;
        }
        ParamPackage currentDeviceParams = getSettingsViewModel().getCurrentDeviceParams(paramPackage);
        if (Intrinsics.areEqual(currentDeviceParams.get("engine", "any"), "any")) {
            return true;
        }
        return Intrinsics.areEqual(paramPackage.get("engine", ""), currentDeviceParams.get("engine", "")) && (Intrinsics.areEqual(paramPackage.get("guid", ""), currentDeviceParams.get("guid", "")) || Intrinsics.areEqual(paramPackage.get("guid", ""), currentDeviceParams.get("guid2", ""))) && paramPackage.get("port", 0) == currentDeviceParams.get("port", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(InputDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NativeInput.INSTANCE.stopMapping();
        this$0.dismiss();
    }

    private final void onInputReceived(InputDevice inputDevice) {
        ParamPackage paramPackage = new ParamPackage(NativeInput.INSTANCE.getNextInput());
        if (paramPackage.has("engine") && isInputAcceptable(paramPackage) && !this.inputAccepted) {
            this.inputAccepted = true;
            setResult(paramPackage, inputDevice);
        }
    }

    private final boolean onKeyEvent(KeyEvent keyEvent) {
        int i;
        if ((keyEvent.getSource() & 16777232) != 16777232 && (keyEvent.getSource() & 1025) != 1025) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            i = 1;
        } else {
            if (action != 1) {
                return false;
            }
            i = 0;
        }
        YuzuPhysicalDevice yuzuPhysicalDevice = (YuzuPhysicalDevice) InputHandler.INSTANCE.getAndroidControllers().get(Integer.valueOf(keyEvent.getDevice().getControllerNumber()));
        if (yuzuPhysicalDevice == null) {
            return false;
        }
        NativeInput.INSTANCE.onGamePadButtonEvent(yuzuPhysicalDevice.getGUID(), yuzuPhysicalDevice.getPort(), keyEvent.getKeyCode(), i);
        InputDevice device = keyEvent.getDevice();
        Intrinsics.checkNotNullExpressionValue(device, "getDevice(...)");
        onInputReceived(device);
        return true;
    }

    private final boolean onMotionEvent(MotionEvent motionEvent) {
        YuzuPhysicalDevice yuzuPhysicalDevice;
        if (((motionEvent.getSource() & 16777232) != 16777232 && (motionEvent.getSource() & 1025) != 1025) || (yuzuPhysicalDevice = (YuzuPhysicalDevice) InputHandler.INSTANCE.getAndroidControllers().get(Integer.valueOf(motionEvent.getDevice().getControllerNumber()))) == null) {
            return false;
        }
        List<InputDevice.MotionRange> motionRanges = motionEvent.getDevice().getMotionRanges();
        Intrinsics.checkNotNullExpressionValue(motionRanges, "getMotionRanges(...)");
        for (InputDevice.MotionRange motionRange : motionRanges) {
            NativeInput.INSTANCE.onGamePadAxisEvent(yuzuPhysicalDevice.getGUID(), yuzuPhysicalDevice.getPort(), motionRange.getAxis(), motionEvent.getAxisValue(motionRange.getAxis()));
            InputDevice device = motionEvent.getDevice();
            Intrinsics.checkNotNullExpressionValue(device, "getDevice(...)");
            onInputReceived(device);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(View view, boolean z) {
        if (z) {
            return;
        }
        view.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$2(InputDialogFragment this$0, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(keyEvent);
        return this$0.onKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$3(InputDialogFragment this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(motionEvent);
        return this$0.onMotionEvent(motionEvent);
    }

    private final void setResult(ParamPackage paramPackage, InputDevice inputDevice) {
        NativeInput nativeInput = NativeInput.INSTANCE;
        nativeInput.stopMapping();
        paramPackage.set("display", inputDevice.getName() + " " + paramPackage.get("port", 0));
        SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
        Intrinsics.checkNotNull(clickedItem, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.InputSetting");
        InputSetting inputSetting = (InputSetting) clickedItem;
        if (inputSetting instanceof ModifierInputSetting ? true : inputSetting instanceof ButtonInputSetting) {
            InputSetting inputSetting2 = this.inputSetting;
            if (inputSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("inputSetting");
                inputSetting2 = null;
            }
            ButtonInputSetting buttonInputSetting = inputSetting2 instanceof ButtonInputSetting ? (ButtonInputSetting) inputSetting2 : null;
            if (buttonInputSetting != null && (buttonInputSetting.getNativeButton() == NativeButton.DUp || (buttonInputSetting.getNativeButton() == NativeButton.DLeft && paramPackage.has("axis")))) {
                paramPackage.set("invert", "-");
            }
            inputSetting.setSelectedValue(paramPackage);
            getSettingsViewModel().setAdapterItemChanged(this.position);
        } else if (inputSetting instanceof AnalogInputSetting) {
            AnalogInputSetting analogInputSetting = (AnalogInputSetting) inputSetting;
            ParamPackage adjustAnalogParam = adjustAnalogParam(paramPackage, nativeInput.getStickParam(inputSetting.getPlayerIndex(), analogInputSetting.getNativeAnalog()), analogInputSetting.getAnalogDirection().getParam());
            adjustAnalogParam.set("invert_y", "-");
            inputSetting.setSelectedValue(adjustAnalogParam);
            getSettingsViewModel().setReloadListAndNotifyDataset(true);
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSettingsViewModel().getClickedItem() == null) {
            dismiss();
        }
        this.position = requireArguments().getInt("Position");
        InputHandler.INSTANCE.updateControllerData();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Boolean bool;
        String string;
        SettingsItem clickedItem = getSettingsViewModel().getClickedItem();
        Intrinsics.checkNotNull(clickedItem, "null cannot be cast to non-null type org.yuzu.yuzu_emu.features.settings.model.view.InputSetting");
        this.inputSetting = (InputSetting) clickedItem;
        DialogMappingBinding inflate = DialogMappingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        MaterialAlertDialogBuilder positiveButton = new MaterialAlertDialogBuilder(requireContext()).setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                InputDialogFragment.onCreateDialog$lambda$0(InputDialogFragment.this, dialogInterface, i);
            }
        });
        DialogMappingBinding dialogMappingBinding = this.binding;
        InputSetting inputSetting = null;
        if (dialogMappingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogMappingBinding = null;
        }
        MaterialAlertDialogBuilder view = positiveButton.setView((View) dialogMappingBinding.getRoot());
        Intrinsics.checkNotNullExpressionValue(view, "setView(...)");
        Function1 function1 = new Function1() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$onCreateDialog$playButtonMapAnimation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Boolean) obj).booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                DialogMappingBinding dialogMappingBinding2;
                DialogMappingBinding dialogMappingBinding3;
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
                dialogMappingBinding2 = InputDialogFragment.this.binding;
                DialogMappingBinding dialogMappingBinding4 = null;
                if (dialogMappingBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    dialogMappingBinding2 = null;
                }
                ImageView imageView = dialogMappingBinding2.imageStickAnimation;
                imageView.setBackgroundResource(z ? R$drawable.stick_two_direction_anim : R$drawable.stick_one_direction_anim);
                Drawable background = imageView.getBackground();
                Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
                ref$ObjectRef.element = (AnimatedVectorDrawable) background;
                dialogMappingBinding3 = InputDialogFragment.this.binding;
                if (dialogMappingBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogMappingBinding4 = dialogMappingBinding3;
                }
                ImageView imageView2 = dialogMappingBinding4.imageButtonAnimation;
                imageView2.setBackgroundResource(R$drawable.button_anim);
                Drawable background2 = imageView2.getBackground();
                Intrinsics.checkNotNull(background2, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
                ref$ObjectRef2.element = (AnimatedVectorDrawable) background2;
                ((AnimatedVectorDrawable) ref$ObjectRef.element).registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$onCreateDialog$playButtonMapAnimation$1.3
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public void onAnimationEnd(Drawable drawable) {
                        ((AnimatedVectorDrawable) Ref$ObjectRef.this.element).start();
                    }
                });
                ((AnimatedVectorDrawable) ref$ObjectRef2.element).registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$onCreateDialog$playButtonMapAnimation$1.4
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public void onAnimationEnd(Drawable drawable) {
                        ((AnimatedVectorDrawable) Ref$ObjectRef.this.element).start();
                    }
                });
                ((AnimatedVectorDrawable) ref$ObjectRef.element).start();
            }
        };
        InputSetting inputSetting2 = this.inputSetting;
        if (inputSetting2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inputSetting");
        } else {
            inputSetting = inputSetting2;
        }
        if (inputSetting instanceof AnalogInputSetting) {
            int i = WhenMappings.$EnumSwitchMapping$0[((AnalogInputSetting) inputSetting).getNativeAnalog().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    string = getString(R$string.map_control, getString(R$string.right_stick));
                }
                view.setMessage(R$string.stick_map_description);
                bool = Boolean.TRUE;
            } else {
                string = getString(R$string.map_control, getString(R$string.left_stick));
            }
            view.setTitle((CharSequence) string);
            view.setMessage(R$string.stick_map_description);
            bool = Boolean.TRUE;
        } else {
            if (!(inputSetting instanceof ModifierInputSetting)) {
                if (inputSetting instanceof ButtonInputSetting) {
                    ButtonInputSetting buttonInputSetting = (ButtonInputSetting) inputSetting;
                    view.setTitle((CharSequence) ((buttonInputSetting.getNativeButton() == NativeButton.DUp || buttonInputSetting.getNativeButton() == NativeButton.DDown || buttonInputSetting.getNativeButton() == NativeButton.DLeft || buttonInputSetting.getNativeButton() == NativeButton.DRight) ? getString(R$string.map_dpad_direction, inputSetting.getTitle()) : getString(R$string.map_control, inputSetting.getTitle())));
                    view.setMessage(R$string.button_map_description);
                }
                AlertDialog create = view.create();
                Intrinsics.checkNotNullExpressionValue(create, "create(...)");
                return create;
            }
            view.setTitle((CharSequence) getString(R$string.map_control, inputSetting.getTitle())).setMessage(R$string.button_map_description);
            bool = Boolean.FALSE;
        }
        function1.invoke(bool);
        AlertDialog create2 = view.create();
        Intrinsics.checkNotNullExpressionValue(create2, "create(...)");
        return create2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogMappingBinding dialogMappingBinding = this.binding;
        if (dialogMappingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogMappingBinding = null;
        }
        LinearLayout root = dialogMappingBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        view.requestFocus();
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z) {
                InputDialogFragment.onViewCreated$lambda$1(view2, z);
            }
        });
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    boolean onViewCreated$lambda$2;
                    onViewCreated$lambda$2 = InputDialogFragment.onViewCreated$lambda$2(InputDialogFragment.this, dialogInterface, i, keyEvent);
                    return onViewCreated$lambda$2;
                }
            });
        }
        DialogMappingBinding dialogMappingBinding = this.binding;
        InputSetting inputSetting = null;
        if (dialogMappingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogMappingBinding = null;
        }
        dialogMappingBinding.getRoot().setOnGenericMotionListener(new View.OnGenericMotionListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.InputDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnGenericMotionListener
            public final boolean onGenericMotion(View view2, MotionEvent motionEvent) {
                boolean onViewCreated$lambda$3;
                onViewCreated$lambda$3 = InputDialogFragment.onViewCreated$lambda$3(InputDialogFragment.this, view2, motionEvent);
                return onViewCreated$lambda$3;
            }
        });
        NativeInput nativeInput = NativeInput.INSTANCE;
        InputSetting inputSetting2 = this.inputSetting;
        if (inputSetting2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inputSetting");
        } else {
            inputSetting = inputSetting2;
        }
        nativeInput.beginMapping(inputSetting.getInputType().getInt());
    }
}
