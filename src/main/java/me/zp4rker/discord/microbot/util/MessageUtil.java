package me.zp4rker.discord.microbot.util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ZP4RKER
 */
public class MessageUtil {

    public static void selfDestruct(Message message, long delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                message.delete().complete();
            }
        }, delay);
    }

    public static Message sendError(String error, String errorMessage, Message message) {
        return message.getChannel().sendMessage(new EmbedBuilder().setFooter(error, null).setDescription(errorMessage)
                                        .setColor(ColorUtil.fromRGB("255,0,0")).build()).complete();
    }

    public static void bypassDeleteLogs(Message message) {
        if (message.getGuild().getSelfMember().getPermissions().contains(Permission.MESSAGE_MANAGE)) {
            message.getTextChannel()
                    .deleteMessages(Arrays.asList(message, message.getChannel().sendMessage("`").complete())).queue();
        }
    }

}
