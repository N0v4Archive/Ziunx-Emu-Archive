package org.yuzu.yuzu_emu.features.settings.ui;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.settings.model.Settings;
import org.yuzu.yuzu_emu.model.Game;

/* loaded from: classes.dex */
public final class SettingsActivityArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    private final Game game;
    private final Settings.MenuTag menuTag;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SettingsActivityArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(SettingsActivityArgs.class.getClassLoader());
            if (!bundle.containsKey("game")) {
                throw new IllegalArgumentException("Required argument \"game\" is missing and does not have an android:defaultValue");
            }
            if (!Parcelable.class.isAssignableFrom(Game.class) && !Serializable.class.isAssignableFrom(Game.class)) {
                throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            Game game = (Game) bundle.get("game");
            if (!bundle.containsKey("menuTag")) {
                throw new IllegalArgumentException("Required argument \"menuTag\" is missing and does not have an android:defaultValue");
            }
            if (Parcelable.class.isAssignableFrom(Settings.MenuTag.class) || Serializable.class.isAssignableFrom(Settings.MenuTag.class)) {
                Settings.MenuTag menuTag = (Settings.MenuTag) bundle.get("menuTag");
                if (menuTag != null) {
                    return new SettingsActivityArgs(game, menuTag);
                }
                throw new IllegalArgumentException("Argument \"menuTag\" is marked as non-null but was passed a null value.");
            }
            throw new UnsupportedOperationException(Settings.MenuTag.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
    }

    public SettingsActivityArgs(Game game, Settings.MenuTag menuTag) {
        Intrinsics.checkNotNullParameter(menuTag, "menuTag");
        this.game = game;
        this.menuTag = menuTag;
    }

    public static final SettingsActivityArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SettingsActivityArgs)) {
            return false;
        }
        SettingsActivityArgs settingsActivityArgs = (SettingsActivityArgs) obj;
        return Intrinsics.areEqual(this.game, settingsActivityArgs.game) && this.menuTag == settingsActivityArgs.menuTag;
    }

    public final Game getGame() {
        return this.game;
    }

    public int hashCode() {
        Game game = this.game;
        return ((game == null ? 0 : game.hashCode()) * 31) + this.menuTag.hashCode();
    }

    public String toString() {
        return "SettingsActivityArgs(game=" + this.game + ", menuTag=" + this.menuTag + ")";
    }
}
