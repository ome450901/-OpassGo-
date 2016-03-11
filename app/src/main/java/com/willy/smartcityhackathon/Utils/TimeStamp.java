package com.willy.smartcityhackathon.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Willy on 2015/10/29.
 */
public class TimeStamp {

    public static String getDateString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getDateStringFromTimeMillis(long timeMillis){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeMillis));
        return df.format(calendar.getTime());
    }

    public static Date getDateFromString(String date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar.getTime();
    }
}
