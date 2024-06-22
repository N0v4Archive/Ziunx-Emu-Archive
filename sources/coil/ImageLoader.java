package coil;

import android.content.Context;
import coil.EventListener;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.util.ImageLoaderOptions;
import coil.util.Requests;
import coil.util.SingletonDiskCache;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public interface ImageLoader {

    /* loaded from: classes.dex */
    public static final class Builder {
        private final Context applicationContext;
        private DefaultRequestOptions defaults = Requests.getDEFAULT_REQUEST_OPTIONS();
        private Lazy memoryCache = null;
        private Lazy diskCache = null;
        private Lazy callFactory = null;
        private EventListener.Factory eventListenerFactory = null;
        private ComponentRegistry componentRegistry = null;
        private ImageLoaderOptions options = new ImageLoaderOptions(false, false, false, 0, null, 31, null);

        public Builder(Context context) {
            this.applicationContext = context.getApplicationContext();
        }

        public final ImageLoader build() {
            Context context = this.applicationContext;
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            Lazy lazy = this.memoryCache;
            if (lazy == null) {
                lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: coil.ImageLoader$Builder$build$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final MemoryCache invoke() {
                        Context context2;
                        context2 = ImageLoader.Builder.this.applicationContext;
                        return new MemoryCache.Builder(context2).build();
                    }
                });
            }
            Lazy lazy2 = lazy;
            Lazy lazy3 = this.diskCache;
            if (lazy3 == null) {
                lazy3 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: coil.ImageLoader$Builder$build$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final DiskCache invoke() {
                        Context context2;
                        SingletonDiskCache singletonDiskCache = SingletonDiskCache.INSTANCE;
                        context2 = ImageLoader.Builder.this.applicationContext;
                        return singletonDiskCache.get(context2);
                    }
                });
            }
            Lazy lazy4 = lazy3;
            Lazy lazy5 = this.callFactory;
            if (lazy5 == null) {
                lazy5 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: coil.ImageLoader$Builder$build$3
                    @Override // kotlin.jvm.functions.Function0
                    public final OkHttpClient invoke() {
                        return new OkHttpClient();
                    }
                });
            }
            Lazy lazy6 = lazy5;
            EventListener.Factory factory = this.eventListenerFactory;
            if (factory == null) {
                factory = EventListener.Factory.NONE;
            }
            EventListener.Factory factory2 = factory;
            ComponentRegistry componentRegistry = this.componentRegistry;
            if (componentRegistry == null) {
                componentRegistry = new ComponentRegistry();
            }
            return new RealImageLoader(context, defaultRequestOptions, lazy2, lazy4, lazy6, factory2, componentRegistry, this.options, null);
        }

        public final Builder components(ComponentRegistry componentRegistry) {
            this.componentRegistry = componentRegistry;
            return this;
        }

        public final Builder memoryCache(Function0 function0) {
            Lazy lazy;
            lazy = LazyKt__LazyJVMKt.lazy(function0);
            this.memoryCache = lazy;
            return this;
        }
    }

    Disposable enqueue(ImageRequest imageRequest);

    Object execute(ImageRequest imageRequest, Continuation continuation);

    ComponentRegistry getComponents();

    MemoryCache getMemoryCache();
}
