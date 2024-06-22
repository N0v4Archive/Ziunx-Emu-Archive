package org.yuzu.yuzu_emu.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.utils.GpuDriverMetadata;

/* loaded from: classes.dex */
public final class Driver implements SelectableItem {
    public static final Companion Companion = new Companion(null);
    private final String description;
    private boolean selected;
    private final String title;
    private final String version;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Driver toDriver$default(Companion companion, GpuDriverMetadata gpuDriverMetadata, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.toDriver(gpuDriverMetadata, z);
        }

        public final Driver toDriver(GpuDriverMetadata gpuDriverMetadata, boolean z) {
            Intrinsics.checkNotNullParameter(gpuDriverMetadata, "<this>");
            String name = gpuDriverMetadata.getName();
            if (name == null) {
                name = "";
            }
            String version = gpuDriverMetadata.getVersion();
            if (version == null) {
                version = "";
            }
            String description = gpuDriverMetadata.getDescription();
            return new Driver(z, name, version, description != null ? description : "");
        }
    }

    public Driver(boolean z, String title, String version, String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(description, "description");
        this.selected = z;
        this.title = title;
        this.version = version;
        this.description = description;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Driver)) {
            return false;
        }
        Driver driver = (Driver) obj;
        return this.selected == driver.selected && Intrinsics.areEqual(this.title, driver.title) && Intrinsics.areEqual(this.version, driver.version) && Intrinsics.areEqual(this.description, driver.description);
    }

    public final String getDescription() {
        return this.description;
    }

    @Override // org.yuzu.yuzu_emu.model.SelectableItem
    public boolean getSelected() {
        return this.selected;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.selected) * 31) + this.title.hashCode()) * 31) + this.version.hashCode()) * 31) + this.description.hashCode();
    }

    @Override // org.yuzu.yuzu_emu.model.SelectableItem
    public void onSelectionStateChanged(boolean z) {
        setSelected(z);
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String toString() {
        return "Driver(selected=" + this.selected + ", title=" + this.title + ", version=" + this.version + ", description=" + this.description + ")";
    }
}
