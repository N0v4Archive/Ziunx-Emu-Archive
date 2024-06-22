package coil.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: coil.util.-Contexts, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class Contexts {
    public static final Drawable getDrawableCompat(Context context, int i) {
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    public static final Drawable getDrawableCompat(Resources resources, int i, Resources.Theme theme) {
        Drawable drawable = ResourcesCompat.getDrawable(resources, i, theme);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r1 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.lifecycle.Lifecycle getLifecycle(android.content.Context r1) {
        /*
        L0:
            boolean r0 = r1 instanceof androidx.lifecycle.LifecycleOwner
            if (r0 == 0) goto Lb
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            androidx.lifecycle.Lifecycle r1 = r1.getLifecycle()
            return r1
        Lb:
            boolean r0 = r1 instanceof android.content.ContextWrapper
            if (r0 != 0) goto L11
            r1 = 0
            return r1
        L11:
            android.content.ContextWrapper r1 = (android.content.ContextWrapper) r1
            android.content.Context r1 = r1.getBaseContext()
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.util.Contexts.getLifecycle(android.content.Context):androidx.lifecycle.Lifecycle");
    }

    public static final Drawable getXmlDrawableCompat(Context context, Resources resources, int i) {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return getDrawableCompat(resources, i, context.getTheme());
        }
        throw new XmlPullParserException("No start tag found.");
    }

    public static final boolean isPermissionGranted(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }
}
