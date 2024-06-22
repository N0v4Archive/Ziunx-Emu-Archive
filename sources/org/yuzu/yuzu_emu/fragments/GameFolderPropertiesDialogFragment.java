package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.databinding.DialogFolderPropertiesBinding;
import org.yuzu.yuzu_emu.model.GameDir;
import org.yuzu.yuzu_emu.model.GamesViewModel;
import org.yuzu.yuzu_emu.utils.NativeConfig;
import org.yuzu.yuzu_emu.utils.SerializableHelper;

/* loaded from: classes.dex */
public final class GameFolderPropertiesDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private boolean deepScan;
    private final Lazy gamesViewModel$delegate;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GameFolderPropertiesDialogFragment newInstance(GameDir gameDir) {
            Intrinsics.checkNotNullParameter(gameDir, "gameDir");
            Bundle bundle = new Bundle();
            bundle.putParcelable("GameDir", gameDir);
            GameFolderPropertiesDialogFragment gameFolderPropertiesDialogFragment = new GameFolderPropertiesDialogFragment();
            gameFolderPropertiesDialogFragment.setArguments(bundle);
            return gameFolderPropertiesDialogFragment;
        }
    }

    public GameFolderPropertiesDialogFragment() {
        final Function0 function0 = null;
        this.gamesViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GamesViewModel.class), new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0() { // from class: org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment$special$$inlined$activityViewModels$default$3
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

    private final GamesViewModel getGamesViewModel() {
        return (GamesViewModel) this.gamesViewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(GameFolderPropertiesDialogFragment this$0, DialogFolderPropertiesBinding binding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        this$0.deepScan = binding.deepScanSwitch.isChecked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(GameFolderPropertiesDialogFragment this$0, GameDir gameDir, DialogFolderPropertiesBinding binding, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(gameDir, "$gameDir");
        Intrinsics.checkNotNullParameter(binding, "$binding");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        int indexOf = ((List) this$0.getGamesViewModel().getFolders().getValue()).indexOf(gameDir);
        if (indexOf != -1) {
            ((GameDir) ((List) this$0.getGamesViewModel().getFolders().getValue()).get(indexOf)).setDeepScan(binding.deepScanSwitch.isChecked());
            this$0.getGamesViewModel().updateGameDirs();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Object obj;
        Object parcelable;
        final DialogFolderPropertiesBinding inflate = DialogFolderPropertiesBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        SerializableHelper serializableHelper = SerializableHelper.INSTANCE;
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = requireArguments.getParcelable("GameDir", GameDir.class);
            obj = (Parcelable) parcelable;
        } else {
            Parcelable parcelable2 = requireArguments.getParcelable("GameDir");
            if (!(parcelable2 instanceof GameDir)) {
                parcelable2 = null;
            }
            obj = (GameDir) parcelable2;
        }
        Intrinsics.checkNotNull(obj);
        final GameDir gameDir = (GameDir) obj;
        inflate.deepScanSwitch.setChecked(bundle != null ? bundle.getBoolean("DeepScan") : gameDir.getDeepScan());
        this.deepScan = inflate.deepScanSwitch.isChecked();
        inflate.deepScanSwitch.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameFolderPropertiesDialogFragment.onCreateDialog$lambda$0(GameFolderPropertiesDialogFragment.this, inflate, view);
            }
        });
        AlertDialog show = new MaterialAlertDialogBuilder(requireContext()).setView((View) inflate.getRoot()).setTitle(R$string.game_folder_properties).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.GameFolderPropertiesDialogFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GameFolderPropertiesDialogFragment.onCreateDialog$lambda$1(GameFolderPropertiesDialogFragment.this, gameDir, inflate, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        Intrinsics.checkNotNullExpressionValue(show, "show(...)");
        return show;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("DeepScan", this.deepScan);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        NativeConfig.INSTANCE.saveGlobalConfig();
    }
}
