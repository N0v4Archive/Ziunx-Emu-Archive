package coil.transition;

import coil.request.ErrorResult;
import coil.request.ImageResult;
import coil.request.SuccessResult;
import coil.transition.Transition;

/* loaded from: classes.dex */
public final class NoneTransition implements Transition {
    private final ImageResult result;
    private final TransitionTarget target;

    /* loaded from: classes.dex */
    public static final class Factory implements Transition.Factory {
        @Override // coil.transition.Transition.Factory
        public Transition create(TransitionTarget transitionTarget, ImageResult imageResult) {
            return new NoneTransition(transitionTarget, imageResult);
        }

        public boolean equals(Object obj) {
            return obj instanceof Factory;
        }

        public int hashCode() {
            return Factory.class.hashCode();
        }
    }

    public NoneTransition(TransitionTarget transitionTarget, ImageResult imageResult) {
        this.target = transitionTarget;
        this.result = imageResult;
    }

    @Override // coil.transition.Transition
    public void transition() {
        ImageResult imageResult = this.result;
        if (imageResult instanceof SuccessResult) {
            this.target.onSuccess(((SuccessResult) imageResult).getDrawable());
        } else if (imageResult instanceof ErrorResult) {
            this.target.onError(imageResult.getDrawable());
        }
    }
}
