package org.yuzu.yuzu_emu.overlay.model;

import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class OverlayControlData {
    private boolean enabled;
    private Pair foldablePosition;
    private final String id;
    private Pair landscapePosition;
    private Pair portraitPosition;

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverlayLayout.values().length];
            try {
                iArr[OverlayLayout.Landscape.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OverlayLayout.Portrait.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OverlayLayout.Foldable.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public OverlayControlData(String id, boolean z, Pair landscapePosition, Pair portraitPosition, Pair foldablePosition) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(landscapePosition, "landscapePosition");
        Intrinsics.checkNotNullParameter(portraitPosition, "portraitPosition");
        Intrinsics.checkNotNullParameter(foldablePosition, "foldablePosition");
        this.id = id;
        this.enabled = z;
        this.landscapePosition = landscapePosition;
        this.portraitPosition = portraitPosition;
        this.foldablePosition = foldablePosition;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OverlayControlData)) {
            return false;
        }
        OverlayControlData overlayControlData = (OverlayControlData) obj;
        return Intrinsics.areEqual(this.id, overlayControlData.id) && this.enabled == overlayControlData.enabled && Intrinsics.areEqual(this.landscapePosition, overlayControlData.landscapePosition) && Intrinsics.areEqual(this.portraitPosition, overlayControlData.portraitPosition) && Intrinsics.areEqual(this.foldablePosition, overlayControlData.foldablePosition);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + Boolean.hashCode(this.enabled)) * 31) + this.landscapePosition.hashCode()) * 31) + this.portraitPosition.hashCode()) * 31) + this.foldablePosition.hashCode();
    }

    public final Pair positionFromLayout(OverlayLayout layout) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        int i = WhenMappings.$EnumSwitchMapping$0[layout.ordinal()];
        if (i == 1) {
            return this.landscapePosition;
        }
        if (i == 2) {
            return this.portraitPosition;
        }
        if (i == 3) {
            return this.foldablePosition;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setFoldablePosition(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<set-?>");
        this.foldablePosition = pair;
    }

    public final void setLandscapePosition(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<set-?>");
        this.landscapePosition = pair;
    }

    public final void setPortraitPosition(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<set-?>");
        this.portraitPosition = pair;
    }

    public String toString() {
        return "OverlayControlData(id=" + this.id + ", enabled=" + this.enabled + ", landscapePosition=" + this.landscapePosition + ", portraitPosition=" + this.portraitPosition + ", foldablePosition=" + this.foldablePosition + ")";
    }
}
