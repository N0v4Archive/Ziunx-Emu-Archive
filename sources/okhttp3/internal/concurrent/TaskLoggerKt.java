package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes.dex */
public abstract class TaskLoggerKt {
    public static final String formatDuration(long j) {
        StringBuilder sb;
        long j2;
        long j3;
        long j4;
        if (j > -999500000) {
            if (j > -999500) {
                if (j <= 0) {
                    sb = new StringBuilder();
                    j4 = j - 500;
                } else if (j < 999500) {
                    sb = new StringBuilder();
                    j4 = j + 500;
                } else if (j < 999500000) {
                    sb = new StringBuilder();
                    j3 = j + 500000;
                } else {
                    sb = new StringBuilder();
                    j2 = j + 500000000;
                }
                sb.append(j4 / 1000);
                sb.append(" µs");
                String sb2 = sb.toString();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%6s", Arrays.copyOf(new Object[]{sb2}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                return format;
            }
            sb = new StringBuilder();
            j3 = j - 500000;
            sb.append(j3 / 1000000);
            sb.append(" ms");
            String sb22 = sb.toString();
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("%6s", Arrays.copyOf(new Object[]{sb22}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            return format2;
        }
        sb = new StringBuilder();
        j2 = j - 500000000;
        sb.append(j2 / 1000000000);
        sb.append(" s ");
        String sb222 = sb.toString();
        StringCompanionObject stringCompanionObject22 = StringCompanionObject.INSTANCE;
        String format22 = String.format("%6s", Arrays.copyOf(new Object[]{sb222}, 1));
        Intrinsics.checkNotNullExpressionValue(format22, "format(format, *args)");
        return format22;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void log(Task task, TaskQueue taskQueue, String str) {
        Logger logger = TaskRunner.Companion.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append(taskQueue.getName$okhttp());
        sb.append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(": ");
        sb.append(task.getName());
        logger.fine(sb.toString());
    }
}
