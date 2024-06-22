package okhttp3.internal.http2;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.internal.Util;
import okio.ByteString;

/* loaded from: classes.dex */
public final class Http2 {
    private static final String[] BINARY;
    public static final Http2 INSTANCE = new Http2();
    public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    private static final String[] FLAGS = new String[64];

    static {
        String replace$default;
        String[] strArr = new String[256];
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            String binaryString = Integer.toBinaryString(i2);
            Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(it)");
            replace$default = StringsKt__StringsJVMKt.replace$default(Util.format("%8s", binaryString), ' ', '0', false, 4, (Object) null);
            strArr[i2] = replace$default;
        }
        BINARY = strArr;
        String[] strArr2 = FLAGS;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i3 = iArr[0];
        strArr2[i3 | 8] = Intrinsics.stringPlus(strArr2[i3], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i4 = 0;
        while (i4 < 3) {
            int i5 = iArr2[i4];
            i4++;
            int i6 = iArr[0];
            String[] strArr3 = FLAGS;
            int i7 = i6 | i5;
            StringBuilder sb = new StringBuilder();
            sb.append((Object) strArr3[i6]);
            sb.append('|');
            sb.append((Object) strArr3[i5]);
            strArr3[i7] = sb.toString();
            strArr3[i7 | 8] = ((Object) strArr3[i6]) + '|' + ((Object) strArr3[i5]) + "|PADDED";
        }
        int length = FLAGS.length;
        while (i < length) {
            int i8 = i + 1;
            String[] strArr4 = FLAGS;
            if (strArr4[i] == null) {
                strArr4[i] = BINARY[i];
            }
            i = i8;
        }
    }

    private Http2() {
    }

    public final String formatFlags(int i, int i2) {
        String str;
        String str2;
        String str3;
        String replace$default;
        if (i2 == 0) {
            return "";
        }
        if (i != 2 && i != 3) {
            if (i == 4 || i == 6) {
                return i2 == 1 ? "ACK" : BINARY[i2];
            }
            if (i != 7 && i != 8) {
                String[] strArr = FLAGS;
                if (i2 < strArr.length) {
                    str = strArr[i2];
                    Intrinsics.checkNotNull(str);
                } else {
                    str = BINARY[i2];
                }
                String str4 = str;
                if (i == 5 && (i2 & 4) != 0) {
                    str2 = "HEADERS";
                    str3 = "PUSH_PROMISE";
                } else {
                    if (i != 0 || (i2 & 32) == 0) {
                        return str4;
                    }
                    str2 = "PRIORITY";
                    str3 = "COMPRESSED";
                }
                replace$default = StringsKt__StringsJVMKt.replace$default(str4, str2, str3, false, 4, (Object) null);
                return replace$default;
            }
        }
        return BINARY[i2];
    }

    public final String formattedType$okhttp(int i) {
        String[] strArr = FRAME_NAMES;
        return i < strArr.length ? strArr[i] : Util.format("0x%02x", Integer.valueOf(i));
    }

    public final String frameLog(boolean z, int i, int i2, int i3, int i4) {
        return Util.format("%s 0x%08x %5d %-13s %s", z ? "<<" : ">>", Integer.valueOf(i), Integer.valueOf(i2), formattedType$okhttp(i3), formatFlags(i3, i4));
    }
}
