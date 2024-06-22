package org.yuzu.yuzu_emu;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public abstract class SettingsNavigationDirections {
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    private static final class ActionGlobalSettingsFragment implements NavDirections {
        private final int actionId;
        private final Game game;
        private final Settings.MenuTag menuTag;

        public ActionGlobalSettingsFragment(Settings.MenuTag menuTag, Game game) {
            Intrinsics.checkNotNullParameter(menuTag, "menuTag");
            this.menuTag = menuTag;
            this.game = game;
            this.actionId = R$id.action_global_settingsFragment;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionGlobalSettingsFragment)) {
                return false;
            }
            ActionGlobalSettingsFragment actionGlobalSettingsFragment = (ActionGlobalSettingsFragment) obj;
            return this.menuTag == actionGlobalSettingsFragment.menuTag && Intrinsics.areEqual(this.game, actionGlobalSettingsFragment.game);
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (Parcelable.class.isAssignableFrom(Settings.MenuTag.class)) {
                Object obj = this.menuTag;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.os.Parcelable");
                bundle.putParcelable("menuTag", (Parcelable) obj);
            } else {
                if (!Serializable.class.isAssignableFrom(Settings.MenuTag.class)) {
                    throw new UnsupportedOperationException(Settings.MenuTag.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                Settings.MenuTag menuTag = this.menuTag;
                Intrinsics.checkNotNull(menuTag, "null cannot be cast to non-null type java.io.Serializable");
                bundle.putSerializable("menuTag", menuTag);
            }
            if (Parcelable.class.isAssignableFrom(Game.class)) {
                bundle.putParcelable("game", this.game);
            } else {
                if (!Serializable.class.isAssignableFrom(Game.class)) {
                    throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                bundle.putSerializable("game", (Serializable) this.game);
            }
            return bundle;
        }

        public int hashCode() {
            int hashCode = this.menuTag.hashCode() * 31;
            Game game = this.game;
            return hashCode + (game == null ? 0 : game.hashCode());
        }

        public String toString() {
            return "ActionGlobalSettingsFragment(menuTag=" + this.menuTag + ", game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NavDirections actionGlobalSettingsFragment(Settings.MenuTag menuTag, Game game) {
            Intrinsics.checkNotNullParameter(menuTag, "menuTag");
            return new ActionGlobalSettingsFragment(menuTag, game);
        }
    }
}
