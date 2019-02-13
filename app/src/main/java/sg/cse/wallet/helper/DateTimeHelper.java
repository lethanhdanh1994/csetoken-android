package sg.cse.wallet.helper;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by thongph on 07.26.2017
 * <p>
 * Last modified on 07.26.2017
 */
public class DateTimeHelper {

    public static final String SDF_DEFAULT = "yyyy-MM-dd";
    public static final String SDF_VN = "dd-MM-yyyy";
    public static final String SDF_WITH_MONTH = "dd-MMM-yyyy";
    public static final String SDF_WITH_MONTH_SPACE = "dd MMM yyyy";
    public static final String SDF_WITH_SHORT_MONTH_SPACE = "dd MMM yy";
    public static final String SDF_WITH_DAY_MONTH_SPACE = "EEE dd MMM yyyy";
    public static final String SDF_WITH_T = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String SDF_MONTH_YEAR = "MMMM yyyy";
    public static final String SDF_SHORT_MONTH_YEAR = "MM yyyy";
    private static final String TAG = DateTimeHelper.class.getSimpleName();
    private static boolean firstDay;

    public static void setFirstDay(boolean firstDay) {
        DateTimeHelper.firstDay = firstDay;
    }

    public static void addDatePicker(final View view, final String positiveText,
                                     final String negativeText, final OnDateListener onDateListener) {
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String date = new SimpleDateFormat(SDF_DEFAULT, Locale.US)
                        .format(myCalendar.getTime());
                if (onDateListener != null) {
                    onDateListener.onGetDate(date);
                }
            }

        };

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        firstDay ? 1 : myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime()); // set max date
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setFirstDay(false);

                                DatePicker datePicker = datePickerDialog
                                        .getDatePicker();
                                dateListener.onDateSet(datePicker,
                                        datePicker.getYear(),
                                        datePicker.getMonth(),
                                        datePicker.getDayOfMonth());
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
    }

    public static void addDatePickerWithFirstDay(final TextView textView, final String positiveText,
                                                 final String negativeText, final OnDateListener onDateListener) {
        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String date = new SimpleDateFormat(SDF_VN, Locale.US)
                        .format(myCalendar.getTime());
                if (onDateListener != null) {
                    onDateListener.onGetDate(date);
                }
            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(textView.getContext(),
                        dateListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime()); // set max date
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker datePicker = datePickerDialog
                                        .getDatePicker();
                                dateListener.onDateSet(datePicker,
                                        datePicker.getYear(),
                                        datePicker.getMonth(),
                                        datePicker.getDayOfMonth());
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
    }

    public static String getFirstDayOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SDF_VN, Locale.US);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getCurrentDayOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SDF_VN, Locale.US);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());

        return formatter.format(milliSeconds);
    }

    public static String reFormatDate(String dateIn, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        return simpleDateFormat.format(date);
    }

    public static long toMilliseconds(String stringDate, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        long dateMilliseconds = 0;
        try {
            Date date = simpleDateFormat.parse(stringDate);

            dateMilliseconds = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMilliseconds;
    }

    public static String formattedDate(String date, String inDateFormat, String outDateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(inDateFormat, Locale.US);
        SimpleDateFormat output = new SimpleDateFormat(outDateFormat, Locale.US);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output.format(d);
    }

    public static int minuteBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / android.text.format.DateUtils.SECOND_IN_MILLIS);
    }

    public static int hoursBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / android.text.format.DateUtils.MINUTE_IN_MILLIS);
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / android.text.format.DateUtils.DAY_IN_MILLIS);
    }

    /**
     * Get week of year
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static int getWeekOfYear(String dateString, String dateFormat) {
        int weekOfYear = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(dateString);
            calendar.setTime(date);
            weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekOfYear;
    }

    /**
     * Get Week of year with day is last day of week
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static int getWeekOfYearWithLastDayOfWeek(String dateString, String dateFormat) {
        int weekOfYear = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(dateString);
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekOfYear;
    }

    /**
     * Get week of year
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static int getWeekOfYearLarger(String dateString, String dateFormat) {
        int weekOfYear = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(dateString);
            calendar.setTime(date);
            int newYear = calendar.get(Calendar.YEAR);
            weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            if (currentYear < newYear) {
                weekOfYear += 52; // because of current year < new year,
                // we should to +52 weeks of current year to new year
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekOfYear;
    }

    public static String getDayFromWeekNo(int weekNo, int year, String dateFormat) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.WEEK_OF_YEAR, weekNo);
        c.set(Calendar.YEAR, year);
        c.get(Calendar.DAY_OF_WEEK);// WTF WARNING this line is required to make the code work.
        // Might have something to do with an inner function called complete which gets called on get.
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // set current day of week is Monday
        return new SimpleDateFormat(dateFormat, Locale.getDefault()).format(c.getTime());
    }

    /**
     * Get date from given week (ex: Week 12 -> Mon-2018-03-04)
     *
     * @param weekNo
     * @return
     */
    public static String getDayFromWeekNo(int weekNo) {
        return getDayFromWeekNo(weekNo, Calendar.getInstance().get(Calendar.YEAR), SDF_WITH_DAY_MONTH_SPACE);
    }

    /**
     * Get calendar from week number
     *
     * @param weekNo
     * @return
     */
    public static Calendar getDateFromWeekNo(int weekNo, int currentYear) {
        Calendar c = new GregorianCalendar(Locale.getDefault());
        c.set(Calendar.YEAR, currentYear);
        c.set(Calendar.WEEK_OF_YEAR, weekNo);
        c.get(Calendar.DAY_OF_WEEK); // WTF WARNING this line is required to make the code work.
        // Might have something to do with an inner function called complete which gets called on get.
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // start week is Monday
        return c;
    }

    /**
     * Get calendar from current time
     *
     * @param currentTime
     * @param dateFormat
     * @return
     */
    public static Calendar getDateFromCurrentTime(String currentTime, String dateFormat) {
        Calendar c = new GregorianCalendar(Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(currentTime);
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Get all days from current week no
     *
     * @param weekNo
     * @return
     */
    public static List<String> getAllDaysFromCurrentWeek(int weekNo, int currentYear) {
        List<String> daysList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.clear();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNo);
        calendar.set(Calendar.YEAR, currentYear);
        for (int i = 0; i < 7; i++) {
            daysList.add(i, getDate(calendar.getTimeInMillis(), "dd"));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysList;
    }

    /**
     * Get day of week from date
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static String getDateWithDayOfWeekFromDate(String dateString, String dateFormat) {
        String dateWithDayOfWeek = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(dateString);
            calendar.setTime(date);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
            dateWithDayOfWeek = getCurrentShortDay(dayOfMonth) + " "
                    + new SimpleDateFormat(SDF_WITH_MONTH_SPACE, Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateWithDayOfWeek;
    }

    /**
     * Get current short day
     *
     * @param day
     * @return
     */
    public static String getCurrentShortDay(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
        }
        return "";
    }

    /**
     * Get current short day
     *
     * @param day
     * @return
     */
    public static String getCurrentFullDay(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }
        return "";
    }

    /**
     * Get date of week
     *
     * @param date
     * @return
     */
    public static String getDateOfWeek(int date) {
        switch (date) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
        }
        return "";
    }

    /**
     * Get time is milliseconds
     *
     * @param dateString
     * @param timeFormat
     * @return
     */
    public static long getTimeMs(String dateString, String timeFormat) {
        Date date;
        try {
            date = new SimpleDateFormat(timeFormat, Locale.ENGLISH).parse(dateString);
            return date.getTime();
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get date time in milliseconds
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static long getDateTimeMs(String dateString, String dateFormat) {
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateString);
            return date.getTime();
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get month from date
     *
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static String getMonthFromDate(String dateStr, String dateFormat) {
        int monthIndex = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateStr);
            calendar.setTime(date);
            monthIndex = calendar.get(Calendar.MONTH);// because of month start at 0
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getMonthFromIndex(monthIndex);
    }

    /**
     * Get month index
     *
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static int getMonthIndex(String dateStr, String dateFormat) {
        int monthIndex = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateStr);
            calendar.setTime(date);
            monthIndex = calendar.get(Calendar.MONTH);// because of month start at 0
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthIndex;
    }

    /**
     * Get month from index
     *
     * @param monthIndex
     * @return
     */
    private static String getMonthFromIndex(int monthIndex) {
        switch (monthIndex) {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
        }
        return "";
    }

    /**
     * Get Month & Year from date
     *
     * @param dateStr
     * @param inputFormat
     * @param outputFormat
     * @return
     */
    public static String getMonthYear(String dateStr, String inputFormat, String outputFormat) {
        String monthYear = "";
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(dateStr);
            date.setTime(getDateTimeMs(dateStr, inputFormat));
            monthYear = new SimpleDateFormat(outputFormat, Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthYear;
    }

    /**
     * Get relative time span
     *
     * @param fromTime
     * @param dateFormat
     * @return
     */
    public static String getRelativeTimeSpan(String fromTime, String dateFormat) {

        Calendar serverCalendar = getServerTimeToPhoneGMTTime(fromTime, dateFormat);
        Calendar localCalendar = getLocalGMTTime(dateFormat);

        int minuteAgo = getDifferenceMinute(serverCalendar.getTime(), localCalendar.getTime());

        StringBuilder dateStr = new StringBuilder();
        // compare
        if (minuteAgo <= 10) {
            dateStr.append("Just now");
        } else if (serverCalendar.get(Calendar.YEAR) == localCalendar.get(Calendar.YEAR)
                && serverCalendar.get(Calendar.MONTH) == localCalendar.get(Calendar.MONTH)
                && serverCalendar.get(Calendar.DAY_OF_MONTH) == localCalendar.get(Calendar.DAY_OF_MONTH)) {
            String timeOfDay = formattedDate(getDate(serverCalendar.getTimeInMillis(), dateFormat), dateFormat, "HH:mm");
            dateStr.append("Today ").append(timeOfDay);
        } else {
            String fullTimeOfDay = formattedDate(getDate(serverCalendar.getTimeInMillis(), dateFormat), dateFormat, "dd MMM HH:mm");
            dateStr.append(fullTimeOfDay);
        }
        return dateStr.toString();
    }

    /**
     * Get server GMT time
     *
     * @return
     */
    public static Calendar getServerTimeToPhoneGMTTime(String fromTime, String dateFormat) {
        Calendar serverCalendar = Calendar.getInstance();
        serverCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        // change to local time zone
        SimpleDateFormat sdfServer = new SimpleDateFormat(dateFormat, Locale.getDefault());
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        sdfServer.setTimeZone(utcZone);
        long serverMillis = 0;
        try {
            serverMillis = sdfServer.parse(fromTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date serverDate = new Date(serverMillis);
        serverCalendar.setTime(serverDate);
        return serverCalendar;
    }

    /**
     * Get local GMT time
     *
     * @return
     */
    private static Calendar getLocalGMTTime(String dateFormat) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        String localTime = getDate(localCalendar.getTimeInMillis(), dateFormat);
        SimpleDateFormat sdfLocal = new SimpleDateFormat(dateFormat, Locale.getDefault());
        sdfLocal.setTimeZone(TimeZone.getDefault());
        long localMillis = 0;
        try {
            localMillis = sdfLocal.parse(localTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date localDate = new Date(localMillis);
        localCalendar.setTime(localDate);
        return localCalendar;
    }

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    private static int getDifferenceMinute(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(different);
        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(different);
        long diffHours = TimeUnit.MILLISECONDS.toHours(different);
        long diffDays = TimeUnit.MILLISECONDS.toDays(different);

        return (int) diffMinutes;
    }

    /**
     * Get first day of week by current day
     *
     * @param currentDay
     * @param inDateFormat
     * @param outDateFormat
     * @return
     */
    public static String getDayOfWeek(String currentDay, int dayOfWeek, String inDateFormat, String outDateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(inDateFormat, Locale.getDefault());
        try {
            Date date = sdf.parse(currentDay);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // first day of week
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        SimpleDateFormat outSdf = new SimpleDateFormat(outDateFormat, Locale.getDefault());
        return outSdf.format(calendar.getTime());
    }

    /**
     * Get first day of week by current day
     *
     * @param firstdate
     * @param inDateFormat
     * @param outDateFormat
     * @return
     */
    public static String getLastDayFromStartDate(String firstdate, String inDateFormat, String outDateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(inDateFormat, Locale.getDefault());
        try {
            Date date = sdf.parse(firstdate);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // first day of week
        calendar.add(Calendar.DATE, 6);
        SimpleDateFormat outSdf = new SimpleDateFormat(outDateFormat, Locale.getDefault());
        return outSdf.format(calendar.getTime());
    }

    /**
     * Get first day of week by current day
     *
     * @param firstdate
     * @param inDateFormat
     * @return
     */
    public static long getLastDayIncludeHourMinuteSecondFromStartDate(String firstdate, String inDateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(inDateFormat, Locale.getDefault());
        try {
            Date date = sdf.parse(firstdate);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // first day of week
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);

        return calendar.getTime().getTime();
    }

    public interface OnDateListener {
        void onGetDate(String date);
    }

}
