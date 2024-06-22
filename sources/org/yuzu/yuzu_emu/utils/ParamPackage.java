package org.yuzu.yuzu_emu.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes.dex */
public final class ParamPackage {
    private final String EMPTY_PLACEHOLDER;
    private final String ESCAPE_CHARACTER;
    private final String ESCAPE_CHARACTER_ESCAPE;
    private final String KEY_VALUE_SEPARATOR;
    private final String KEY_VALUE_SEPARATOR_ESCAPE;
    private final String PARAM_SEPARATOR;
    private final String PARAM_SEPARATOR_ESCAPE;
    private final Map data;

    public ParamPackage(String serialized) {
        List split$default;
        List split$default2;
        List mutableList;
        String replace$default;
        String replace$default2;
        String replace$default3;
        Intrinsics.checkNotNullParameter(serialized, "serialized");
        this.KEY_VALUE_SEPARATOR = ":";
        this.PARAM_SEPARATOR = ",";
        this.ESCAPE_CHARACTER = "$";
        this.KEY_VALUE_SEPARATOR_ESCAPE = "$0";
        this.PARAM_SEPARATOR_ESCAPE = "$1";
        this.ESCAPE_CHARACTER_ESCAPE = "$2";
        this.EMPTY_PLACEHOLDER = "[empty]";
        this.data = new LinkedHashMap();
        split$default = StringsKt__StringsKt.split$default((CharSequence) serialized, new String[]{","}, false, 0, 6, (Object) null);
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            split$default2 = StringsKt__StringsKt.split$default((CharSequence) it.next(), new String[]{this.KEY_VALUE_SEPARATOR}, false, 0, 6, (Object) null);
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) split$default2);
            if (mutableList.size() != 2) {
                Log.INSTANCE.error("[ParamPackage] Invalid key pair " + mutableList);
            } else {
                int i = 0;
                for (Object obj : mutableList) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    replace$default = StringsKt__StringsJVMKt.replace$default((String) mutableList.get(i), this.KEY_VALUE_SEPARATOR_ESCAPE, this.KEY_VALUE_SEPARATOR, false, 4, (Object) null);
                    mutableList.set(i, replace$default);
                    replace$default2 = StringsKt__StringsJVMKt.replace$default((String) mutableList.get(i), this.PARAM_SEPARATOR_ESCAPE, this.PARAM_SEPARATOR, false, 4, (Object) null);
                    mutableList.set(i, replace$default2);
                    replace$default3 = StringsKt__StringsJVMKt.replace$default((String) mutableList.get(i), this.ESCAPE_CHARACTER_ESCAPE, this.ESCAPE_CHARACTER, false, 4, (Object) null);
                    mutableList.set(i, replace$default3);
                    i = i2;
                }
                set((String) mutableList.get(0), (String) mutableList.get(1));
            }
        }
    }

    public /* synthetic */ ParamPackage(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    private final boolean toBoolean(int i) {
        if (i == 0) {
            return false;
        }
        if (i == 1) {
            return true;
        }
        throw new Exception("Tried to convert a value to a boolean that was not 0 or 1!");
    }

    public final void clear() {
        this.data.clear();
    }

    public final String erase(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (String) this.data.remove(key);
    }

    public final float get(String key, float f) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (!has(key)) {
            Log.INSTANCE.debug("[ParamPackage] key " + key + " not found");
            return f;
        }
        try {
            Object obj = this.data.get(key);
            Intrinsics.checkNotNull(obj);
            return Float.parseFloat((String) obj);
        } catch (NumberFormatException unused) {
            Log log = Log.INSTANCE;
            Object obj2 = this.data.get(key);
            Intrinsics.checkNotNull(obj2);
            log.debug("[ParamPackage] failed to convert " + obj2 + " to float");
            return f;
        }
    }

    public final int get(String key, int i) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (!has(key)) {
            Log.INSTANCE.debug("[ParamPackage] key " + key + " not found");
            return i;
        }
        try {
            Object obj = this.data.get(key);
            Intrinsics.checkNotNull(obj);
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            Log log = Log.INSTANCE;
            Object obj2 = this.data.get(key);
            Intrinsics.checkNotNull(obj2);
            log.debug("[ParamPackage] failed to convert " + obj2 + " to int");
            return i;
        }
    }

    public final String get(String key, String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (has(key)) {
            Object obj = this.data.get(key);
            Intrinsics.checkNotNull(obj);
            return (String) obj;
        }
        Log.INSTANCE.debug("[ParamPackage] key " + key + " not found");
        return defaultValue;
    }

    public final boolean get(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (!has(key)) {
            Log.INSTANCE.debug("[ParamPackage] key " + key + " not found");
            return z;
        }
        try {
            return toBoolean(get(key, z ? 1 : 0));
        } catch (Exception unused) {
            Log log = Log.INSTANCE;
            Object obj = this.data.get(key);
            Intrinsics.checkNotNull(obj);
            log.debug("[ParamPackage] failed to convert " + obj + " to boolean");
            return z;
        }
    }

    public final boolean has(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.data.containsKey(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String serialize() {
        CharSequence removeSuffix;
        List mutableListOf;
        String replace$default;
        String replace$default2;
        String replace$default3;
        if (this.data.isEmpty()) {
            return this.EMPTY_PLACEHOLDER;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : this.data.entrySet()) {
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(entry.getKey(), entry.getValue());
            int i = 0;
            for (Object obj : mutableListOf) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                replace$default = StringsKt__StringsJVMKt.replace$default((String) mutableListOf.get(i), this.ESCAPE_CHARACTER, this.ESCAPE_CHARACTER_ESCAPE, false, 4, (Object) null);
                mutableListOf.set(i, replace$default);
                replace$default2 = StringsKt__StringsJVMKt.replace$default((String) mutableListOf.get(i), this.PARAM_SEPARATOR, this.PARAM_SEPARATOR_ESCAPE, false, 4, (Object) null);
                mutableListOf.set(i, replace$default2);
                replace$default3 = StringsKt__StringsJVMKt.replace$default((String) mutableListOf.get(i), this.KEY_VALUE_SEPARATOR, this.KEY_VALUE_SEPARATOR_ESCAPE, false, 4, (Object) null);
                mutableListOf.set(i, replace$default3);
                i = i2;
            }
            sb.append(mutableListOf.get(0) + this.KEY_VALUE_SEPARATOR + mutableListOf.get(1) + this.PARAM_SEPARATOR);
        }
        removeSuffix = StringsKt__StringsKt.removeSuffix(sb, this.PARAM_SEPARATOR);
        return removeSuffix.toString();
    }

    public final void set(String key, float f) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.data.put(key, String.valueOf(f));
    }

    public final void set(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.data.put(key, value);
    }

    public final void set(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.data.put(key, String.valueOf(toInt(z)));
    }

    public final int toInt(boolean z) {
        return z ? 1 : 0;
    }
}
