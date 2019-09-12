package com.androiddevnkds.myapplication.utils.helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAndTimeHelper {private String date;
    private String time;
    private SimpleDateFormat sdf;
    private Calendar todayDate;

    public DateAndTimeHelper() {

    }

    @SuppressLint("SimpleDateFormat")
    public String getCurrentDate(String formatTanggal) {
        todayDate = Calendar.getInstance();
        //dapatin tanggal hari ini
        sdf = new SimpleDateFormat(formatTanggal);
        date = sdf.format(todayDate.getTime());

        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public String getCurrentTime(String formatWaktu) {
        todayDate = Calendar.getInstance();
        //dapatin jam
        sdf = new SimpleDateFormat(formatWaktu);
        time = sdf.format(todayDate.getTime());

        return time;
    }
}

