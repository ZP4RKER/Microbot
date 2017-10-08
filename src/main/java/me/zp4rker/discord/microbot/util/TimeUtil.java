package me.zp4rker.discord.microbot.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @author ZP4RKER
 */
public class TimeUtil {

    public static long getMillisSince(Calendar since) {
        // Get current time
        Calendar now = GregorianCalendar.getInstance();
        // Minus now millis by since millis
        return now.toInstant().toEpochMilli() - since.toInstant().toEpochMilli();
    }

    public static long[] toHoursMinutesSeconds(long millis) {
        // Get hours
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        // Subtract hours
        millis -= TimeUnit.HOURS.toMillis(hours);
        // Get minutes
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        // Subtract minutes
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        // Get seconds
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        // Return array
        return new long[]{hours, minutes, seconds};
    }

    public static String timeAgoString(long[] time) {
        // Return string
        return time[0] + (time[0] == 1 ? " hour " : " hours ") + time[1] + (time[1] == 1 ? "" + " " +
                "minute " : " minutes ") + " and " + time[2] + (time[2] == 1 ? " second" : " seconds");
    }

    public static String timeAgoString(long millis) {
        // Get time array
        long[] time = toHoursMinutesSeconds(millis);
        // Return string
        return time[0] + (time[0] == 1 ? " hour " : " hours ") + time[1] + (time[1] == 1 ? "" + " " +
                "minute " : " minutes ") + " and " + time[2] + (time[2] == 1 ? " second" : " seconds") + " ago";
    }

}
