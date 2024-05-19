package ru.techtask.mobilshop.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date date = formatter.parse(strDate);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
