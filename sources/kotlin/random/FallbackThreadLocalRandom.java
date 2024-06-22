package kotlin.random;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
    private final FallbackThreadLocalRandom$implStorage$1 implStorage = new ThreadLocal() { // from class: kotlin.random.FallbackThreadLocalRandom$implStorage$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public java.util.Random initialValue() {
            return new java.util.Random();
        }
    };

    @Override // kotlin.random.AbstractPlatformRandom
    public java.util.Random getImpl() {
        Object obj = get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (java.util.Random) obj;
    }
}
