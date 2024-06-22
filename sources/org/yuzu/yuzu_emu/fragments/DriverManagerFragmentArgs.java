package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class DriverManagerFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final Game game;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DriverManagerFragmentArgs fromBundle(Bundle bundle) {
            Game game;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(DriverManagerFragmentArgs.class.getClassLoader());
            if (!bundle.containsKey("game")) {
                game = null;
            } else {
                if (!Parcelable.class.isAssignableFrom(Game.class) && !Serializable.class.isAssignableFrom(Game.class)) {
                    throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                game = (Game) bundle.get("game");
            }
            return new DriverManagerFragmentArgs(game);
        }
    }

    public DriverManagerFragmentArgs(Game game) {
        this.game = game;
    }

    public static final DriverManagerFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DriverManagerFragmentArgs) && Intrinsics.areEqual(this.game, ((DriverManagerFragmentArgs) obj).game);
    }

    public final Game getGame() {
        return this.game;
    }

    public int hashCode() {
        Game game = this.game;
        if (game == null) {
            return 0;
        }
        return game.hashCode();
    }

    public String toString() {
        return "DriverManagerFragmentArgs(game=" + this.game + ")";
    }
}
