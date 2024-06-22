package org.yuzu.yuzu_emu.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.databinding.DialogLicenseBinding;
import org.yuzu.yuzu_emu.model.License;
import org.yuzu.yuzu_emu.utils.SerializableHelper;

/* loaded from: classes.dex */
public final class LicenseBottomSheetDialogFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private DialogLicenseBinding _binding;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LicenseBottomSheetDialogFragment newInstance(License license) {
            Intrinsics.checkNotNullParameter(license, "license");
            LicenseBottomSheetDialogFragment licenseBottomSheetDialogFragment = new LicenseBottomSheetDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("License", license);
            licenseBottomSheetDialogFragment.setArguments(bundle);
            return licenseBottomSheetDialogFragment;
        }
    }

    private final DialogLicenseBinding getBinding() {
        DialogLicenseBinding dialogLicenseBinding = this._binding;
        Intrinsics.checkNotNull(dialogLicenseBinding);
        return dialogLicenseBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = DialogLicenseBinding.inflate(getLayoutInflater());
        CoordinatorLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Object obj;
        Object parcelable;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Object parent = view.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior.from((View) parent).setState(6);
        SerializableHelper serializableHelper = SerializableHelper.INSTANCE;
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = requireArguments.getParcelable("License", License.class);
            obj = (Parcelable) parcelable;
        } else {
            Parcelable parcelable2 = requireArguments.getParcelable("License");
            if (!(parcelable2 instanceof License)) {
                parcelable2 = null;
            }
            obj = (License) parcelable2;
        }
        Intrinsics.checkNotNull(obj);
        License license = (License) obj;
        DialogLicenseBinding binding = getBinding();
        binding.textTitle.setText(license.getTitleId());
        binding.textLink.setText(license.getLinkId());
        binding.textCopyright.setText(license.getCopyrightId());
        binding.textLicense.setText(license.getLicenseId());
    }
}
