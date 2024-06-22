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
public abstract class HomeNavigationDirections {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ActionGlobalEmulationActivity implements NavDirections {
        private final int actionId = R$id.action_global_emulationActivity;
        private final boolean custom;
        private final Game game;

        public ActionGlobalEmulationActivity(Game game, boolean z) {
            this.game = game;
            this.custom = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionGlobalEmulationActivity)) {
                return false;
            }
            ActionGlobalEmulationActivity actionGlobalEmulationActivity = (ActionGlobalEmulationActivity) obj;
            return Intrinsics.areEqual(this.game, actionGlobalEmulationActivity.game) && this.custom == actionGlobalEmulationActivity.custom;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (Parcelable.class.isAssignableFrom(Game.class)) {
                bundle.putParcelable("game", this.game);
            } else if (Serializable.class.isAssignableFrom(Game.class)) {
                bundle.putSerializable("game", (Serializable) this.game);
            }
            bundle.putBoolean("custom", this.custom);
            return bundle;
        }

        public int hashCode() {
            Game game = this.game;
            return ((game == null ? 0 : game.hashCode()) * 31) + Boolean.hashCode(this.custom);
        }

        public String toString() {
            return "ActionGlobalEmulationActivity(game=" + this.game + ", custom=" + this.custom + ")";
        }
    }

    /* loaded from: classes.dex */
    private static final class ActionGlobalPerGamePropertiesFragment implements NavDirections {
        private final int actionId;
        private final Game game;

        public ActionGlobalPerGamePropertiesFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            this.game = game;
            this.actionId = R$id.action_global_perGamePropertiesFragment;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionGlobalPerGamePropertiesFragment) && Intrinsics.areEqual(this.game, ((ActionGlobalPerGamePropertiesFragment) obj).game);
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (Parcelable.class.isAssignableFrom(Game.class)) {
                Game game = this.game;
                Intrinsics.checkNotNull(game, "null cannot be cast to non-null type android.os.Parcelable");
                bundle.putParcelable("game", game);
            } else {
                if (!Serializable.class.isAssignableFrom(Game.class)) {
                    throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                Parcelable parcelable = this.game;
                Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type java.io.Serializable");
                bundle.putSerializable("game", (Serializable) parcelable);
            }
            return bundle;
        }

        public int hashCode() {
            return this.game.hashCode();
        }

        public String toString() {
            return "ActionGlobalPerGamePropertiesFragment(game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    private static final class ActionGlobalSettingsActivity implements NavDirections {
        private final int actionId;
        private final Game game;
        private final Settings.MenuTag menuTag;

        public ActionGlobalSettingsActivity(Game game, Settings.MenuTag menuTag) {
            Intrinsics.checkNotNullParameter(menuTag, "menuTag");
            this.game = game;
            this.menuTag = menuTag;
            this.actionId = R$id.action_global_settingsActivity;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionGlobalSettingsActivity)) {
                return false;
            }
            ActionGlobalSettingsActivity actionGlobalSettingsActivity = (ActionGlobalSettingsActivity) obj;
            return Intrinsics.areEqual(this.game, actionGlobalSettingsActivity.game) && this.menuTag == actionGlobalSettingsActivity.menuTag;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return this.actionId;
        }

        @Override // androidx.navigation.NavDirections
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (Parcelable.class.isAssignableFrom(Game.class)) {
                bundle.putParcelable("game", this.game);
            } else {
                if (!Serializable.class.isAssignableFrom(Game.class)) {
                    throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                bundle.putSerializable("game", (Serializable) this.game);
            }
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
            return bundle;
        }

        public int hashCode() {
            Game game = this.game;
            return ((game == null ? 0 : game.hashCode()) * 31) + this.menuTag.hashCode();
        }

        public String toString() {
            return "ActionGlobalSettingsActivity(game=" + this.game + ", menuTag=" + this.menuTag + ")";
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ NavDirections actionGlobalEmulationActivity$default(Companion companion, Game game, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                game = null;
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.actionGlobalEmulationActivity(game, z);
        }

        public final NavDirections actionGlobalEmulationActivity(Game game, boolean z) {
            return new ActionGlobalEmulationActivity(game, z);
        }

        public final NavDirections actionGlobalPerGamePropertiesFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            return new ActionGlobalPerGamePropertiesFragment(game);
        }

        public final NavDirections actionGlobalSettingsActivity(Game game, Settings.MenuTag menuTag) {
            Intrinsics.checkNotNullParameter(menuTag, "menuTag");
            return new ActionGlobalSettingsActivity(game, menuTag);
        }
    }
}
