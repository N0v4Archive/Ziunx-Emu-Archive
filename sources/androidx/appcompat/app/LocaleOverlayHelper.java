package androidx.appcompat.app;

import androidx.core.os.LocaleListCompat;
import java.util.LinkedHashSet;
import java.util.Locale;

/* loaded from: classes.dex */
abstract class LocaleOverlayHelper {
    private static LocaleListCompat combineLocales(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        while (i < localeListCompat.size() + localeListCompat2.size()) {
            Locale locale = i < localeListCompat.size() ? localeListCompat.get(i) : localeListCompat2.get(i - localeListCompat.size());
            if (locale != null) {
                linkedHashSet.add(locale);
            }
            i++;
        }
        return LocaleListCompat.create((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocaleListCompat combineLocalesIfOverlayExists(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        return (localeListCompat == null || localeListCompat.isEmpty()) ? LocaleListCompat.getEmptyLocaleList() : combineLocales(localeListCompat, localeListCompat2);
    }
}
