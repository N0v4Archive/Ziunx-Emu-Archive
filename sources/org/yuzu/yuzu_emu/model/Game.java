package org.yuzu.yuzu_emu.model;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.yuzu.yuzu_emu.NativeLibrary;
import org.yuzu.yuzu_emu.R$string;
import org.yuzu.yuzu_emu.YuzuApplication;
import org.yuzu.yuzu_emu.activities.EmulationActivity;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.FileUtil;

/* loaded from: classes.dex */
public final class Game implements Parcelable {
    private static final Set extensions;
    private final String developer;
    private final boolean isHomebrew;
    private final String path;
    private final String programId;
    private final String title;
    private String version;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<Game> CREATOR = new Creator();

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set getExtensions() {
            return Game.extensions;
        }

        public final KSerializer serializer() {
            return Game$$serializer.INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Game createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Game(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Game[] newArray(int i) {
            return new Game[i];
        }
    }

    static {
        List listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"xci", "nsp", "nca", "nro"});
        extensions = new HashSet(listOf);
    }

    public /* synthetic */ Game(int i, String str, String str2, String str3, String str4, String str5, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i, 2, Game$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.title = "";
        } else {
            this.title = str;
        }
        this.path = str2;
        if ((i & 4) == 0) {
            this.programId = "";
        } else {
            this.programId = str3;
        }
        if ((i & 8) == 0) {
            this.developer = "";
        } else {
            this.developer = str4;
        }
        if ((i & 16) == 0) {
            this.version = "";
        } else {
            this.version = str5;
        }
        if ((i & 32) == 0) {
            this.isHomebrew = false;
        } else {
            this.isHomebrew = z;
        }
    }

    public Game(String title, String path, String programId, String developer, String version, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(programId, "programId");
        Intrinsics.checkNotNullParameter(developer, "developer");
        Intrinsics.checkNotNullParameter(version, "version");
        this.title = title;
        this.path = path;
        this.programId = programId;
        this.developer = developer;
        this.version = version;
        this.isHomebrew = z;
    }

    public /* synthetic */ Game(String str, String str2, String str3, String str4, String str5, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? false : z);
    }

    public static final /* synthetic */ void write$Self$app_eaRelease(Game game, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || !Intrinsics.areEqual(game.title, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 0, game.title);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 1, game.path);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual(game.programId, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 2, game.programId);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || !Intrinsics.areEqual(game.developer, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 3, game.developer);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || !Intrinsics.areEqual(game.version, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 4, game.version);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || game.isHomebrew) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 5, game.isHomebrew);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(Game.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.yuzu.yuzu_emu.model.Game");
        Game game = (Game) obj;
        return Intrinsics.areEqual(this.title, game.title) && Intrinsics.areEqual(this.path, game.path) && Intrinsics.areEqual(this.programId, game.programId) && Intrinsics.areEqual(this.developer, game.developer) && Intrinsics.areEqual(this.version, game.version) && this.isHomebrew == game.isHomebrew;
    }

    public final String getAddonDir() {
        return DirectoryInitialization.INSTANCE.getUserDirectory() + "/load/" + getProgramIdHex() + "/";
    }

    public final String getDeveloper() {
        return this.developer;
    }

    public final String getKeyAddedToLibraryTime() {
        return this.path + "_AddedToLibraryTime";
    }

    public final String getKeyLastPlayedTime() {
        return this.path + "_LastPlayed";
    }

    public final Intent getLaunchIntent() {
        Intent intent = new Intent(YuzuApplication.Companion.getAppContext(), (Class<?>) EmulationActivity.class);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(this.path));
        return intent;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getProgramId() {
        return this.programId;
    }

    public final String getProgramIdHex() {
        int checkRadix;
        long parseLong = Long.parseLong(this.programId);
        if (parseLong == 0) {
            return "0";
        }
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String l = Long.toString(parseLong, checkRadix);
        Intrinsics.checkNotNullExpressionValue(l, "toString(...)");
        String upperCase = l.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return "0" + upperCase;
    }

    public final String getSaveDir() {
        return DirectoryInitialization.INSTANCE.getUserDirectory() + "/nand" + NativeLibrary.INSTANCE.getSavePath(this.programId);
    }

    public final String getSaveZipName() {
        String str = this.title;
        String string = YuzuApplication.Companion.getAppContext().getString(R$string.save_data);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return str + " " + lowerCase + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ".zip";
    }

    public final String getSettingsName() {
        int checkRadix;
        long parseLong = Long.parseLong(this.programId);
        if (parseLong == 0) {
            FileUtil fileUtil = FileUtil.INSTANCE;
            Uri parse = Uri.parse(this.path);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return fileUtil.getFilename(parse);
        }
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String l = Long.toString(parseLong, checkRadix);
        Intrinsics.checkNotNullExpressionValue(l, "toString(...)");
        String upperCase = l.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return "0" + upperCase;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((this.title.hashCode() * 31) + this.path.hashCode()) * 31) + this.programId.hashCode()) * 31) + this.developer.hashCode()) * 31) + this.version.hashCode()) * 31) + Boolean.hashCode(this.isHomebrew);
    }

    public final boolean isHomebrew() {
        return this.isHomebrew;
    }

    public final void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.title);
        out.writeString(this.path);
        out.writeString(this.programId);
        out.writeString(this.developer);
        out.writeString(this.version);
        out.writeInt(this.isHomebrew ? 1 : 0);
    }
}
