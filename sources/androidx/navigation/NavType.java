package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes.dex */
public abstract class NavType {
    private final boolean isNullableAllowed;
    private final String name = "nav_type";
    public static final Companion Companion = new Companion(null);
    public static final NavType IntType = new NavType() { // from class: androidx.navigation.NavType$Companion$IntType$1
        @Override // androidx.navigation.NavType
        public Integer get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = bundle.get(key);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return (Integer) obj;
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "integer";
        }

        @Override // androidx.navigation.NavType
        public Integer parseValue(String value) {
            boolean startsWith$default;
            int parseInt;
            int checkRadix;
            Intrinsics.checkNotNullParameter(value, "value");
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(value, "0x", false, 2, null);
            if (startsWith$default) {
                String substring = value.substring(2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                checkRadix = CharsKt__CharJVMKt.checkRadix(16);
                parseInt = Integer.parseInt(substring, checkRadix);
            } else {
                parseInt = Integer.parseInt(value);
            }
            return Integer.valueOf(parseInt);
        }

        public void put(Bundle bundle, String key, int i) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putInt(key, i);
        }

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, ((Number) obj).intValue());
        }
    };
    public static final NavType ReferenceType = new NavType() { // from class: androidx.navigation.NavType$Companion$ReferenceType$1
        @Override // androidx.navigation.NavType
        public Integer get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = bundle.get(key);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return (Integer) obj;
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "reference";
        }

        @Override // androidx.navigation.NavType
        public Integer parseValue(String value) {
            boolean startsWith$default;
            int parseInt;
            int checkRadix;
            Intrinsics.checkNotNullParameter(value, "value");
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(value, "0x", false, 2, null);
            if (startsWith$default) {
                String substring = value.substring(2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                checkRadix = CharsKt__CharJVMKt.checkRadix(16);
                parseInt = Integer.parseInt(substring, checkRadix);
            } else {
                parseInt = Integer.parseInt(value);
            }
            return Integer.valueOf(parseInt);
        }

        public void put(Bundle bundle, String key, int i) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putInt(key, i);
        }

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, ((Number) obj).intValue());
        }
    };
    public static final NavType IntArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$IntArrayType$1
        @Override // androidx.navigation.NavType
        public int[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (int[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "integer[]";
        }

        @Override // androidx.navigation.NavType
        public int[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new int[]{((Number) NavType.IntType.parseValue(value)).intValue()};
        }

        /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
        
            r3 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r3, parseValue(r2));
         */
        @Override // androidx.navigation.NavType
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int[] parseValue(java.lang.String r2, int[] r3) {
            /*
                r1 = this;
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                if (r3 == 0) goto L11
                int[] r0 = r1.parseValue(r2)
                int[] r3 = kotlin.collections.ArraysKt.plus(r3, r0)
                if (r3 != 0) goto L15
            L11:
                int[] r3 = r1.parseValue(r2)
            L15:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType$Companion$IntArrayType$1.parseValue(java.lang.String, int[]):int[]");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, int[] iArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putIntArray(key, iArr);
        }
    };
    public static final NavType LongType = new NavType() { // from class: androidx.navigation.NavType$Companion$LongType$1
        @Override // androidx.navigation.NavType
        public Long get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = bundle.get(key);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
            return (Long) obj;
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "long";
        }

        @Override // androidx.navigation.NavType
        public Long parseValue(String value) {
            boolean endsWith$default;
            String str;
            boolean startsWith$default;
            long parseLong;
            int checkRadix;
            Intrinsics.checkNotNullParameter(value, "value");
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default(value, "L", false, 2, null);
            if (endsWith$default) {
                str = value.substring(0, value.length() - 1);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            } else {
                str = value;
            }
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(value, "0x", false, 2, null);
            if (startsWith$default) {
                String substring = str.substring(2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                checkRadix = CharsKt__CharJVMKt.checkRadix(16);
                parseLong = Long.parseLong(substring, checkRadix);
            } else {
                parseLong = Long.parseLong(str);
            }
            return Long.valueOf(parseLong);
        }

        public void put(Bundle bundle, String key, long j) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putLong(key, j);
        }

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, ((Number) obj).longValue());
        }
    };
    public static final NavType LongArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$LongArrayType$1
        @Override // androidx.navigation.NavType
        public long[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (long[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "long[]";
        }

        @Override // androidx.navigation.NavType
        public long[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new long[]{((Number) NavType.LongType.parseValue(value)).longValue()};
        }

        /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
        
            r3 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r3, parseValue(r2));
         */
        @Override // androidx.navigation.NavType
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long[] parseValue(java.lang.String r2, long[] r3) {
            /*
                r1 = this;
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                if (r3 == 0) goto L11
                long[] r0 = r1.parseValue(r2)
                long[] r3 = kotlin.collections.ArraysKt.plus(r3, r0)
                if (r3 != 0) goto L15
            L11:
                long[] r3 = r1.parseValue(r2)
            L15:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType$Companion$LongArrayType$1.parseValue(java.lang.String, long[]):long[]");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, long[] jArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putLongArray(key, jArr);
        }
    };
    public static final NavType FloatType = new NavType() { // from class: androidx.navigation.NavType$Companion$FloatType$1
        @Override // androidx.navigation.NavType
        public Float get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = bundle.get(key);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
            return (Float) obj;
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "float";
        }

        @Override // androidx.navigation.NavType
        public Float parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return Float.valueOf(Float.parseFloat(value));
        }

        public void put(Bundle bundle, String key, float f) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putFloat(key, f);
        }

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, ((Number) obj).floatValue());
        }
    };
    public static final NavType FloatArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$FloatArrayType$1
        @Override // androidx.navigation.NavType
        public float[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (float[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "float[]";
        }

        @Override // androidx.navigation.NavType
        public float[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new float[]{((Number) NavType.FloatType.parseValue(value)).floatValue()};
        }

        /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
        
            r3 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r3, parseValue(r2));
         */
        @Override // androidx.navigation.NavType
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public float[] parseValue(java.lang.String r2, float[] r3) {
            /*
                r1 = this;
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                if (r3 == 0) goto L11
                float[] r0 = r1.parseValue(r2)
                float[] r3 = kotlin.collections.ArraysKt.plus(r3, r0)
                if (r3 != 0) goto L15
            L11:
                float[] r3 = r1.parseValue(r2)
            L15:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType$Companion$FloatArrayType$1.parseValue(java.lang.String, float[]):float[]");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, float[] fArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putFloatArray(key, fArr);
        }
    };
    public static final NavType BoolType = new NavType() { // from class: androidx.navigation.NavType$Companion$BoolType$1
        @Override // androidx.navigation.NavType
        public Boolean get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (Boolean) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "boolean";
        }

        @Override // androidx.navigation.NavType
        public Boolean parseValue(String value) {
            boolean z;
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, "true")) {
                z = true;
            } else {
                if (!Intrinsics.areEqual(value, "false")) {
                    throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
                }
                z = false;
            }
            return Boolean.valueOf(z);
        }

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, ((Boolean) obj).booleanValue());
        }

        public void put(Bundle bundle, String key, boolean z) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putBoolean(key, z);
        }
    };
    public static final NavType BoolArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$BoolArrayType$1
        @Override // androidx.navigation.NavType
        public boolean[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (boolean[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "boolean[]";
        }

        @Override // androidx.navigation.NavType
        public boolean[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new boolean[]{((Boolean) NavType.BoolType.parseValue(value)).booleanValue()};
        }

        /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
        
            r3 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r3, parseValue(r2));
         */
        @Override // androidx.navigation.NavType
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean[] parseValue(java.lang.String r2, boolean[] r3) {
            /*
                r1 = this;
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                if (r3 == 0) goto L11
                boolean[] r0 = r1.parseValue(r2)
                boolean[] r3 = kotlin.collections.ArraysKt.plus(r3, r0)
                if (r3 != 0) goto L15
            L11:
                boolean[] r3 = r1.parseValue(r2)
            L15:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType$Companion$BoolArrayType$1.parseValue(java.lang.String, boolean[]):boolean[]");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, boolean[] zArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putBooleanArray(key, zArr);
        }
    };
    public static final NavType StringType = new NavType() { // from class: androidx.navigation.NavType$Companion$StringType$1
        @Override // androidx.navigation.NavType
        public String get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (String) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string";
        }

        @Override // androidx.navigation.NavType
        public String parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, "null")) {
                return null;
            }
            return value;
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, String str) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putString(key, str);
        }
    };
    public static final NavType StringArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$StringArrayType$1
        @Override // androidx.navigation.NavType
        public String[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (String[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string[]";
        }

        @Override // androidx.navigation.NavType
        public String[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new String[]{value};
        }

        @Override // androidx.navigation.NavType
        public String[] parseValue(String value, String[] strArr) {
            Object[] plus;
            Intrinsics.checkNotNullParameter(value, "value");
            if (strArr != null) {
                plus = ArraysKt___ArraysJvmKt.plus(strArr, parseValue(value));
                String[] strArr2 = (String[]) plus;
                if (strArr2 != null) {
                    return strArr2;
                }
            }
            return parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, String[] strArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            bundle.putStringArray(key, strArr);
        }
    };

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public NavType fromArgType(String str, String str2) {
            boolean startsWith$default;
            String str3;
            boolean endsWith$default;
            NavType navType = NavType.IntType;
            if (Intrinsics.areEqual(navType.getName(), str)) {
                return navType;
            }
            NavType navType2 = NavType.IntArrayType;
            if (Intrinsics.areEqual(navType2.getName(), str)) {
                return navType2;
            }
            NavType navType3 = NavType.LongType;
            if (Intrinsics.areEqual(navType3.getName(), str)) {
                return navType3;
            }
            NavType navType4 = NavType.LongArrayType;
            if (Intrinsics.areEqual(navType4.getName(), str)) {
                return navType4;
            }
            NavType navType5 = NavType.BoolType;
            if (Intrinsics.areEqual(navType5.getName(), str)) {
                return navType5;
            }
            NavType navType6 = NavType.BoolArrayType;
            if (Intrinsics.areEqual(navType6.getName(), str)) {
                return navType6;
            }
            NavType navType7 = NavType.StringType;
            if (Intrinsics.areEqual(navType7.getName(), str)) {
                return navType7;
            }
            NavType navType8 = NavType.StringArrayType;
            if (Intrinsics.areEqual(navType8.getName(), str)) {
                return navType8;
            }
            NavType navType9 = NavType.FloatType;
            if (Intrinsics.areEqual(navType9.getName(), str)) {
                return navType9;
            }
            NavType navType10 = NavType.FloatArrayType;
            if (Intrinsics.areEqual(navType10.getName(), str)) {
                return navType10;
            }
            NavType navType11 = NavType.ReferenceType;
            if (Intrinsics.areEqual(navType11.getName(), str)) {
                return navType11;
            }
            if (str == null || str.length() == 0) {
                return navType7;
            }
            try {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, ".", false, 2, null);
                if (!startsWith$default || str2 == null) {
                    str3 = str;
                } else {
                    str3 = str2 + str;
                }
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str, "[]", false, 2, null);
                if (endsWith$default) {
                    str3 = str3.substring(0, str3.length() - 2);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    Class<?> cls = Class.forName(str3);
                    if (Parcelable.class.isAssignableFrom(cls)) {
                        Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<android.os.Parcelable>");
                        return new ParcelableArrayType(cls);
                    }
                    if (Serializable.class.isAssignableFrom(cls)) {
                        Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<java.io.Serializable>");
                        return new SerializableArrayType(cls);
                    }
                } else {
                    Class<?> cls2 = Class.forName(str3);
                    if (Parcelable.class.isAssignableFrom(cls2)) {
                        Intrinsics.checkNotNull(cls2, "null cannot be cast to non-null type java.lang.Class<kotlin.Any?>");
                        return new ParcelableType(cls2);
                    }
                    if (Enum.class.isAssignableFrom(cls2)) {
                        Intrinsics.checkNotNull(cls2, "null cannot be cast to non-null type java.lang.Class<kotlin.Enum<*>>");
                        return new EnumType(cls2);
                    }
                    if (Serializable.class.isAssignableFrom(cls2)) {
                        Intrinsics.checkNotNull(cls2, "null cannot be cast to non-null type java.lang.Class<java.io.Serializable>");
                        return new SerializableType(cls2);
                    }
                }
                throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public final NavType inferFromValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                try {
                    try {
                        try {
                            NavType navType = NavType.IntType;
                            navType.parseValue(value);
                            Intrinsics.checkNotNull(navType, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                            return navType;
                        } catch (IllegalArgumentException unused) {
                            NavType navType2 = NavType.FloatType;
                            navType2.parseValue(value);
                            Intrinsics.checkNotNull(navType2, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                            return navType2;
                        }
                    } catch (IllegalArgumentException unused2) {
                        NavType navType3 = NavType.StringType;
                        Intrinsics.checkNotNull(navType3, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                        return navType3;
                    }
                } catch (IllegalArgumentException unused3) {
                    NavType navType4 = NavType.LongType;
                    navType4.parseValue(value);
                    Intrinsics.checkNotNull(navType4, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                    return navType4;
                }
            } catch (IllegalArgumentException unused4) {
                NavType navType5 = NavType.BoolType;
                navType5.parseValue(value);
                Intrinsics.checkNotNull(navType5, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                return navType5;
            }
        }

        public final NavType inferFromValueType(Object obj) {
            NavType navType;
            if (obj instanceof Integer) {
                navType = NavType.IntType;
            } else if (obj instanceof int[]) {
                navType = NavType.IntArrayType;
            } else if (obj instanceof Long) {
                navType = NavType.LongType;
            } else if (obj instanceof long[]) {
                navType = NavType.LongArrayType;
            } else if (obj instanceof Float) {
                navType = NavType.FloatType;
            } else if (obj instanceof float[]) {
                navType = NavType.FloatArrayType;
            } else if (obj instanceof Boolean) {
                navType = NavType.BoolType;
            } else if (obj instanceof boolean[]) {
                navType = NavType.BoolArrayType;
            } else if ((obj instanceof String) || obj == null) {
                navType = NavType.StringType;
            } else {
                if (!(obj instanceof Object[]) || !(((Object[]) obj) instanceof String[])) {
                    if (obj.getClass().isArray()) {
                        Class<?> componentType = obj.getClass().getComponentType();
                        Intrinsics.checkNotNull(componentType);
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            Class<?> componentType2 = obj.getClass().getComponentType();
                            Intrinsics.checkNotNull(componentType2, "null cannot be cast to non-null type java.lang.Class<android.os.Parcelable>");
                            return new ParcelableArrayType(componentType2);
                        }
                    }
                    if (obj.getClass().isArray()) {
                        Class<?> componentType3 = obj.getClass().getComponentType();
                        Intrinsics.checkNotNull(componentType3);
                        if (Serializable.class.isAssignableFrom(componentType3)) {
                            Class<?> componentType4 = obj.getClass().getComponentType();
                            Intrinsics.checkNotNull(componentType4, "null cannot be cast to non-null type java.lang.Class<java.io.Serializable>");
                            return new SerializableArrayType(componentType4);
                        }
                    }
                    if (obj instanceof Parcelable) {
                        return new ParcelableType(obj.getClass());
                    }
                    if (obj instanceof Enum) {
                        return new EnumType(obj.getClass());
                    }
                    if (obj instanceof Serializable) {
                        return new SerializableType(obj.getClass());
                    }
                    throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
                }
                navType = NavType.StringArrayType;
            }
            Intrinsics.checkNotNull(navType, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType;
        }
    }

    /* loaded from: classes.dex */
    public static final class EnumType extends SerializableType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnumType(Class type) {
            super(false, type);
            Intrinsics.checkNotNullParameter(type, "type");
            if (type.isEnum()) {
                this.type = type;
                return;
            }
            throw new IllegalArgumentException((type + " is not an Enum type.").toString());
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        public String getName() {
            String name = this.type.getName();
            Intrinsics.checkNotNullExpressionValue(name, "type.name");
            return name;
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        public Enum parseValue(String value) {
            Object obj;
            boolean equals;
            Intrinsics.checkNotNullParameter(value, "value");
            Object[] enumConstants = this.type.getEnumConstants();
            Intrinsics.checkNotNullExpressionValue(enumConstants, "type.enumConstants");
            int length = enumConstants.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    obj = null;
                    break;
                }
                obj = enumConstants[i];
                equals = StringsKt__StringsJVMKt.equals(((Enum) obj).name(), value, true);
                if (equals) {
                    break;
                }
                i++;
            }
            Enum r3 = (Enum) obj;
            if (r3 != null) {
                return r3;
            }
            throw new IllegalArgumentException("Enum value " + value + " not found for type " + this.type.getName() + '.');
        }
    }

    /* loaded from: classes.dex */
    public static final class ParcelableArrayType extends NavType {
        private final Class arrayType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParcelableArrayType(Class type) {
            super(true);
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Parcelable.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException((type + " does not implement Parcelable.").toString());
            }
            try {
                Class<?> cls = Class.forName("[L" + type.getName() + ';');
                Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<kotlin.Array<D of androidx.navigation.NavType.ParcelableArrayType>>");
                this.arrayType = cls;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !Intrinsics.areEqual(ParcelableArrayType.class, obj.getClass())) {
                return false;
            }
            return Intrinsics.areEqual(this.arrayType, ((ParcelableArrayType) obj).arrayType);
        }

        @Override // androidx.navigation.NavType
        public Parcelable[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (Parcelable[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            String name = this.arrayType.getName();
            Intrinsics.checkNotNullExpressionValue(name, "arrayType.name");
            return name;
        }

        public int hashCode() {
            return this.arrayType.hashCode();
        }

        @Override // androidx.navigation.NavType
        public Parcelable[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Parcelable[] parcelableArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            this.arrayType.cast(parcelableArr);
            bundle.putParcelableArray(key, parcelableArr);
        }
    }

    /* loaded from: classes.dex */
    public static final class ParcelableType extends NavType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParcelableType(Class type) {
            super(true);
            Intrinsics.checkNotNullParameter(type, "type");
            boolean z = true;
            if (!Parcelable.class.isAssignableFrom(type) && !Serializable.class.isAssignableFrom(type)) {
                z = false;
            }
            if (z) {
                this.type = type;
                return;
            }
            throw new IllegalArgumentException((type + " does not implement Parcelable or Serializable.").toString());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !Intrinsics.areEqual(ParcelableType.class, obj.getClass())) {
                return false;
            }
            return Intrinsics.areEqual(this.type, ((ParcelableType) obj).type);
        }

        @Override // androidx.navigation.NavType
        public Object get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            String name = this.type.getName();
            Intrinsics.checkNotNullExpressionValue(name, "type.name");
            return name;
        }

        public int hashCode() {
            return this.type.hashCode();
        }

        @Override // androidx.navigation.NavType
        public Object parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Object obj) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            this.type.cast(obj);
            if (obj == null || (obj instanceof Parcelable)) {
                bundle.putParcelable(key, (Parcelable) obj);
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) obj);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class SerializableArrayType extends NavType {
        private final Class arrayType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SerializableArrayType(Class type) {
            super(true);
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Serializable.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException((type + " does not implement Serializable.").toString());
            }
            try {
                Class<?> cls = Class.forName("[L" + type.getName() + ';');
                Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<kotlin.Array<D of androidx.navigation.NavType.SerializableArrayType>>");
                this.arrayType = cls;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !Intrinsics.areEqual(SerializableArrayType.class, obj.getClass())) {
                return false;
            }
            return Intrinsics.areEqual(this.arrayType, ((SerializableArrayType) obj).arrayType);
        }

        @Override // androidx.navigation.NavType
        public Serializable[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (Serializable[]) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            String name = this.arrayType.getName();
            Intrinsics.checkNotNullExpressionValue(name, "arrayType.name");
            return name;
        }

        public int hashCode() {
            return this.arrayType.hashCode();
        }

        @Override // androidx.navigation.NavType
        public Serializable[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Serializable[] serializableArr) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            this.arrayType.cast(serializableArr);
            bundle.putSerializable(key, serializableArr);
        }
    }

    /* loaded from: classes.dex */
    public static class SerializableType extends NavType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SerializableType(Class type) {
            super(true);
            Intrinsics.checkNotNullParameter(type, "type");
            if (!Serializable.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException((type + " does not implement Serializable.").toString());
            }
            if (true ^ type.isEnum()) {
                this.type = type;
                return;
            }
            throw new IllegalArgumentException((type + " is an Enum. You should use EnumType instead.").toString());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SerializableType(boolean z, Class type) {
            super(z);
            Intrinsics.checkNotNullParameter(type, "type");
            if (Serializable.class.isAssignableFrom(type)) {
                this.type = type;
                return;
            }
            throw new IllegalArgumentException((type + " does not implement Serializable.").toString());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SerializableType) {
                return Intrinsics.areEqual(this.type, ((SerializableType) obj).type);
            }
            return false;
        }

        @Override // androidx.navigation.NavType
        public Serializable get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return (Serializable) bundle.get(key);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            String name = this.type.getName();
            Intrinsics.checkNotNullExpressionValue(name, "type.name");
            return name;
        }

        public int hashCode() {
            return this.type.hashCode();
        }

        @Override // androidx.navigation.NavType
        public Serializable parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Serializable value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.type.cast(value);
            bundle.putSerializable(key, value);
        }
    }

    public NavType(boolean z) {
        this.isNullableAllowed = z;
    }

    public abstract Object get(Bundle bundle, String str);

    public abstract String getName();

    public boolean isNullableAllowed() {
        return this.isNullableAllowed;
    }

    public final Object parseAndPut(Bundle bundle, String key, String value) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Object parseValue = parseValue(value);
        put(bundle, key, parseValue);
        return parseValue;
    }

    public final Object parseAndPut(Bundle bundle, String key, String str, Object obj) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!bundle.containsKey(key)) {
            throw new IllegalArgumentException("There is no previous value in this bundle.");
        }
        if (str == null) {
            return obj;
        }
        Object parseValue = parseValue(str, obj);
        put(bundle, key, parseValue);
        return parseValue;
    }

    public abstract Object parseValue(String str);

    public Object parseValue(String value, Object obj) {
        Intrinsics.checkNotNullParameter(value, "value");
        return parseValue(value);
    }

    public abstract void put(Bundle bundle, String str, Object obj);

    public String toString() {
        return getName();
    }
}
