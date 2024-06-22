package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;

/* loaded from: classes.dex */
public final class FragmentAboutBinding implements ViewBinding {
    public final AppBarLayout appbarAbout;
    public final LinearLayout buttonContributors;
    public final Button buttonDiscord;
    public final Button buttonGithub;
    public final LinearLayout buttonLicenses;
    public final LinearLayout buttonVersionName;
    public final Button buttonWebsite;
    public final LinearLayout contentAbout;
    public final CoordinatorLayout coordinatorAbout;
    public final ImageView imageLogo;
    private final CoordinatorLayout rootView;
    public final NestedScrollView scrollAbout;
    public final MaterialTextView textVersionName;
    public final MaterialToolbar toolbarAbout;

    private FragmentAboutBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, LinearLayout linearLayout, Button button, Button button2, LinearLayout linearLayout2, LinearLayout linearLayout3, Button button3, LinearLayout linearLayout4, CoordinatorLayout coordinatorLayout2, ImageView imageView, NestedScrollView nestedScrollView, MaterialTextView materialTextView, MaterialToolbar materialToolbar) {
        this.rootView = coordinatorLayout;
        this.appbarAbout = appBarLayout;
        this.buttonContributors = linearLayout;
        this.buttonDiscord = button;
        this.buttonGithub = button2;
        this.buttonLicenses = linearLayout2;
        this.buttonVersionName = linearLayout3;
        this.buttonWebsite = button3;
        this.contentAbout = linearLayout4;
        this.coordinatorAbout = coordinatorLayout2;
        this.imageLogo = imageView;
        this.scrollAbout = nestedScrollView;
        this.textVersionName = materialTextView;
        this.toolbarAbout = materialToolbar;
    }

    public static FragmentAboutBinding bind(View view) {
        int i = R$id.appbar_about;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
        if (appBarLayout != null) {
            i = R$id.button_contributors;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R$id.button_discord;
                Button button = (Button) ViewBindings.findChildViewById(view, i);
                if (button != null) {
                    i = R$id.button_github;
                    Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                    if (button2 != null) {
                        i = R$id.button_licenses;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R$id.button_version_name;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout3 != null) {
                                i = R$id.button_website;
                                Button button3 = (Button) ViewBindings.findChildViewById(view, i);
                                if (button3 != null) {
                                    i = R$id.content_about;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout4 != null) {
                                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                                        i = R$id.image_logo;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView != null) {
                                            i = R$id.scroll_about;
                                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                                            if (nestedScrollView != null) {
                                                i = R$id.text_version_name;
                                                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                                if (materialTextView != null) {
                                                    i = R$id.toolbar_about;
                                                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                                                    if (materialToolbar != null) {
                                                        return new FragmentAboutBinding(coordinatorLayout, appBarLayout, linearLayout, button, button2, linearLayout2, linearLayout3, button3, linearLayout4, coordinatorLayout, imageView, nestedScrollView, materialTextView, materialToolbar);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentAboutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAboutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_about, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
