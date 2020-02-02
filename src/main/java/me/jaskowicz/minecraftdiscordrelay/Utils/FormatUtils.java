package me.jaskowicz.minecraftdiscordrelay.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");;

    public static String formatTime(long duration) {
        if(duration == Long.MAX_VALUE)
            return "MAX_VALUE";
        long seconds = Math.round(duration/1000.0);
        long hours = seconds/(60*60);
        seconds %= 60*60;
        long minutes = seconds/60;
        seconds %= 60;
        return (hours>0 ? hours+" : " : "") + (minutes<10 ? "0"+minutes : minutes) + " : " + (seconds<10 ? "0"+seconds : seconds) + "";
    }

    public static long getHoursFromTime(long duration) {
        long seconds = Math.round(duration/1000.0);
        return seconds/(60*60);
    }

    public static String formatTimeFromDate(Date date) {
        return simpleDateFormat.format(date);
    }
}
