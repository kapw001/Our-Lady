package com.pappayaed.common;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;


import com.pappayaed.R;
import com.pappayaed.data.model.AttendanceList;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.HomeWork;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import calendar.android.com.customcalendar.CalendarUtils;
import calendar.android.com.customcalendar.EventObjects;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yasar on 26/4/17.
 */

public class Utils {
    private static final String TAG = "Utils";
    private static ProgressDialog progressDialog;

    public static void show(Activity context, String title, String msg) {
        new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme))
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }


    public static void showProgress(Context context, String msg) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    public static void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
//            progressDialog = null;
        }
    }


    public static Bitmap decodeBitmap(Context context, String encodedImage) {
        Log.e(TAG, "decodeBitmap: " + encodedImage);
        if (!encodedImage.equalsIgnoreCase("")) {
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else {
            return BitmapFactory.decodeResource(context.getResources(),
                    R.mipmap.profilebig);
        }
    }


    public static String getTimeFromDate(String d) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa"); // Set your date format
        String currentData = sdf.format(date);
        return currentData;
    }


    public static String getConvertedDate(String d) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Set your date format
        String currentData = sdf.format(date);
        return currentData;
    }

    public static void animateRevealShow(View viewRoot) {

        if (Build.VERSION.SDK_INT >= 21) {
            int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
            int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
            int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
            viewRoot.setVisibility(View.VISIBLE);
//        anim.setDuration(500);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.start();
            // Call some material design APIs here
        }

    }

    public static Calendar getCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }


    public static Date convertStringToDate1(String s) {
        String str_date = s;
        DateFormat formatter;
        Date date = null;
//        formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Date convertStringToDate(String s) {
        String str_date = s;
        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
//        formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date convertStringToDate(String s, String format) {
        String str_date = s;
        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date date, String format) {
        Format formatter = new SimpleDateFormat(format);
        String s = formatter.format(date);
        return s;
    }

    public static String convertDateToString(String date, String format, String retrnFormat) {
        String s = convertDateToString(convertStringToDate(date, format), retrnFormat);
        return s;
    }

    public static String convertDateToString(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        return s;
    }

    public static String convertDateGivenFormat(Date date) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Format formatter = new SimpleDateFormat("MM-dd-yyyy");
        String s = formatter.format(date);
        return s;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long putdays(String startdate, String enddate) throws ParseException {
        String str_date = startdate;
        String end_date = enddate;
        DateFormat formatter;
        Date s;
        Date e;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        s = formatter.parse(str_date);
        e = formatter.parse(end_date);
        long diff = e.getTime() - s.getTime();
        long leavedays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        return leavedays;
    }


    public static String getCurrentDate(int plusDays, String format) {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, plusDays);

        SimpleDateFormat df = new SimpleDateFormat(format);
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }

    public static String getCurrentDate(String startdate, int plusDays, String format) {
        Calendar c = Calendar.getInstance();

        c.setTime(convertStringToDate(startdate));
        c.add(Calendar.DATE, plusDays);

        SimpleDateFormat df = new SimpleDateFormat(format);
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }


    public static String getFullNameFromDate(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("d\nEEEE");
        String formattedDate = df.format(date.getTime());

        return formattedDate;


    }

    public static int diffBwTwodates(String startdate, String enddate) {

        try {
            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
            Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
            long diff = eDate.getTime() - sdate.getTime();
            int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
            return numOfDays;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public static String getMonthByName(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = format1.parse(date);
        DateFormat format2 = new SimpleDateFormat("MMM");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getDaysByNumber(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = format1.parse(date);
        DateFormat format2 = new SimpleDateFormat("dd");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getDaysByName(String date) throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = format1.parse(date);
        DateFormat format2 = new SimpleDateFormat("EEE");
        String finalDay = format2.format(dt1);
        return finalDay;
    }


    public static Spanned getHtmlText(String msg) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(msg, Html.FROM_HTML_MODE_COMPACT);
        }

        return Html.fromHtml(msg);

    }

    public static String getDecimal(Double aDouble) {

//        double decimalValue = aDouble / 1000000.00;
//
//        DecimalFormat df2 = new DecimalFormat(".##");

        return String.format("%.2f", aDouble);

//        return df2.format(decimalValue);
    }


    public static void setBorderColor(final CircleImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {

                Palette.Swatch vibrant = palette.getVibrantSwatch();

                if (vibrant != null) {
                    imageView.setBorderWidth(15);
                    imageView.setBorderColor(vibrant.getRgb());

//                    Toast.makeText(imageView.getContext(), "Called", Toast.LENGTH_SHORT).show();

//                    imageView.setBorderOverlay(true);
                } else {
//                    imageView.setBorderWidth(15);
//                    imageView.setBorderColor(ContextCompat.getColor(imageView.getContext(), R.color.line2));
//                    Toast.makeText(imageView.getContext(), "No", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static List<Circular> getParticularDateEventsForCircular(Date mDate, List<Circular> allEvents) {

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);

        Calendar eventCalendar = Calendar.getInstance();

        List mList = new ArrayList<EventObjects>();

        for (int i = 0; i < allEvents.size(); i++) {

            Date date = CalendarUtils.convertStringToDate(allEvents.get(i).getDate());

            eventCalendar.setTime(date);
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {

                mList.add(allEvents.get(i));

            }
        }

        return mList;

    }

    public static List<AttendanceList> getParticularDateEventsForAttendance(Date mDate, List<AttendanceList> allEvents) {

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);

        Calendar eventCalendar = Calendar.getInstance();

        List mList = new ArrayList<EventObjects>();

        for (int i = 0; i < allEvents.size(); i++) {

            Date date = CalendarUtils.convertStringToDate1(allEvents.get(i).getAttendanceDate());

            eventCalendar.setTime(date);
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {

                mList.add(allEvents.get(i));

            }
        }

        return mList;

    }

    public static List<HomeWork> getParticularDateEventsForHomeWork(Date mDate, List<HomeWork> allEvents) {

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);

        Calendar eventCalendar = Calendar.getInstance();

        List mList = new ArrayList<EventObjects>();

        for (int i = 0; i < allEvents.size(); i++) {

            Date date = CalendarUtils.convertStringToDate(allEvents.get(i).getDate());

            eventCalendar.setTime(date);
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {

                mList.add(allEvents.get(i));

            }
        }

        return mList;

    }


    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public static void saveImage(Context context, Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = shre.edit();
        edit.putString("image_data", encodedImage);
        edit.commit();
    }

    public static Bitmap getBitmap(Context context) {

        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(context);
        String previouslyEncodedImage = shre.getString("image_data", "");

        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        }

        return null;
    }

}
