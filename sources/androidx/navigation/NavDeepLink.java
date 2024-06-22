package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes.dex */
public final class NavDeepLink {
    private final String action;
    private final Lazy fragArgs$delegate;
    private final Lazy fragArgsAndRegex$delegate;
    private final Lazy fragPattern$delegate;
    private final Lazy fragRegex$delegate;
    private boolean isExactDeepLink;
    private final Lazy isParameterizedQuery$delegate;
    private boolean isSingleQueryParamValueOnly;
    private final String mimeType;
    private final Lazy mimeTypePattern$delegate;
    private String mimeTypeRegex;
    private final List pathArgs = new ArrayList();
    private final Lazy pathPattern$delegate;
    private String pathRegex;
    private final Lazy queryArgsMap$delegate;
    private final String uriPattern;
    private static final Companion Companion = new Companion(null);
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    private static final Pattern FILL_IN_PATTERN = Pattern.compile("\\{(.+?)\\}");

    /* loaded from: classes.dex */
    public static final class Builder {
        public static final Companion Companion = new Companion(null);
        private String action;
        private String mimeType;
        private String uriPattern;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public final NavDeepLink build() {
            return new NavDeepLink(this.uriPattern, this.action, this.mimeType);
        }

        public final Builder setAction(String action) {
            Intrinsics.checkNotNullParameter(action, "action");
            if (!(action.length() > 0)) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.".toString());
            }
            this.action = action;
            return this;
        }

        public final Builder setMimeType(String mimeType) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            this.mimeType = mimeType;
            return this;
        }

        public final Builder setUriPattern(String uriPattern) {
            Intrinsics.checkNotNullParameter(uriPattern, "uriPattern");
            this.uriPattern = uriPattern;
            return this;
        }
    }

    /* loaded from: classes.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MimeType implements Comparable {
        private String subType;
        private String type;

        public MimeType(String mimeType) {
            List emptyList;
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            List split = new Regex("/").split(mimeType, 0);
            if (!split.isEmpty()) {
                ListIterator listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (!(((String) listIterator.previous()).length() == 0)) {
                        emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            this.type = (String) emptyList.get(0);
            this.subType = (String) emptyList.get(1);
        }

        @Override // java.lang.Comparable
        public int compareTo(MimeType other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i = Intrinsics.areEqual(this.type, other.type) ? 2 : 0;
            return Intrinsics.areEqual(this.subType, other.subType) ? i + 1 : i;
        }

        public final String getSubType() {
            return this.subType;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ParamQuery {
        private final List arguments = new ArrayList();
        private String paramRegex;

        public final void addArgumentName(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.arguments.add(name);
        }

        public final List getArguments() {
            return this.arguments;
        }

        public final String getParamRegex() {
            return this.paramRegex;
        }

        public final void setParamRegex(String str) {
            this.paramRegex = str;
        }
    }

    public NavDeepLink(String str, String str2, String str3) {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        Lazy lazy5;
        Lazy lazy6;
        Lazy lazy7;
        Lazy lazy8;
        this.uriPattern = str;
        this.action = str2;
        this.mimeType = str3;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$pathPattern$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Pattern invoke() {
                String str4;
                str4 = NavDeepLink.this.pathRegex;
                if (str4 != null) {
                    return Pattern.compile(str4, 2);
                }
                return null;
            }
        });
        this.pathPattern$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$isParameterizedQuery$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf((NavDeepLink.this.getUriPattern() == null || Uri.parse(NavDeepLink.this.getUriPattern()).getQuery() == null) ? false : true);
            }
        });
        this.isParameterizedQuery$delegate = lazy2;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        lazy3 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.navigation.NavDeepLink$queryArgsMap$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map invoke() {
                Map parseQuery;
                parseQuery = NavDeepLink.this.parseQuery();
                return parseQuery;
            }
        });
        this.queryArgsMap$delegate = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.navigation.NavDeepLink$fragArgsAndRegex$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Pair invoke() {
                Pair parseFragment;
                parseFragment = NavDeepLink.this.parseFragment();
                return parseFragment;
            }
        });
        this.fragArgsAndRegex$delegate = lazy4;
        lazy5 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.navigation.NavDeepLink$fragArgs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List invoke() {
                Pair fragArgsAndRegex;
                List list;
                fragArgsAndRegex = NavDeepLink.this.getFragArgsAndRegex();
                return (fragArgsAndRegex == null || (list = (List) fragArgsAndRegex.getFirst()) == null) ? new ArrayList() : list;
            }
        });
        this.fragArgs$delegate = lazy5;
        lazy6 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.navigation.NavDeepLink$fragRegex$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Pair fragArgsAndRegex;
                fragArgsAndRegex = NavDeepLink.this.getFragArgsAndRegex();
                if (fragArgsAndRegex != null) {
                    return (String) fragArgsAndRegex.getSecond();
                }
                return null;
            }
        });
        this.fragRegex$delegate = lazy6;
        lazy7 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$fragPattern$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Pattern invoke() {
                String fragRegex;
                fragRegex = NavDeepLink.this.getFragRegex();
                if (fragRegex != null) {
                    return Pattern.compile(fragRegex, 2);
                }
                return null;
            }
        });
        this.fragPattern$delegate = lazy7;
        lazy8 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$mimeTypePattern$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Pattern invoke() {
                String str4;
                str4 = NavDeepLink.this.mimeTypeRegex;
                if (str4 != null) {
                    return Pattern.compile(str4);
                }
                return null;
            }
        });
        this.mimeTypePattern$delegate = lazy8;
        parsePath();
        parseMime();
    }

    private final void buildRegex(String str, List list, StringBuilder sb) {
        Matcher matcher = FILL_IN_PATTERN.matcher(str);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group(1);
            Intrinsics.checkNotNull(group, "null cannot be cast to non-null type kotlin.String");
            list.add(group);
            if (matcher.start() > i) {
                String substring = str.substring(i, matcher.start());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(Pattern.quote(substring));
            }
            sb.append("([^/]+?)");
            i = matcher.end();
        }
        if (i < str.length()) {
            String substring2 = str.substring(i);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            sb.append(Pattern.quote(substring2));
        }
    }

    private final List getFragArgs() {
        return (List) this.fragArgs$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair getFragArgsAndRegex() {
        return (Pair) this.fragArgsAndRegex$delegate.getValue();
    }

    private final Pattern getFragPattern() {
        return (Pattern) this.fragPattern$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFragRegex() {
        return (String) this.fragRegex$delegate.getValue();
    }

    private final boolean getMatchingPathArguments(Matcher matcher, Bundle bundle, Map map) {
        int collectionSizeOrDefault;
        List list = this.pathArgs;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            String value = Uri.decode(matcher.group(i2));
            NavArgument navArgument = (NavArgument) map.get(str);
            try {
                Intrinsics.checkNotNullExpressionValue(value, "value");
                if (parseArgument(bundle, str, value, navArgument)) {
                    return false;
                }
                arrayList.add(Unit.INSTANCE);
                i = i2;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return true;
    }

    private final boolean getMatchingQueryArguments(Uri uri, Bundle bundle, Map map) {
        String query;
        for (Map.Entry entry : getQueryArgsMap().entrySet()) {
            String str = (String) entry.getKey();
            ParamQuery paramQuery = (ParamQuery) entry.getValue();
            List<String> queryParameters = uri.getQueryParameters(str);
            if (this.isSingleQueryParamValueOnly && (query = uri.getQuery()) != null && !Intrinsics.areEqual(query, uri.toString())) {
                queryParameters = CollectionsKt__CollectionsJVMKt.listOf(query);
            }
            if (!parseInputParams(queryParameters, paramQuery, bundle, map)) {
                return false;
            }
        }
        return true;
    }

    private final void getMatchingUriFragment(String str, Bundle bundle, Map map) {
        int collectionSizeOrDefault;
        Pattern fragPattern = getFragPattern();
        Matcher matcher = fragPattern != null ? fragPattern.matcher(String.valueOf(str)) : null;
        if (matcher != null && matcher.matches()) {
            List fragArgs = getFragArgs();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(fragArgs, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            int i = 0;
            for (Object obj : fragArgs) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                String str2 = (String) obj;
                String value = Uri.decode(matcher.group(i2));
                NavArgument navArgument = (NavArgument) map.get(str2);
                try {
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    if (parseArgument(bundle, str2, value, navArgument)) {
                        return;
                    }
                    arrayList.add(Unit.INSTANCE);
                    i = i2;
                } catch (IllegalArgumentException unused) {
                    return;
                }
            }
        }
    }

    private final Pattern getMimeTypePattern() {
        return (Pattern) this.mimeTypePattern$delegate.getValue();
    }

    private final Pattern getPathPattern() {
        return (Pattern) this.pathPattern$delegate.getValue();
    }

    private final Map getQueryArgsMap() {
        return (Map) this.queryArgsMap$delegate.getValue();
    }

    private final boolean isParameterizedQuery() {
        return ((Boolean) this.isParameterizedQuery$delegate.getValue()).booleanValue();
    }

    private final boolean parseArgument(Bundle bundle, String str, String str2, NavArgument navArgument) {
        if (navArgument != null) {
            navArgument.getType().parseAndPut(bundle, str, str2);
            return false;
        }
        bundle.putString(str, str2);
        return false;
    }

    private final boolean parseArgumentForRepeatedParam(Bundle bundle, String str, String str2, NavArgument navArgument) {
        if (!bundle.containsKey(str)) {
            return true;
        }
        if (navArgument == null) {
            return false;
        }
        NavType type = navArgument.getType();
        type.parseAndPut(bundle, str, str2, type.get(bundle, str));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair parseFragment() {
        String str = this.uriPattern;
        if (str == null || Uri.parse(str).getFragment() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String fragment = Uri.parse(this.uriPattern).getFragment();
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(fragment);
        buildRegex(fragment, arrayList, sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "fragRegex.toString()");
        return TuplesKt.to(arrayList, sb2);
    }

    private final boolean parseInputParams(List list, ParamQuery paramQuery, Bundle bundle, Map map) {
        int collectionSizeOrDefault;
        if (list == null) {
            return true;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String paramRegex = paramQuery.getParamRegex();
            Matcher matcher = paramRegex != null ? Pattern.compile(paramRegex, 32).matcher(str) : null;
            if (matcher == null || !matcher.matches()) {
                return false;
            }
            Bundle bundle2 = new Bundle();
            try {
                List arguments = paramQuery.getArguments();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                int i = 0;
                for (Object obj : arguments) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    String str2 = (String) obj;
                    String group = matcher.group(i2);
                    if (group == null) {
                        group = "";
                    } else {
                        Intrinsics.checkNotNullExpressionValue(group, "argMatcher.group(index + 1) ?: \"\"");
                    }
                    NavArgument navArgument = (NavArgument) map.get(str2);
                    if (parseArgumentForRepeatedParam(bundle, str2, group, navArgument)) {
                        if (!Intrinsics.areEqual(group, '{' + str2 + '}') && parseArgument(bundle2, str2, group, navArgument)) {
                            return false;
                        }
                    }
                    arrayList.add(Unit.INSTANCE);
                    i = i2;
                }
                bundle.putAll(bundle2);
            } catch (IllegalArgumentException unused) {
            }
        }
        return true;
    }

    private final void parseMime() {
        String replace$default;
        if (this.mimeType == null) {
            return;
        }
        if (!Pattern.compile("^[\\s\\S]+/[\\s\\S]+$").matcher(this.mimeType).matches()) {
            throw new IllegalArgumentException(("The given mimeType " + this.mimeType + " does not match to required \"type/subtype\" format").toString());
        }
        MimeType mimeType = new MimeType(this.mimeType);
        replace$default = StringsKt__StringsJVMKt.replace$default("^(" + mimeType.getType() + "|[*]+)/(" + mimeType.getSubType() + "|[*]+)$", "*|[*]", "[\\s\\S]", false, 4, (Object) null);
        this.mimeTypeRegex = replace$default;
    }

    private final void parsePath() {
        boolean contains$default;
        String replace$default;
        boolean contains$default2;
        if (this.uriPattern == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("^");
        if (!SCHEME_PATTERN.matcher(this.uriPattern).find()) {
            sb.append("http[s]?://");
        }
        Matcher matcher = Pattern.compile("(\\?|\\#|$)").matcher(this.uriPattern);
        matcher.find();
        boolean z = false;
        String substring = this.uriPattern.substring(0, matcher.start());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        buildRegex(substring, this.pathArgs, sb);
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) sb, (CharSequence) ".*", false, 2, (Object) null);
        if (!contains$default) {
            contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) sb, (CharSequence) "([^/]+?)", false, 2, (Object) null);
            if (!contains$default2) {
                z = true;
            }
        }
        this.isExactDeepLink = z;
        sb.append("($|(\\?(.)*)|(\\#(.)*))");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "uriRegex.toString()");
        replace$default = StringsKt__StringsJVMKt.replace$default(sb2, ".*", "\\E.*\\Q", false, 4, (Object) null);
        this.pathRegex = replace$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map parseQuery() {
        Object firstOrNull;
        String replace$default;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!isParameterizedQuery()) {
            return linkedHashMap;
        }
        Uri parse = Uri.parse(this.uriPattern);
        for (String paramName : parse.getQueryParameterNames()) {
            StringBuilder sb = new StringBuilder();
            List<String> queryParams = parse.getQueryParameters(paramName);
            int i = 0;
            if (!(queryParams.size() <= 1)) {
                throw new IllegalArgumentException(("Query parameter " + paramName + " must only be present once in " + this.uriPattern + ". To support repeated query parameters, use an array type for your argument and the pattern provided in your URI will be used to parse each query parameter instance.").toString());
            }
            Intrinsics.checkNotNullExpressionValue(queryParams, "queryParams");
            firstOrNull = CollectionsKt___CollectionsKt.firstOrNull(queryParams);
            String queryParam = (String) firstOrNull;
            if (queryParam == null) {
                this.isSingleQueryParamValueOnly = true;
                queryParam = paramName;
            }
            Matcher matcher = FILL_IN_PATTERN.matcher(queryParam);
            ParamQuery paramQuery = new ParamQuery();
            while (matcher.find()) {
                String group = matcher.group(1);
                Intrinsics.checkNotNull(group, "null cannot be cast to non-null type kotlin.String");
                paramQuery.addArgumentName(group);
                Intrinsics.checkNotNullExpressionValue(queryParam, "queryParam");
                String substring = queryParam.substring(i, matcher.start());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(Pattern.quote(substring));
                sb.append("(.+?)?");
                i = matcher.end();
            }
            if (i < queryParam.length()) {
                Intrinsics.checkNotNullExpressionValue(queryParam, "queryParam");
                String substring2 = queryParam.substring(i);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                sb.append(Pattern.quote(substring2));
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "argRegex.toString()");
            replace$default = StringsKt__StringsJVMKt.replace$default(sb2, ".*", "\\E.*\\Q", false, 4, (Object) null);
            paramQuery.setParamRegex(replace$default);
            Intrinsics.checkNotNullExpressionValue(paramName, "paramName");
            linkedHashMap.put(paramName, paramQuery);
        }
        return linkedHashMap;
    }

    public final int calculateMatchingPathSegments$navigation_common_release(Uri uri) {
        Set intersect;
        if (uri == null || this.uriPattern == null) {
            return 0;
        }
        List<String> requestedPathSegments = uri.getPathSegments();
        List<String> uriPathSegments = Uri.parse(this.uriPattern).getPathSegments();
        Intrinsics.checkNotNullExpressionValue(requestedPathSegments, "requestedPathSegments");
        Intrinsics.checkNotNullExpressionValue(uriPathSegments, "uriPathSegments");
        intersect = CollectionsKt___CollectionsKt.intersect(requestedPathSegments, uriPathSegments);
        return intersect.size();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NavDeepLink)) {
            return false;
        }
        NavDeepLink navDeepLink = (NavDeepLink) obj;
        return Intrinsics.areEqual(this.uriPattern, navDeepLink.uriPattern) && Intrinsics.areEqual(this.action, navDeepLink.action) && Intrinsics.areEqual(this.mimeType, navDeepLink.mimeType);
    }

    public final String getAction() {
        return this.action;
    }

    public final List getArgumentsNames$navigation_common_release() {
        List plus;
        List plus2;
        List list = this.pathArgs;
        Collection values = getQueryArgsMap().values();
        ArrayList arrayList = new ArrayList();
        Iterator it = values.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((ParamQuery) it.next()).getArguments());
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection) list, (Iterable) arrayList);
        plus2 = CollectionsKt___CollectionsKt.plus((Collection) plus, (Iterable) getFragArgs());
        return plus2;
    }

    public final Bundle getMatchingArguments(Uri deepLink, Map arguments) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Pattern pathPattern = getPathPattern();
        Matcher matcher = pathPattern != null ? pathPattern.matcher(deepLink.toString()) : null;
        if (matcher == null || !matcher.matches()) {
            return null;
        }
        final Bundle bundle = new Bundle();
        if (!getMatchingPathArguments(matcher, bundle, arguments)) {
            return null;
        }
        if (isParameterizedQuery() && !getMatchingQueryArguments(deepLink, bundle, arguments)) {
            return null;
        }
        getMatchingUriFragment(deepLink.getFragment(), bundle, arguments);
        if (!NavArgumentKt.missingRequiredArguments(arguments, new Function1() { // from class: androidx.navigation.NavDeepLink$getMatchingArguments$missingRequiredArguments$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String argName) {
                Intrinsics.checkNotNullParameter(argName, "argName");
                return Boolean.valueOf(!bundle.containsKey(argName));
            }
        }).isEmpty()) {
            return null;
        }
        return bundle;
    }

    public final Bundle getMatchingPathAndQueryArgs$navigation_common_release(Uri uri, Map arguments) {
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Bundle bundle = new Bundle();
        if (uri == null) {
            return bundle;
        }
        Pattern pathPattern = getPathPattern();
        Matcher matcher = pathPattern != null ? pathPattern.matcher(uri.toString()) : null;
        if (matcher == null || !matcher.matches()) {
            return bundle;
        }
        getMatchingPathArguments(matcher, bundle, arguments);
        if (isParameterizedQuery()) {
            getMatchingQueryArguments(uri, bundle, arguments);
        }
        return bundle;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final int getMimeTypeMatchRating(String mimeType) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (this.mimeType != null) {
            Pattern mimeTypePattern = getMimeTypePattern();
            Intrinsics.checkNotNull(mimeTypePattern);
            if (mimeTypePattern.matcher(mimeType).matches()) {
                return new MimeType(this.mimeType).compareTo(new MimeType(mimeType));
            }
        }
        return -1;
    }

    public final String getUriPattern() {
        return this.uriPattern;
    }

    public int hashCode() {
        String str = this.uriPattern;
        int hashCode = ((str != null ? str.hashCode() : 0) + 0) * 31;
        String str2 = this.action;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mimeType;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final boolean isExactDeepLink() {
        return this.isExactDeepLink;
    }
}
