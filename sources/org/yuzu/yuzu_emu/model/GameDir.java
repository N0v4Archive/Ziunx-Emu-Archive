package org.yuzu.yuzu_emu.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class GameDir implements Parcelable {
    public static final Parcelable.Creator<GameDir> CREATOR = new Creator();
    private boolean deepScan;
    private final String uriString;

    /* loaded from: classes.dex */
    public static final class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final GameDir createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new GameDir(parcel.readString(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final GameDir[] newArray(int i) {
            return new GameDir[i];
        }
    }

    public GameDir(String uriString, boolean z) {
        Intrinsics.checkNotNullParameter(uriString, "uriString");
        this.uriString = uriString;
        this.deepScan = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameDir)) {
            return false;
        }
        GameDir gameDir = (GameDir) obj;
        return Intrinsics.areEqual(this.uriString, gameDir.uriString) && this.deepScan == gameDir.deepScan;
    }

    public final boolean getDeepScan() {
        return this.deepScan;
    }

    public final String getUriString() {
        return this.uriString;
    }

    public int hashCode() {
        return (this.uriString.hashCode() * 31) + Boolean.hashCode(this.deepScan);
    }

    public final void setDeepScan(boolean z) {
        this.deepScan = z;
    }

    public String toString() {
        return "GameDir(uriString=" + this.uriString + ", deepScan=" + this.deepScan + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.uriString);
        out.writeInt(this.deepScan ? 1 : 0);
    }
}
