package org.yuzu.yuzu_emu.features.settings.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.transition.MaterialSharedAxis;
import info.debatty.java.stringsimilarity.Cosine;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$dimen;
import org.yuzu.yuzu_emu.databinding.FragmentSettingsSearchBinding;
import org.yuzu.yuzu_emu.features.settings.model.view.SettingsItem;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.ViewUtils;

/* loaded from: classes.dex */
public final class SettingsSearchFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private FragmentSettingsSearchBinding _binding;
    private SettingsAdapter settingsAdapter;
    private final Lazy settingsViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SettingsSearchFragment() {
        final Function0 function0 = null;
        this.settingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$special$$inlined$activityViewModels$default$3
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

    private final void focusSearch() {
        getBinding().searchText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(getBinding().searchText, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentSettingsSearchBinding getBinding() {
        FragmentSettingsSearchBinding fragmentSettingsSearchBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSettingsSearchBinding);
        return fragmentSettingsSearchBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SettingsViewModel getSettingsViewModel() {
        return (SettingsViewModel) this.settingsViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(SettingsSearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSettingsViewModel().setShouldNavigateBack(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(SettingsSearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.focusSearch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(SettingsSearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().searchText.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void search() {
        List<Pair> sortedWith;
        List emptyList;
        String lowerCase = getBinding().searchText.getText().toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        Button clearButton = getBinding().clearButton;
        Intrinsics.checkNotNullExpressionValue(clearButton, "clearButton");
        viewUtils.setVisible(clearButton, lowerCase.length() > 0, false);
        if (lowerCase.length() == 0) {
            LinearLayout noResultsView = getBinding().noResultsView;
            Intrinsics.checkNotNullExpressionValue(noResultsView, "noResultsView");
            viewUtils.setVisible(noResultsView, false, false);
            SettingsAdapter settingsAdapter = this.settingsAdapter;
            if (settingsAdapter != null) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                settingsAdapter.submitList(emptyList);
                return;
            }
            return;
        }
        HashMap settingsItems = SettingsItem.Companion.getSettingsItems();
        Cosine cosine = lowerCase.length() > 2 ? new Cosine() : new Cosine(1);
        ArrayList arrayList = new ArrayList();
        Iterator it = settingsItems.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String lowerCase2 = ((SettingsItem) entry.getValue()).getTitle().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            double similarity = cosine.similarity(lowerCase, lowerCase2);
            Pair pair = similarity > 0.08d ? new Pair(Double.valueOf(similarity), entry) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$search$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues((Double) ((Pair) obj2).getFirst(), (Double) ((Pair) obj).getFirst());
                return compareValues;
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Pair pair2 : sortedWith) {
            String pairedSettingKey = ((SettingsItem) ((Map.Entry) pair2.getSecond()).getValue()).getSetting().getPairedSettingKey();
            SettingsItem settingsItem = (!(pairedSettingKey.length() > 0) || NativeConfig.INSTANCE.getBoolean(pairedSettingKey, false)) ? (SettingsItem) ((Map.Entry) pair2.getSecond()).getValue() : null;
            if (settingsItem != null) {
                arrayList2.add(settingsItem);
            }
        }
        SettingsAdapter settingsAdapter2 = this.settingsAdapter;
        if (settingsAdapter2 != null) {
            settingsAdapter2.submitList(arrayList2);
        }
        ViewUtils viewUtils2 = ViewUtils.INSTANCE;
        LinearLayout noResultsView2 = getBinding().noResultsView;
        Intrinsics.checkNotNullExpressionValue(noResultsView2, "noResultsView");
        viewUtils2.setVisible(noResultsView2, arrayList2.isEmpty(), false);
    }

    private final void setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(getBinding().getRoot(), new OnApplyWindowInsetsListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$$ExternalSyntheticLambda3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat insets$lambda$9;
                insets$lambda$9 = SettingsSearchFragment.setInsets$lambda$9(SettingsSearchFragment.this, view, windowInsetsCompat);
                return insets$lambda$9;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat setInsets$lambda$9(SettingsSearchFragment this$0, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        int dimensionPixelSize = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_med);
        int dimensionPixelSize2 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_medlarge);
        int dimensionPixelSize3 = this$0.getResources().getDimensionPixelSize(R$dimen.spacing_chip);
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i = insets.left + insets2.left;
        int i2 = insets.right + insets2.right;
        RecyclerView settingsList = this$0.getBinding().settingsList;
        Intrinsics.checkNotNullExpressionValue(settingsList, "settingsList");
        settingsList.setPadding(settingsList.getPaddingLeft(), settingsList.getPaddingTop(), settingsList.getPaddingRight(), insets.bottom + dimensionPixelSize);
        FrameLayout frameSearch = this$0.getBinding().frameSearch;
        Intrinsics.checkNotNullExpressionValue(frameSearch, "frameSearch");
        int i3 = i + dimensionPixelSize2;
        int i4 = dimensionPixelSize2 + i2;
        frameSearch.setPadding(i3, insets.top + dimensionPixelSize3, i4, frameSearch.getPaddingBottom());
        LinearLayout noResultsView = this$0.getBinding().noResultsView;
        Intrinsics.checkNotNullExpressionValue(noResultsView, "noResultsView");
        noResultsView.setPadding(i, noResultsView.getPaddingTop(), i2, insets.bottom);
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        RecyclerView settingsList2 = this$0.getBinding().settingsList;
        Intrinsics.checkNotNullExpressionValue(settingsList2, "settingsList");
        viewUtils.updateMargins(settingsList2, (r13 & 1) != 0 ? -1 : i3, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i4, (r13 & 8) != 0 ? -1 : 0);
        MaterialDivider divider = this$0.getBinding().divider;
        Intrinsics.checkNotNullExpressionValue(divider, "divider");
        viewUtils.updateMargins(divider, (r13 & 1) != 0 ? -1 : i3, (r13 & 2) != 0 ? -1 : 0, (r13 & 4) != 0 ? -1 : i4, (r13 & 8) != 0 ? -1 : 0);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new MaterialSharedAxis(0, true));
        setReturnTransition(new MaterialSharedAxis(0, false));
        setReenterTransition(new MaterialSharedAxis(0, false));
        setExitTransition(new MaterialSharedAxis(0, true));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSettingsSearchBinding.inflate(getLayoutInflater());
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString("SearchText", getBinding().searchText.getText().toString());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (bundle != null) {
            getBinding().searchText.setText(bundle.getString("SearchText"));
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.settingsAdapter = new SettingsAdapter(this, requireContext);
        MaterialDividerItemDecoration materialDividerItemDecoration = new MaterialDividerItemDecoration(requireContext(), 1);
        materialDividerItemDecoration.setLastItemDecorated(false);
        RecyclerView recyclerView = getBinding().settingsList;
        recyclerView.setAdapter(this.settingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(materialDividerItemDecoration);
        focusSearch();
        getBinding().backButton.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsSearchFragment.onViewCreated$lambda$1(SettingsSearchFragment.this, view2);
            }
        });
        getBinding().searchBackground.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsSearchFragment.onViewCreated$lambda$2(SettingsSearchFragment.this, view2);
            }
        });
        getBinding().clearButton.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsSearchFragment.onViewCreated$lambda$3(SettingsSearchFragment.this, view2);
            }
        });
        EditText searchText = getBinding().searchText;
        Intrinsics.checkNotNullExpressionValue(searchText, "searchText");
        searchText.addTextChangedListener(new TextWatcher() { // from class: org.yuzu.yuzu_emu.features.settings.ui.SettingsSearchFragment$onViewCreated$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                FragmentSettingsSearchBinding binding;
                SettingsSearchFragment.this.search();
                binding = SettingsSearchFragment.this.getBinding();
                binding.settingsList.smoothScrollToPosition(0);
            }
        });
        StateFlow shouldReloadSettingsList = getSettingsViewModel().getShouldReloadSettingsList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new SettingsSearchFragment$onViewCreated$$inlined$collect$default$1(viewLifecycleOwner, Lifecycle.State.CREATED, shouldReloadSettingsList, null, this), 3, null);
        search();
        setInsets();
    }
}
