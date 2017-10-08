package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import me.zp4rker.discord.microbot.util.MessageUtil;
import me.zp4rker.discord.microbot.util.ColorUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author ZP4RKER
 */
public class ReverseCommand implements ICommand {

    @RegisterCommand(aliases = "reverse",
                    description = "Returns the provided string backwards.",
                    usage = "{prefix}reverse <TEXT>")
    public void onCommand(Message message, String[] args) {
        MessageUtil.bypassDeleteLogs(message);

        StringBuilder sb = new StringBuilder();
        sb.append("`");
        sb.append(String.join(" ", args));
        sb.append("`");

        sb.reverse();

        message.getChannel().sendMessage(new EmbedBuilder()
                .setDescription(sb.toString())
                .setAuthor("Reverse text", null, null)
                .setColor(ColorUtil.randomColour()).build()).complete();
    }

}
