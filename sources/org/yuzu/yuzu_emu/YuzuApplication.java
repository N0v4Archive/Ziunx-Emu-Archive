package org.yuzu.yuzu_emu;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yuzu.yuzu_emu.features.input.NativeInput;
import org.yuzu.yuzu_emu.utils.DirectoryInitialization;
import org.yuzu.yuzu_emu.utils.DocumentsTree;
import org.yuzu.yuzu_emu.utils.GpuDriverHelper;
import org.yuzu.yuzu_emu.utils.Log;

/* loaded from: classes.dex */
public final class YuzuApplication extends Application {
    public static final Companion Companion = new Companion(null);
    public static YuzuApplication application;
    private static DocumentsTree documentsTree;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Context getAppContext() {
            Context applicationContext = getApplication().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return applicationContext;
        }

        public final YuzuApplication getApplication() {
            YuzuApplication yuzuApplication = YuzuApplication.application;
            if (yuzuApplication != null) {
                return yuzuApplication;
            }
            Intrinsics.throwUninitializedPropertyAccessException("application");
            return null;
        }

        public final DocumentsTree getDocumentsTree() {
            return YuzuApplication.documentsTree;
        }

        public final void setApplication(YuzuApplication yuzuApplication) {
            Intrinsics.checkNotNullParameter(yuzuApplication, "<set-?>");
            YuzuApplication.application = yuzuApplication;
        }
    }

    private final void createNotificationChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(getString(R$string.notice_notification_channel_id), getString(R$string.notice_notification_channel_name), 4);
        notificationChannel.setDescription(getString(R$string.notice_notification_channel_description));
        notificationChannel.setSound(null, null);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Companion.setApplication(this);
        documentsTree = new DocumentsTree();
        DirectoryInitialization.INSTANCE.start();
        GpuDriverHelper.INSTANCE.initializeDriverParameters();
        NativeInput.INSTANCE.reloadInputDevices();
        NativeLibrary.INSTANCE.logDeviceInfo();
        Log.INSTANCE.logDeviceInfo();
        createNotificationChannels();
    }
}
