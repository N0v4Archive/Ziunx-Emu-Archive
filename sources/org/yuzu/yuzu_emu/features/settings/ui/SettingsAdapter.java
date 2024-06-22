package org.yuzu.yuzu_emu.features.settings.ui;

import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.ViewKt;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$menu;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.SettingsNavigationDirections;
import org.yuzu.yuzu_emu.databinding.ListItemSettingBinding;
import org.yuzu.yuzu_emu.databinding.ListItemSettingInputBinding;
import org.yuzu.yuzu_emu.databinding.ListItemSettingSwitchBinding;
import org.yuzu.yuzu_emu.databinding.ListItemSettingsHeaderBinding;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.features.input.model.AnalogDirection;
import org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ButtonInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.DateTimeSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.InputProfileSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.InputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.IntSingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.ModifierInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.features.settings.model.view.SingleChoiceSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SliderSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.StringInputSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SubmenuSetting;
import org.yuzu.yuzu_emu.features.settings.model.view.SwitchSetting;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.DateTimeViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.HeaderViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.InputProfileViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.InputViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.RunnableViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.SettingViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.SingleChoiceViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.SliderViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.StringInputViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.SubmenuViewHolder;
import org.yuzu.yuzu_emu.features.settings.ui.viewholder.SwitchSettingViewHolder;
import org.yuzu.yuzu_emu.utils.ParamPackage;

/* loaded from: classes.dex */
public final class SettingsAdapter extends ListAdapter {
    private final Context context;
    private final Fragment fragment;

    /* loaded from: classes.dex */
    private static final class DiffCallback extends DiffUtil.ItemCallback {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(SettingsItem oldItem, SettingsItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getSetting().getKey(), newItem.getSetting().getKey());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(SettingsItem oldItem, SettingsItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getSetting().getKey(), newItem.getSetting().getKey());
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnalogDirection.values().length];
            try {
                iArr[AnalogDirection.Left.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnalogDirection.Right.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnalogDirection.Up.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnalogDirection.Down.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsAdapter(Fragment fragment, Context context) {
        super(new AsyncDifferConfig.Builder(new DiffCallback()).build());
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(context, "context");
        this.fragment = fragment;
        this.context = context;
    }

    private final SettingsViewModel getSettingsViewModel() {
        FragmentActivity requireActivity = this.fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return (SettingsViewModel) new ViewModelProvider(requireActivity).get(SettingsViewModel.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDateTimeClick$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDateTimeClick$lambda$1(MaterialDatePicker datePicker, MaterialTimePicker timePicker, DateTimeSetting item, SettingsAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(datePicker, "$datePicker");
        Intrinsics.checkNotNullParameter(timePicker, "$timePicker");
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object selection = datePicker.getSelection();
        Intrinsics.checkNotNull(selection);
        long j = 60;
        long longValue = (((Number) selection).longValue() / 1000) + (timePicker.getHour() * j * j) + (timePicker.getMinute() * j);
        if (DateTimeSetting.getValue$default(item, false, 1, null) != longValue) {
            this$0.notifyItemChanged(i);
            item.setValue(longValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$10(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("toggle", !z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onInputOptionsClick$lambda$15$lambda$11(InputSetting item, ParamPackage params, SettingsAdapter this$0, int i, PopupMenu popupMenu) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NativeInput.INSTANCE.setButtonParam(item.getPlayerIndex(), ((ButtonInputSetting) item).getNativeButton(), params);
        this$0.getSettingsViewModel().setAdapterItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$12(ParamPackage modifierParams, boolean z, ParamPackage stickParams, MenuItem it) {
        Intrinsics.checkNotNullParameter(modifierParams, "$modifierParams");
        Intrinsics.checkNotNullParameter(stickParams, "$stickParams");
        Intrinsics.checkNotNullParameter(it, "it");
        modifierParams.set("inverted", !z);
        stickParams.set("modifier", modifierParams.serialize());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$13(ParamPackage modifierParams, boolean z, ParamPackage stickParams, MenuItem it) {
        Intrinsics.checkNotNullParameter(modifierParams, "$modifierParams");
        Intrinsics.checkNotNullParameter(stickParams, "$stickParams");
        Intrinsics.checkNotNullParameter(it, "it");
        modifierParams.set("toggle", !z);
        stickParams.set("modifier", modifierParams.serialize());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onInputOptionsClick$lambda$15$lambda$14(InputSetting item, ParamPackage stickParams, SettingsAdapter this$0, int i, PopupMenu popupMenu) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(stickParams, "$stickParams");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NativeInput.INSTANCE.setStickParam(item.getPlayerIndex(), ((ModifierInputSetting) item).getNativeAnalog(), stickParams);
        this$0.getSettingsViewModel().setAdapterItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4.get("invert_y", "+"), "-") != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004f, code lost:
    
        r1 = "+";
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4.get("invert_x", "+"), "-") != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean onInputOptionsClick$lambda$15$lambda$2(org.yuzu.yuzu_emu.features.settings.model.view.InputSetting r3, org.yuzu.yuzu_emu.utils.ParamPackage r4, android.view.MenuItem r5) {
        /*
            java.lang.String r0 = "$item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "$params"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting r3 = (org.yuzu.yuzu_emu.features.settings.model.view.AnalogInputSetting) r3
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r5 = r3.getAnalogDirection()
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r0 = org.yuzu.yuzu_emu.features.input.model.AnalogDirection.Left
            java.lang.String r1 = "-"
            java.lang.String r2 = "+"
            if (r5 == r0) goto L43
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r5 = r3.getAnalogDirection()
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r0 = org.yuzu.yuzu_emu.features.input.model.AnalogDirection.Right
            if (r5 != r0) goto L26
            goto L43
        L26:
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r5 = r3.getAnalogDirection()
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r0 = org.yuzu.yuzu_emu.features.input.model.AnalogDirection.Up
            if (r5 == r0) goto L36
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r3 = r3.getAnalogDirection()
            org.yuzu.yuzu_emu.features.input.model.AnalogDirection r5 = org.yuzu.yuzu_emu.features.input.model.AnalogDirection.Down
            if (r3 != r5) goto L53
        L36:
            java.lang.String r3 = "invert_y"
            java.lang.String r5 = r4.get(r3, r2)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r1)
            if (r5 == 0) goto L50
            goto L4f
        L43:
            java.lang.String r3 = "invert_x"
            java.lang.String r5 = r4.get(r3, r2)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r1)
            if (r5 == 0) goto L50
        L4f:
            r1 = r2
        L50:
            r4.set(r3, r1)
        L53:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter.onInputOptionsClick$lambda$15$lambda$2(org.yuzu.yuzu_emu.features.settings.model.view.InputSetting, org.yuzu.yuzu_emu.utils.ParamPackage, android.view.MenuItem):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onInputOptionsClick$lambda$15$lambda$3(InputSetting item, ParamPackage params, SettingsAdapter this$0, PopupMenu popupMenu) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NativeInput.INSTANCE.setStickParam(item.getPlayerIndex(), ((AnalogInputSetting) item).getNativeAnalog(), params);
        this$0.getSettingsViewModel().setDatasetChanged(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$4(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("inverted", !z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$5(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("toggle", !z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$6(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("turbo", !z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$7(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("invert", !z ? "-" : "+");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$8(ParamPackage params, boolean z, MenuItem it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(it, "it");
        params.set("inverted", !z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onInputOptionsClick$lambda$15$lambda$9(SettingsAdapter this$0, SettingsAdapter$onInputOptionsClick$1$thresholdSetting$1 thresholdSetting, int i, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(thresholdSetting, "$thresholdSetting");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.onSliderClick(new SliderSetting(thresholdSetting, R$string.set_threshold, null, 0, null, 0, 0, null, 252, null), i);
        return true;
    }

    @Override // androidx.recyclerview.widget.ListAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getCurrentList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return ((SettingsItem) getCurrentList().get(i)).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SettingViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Object obj = getCurrentList().get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        holder.bind((SettingsItem) obj);
    }

    public final void onBooleanClick(SwitchSetting item, boolean z, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        item.setChecked(z);
        notifyItemChanged(i);
        getSettingsViewModel().setShouldReloadSettingsList(true);
    }

    public final void onClearClick(SettingsItem item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        item.getSetting().setGlobal(true);
        notifyItemChanged(i);
        getSettingsViewModel().setShouldReloadSettingsList(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        switch (i) {
            case 0:
                ListItemSettingsHeaderBinding inflate = ListItemSettingsHeaderBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                return new HeaderViewHolder(inflate, this);
            case 1:
                ListItemSettingSwitchBinding inflate2 = ListItemSettingSwitchBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
                return new SwitchSettingViewHolder(inflate2, this);
            case 2:
            case 5:
                ListItemSettingBinding inflate3 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate3, "inflate(...)");
                return new SingleChoiceViewHolder(inflate3, this);
            case 3:
                ListItemSettingBinding inflate4 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate4, "inflate(...)");
                return new SliderViewHolder(inflate4, this);
            case 4:
                ListItemSettingBinding inflate5 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate5, "inflate(...)");
                return new SubmenuViewHolder(inflate5, this);
            case 6:
                ListItemSettingBinding inflate6 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate6, "inflate(...)");
                return new DateTimeViewHolder(inflate6, this);
            case 7:
                ListItemSettingBinding inflate7 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate7, "inflate(...)");
                return new RunnableViewHolder(inflate7, this);
            case 8:
                ListItemSettingInputBinding inflate8 = ListItemSettingInputBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate8, "inflate(...)");
                return new InputViewHolder(inflate8, this);
            case 9:
                ListItemSettingBinding inflate9 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate9, "inflate(...)");
                return new SingleChoiceViewHolder(inflate9, this);
            case 10:
                ListItemSettingBinding inflate10 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate10, "inflate(...)");
                return new InputProfileViewHolder(inflate10, this);
            case 11:
                ListItemSettingBinding inflate11 = ListItemSettingBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate11, "inflate(...)");
                return new StringInputViewHolder(inflate11, this);
            default:
                ListItemSettingsHeaderBinding inflate12 = ListItemSettingsHeaderBinding.inflate(from);
                Intrinsics.checkNotNullExpressionValue(inflate12, "inflate(...)");
                return new HeaderViewHolder(inflate12, this);
        }
    }

    public final void onDateTimeClick(final DateTimeSetting item, final int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        long value$default = DateTimeSetting.getValue$default(item, false, 1, null) * 1000;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        calendar.setTimeInMillis(value$default);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        boolean is24HourFormat = DateFormat.is24HourFormat(this.context);
        final MaterialDatePicker build = MaterialDatePicker.Builder.datePicker().setSelection(Long.valueOf(value$default)).setTitleText(R$string.select_rtc_date).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        final MaterialTimePicker build2 = new MaterialTimePicker.Builder().setTimeFormat(is24HourFormat ? 1 : 0).setHour(calendar.get(11)).setMinute(calendar.get(12)).setTitleText(R$string.select_rtc_time).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        final Function1 function1 = new Function1() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$onDateTimeClick$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Long) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Long l) {
                Fragment fragment;
                MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
                fragment = this.fragment;
                materialTimePicker.show(fragment.getChildFragmentManager(), "TimePicker");
            }
        };
        build.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda0
            @Override // com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
            public final void onPositiveButtonClick(Object obj) {
                SettingsAdapter.onDateTimeClick$lambda$0(Function1.this, obj);
            }
        });
        build2.addOnPositiveButtonClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsAdapter.onDateTimeClick$lambda$1(MaterialDatePicker.this, build2, item, this, i, view);
            }
        });
        build.show(this.fragment.getChildFragmentManager(), "DatePicker");
    }

    public final void onInputClick(InputSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        InputDialogFragment.Companion.newInstance(getSettingsViewModel(), item, i).show(this.fragment.getChildFragmentManager(), "InputDialogFragment");
    }

    /* JADX WARN: Type inference failed for: r5v11, types: [org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$onInputOptionsClick$1$thresholdSetting$1] */
    public final void onInputOptionsClick(View anchor, final InputSetting item, final int i) {
        String str;
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(item, "item");
        PopupMenu popupMenu = new PopupMenu(this.context, anchor);
        popupMenu.getMenuInflater().inflate(R$menu.menu_input_options, popupMenu.getMenu());
        Menu menu = popupMenu.getMenu();
        MenuItem findItem = menu.findItem(R$id.invert_axis);
        MenuItem findItem2 = menu.findItem(R$id.invert_button);
        MenuItem findItem3 = menu.findItem(R$id.toggle_button);
        MenuItem findItem4 = menu.findItem(R$id.turbo_button);
        MenuItem findItem5 = menu.findItem(R$id.set_threshold);
        MenuItem findItem6 = menu.findItem(R$id.toggle_axis);
        if (item instanceof AnalogInputSetting) {
            AnalogInputSetting analogInputSetting = (AnalogInputSetting) item;
            final ParamPackage stickParam = NativeInput.INSTANCE.getStickParam(item.getPlayerIndex(), analogInputSetting.getNativeAnalog());
            findItem.setVisible(true);
            findItem.setCheckable(true);
            int i2 = WhenMappings.$EnumSwitchMapping$0[analogInputSetting.getAnalogDirection().ordinal()];
            if (i2 == 1 || i2 == 2) {
                str = "invert_x";
            } else {
                if (i2 != 3 && i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                str = "invert_y";
            }
            findItem.setChecked(Intrinsics.areEqual(stickParam.get(str, "+"), "-"));
            findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda2
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean onInputOptionsClick$lambda$15$lambda$2;
                    onInputOptionsClick$lambda$15$lambda$2 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$2(InputSetting.this, stickParam, menuItem);
                    return onInputOptionsClick$lambda$15$lambda$2;
                }
            });
            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda6
                @Override // android.widget.PopupMenu.OnDismissListener
                public final void onDismiss(PopupMenu popupMenu2) {
                    SettingsAdapter.onInputOptionsClick$lambda$15$lambda$3(InputSetting.this, stickParam, this, popupMenu2);
                }
            });
        } else if (item instanceof ButtonInputSetting) {
            final ParamPackage buttonParam = NativeInput.INSTANCE.getButtonParam(item.getPlayerIndex(), ((ButtonInputSetting) item).getNativeButton());
            if (buttonParam.has("code") || buttonParam.has("button") || buttonParam.has("hat")) {
                final boolean z = buttonParam.get("inverted", false);
                findItem2.setVisible(true);
                findItem2.setCheckable(true);
                findItem2.setChecked(z);
                findItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda7
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$4;
                        onInputOptionsClick$lambda$15$lambda$4 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$4(ParamPackage.this, z, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$4;
                    }
                });
                final boolean z2 = buttonParam.get("toggle", false);
                findItem3.setVisible(true);
                findItem3.setCheckable(true);
                findItem3.setChecked(z2);
                findItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda8
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$5;
                        onInputOptionsClick$lambda$15$lambda$5 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$5(ParamPackage.this, z2, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$5;
                    }
                });
                final boolean z3 = buttonParam.get("turbo", false);
                findItem4.setVisible(true);
                findItem4.setCheckable(true);
                findItem4.setChecked(z3);
                findItem4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda9
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$6;
                        onInputOptionsClick$lambda$15$lambda$6 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$6(ParamPackage.this, z3, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$6;
                    }
                });
            } else if (buttonParam.has("axis")) {
                final boolean areEqual = Intrinsics.areEqual(buttonParam.get("invert", "+"), "-");
                findItem.setVisible(true);
                findItem.setCheckable(true);
                findItem.setChecked(areEqual);
                findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda10
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$7;
                        onInputOptionsClick$lambda$15$lambda$7 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$7(ParamPackage.this, areEqual, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$7;
                    }
                });
                final boolean z4 = buttonParam.get("inverted", false);
                findItem2.setVisible(true);
                findItem2.setCheckable(true);
                findItem2.setChecked(z4);
                findItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda11
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$8;
                        onInputOptionsClick$lambda$15$lambda$8 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$8(ParamPackage.this, z4, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$8;
                    }
                });
                findItem5.setVisible(true);
                final ?? r5 = new AbstractIntSetting() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$onInputOptionsClick$1$thresholdSetting$1
                    private final String key = "";
                    private final int defaultValue = 50;

                    public Integer getDefaultValue() {
                        return Integer.valueOf(this.defaultValue);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public boolean getGlobal() {
                        return AbstractIntSetting.DefaultImpls.getGlobal(this);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
                    public int getInt(boolean z5) {
                        return (int) (ParamPackage.this.get("threshold", 0.5f) * 100);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public String getKey() {
                        return this.key;
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public String getPairedSettingKey() {
                        return AbstractIntSetting.DefaultImpls.getPairedSettingKey(this);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public String getValueAsString(boolean z5) {
                        return String.valueOf(getInt(z5));
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public boolean isRuntimeModifiable() {
                        return AbstractIntSetting.DefaultImpls.isRuntimeModifiable(this);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public boolean isSaveable() {
                        return AbstractIntSetting.DefaultImpls.isSaveable(this);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public boolean isSwitchable() {
                        return AbstractIntSetting.DefaultImpls.isSwitchable(this);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public void reset() {
                        setInt(getDefaultValue().intValue());
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractSetting
                    public void setGlobal(boolean z5) {
                        AbstractIntSetting.DefaultImpls.setGlobal(this, z5);
                    }

                    @Override // org.yuzu.yuzu_emu.features.settings.model.AbstractIntSetting
                    public void setInt(int i3) {
                        ParamPackage.this.set("threshold", i3 / 100);
                        NativeInput.INSTANCE.setButtonParam(item.getPlayerIndex(), ((ButtonInputSetting) item).getNativeButton(), ParamPackage.this);
                    }
                };
                findItem5.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda12
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$9;
                        onInputOptionsClick$lambda$15$lambda$9 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$9(SettingsAdapter.this, r5, i, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$9;
                    }
                });
                final boolean z5 = buttonParam.get("toggle", false);
                findItem6.setVisible(true);
                findItem6.setCheckable(true);
                findItem6.setChecked(z5);
                findItem6.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda13
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        boolean onInputOptionsClick$lambda$15$lambda$10;
                        onInputOptionsClick$lambda$15$lambda$10 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$10(ParamPackage.this, z5, menuItem);
                        return onInputOptionsClick$lambda$15$lambda$10;
                    }
                });
            }
            PopupMenu.OnDismissListener onDismissListener = new PopupMenu.OnDismissListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda14
                @Override // android.widget.PopupMenu.OnDismissListener
                public final void onDismiss(PopupMenu popupMenu2) {
                    SettingsAdapter.onInputOptionsClick$lambda$15$lambda$11(InputSetting.this, buttonParam, this, i, popupMenu2);
                }
            };
            popupMenu = popupMenu;
            popupMenu.setOnDismissListener(onDismissListener);
        } else if (item instanceof ModifierInputSetting) {
            final ParamPackage stickParam2 = NativeInput.INSTANCE.getStickParam(item.getPlayerIndex(), ((ModifierInputSetting) item).getNativeAnalog());
            final ParamPackage paramPackage = new ParamPackage(stickParam2.get("modifier", ""));
            final boolean z6 = paramPackage.get("inverted", false);
            findItem2.setVisible(true);
            findItem2.setCheckable(true);
            findItem2.setChecked(z6);
            findItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda3
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean onInputOptionsClick$lambda$15$lambda$12;
                    onInputOptionsClick$lambda$15$lambda$12 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$12(ParamPackage.this, z6, stickParam2, menuItem);
                    return onInputOptionsClick$lambda$15$lambda$12;
                }
            });
            final boolean z7 = paramPackage.get("toggle", false);
            findItem3.setVisible(true);
            findItem3.setCheckable(true);
            findItem3.setChecked(z7);
            findItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda4
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean onInputOptionsClick$lambda$15$lambda$13;
                    onInputOptionsClick$lambda$15$lambda$13 = SettingsAdapter.onInputOptionsClick$lambda$15$lambda$13(ParamPackage.this, z7, stickParam2, menuItem);
                    return onInputOptionsClick$lambda$15$lambda$13;
                }
            });
            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsAdapter$$ExternalSyntheticLambda5
                @Override // android.widget.PopupMenu.OnDismissListener
                public final void onDismiss(PopupMenu popupMenu2) {
                    SettingsAdapter.onInputOptionsClick$lambda$15$lambda$14(InputSetting.this, stickParam2, this, i, popupMenu2);
                }
            });
        }
        popupMenu.show();
    }

    public final void onInputProfileClick(InputProfileSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        InputProfileDialogFragment.Companion.newInstance(getSettingsViewModel(), item, i).show(this.fragment.getChildFragmentManager(), "InputProfileDialogFragment");
    }

    public final void onIntSingleChoiceClick(IntSingleChoiceSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        SettingsDialogFragment.Companion.newInstance(getSettingsViewModel(), item, 9, i).show(this.fragment.getChildFragmentManager(), "SettingsDialogFragment");
    }

    public final boolean onLongClick(SettingsItem item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        SettingsDialogFragment.Companion.newInstance(getSettingsViewModel(), item, -1, i).show(this.fragment.getChildFragmentManager(), "SettingsDialogFragment");
        return true;
    }

    public final void onSingleChoiceClick(SingleChoiceSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        SettingsDialogFragment.Companion.newInstance(getSettingsViewModel(), item, 2, i).show(this.fragment.getChildFragmentManager(), "SettingsDialogFragment");
    }

    public final void onSliderClick(SliderSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        SettingsDialogFragment.Companion.newInstance(getSettingsViewModel(), item, 3, i).show(this.fragment.getChildFragmentManager(), "SettingsDialogFragment");
    }

    public final void onStringInputClick(StringInputSetting item, int i) {
        Intrinsics.checkNotNullParameter(item, "item");
        SettingsDialogFragment.Companion.newInstance(getSettingsViewModel(), item, 11, i).show(this.fragment.getChildFragmentManager(), "SettingsDialogFragment");
    }

    public final void onSubmenuClick(SubmenuSetting item) {
        NavController findNavController;
        Intrinsics.checkNotNullParameter(item, "item");
        NavDirections actionGlobalSettingsFragment = SettingsNavigationDirections.Companion.actionGlobalSettingsFragment(item.getMenuKey(), null);
        View view = this.fragment.getView();
        if (view == null || (findNavController = ViewKt.findNavController(view)) == null) {
            return;
        }
        findNavController.navigate(actionGlobalSettingsFragment);
    }
}
