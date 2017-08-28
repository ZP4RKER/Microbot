package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import me.zp4rker.microbot.util.ColorUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import static me.zp4rker.microbot.util.MessageUtil.*;

/**
 * @author ZP4RKER
 */
public class ReverseCommand implements ICommand {

    @RegisterCommand(aliases = "reverse",
                    description = "Returns the provided string backwards.",
                    usage = "{prefix}reverse <TEXT>")
    public void onCommand(Message message, String[] args) {
        bypassDeleteLogs(message);

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
