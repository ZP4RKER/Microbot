package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import me.zp4rker.discord.microbot.util.MessageUtil;
import me.zp4rker.discord.microbot.util.ColorUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ZP4RKER
 */
public class SelfDestructCommand implements ICommand {

    @RegisterCommand(aliases = {"sd", "selfdestruct"},
                    description = "Sends a self-destructing message.",
                    usage = "{prefix}selfdestruct <DELAY> <MESSAGE>")
    public void onCommand(Message message, String[] args) {
        MessageUtil.bypassDeleteLogs(message);

        try {
            if (args.length < 2) {
                MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", "You did not provide enough information!\n\nUsage: ```" +
                        ".selfdestruct <DELAY> <MESSAGE>```", message), 8000);
                return;
            }

            Long delay = Long.parseLong(args[0]);

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                sb.append(args[i] + " ");
            }

            DecimalFormat df = new DecimalFormat("#.##");

            Message futureMessage = message.getChannel().sendMessage(new EmbedBuilder()
                    .setFooter("Will self-destruct in " + df.format(delay / 1000) + " seconds!", null)
                    .setColor(ColorUtil.randomColour())
                    .setTitle(sb.toString()).build()).complete();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    MessageUtil.bypassDeleteLogs(futureMessage);
                }
            }, delay);
        } catch (Exception e) {
            if (e instanceof NumberFormatException)
                MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", args[0] + " is not a number!\n\nUsage: ```.selfdestruct " +
                        "<DELAY> <MESSAGE>```", message), 8000);
        }
    }

}
