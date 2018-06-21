package calendar.android.com.customcalendar;

import java.util.Date;

/**
 * Created by yasar on 18/4/18.
 */

public class CalendarModel {

    private Date date;

    private boolean isSelected = false;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
