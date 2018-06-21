package calendar.android.com.customcalendar;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import java.util.Date;

/**
 * Created by yasar on 13/4/18.
 */

public class EventObjects {

    private int id;
    private String message;
    private Date date;
    @ColorInt
    private int color;

    public EventObjects(String message, Date date, int color) {
        this.message = message;
        this.date = date;
        this.color = color;
    }

    public EventObjects(int id, String message, Date date, int color) {
        this.date = date;
        this.message = message;
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
