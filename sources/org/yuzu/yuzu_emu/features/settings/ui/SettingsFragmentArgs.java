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
public final class SettingsFragmentArgs implements NavArgs {
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

        public final SettingsFragmentArgs fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(SettingsFragmentArgs.class.getClassLoader());
            if (!bundle.containsKey("menuTag")) {
                throw new IllegalArgumentException("Required argument \"menuTag\" is missing and does not have an android:defaultValue");
            }
            if (!Parcelable.class.isAssignableFrom(Settings.MenuTag.class) && !Serializable.class.isAssignableFrom(Settings.MenuTag.class)) {
                throw new UnsupportedOperationException(Settings.MenuTag.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            Settings.MenuTag menuTag = (Settings.MenuTag) bundle.get("menuTag");
            if (menuTag == null) {
                throw new IllegalArgumentException("Argument \"menuTag\" is marked as non-null but was passed a null value.");
            }
            if (!bundle.containsKey("game")) {
                throw new IllegalArgumentException("Required argument \"game\" is missing and does not have an android:defaultValue");
            }
            if (Parcelable.class.isAssignableFrom(Game.class) || Serializable.class.isAssignableFrom(Game.class)) {
                return new SettingsFragmentArgs(menuTag, (Game) bundle.get("game"));
            }
            throw new UnsupportedOperationException(Game.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
    }

    public SettingsFragmentArgs(Settings.MenuTag menuTag, Game game) {
        Intrinsics.checkNotNullParameter(menuTag, "menuTag");
        this.menuTag = menuTag;
        this.game = game;
    }

    public static final SettingsFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SettingsFragmentArgs)) {
            return false;
        }
        SettingsFragmentArgs settingsFragmentArgs = (SettingsFragmentArgs) obj;
        return this.menuTag == settingsFragmentArgs.menuTag && Intrinsics.areEqual(this.game, settingsFragmentArgs.game);
    }

    public final Game getGame() {
        return this.game;
    }

    public final Settings.MenuTag getMenuTag() {
        return this.menuTag;
    }

    public int hashCode() {
        int hashCode = this.menuTag.hashCode() * 31;
        Game game = this.game;
        return hashCode + (game == null ? 0 : game.hashCode());
    }

    public String toString() {
        return "SettingsFragmentArgs(menuTag=" + this.menuTag + ", game=" + this.game + ")";
    }
}
