package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class GamePropertiesFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final Game game;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GamePropertiesFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(GamePropertiesFragmentArgs.class.getClassLoader());
            if (!bundle.containsKey("game")) {
                throw new IllegalArgumentException("Required argument \"game\" is missing and does not have an android:defaultValue");
            }
            if (Parcelable.class.isAssignableFrom(Game.class) || Serializable.class.isAssignableFrom(Game.class)) {
                Game game = (Game) bundle.get("game");
                if (game != null) {
                    return new GamePropertiesFragmentArgs(game);
                }
                throw new IllegalArgumentException("Argument \"game\" is marked as non-null but was passed a null value.");
            }
            throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
    }

    public GamePropertiesFragmentArgs(Game game) {
        Intrinsics.checkNotNullParameter(game, "game");
        this.game = game;
    }

    public static final GamePropertiesFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GamePropertiesFragmentArgs) && Intrinsics.areEqual(this.game, ((GamePropertiesFragmentArgs) obj).game);
    }

    public final Game getGame() {
        return this.game;
    }

    public int hashCode() {
        return this.game.hashCode();
    }

    public String toString() {
        return "GamePropertiesFragmentArgs(game=" + this.game + ")";
    }
}
