package androidx.core.content.pm;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ShortcutInfoCompatSaver {

    /* loaded from: classes.dex */
    public static class NoopImpl extends ShortcutInfoCompatSaver {
        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Void addShortcuts(List list) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Void removeShortcuts(List list) {
            return null;
        }
    }

    public abstract Object addShortcuts(List list);

    public List getShortcuts() {
        return new ArrayList();
    }

    public abstract Object removeShortcuts(List list);
}
