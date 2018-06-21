package calendar.android.com.customcalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yasar on 13/4/18.
 */

public class CalendarUtils {

    public static Date convertStringToDate(String dateInString) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date convertStringToDate1(String dateInString) {
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getMonthbyDate(Date dateInString) {
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateNmae = new SimpleDateFormat("MMMM dd - yyyy").format(dateInString.getTime());

        return dateNmae;
    }

    public static String getDate(Date dateInString) {
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateNmae = new SimpleDateFormat("yyyy-MM-dd").format(dateInString.getTime());

        return dateNmae;
    }


    public static List<EventObjects> getParticularDateEvents(Date mDate, List<EventObjects> allEvents) {

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);

        Calendar eventCalendar = Calendar.getInstance();

        List<EventObjects> mList = new ArrayList<EventObjects>();

        for (int i = 0; i < allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {

                mList.add(allEvents.get(i));

            }
        }

        return mList;

    }


}
