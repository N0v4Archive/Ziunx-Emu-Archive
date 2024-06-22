package coil.size;

/* loaded from: classes.dex */
public abstract class SizeResolvers {
    public static final SizeResolver create(Size size) {
        return new RealSizeResolver(size);
    }
}
