package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class NotificationCompat {

    /* loaded from: classes.dex */
    public static class Action {
    }

    /* loaded from: classes.dex */
    public static final class BubbleMetadata {
        public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        boolean mAllowSystemGeneratedContextualActions;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        IconCompat mLargeIcon;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;
        public ArrayList mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortcutId;
        boolean mSilent;
        Object mSmallIcon;
        String mSortKey;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        public ArrayList mActions = new ArrayList();
        public ArrayList mPersonList = new ArrayList();
        ArrayList mInvisibleActions = new ArrayList();
        boolean mShowWhen = true;
        boolean mLocalOnly = false;
        int mColor = 0;
        int mVisibility = 0;
        int mBadgeIcon = 0;
        int mGroupAlertBehavior = 0;
        int mFgsDeferBehavior = 0;

        public Builder(Context context, String str) {
            Notification notification = new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = str;
            notification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        private void setFlag(int i, boolean z) {
            int i2;
            Notification notification = this.mNotification;
            if (z) {
                i2 = i | notification.flags;
            } else {
                i2 = (~i) & notification.flags;
            }
            notification.flags = i2;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mNotification.icon = i;
            return this;
        }
    }

    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }
}
