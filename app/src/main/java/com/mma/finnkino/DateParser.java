package com.mma.finnkino;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date parseDateTime(String text, String dateFormat) {
        Date date = null;

        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            date = df.parse(text);
        } catch (ParseException ex) {
            date = null;
        }

        return date;
    }

    public static Date parseDateTime(String dateString, String timeString, String dateFormat) {
        Date date = null;
        SimpleDateFormat dFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat tFormat = new SimpleDateFormat("HH.mm");
        dFormat.setLenient(false);
        tFormat.setLenient(false);

        if (dateString != null && !dateString.isEmpty()) {
            try {
                date = dFormat.parse(dateString);
            } catch (ParseException ex) {
                date = null;
            }
        }

        if (date == null) {
            date = new Date();
            dateString = dFormat.format(date);
        }

        System.out.println("1 timeString: " + timeString);

        try {
            tFormat.parse(timeString);
        } catch (ParseException ex) {
            timeString = null;
        }

        if (timeString == null) {
            timeString = "00.00";
        }

        System.out.println("2 timeString: " + timeString);

        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            date = df.parse(dateString + " " + timeString);
        } catch (ParseException ex) {
            date = null;
        }

        return date;
    }
}
