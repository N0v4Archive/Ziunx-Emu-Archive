package org.yuzu.yuzu_emu.fragments;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.HomeNavigationDirections;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.model.Game;
import org.yuzu.yuzu_emu.utils.SerializableHelper;

/* loaded from: classes.dex */
public final class LaunchGameDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private int selectedItem = 1;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LaunchGameDialogFragment newInstance(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            Bundle bundle = new Bundle();
            bundle.putParcelable("Game", game);
            LaunchGameDialogFragment launchGameDialogFragment = new LaunchGameDialogFragment();
            launchGameDialogFragment.setArguments(bundle);
            return launchGameDialogFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$0(Game game, LaunchGameDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        NavDirections actionGlobalEmulationActivity = HomeNavigationDirections.Companion.actionGlobalEmulationActivity(game, this$0.selectedItem != 0);
        Fragment requireParentFragment = this$0.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(requireParentFragment, "requireParentFragment(...)");
        FragmentKt.findNavController(requireParentFragment).navigate(actionGlobalEmulationActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(LaunchGameDialogFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialogInterface, "<anonymous parameter 0>");
        this$0.selectedItem = i;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Parcelable parcelable;
        Object parcelable2;
        SerializableHelper serializableHelper = SerializableHelper.INSTANCE;
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable2 = requireArguments.getParcelable("Game", Game.class);
            parcelable = (Parcelable) parcelable2;
        } else {
            Parcelable parcelable3 = requireArguments.getParcelable("Game");
            if (!(parcelable3 instanceof Game)) {
                parcelable3 = null;
            }
            parcelable = (Game) parcelable3;
        }
        final Game game = (Game) parcelable;
        String[] strArr = {getString(R$string.global), getString(R$string.custom)};
        if (bundle != null) {
            this.selectedItem = bundle.getInt("SelectedItem");
        }
        AlertDialog show = new MaterialAlertDialogBuilder(requireContext()).setTitle(R$string.launch_options).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.LaunchGameDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LaunchGameDialogFragment.onCreateDialog$lambda$0(Game.this, this, dialogInterface, i);
            }
        }).setSingleChoiceItems((CharSequence[]) strArr, 1, new DialogInterface.OnClickListener() { // from class: org.yuzu.yuzu_emu.fragments.LaunchGameDialogFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LaunchGameDialogFragment.onCreateDialog$lambda$1(LaunchGameDialogFragment.this, dialogInterface, i);
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
