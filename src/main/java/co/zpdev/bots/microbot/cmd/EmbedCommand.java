package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import co.zpdev.bots.microbot.util.ColorUtil;
import co.zpdev.bots.microbot.util.MessageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

/**
 * @author ZP4RKER
 */
public class EmbedCommand {

    @Command(aliases = "embed")
    public void onCommand(Message message, String[] args) {
        MessageUtil.bypassDeleteLogs(message);

        if (args.length < 2) {
            MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", "You did not provide enough information!\n\nUsage: ```.embed " +
                    "<TITLE>" + " <MESSAGE> <{F}Footer|{C}Colour|{RGB}R,G,B|{HEX}#000000|{I}IconURL>```\nExample: " +
                    "```.embed This_is_a_really_cool_title The underlines in the footer and header get replaced " +
                    "as spaces. {F}Powered_by_awesome! {RGB}255,0,10```", message), 10000);
            return;
        }

        String title = args[0].replace("_", " ");

        String footer = "";

        Color c = null;

        String iconUrl = null;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("{F}")) {
                footer = args[i].replace("{F}", "").replace("_", " ");
                continue;
            }
            if (args[i].startsWith("{C}")) {
                c = ColorUtil.fromString(args[i].replace("{C}", "").toUpperCase());
                continue;
            }
            if (args[i].startsWith("{RGB}")) {
                c = ColorUtil.fromRGB(args[i].replace("{RGB}", ""));
                continue;
            }
            if (args[i].startsWith("{HEX}")) {
                c = Color.decode(args[i].replace("{HEX}", ""));
                continue;
            }
            if (args[i].startsWith("{I}")) {
                iconUrl = args[i].replace("{I}", "");
                continue;
            }
            sb.append(args[i]).append(" ");

        }

        String content = sb.toString().trim();

        if (iconUrl == null) {
            message.getChannel().sendMessage(new EmbedBuilder()
                    .setAuthor(title, null, null)
                    .setFooter(footer, null)
                    .setColor(c)
                    .setDescription(content).build()).complete();
        } else {
            message.getChannel().sendMessage(new EmbedBuilder()
                    .setAuthor(title, iconUrl, null)
                    .setFooter(footer, null)
                    .setColor(c)
                    .setDescription(content).build()).complete();
        }
    }

}
