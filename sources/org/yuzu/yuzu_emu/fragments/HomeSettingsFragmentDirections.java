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
public abstract class HomeSettingsFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    private static final class ActionHomeSettingsFragmentToDriverManagerFragment implements NavDirections {
        private final int actionId = R$id.action_homeSettingsFragment_to_driverManagerFragment;
        private final Game game;

        public ActionHomeSettingsFragmentToDriverManagerFragment(Game game) {
            this.game = game;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ActionHomeSettingsFragmentToDriverManagerFragment) && Intrinsics.areEqual(this.game, ((ActionHomeSettingsFragmentToDriverManagerFragment) obj).game);
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
            return "ActionHomeSettingsFragmentToDriverManagerFragment(game=" + this.game + ")";
        }
    }

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NavDirections actionHomeSettingsFragmentToDriverManagerFragment(Game game) {
            return new ActionHomeSettingsFragmentToDriverManagerFragment(game);
        }
    }
}
