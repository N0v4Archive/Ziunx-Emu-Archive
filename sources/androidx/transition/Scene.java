package androidx.transition;

import android.view.ViewGroup;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes.dex */
public abstract class Scene {
    public static Scene getCurrentScene(ViewGroup viewGroup) {
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(viewGroup.getTag(R$id.transition_current_scene));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setCurrentScene(ViewGroup viewGroup, Scene scene) {
        viewGroup.setTag(R$id.transition_current_scene, scene);
    }
}
