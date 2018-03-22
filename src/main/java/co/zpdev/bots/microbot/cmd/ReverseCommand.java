package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import co.zpdev.bots.microbot.util.ColorUtil;
import co.zpdev.bots.microbot.util.MessageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author ZP4RKER
 */
public class ReverseCommand {

    @Command(aliases = "reverse")
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
