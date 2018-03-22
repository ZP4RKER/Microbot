package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import co.zpdev.bots.microbot.util.MessageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author ZP4RKER
 */
public class TimeCommand {

    @Command(aliases = "time")
    public void onCommand(Message message, String[] args) {
        MessageUtil.bypassDeleteLogs(message);

        if (args.length == 0) {
            Calendar time = GregorianCalendar.getInstance();
            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(time.get(Calendar.HOUR) + ":" + ((time.get(Calendar.MINUTE) + "").length() == 1 ? "0" +
                            time.get(Calendar.MINUTE) : time.get(Calendar.MINUTE))
                            + " " + (time.get(Calendar.AM_PM) == 1 ? "PM" : "AM")).build()).complete();
        }

        if (args.length == 1) {
            Calendar time = GregorianCalendar.getInstance(TimeZone.getTimeZone(args[0]));

            message.getChannel().sendMessage(new EmbedBuilder().setTitle(time.get(Calendar.HOUR) + ":" +
                    ((time.get(Calendar.MINUTE) + "").length() == 1 ? "0" + time.get(Calendar.MINUTE) : time.get(Calendar.MINUTE))
                    + " " + (time.get(Calendar.AM_PM) == 1 ? "PM" : "AM")).setFooter("Time in " + args[0], null).build()).complete();
        }
    }

}
