package kotlin.random;

/* loaded from: classes.dex */
public abstract class AbstractPlatformRandom extends Random {
    public abstract java.util.Random getImpl();

    @Override // kotlin.random.Random
    public int nextInt() {
        return getImpl().nextInt();
    }
}
