package org.yuzu.yuzu_emu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;
import org.yuzu.yuzu_emu.R$drawable;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.adapters.HomeSettingAdapter;
import org.yuzu.yuzu_emu.databinding.CardHomeOptionBinding;
import org.yuzu.yuzu_emu.fragments.MessageDialogFragment;
import org.yuzu.yuzu_emu.model.HomeSetting;
import org.yuzu.yuzu_emu.utils.ViewUtils;
import org.yuzu.yuzu_emu.viewholder.AbstractViewHolder;

/* loaded from: classes.dex */
public final class HomeSettingAdapter extends AbstractListAdapter {
    private final AppCompatActivity activity;
    private final LifecycleOwner viewLifecycle;

    /* loaded from: classes.dex */
    public final class HomeOptionViewHolder extends AbstractViewHolder {
        private final CardHomeOptionBinding binding;
        final /* synthetic */ HomeSettingAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HomeOptionViewHolder(HomeSettingAdapter homeSettingAdapter, CardHomeOptionBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = homeSettingAdapter;
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(HomeOptionViewHolder this$0, HomeSetting model, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(model, "$model");
            this$0.onClick(model);
        }

        private final void onClick(HomeSetting homeSetting) {
            MessageDialogFragment newInstance;
            if (((Boolean) homeSetting.isEnabled().invoke()).booleanValue()) {
                homeSetting.getOnClick().invoke();
            } else {
                newInstance = MessageDialogFragment.Companion.newInstance((r31 & 1) != 0 ? null : this.this$0.activity, (r31 & 2) != 0 ? 0 : homeSetting.getDisabledTitleId(), (r31 & 4) != 0 ? "" : null, (r31 & 8) != 0 ? 0 : homeSetting.getDisabledMessageId(), (r31 & 16) != 0 ? "" : null, (r31 & 32) != 0 ? 0 : 0, (r31 & 64) != 0, (r31 & 128) != 0 ? 0 : 0, (r31 & 256) != 0 ? "" : null, (r31 & 512) != 0 ? null : null, (r31 & 1024) != 0 ? false : false, (r31 & 2048) == 0 ? 0 : 0, (r31 & 4096) == 0 ? null : "", (r31 & 8192) == 0 ? null : null);
                newInstance.show(this.this$0.activity.getSupportFragmentManager(), "MessageDialogFragment");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateOptionDetails(String str) {
            if (str.length() > 0) {
                this.binding.optionDetail.setText(str);
                ViewUtils viewUtils = ViewUtils.INSTANCE;
                MaterialTextView optionDetail = this.binding.optionDetail;
                Intrinsics.checkNotNullExpressionValue(optionDetail, "optionDetail");
                ViewUtils.setVisible$default(viewUtils, optionDetail, true, false, 2, null);
            }
        }

        @Override // org.yuzu.yuzu_emu.viewholder.AbstractViewHolder
        public void bind(final HomeSetting model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.binding.optionTitle.setText(this.this$0.activity.getResources().getString(model.getTitleId()));
            this.binding.optionDescription.setText(this.this$0.activity.getResources().getString(model.getDescriptionId()));
            this.binding.optionIcon.setImageDrawable(ResourcesCompat.getDrawable(this.this$0.activity.getResources(), model.getIconId(), this.this$0.activity.getTheme()));
            if (model.getTitleId() == R$string.get_early_access) {
                CardHomeOptionBinding cardHomeOptionBinding = this.binding;
                cardHomeOptionBinding.optionLayout.setBackground(ContextCompat.getDrawable(cardHomeOptionBinding.optionCard.getContext(), R$drawable.premium_background));
            }
            if (!((Boolean) model.isEnabled().invoke()).booleanValue()) {
                this.binding.optionTitle.setAlpha(0.5f);
                this.binding.optionDescription.setAlpha(0.5f);
                this.binding.optionIcon.setAlpha(0.5f);
            }
            StateFlow details = model.getDetails();
            LifecycleOwner lifecycleOwner = this.this$0.viewLifecycle;
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new HomeSettingAdapter$HomeOptionViewHolder$bind$$inlined$collect$default$1(lifecycleOwner, Lifecycle.State.CREATED, details, null, this), 3, null);
            ViewUtils viewUtils = ViewUtils.INSTANCE;
            MaterialTextView optionDetail = this.binding.optionDetail;
            Intrinsics.checkNotNullExpressionValue(optionDetail, "optionDetail");
            ViewUtils.marquee$default(viewUtils, optionDetail, 0L, 1, null);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: org.yuzu.yuzu_emu.adapters.HomeSettingAdapter$HomeOptionViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeSettingAdapter.HomeOptionViewHolder.bind$lambda$1(HomeSettingAdapter.HomeOptionViewHolder.this, model, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeSettingAdapter(AppCompatActivity activity, LifecycleOwner viewLifecycle, List options) {
        super(options);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(viewLifecycle, "viewLifecycle");
        Intrinsics.checkNotNullParameter(options, "options");
        this.activity = activity;
        this.viewLifecycle = viewLifecycle;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public HomeOptionViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CardHomeOptionBinding inflate = CardHomeOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNull(inflate);
        return new HomeOptionViewHolder(this, inflate);
    }
}
