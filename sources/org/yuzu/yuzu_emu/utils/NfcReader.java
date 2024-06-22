package org.yuzu.yuzu_emu.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.NativeInput;

/* loaded from: classes.dex */
public final class NfcReader {
    private final Activity activity;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    public NfcReader(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    private final byte[] ntag215FastRead(NfcA nfcA, int i, int i2) {
        return nfcA.transceive(new byte[]{58, (byte) (i & 255), (byte) (i2 & 255)});
    }

    private final byte[] ntag215ReadAll(NfcA nfcA) {
        int maxTransceiveLength = nfcA.getMaxTransceiveLength();
        byte[] bArr = new byte[540];
        int i = maxTransceiveLength - 1;
        if (i <= 0) {
            throw new IllegalArgumentException("Step must be positive, was: " + i + ".");
        }
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, 540, i);
        if (progressionLastElement >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 / 4;
                int i4 = (i2 + maxTransceiveLength) / 4;
                if (i4 > 134) {
                    i4 = 134;
                }
                try {
                    System.arraycopy(ntag215FastRead(nfcA, i3, i4 - 1), 0, bArr, i2, (i4 - i3) * 4);
                    if (i2 == progressionLastElement) {
                        break;
                    }
                    i2 += i;
                } catch (IOException unused) {
                    return null;
                }
            }
        }
        return bArr;
    }

    private final void readTagData(Tag tag) {
        boolean contains;
        NfcA nfcA;
        String[] techList = tag.getTechList();
        Intrinsics.checkNotNullExpressionValue(techList, "getTechList(...)");
        contains = ArraysKt___ArraysKt.contains(techList, "android.nfc.tech.NfcA");
        if (contains && (nfcA = NfcA.get(tag)) != null) {
            nfcA.connect();
            byte[] ntag215ReadAll = ntag215ReadAll(nfcA);
            if (ntag215ReadAll == null) {
                return;
            }
            NativeInput.INSTANCE.onReadNfcTag(ntag215ReadAll);
            NfcAdapter nfcAdapter = this.nfcAdapter;
            if (nfcAdapter != null) {
                nfcAdapter.ignore(tag, 1000, new NfcAdapter.OnTagRemovedListener() { // from class: org.yuzu.yuzu_emu.utils.NfcReader$$ExternalSyntheticLambda1
                    @Override // android.nfc.NfcAdapter.OnTagRemovedListener
                    public final void onTagRemoved() {
                        NfcReader.readTagData$lambda$0();
                    }
                }, new Handler(Looper.getMainLooper()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readTagData$lambda$0() {
        NativeInput.INSTANCE.onRemoveNfcTag();
    }

    public final void initialize() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.activity);
        if (defaultAdapter == null) {
            return;
        }
        this.nfcAdapter = defaultAdapter;
        Activity activity = this.activity;
        Activity activity2 = this.activity;
        this.pendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity2, activity2.getClass()), Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
        new IntentFilter("android.nfc.action.TAG_DISCOVERED").addCategory("android.intent.category.DEFAULT");
    }

    public final void onNewIntent(Intent intent) {
        Object parcelableExtra;
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual("android.nfc.action.TAG_DISCOVERED", action) || Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", action) || Intrinsics.areEqual("android.nfc.action.NDEF_DISCOVERED", action)) {
            if (Build.VERSION.SDK_INT < 33) {
                Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
                if (tag == null) {
                    return;
                }
                readTagData(tag);
                return;
            }
            parcelableExtra = intent.getParcelableExtra("android.nfc.extra.TAG", Tag.class);
            Tag tag2 = (Tag) parcelableExtra;
            if (tag2 == null) {
                return;
            }
            readTagData(tag2);
        }
    }

    public final void startScanning() {
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this.activity, this.pendingIntent, null, null);
        }
    }

    public final void stopScanning() {
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this.activity);
        }
    }
}
