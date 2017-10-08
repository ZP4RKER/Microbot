package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static me.zp4rker.discord.microbot.util.MessageUtil.*;

/**
 * @author ZP4RKER
 */
public class TimeCommand implements ICommand {

    @RegisterCommand(aliases = "time",
                    description = "Shows the time.",
                    usage = "{prefix}time <TIMEZONE>")
    public void onCommand(Message message, String[] args) {
        bypassDeleteLogs(message);

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
