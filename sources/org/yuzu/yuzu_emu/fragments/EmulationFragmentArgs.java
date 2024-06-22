package org.yuzu.yuzu_emu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class EmulationFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final boolean custom;
    private final Game game;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EmulationFragmentArgs fromBundle(Bundle bundle) {
            Game game;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(EmulationFragmentArgs.class.getClassLoader());
            if (!bundle.containsKey("game")) {
                game = null;
            } else {
                if (!Parcelable.class.isAssignableFrom(Game.class) && !Serializable.class.isAssignableFrom(Game.class)) {
                    throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                }
                game = (Game) bundle.get("game");
            }
            return new EmulationFragmentArgs(game, bundle.containsKey("custom") ? bundle.getBoolean("custom") : false);
        }
    }

    public EmulationFragmentArgs(Game game, boolean z) {
        this.game = game;
        this.custom = z;
    }

    public static final EmulationFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmulationFragmentArgs)) {
            return false;
        }
        EmulationFragmentArgs emulationFragmentArgs = (EmulationFragmentArgs) obj;
        return Intrinsics.areEqual(this.game, emulationFragmentArgs.game) && this.custom == emulationFragmentArgs.custom;
    }

    public final boolean getCustom() {
        return this.custom;
    }

    public final Game getGame() {
        return this.game;
    }

    public int hashCode() {
        Game game = this.game;
        return ((game == null ? 0 : game.hashCode()) * 31) + Boolean.hashCode(this.custom);
    }

    public String toString() {
        return "EmulationFragmentArgs(game=" + this.game + ", custom=" + this.custom + ")";
    }
}
