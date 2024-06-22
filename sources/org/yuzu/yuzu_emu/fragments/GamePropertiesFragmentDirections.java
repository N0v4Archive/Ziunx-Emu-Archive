package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.R$id;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public abstract class GamePropertiesFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    private static final class ActionPerGamePropertiesFragmentToAddonsFragment implements NavDirections {
        private final int actionId;
        private final Game game;

        public ActionPerGamePropertiesFragmentToAddonsFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            this.game = game;
            this.actionId = R$id.action_perGamePropertiesFragment_to_addonsFragment;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionPerGamePropertiesFragmentToAddonsFragment) && Intrinsics.areEqual(this.game, ((ActionPerGamePropertiesFragmentToAddonsFragment) obj).game);
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
            return "ActionPerGamePropertiesFragmentToAddonsFragment(game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    private static final class ActionPerGamePropertiesFragmentToDriverManagerFragment implements NavDirections {
        private final int actionId = R$id.action_perGamePropertiesFragment_to_driverManagerFragment;
        private final Game game;

        public ActionPerGamePropertiesFragmentToDriverManagerFragment(Game game) {
            this.game = game;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionPerGamePropertiesFragmentToDriverManagerFragment) && Intrinsics.areEqual(this.game, ((ActionPerGamePropertiesFragmentToDriverManagerFragment) obj).game);
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
            return bundle;
        }

        public int hashCode() {
            Game game = this.game;
            if (game == null) {
                return 0;
            }
            return game.hashCode();
        }

        public String toString() {
            return "ActionPerGamePropertiesFragmentToDriverManagerFragment(game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    private static final class ActionPerGamePropertiesFragmentToGameInfoFragment implements NavDirections {
        private final int actionId;
        private final Game game;

        public ActionPerGamePropertiesFragmentToGameInfoFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            this.game = game;
            this.actionId = R$id.action_perGamePropertiesFragment_to_gameInfoFragment;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionPerGamePropertiesFragmentToGameInfoFragment) && Intrinsics.areEqual(this.game, ((ActionPerGamePropertiesFragmentToGameInfoFragment) obj).game);
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
            return "ActionPerGamePropertiesFragmentToGameInfoFragment(game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NavDirections actionPerGamePropertiesFragmentToAddonsFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            return new ActionPerGamePropertiesFragmentToAddonsFragment(game);
        }

        public final NavDirections actionPerGamePropertiesFragmentToDriverManagerFragment(Game game) {
            return new ActionPerGamePropertiesFragmentToDriverManagerFragment(game);
        }

        public final NavDirections actionPerGamePropertiesFragmentToGameInfoFragment(Game game) {
            Intrinsics.checkNotNullParameter(game, "game");
            return new ActionPerGamePropertiesFragmentToGameInfoFragment(game);
        }
    }
}
