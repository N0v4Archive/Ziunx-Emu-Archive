package org.yuzu.yuzu_emu.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.adapters.SetupAdapter;
import org.yuzu.yuzu_emu.databinding.PageSetupBinding;
import org.yuzu.yuzu_emu.model.HomeViewModel;
import org.yuzu.yuzu_emu.model.SetupCallback;
import org.yuzu.yuzu_emu.model.SetupPage;
import org.yuzu.yuzu_emu.model.StepState;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class SetupAdapter extends AbstractListAdapter {
    private final AppCompatActivity activity;

    /* loaded from: classes.dex */
    public final class SetupPageViewHolder extends AbstractViewHolder implements SetupCallback {
        private final PageSetupBinding binding;
        final /* synthetic */ SetupAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SetupPageViewHolder(SetupAdapter setupAdapter, PageSetupBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = setupAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1$lambda$0(SetupPage model, SetupPageViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            model.getButtonAction().invoke(this$0);
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final SetupPage model) {
            Intrinsics.checkNotNullParameter(model, "model");
            if (model.getStepCompleted().invoke() == StepState.COMPLETE) {
                ViewUtils viewUtils = ViewUtils.INSTANCE;
                MaterialButton buttonAction = this.binding.buttonAction;
                Intrinsics.checkNotNullExpressionValue(buttonAction, "buttonAction");
                viewUtils.setVisible(buttonAction, false, false);
                MaterialTextView textConfirmation = this.binding.textConfirmation;
                Intrinsics.checkNotNullExpressionValue(textConfirmation, "textConfirmation");
                ViewUtils.setVisible$default(viewUtils, textConfirmation, true, false, 2, null);
            }
            this.binding.icon.setImageDrawable(ResourcesCompat.getDrawable(this.this$0.getActivity().getResources(), model.getIconId(), this.this$0.getActivity().getTheme()));
            this.binding.textTitle.setText(this.this$0.getActivity().getResources().getString(model.getTitleId()));
            this.binding.textDescription.setText(Html.fromHtml(this.this$0.getActivity().getResources().getString(model.getDescriptionId()), 0));
            MaterialButton materialButton = this.binding.buttonAction;
            SetupAdapter setupAdapter = this.this$0;
            materialButton.setText(setupAdapter.getActivity().getResources().getString(model.getButtonTextId()));
            if (model.getButtonIconId() != 0) {
                materialButton.setIcon(ResourcesCompat.getDrawable(setupAdapter.getActivity().getResources(), model.getButtonIconId(), setupAdapter.getActivity().getTheme()));
            }
            materialButton.setIconGravity(model.getLeftAlignedIcon() ? 1 : 3);
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.SetupAdapter$SetupPageViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SetupAdapter.SetupPageViewHolder.bind$lambda$1$lambda$0(SetupPage.this, this, view);
                }
            });
        }

        @Override // org.yuzu.yuzu_emu.model.SetupCallback
        public void onStepCompleted() {
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialButton buttonAction = this.binding.buttonAction;
            Intrinsics.checkNotNullExpressionValue(buttonAction, "buttonAction");
            viewUtils.hideView(buttonAction, 200L);
            MaterialTextView textConfirmation = this.binding.textConfirmation;
            Intrinsics.checkNotNullExpressionValue(textConfirmation, "textConfirmation");
            viewUtils.showView(textConfirmation, 200L);
            ((HomeViewModel) new ViewModelProvider(this.this$0.getActivity()).get(HomeViewModel.class)).setShouldPageForward(true);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupAdapter(AppCompatActivity activity, List pages) {
        super(pages);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(pages, "pages");
        this.activity = activity;
    }

    public final AppCompatActivity getActivity() {
        return this.activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SetupPageViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        PageSetupBinding inflate = PageSetupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new SetupPageViewHolder(this, inflate);
    }
}
