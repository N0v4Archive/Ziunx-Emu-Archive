package com.google.android.material.datepicker;

import android.content.Context;
import com.google.android.material.R$string;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
abstract class DateStrings {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDateString(long j) {
        return getDateString(j, null);
    }

    static String getDateString(long j, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j)) : isDateWithinCurrentYear(j) ? getMonthDay(j) : getYearMonthDay(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDayContentDescription(Context context, long j, boolean z, boolean z2, boolean z3) {
        String optionalYearMonthDayOfWeekDay = getOptionalYearMonthDayOfWeekDay(j);
        if (z) {
            optionalYearMonthDayOfWeekDay = String.format(context.getString(R$string.mtrl_picker_today_description), optionalYearMonthDayOfWeekDay);
        }
        return z2 ? String.format(context.getString(R$string.mtrl_picker_start_date_description), optionalYearMonthDayOfWeekDay) : z3 ? String.format(context.getString(R$string.mtrl_picker_end_date_description), optionalYearMonthDayOfWeekDay) : optionalYearMonthDayOfWeekDay;
    }

    static String getMonthDay(long j) {
        return getMonthDay(j, Locale.getDefault());
    }

    static String getMonthDay(long j, Locale locale) {
        return UtcDates.getAbbrMonthDayFormat(locale).format(new Date(j));
    }

    static String getMonthDayOfWeekDay(long j) {
        return getMonthDayOfWeekDay(j, Locale.getDefault());
    }

    static String getMonthDayOfWeekDay(long j, Locale locale) {
        return UtcDates.getAbbrMonthWeekdayDayFormat(locale).format(new Date(j));
    }

    static String getOptionalYearMonthDayOfWeekDay(long j) {
        return isDateWithinCurrentYear(j) ? getMonthDayOfWeekDay(j) : getYearMonthDayOfWeekDay(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getYearContentDescription(Context context, int i) {
        return UtcDates.getTodayCalendar().get(1) == i ? String.format(context.getString(R$string.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i)) : String.format(context.getString(R$string.mtrl_picker_navigate_to_year_description), Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getYearMonth(long j) {
        return UtcDates.getYearMonthFormat(Locale.getDefault()).format(new Date(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getYearMonthDay(long j) {
        return getYearMonthDay(j, Locale.getDefault());
    }

    static String getYearMonthDay(long j, Locale locale) {
        return UtcDates.getYearAbbrMonthDayFormat(locale).format(new Date(j));
    }

    static String getYearMonthDayOfWeekDay(long j) {
        return getYearMonthDayOfWeekDay(j, Locale.getDefault());
    }

    static String getYearMonthDayOfWeekDay(long j, Locale locale) {
        return UtcDates.getYearAbbrMonthWeekdayDayFormat(locale).format(new Date(j));
    }

    private static boolean isDateWithinCurrentYear(long j) {
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j);
        return todayCalendar.get(1) == utcCalendar.get(1);
    }
}
