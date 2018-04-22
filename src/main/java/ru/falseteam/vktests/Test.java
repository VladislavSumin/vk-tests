package ru.falseteam.vktests;

import java.util.Calendar;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Calendar gmt = Calendar.getInstance();
        gmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar msk = Calendar.getInstance();
        msk.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

        System.out.println(gmt.getTime());
        System.out.println(msk.getTime());

    }
}
