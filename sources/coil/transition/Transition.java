package coil.transition;

import coil.request.ImageResult;
import coil.transition.NoneTransition;

/* loaded from: classes.dex */
public interface Transition {

    /* loaded from: classes.dex */
    public interface Factory {
        public static final Companion Companion = Companion.$$INSTANCE;
        public static final Factory NONE = new NoneTransition.Factory();

        /* loaded from: classes.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }

        Transition create(TransitionTarget transitionTarget, ImageResult imageResult);
    }

    void transition();
}
