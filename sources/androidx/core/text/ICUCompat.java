package androidx.core.text;

import android.icu.util.ULocale;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class ICUCompat {

    /* loaded from: classes.dex */
    static class Api24Impl {
        static ULocale addLikelySubtags(Object obj) {
            return ULocale.addLikelySubtags((ULocale) obj);
        }

        static ULocale forLocale(Locale locale) {
            return ULocale.forLocale(locale);
        }

        static String getScript(Object obj) {
            return ((ULocale) obj).getScript();
        }
    }

    public static String maximizeAndGetScript(Locale locale) {
        return Api24Impl.getScript(Api24Impl.addLikelySubtags(Api24Impl.forLocale(locale)));
    }
}
