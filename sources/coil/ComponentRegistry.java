package coil;

import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.fetch.SourceResult;
import coil.key.Keyer;
import coil.map.Mapper;
import coil.request.Options;
import coil.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class ComponentRegistry {
    private final List decoderFactories;
    private final List fetcherFactories;
    private final List interceptors;
    private final List keyers;
    private final List mappers;

    /* loaded from: classes.dex */
    public static final class Builder {
        private final List decoderFactories;
        private final List fetcherFactories;
        private final List interceptors;
        private final List keyers;
        private final List mappers;

        public Builder() {
            this.interceptors = new ArrayList();
            this.mappers = new ArrayList();
            this.keyers = new ArrayList();
            this.fetcherFactories = new ArrayList();
            this.decoderFactories = new ArrayList();
        }

        public Builder(ComponentRegistry componentRegistry) {
            List mutableList;
            List mutableList2;
            List mutableList3;
            List mutableList4;
            List mutableList5;
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) componentRegistry.getInterceptors());
            this.interceptors = mutableList;
            mutableList2 = CollectionsKt___CollectionsKt.toMutableList((Collection) componentRegistry.getMappers());
            this.mappers = mutableList2;
            mutableList3 = CollectionsKt___CollectionsKt.toMutableList((Collection) componentRegistry.getKeyers());
            this.keyers = mutableList3;
            mutableList4 = CollectionsKt___CollectionsKt.toMutableList((Collection) componentRegistry.getFetcherFactories());
            this.fetcherFactories = mutableList4;
            mutableList5 = CollectionsKt___CollectionsKt.toMutableList((Collection) componentRegistry.getDecoderFactories());
            this.decoderFactories = mutableList5;
        }

        public final Builder add(Decoder.Factory factory) {
            this.decoderFactories.add(factory);
            return this;
        }

        public final Builder add(Fetcher.Factory factory, Class cls) {
            this.fetcherFactories.add(TuplesKt.to(factory, cls));
            return this;
        }

        public final Builder add(Keyer keyer, Class cls) {
            this.keyers.add(TuplesKt.to(keyer, cls));
            return this;
        }

        public final Builder add(Mapper mapper, Class cls) {
            this.mappers.add(TuplesKt.to(mapper, cls));
            return this;
        }

        public final ComponentRegistry build() {
            return new ComponentRegistry(Collections.toImmutableList(this.interceptors), Collections.toImmutableList(this.mappers), Collections.toImmutableList(this.keyers), Collections.toImmutableList(this.fetcherFactories), Collections.toImmutableList(this.decoderFactories), null);
        }

        public final List getDecoderFactories$coil_base_release() {
            return this.decoderFactories;
        }

        public final List getFetcherFactories$coil_base_release() {
            return this.fetcherFactories;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ComponentRegistry() {
        /*
            r6 = this;
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
            r0 = r6
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.ComponentRegistry.<init>():void");
    }

    private ComponentRegistry(List list, List list2, List list3, List list4, List list5) {
        this.interceptors = list;
        this.mappers = list2;
        this.keyers = list3;
        this.fetcherFactories = list4;
        this.decoderFactories = list5;
    }

    public /* synthetic */ ComponentRegistry(List list, List list2, List list3, List list4, List list5, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, list4, list5);
    }

    public final List getDecoderFactories() {
        return this.decoderFactories;
    }

    public final List getFetcherFactories() {
        return this.fetcherFactories;
    }

    public final List getInterceptors() {
        return this.interceptors;
    }

    public final List getKeyers() {
        return this.keyers;
    }

    public final List getMappers() {
        return this.mappers;
    }

    public final String key(Object obj, Options options) {
        List list = this.keyers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Pair pair = (Pair) list.get(i);
            Keyer keyer = (Keyer) pair.component1();
            if (((Class) pair.component2()).isAssignableFrom(obj.getClass())) {
                Intrinsics.checkNotNull(keyer, "null cannot be cast to non-null type coil.key.Keyer<kotlin.Any>");
                String key = keyer.key(obj, options);
                if (key != null) {
                    return key;
                }
            }
        }
        return null;
    }

    public final Object map(Object obj, Options options) {
        List list = this.mappers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Pair pair = (Pair) list.get(i);
            Mapper mapper = (Mapper) pair.component1();
            if (((Class) pair.component2()).isAssignableFrom(obj.getClass())) {
                Intrinsics.checkNotNull(mapper, "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>");
                Object map = mapper.map(obj, options);
                if (map != null) {
                    obj = map;
                }
            }
        }
        return obj;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final Pair newDecoder(SourceResult sourceResult, Options options, ImageLoader imageLoader, int i) {
        int size = this.decoderFactories.size();
        while (i < size) {
            Decoder create = ((Decoder.Factory) this.decoderFactories.get(i)).create(sourceResult, options, imageLoader);
            if (create != null) {
                return TuplesKt.to(create, Integer.valueOf(i));
            }
            i++;
        }
        return null;
    }

    public final Pair newFetcher(Object obj, Options options, ImageLoader imageLoader, int i) {
        int size = this.fetcherFactories.size();
        while (i < size) {
            Pair pair = (Pair) this.fetcherFactories.get(i);
            Fetcher.Factory factory = (Fetcher.Factory) pair.component1();
            if (((Class) pair.component2()).isAssignableFrom(obj.getClass())) {
                Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type coil.fetch.Fetcher.Factory<kotlin.Any>");
                Fetcher create = factory.create(obj, options, imageLoader);
                if (create != null) {
                    return TuplesKt.to(create, Integer.valueOf(i));
                }
            }
            i++;
        }
        return null;
    }
}
