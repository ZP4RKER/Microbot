package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import me.zp4rker.microbot.util.ColorUtil;
import me.zp4rker.microbot.util.YamlUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import static me.zp4rker.microbot.util.MessageUtil.*;

/**
 * @author ZP4RKER
 */
public class IMcApiCommand implements ICommand {

    @RegisterCommand(aliases = "imcapi",
            description = "Gets an image from McApi",
            usage = "{prefix}imcapi <ARGUMENTS>")
    public void onCommand(Message message, String[] args) {
        bypassDeleteLogs(message);

        String url = "https://mcapi.ca";

        for (String arg : args) {
            url += "/" + arg;
        }

        if (YamlUtil.fromUrl(url).contains("_throwDispatchException")) {
            selfDestruct(sendError("Invalid query!", "Could not gather information from " + url + "\nPlease " +
                    "make sure you spelled everything correctly!", message), 6000);
            return;
        }
        message.getChannel().sendMessage(new EmbedBuilder()
                .setImage(url)
                .setFooter(url, null)
                .setColor(ColorUtil.randomColour()).build()).complete();
    }

}
