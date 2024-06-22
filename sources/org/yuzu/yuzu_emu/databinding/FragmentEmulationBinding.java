package org.yuzu.yuzu_emu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.R$layout;
import org.yuzu.yuzu_emu.overlay.InputOverlay;
import org.yuzu.yuzu_emu.views.FixedRatioSurfaceView;

/* loaded from: classes.dex */
public final class FragmentEmulationBinding implements ViewBinding {
    public final Button doneControlConfig;
    public final DrawerLayout drawerLayout;
    public final FrameLayout emulationContainer;
    public final NavigationView inGameMenu;
    public final FrameLayout inputContainer;
    public final LinearLayout linearLayout;
    public final ImageView loadingImage;
    public final MaterialCardView loadingIndicator;
    public final ConstraintLayout loadingLayout;
    public final LinearProgressIndicator loadingProgressIndicator;
    public final MaterialTextView loadingText;
    public final MaterialTextView loadingTitle;
    public final FrameLayout overlayContainer;
    private final DrawerLayout rootView;
    public final MaterialTextView showFpsText;
    public final MaterialTextView showThermalsText;
    public final FixedRatioSurfaceView surfaceEmulation;
    public final InputOverlay surfaceInputOverlay;

    private FragmentEmulationBinding(DrawerLayout drawerLayout, Button button, DrawerLayout drawerLayout2, FrameLayout frameLayout, NavigationView navigationView, FrameLayout frameLayout2, LinearLayout linearLayout, ImageView imageView, MaterialCardView materialCardView, ConstraintLayout constraintLayout, LinearProgressIndicator linearProgressIndicator, MaterialTextView materialTextView, MaterialTextView materialTextView2, FrameLayout frameLayout3, MaterialTextView materialTextView3, MaterialTextView materialTextView4, FixedRatioSurfaceView fixedRatioSurfaceView, InputOverlay inputOverlay) {
        this.rootView = drawerLayout;
        this.doneControlConfig = button;
        this.drawerLayout = drawerLayout2;
        this.emulationContainer = frameLayout;
        this.inGameMenu = navigationView;
        this.inputContainer = frameLayout2;
        this.linearLayout = linearLayout;
        this.loadingImage = imageView;
        this.loadingIndicator = materialCardView;
        this.loadingLayout = constraintLayout;
        this.loadingProgressIndicator = linearProgressIndicator;
        this.loadingText = materialTextView;
        this.loadingTitle = materialTextView2;
        this.overlayContainer = frameLayout3;
        this.showFpsText = materialTextView3;
        this.showThermalsText = materialTextView4;
        this.surfaceEmulation = fixedRatioSurfaceView;
        this.surfaceInputOverlay = inputOverlay;
    }

    public static FragmentEmulationBinding bind(View view) {
        int i = R$id.done_control_config;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            i = R$id.emulation_container;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R$id.in_game_menu;
                NavigationView navigationView = (NavigationView) ViewBindings.findChildViewById(view, i);
                if (navigationView != null) {
                    i = R$id.input_container;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout2 != null) {
                        i = R$id.linearLayout;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R$id.loading_image;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                i = R$id.loading_indicator;
                                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
                                if (materialCardView != null) {
                                    i = R$id.loading_layout;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout != null) {
                                        i = R$id.loading_progress_indicator;
                                        LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
                                        if (linearProgressIndicator != null) {
                                            i = R$id.loading_text;
                                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                            if (materialTextView != null) {
                                                i = R$id.loading_title;
                                                MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                                if (materialTextView2 != null) {
                                                    i = R$id.overlay_container;
                                                    FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                    if (frameLayout3 != null) {
                                                        i = R$id.show_fps_text;
                                                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                                        if (materialTextView3 != null) {
                                                            i = R$id.show_thermals_text;
                                                            MaterialTextView materialTextView4 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                                            if (materialTextView4 != null) {
                                                                i = R$id.surface_emulation;
                                                                FixedRatioSurfaceView fixedRatioSurfaceView = (FixedRatioSurfaceView) ViewBindings.findChildViewById(view, i);
                                                                if (fixedRatioSurfaceView != null) {
                                                                    i = R$id.surface_input_overlay;
                                                                    InputOverlay inputOverlay = (InputOverlay) ViewBindings.findChildViewById(view, i);
                                                                    if (inputOverlay != null) {
                                                                        return new FragmentEmulationBinding(drawerLayout, button, drawerLayout, frameLayout, navigationView, frameLayout2, linearLayout, imageView, materialCardView, constraintLayout, linearProgressIndicator, materialTextView, materialTextView2, frameLayout3, materialTextView3, materialTextView4, fixedRatioSurfaceView, inputOverlay);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static FragmentEmulationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentEmulationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.fragment_emulation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public DrawerLayout getRoot() {
        return this.rootView;
    }
}
