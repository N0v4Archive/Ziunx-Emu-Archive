package org.yuzu.yuzu_emu.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class License implements Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new Creator();
    private final int copyrightId;
    private final int descriptionId;
    private final int licenseId;
    private final int linkId;
    private final int titleId;

    /* loaded from: classes.dex */
    public static final class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final License createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new License(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final License[] newArray(int i) {
            return new License[i];
        }
    }

    public License(int i, int i2, int i3, int i4, int i5) {
        this.titleId = i;
        this.descriptionId = i2;
        this.linkId = i3;
        this.copyrightId = i4;
        this.licenseId = i5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof License)) {
            return false;
        }
        License license = (License) obj;
        return this.titleId == license.titleId && this.descriptionId == license.descriptionId && this.linkId == license.linkId && this.copyrightId == license.copyrightId && this.licenseId == license.licenseId;
    }

    public final int getCopyrightId() {
        return this.copyrightId;
    }

    public final int getDescriptionId() {
        return this.descriptionId;
    }

    public final int getLicenseId() {
        return this.licenseId;
    }

    public final int getLinkId() {
        return this.linkId;
    }

    public final int getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.descriptionId)) * 31) + Integer.hashCode(this.linkId)) * 31) + Integer.hashCode(this.copyrightId)) * 31) + Integer.hashCode(this.licenseId);
    }

    public String toString() {
        return "License(titleId=" + this.titleId + ", descriptionId=" + this.descriptionId + ", linkId=" + this.linkId + ", copyrightId=" + this.copyrightId + ", licenseId=" + this.licenseId + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.titleId);
        out.writeInt(this.descriptionId);
        out.writeInt(this.linkId);
        out.writeInt(this.copyrightId);
        out.writeInt(this.licenseId);
    }
}
